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

import java.util.ArrayList;


public class Map extends Fragment implements OnMapReadyCallback {

//    private FragmentManager fragmentManager;
//    private FragmentTransaction transaction;
    //private Fragment mapFragment;
    private MapView mapView;
    private ArrayList<LatLng> locations = new ArrayList<>();

    public Map() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Map");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
//        mapFragment = fragmentManager.findFragmentById(R.id.googleMap);
//        mapFragment.get(this);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) view.findViewById(R.id.googleMap);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return view;


    }

    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions eventMarker = new MarkerOptions();

        LatLng centerLocation = new LatLng(49.231730, -123.116663);
        locations.add(new LatLng(49.231740, -123.116673));
        locations.add(new LatLng(49.241750, -123.126683));
        locations.add(new LatLng(49.251760, -123.136693));

        eventMarker.position(centerLocation);
        eventMarker.title("someTitle");
        eventMarker.snippet("someDesc");
        googleMap.addMarker(eventMarker);

        for (LatLng point : locations) {
            eventMarker.position(point);
            eventMarker.title("someTitle");
            eventMarker.snippet("someDesc");
            googleMap.addMarker(eventMarker);
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLocation, 12));
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