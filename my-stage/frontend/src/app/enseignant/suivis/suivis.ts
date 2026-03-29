import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-suivis',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './suivis.html',
  styleUrl: './suivis.css'
})
export class Suivis {

  etudiantsSuivis: any[] = [];
  resultatsRecherche: any[] = [];
  promotionFiltre: string = '';
  lieuFiltre: string = '';
  rechercheLancee: boolean = false;

  rechercher() {
    this.rechercheLancee = true;
    console.log('Recherche :', this.promotionFiltre, this.lieuFiltre);
  }

  suivre(etudiant: any) {
    this.etudiantsSuivis.push(etudiant);
    this.resultatsRecherche = this.resultatsRecherche.filter(e => e !== etudiant);
    console.log('Étudiant suivi :', etudiant);
  }

}
