package com.ml.gs_relation.controller;

import com.ml.gs_relation.Dto.CategorieDto.CategorieDto;
import com.ml.gs_relation.entite.CategorieProduit;
import com.ml.gs_relation.service.CategorieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CategorieService categorieService;
    @GetMapping
    public ResponseEntity<String> SayHello(){
        return  ResponseEntity.ok("hi admin");
    }

    @PostMapping("/categories")
    public ResponseEntity<String> ajouterCategorie(@Valid @RequestBody CategorieDto categorieDto) {
        try {
            CategorieProduit categorieProduit = categorieService.AjouterCategorie(categorieDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Catégorie ajoutée avec succès : " + categorieProduit.getNom());
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de la catégorie");
        }
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<String> modifierCategorie(@PathVariable Long id, @Valid @RequestBody CategorieDto categorieDto) {
        try {
            CategorieProduit categorieProduit = categorieService.ModifierCategorie(id, categorieDto);
            return ResponseEntity.ok("Catégorie modifiée avec succès : " + categorieProduit.getNom());
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la modification de la catégorie");
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> supprimerCategorie(@PathVariable Long id) {
        try {
            categorieService.SupprimerCategorie(id);
            return ResponseEntity.ok("Catégorie supprimée");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de la catégorie");
        }
    }

}
