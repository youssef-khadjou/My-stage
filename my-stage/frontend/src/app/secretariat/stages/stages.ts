import { CommonModule } from '@angular/common';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
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

  candidatures: any[] = [];
  candidaturesFiltrees: any[] = [];
  recherche: string = '';
  erreur: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/candidatures').subscribe({
      next: (data) => {
        this.candidatures = data.filter(c => c.statut === 'CONFIRMEE');
        this.candidaturesFiltrees = [...this.candidatures];
        this.cdr.detectChanges();
      },
      error: () => this.erreur = 'Erreur lors du chargement des stages'
    });
  }

  filtrer() {
    const r = this.recherche.toLowerCase();
    this.candidaturesFiltrees = this.candidatures.filter(c =>
      c.etudiant?.utilisateur?.nom?.toLowerCase().includes(r) ||
      c.etudiant?.utilisateur?.prenom?.toLowerCase().includes(r) ||
      c.stage?.titre?.toLowerCase().includes(r) ||
      c.stage?.entreprise?.nomEntreprise?.toLowerCase().includes(r) ||
      c.etudiant?.niveau?.toLowerCase().includes(r)
    );
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }
}
