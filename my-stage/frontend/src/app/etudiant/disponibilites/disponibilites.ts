import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-disponibilites',
  imports: [RouterLink, CommonModule],
  templateUrl: './disponibilites.html',
  styleUrl: './disponibilites.css'
})
export class Disponibilites {

  constructor(private router: Router) {}

  heures = [8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19];

  jours: { nom: string, num: number, date: Date }[] = [];

  disponibilites: { jour: number, heure: number }[] = [];

  ngOnInit() {
    this.genererSemaine();
  }

  genererSemaine() {
    const nomsJours = ['DIM', 'LUN', 'MAR', 'MER', 'JEU', 'VEN', 'SAM'];
    const today = new Date();
    const lundi = new Date(today);
    lundi.setDate(today.getDate() - today.getDay() + 1);

    this.jours = [];
    for (let i = 0; i < 7; i++) {
      const date = new Date(lundi);
      date.setDate(lundi.getDate() + i);
      this.jours.push({
        nom: nomsJours[date.getDay()],
        num: date.getDate(),
        date: date
      });
    }
  }

  isDisponible(jour: number, heure: number): boolean {
    return this.disponibilites.some(d => d.jour === jour && d.heure === heure);
  }

  toggleDisponibilite(jour: number, heure: number) {
    if (this.isDisponible(jour, heure)) {
      this.disponibilites = this.disponibilites.filter(d => !(d.jour === jour && d.heure === heure));
    } else {
      this.disponibilites.push({ jour, heure });
    }
  }

  enregistrer() {
    console.log('Disponibilités enregistrées', this.disponibilites);
  }

}
