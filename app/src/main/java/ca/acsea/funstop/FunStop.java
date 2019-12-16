package ca.acsea.funstop;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class FunStop extends Fragment {
    Button btnStart;
    FragmentManager fragmentManager;
    FragmentTransaction transaction2;
    FunStopSub funstopSub;

    public FunStop() {
        // Required empty public constructor
    }
    public FunStop(FragmentManager fm) {
        fragmentManager=fm;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        funstopSub=new FunStopSub();
        View view=inflater.inflate(R.layout.fragment_fun_stop, container, false);

        transaction2=fragmentManager.beginTransaction();

        btnStart=(Button)view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                transaction2.replace(R.id.frameLayout, funstopSub).addToBackStack("tag2").commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
