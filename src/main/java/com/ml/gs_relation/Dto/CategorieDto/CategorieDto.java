package com.ml.gs_relation.Dto.CategorieDto;

import com.ml.gs_relation.entite.Produit;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CategorieDto {
    private String nom;
}
