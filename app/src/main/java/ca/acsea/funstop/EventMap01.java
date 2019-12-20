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

public class EventMap01 extends AppCompatActivity implements OnMapReadyCallback {

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_map01);

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
    }


    public void onMapReady(GoogleMap googleMap){
        LatLng location = new LatLng(49.231730, -123.116663);
        MarkerOptions event01Marker = new MarkerOptions();
        event01Marker.title("Oakridge Mall KFC");
        event01Marker.snippet("Lunar Fest Event Location");
        event01Marker.position(location);
        googleMap.addMarker(event01Marker);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16));


    }

}
