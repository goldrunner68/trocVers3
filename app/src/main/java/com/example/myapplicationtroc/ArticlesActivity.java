package com.example.myapplicationtroc;

import android.app.ListActivity;
import android.os.Bundle;

public class ArticlesActivity extends ListActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] values = new String[] { "Device", "Géo localisation" };

        MonAdaptateurDeListe adaptateur = new MonAdaptateurDeListe(this, values);
        setListAdapter(adaptateur);
    }
}