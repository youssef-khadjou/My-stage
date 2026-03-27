package com.monstage.service;

import com.monstage.modele.Entreprise;
import com.monstage.repository.EntrepriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    public List<Entreprise> toutesLesEntreprises() {
        return entrepriseRepository.findAll();
    }

    public Entreprise trouverParId(Long id) {
        return entrepriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée : " + id));
    }

    public Entreprise sauvegarder(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }
}