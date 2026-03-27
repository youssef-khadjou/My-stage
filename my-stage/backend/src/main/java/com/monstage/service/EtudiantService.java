package com.monstage.service;

import com.monstage.modele.Etudiant;
import com.monstage.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;

    // Constructeur manuel
    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public List<Etudiant> tousLesEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant trouverParId(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé : " + id));
    }

    public Etudiant trouverParNumero(String numeroEtudiant) {
        return etudiantRepository.findByNumeroEtudiant(numeroEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé : " + numeroEtudiant));
    }

    public Etudiant sauvegarder(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }
}
