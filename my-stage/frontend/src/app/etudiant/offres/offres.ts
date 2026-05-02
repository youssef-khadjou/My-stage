import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-offres',
  imports: [RouterLink, CommonModule],
  templateUrl: './offres.html',
  styleUrl: './offres.css'
})
export class Offres implements OnInit {

  offres: any[] = [];

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/sujets/valides')
      .subscribe({
        next: (data) => this.offres = data,
        error: () => console.log('Erreur chargement offres')
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
