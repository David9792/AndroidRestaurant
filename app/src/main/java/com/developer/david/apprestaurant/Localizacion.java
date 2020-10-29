package com.developer.david.apprestaurant;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Localizacion implements LocationListener {

    crear_restaurant crear_restaurant;
    TextView txtubicacion;

    public crear_restaurant getCrear(){
        return crear_restaurant;
    }

    public void setCrear(crear_restaurant crear_restaurant, TextView txtubicacion){
        this.crear_restaurant = crear_restaurant;
        this.txtubicacion = txtubicacion;
    }

    @Override
    public void onLocationChanged(Location location) {
        //Este metodo se ejecuta cuando el GPS recibe nuevas coordenadas
       String texto = "Mi ubicacion es: \n"
                + "Latitude = " + location.getLatitude() + "\n"
                + "Longitude = " + location.getLongitude();

        crear_restaurant.lat =String.valueOf(location.getLatitude());
        crear_restaurant.lng =String.valueOf(location.getLongitude());

        txtubicacion.setText(texto);

        mapa(location.getLatitude(), location.getLongitude());
    }

    public void mapa(double lat, double lon){
        //Fragment del Mapa
        FragmentMaps fragment = new FragmentMaps();

        Bundle bundle = new Bundle();
        bundle.putDouble("lat", new Double(lat));
        bundle.putDouble("lon", new Double(lon));
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getCrear().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.map_frat, fragment, null);
        fragmentTransaction.commitAllowingStateLoss();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status){
            case LocationProvider
                    .AVAILABLE:
                Log.d("debug", "LocationProvider.AVAILABLE");
                break;
            case LocationProvider
                    .OUT_OF_SERVICE:
                Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                break;
            case LocationProvider
                    .TEMPORARILY_UNAVAILABLE:
                Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        txtubicacion.setText("GPS Activado");
    }

    @Override
    public void onProviderDisabled(String provider) {
        txtubicacion.setText("GPS Desactivado");
    }
}
