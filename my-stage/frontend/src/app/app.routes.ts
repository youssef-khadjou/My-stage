import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { Dashboard as EtudiantDashboard } from './etudiant/dashboard/dashboard';
import { Offres } from './etudiant/offres/offres';
import { ConfirmerStage } from './etudiant/confirmer-stage/confirmer-stage';
import { Disponibilites } from './etudiant/disponibilites/disponibilites';
import { Profil as EtudiantProfil } from './etudiant/profil/profil';
import { Dashboard as DirecteurDashboard } from './directeur/dashboard/dashboard';
import { ValidationSujets } from './directeur/validation-sujets/validation-sujets';
import { Publications } from './directeur/publications/publications';
import { Suivis as DirecteurSuivis } from './directeur/suivis/suivis';
import { Soutenances as DirecteurSoutenances } from './directeur/soutenances/soutenances';
import { Profil as DirecteurProfil } from './directeur/profil/profil';
import { Dashboard as EnseignantDashboard } from './enseignant/dashboard/dashboard';
import { Profil as EnseignantProfil } from './enseignant/profil/profil';
import { Calendrier } from './enseignant/calendrier/calendrier';
import { Suivis as EnseignantSuivis } from './enseignant/suivis/suivis';
import { Soutenances as EnseignantSoutenances } from './enseignant/soutenances/soutenances';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login/:role', component: Login },
  { path: 'register', component: Register },
  { path: 'etudiant', component: EtudiantDashboard },
  { path: 'etudiant/profil', component: EtudiantProfil },
  { path: 'etudiant/offres', component: Offres },
  { path: 'etudiant/confirmer', component: ConfirmerStage },
  { path: 'etudiant/disponibilites', component: Disponibilites },
  { path: 'directeur', component: DirecteurDashboard },
  { path: 'directeur/profil', component: DirecteurProfil },
  { path: 'directeur/validation', component: ValidationSujets },
  { path: 'directeur/publications', component: Publications },
  { path: 'directeur/suivis', component: DirecteurSuivis },
  { path: 'directeur/soutenances', component: DirecteurSoutenances },
  { path: 'enseignant', component: EnseignantDashboard },
  { path: 'enseignant/profil', component: EnseignantProfil },
  { path: 'enseignant/calendrier', component: Calendrier },
  { path: 'enseignant/suivis', component: EnseignantSuivis },
  { path: 'enseignant/soutenances', component: EnseignantSoutenances }
];
