package com.monstage.controleur;

import com.monstage.dto.RequeteConnexion;
import com.monstage.dto.RequeteInscription;
import com.monstage.modele.Utilisateur;
import com.monstage.modele.Etudiant;
import com.monstage.modele.Entreprise;
import com.monstage.modele.Professeur;
import com.monstage.repository.UtilisateurRepository;
import com.monstage.repository.EtudiantRepository;
import com.monstage.repository.EntrepriseRepository;
import com.monstage.repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthControleur {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private ProfesseurRepository professeurRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody RequeteConnexion requete) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmail(requete.getEmail());
        
        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            if (passwordEncoder.matches(requete.getMotDePasse(), utilisateur.getMotDePasse())) {
                Map<String, Object> response = new HashMap<>();
                response.put("id", utilisateur.getId());
                response.put("email", utilisateur.getEmail());
                response.put("nom", utilisateur.getNom());
                response.put("prenom", utilisateur.getPrenom());
                response.put("telephone", utilisateur.getTelephone());
                response.put("role", utilisateur.getRole());
                response.put("actif", utilisateur.getActif());
                response.put("message", "Connexion réussie");
                return ResponseEntity.ok(response);
            }
        }
        
        Map<String, Object> error = new HashMap<>();
        error.put("message", "Email ou mot de passe incorrect");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RequeteInscription requete) {
        if (utilisateurRepository.existsByEmail(requete.getEmail())) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Email déjà existant");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        // Création de l'utilisateur de base
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(requete.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(requete.getMotDePasse()));
        utilisateur.setNom(requete.getNom());
        utilisateur.setPrenom(requete.getPrenom());
        utilisateur.setTelephone(requete.getTelephone());
        utilisateur.setRole(requete.getRole());
        utilisateur.setActif(true);
        utilisateur.setDateCreation(LocalDateTime.now());

        Utilisateur savedUser = utilisateurRepository.save(utilisateur);

        // Création du profil spécifique selon le rôle
        switch (requete.getRole()) {
            case ETUDIANT:
                Etudiant etudiant = new Etudiant();
                etudiant.setId(savedUser.getId());
                etudiant.setNumeroEtudiant(requete.getNumeroEtudiant());
                etudiant.setFiliere(requete.getFiliere());
                etudiant.setNiveau(requete.getNiveau());
                etudiant.setAnneeScolaire(requete.getAnneeScolaire());
                etudiant.setUtilisateur(savedUser);
                etudiantRepository.save(etudiant);
                break;
                
            case ENTREPRISE:
                Entreprise entreprise = new Entreprise();
                entreprise.setId(savedUser.getId());
                entreprise.setSiret(requete.getSiret());
                entreprise.setNomEntreprise(requete.getNomEntreprise());
                entreprise.setAdresse(requete.getAdresse());
                entreprise.setVille(requete.getVille());
                entreprise.setCodePostal(requete.getCodePostal());
                entreprise.setSecteurActivite(requete.getSecteurActivite());
                entreprise.setUtilisateur(savedUser);
                entrepriseRepository.save(entreprise);
                break;
                
            case PROFESSEUR:
                Professeur professeur = new Professeur();
                professeur.setNom(requete.getNom());
                professeur.setPrenom(requete.getPrenom());
                professeur.setSpecialite(requete.getSpecialite());
                professeur.setTelephone(requete.getTelephone());
                professeur.setUtilisateur(savedUser);
                professeurRepository.save(professeur);
                break;
                
            default:
                break;
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Inscription réussie");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Déconnexion réussie");
        return ResponseEntity.ok(response);
    }
}