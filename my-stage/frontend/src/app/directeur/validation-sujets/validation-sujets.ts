import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-validation-sujets',
  imports: [RouterLink, CommonModule],
  templateUrl: './validation-sujets.html',
  styleUrl: './validation-sujets.css'
})
export class ValidationSujets implements OnInit {

  sujets: any[] = [];

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/sujets/en-attente')
      .subscribe({
        next: (data) => this.sujets = data,
        error: () => console.log('Erreur chargement sujets')
      });
  }

  valider(sujet: any) {
    this.http.put<any>(`http://localhost:8080/api/sujets/${sujet.id}/valider`, {})
      .subscribe({
        next: () => this.sujets = this.sujets.filter(s => s.id !== sujet.id),
        error: () => console.log('Erreur validation')
      });
  }

  refuser(sujet: any) {
    this.http.put<any>(`http://localhost:8080/api/sujets/${sujet.id}/refuser`, {})
      .subscribe({
        next: () => this.sujets = this.sujets.filter(s => s.id !== sujet.id),
        error: () => console.log('Erreur refus')
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
