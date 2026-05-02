import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-dashboard-entreprise',
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {

  constructor(
    private router: Router,
    private storage: StorageService
  ) {}

  naviguer(chemin: string) {
    this.router.navigate([chemin]);
  }

  deconnecter() {
    this.storage.removeItem('utilisateur');
    this.router.navigate(['/']);
  }

}
