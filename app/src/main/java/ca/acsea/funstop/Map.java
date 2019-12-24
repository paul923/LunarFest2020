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
    protected int mapCode;


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
        mapCode = 1; // 0 is Vancouver, 1 is Toronto

        MarkerOptions eventMarker = new MarkerOptions();
        if (Globals.getInstance().getData() == 0){ //Vancouver
            eventMarker.position(new LatLng(49.2829607, -123.1226602));
            eventMarker.title("Vancouver Art Gallery - LunarFest Celebrations");
            eventMarker.snippet("Art gallery");
            googleMap.addMarker(eventMarker);


            eventMarker.position(new LatLng(49.196778, -123.181264));
            eventMarker.title("YVR - The Tunnel of Blessings");
            eventMarker.snippet("Airport");
            googleMap.addMarker(eventMarker);

            eventMarker.position(new LatLng(49.232469, -123.117416));
            eventMarker.title("Oakridge Centre - LunarFest Visual Arts");
            eventMarker.snippet("Shopping mall");
            googleMap.addMarker(eventMarker);

            eventMarker.position(new LatLng(49.289448, -123.117141));
            eventMarker.title("Jack Poole Plaza/Lot19 - Coastal Lunar Lanterns");
            eventMarker.snippet("Public amenity house");
            googleMap.addMarker(eventMarker);

            eventMarker.position(new LatLng(49.280505, -123.112767));
            eventMarker.title("Queen Elizabeth Theatre - A Musical Banquet");
            eventMarker.snippet("Theatre");
            googleMap.addMarker(eventMarker);

            LatLng centerLocation = new LatLng(49.241970, -123.143407);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLocation, 12));
        } else { //Toronto
            eventMarker.position(new LatLng(43.589701, -79.645842));
            eventMarker.title("Living Arts Centre - LunarFest Celebrations");
            eventMarker.snippet("Performing arts theater");
            googleMap.addMarker(eventMarker);

            eventMarker.position(new LatLng(43.869716, -79.312400));
            eventMarker.title("Varley Art Gallery of Markham - LunarFest Celebrations");
            eventMarker.snippet("Museum");
            googleMap.addMarker(eventMarker);

            LatLng centerLocation = new LatLng(43.708336, -79.491058);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLocation, 10));
        }

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