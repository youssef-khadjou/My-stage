import { CommonModule } from '@angular/common';
import { Component, OnInit, PLATFORM_ID, inject, ChangeDetectorRef } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { RouterLink, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-propositions',
  imports: [CommonModule, RouterLink],
  templateUrl: './propositions.html',
  styleUrl: './propositions.css'
})
export class Propositions implements OnInit {

  propositions: any[] = [];
  private platformId = inject(PLATFORM_ID);

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    if (!isPlatformBrowser(this.platformId)) return;
    const utilisateur = JSON.parse(this.storage.getItem('utilisateur') || '{}');
    const entrepriseId = utilisateur.id;

    this.http.get<any[]>(`http://localhost:8080/api/sujets/entreprise/${entrepriseId}`)
      .subscribe({
        next: (data) => {
          this.propositions = data;
          this.cdr.detectChanges(); // ← force Angular à rafraîchir
        },
        error: (err) => console.log('Erreur chargement propositions', err)
      });
  }

  supprimer(id: number) {
    this.http.delete(`http://localhost:8080/api/sujets/${id}`)
      .subscribe({
        next: () => {
          this.propositions = this.propositions.filter(p => p.id !== id);
          this.cdr.detectChanges();
        },
        error: (err) => console.log('Erreur suppression', err)
      });
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }
}
