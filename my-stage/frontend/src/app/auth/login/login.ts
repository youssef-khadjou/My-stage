import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login implements OnInit {

  role: string = '';
  email: string = '';
  motDePasse: string = '';
  erreur: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.role = this.route.snapshot.paramMap.get('role') || '';
  }

  seConnecter() {
    this.http.post<any>('http://localhost:8080/api/auth/login', {
      email: this.email,
      motDePasse: this.motDePasse
    }).subscribe({
      next: (reponse) => {
        localStorage.setItem('utilisateur', JSON.stringify(reponse));
        const role = reponse.role.toLowerCase();
        this.router.navigate(['/' + role]);
      },
      error: () => {
        this.erreur = 'Email ou mot de passe incorrect';
      }
    });
  }

}
