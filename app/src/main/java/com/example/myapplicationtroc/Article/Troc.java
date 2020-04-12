package com.example.myapplicationtroc.Article;

import android.graphics.drawable.Drawable;

public class Troc {
    private String nomDeLarticle;
    private String descriptionArticle;
    private String etatDeLarticle;
    private int imageArticle;

    public Troc( String nomDeLarticle , int imageArticle, String descriptionArticle , String etatDeLarticle  ) {
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
