package com.example.myapplicationtroc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplicationtroc.R;

public class MonAdaptateurDeListe extends ArrayAdapter<String> {

    private Integer[] tab_images_pour_la_liste = {
            R.drawable.device, R.drawable.geolocation
             };

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        textView.setText(getItem(position));

        if(convertView == null )
            imageView.setImageResource(tab_images_pour_la_liste[position]);
        else
            rowView = (View)convertView;

        return rowView;
    }

    public MonAdaptateurDeListe(Context context, String[] values) {
        super(context, R.layout.rowlayout, values);
    }
}