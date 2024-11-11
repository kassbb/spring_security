package com.ml.gs_relation.repository;

import com.ml.gs_relation.entite.CategorieProduit;
import com.ml.gs_relation.entite.Etat;
import com.ml.gs_relation.entite.Produit;
import com.ml.gs_relation.entite.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    public Produit findByNom(String nom);
    public List<Produit> findByNomContaining(String nom);
    public List<Produit> findByOrderByDateAjout();
    public List<Produit> findByOrderByPrix();
    public List<Produit> findByOrderByQuantite();

    public List<Produit> findByEtat(Etat etat);

    public List<Produit> findByCategorie(CategorieProduit categorie);

    public List<Produit> findByUser(User user);
    public boolean existsByNom(String nom);

    public List<Produit>findByNomContainingOrPrixGreaterThan(String nom,Double prix);

}
