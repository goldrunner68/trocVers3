package com.example.myapplicationtroc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Articles3.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseManager( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "create table Troc_Articles ("
                + "    idArticle integer primary key autoincrement,"
                + "    name text not null,"
                + "    categorie text not null,"
                + "    when_ integer not null"
                + ")";
        db.execSQL( strSql );
        Log.i( "DATABASE", "onCreate invoked" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //String strSql = "alter table Troc_Articles add column ...";
        String strSql = "drop table Troc_Articles";
        db.execSQL( strSql );
        this.onCreate( db );
        Log.i( "DATABASE", "onUpgrade invoked" );
    }

    public void insertCategorie( String name, String categorie ) {
        name = name.replace( "'", "''" );
        categorie = categorie.replace( "'", "''" );
        String strSql = "insert into Troc_Articles (name, categorie, when_) values ('"
                + name + "', '" + categorie + "', " + new Date().getTime() + ")";
        this.getWritableDatabase().execSQL( strSql );
        Log.i( "DATABASE", "insertCategorie demande" );
    }

    public List<CategorieData> readTop10() {
        List<CategorieData> categories = new ArrayList<>();

        Cursor cursor = this.getReadableDatabase().query( "Troc_Articles",
                new String[] { "idArticle", "name", "categorie", "when_" },
                null, null, null, null, "idArticle", "10" );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            CategorieData categorie = new CategorieData( cursor.getInt( 0 ), cursor.getString( 1 ),
                    cursor.getString( 2 ), new Date( cursor.getLong( 3 ) ) );
            categories.add( categorie );
            cursor.moveToNext();
        }
        cursor.close();

        return categories;
    }

}