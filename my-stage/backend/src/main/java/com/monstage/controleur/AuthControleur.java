package com.monstage.controleur;

import com.monstage.dto.RequeteConnexion;
import com.monstage.dto.RequeteInscription;
import com.monstage.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthControleur {

    private final AuthService authService;

    @PostMapping("/inscription")
    public ResponseEntity<Map<String, Object>> inscrire(@RequestBody RequeteInscription requete) {
        return ResponseEntity.ok(authService.inscrire(requete));
    }

    @PostMapping("/connexion")
    public ResponseEntity<Map<String, Object>> connecter(@RequestBody RequeteConnexion requete) {
        return ResponseEntity.ok(authService.connecter(requete));
    }
}