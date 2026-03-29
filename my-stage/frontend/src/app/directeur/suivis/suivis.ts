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

  promotion: string = '';
  lieu: string = '';
  etudiants: any[] = [];

  rechercher() {
    console.log('Recherche :', this.promotion, this.lieu);
  }

}
