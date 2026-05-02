import { Component, OnInit } from '@angular/core';
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

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/stages')
      .subscribe({
        next: (data) => this.etudiantsSuivis = data,
        error: () => console.log('Erreur chargement étudiants suivis')
      });
  }

  rechercher() {
    this.rechercheLancee = true;
    this.http.get<any[]>('http://localhost:8080/api/stages')
      .subscribe({
        next: (data) => this.resultatsRecherche = data,
        error: () => console.log('Erreur recherche')
      });
  }

  suivre(etudiant: any) {
    this.etudiantsSuivis.push(etudiant);
    this.resultatsRecherche = this.resultatsRecherche.filter(e => e !== etudiant);
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
