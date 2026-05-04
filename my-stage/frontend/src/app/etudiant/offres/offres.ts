import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-offres',
  imports: [RouterLink, CommonModule],
  templateUrl: './offres.html',
  styleUrl: './offres.css'
})
export class Offres implements OnInit {

  offres: any[] = [];
  candidatures: any[] = [];
  succes: string = '';
  erreur: string = '';
  utilisateurId: number = 0;

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    const utilisateur = JSON.parse(this.storage.getItem('utilisateur') || '{}');
    this.utilisateurId = utilisateur.id;

    this.http.get<any[]>('http://localhost:8080/api/stages')
      .subscribe({
        next: (data) => {
          this.offres = data.filter(s => s.statut === 'VALIDE');
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur chargement offres')
      });

    this.http.get<any[]>(`http://localhost:8080/api/candidatures/etudiant/${this.utilisateurId}`)
      .subscribe({
        next: (data) => {
          this.candidatures = data;
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur chargement candidatures')
      });
  }

  dejaCandidat(stageId: number): boolean {
    return this.candidatures.some(c => c.stage?.id === stageId);
  }

  getCandidatureId(stageId: number): number | null {
    const c = this.candidatures.find(c => c.stage?.id === stageId);
    return c ? c.id : null;
  }

  postuler(offre: any) {
    this.http.post<any>('http://localhost:8080/api/candidatures', {
      etudiantId: this.utilisateurId,
      stageId: offre.id
    }).subscribe({
      next: () => {
        // Recharger les candidatures pour avoir le bon objet
        this.http.get<any[]>(`http://localhost:8080/api/candidatures/etudiant/${this.utilisateurId}`)
          .subscribe({
            next: (data) => {
              this.candidatures = data;
              this.succes = 'Candidature envoyée avec succès !';
              this.erreur = '';
              this.cdr.detectChanges();
            }
          });
      },
      error: (err) => {
        this.erreur = err.error?.message || 'Erreur lors de la candidature';
        this.cdr.detectChanges();
      }
    });
  }

  annuler(stageId: number) {
    const id = this.getCandidatureId(stageId);
    if (!id) return;

    this.http.delete(`http://localhost:8080/api/candidatures/${id}`)
      .subscribe({
        next: () => {
          this.candidatures = this.candidatures.filter(c => c.stage?.id !== stageId);
          this.succes = 'Candidature annulée.';
          this.erreur = '';
          this.cdr.detectChanges();
        },
        error: () => {
          this.erreur = 'Erreur lors de l\'annulation.';
          this.cdr.detectChanges();
        }
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }
}
