import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-proposer-sujet',
  imports: [FormsModule, RouterLink, CommonModule],
  templateUrl: './proposer-sujet.html',
  styleUrl: './proposer-sujet.css'
})
export class ProposerSujet {

  titrePoste: string = '';
  description: string = '';
  duree: string = '';
  gratification: string = '';
  competences: string = '';
  teletravail: string = '';
  fichierNom: string = '';
  succes: string = '';
  erreur: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.fichierNom = input.files[0].name;
    }
  }

  publierOffre() {
    const utilisateur = JSON.parse(localStorage.getItem('utilisateur') || '{}');
    const entrepriseId = utilisateur.id;

    this.http.post<any>(`http://localhost:8080/api/sujets?entrepriseId=${entrepriseId}`, {
      intitule: this.titrePoste,
      description: this.description,
      competencesRequises: this.competences,
      dureeSemaines: parseInt(this.duree) || 0,
      remuneration: parseFloat(this.gratification) || 0,
      lieuTravail: this.teletravail
    }).subscribe({
      next: () => {
        this.succes = 'Offre publiée avec succès !';
        setTimeout(() => this.router.navigate(['/entreprise/propositions']), 2000);
      },
      error: () => {
        this.erreur = 'Erreur lors de la publication';
      }
    });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
