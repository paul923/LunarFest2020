package ca.acsea.funstop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class EventSub1 extends Fragment {

    View view;
    ImageView mapImg;

    public EventSub1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_event_sub1, container, false);

        onClickMap();

        // Inflate the layout for this fragment
        return view;
    }

    /**
     * Takes to the map application when map is clicked
     */
    public void onClickMap(){
        mapImg = view.findViewById(R.id.eventMap1);
        mapImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Uri gmmIntentUri = Uri.parse("https://www.google.ca/maps/place/Vancouver+Art+Gallery/@49.2829607,-123.1226602,17z/data=!3m1!4b1!4m5!3m4!1s0x5486717f7ffd7cc1:0xb595c3035cb17a4f!8m2!3d49.2829607!4d-123.1204715");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                }, 1000);
            }
        });
    }
}
