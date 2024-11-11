package com.ml.gs_relation.entite;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "image")

@RequiredArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // Ajout d'une contrainte de non-null si l'URL ne doit jamais être vide
    private String url;
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @OneToOne
    @JoinColumn(name = "profile_user_id")
    private ProfileUser profileUser;
}
