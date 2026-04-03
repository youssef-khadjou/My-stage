import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-compte-rendu-visite',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './compte-rendu-visite.html',
  styleUrl: './compte-rendu-visite.css'
})
export class CompteRenduVisite {
  etape: number = 1;

  stagiaire: string = '';
  typeVisite: string = '';
  dateVisite: string = '';
  heureVisite: string = '';

  presenceStagiaire: string = '';
  travauxRealises: string = '';
  evaluationCompetences: string = '';
  pointsForts: string = '';
  axesAmelioration: string = '';
  objectifsProchaineVisite: string = '';

  confirmer() {
    this.etape = 2;
  }

  retourEtape1() {
    this.etape = 1;
  }

  soumettre() {
    console.log('Compte rendu soumis', {
      stagiaire: this.stagiaire,
      typeVisite: this.typeVisite,
      dateVisite: this.dateVisite,
      heureVisite: this.heureVisite,
      presenceStagiaire: this.presenceStagiaire,
      travauxRealises: this.travauxRealises,
      evaluationCompetences: this.evaluationCompetences,
      pointsForts: this.pointsForts,
      axesAmelioration: this.axesAmelioration,
      objectifsProchaineVisite: this.objectifsProchaineVisite
    });
  }
}
