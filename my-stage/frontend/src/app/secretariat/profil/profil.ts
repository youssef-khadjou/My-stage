import { Component, OnInit, PLATFORM_ID, inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-profil-secretariat',
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
  initiales: string = '';

  private platformId = inject(PLATFORM_ID);

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    if (!isPlatformBrowser(this.platformId)) return;
    const utilisateur = JSON.parse(localStorage.getItem('utilisateur') || '{}');
    this.nom = utilisateur.nom || '';
    this.prenom = utilisateur.prenom || '';
    this.email = utilisateur.email || '';
    const n = utilisateur.nom?.charAt(0) || '';
    const p = utilisateur.prenom?.charAt(0) || '';
    this.initiales = (n + '.' + p).toUpperCase();
  }

  enregistrer() {
    if (!isPlatformBrowser(this.platformId)) return;
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
