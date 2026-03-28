import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { Dashboard as EtudiantDashboard } from './etudiant/dashboard/dashboard';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login/:role', component: Login },
  { path: 'register', component: Register },
  { path: 'etudiant', component: EtudiantDashboard }
];
