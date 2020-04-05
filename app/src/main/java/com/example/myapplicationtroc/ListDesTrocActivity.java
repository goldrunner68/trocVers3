package com.example.myapplicationtroc;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class ListDesTrocActivity extends AppCompatActivity {
    private TextView scoresView;
    private DatabaseManager databaseManager;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_des_troc);
        scoresView = (TextView) findViewById( R.id.ScoreView );
        databaseManager = new DatabaseManager( this );

        databaseManager.insertScore( "Alexandre", 800 );
//        databaseManager.insertScore( "Christelle", 530 );
//        databaseManager.insertScore( "Dominique", 50 );
//        databaseManager.insertScore( "Aurélie", 100 );
//        databaseManager.insertScore( "Guillaume", 980 );

        List<ScoreData> scores = databaseManager.readTop10();
        for ( ScoreData score : scores ) {
            scoresView.append( score.toString() + "\n\n" );
        }

        databaseManager.close();
    }
}