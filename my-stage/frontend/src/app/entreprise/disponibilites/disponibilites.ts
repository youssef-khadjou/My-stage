import { CommonModule } from '@angular/common';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-disponibilites',
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './disponibilites.html',
  styleUrl: './disponibilites.css'
})
export class Disponibilites implements OnInit {

  disponibilites: any[] = [];
  date: string = '';
  heureDebut: string = '';
  heureFin: string = '';
  succes: string = '';
  erreur: string = '';
  utilisateurId: number = 0;

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    const utilisateur = JSON.parse(this.storage.getItem('utilisateur') || '{}');
    this.utilisateurId = utilisateur.id;
    this.chargerDisponibilites();
  }

  chargerDisponibilites() {
    this.http.get<any[]>(`http://localhost:8080/api/disponibilites/utilisateur/${this.utilisateurId}`)
      .subscribe({
        next: (data) => {
          this.disponibilites = data;
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur chargement')
      });
  }

  ajouter() {
    if (!this.date || !this.heureDebut || !this.heureFin) {
      this.erreur = 'Veuillez remplir tous les champs.';
      return;
    }
    if (this.heureFin <= this.heureDebut) {
      this.erreur = 'L\'heure de fin doit être après l\'heure de début.';
      return;
    }

    this.http.post<any>(`http://localhost:8080/api/disponibilites/utilisateur/${this.utilisateurId}`, {
      date: this.date,
      heureDebut: this.heureDebut,
      heureFin: this.heureFin
    }).subscribe({
      next: (data) => {
        this.disponibilites.push(data);
        this.cdr.detectChanges();
        this.succes = 'Disponibilité ajoutée !';
        this.erreur = '';
        this.date = '';
        this.heureDebut = '';
        this.heureFin = '';
      },
      error: () => this.erreur = 'Erreur lors de l\'ajout.'
    });
  }

  supprimer(id: number) {
    if (!confirm('Supprimer cette disponibilité ?')) return;
    this.http.delete(`http://localhost:8080/api/disponibilites/${id}`)
      .subscribe({
        next: () => {
          this.disponibilites = this.disponibilites.filter(d => d.id !== id);
          this.cdr.detectChanges();
        },
        error: () => this.erreur = 'Erreur lors de la suppression.'
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }
}
