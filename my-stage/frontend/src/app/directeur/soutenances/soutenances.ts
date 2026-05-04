import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-soutenances',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './soutenances.html',
  styleUrl: './soutenances.css'
})
export class Soutenances implements OnInit {

  etudiantSelectionne: string = '';
  date: string = '';
  salle: string = '';
  heure: string = '';
  soutenances: any[] = [];
  etudiants: any[] = [];
  succes: string = '';

  constructor(
    private router: Router,
    private http: HttpClient,
    private storage: StorageService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/soutenances')
      .subscribe({
        next: (soutenancesData) => {
          this.http.get<any[]>('http://localhost:8080/api/etudiants')
            .subscribe({
              next: (etudiantsData) => {
                this.etudiants = etudiantsData;
                this.soutenances = soutenancesData.map(s => {
                  s.etudiant = etudiantsData.find(e => e.id == s.etudiantId);
                  return s;
                });
                this.cdr.detectChanges();
              },
              error: () => console.log('Erreur chargement étudiants')
            });
        },
        error: () => console.log('Erreur chargement soutenances')
      });
  }

  ajouter() {
    if (!this.date || !this.heure || !this.salle) return;

    const etudiantChoisi = this.etudiants.find(e => e.id == this.etudiantSelectionne);

    const body = {
      etudiantId: this.etudiantSelectionne,
      date: this.date,
      heure: this.heure,
      salle: this.salle
    };

    this.http.post<any>('http://localhost:8080/api/soutenances', body)
      .subscribe({
        next: (data) => {
          data.etudiant = etudiantChoisi;
          this.soutenances = [...this.soutenances, data];
          this.succes = 'Soutenance ajoutée avec succès !';
          this.etudiantSelectionne = '';
          this.date = '';
          this.salle = '';
          this.heure = '';
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur ajout soutenance')
      });
  }

  supprimer(soutenance: any) {
    this.http.delete(`http://localhost:8080/api/soutenances/${soutenance.id}`)
      .subscribe({
        next: () => {
          this.soutenances = [...this.soutenances.filter(s => s.id !== soutenance.id)];
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur suppression soutenance')
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }
}
