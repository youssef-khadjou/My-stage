import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-profil-entreprise',
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

  constructor(
    private router: Router,
    private storage: StorageService
  ) {}

  enregistrer() {
    console.log('Profil entreprise enregistré');
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
