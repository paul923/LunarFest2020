package ca.acsea.funstop.event;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ca.acsea.funstop.Globals;
import ca.acsea.funstop.MainActivity;
import ca.acsea.funstop.MyPoint;
import ca.acsea.funstop.R;


public class Event extends Fragment {

    ImageView event1;
    ImageView event2;
    ImageView event3;
    ImageView event4;
    ImageView event5;
    ImageView event6;
    ImageView event7;

    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    EventSub1 sub1;
    EventSub2 sub2;
    EventSub3 sub3;
    EventSub4 sub4;
    EventSub5 sub5;
    EventSub6 sub6;
    EventSub7 sub7;
    public Event() {
        // Required empty public constructor
    }

    public Event(FragmentManager fm){
        fragmentManager = fm;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Event");

        if(Globals.getInstance().getData() == 1){
            sub6 = new EventSub6();
            sub7 = new EventSub7();
            View view2 = inflater.inflate(R.layout.fragment_event2, container, false);

            //Toronto events
            event6=(ImageView) view2.findViewById(R.id.event6inList);
            event6.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view2) {
                    transaction.replace(R.id.frameLayout, sub6).addToBackStack("tag").commit();
                }
            });

            event7=(ImageView) view2.findViewById(R.id.event7inList);
            event7.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view2) {
                    transaction.replace(R.id.frameLayout, sub7).addToBackStack("tag").commit();
                }
            });
            return view2;

        } else{
            sub1 = new EventSub1();
            sub2 = new EventSub2();
            sub3 = new EventSub3();
            sub4 = new EventSub4();
            sub5 = new EventSub5();
            View view = inflater.inflate(R.layout.fragment_event, container, false);

            transaction = fragmentManager.beginTransaction();

            event1=(ImageView) view.findViewById(R.id.event1inList);
            event1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
//                ((MainActivity) getActivity()).replaceFragment(sub1);

                    //Add replacing fragment to backstack to allow back button
                    transaction.replace(R.id.frameLayout, sub1).addToBackStack("tag").commit();
                }
            });

            event2=(ImageView) view.findViewById(R.id.event2inList);
            event2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    transaction.replace(R.id.frameLayout, sub2).addToBackStack("tag").commit();
                }
            });

            event3=(ImageView) view.findViewById(R.id.event3inList);
            event3.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    transaction.replace(R.id.frameLayout, sub3).addToBackStack("tag").commit();
                }
            });

            event4=(ImageView) view.findViewById(R.id.event4inList);
            event4.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    transaction.replace(R.id.frameLayout, sub4).addToBackStack("tag").commit();
                }
            });

            event5=(ImageView) view.findViewById(R.id.event5inList);
            event5.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    transaction.replace(R.id.frameLayout, sub5).addToBackStack("tag").commit();
                }
            });
            return view;
        }

    }



}