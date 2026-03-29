import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-confirmer-stage',
  imports: [RouterLink],
  templateUrl: './confirmer-stage.html',
  styleUrl: './confirmer-stage.css'
})
export class ConfirmerStage {

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
