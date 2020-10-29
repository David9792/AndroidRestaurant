package com.developer.david.apprestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class subirImg extends AppCompatActivity {
    private ArrayList<JSONObject> Aimages;
    private GridView gridView;
    private AdapterGalleryImage adapterGalleryImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_img);
        loadComponents();

    }

    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient();
        gridView = (GridView) findViewById(R.id.gvSubirImg);
        Aimages = new ArrayList<JSONObject>();
        client.get(Services.RESTAURANT, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    for(int i=0; i<response.length(); i++){
                        Aimages.add(response.getJSONObject(i));
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
                adapterGalleryImage = new AdapterGalleryImage(getApplicationContext(), Aimages);
                gridView.setAdapter(adapterGalleryImage);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(subirImg.this, Mostrar_Galeria.class);
                        intent.putExtra("url", adapterGalleryImage.getItem(position).toString());
                        startActivity(intent);
                    }
                });
            }
        });

    }
}
