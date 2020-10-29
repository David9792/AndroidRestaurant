package com.developer.david.apprestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mostrar_Galeria extends AppCompatActivity {
    private Button btn;
    private ArrayList<String> arrayList;
    private GridView gridView;
    private AdapterGalleriaTwo adapterGalleriaTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar__galeria);
        try {
            loadComponents();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void loadComponents() throws JSONException {
        gridView = (GridView) findViewById(R.id.gvMostrarImg);
        arrayList = new ArrayList<String>();
        btn = findViewById(R.id.btnAgregarImagen);
        final JSONObject jsonObject = new JSONObject(getIntent().getExtras().get("url").toString());
        final JSONArray jsonArray = new JSONArray(jsonObject.getString("image"));

        for(int i=0; i<jsonArray.length(); i++){
            arrayList.add(jsonArray.getJSONObject(i).getString("url"));
        }

        adapterGalleriaTwo = new AdapterGalleriaTwo(getApplicationContext(), arrayList);
        gridView.setAdapter(adapterGalleriaTwo);

        Toast.makeText(Mostrar_Galeria.this, jsonArray+"", Toast.LENGTH_SHORT).show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Mostrar_Galeria.this, AddToGallery.class);
                    intent.putExtra("url", jsonObject.getString("_id"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

