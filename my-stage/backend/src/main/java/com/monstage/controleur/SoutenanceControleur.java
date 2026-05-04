package com.monstage.controleur;

import com.monstage.modele.CreneauSoutenance;
import com.monstage.repository.CreneauSoutenanceRepository;
import com.monstage.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/soutenances")
@CrossOrigin(origins = "http://localhost:4200")
public class SoutenanceControleur {

    @Autowired
    private CreneauSoutenanceRepository creneauRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping
    public List<CreneauSoutenance> getAllSoutenances() {
        return creneauRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<CreneauSoutenance> creerSoutenance(@RequestBody Map<String, String> body) {
        CreneauSoutenance creneau = new CreneauSoutenance();
        creneau.setEtudiantId(body.get("etudiantId") != null ? Long.parseLong(body.get("etudiantId")) : null);
        creneau.setDateSoutenance(LocalDate.parse(body.get("date")));
        creneau.setHeureDebut(LocalTime.parse(body.get("heure")));
        creneau.setHeureFin(LocalTime.parse(body.get("heure")).plusHours(1));
        creneau.setSalle(body.get("salle"));
        creneau.setDisponible(false);
        return ResponseEntity.ok(creneauRepository.save(creneau));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerSoutenance(@PathVariable Long id) {
        if (creneauRepository.existsById(id)) {
            creneauRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/salle/{salle}")
    public List<Map<String, Object>> getBySalle(@PathVariable String salle) {
        List<CreneauSoutenance> creneaux = creneauRepository.findBySalleOrderByDateSoutenanceAscHeureDebutAsc(salle);
        List<Map<String, Object>> result = new ArrayList<>();

        for (CreneauSoutenance c : creneaux) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", c.getId());
            map.put("dateSoutenance", c.getDateSoutenance());
            map.put("heureDebut", c.getHeureDebut().toString());
            map.put("heureFin", c.getHeureFin().toString());
            map.put("salle", c.getSalle());
            map.put("etudiantId", c.getEtudiantId());

            if (c.getEtudiantId() != null) {
                utilisateurRepository.findById(c.getEtudiantId()).ifPresent(u -> {
                    map.put("nomEtudiant", u.getPrenom() + " " + u.getNom());
                });
            }
            result.add(map);
        }
        return result;
    }

    @GetMapping("/salles")
    public List<String> getSalles() {
        return creneauRepository.findDistinctSalles();
    }
}