import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

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

  nomsMois = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin',
    'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];

  get nomMoisActuel(): string {
    return this.nomsMois[this.mois];
  }

  ngOnInit() {
    this.genererCalendrier();
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
  }

  moisSuivant() {
    if (this.mois === 11) { this.mois = 0; this.annee++; }
    else this.mois++;
    this.genererCalendrier();
  }

  selectionnerJour(jour: number) {
    this.jourSelectionne = jour;
  }

  sInscrire(soutenance: any) {
    console.log('Inscrit comme jury pour :', soutenance);
  }

}
