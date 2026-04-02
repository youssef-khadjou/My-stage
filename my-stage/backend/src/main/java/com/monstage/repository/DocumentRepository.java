package com.monstage.repository;

import com.monstage.modele.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository pour les opérations CRUD sur Document.
 * Hérite de JpaRepository qui fournit save(), findById(), findAll(), delete()...
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    /** Récupère tous les documents déposés par un utilisateur donné */
    List<Document> findByUploadParId(Long utilisateurId);
}