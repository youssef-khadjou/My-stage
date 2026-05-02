import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';
import { Home } from './home/home';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { Dashboard as EtudiantDashboard } from './etudiant/dashboard/dashboard';
import { Offres } from './etudiant/offres/offres';
import { ConfirmerStage } from './etudiant/confirmer-stage/confirmer-stage';
import { Disponibilites as EtudiantDisponibilites } from './etudiant/disponibilites/disponibilites';
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
import { Dashboard as SecretariatDashboard } from './secretariat/dashboard/dashboard';
import { Profil as SecretariatProfil } from './secretariat/profil/profil';
import { Calendrier as SecretariatCalendrier } from './secretariat/calendrier/calendrier';
import { Salles } from './secretariat/salles/salles';
import { SallesPlanning } from './secretariat/salles-planning/salles-planning';
import { Stages as SecretariatStages } from './secretariat/stages/stages';
import { Dashboard as EntrepriseDashboard } from './entreprise/dashboard/dashboard';
import { Profil as EntrepriseProfil } from './entreprise/profil/profil';
import { ProposerSujet } from './entreprise/proposer-sujet/proposer-sujet';
import { Propositions } from './entreprise/propositions/propositions';
import { CompteRenduVisite } from './entreprise/compte-rendu-visite/compte-rendu-visite';
import { Disponibilites as EntrepriseDisponibilites } from './entreprise/disponibilites/disponibilites';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login/:role', component: Login },
  { path: 'register', component: Register },

  // Étudiant
  { path: 'etudiant', component: EtudiantDashboard, canActivate: [authGuard] },
  { path: 'etudiant/profil', component: EtudiantProfil, canActivate: [authGuard] },
  { path: 'etudiant/offres', component: Offres, canActivate: [authGuard] },
  { path: 'etudiant/confirmer', component: ConfirmerStage, canActivate: [authGuard] },
  { path: 'etudiant/disponibilites', component: EtudiantDisponibilites, canActivate: [authGuard] },

  // Directeur
  { path: 'directeur', component: DirecteurDashboard, canActivate: [authGuard] },
  { path: 'directeur/profil', component: DirecteurProfil, canActivate: [authGuard] },
  { path: 'directeur/validation', component: ValidationSujets, canActivate: [authGuard] },
  { path: 'directeur/publications', component: Publications, canActivate: [authGuard] },
  { path: 'directeur/suivis', component: DirecteurSuivis, canActivate: [authGuard] },
  { path: 'directeur/soutenances', component: DirecteurSoutenances, canActivate: [authGuard] },

  // Enseignant
  { path: 'enseignant', component: EnseignantDashboard, canActivate: [authGuard] },
  { path: 'enseignant/profil', component: EnseignantProfil, canActivate: [authGuard] },
  { path: 'enseignant/calendrier', component: Calendrier, canActivate: [authGuard] },
  { path: 'enseignant/suivis', component: EnseignantSuivis, canActivate: [authGuard] },
  { path: 'enseignant/soutenances', component: EnseignantSoutenances, canActivate: [authGuard] },

  // Secrétariat
  { path: 'secretariat', component: SecretariatDashboard, canActivate: [authGuard] },
  { path: 'secretariat/profil', component: SecretariatProfil, canActivate: [authGuard] },
  { path: 'secretariat/calendrier', component: SecretariatCalendrier, canActivate: [authGuard] },
  { path: 'secretariat/salles', component: Salles, canActivate: [authGuard] },
  { path: 'secretariat/salles-planning', component: SallesPlanning, canActivate: [authGuard] },
  { path: 'secretariat/stages', component: SecretariatStages, canActivate: [authGuard] },

  // Entreprise
  { path: 'entreprise', component: EntrepriseDashboard, canActivate: [authGuard] },
  { path: 'entreprise/profil', component: EntrepriseProfil, canActivate: [authGuard] },
  { path: 'entreprise/proposer-sujet', component: ProposerSujet, canActivate: [authGuard] },
  { path: 'entreprise/propositions', component: Propositions, canActivate: [authGuard] },
  { path: 'entreprise/compte-rendu-visite', component: CompteRenduVisite, canActivate: [authGuard] },
  { path: 'entreprise/disponibilites', component: EntrepriseDisponibilites, canActivate: [authGuard] },
];
