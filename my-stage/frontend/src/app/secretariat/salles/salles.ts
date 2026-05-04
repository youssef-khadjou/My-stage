import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-salles',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './salles.html',
  styleUrl: './salles.css'
})
export class Salles implements OnInit {

  salles: string[] = [];
  selectedSalle: string = '';
  creneaux: any[] = [];
  jours: string[] = [];
  heures = ['8 H', '9 H', '10 H', '11 H', '12 H', '13 H', '14 H', '15 H', '16 H', '17 H'];

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.http.get<string[]>('http://localhost:8080/api/soutenances/salles')
      .subscribe({
        next: (data) => {
          this.salles = data;
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur chargement salles')
      });
  }

  rechercher() {
    if (!this.selectedSalle) return;

    this.http.get<any[]>(`http://localhost:8080/api/soutenances/salle/${this.selectedSalle}`)
      .subscribe({
        next: (data) => {
          this.creneaux = data;
          this.jours = [...new Set(data.map(c => c.dateSoutenance))].sort() as string[];
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur chargement créneaux')
      });
  }

  getEvent(jour: string, heure: string): any {
    const h = heure.replace(' H', '').trim().padStart(2, '0') + ':';
    return this.creneaux.find(c =>
      c.dateSoutenance === jour && c.heureDebut?.startsWith(h)
    );
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }
}
