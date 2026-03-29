import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { Dashboard as EtudiantDashboard } from './etudiant/dashboard/dashboard';
import { Offres } from './etudiant/offres/offres';
import { ConfirmerStage } from './etudiant/confirmer-stage/confirmer-stage';
import { Disponibilites } from './etudiant/disponibilites/disponibilites';
import { Profil } from './etudiant/profil/profil';
import { Dashboard as DirecteurDashboard } from './directeur/dashboard/dashboard';
import { ValidationSujets } from './directeur/validation-sujets/validation-sujets';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login/:role', component: Login },
  { path: 'register', component: Register },
  { path: 'etudiant', component: EtudiantDashboard },
  { path: 'etudiant/profil', component: Profil },
  { path: 'etudiant/offres', component: Offres },
  { path: 'etudiant/confirmer', component: ConfirmerStage },
  { path: 'etudiant/disponibilites', component: Disponibilites },
  { path: 'directeur', component: DirecteurDashboard },
  { path: 'directeur/validation', component: ValidationSujets }
];
