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

        sub1 = new EventSub1();
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        imgTopLeft=(ImageView) view.findViewById(R.id.event1inList);

        imgTopLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(sub1);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }



}
