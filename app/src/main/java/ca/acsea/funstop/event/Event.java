package ca.acsea.funstop.event;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import ca.acsea.funstop.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Event extends Fragment {

    ImageView imgTopLeft, mapImg;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    EventSub1 sub1;
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
        sub1 = new EventSub1();
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        transaction = fragmentManager.beginTransaction();

        imgTopLeft=(ImageView) view.findViewById(R.id.event1inList);
        imgTopLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                ((MainActivity) getActivity()).replaceFragment(sub1);

                //Add replacing fragment to backstack to allow back button
                transaction.replace(R.id.frameLayout, sub1).addToBackStack("tag").commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
