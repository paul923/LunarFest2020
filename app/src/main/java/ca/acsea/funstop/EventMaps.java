package ca.acsea.funstop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class EventMaps extends AppCompatActivity implements OnMapReadyCallback {


    private FragmentManager fragmentManager;
    private MapFragment mapFragment;
    private ArrayList<LatLng> locations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
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

}
