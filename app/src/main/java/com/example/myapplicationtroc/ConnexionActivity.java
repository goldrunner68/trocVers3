package com.example.myapplicationtroc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationtroc.article.TrocDisplayActivity;
import com.example.myapplicationtroc.bddManager.UsersManager;


public class ConnexionActivity extends AppCompatActivity {


    private Button idButtonConnexion;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        this.idButtonConnexion = (Button) this.findViewById(R.id.idButtonValidationConnexion);
        final EditText ed1 = (EditText) findViewById(R.id.idEmail);
        final EditText ed2 = (EditText) findViewById(R.id.idPassword);
        // gestionnaire de la table
        UsersManager usersManager = new UsersManager(this);

        usersManager.open();// ouverture de la table en lecture/écriture
        // j ecoute mes Buttons car il attend un evenement
        idButtonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {// depart de page,  vers l autre page
                if (ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {

                    startActivity(new Intent(ConnexionActivity.this , TrocDisplayActivity.class));
                }
            }
        });

    }

}
