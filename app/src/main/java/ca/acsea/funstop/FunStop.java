package ca.acsea.funstop;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


public class FunStop extends Fragment {
    Button btnStart;
    FragmentManager fragmentManager;
    FragmentTransaction transaction2;
    FunStopSub funstopSub;

    public FunStop() {
        // Required empty public constructor
    }
    public FunStop(FragmentManager fm, FirebaseUser user, DatabaseReference ref) {
        fragmentManager=fm;
        funstopSub=new FunStopSub(fm, user, ref);
        System.out.println("funstop constructor");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Changes the actionbar's Title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Fun Stop: How to play");
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
