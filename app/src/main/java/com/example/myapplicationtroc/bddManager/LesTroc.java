package com.example.myapplicationtroc.bddManager;

public class LesTroc {

    private int id_troc;
    private String titre;
    private String descriptif;
    private String etat;
    private String image;

    public LesTroc( int id_troc , String titre , String descriptif , String image, String etat ) {
        this.id_troc = id_troc;
        this.titre = titre;
        this.descriptif = descriptif;
        this.image = image;
        this.etat = etat;

    }

    public int getId_troc() {
        return id_troc;
    }

    public void setId_troc( int id_troc ) {
        this.id_troc = id_troc;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre( String titre ) {
        this.titre = titre;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif( String descriptif ) {
        this.descriptif = descriptif;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat( String etat ) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }
}