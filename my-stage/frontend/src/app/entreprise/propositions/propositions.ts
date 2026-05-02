import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-propositions',
  imports: [CommonModule, RouterLink],
  templateUrl: './propositions.html',
  styleUrl: './propositions.css'
})
export class Propositions implements OnInit {

  propositions: any[] = [];

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    const utilisateur = JSON.parse(localStorage.getItem('utilisateur') || '{}');
    const entrepriseId = utilisateur.id;

    this.http.get<any[]>(`http://localhost:8080/api/sujets/entreprise/${entrepriseId}`)
      .subscribe({
        next: (data) => this.propositions = data,
        error: () => console.log('Erreur chargement propositions')
      });
  }

  voirProposition(proposition: any) {
    console.log('Voir proposition :', proposition);
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
