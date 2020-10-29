package com.developer.david.apprestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdapterGalleryImage extends BaseAdapter {
    private Context context;
    private int pos;
    private LayoutInflater layoutInflater;
    private ImageView imageView;
    private ArrayList<JSONObject> vArray;

    public AdapterGalleryImage(Context applicationContext, ArrayList<JSONObject> aimages) {
        context = applicationContext;
        vArray = aimages;
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
        View view = layoutInflater.inflate(R.layout.item_gallery_image, parent, false);
        imageView = (ImageView) view.findViewById(R.id.ivImage);

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(vArray.get(position).getString("image"));
            Glide.with(context).load(Services.IMAGE + jsonArray.getJSONObject(0).getString("url")).into(imageView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}
