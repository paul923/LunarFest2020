package ca.acsea.funstop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//import java.util.ArrayList;


public class Map extends Fragment implements OnMapReadyCallback {

    private MapView mapView;


    public Map() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Map");

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) view.findViewById(R.id.googleMap);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return view;


    }

    public void onMapReady(GoogleMap googleMap) {

        MarkerOptions eventMarker = new MarkerOptions();


        eventMarker.position(new LatLng(49.2829607,-123.1226602));
        eventMarker.title("Vancouver Art Gallery - LunarFest Celebrations");
        eventMarker.snippet("Art gallery");
        googleMap.addMarker(eventMarker);

        LatLng centerLocation = new LatLng(49.232469, -123.117416);
        eventMarker.position(centerLocation);
        eventMarker.title("Oakridge Centre - LunarFest Visual Arts");
        eventMarker.snippet("Shopping mall");
        googleMap.addMarker(eventMarker);

        eventMarker.position(new LatLng(49.196778, -123.181264));
        eventMarker.title("YVR - The Tunnel of Blessings");
        eventMarker.snippet("Airport");
        googleMap.addMarker(eventMarker);

        eventMarker.position(new LatLng(49.289448, -123.117141));
        eventMarker.title("Jack Poole Plaza/Lot19 - Coastal Lunar Lanterns");
        eventMarker.snippet("Public amenity house");
        googleMap.addMarker(eventMarker);

        eventMarker.position(new LatLng(49.280505, -123.112767));
        eventMarker.title("Queen Elizabeth Theatre - A Musical Banquet");
        eventMarker.snippet("Theatre");
        googleMap.addMarker(eventMarker);


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLocation, 11));
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}