package com.ml.gs_relation.service;

import com.ml.gs_relation.Dto.CategorieDto.CategorieDto;
import com.ml.gs_relation.entite.CategorieProduit;
import com.ml.gs_relation.repository.CategorieRepository;
import com.ml.gs_relation.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorieService {
    private final CategorieRepository categorieRepository;
    private final ProduitRepository produitRepository;


    public List<CategorieProduit> ListerCategorie() {
        return categorieRepository.findAll();
    }

    public CategorieProduit AjouterCategorie(CategorieDto categorieDto) {
        // Validation des données
        if (categorieDto.getNom() == null || categorieDto.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la catégorie ne peut pas être vide.");
        }

        // Créer une nouvelle instance de CategorieProduit
        CategorieProduit c1 = new CategorieProduit();
        BeanUtils.copyProperties(categorieDto, c1);
        return categorieRepository.save(c1); // Cela devrait également sauvegarder les produits associés
    }

    public CategorieProduit ModifierCategorie(Long id, CategorieDto categorieDto) {

        if (categorieDto.getNom() == null || categorieDto.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la catégorie ne peut pas être vide.");
        }


        CategorieProduit categorieExistante = categorieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Catégorie non trouvée"));


        BeanUtils.copyProperties(categorieDto, categorieExistante, "id"); // Ignorer l'ID pour ne pas le modifier

        // Sauvegarder la catégorie mise à jour
        return categorieRepository.save(categorieExistante);
    }

    public CategorieProduit SupprimerCategorie(Long id) {
        CategorieProduit c1 = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie non trouvé"));
        if (c1 == null) {
            return null;
        }
        categorieRepository.delete(c1);
        return c1;
    }

    public List<CategorieProduit> categorieLiister() {
        // Récupérer l'utilisateur à partir de l'ID
        return categorieRepository.findAll();
    }


}
