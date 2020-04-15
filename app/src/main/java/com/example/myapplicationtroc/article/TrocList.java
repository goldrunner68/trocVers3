package com.example.myapplicationtroc.article;


public class TrocList {
    private String nomDeLarticle;
    private String descriptionArticle;
    private String etatDeLarticle;
    private int imageArticle;

    public TrocList( String nomDeLarticle , int imageArticle, String descriptionArticle , String etatDeLarticle  ) {
        this.nomDeLarticle = nomDeLarticle;
        this.descriptionArticle = descriptionArticle;
        this.etatDeLarticle = etatDeLarticle;
        this.imageArticle = imageArticle;
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

    public int getImageArticle() {
        return imageArticle;
    }
}
