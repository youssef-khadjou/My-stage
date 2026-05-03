import { Component, OnInit } from '@angular/core';
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
    private storage: StorageService
  ) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/soutenances')
      .subscribe({
        next: (data) => this.soutenances = data,
        error: () => console.log('Erreur chargement soutenances')
      });

    // Charger les étudiants depuis /api/etudiants au lieu de /api/stages
    this.http.get<any[]>('http://localhost:8080/api/etudiants')
      .subscribe({
        next: (data) => this.etudiants = data,
        error: () => console.log('Erreur chargement étudiants')
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
          console.log(data)
          data.etudiant = etudiantChoisi;
          this.soutenances.push(data);
          this.succes = 'Soutenance ajoutée avec succès !';
          this.etudiantSelectionne = '';
          this.date = '';
          this.salle = '';
          this.heure = '';
        },
        error: () => console.log('Erreur ajout soutenance')
      });
  }

  supprimer(soutenance: any) {
    this.http.delete(`http://localhost:8080/api/soutenances/${soutenance.id}`)
      .subscribe({
        next: () => {
          this.soutenances = [this.soutenances.filter(s => s.id !== soutenance.id)];
        },
        error: () => console.log('Erreur suppression soutenance')
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
