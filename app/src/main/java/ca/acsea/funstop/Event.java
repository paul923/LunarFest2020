package ca.acsea.funstop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Event extends Fragment {

    ImageView imgTopLeft;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    EventSub1 sub1;
    public Event() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imgTopLeft=(ImageView) getView().findViewById(R.id.topLeft);

        imgTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                sub1 = new EventSub1();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.eventsub1, sub1).commitAllowingStateLoss();
            }
        });
    }

    //    public void onStart()
//    @Override
//    public void (@NonNull Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        imgTopLeft=(ImageView) getView().findViewById(R.id.topLeft);
//
//        imgTopLeft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sub1 = new EventSub1();
//                transaction.replace(R.id.eventsub1, sub1).commitAllowingStateLoss();
//            }
//        });
//    }
}
