package com.monstage.controleur;

import com.monstage.modele.Utilisateur;
import com.monstage.modele.Utilisateur.Role;
import com.monstage.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "*")
public class UtilisateurControleur {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Utilisateur> getUtilisateurByEmail(@PathVariable String email) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        return utilisateur.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public List<Utilisateur> getUtilisateursByRole(@PathVariable String role) {
        return utilisateurRepository.findByRole(Role.valueOf(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurDetails) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(id);
        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            utilisateur.setEmail(utilisateurDetails.getEmail());
            utilisateur.setNom(utilisateurDetails.getNom());
            utilisateur.setPrenom(utilisateurDetails.getPrenom());
            utilisateur.setTelephone(utilisateurDetails.getTelephone());
            utilisateur.setRole(utilisateurDetails.getRole());
            return ResponseEntity.ok(utilisateurRepository.save(utilisateur));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Map<String, String>> updatePassword(@PathVariable Long id, @RequestBody Map<String, String> passwordInfo) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(id);
        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            utilisateur.setMotDePasse(passwordEncoder.encode(passwordInfo.get("motDePasse")));
            utilisateurRepository.save(utilisateur);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Mot de passe mis à jour avec succès");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/actif")
    public ResponseEntity<Utilisateur> toggleActif(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(id);
        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            utilisateur.setActif(body.get("actif"));
            return ResponseEntity.ok(utilisateurRepository.save(utilisateur));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}