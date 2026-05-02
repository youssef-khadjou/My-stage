import { Component } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

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

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  rechercher() {
    let url = 'http://localhost:8080/api/etudiants';
    if (this.promotion) {
      url += `/niveau/${this.promotion}`;
    }
    this.http.get<any[]>(url).subscribe({
      next: (data) => this.etudiants = data,
      error: () => console.log('Erreur chargement étudiants')
    });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
