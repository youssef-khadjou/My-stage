package com.monstage.controleur;

import com.monstage.dto.RequeteEvaluation;
import com.monstage.modele.Evaluation;
import com.monstage.modele.Stage;
import com.monstage.modele.Etudiant;
import com.monstage.repository.EvaluationRepository;
import com.monstage.repository.StageRepository;
import com.monstage.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluations")
@CrossOrigin(origins = "*")
public class EvaluationControleur {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Long id) {
        Optional<Evaluation> evaluation = evaluationRepository.findById(id);
        return evaluation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stage/{stageId}")
    public List<Evaluation> getEvaluationsByStage(@PathVariable Long stageId) {
        return evaluationRepository.findByStageId(stageId);
    }

    @GetMapping("/etudiant/{etudiantId}")
    public List<Evaluation> getEvaluationsByEtudiant(@PathVariable Long etudiantId) {
        return evaluationRepository.findByEtudiantId(etudiantId);
    }

    @GetMapping("/type/{typeEvaluation}")
    public List<Evaluation> getEvaluationsByType(@PathVariable String typeEvaluation) {
        return evaluationRepository.findByTypeEvaluation(typeEvaluation);
    }

    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody RequeteEvaluation requete) {
        Optional<Stage> stageOpt = stageRepository.findById(requete.getIdStage());
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(requete.getIdEtudiant());

        if (stageOpt.isEmpty() || etudiantOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Evaluation evaluation = new Evaluation();
        evaluation.setCommentaire(requete.getCommentaire());
        evaluation.setDateEvaluation(requete.getDateEvaluation());
        evaluation.setNote(requete.getNote());
        evaluation.setNoteSoutenance(requete.getNoteSoutenance());
        evaluation.setTexteStage(requete.getTexteStage());
        evaluation.setTypeEvaluation(requete.getTypeEvaluation());
        evaluation.setStage(stageOpt.get());
        evaluation.setEtudiant(etudiantOpt.get());

        return ResponseEntity.ok(evaluationRepository.save(evaluation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable Long id, @RequestBody RequeteEvaluation requete) {
        Optional<Evaluation> evaluationOpt = evaluationRepository.findById(id);
        if (evaluationOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Evaluation evaluation = evaluationOpt.get();
        evaluation.setCommentaire(requete.getCommentaire());
        evaluation.setDateEvaluation(requete.getDateEvaluation());
        evaluation.setNote(requete.getNote());
        evaluation.setNoteSoutenance(requete.getNoteSoutenance());
        evaluation.setTexteStage(requete.getTexteStage());
        evaluation.setTypeEvaluation(requete.getTypeEvaluation());

        return ResponseEntity.ok(evaluationRepository.save(evaluation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        if (evaluationRepository.existsById(id)) {
            evaluationRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}