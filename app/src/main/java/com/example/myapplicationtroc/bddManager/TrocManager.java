package com.example.myapplicationtroc.bddManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TrocManager {

    private static final String TABLE_TROC = "troc";
    public static final String KEY_ID_TROC ="id_troc";
    public static final String KEY_TITRE ="titre";
    public static final String KEY_DESCRIPTIF ="decriptif";
    public static final String KEY_ETAT ="etat";
    public static final String CREATE_TABLE_TROC = "CREATE TABLE "+ TABLE_TROC +
            " (" +
            " "+ KEY_ID_TROC +" INTEGER primary key," +
            " "+ KEY_TITRE +" TEXT" +
            " "+ KEY_DESCRIPTIF +" TEXT" +
            " "+ KEY_ETAT +" TEXT" +
            ");";

    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public TrocManager( Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addTroc( LesTroc lesTroc ) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_TITRE , lesTroc.getTitre());
        values.put(KEY_DESCRIPTIF , lesTroc.getTitre());
        values.put(KEY_ETAT , lesTroc.getTitre());
        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_TROC ,null,values);
    }

    public int modTroc( LesTroc lesTroc ) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_TITRE , lesTroc.getTitre());
        values.put(KEY_DESCRIPTIF , lesTroc.getTitre());
        values.put(KEY_ETAT , lesTroc.getTitre());

        String where = KEY_ID_TROC +" = ?";
        String[] whereArgs = {lesTroc.getId_troc()+""};

        return db.update(TABLE_TROC , values, where, whereArgs);
    }

    public int supTroc( LesTroc lesTroc ) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_TROC +" = ?";
        String[] whereArgs = {lesTroc.getId_troc()+""};

        return db.delete(TABLE_TROC , where, whereArgs);
    }

    public LesTroc getTroc( int id) {
        // Retourne le TROC dont l'id est passé en paramètre

        LesTroc a=new LesTroc(0,"","","");

        Cursor c = db.rawQuery("SELECT * FROM "+ TABLE_TROC +" WHERE "+ KEY_ID_TROC +"="+id, null);
        if (c.moveToFirst()) {
            a.setId_troc(c.getInt(c.getColumnIndex(KEY_ID_TROC)));
            a.setTitre(c.getString(c.getColumnIndex(KEY_TITRE)));
            a.setTitre(c.getString(c.getColumnIndex(KEY_DESCRIPTIF)));
            a.setTitre(c.getString(c.getColumnIndex(KEY_ETAT)));
            c.close();
        }

        return a;
    }

    public Cursor getAnimaux() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+ TABLE_TROC , null);
    }

} // class TrocManager