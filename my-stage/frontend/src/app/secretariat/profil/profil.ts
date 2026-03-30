import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profil-secretariat',
  imports: [RouterLink, FormsModule],
  templateUrl: './profil.html',
  styleUrl: './profil.css'
})
export class Profil {

  nom: string = '';
  prenom: string = '';
  email: string = '';
  motDePasse: string = '';
  sexe: string = '';
  departement: string = '';

  enregistrer() {
    console.log('Profil secrétariat enregistré');
  }

}