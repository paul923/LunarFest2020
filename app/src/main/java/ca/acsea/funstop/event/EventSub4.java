package ca.acsea.funstop.event;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.model.LatLng;

import ca.acsea.funstop.R;

public class EventSub4 extends Fragment {

    View view;
    ImageView mapImg;
    private ImageView imageView;
    private int state = 0;
    private int numimg = 3;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MapSub mapSub;


    public EventSub4() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        mapSub = new MapSub("Jack Poole Plaza/Lot19",
                "Jan 18 ~ Feb 9", new LatLng(49.289448, -123.117141));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Jack Poole Plaza/Lot19");

        view = inflater.inflate(R.layout.activity_event_sub4, container, false);
        onClickMap();
        // Inflate the layout for this fragment

        return view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        imageView = (ImageView) getView().findViewById(R.id.eventPicture4);

        imageView.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {

                state++;
                state = state % numimg;

                switch (state) {

                    case 0:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.jack1));
                        break;


                    case 1:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.jack2));
                        break;

                    case 2:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.jack3));
                        break;



                    default: imageView.setImageDrawable(getResources().getDrawable(R.drawable.jack1));
                }
            }
        });



    }

    /**
     * Redirects to the map application when map is clicked
     */
    public void onClickMap(){
        mapImg = view.findViewById(R.id.eventMap4);
        mapImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.frameLayout, mapSub).addToBackStack("tag").commit();
            }
        });
    }
}