import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
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
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/soutenances')
      .subscribe({
        next: (data) => this.soutenances = data,
        error: () => console.log('Erreur chargement soutenances')
      });

    this.http.get<any[]>('http://localhost:8080/api/stages')
      .subscribe({
        next: (data) => this.etudiants = data,
        error: () => console.log('Erreur chargement étudiants')
      });
  }

  ajouter() {
    const body = {
      etudiantId: this.etudiantSelectionne,
      date: this.date,
      heure: this.heure,
      salle: this.salle
    };
    this.http.post<any>('http://localhost:8080/api/soutenances', body)
      .subscribe({
        next: (data) => {
          this.soutenances.push(data);
          this.succes = 'Soutenance planifiée avec succès !';
          this.etudiantSelectionne = '';
          this.date = '';
          this.salle = '';
          this.heure = '';
        },
        error: () => console.log('Erreur ajout soutenance')
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
