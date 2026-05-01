import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {

  nom: string = '';
  prenom: string = '';
  sexe: string = '';
  fonction: string = '';
  departement: string = '';
  email: string = '';
  motDePasse: string = '';
  confirmation: string = '';
  erreur: string = '';
  succes: string = '';

  constructor(private router: Router, private http: HttpClient) {}

  creerCompte() {
    if (this.motDePasse !== this.confirmation) {
      this.erreur = 'Les mots de passe ne correspondent pas';
      return;
    }

    this.http.post<any>('http://localhost:8080/api/auth/register', {
      nom: this.nom,
      prenom: this.prenom,
      email: this.email,
      motDePasse: this.motDePasse,
      telephone: '',
      role: this.fonction.toUpperCase()
    }).subscribe({
      next: () => {
        this.succes = 'Compte créé avec succès !';
        setTimeout(() => this.router.navigate(['/']), 2000);
      },
      error: (err) => {
        this.erreur = err.error?.message || 'Erreur lors de la création du compte';
      }
    });
  }

}
