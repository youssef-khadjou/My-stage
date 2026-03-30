import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-calendrier-secretariat',
  imports: [RouterLink, CommonModule],
  templateUrl: './calendrier.html',
  styleUrl: './calendrier.css'
})
export class Calendrier {
  mois: string[] = [
    'Janvier 2026',
    'Février 2026',
    'Mars 2026',
    'Avril 2026',
    'Mai 2026',
    'Juin 2026',
    'Juillet 2026',
    'Août 2026',
    'Septembre 2026',
    'Octobre 2026',
    'Novembre 2026',
    'Décembre 2026'
  ];

  indexMois: number = 5;
  jourSelectionne: number = 18;

  joursDuMois: number[] = Array.from({ length: 31 }, (_, i) => i + 1);
  joursInactifs: number[] = [1, 2, 3, 4];

  get moisActuel(): string {
    return this.mois[this.indexMois];
  }

  moisPrecedent() {
    if (this.indexMois > 0) {
      this.indexMois--;
    }
  }

  moisSuivant() {
    if (this.indexMois < this.mois.length - 1) {
      this.indexMois++;
    }
  }

  selectionnerJour(jour: number) {
    this.jourSelectionne = jour;
  }
}