package com.example.myapplicationtroc.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapplicationtroc.R;

public class TrocAdaptateur extends ArrayAdapter<Troc> {
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
    Troc currentTroc = getItem(position);
        TextView nomDuTroc = (TextView)view.findViewById(R.id.typeTroc);
        TextView descriptionDuTroc = (TextView)view.findViewById(R.id.trocDescription);
        TextView etatDuTroc = (TextView)view.findViewById(R.id.trocEtat);
        assert currentTroc != null;
        nomDuTroc.setText(currentTroc.getNomDeLarticle());
        descriptionDuTroc.setText(currentTroc.getDescriptionArticle());
        etatDuTroc.setText(currentTroc.getEtatDeLarticle());
    return  view;

    }
}
