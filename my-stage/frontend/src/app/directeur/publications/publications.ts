import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-publications',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './publications.html',
  styleUrl: './publications.css'
})
export class Publications {

  promotionCible: string = '';

  sujets: any[] = [];

  publier() {
    const selectionnes = this.sujets.filter(s => s.selectionne);
    console.log('Sujets publiés :', selectionnes);
  }

  envoyerEmail() {
    console.log('Email envoyé à la promotion :', this.promotionCible);
  }

}
