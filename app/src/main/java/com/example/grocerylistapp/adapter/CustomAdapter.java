package com.example.grocerylistapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.grocerylistapp.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    Context context;

    List<String> modelItem;
    public CustomAdapter(@NonNull Context context, List<String> resource) {
        super(context, R.layout.grocery_item,resource);
        this.context = context;
        this.modelItem = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.grocery_item,parent,false);
        TextView item = convertView.findViewById(R.id.textView1);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox1);

        item.setText(modelItem.get(position));
        return convertView;
    }
}
