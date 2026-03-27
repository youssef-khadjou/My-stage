package com.monstage.dto;

import com.monstage.modele.Utilisateur;
import lombok.Data;

@Data
public class RequeteInscription {
    // Commun
    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String telephone;
    private Utilisateur.Role role;

    // Etudiant
    private String numeroEtudiant;
    private String filiere;
    private String niveau;
    private String anneeScolaire;

    // Entreprise
    private String siret;
    private String nomEntreprise;
    private String adresse;
    private String ville;
    private String codePostal;
    private String secteurActivite;

    // Professeur
    private String specialite;
}