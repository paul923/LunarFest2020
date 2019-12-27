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

public class EventSub2 extends Fragment {

    View view;
    ImageView mapImg;
    private ImageView imageView;
    private int state = 0;
    private int numimg = 10;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MapSub mapSub;


    public EventSub2() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        mapSub = new MapSub("Oakridge Centre",
                "Jan 16 ~ Feb 10", new LatLng(49.232469, -123.117416));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Oakridge Centre");

        view = inflater.inflate(R.layout.activity_event_sub2, container, false);
        onClickMap();
        // Inflate the layout for this fragment

        return view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        imageView = (ImageView) getView().findViewById(R.id.eventPicture2);

        imageView.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {

                state++;
                state = state % numimg;

                switch (state) {

                    case 0:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.oak1));
                        break;


                    case 1:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.oak2));
                        break;

                    case 2:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.oak3));
                        break;

                    case 3:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.oak4));
                        break;

                    case 4:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.oak5));
                        break;

                    case 5:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.oak6));
                        break;


                    case 6:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.vag3));
                        break;

                    case 7:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.vag4));
                        break;

                    case 8:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.vag5));
                        break;

                    case 9:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.vag6));
                        break;


                    default: imageView.setImageDrawable(getResources().getDrawable(R.drawable.oak1));

               

                }
            }
        });
    }

    /**
     * Redirects to the map application when map is clicked
     */
    public void onClickMap(){
        mapImg = view.findViewById(R.id.eventMap2);
        mapImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.frameLayout, mapSub).addToBackStack("tag").commit();
            }
        });
    }
}