import { Component, OnInit, PLATFORM_ID, inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
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
  private platformId = inject(PLATFORM_ID);

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    if (!isPlatformBrowser(this.platformId)) return;
    const utilisateur = JSON.parse(localStorage.getItem('utilisateur') || '{}');
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
