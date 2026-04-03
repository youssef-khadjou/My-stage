import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-stages',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './stages.html',
  styleUrl: './stages.css'
})
export class Stages {
  promotionSelectionnee: string = 'master1';

  stages = [
    {
      nom: 'XXXXXXXXXX',
      prenom: 'XXXXXXXXXX',
      promotion: 'Master 1',
      entreprise: 'XXXXXXXXXX',
      sujet: 'XXXXXXXXXX',
      tuteur: 'XXXXXXXXXX'
    },
    {
      nom: 'XXXXXXXXXX',
      prenom: 'XXXXXXXXXX',
      promotion: 'Master 2',
      entreprise: 'XXXXXXXXXX',
      sujet: 'XXXXXXXXXX',
      tuteur: 'XXXXXXXXXX'
    }
  ];

  stagesFiltres = [...this.stages];

  rechercher() {
    this.stagesFiltres = this.stages.filter(stage =>
      stage.promotion.toLowerCase().replace(' ', '') === this.promotionSelectionnee
    );
  }
}