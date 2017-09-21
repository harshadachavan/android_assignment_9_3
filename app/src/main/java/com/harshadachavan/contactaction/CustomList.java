package com.harshadachavan.contactaction;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Harshada Chavan on 9/19/2017.
 */

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name;
    private final String[] number;

    public CustomList (Activity context , String[] name, String[] number) {

        super(context, R.layout.list_single, name);
        this.context = context;
        this.name = name;
        this.number = number;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.contactname);
        TextView txtNumber = (TextView) rowView.findViewById(R.id.contactnum);
        txtTitle.setText(name[position]);
        txtNumber.setText(number[position]);
        return rowView;
    }
}

