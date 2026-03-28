import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
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

  constructor(private router: Router) {}

  creerCompte() {
    console.log('Création compte pour', this.email, 'fonction:', this.fonction);
  }

}
