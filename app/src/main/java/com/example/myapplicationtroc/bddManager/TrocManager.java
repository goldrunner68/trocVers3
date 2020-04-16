package com.example.myapplicationtroc.bddManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TrocManager {

    private static final String TABLE_TROC = "troc";
    public static final String KEY_ID_TROC = "id_troc";
    public static final String KEY_TITRE = "titre";
    public static final String KEY_DESCRIPTIF = "descriptif";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_ETAT = "etat";

    // attention a la virgule apres l avant dernier "TEXT" sinon genere erreur
    // has no column named image
    public static final String CREATE_TABLE_TROC = "CREATE TABLE " + TABLE_TROC +
            " (" +
            " " + KEY_ID_TROC + " INTEGER primary key," +
            " " + KEY_TITRE + " TEXT," +
            " " + KEY_DESCRIPTIF + " TEXT," +
            " " + KEY_ETAT + " TEXT," +
            " " + KEY_IMAGE + " TEXT" +
            ");";

    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public TrocManager( Context context ) {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open() {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addTroc( TrocTable trocTable ) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_TITRE , trocTable.getTitre());
        values.put(KEY_DESCRIPTIF , trocTable.getDescriptif());
        values.put(KEY_IMAGE , trocTable.getImage());
        values.put(KEY_ETAT , trocTable.getEtat());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_TROC , null , values);
    }

    public int modTroc( TrocTable trocTable ) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_TITRE , trocTable.getTitre());
        values.put(KEY_DESCRIPTIF , trocTable.getDescriptif());
        values.put(KEY_IMAGE , trocTable.getImage());
        values.put(KEY_ETAT , trocTable.getEtat());

        String where = KEY_ID_TROC + " = ?";
        String[] whereArgs = {trocTable.getId_troc() + ""};

        return db.update(TABLE_TROC , values , where , whereArgs);
    }

    public int supTroc( TrocTable trocTable ) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_TROC + " = ?";
        String[] whereArgs = {trocTable.getId_troc() + ""};

        return db.delete(TABLE_TROC , where , whereArgs);
    }

    public TrocTable getTroc( int id ) {
        // Retourne le TROC dont l'id est passé en paramètre

        TrocTable a = new TrocTable(0 , "" , "" , "" , "");

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_TROC + " WHERE " + KEY_ID_TROC + "=" + id , null);
        if (c.moveToFirst()) {
            a.setId_troc(c.getInt(c.getColumnIndex(KEY_ID_TROC)));
            a.setTitre(c.getString(c.getColumnIndex(KEY_TITRE)));
            a.setDescriptif(c.getString(c.getColumnIndex(KEY_DESCRIPTIF)));
            a.setImage(c.getString(c.getColumnIndex(KEY_IMAGE)));
            a.setEtat(c.getString(c.getColumnIndex(KEY_ETAT)));

            c.close();
        }

        return a;
    }

    public Cursor getTroc() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM " + TABLE_TROC , null);
    }

}