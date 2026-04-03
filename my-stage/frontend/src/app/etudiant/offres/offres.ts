import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-offres',
  imports: [RouterLink, CommonModule],
  templateUrl: './offres.html',
  styleUrl: './offres.css'
})
export class Offres {

  constructor(private router: Router) {}

  offres: any[] = [];

}
