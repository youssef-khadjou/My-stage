import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-soutenances',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './soutenances.html',
  styleUrl: './soutenances.css'
})
export class Soutenances {

  etudiantSelectionne: string = '';
  date: string = '';
  salle: string = '';
  heure: string = '';

  soutenances: any[] = [];

  ajouter() {
    console.log('Soutenance ajoutée :', this.etudiantSelectionne, this.date, this.salle, this.heure);
  }

}
