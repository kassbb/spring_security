package com.ml.gs_relation.repository;

import com.ml.gs_relation.entite.CategorieProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<CategorieProduit, Long> {
   public CategorieProduit findByNom(String nom);
}
