package ca.acsea.funstop.event;


        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.Fragment;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapView;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;

        import ca.acsea.funstop.R;

public class MapSub extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    public String eventLocation;
    public String eventTime;
    public LatLng eventPosition;


    public MapSub() {
        // Required empty public constructor
    }

    public MapSub(String eventLocation, String eventTime, LatLng eventPosition) {
        this.eventLocation = eventLocation;
        this.eventTime = eventTime;
        this.eventPosition = eventPosition;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(eventLocation);

        View view = inflater.inflate(R.layout.fragment_mapsub, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) view.findViewById(R.id.googleMapSub);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return view;
    }

    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions eventMarker = new MarkerOptions();
        eventMarker.position(eventPosition);
        eventMarker.title(eventLocation);
        eventMarker.snippet(eventTime);
        googleMap.addMarker(eventMarker);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(eventPosition, 16));
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

