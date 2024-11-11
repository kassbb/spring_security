package com.ml.gs_relation.entite;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class ProfileUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Nom;
    private String Prenom;


    @OneToOne(mappedBy = "profileUser")
    private Image image;

    @OneToOne(mappedBy = "profileUser", cascade = CascadeType.ALL)
    private User user;

}
