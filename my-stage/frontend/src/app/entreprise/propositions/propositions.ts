import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-propositions',
  imports: [CommonModule, RouterLink],
  templateUrl: './propositions.html',
  styleUrl: './propositions.css'
})
export class Propositions {

  propositions = [
    {
      titre: 'Développeur Full-Stack Java',
      etudiant: 'XXXXXXXXXX',
      statut: 'XXXXXXXXXX'
    },
    {
      titre: 'Data Analyst - Python/SQL',
      etudiant: 'XXXXXXXXXX',
      statut: 'XXXXXXXXXX'
    },
    {
      titre: 'Développeur Mobile React',
      etudiant: 'XXXXXXXXXX',
      statut: 'XXXXXXXXXX'
    },
    {
      titre: 'Architecte Cloud',
      etudiant: 'XXXXXXXXXX',
      statut: 'XXXXXXXXXX'
    }
  ];

  voirProposition(proposition: any) {
    console.log('Voir proposition :', proposition);
  }

}
