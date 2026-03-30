import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-salles',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './salles.html',
  styleUrl: './salles.css'
})
export class Salles {
  salles = ['Salle 1', 'Salle 2', 'Salle 3', 'Salle 4'];
  selectedSalle: string = '';

  constructor(private router: Router) {}

  rechercher() {
    if (this.selectedSalle) {
      this.router.navigate(['/secretariat/salles-planning'], {
        queryParams: { salle: this.selectedSalle }
      });
    }
  }
}