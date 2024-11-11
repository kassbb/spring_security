package com.ml.gs_relation.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;



    private Date dateAjout;


    private Double prix;
    private Double quantite;

    private Date dateExpiration;
    @Enumerated(EnumType.STRING)
    private Etat etat;


    @ElementCollection
    private List<String> imagePaths;



    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private CategorieProduit categorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}
