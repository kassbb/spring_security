package com.ml.gs_relation.service;

import com.ml.gs_relation.Dto.ProduitDto.ProduitDto;
import com.ml.gs_relation.entite.*;
import com.ml.gs_relation.repository.CategorieRepository;
import com.ml.gs_relation.repository.ImageRepository;
import com.ml.gs_relation.repository.ProduitRepository;
import com.ml.gs_relation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProduitService {
    private final String uploadDirectory = "src/main/resources/static/images/products";
    private final UserRepository userRepository;
    private final CategorieRepository categorieRepository;
    private final ProduitRepository produitRepository;
    private ImageRepository imageRepository;

    public List<Produit> VoirMesProduits(Long userId) {
        // Récupérer l'utilisateur par ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        // Récupérer et retourner la liste des produits associés à l'utilisateur
        return produitRepository.findByUser(user);
    }

    // Assurez-vous d'avoir ce repository

    public Produit ajouterProduit(ProduitDto produitDto) {
        // Vérifier si le produit existe déjà par son nom
        if (produitRepository.existsByNom(produitDto.getNom())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produit existant");
        }

        // Créer une nouvelle instance de Produit
        Produit produit = new Produit();

        // Copier les propriétés de ProduitDto vers Produit
        BeanUtils.copyProperties(produitDto, produit, "images", "categoriesId", "userId");

        // Gérer la catégorie
        if (produitDto.getCategoriesId() != null) {
            Optional<CategorieProduit> categorieOpt = categorieRepository.findById(produitDto.getCategoriesId());
            if (categorieOpt.isPresent()) {
                produit.setCategorie(categorieOpt.get());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Catégorie non trouvée");
            }
        }

        // Gérer les images
        if (produitDto.getImages() != null) {
            List<String> imagePaths = new ArrayList<>();
            for (MultipartFile image : produitDto.getImages()) {
                try {
                    String imagePath = saveImage(image);
                    imagePaths.add(imagePath);
                } catch (IOException e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'enregistrement de l'image");
                }
            }
            produit.setImagePaths(imagePaths); // Assurez-vous que votre entité Produit a un champ imagePaths
        }

        // Gérer l'utilisateur (si nécessaire)
        if (produitDto.getUserId() != null) {
            Optional<User> userOpt = userRepository.findById(produitDto.getUserId());
            if (userOpt.isPresent()) {
                produit.setUser(userOpt.get());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
            }
        }

        // Sauvegarder le produit dans la base de données
        return produitRepository.save(produit);
    }

    private String saveImage(MultipartFile image) throws IOException {
        // Créer un nom de fichier unique
        String uniqueFileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path path = Paths.get(uploadDirectory, uniqueFileName);
        Files.createDirectories(path.getParent()); // Créer le répertoire si nécessaire
        Files.copy(image.getInputStream(), path); // Sauvegarder l'image
        return uniqueFileName; // Retourner le nom du fichier pour le stocker dans la base de données
    }

    public Produit modifierProduit(Long id, ProduitDto produitDto) {
        Optional<Produit> produitOpt = produitRepository.findById(id);
        if (produitOpt.isPresent()) {
            Produit produit = produitOpt.get();
            BeanUtils.copyProperties(produitDto, produit, "images", "categoriesId", "userId");
            if (produitDto.getCategoriesId() != null) {
                Optional<CategorieProduit> categorieOpt = categorieRepository.findById(produitDto.getCategoriesId());
                if (categorieOpt.isPresent()) {
                    produit.setCategorie(categorieOpt.get());
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie non trouvée");
                }
            }

            if (produitDto.getImages() != null) {
                List<String> imagePaths = new ArrayList<>();
                for (MultipartFile image : produitDto.getImages()) {
                    try {
                        String imagePath = saveImage(image);
                        imagePaths.add(imagePath);
                    } catch (IOException e) {
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de l'enregistrement de l'image");
                    }
                }
                produit.setImagePaths(imagePaths);
            }
            return produitRepository.save(produit);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé");
        }
    }

    public void supprimerProduit(Long id) {
        Optional<Produit> produitOpt = produitRepository.findById(id);
        if (produitOpt.isPresent()) {
            produitRepository.delete(produitOpt.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé");
        }
    }

    public List<Produit> rechercherProduit(String nom,Double prix){
        return produitRepository.findByNomContainingOrPrixGreaterThan(nom,prix);
    }
}





