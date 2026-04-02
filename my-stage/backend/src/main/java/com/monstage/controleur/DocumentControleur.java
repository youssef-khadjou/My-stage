package com.monstage.controleur;

import com.monstage.modele.Document;
import com.monstage.modele.Utilisateur;
import com.monstage.repository.DocumentRepository;
import com.monstage.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class DocumentControleur {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    private final String uploadDir = "uploads/documents/";

    @GetMapping
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        Optional<Document> document = documentRepository.findById(id);
        return document.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Document> getDocumentsByUtilisateur(@PathVariable Long utilisateurId) {
        return documentRepository.findByUploadParId(utilisateurId);
    }

    @PostMapping("/upload")
    public ResponseEntity<Document> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("utilisateurId") Long utilisateurId) {
        
        try {
            Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(utilisateurId);
            if (utilisateurOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Créer le répertoire s'il n'existe pas
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Générer un nom de fichier unique
            String originalFileName = file.getOriginalFilename();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString() + extension;
            
            // Sauvegarder le fichier
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath);

            // Créer l'entité Document
            Document document = new Document();
            document.setNomFichier(originalFileName);
            document.setUrlFichier(uploadDir + uniqueFileName);
            document.setTailleOctets(file.getSize());
            document.setDateUpload(LocalDateTime.now());
            document.setUploadPar(utilisateurOpt.get());

            Document savedDocument = documentRepository.save(document);
            return ResponseEntity.ok(savedDocument);
            
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        Optional<Document> documentOpt = documentRepository.findById(id);
        if (documentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        try {
            // Supprimer le fichier physique
            Path filePath = Paths.get(documentOpt.get().getUrlFichier());
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        documentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}