package com.monstage.service;

import com.monstage.modele.Utilisateur;
import com.monstage.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur inscrire(Utilisateur utilisateur) {
        utilisateur.setActif(true);
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> connecter(String email, String motDePasse) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur.isPresent() && utilisateur.get().getMotDePasse().equals(motDePasse)) {
            return utilisateur;
        }
        return Optional.empty();
    }
}