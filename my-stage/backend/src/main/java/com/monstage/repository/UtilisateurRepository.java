package com.monstage.repository;

import com.monstage.modele.Utilisateur;
import com.monstage.modele.Utilisateur.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    
    Optional<Utilisateur> findByEmail(String email);
    
    List<Utilisateur> findByRole(Role role);
    
    boolean existsByEmail(String email);
}