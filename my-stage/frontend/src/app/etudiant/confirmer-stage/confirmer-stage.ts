import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-confirmer-stage',
  imports: [RouterLink, CommonModule],
  templateUrl: './confirmer-stage.html',
  styleUrl: './confirmer-stage.css'
})
export class ConfirmerStage implements OnInit {

  stage: any = null;
  candidatureId: number | null = null;

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    const utilisateur = JSON.parse(localStorage.getItem('utilisateur') || '{}');
    this.http.get<any[]>(`http://localhost:8080/api/candidatures/etudiant/${utilisateur.id}`)
      .subscribe({
        next: (data) => {
          const acceptee = data.find(c => c.statut === 'ACCEPTE');
          if (acceptee) {
            this.candidatureId = acceptee.id;
            this.stage = acceptee.stage;
          }
        },
        error: () => console.log('Erreur chargement candidature')
      });
  }

  confirmer() {
    console.log('Stage confirmé');
    this.router.navigate(['/etudiant']);
  }

  refuser() {
    console.log('Stage refusé');
    this.router.navigate(['/etudiant']);
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
