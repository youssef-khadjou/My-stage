import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-proposer-sujet',
  imports: [FormsModule, RouterLink],
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

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.fichierNom = input.files[0].name;
    }
  }

  publierOffre() {
    console.log('Offre publiée');
  }

}
