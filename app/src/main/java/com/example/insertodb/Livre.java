package com.example.insertodb;

public class Livre {
    private String titre,description;
    private String prix;


    public Livre(String titre, String description, String prix) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
