import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { Dashboard as EtudiantDashboard } from './etudiant/dashboard/dashboard';
import { Offres } from './etudiant/offres/offres';
import { ConfirmerStage } from './etudiant/confirmer-stage/confirmer-stage';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login/:role', component: Login },
  { path: 'register', component: Register },
  { path: 'etudiant', component: EtudiantDashboard },
  { path: 'etudiant/offres', component: Offres },
  { path: 'etudiant/confirmer', component: ConfirmerStage }
];
