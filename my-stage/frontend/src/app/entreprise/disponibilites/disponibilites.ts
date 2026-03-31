import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-disponibilites',
  imports: [CommonModule, RouterLink],
  templateUrl: './disponibilites.html',
  styleUrl: './disponibilites.css'
})
export class Disponibilites {

  jours = ['LUN 21', 'MAR 22', 'MER 23', 'JEU 24', 'VEN 25', 'SAM 26', 'DIM 27'];
  heures = ['8 H', '9 H', '10 H', '11 H', '12 H', '13 H', '14 H', '15 H', '16 H', '17 H'];

  creneauxSelectionnes: { jour: string; heure: string }[] = [
    { jour: 'MER 23', heure: '8 H' },
    { jour: 'VEN 25', heure: '8 H' },
    { jour: 'JEU 24', heure: '10 H' },
    { jour: 'LUN 21', heure: '11 H' },
    { jour: 'MER 23', heure: '13 H' },
    { jour: 'VEN 25', heure: '14 H' },
    { jour: 'LUN 21', heure: '15 H' },
    { jour: 'MAR 22', heure: '17 H' }
  ];

  estSelectionne(jour: string, heure: string): boolean {
    return this.creneauxSelectionnes.some(
      creneau => creneau.jour === jour && creneau.heure === heure
    );
  }

  toggleCreneau(jour: string, heure: string) {
    const index = this.creneauxSelectionnes.findIndex(
      creneau => creneau.jour === jour && creneau.heure === heure
    );

    if (index !== -1) {
      this.creneauxSelectionnes.splice(index, 1);
    } else {
      this.creneauxSelectionnes.push({ jour, heure });
    }
  }

  enregistrer() {
    console.log('Disponibilités enregistrées :', this.creneauxSelectionnes);
  }

  heureAffichage(heure: string): string {
    return heure.toLowerCase().replace(' h', ':00');
  }

}
