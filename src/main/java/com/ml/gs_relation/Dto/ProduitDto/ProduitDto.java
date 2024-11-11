package com.ml.gs_relation.Dto.ProduitDto;

import com.ml.gs_relation.entite.CategorieProduit;
import com.ml.gs_relation.entite.Etat;
import com.ml.gs_relation.entite.Image;
import com.ml.gs_relation.entite.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class ProduitDto {
    private Long id;
    private String nom;
    private String description;
    private Date dateAjout;
    private Double prix;
    private Double quantite;
    private Date dateExpiration;
    private Etat etat;
    private List<MultipartFile> images;
    private Long categoriesId;
    private Long userId;

}
