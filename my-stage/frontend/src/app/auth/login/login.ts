import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login implements OnInit {

  role: string = '';
  email: string = '';
  motDePasse: string = '';

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.role = this.route.snapshot.paramMap.get('role') || '';
  }

  seConnecter() {
    console.log('Connexion avec', this.email, 'role:', this.role);
  }

}
