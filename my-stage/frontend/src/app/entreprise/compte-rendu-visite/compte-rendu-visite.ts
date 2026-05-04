import { Component, OnInit, ChangeDetectorRef, ChangeDetectionStrategy, PLATFORM_ID, inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-compte-rendu-visite',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './compte-rendu-visite.html',
  styleUrl: './compte-rendu-visite.css',
  changeDetection: ChangeDetectionStrategy.Default
})
export class CompteRenduVisite implements OnInit {

  stages: any[] = [];
  comptesRendus: any[] = [];
  stageSelectionne: string = '';
  typeVisite: string = '';
  dateVisite: string = '';
  heureVisite: string = '';
  presenceStagiaire: string = '';
  travauxRealises: string = '';
  evaluationCompetences: string = '';
  pointsForts: string = '';
  axesAmelioration: string = '';
  objectifsProchaineVisite: string = '';
  succes: string = '';
  erreur: string = '';
  estNavigateur: boolean = false;

  private platformId = inject(PLATFORM_ID);

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.estNavigateur = isPlatformBrowser(this.platformId);
    if (!this.estNavigateur) return;

    const utilisateur = JSON.parse(this.storage.getItem('utilisateur') || '{}');

    // Charger les stages de l'entreprise connectée
    this.http.get<any[]>(`http://localhost:8080/api/stages/entreprise/${utilisateur.id}`)
      .subscribe({
        next: (data) => {
          this.stages = data;
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur chargement stages')
      });
  }

  onStageChange() {
    console.log('Stage sélectionné ID:', this.stageSelectionne);
    if (this.stageSelectionne) {
      this.http.get<any[]>(`http://localhost:8080/api/visites/stage/${this.stageSelectionne}`)
        .subscribe({
          next: (data) => {
            console.log('Comptes rendus reçus:', data);
            this.comptesRendus = data;
            this.cdr.detectChanges();
          },
          error: () => console.log('Erreur chargement comptes rendus')
        });
    } else {
      this.comptesRendus = [];
    }
  }

  soumettre() {
    if (!this.stageSelectionne || !this.dateVisite) {
      this.erreur = 'Veuillez sélectionner un stage et une date.';
      return;
    }

    const compteRenduComplet = `
Présence : ${this.presenceStagiaire}
Travaux réalisés : ${this.travauxRealises}
Évaluation compétences : ${this.evaluationCompetences}
Points forts : ${this.pointsForts}
Axes d'amélioration : ${this.axesAmelioration}
Objectifs prochaine visite : ${this.objectifsProchaineVisite}
    `.trim();

    this.http.post<any>('http://localhost:8080/api/visites', {
      stageId: parseInt(this.stageSelectionne),
      dateVisite: this.dateVisite,
      present: this.presenceStagiaire === 'Oui',
      compteRendu: compteRenduComplet,
      nomTuteur: this.typeVisite
    }).subscribe({
      next: (data) => {
        this.succes = 'Compte rendu soumis avec succès !';
        this.comptesRendus.unshift(data);
        this.erreur = '';
        this.typeVisite = '';
        this.dateVisite = '';
        this.heureVisite = '';
        this.presenceStagiaire = '';
        this.travauxRealises = '';
        this.evaluationCompetences = '';
        this.pointsForts = '';
        this.axesAmelioration = '';
        this.objectifsProchaineVisite = '';
      },
      error: () => this.erreur = 'Erreur lors de la soumission'
    });
  }

  supprimerCompteRendu(idVisite: number) {
    if (!confirm('Supprimer ce compte rendu ?')) return;

    this.http.delete(`http://localhost:8080/api/visites/${idVisite}`)
      .subscribe({
        next: () => {
          this.comptesRendus = this.comptesRendus.filter(cr => cr.id !== idVisite);
          this.succes = 'Compte rendu supprimé.';
        },
        error: () => this.erreur = 'Erreur lors de la suppression.'
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }
}
