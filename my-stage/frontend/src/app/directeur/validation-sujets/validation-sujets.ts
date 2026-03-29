import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-validation-sujets',
  imports: [RouterLink, CommonModule],
  templateUrl: './validation-sujets.html',
  styleUrl: './validation-sujets.css'
})
export class ValidationSujets {

  sujets: any[] = [];

  valider(sujet: any) {
    console.log('Sujet validé :', sujet);
  }

  refuser(sujet: any) {
    console.log('Sujet refusé :', sujet);
  }

}
