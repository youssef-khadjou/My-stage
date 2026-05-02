import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-stages',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './stages.html',
  styleUrl: './stages.css'
})
export class Stages implements OnInit {

  promotionSelectionnee: string = 'master1';
  stages: any[] = [];
  stagesFiltres: any[] = [];
  erreur: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/stages').subscribe({
      next: (data) => {
        this.stages = data;
        this.stagesFiltres = data;
      },
      error: () => this.erreur = 'Erreur lors du chargement des stages'
    });
  }

  rechercher() {
    // filtre sur le niveau de l'étudiant lié à la candidature — ici on filtre sur le statut ou titre en attendant un vrai champ promotion
    this.stagesFiltres = this.stages.filter(stage =>
      stage.statut?.toLowerCase() !== 'refuse'
    );
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }
}
