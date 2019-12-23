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

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


public class FunStop extends Fragment {
    Button btnStart;
    FragmentManager fragmentManager;
    FragmentTransaction transaction2;
    DatabaseReference ref;
    FirebaseUser user;
    FunStopSub funstopSub;

    public FunStop() {
        // Required empty public constructor
    }
    public FunStop(FragmentManager fm, FirebaseUser user, DatabaseReference ref) {
        fragmentManager=fm;
        this.user=user;
        this.ref=ref;


//        System.out.println("funstop constructor");
    }

    public void init() {
        Runnable r=new Runnable() {
            @Override
            public void run() {
                funstopSub=new FunStopSub(fragmentManager, user, ref);
            }
        };
        Thread thread=new Thread(r);
        thread.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        init();
        View view=inflater.inflate(R.layout.fragment_fun_stop, container, false);
        transaction2=fragmentManager.beginTransaction();

        btnStart=(Button)view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                transaction2.replace(R.id.frameLayout, funstopSub).addToBackStack("tag2").commit();
            }
        });
//        btnStart.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                ((MainActivity)getActivity()).replaceFragment(new FunStopSub(fragmentManager, user, ref));
//            }
//        });

        // Inflate the layout for this fragment
        return view;
    }

}
