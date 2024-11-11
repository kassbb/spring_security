package com.ml.gs_relation.controller;

import com.ml.gs_relation.Dto.CategorieDto.CategorieDto;
import com.ml.gs_relation.Dto.ProduitDto.ProduitDto;
import com.ml.gs_relation.entite.CategorieProduit;
import com.ml.gs_relation.entite.Produit;
import com.ml.gs_relation.service.CategorieService;
import com.ml.gs_relation.service.ProduitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/agriculteur")
@RequiredArgsConstructor
public class AgriculteurController {
    private final ProduitService produitService;
    private final CategorieService categorieService;

    @GetMapping
    public ResponseEntity<String> SayHello() {
        return ResponseEntity.ok("hi agriculteur");
    }

    @PostMapping("/produit")
    public ResponseEntity<Produit> ajouterProduit(@RequestBody ProduitDto produitDto) {
        try {
            produitService.ajouterProduit(produitDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/produits/{userId}")
    public ResponseEntity<List<Produit>> voirMesProduits(@PathVariable Long userId) {
        List<Produit> produits = produitService.VoirMesProduits(userId);
        return ResponseEntity.ok(produits);
    }

    @PutMapping("/produit/{id}")
    public ResponseEntity<Produit> modifierProduit(@PathVariable Long id, @RequestBody ProduitDto produitDto) {
        try {
            Produit produit = produitService.modifierProduit(id, produitDto);
            return ResponseEntity.ok(produit);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/produit/id")
    public ResponseEntity<String> supprimerProduit(@PathVariable Long id) {
        try {
            produitService.supprimerProduit(id);
            return ResponseEntity.ok("Produit supprimé");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}