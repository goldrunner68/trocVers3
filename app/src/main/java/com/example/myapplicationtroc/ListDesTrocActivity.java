package com.example.myapplicationtroc;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.Map;

public class ListDesTrocActivity extends AppCompatActivity {
    private TextView categorieView;
    private DatabaseManager databaseManager;
    private ImageView imageView3;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_des_troc);
        categorieView = (TextView) findViewById( R.id.ScoreView );
        imageView3 = (ImageView) findViewById((R.id.imageView3)) ;
        Drawable drawable  = getResources().getDrawable(R.drawable.troclogo);

        databaseManager = new DatabaseManager( this );
       databaseManager.insertCategorie( "Alexandre", "Lorem Ipsum est tout simplement un texte factice " );
       databaseManager.insertCategorie( "Christelle","Lorem Ipsum est tout simplement un texte factice" );
       databaseManager.insertCategorie( "Dominique", "Lorem Ipsum est tout simplement un texte factice " );
        databaseManager.insertCategorie( "Aurélie", "Lorem Ipsum est tout simplement un texte factice " );
        databaseManager.insertCategorie( "Guillaume", "Lorem Ipsum est tout simplement un texte factice " );

        List<CategorieData> categories = databaseManager.readTop10();
        for ( CategorieData categorie : categories ) {

            imageView3.setImageDrawable(drawable);
        categorieView.append("*"+categorie.toString() + "\n\n" );
        }

        databaseManager.close();
    }
}