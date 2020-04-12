package com.example.myapplicationtroc.Article;

public class Troc {
    private String nomDeLarticle;
    private String descriptionArticle;
    private String etatDeLarticle;

    public Troc( String nomDeLarticle , String descriptionArticle , String etatDeLarticle ) {
        this.nomDeLarticle = nomDeLarticle;
        this.descriptionArticle = descriptionArticle;
        this.etatDeLarticle = etatDeLarticle;
    }

    public String getNomDeLarticle() {
        return nomDeLarticle;
    }

    public String getDescriptionArticle() {
        return descriptionArticle;
    }

    public String getEtatDeLarticle() {
        return etatDeLarticle;
    }
}
