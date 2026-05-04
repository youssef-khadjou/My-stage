import { Component, OnInit, PLATFORM_ID, inject, ChangeDetectorRef } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Router, RouterLink, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-confirmer-stage',
  imports: [RouterLink, CommonModule],
  templateUrl: './confirmer-stage.html',
  styleUrl: './confirmer-stage.css'
})
export class ConfirmerStage implements OnInit {

  candidaturesAConfirmer: any[] = [];
  candidaturesConfirmees: any[] = [];
  candidaturesRefusees: any[] = [];
  chargement = true;

  private platformId = inject(PLATFORM_ID);

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    if (!isPlatformBrowser(this.platformId)) return;

    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.chargerCandidatures();
    });

    this.chargerCandidatures();
  }

  chargerCandidatures() {
    const utilisateur = JSON.parse(localStorage.getItem('utilisateur') || '{}');
    this.chargement = true;

    this.http.get<any[]>(`http://localhost:8080/api/candidatures/etudiant/${utilisateur.id}`)
      .subscribe({
        next: (data) => {
          this.candidaturesAConfirmer = data.filter(c => c.statut === 'ACCEPTEE');
          this.candidaturesConfirmees  = data.filter(c => c.statut === 'CONFIRMEE');
          this.candidaturesRefusees    = data.filter(c => c.statut === 'REFUSEE_ETUDIANT');
          this.chargement = false;
          this.cdr.detectChanges();
        },
        error: () => {
          console.log('Erreur chargement candidatures');
          this.chargement = false;
          this.cdr.detectChanges();
        }
      });
  }

  confirmer(candidatureId: number) {
    this.http.put(`http://localhost:8080/api/candidatures/${candidatureId}/statut`, { statut: 'CONFIRMEE' })
      .subscribe({
        next: () => {
          const idx = this.candidaturesAConfirmer.findIndex(c => c.id === candidatureId);
          if (idx !== -1) {
            const c = { ...this.candidaturesAConfirmer[idx], statut: 'CONFIRMEE' };
            this.candidaturesAConfirmer.splice(idx, 1);
            this.candidaturesConfirmees.push(c);
            this.cdr.detectChanges();
          }
        },
        error: () => alert('Erreur lors de la confirmation.')
      });
  }

  refuser(candidatureId: number) {
    this.http.put(`http://localhost:8080/api/candidatures/${candidatureId}/statut`, { statut: 'REFUSEE_ETUDIANT' })
      .subscribe({
        next: () => {
          const idx = this.candidaturesAConfirmer.findIndex(c => c.id === candidatureId);
          if (idx !== -1) {
            const c = { ...this.candidaturesAConfirmer[idx], statut: 'REFUSEE_ETUDIANT' };
            this.candidaturesAConfirmer.splice(idx, 1);
            this.candidaturesRefusees.push(c);
            this.cdr.detectChanges();
          }
        },
        error: () => alert('Erreur lors du refus.')
      });
  }

  annulerConfirmation(candidatureId: number) {
    this.http.put(`http://localhost:8080/api/candidatures/${candidatureId}/statut`, { statut: 'ACCEPTEE' })
      .subscribe({
        next: () => {
          const idx = this.candidaturesConfirmees.findIndex(c => c.id === candidatureId);
          if (idx !== -1) {
            const c = { ...this.candidaturesConfirmees[idx], statut: 'ACCEPTEE' };
            this.candidaturesConfirmees.splice(idx, 1);
            this.candidaturesAConfirmer.push(c);
            this.cdr.detectChanges();
          }
        },
        error: () => alert('Erreur lors de l\'annulation.')
      });
  }

  annulerRefus(candidatureId: number) {
    this.http.put(`http://localhost:8080/api/candidatures/${candidatureId}/statut`, { statut: 'ACCEPTEE' })
      .subscribe({
        next: () => {
          const idx = this.candidaturesRefusees.findIndex(c => c.id === candidatureId);
          if (idx !== -1) {
            const c = { ...this.candidaturesRefusees[idx], statut: 'ACCEPTEE' };
            this.candidaturesRefusees.splice(idx, 1);
            this.candidaturesAConfirmer.push(c);
            this.cdr.detectChanges();
          }
        },
        error: () => alert('Erreur lors de l\'annulation.')
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }
}
