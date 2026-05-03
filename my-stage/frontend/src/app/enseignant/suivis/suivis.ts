import { Component, OnInit, PLATFORM_ID, inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-suivis',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './suivis.html',
  styleUrl: './suivis.css'
})
export class Suivis implements OnInit {

  etudiantsSuivis: any[] = [];
  resultatsRecherche: any[] = [];
  promotionFiltre: string = '';
  lieuFiltre: string = '';
  rechercheLancee: boolean = false;

  private platformId = inject(PLATFORM_ID);

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      const saved = localStorage.getItem('etudiantsSuivis');
      if (saved) {
        this.etudiantsSuivis = JSON.parse(saved);
      }
    }
  }

  rechercher() {
    let url = 'http://localhost:8080/api/etudiants';
    if (this.promotionFiltre) {
      url += `/niveau/${this.promotionFiltre}`;
    }
    this.http.get<any[]>(url).subscribe({
      next: (data) => {
        this.resultatsRecherche = data;
        this.rechercheLancee = true;
      },
      error: () => console.log('Erreur chargement étudiants')
    });
  }

  suivre(etudiant: any) {
    // Vérifier si déjà suivi
    const dejasuivi = this.etudiantsSuivis.find(e => e.id === etudiant.id);
    if (dejasuivi) return;

    this.etudiantsSuivis.push(etudiant);
    this.resultatsRecherche = this.resultatsRecherche.filter(e => e !== etudiant);
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('etudiantsSuivis', JSON.stringify(this.etudiantsSuivis));
    }
  }

  nePlusSuivre(etudiant: any) {
    this.etudiantsSuivis = this.etudiantsSuivis.filter(e => e.id !== etudiant.id);
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('etudiantsSuivis', JSON.stringify(this.etudiantsSuivis));
    }
  }


  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
