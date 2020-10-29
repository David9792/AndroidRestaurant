package com.developer.david.apprestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterGalleriaTwo extends BaseAdapter {
    private Context context;
    private int pos;
    private LayoutInflater layoutInflater;
    private ImageView imageView;
    private ArrayList<String> vArray;

    public AdapterGalleriaTwo(Context applicationContext, ArrayList<String> arrayList) {
        context = applicationContext;
        vArray = arrayList;
    }

    @Override
    public int getCount() {
        return vArray.size();
    }

    @Override
    public Object getItem(int position) {
        return vArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        pos = position;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=  layoutInflater.inflate(R.layout.item_gallery_image, parent, false);
        imageView =  (ImageView) view.findViewById(R.id.ivImage);

        Glide.with(context).load(Services.IMAGE + vArray.get(position)).into(imageView);

        return view;
    }
}
