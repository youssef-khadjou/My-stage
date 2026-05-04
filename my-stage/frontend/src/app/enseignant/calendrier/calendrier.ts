import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { StorageService } from '../../services/storage.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-calendrier',
  imports: [RouterLink, CommonModule],
  templateUrl: './calendrier.html',
  styleUrl: './calendrier.css'
})
export class Calendrier implements OnInit {

  mois: number = new Date().getMonth();
  annee: number = new Date().getFullYear();
  jourSelectionne: number = new Date().getDate();
  jours: number[] = [];
  soutenances: any[] = [];
  toutesLesSoutenances: any[] = [];
  etudiants: any[] = [];

  nomsMois = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin',
    'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];

  constructor(
    private router: Router,
    private storage: StorageService,
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  get nomMoisActuel(): string {
    return this.nomsMois[this.mois];
  }

  ngOnInit() {
    this.genererCalendrier();
    this.http.get<any[]>('http://localhost:8080/api/soutenances')
      .subscribe({
        next: (soutenancesData) => {
          this.http.get<any[]>('http://localhost:8080/api/etudiants')
            .subscribe({
              next: (etudiantsData) => {
                this.etudiants = etudiantsData;
                this.toutesLesSoutenances = soutenancesData.map(s => {
                  s.etudiantObj = etudiantsData.find(e => e.id == s.etudiantId);
                  return s;
                });
                this.filtrerSoutenances();
                this.jours = [...this.jours];
                this.cdr.detectChanges();
              }
            });
        },
        error: () => console.log('Erreur chargement soutenances')
      });
  }

  filtrerSoutenances() {
    const dateSelectionnee = `${this.annee}-${String(this.mois + 1).padStart(2, '0')}-${String(this.jourSelectionne).padStart(2, '0')}`;
    this.soutenances = this.toutesLesSoutenances.filter(s => s.dateSoutenance === dateSelectionnee);
  }

  genererCalendrier() {
    const premierJour = new Date(this.annee, this.mois, 1).getDay();
    const nbJours = new Date(this.annee, this.mois + 1, 0).getDate();
    const decalage = premierJour === 0 ? 6 : premierJour - 1;
    this.jours = [];
    for (let i = 0; i < decalage; i++) this.jours.push(0);
    for (let i = 1; i <= nbJours; i++) this.jours.push(i);
  }

  moisPrecedent() {
    if (this.mois === 0) { this.mois = 11; this.annee--; }
    else this.mois--;
    this.genererCalendrier();
    this.filtrerSoutenances();
  }

  moisSuivant() {
    if (this.mois === 11) { this.mois = 0; this.annee++; }
    else this.mois++;
    this.genererCalendrier();
    this.filtrerSoutenances();
  }

  selectionnerJour(jour: number) {
    this.jourSelectionne = jour;
    this.filtrerSoutenances();
  }

  aSoutenance(jour: number): boolean {
    if (jour === 0) return false;
    const date = `${this.annee}-${String(this.mois + 1).padStart(2, '0')}-${String(jour).padStart(2, '0')}`;
    return this.toutesLesSoutenances.some(s => s.dateSoutenance === date);
  }

  trackByJour(index: number, jour: number): number {
    return index;
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }
}
