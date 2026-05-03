package com.monstage.controleur;

import com.monstage.modele.CreneauSoutenance;
import com.monstage.repository.CreneauSoutenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/soutenances")
@CrossOrigin(origins = "http://localhost:4200")
public class SoutenanceControleur {

    @Autowired
    private CreneauSoutenanceRepository creneauRepository;

    @GetMapping
    public List<CreneauSoutenance> getAllSoutenances() {
        return creneauRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<CreneauSoutenance> creerSoutenance(@RequestBody Map<String, String> body) {
        CreneauSoutenance creneau = new CreneauSoutenance();
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
}