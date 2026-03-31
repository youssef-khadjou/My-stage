import { Routes } from '@angular/router';
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
  { path: 'etudiant', component: EtudiantDashboard },
  { path: 'etudiant/profil', component: EtudiantProfil },
  { path: 'etudiant/offres', component: Offres },
  { path: 'etudiant/confirmer', component: ConfirmerStage },
  { path: 'etudiant/disponibilites', component: EtudiantDisponibilites },
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
  { path: 'enseignant/soutenances', component: EnseignantSoutenances },

  { path: 'secretariat', component: SecretariatDashboard },

  { path: 'secretariat/profil', component: SecretariatProfil },

  { path: 'secretariat/calendrier', component: SecretariatCalendrier },

  { path: 'secretariat/salles', component: Salles },
  { path: 'secretariat/salles-planning', component: SallesPlanning },

  { path: 'secretariat/stages', component: SecretariatStages },

  { path: 'entreprise', component: EntrepriseDashboard },

  { path: 'entreprise/profil', component: EntrepriseProfil },

  { path: 'entreprise/proposer-sujet', component: ProposerSujet },

  { path: 'entreprise/propositions', component: Propositions },

  { path: 'entreprise/compte-rendu-visite', component: CompteRenduVisite },

  { path: 'entreprise/disponibilites', component: EntrepriseDisponibilites },
];
