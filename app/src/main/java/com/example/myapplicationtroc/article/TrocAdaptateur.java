package com.example.myapplicationtroc.article;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapplicationtroc.ConnexionActivity;
import com.example.myapplicationtroc.MainActivity;
import com.example.myapplicationtroc.R;

public class TrocAdaptateur extends ArrayAdapter<TrocList> {
    public TrocAdaptateur( @NonNull Context context , int resource ) {
        super(context , resource);
    }

    @NonNull
    @Override
    public View getView( int position , @Nullable View convertView , @NonNull ViewGroup parent ) {
        View view;
        // pour chercher la cellule(troc_cell)
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    view = layoutInflater.inflate(R.layout.troc_cell, null);
    TrocList currentTrocList = getItem(position);

        TextView nomDuTroc = (TextView)view.findViewById(R.id.typeTroc);
        ImageView imageDutroc = (ImageView)view.findViewById(R.id.imageView10);
        final TextView descriptionDuTroc = (TextView)view.findViewById(R.id.trocDescription);
        TextView etatDuTroc = (TextView)view.findViewById(R.id.trocEtat);
        assert currentTrocList != null;

        nomDuTroc.setText(currentTrocList.getNomDeLarticle());
        imageDutroc.setImageResource(currentTrocList.getImageArticle());
        descriptionDuTroc.setText(currentTrocList.getDescriptionArticle());
        etatDuTroc.setText(currentTrocList.getEtatDeLarticle());
        // j ecoute mes Buttons car il attend un evenement
        descriptionDuTroc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick( View v ) {// depart de page,  vers l autre page
                descriptionDuTroc.setText("etudiant@uha.fr");
            }
        });
    return  view;

    }

}
