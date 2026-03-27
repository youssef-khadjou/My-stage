package com.monstage.controleur;

import com.monstage.modele.Utilisateur;
import com.monstage.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurControleur {

    private final UtilisateurService utilisateurService;

    @GetMapping("/moi")
    public ResponseEntity<Utilisateur> moi(Authentication authentication) {
        Utilisateur utilisateur = utilisateurService.trouverParEmail(authentication.getName());
        return ResponseEntity.ok(utilisateur);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        // suppression admin seulement
        return ResponseEntity.noContent().build();
    }
}