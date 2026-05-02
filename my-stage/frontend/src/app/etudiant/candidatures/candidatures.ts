import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-candidatures',
  imports: [RouterLink, CommonModule],
  templateUrl: './candidatures.html',
  styleUrl: './candidatures.css'
})
export class Candidatures implements OnInit {

  candidatures: any[] = [];

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    const utilisateur = JSON.parse(this.storage.getItem('utilisateur') || '{}');
    this.http.get<any[]>(`http://localhost:8080/api/candidatures/etudiant/${utilisateur.id}`)
      .subscribe({
        next: (data) => this.candidatures = data,
        error: () => console.log('Erreur chargement candidatures')
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
