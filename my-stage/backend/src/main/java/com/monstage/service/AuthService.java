package com.monstage.service;

import com.monstage.dto.RequeteConnexion;
import com.monstage.dto.RequeteInscription;
import com.monstage.modele.*;
import com.monstage.repository.*;
import com.monstage.securite.JwtUtilisateur;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final EtudiantRepository etudiantRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final ProfesseurRepository professeurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilisateur jwtUtilisateur;
    private final AuthenticationManager authenticationManager;

    // Constructeur manuel
    public AuthService(UtilisateurRepository utilisateurRepository, 
                       EtudiantRepository etudiantRepository,
                       EntrepriseRepository entrepriseRepository,
                       ProfesseurRepository professeurRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtilisateur jwtUtilisateur,
                       AuthenticationManager authenticationManager) {
        this.utilisateurRepository = utilisateurRepository;
        this.etudiantRepository = etudiantRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.professeurRepository = professeurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtilisateur = jwtUtilisateur;
        this.authenticationManager = authenticationManager;
    }

    public Map<String, Object> inscrire(RequeteInscription requete) {
        if (utilisateurRepository.existsByEmail(requete.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(requete.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(requete.getMotDePasse()));
        utilisateur.setNom(requete.getNom());
        utilisateur.setPrenom(requete.getPrenom());
        utilisateur.setTelephone(requete.getTelephone());
        utilisateur.setRole(requete.getRole());
        utilisateur.setActif(true);
        utilisateurRepository.save(utilisateur);

        switch (requete.getRole()) {
            case ETUDIANT -> {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(utilisateur.getId());
                etudiant.setNumeroEtudiant(requete.getNumeroEtudiant());
                etudiant.setFiliere(requete.getFiliere());
                etudiant.setNiveau(requete.getNiveau());
                etudiant.setAnneeScolaire(requete.getAnneeScolaire());
                etudiant.setUtilisateur(utilisateur);
                etudiantRepository.save(etudiant);
            }
            case ENTREPRISE -> {
                Entreprise entreprise = new Entreprise();
                entreprise.setId(utilisateur.getId());
                entreprise.setSiret(requete.getSiret());
                entreprise.setNomEntreprise(requete.getNomEntreprise());
                entreprise.setAdresse(requete.getAdresse());
                entreprise.setVille(requete.getVille());
                entreprise.setCodePostal(requete.getCodePostal());
                entreprise.setSecteurActivite(requete.getSecteurActivite());
                entreprise.setTelephoneContact(requete.getTelephone());
                entreprise.setNomContact(requete.getNom() + " " + requete.getPrenom());
                entreprise.setUtilisateur(utilisateur);
                entrepriseRepository.save(entreprise);
            }
            case PROFESSEUR -> {
                Professeur professeur = new Professeur();
                professeur.setNom(requete.getNom());
                professeur.setPrenom(requete.getPrenom());
                professeur.setSpecialite(requete.getSpecialite());
                professeur.setTelephone(requete.getTelephone());
                professeur.setUtilisateur(utilisateur);
                professeurRepository.save(professeur);
            }
            default -> {}
        }

        String token = jwtUtilisateur.genererToken(utilisateur.getEmail());
        Map<String, Object> reponse = new HashMap<>();
        reponse.put("token", token);
        reponse.put("role", utilisateur.getRole());
        reponse.put("email", utilisateur.getEmail());
        reponse.put("id", utilisateur.getId());
        return reponse;
    }

    public Map<String, Object> connecter(RequeteConnexion requete) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requete.getEmail(), requete.getMotDePasse())
        );

        Utilisateur utilisateur = utilisateurRepository.findByEmail(requete.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        String token = jwtUtilisateur.genererToken(utilisateur.getEmail());
        Map<String, Object> reponse = new HashMap<>();
        reponse.put("token", token);
        reponse.put("role", utilisateur.getRole());
        reponse.put("email", utilisateur.getEmail());
        reponse.put("id", utilisateur.getId());
        reponse.put("nom", utilisateur.getNom());
        reponse.put("prenom", utilisateur.getPrenom());
        return reponse;
    }
}
