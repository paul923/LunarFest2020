package ca.acsea.funstop.event;

import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
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

public class EventSub7 extends Fragment {

    View view;
    ImageView mapImg;
    private ImageView imageView;
    private int state = 0;
    private int numimg = 6;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MapSub mapSub;


    public EventSub7() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        mapSub = new MapSub("Varley Art Gallery of Markham",
                "Feb 2, 11AM ~ 4PM", new LatLng(43.869716, -79.312400));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#e6b773'>Varley Art Gallery of Markham </font>"));

        view = inflater.inflate(R.layout.activity_event_sub7, container, false);
        onClickMap();
        // Inflate the layout for this fragment

        return view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        imageView = (ImageView) getView().findViewById(R.id.eventPicture7);

        imageView.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {

                state++;
                state = state % numimg;

                switch (state) {

                    case 0:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.t3));
                        break;


                    case 1:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.t2));
                        break;

                    case 2:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.t1));
                        break;

                    case 3:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.t4));
                        break;

                    case 4:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.t6));
                        break;

                    case 5:  imageView.setImageDrawable(getResources().getDrawable(R.drawable.t6));
                        break;

                    default: imageView.setImageDrawable(getResources().getDrawable(R.drawable.t3));
                }
            }
        });



    }

    /**
     * Redirects to the map application when map is clicked
     */
    public void onClickMap(){
        mapImg = view.findViewById(R.id.eventMap7);
        mapImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.frameLayout, mapSub).addToBackStack("tag").commit();
            }
        });
    }
}