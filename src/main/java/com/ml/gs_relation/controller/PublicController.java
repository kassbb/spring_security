package com.ml.gs_relation.controller;

import com.ml.gs_relation.entite.CategorieProduit;
import com.ml.gs_relation.entite.Produit;
import com.ml.gs_relation.service.CategorieService;
import com.ml.gs_relation.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/public")
public class PublicController {
    private final CategorieService cate;
    private final ProduitService produitService;

    @GetMapping("categories")
    public ResponseEntity<List<CategorieProduit>> listCategories() {
        List<CategorieProduit> categories = cate.categorieLiister();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/produits/{nom}/{prix}")

    public ResponseEntity<List<Produit>> rechercherProduit(@PathVariable String nom, @PathVariable Double prix) {
        List<Produit> produits = produitService.rechercherProduit(nom, prix);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }
}
