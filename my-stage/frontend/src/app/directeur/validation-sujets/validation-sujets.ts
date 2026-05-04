import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { RouterLink, Router, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-validation-sujets',
  imports: [RouterLink, CommonModule],
  templateUrl: './validation-sujets.html',
  styleUrl: './validation-sujets.css'
})
export class ValidationSujets implements OnInit {

  sujets: any[] = [];
  historique: any[] = [];

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.chargerSujets();
    });

    this.chargerSujets();
  }

  chargerSujets() {
    this.http.get<any[]>('http://localhost:8080/api/sujets/en-attente')
      .subscribe({
        next: (data) => {
          this.sujets = data;
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur chargement sujets')
      });

    // Charger l'historique (validés + refusés)
    this.http.get<any[]>('http://localhost:8080/api/sujets')
      .subscribe({
        next: (data) => {
          this.historique = data.filter(s => s.statut === 'VALIDE' || s.statut === 'REFUSE');
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur chargement historique')
      });
  }

  valider(sujet: any) {
    this.http.put<any>(`http://localhost:8080/api/sujets/${sujet.id}/valider`, {})
      .subscribe({
        next: () => {
          this.sujets = this.sujets.filter(s => s.id !== sujet.id);
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur validation')
      });
  }

  refuser(sujet: any) {
    this.http.put<any>(`http://localhost:8080/api/sujets/${sujet.id}/refuser`, {})
      .subscribe({
        next: () => {
          this.sujets = this.sujets.filter(s => s.id !== sujet.id);
          this.cdr.detectChanges();
        },
        error: () => console.log('Erreur refus')
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }
}
