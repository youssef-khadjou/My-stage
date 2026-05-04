# My'Stage - Plateforme de Gestion de Stages

## Description du projet

My'Stage est une plateforme web de gestion des stages académiques. Elle permet de digitaliser l'ensemble du processus de stage, depuis la proposition des sujets par les entreprises jusqu'à la soutenance finale.

### Acteurs concernés

| Role | Routes | Fonctionnalites principales |
|------|--------|----------------------------|
| Etudiant | /etudiant | Consulter les offres, postuler, confirmer son stage, gerer ses disponibilites |
| Entreprise | /entreprise | Proposer des sujets, suivre les candidatures, gerer les comptes rendus de visite |
| Enseignant | /enseignant | Suivre les etudiants, gerer le calendrier des soutenances |
| Directeur | /directeur | Valider les sujets, publier les offres, suivre les stages |
| Secretariat | /secretariat | Gerer le calendrier, les salles et consulter les stages |

---

## Technologies utilisees

### Backend

| Technologie | Version | Utilisation |
|-------------|---------|-------------|
| Spring Boot | 3.1.5 | API REST |
| Spring Data JPA | - | Acces a la base de donnees |
| Spring Security | - | Authentification (BCrypt) |
| MySQL Connector | 8.0.33 | Pilote MySQL/MariaDB |
| Lombok | 1.18.30 | Reduction du code boilerplate |
| JJWT | 0.11.5 | Generation de tokens JWT |

### Frontend

| Technologie | Version | Utilisation |
|-------------|---------|-------------|
| Angular | 21.2.5 | Framework frontend |
| TypeScript | 5.9.2 | Langage |
| RxJS | 7.8.0 | Programmation reactive |
| Express | 5.1.0 | Serveur SSR |

### Base de donnees

| Technologie | Version |
|-------------|---------|
| MariaDB / MySQL | 10.10+ / 8.0+ |

---

## Installation et demarrage

### Prerequis

- Java 17 ou superieur
- Node.js 20+ et npm
- MariaDB ou MySQL (version 8.0+)
- Angular CLI (optionnel, peut etre utilise via npx)

### 1. Cloner le depot

```bash
  git clone https://github.com/youssef-khadjou/My-stage.git
  cd My-stage
```

### 2. Configurer la base de donnees

#### Étape 1 — Lancer MariaDB/MySQL et se connecter
```bash
mysql -u root -p
```

#### Étape 2 — La base de données
#### Création de la base de données
```sql
CREATE DATABASE my_stage_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE my_stage_db;
```

#### Connexion à la base de données
Accès via terminal
```bash
  cd "C:\Program Files\MariaDB 10.10\bin"
  .\mysql.exe -u root -p
```

#### Commandes utiles
```sql
-- Afficher toutes les bases de données
SHOW DATABASES;

-- Utiliser la base du projet
USE my_stage_db;

-- Afficher toutes les tables
SHOW TABLES;

-- Voir la structure d'une table
DESCRIBE utilisateur;

--Voir le contenu d'une table
SELECT * FROM utilisateur;

-- Quitter
EXIT;
```
    
#### Étape 3 — Configurer l'accès dans `backend/src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/my_stage_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=TON_MOT_DE_PASSE
```

#### Étape 4 — Lancer le backend
Le backend va automatiquement créer toutes les tables grâce à `spring.jpa.hibernate.ddl-auto=update`.
**Il n'y a pas de script SQL à exécuter manuellement.**

Depuis IntelliJ :
- Ouvrir `backend/src/main/java/com/monstage/MonStageApplication.java`
- Cliquer sur le bouton vert Run

Depuis le terminal (dans `backend/`) :

```bash
  ./mvnw spring-boot:run   # Linux/Mac
  mvnw spring-boot:run     # Windows
```

Le backend est accessible sur `http://localhost:8080`

#### Étape 5 — Lancer le frontend (Angular)

Dans un nouveau terminal, aller dans `frontend/` :

```bash
  cd frontend
  npm install
  ng serve
  # ou npx ng serve
```

Le frontend est accessible sur `http://localhost:4200`

#### Étape 6 — Tester l'application

1. Creer un compte via `http://localhost:4200/register`
2. Se connecter via `http://localhost:4200/login/{role}` (ex: `/login/etudiant`)
3. Explorer les fonctionnalites selon le role

---

## Structure du projet

```
My-stage/
├── backend/                      # Spring Boot
│   ├── src/main/java/com/monstage/
│   │   ├── configuration/       # Configuration CORS
│   │   ├── controleur/          # API REST (Auth, Etudiant, Enseignant, Entreprise, Stage, Soutenance...)
│   │   ├── dto/                 # Objets de transfert de donnees
│   │   ├── modele/              # Entités JPA (Utilisateur, Stage, Enseignant, Etudiant...)
│   │   ├── repository/          # Interfaces d'acces BDD
│   │   └── service/             # Logique metier (AuthService)
│   └── src/main/resources/
│       └── application.properties # Configuration BDD et serveur
├── frontend/                    # Angular
│   ├── src/app/
│   │   ├── auth/                # Login et Register
│   │   ├── etudiant/            # Dashboard etudiant
│   │   ├── entreprise/          # Dashboard entreprise
│   │   ├── enseignant/          # Dashboard enseignant
│   │   ├── directeur/           # Dashboard directeur
│   │   ├── secretariat/         # Dashboard secretariat
│   │   ├── guards/              # Auth guard (protection des routes)
│   │   ├── services/            # StorageService
│   │   ├── home/                # Page d'accueil avec choix du role
│   │   └── shared/              # Composants partages
│   └── public/                  # Images et ressources statiques
└── docker/                      # Configuration Docker (optionnel)
```

---

## API Principales

Toutes les API sont prefixees par `/api` et accessibles sur `localhost:8080`.

### Authentification

| Methode | Endpoint | Description |
|---------|----------|-------------|
| POST | /api/auth/register | Creer un compte (email, mot de passe, role) |
| POST | /api/auth/login | Se connecter (renvoie les infos utilisateur) |
| POST | /api/auth/logout | Se deconnecter |

### Gestion des stages et sujets

| Methode | Endpoint | Description |
|---------|----------|-------------|
| GET | /api/sujets/valides | Recuperer tous les sujets validés |
| POST | /api/sujets?entrepriseId={id} | Proposer un nouveau sujet (entreprise) |
| PUT | /api/sujets/{id}/valider | Valider un sujet (directeur) |
| PUT | /api/sujets/{id}/refuser | Refuser un sujet (directeur) |

### Gestion des candidatures

| Methode | Endpoint | Description |
|---------|----------|-------------|
| POST | /api/candidatures | Postuler a un stage (etudiant) |
| GET | /api/candidatures/etudiant/{id} | Voir ses candidatures (etudiant) |
| GET | /api/candidatures/stage/{id} | Voir les candidatures d'un stage (entreprise) |

---
