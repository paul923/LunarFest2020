package ca.acsea.funstop.event;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import ca.acsea.funstop.R;

public class EventSub7 extends Fragment {

    View view;
    ImageView mapImg;
    private ImageView imageView;
    private int state = 0;
    private int numimg = 6;


    public EventSub7() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

                    default: imageView.setImageDrawable(getResources().getDrawable(R.drawable.vag1));
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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Uri gmmIntentUri = Uri.parse("https://www.google.ca/maps/place/Varley+Art+Gallery+of+Markham/@43.8692296,-79.3148263,17z/data=!3m1!4b1!4m5!3m4!1s0x89d4d5c81b972c51:0xded7843301d2e649!8m2!3d43.8692296!4d-79.3126376");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                }, 1000);
            }
        });
    }
}