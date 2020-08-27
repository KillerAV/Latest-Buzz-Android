package com.example.newsapplicationroom.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.newsapplicationroom.R;

import java.util.ArrayList;

public class CountrySpinnerAdapter extends ArrayAdapter<CountryItem> {

    public CountrySpinnerAdapter(@NonNull Context context, ArrayList<CountryItem> countryItemArrayList) {
        super(context, 0, countryItemArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.spinner_dropdown_item, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.country_image);
        TextView textView = convertView.findViewById(R.id.country_name);

        CountryItem countryItem = getItem(position);
        if(countryItem != null) {
            imageView.setImageResource(countryItem.getCountryImage());
            textView.setText(countryItem.getCountryName());
        }
        return convertView;
    }
}
