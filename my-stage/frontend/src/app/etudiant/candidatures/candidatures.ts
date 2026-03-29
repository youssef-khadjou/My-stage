import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-candidatures',
  imports: [RouterLink],
  templateUrl: './candidatures.html',
  styleUrl: './candidatures.css'
})
export class Candidatures {

  constructor(private router: Router) {}

  stage = {
    entreprise: '',
    poste: '',
    debut: '',
    fin: '',
    gratification: '',
    tuteur: '',
    lieu: ''
  };

  confirmer() {
    console.log('Stage confirmé');
  }

  refuser() {
    console.log('Stage refusé');
  }

}
