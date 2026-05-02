import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-publications',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './publications.html',
  styleUrl: './publications.css'
})
export class Publications implements OnInit {

  promotionCible: string = '';
  sujets: any[] = [];
  succes: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/sujets/valides')
      .subscribe({
        next: (data) => this.sujets = data.map(s => ({ ...s, selectionne: false })),
        error: () => console.log('Erreur chargement sujets')
      });
  }

  publier() {
    const selectionnes = this.sujets.filter(s => s.selectionne);
    console.log('Sujets publiés :', selectionnes);
    this.succes = 'Sujets publiés avec succès !';
  }

  envoyerEmail() {
    console.log('Email envoyé à la promotion :', this.promotionCible);
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
