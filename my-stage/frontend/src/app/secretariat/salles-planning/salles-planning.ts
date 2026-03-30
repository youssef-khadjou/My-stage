import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-salles-planning',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './salles-planning.html',
  styleUrl: './salles-planning.css'
})
export class SallesPlanning {
  salle = '';

  jours = ['LUN 21', 'MAR 22', 'MER 23', 'JEU 24', 'VEN 25', 'SAM 26', 'DIM 27'];

  heures = [
    '8 H', '9 H', '10 H', '11 H', '12 H',
    '13 H', '14 H', '15 H', '16 H', '17 H'
  ];

  events = [
    { jour: 'LUN 21', heure: '9 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'LUN 21', heure: '11 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'LUN 21', heure: '15 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'LUN 21', heure: '16 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },

    { jour: 'MAR 22', heure: '10 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'MAR 22', heure: '11 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'MAR 22', heure: '14 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'MAR 22', heure: '17 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },

    { jour: 'MER 23', heure: '8 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'MER 23', heure: '9 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'MER 23', heure: '11 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'MER 23', heure: '13 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },

    { jour: 'JEU 24', heure: '10 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'JEU 24', heure: '16 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'JEU 24', heure: '17 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },

    { jour: 'VEN 25', heure: '8 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'VEN 25', heure: '9 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'VEN 25', heure: '10 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'VEN 25', heure: '11 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' },
    { jour: 'VEN 25', heure: '14 H', etudiant: 'XXXXXX', enseignant: 'XXXXXX', entreprise: 'XXXXXX' }
  ];

  constructor(private route: ActivatedRoute, private router: Router) {
    this.route.queryParams.subscribe(params => {
      this.salle = params['salle'] || 'Salle 1';
    });
  }

  getEvent(jour: string, heure: string) {
    return this.events.find(e => e.jour === jour && e.heure === heure);
  }

  retour() {
    this.router.navigate(['/secretariat/salles']);
  }
}