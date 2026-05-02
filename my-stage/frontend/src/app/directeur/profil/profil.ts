import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-profil',
  imports: [RouterLink, FormsModule],
  templateUrl: './profil.html',
  styleUrl: './profil.css'
})
export class Profil implements OnInit {

  nom: string = '';
  prenom: string = '';
  email: string = '';
  motDePasse: string = '';
  sexe: string = '';
  departement: string = '';
  succes: string = '';
  erreur: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    const utilisateur = JSON.parse(localStorage.getItem('utilisateur') || '{}');
    this.nom = utilisateur.nom || '';
    this.prenom = utilisateur.prenom || '';
    this.email = utilisateur.email || '';
  }

  enregistrer() {
    const utilisateur = JSON.parse(localStorage.getItem('utilisateur') || '{}');
    this.http.put<any>(`http://localhost:8080/api/utilisateurs/${utilisateur.id}`, {
      nom: this.nom,
      prenom: this.prenom,
      email: this.email,
      role: utilisateur.role
    }).subscribe({
      next: (data) => {
        localStorage.setItem('utilisateur', JSON.stringify(data));
        this.succes = 'Profil mis à jour !';
      },
      error: () => this.erreur = 'Erreur lors de la mise à jour'
    });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
