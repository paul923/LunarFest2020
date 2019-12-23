package ca.acsea.funstop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class FunStopSub extends Fragment implements Serializable {
    HashMap<String, CheckBox> checkbox = new HashMap<>();
    HashMap<String, String> map=new HashMap<>();
    View view;
    TextView textView;
    FragmentManager fragmentManager;
    FirebaseUser currentUser;
    DatabaseReference ref;
//    CheckBox station1;
//    CheckBox station2;
//    CheckBox station3;
//    CheckBox station4;
//    CheckBox station5;
//    CheckBox station6;
//    CheckBox station7;
//    CheckBox station8;
//    CheckBox station9;
//    CheckBox station10;
//    CheckBox station11;
//    CheckBox station12;
//    CheckBox Korean;
//    CheckBox taiwanese;
//    CheckBox chinese;
//    CheckBox vietnamese;
//    CheckBox salishSea1;
//    CheckBox salishSea2;
//    CheckBox loneWolf1;
//    CheckBox loneWolf2;
//    CheckBox redFawn1;
//    CheckBox redFawn2;
//    CheckBox protector1;
//    CheckBox protector2;
//    CheckBox ladyHao;
//    DataSnapshot test;
//
//    String s1Result;

    public FunStopSub() {
//        System.out.println("Working funstopsub?");

    }

    public FunStopSub(FragmentManager fm, FirebaseUser user, DatabaseReference ref) {
        this.fragmentManager=fm;
        this.currentUser=user;
        this.ref=ref;
        System.out.println("Working? fss constructor");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("fss init working?");
        System.out.println("what is ref"+ref);
        System.out.println(ref.child("users").child(currentUser.getUid()).child("QR"));
        init();
        view=inflater.inflate(R.layout.fragment_fun_stop_sub, container, false);
//        Runnable r =new Runnable() {
//            @Override
//            public void run() {

//                System.out.println("Thread"+Thread.currentThread().getName());
//            }
//        };

        Runnable r2=new Runnable() {
            @Override
            public void run() {
                onClickQR();
            }
        };

//        Thread thread=new Thread(r);
        Thread thread2=new Thread(r2);

//        thread.start();
        thread2.start();



        // Inflate the layout for this fragment

//        SharedPreferences sharedPreferences=getActivity().getPreferences(getContext().MODE_PRIVATE);
//        SharedPreferences.Editor edt=sharedPreferences.edit();
//
//        if(ref.database.station1==true)
//            station1key=true;
//        else
//            sharedPreferences.getString("station1Key", "false");
//
//        edt.commit();


        return view;

    }



    public void onClickQR(){
        textView = view.findViewById(R.id.qrScanner);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent i=new Intent(getActivity(), QrCodeScanner.class);
            startActivity(i);

            }
        });
    }

    public void init() {
        System.out.println("init inside");
//        ValueEventListener valueEventListener=new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                System.out.println("Done? before");
//                checkbox.put("station1", (CheckBox)view.findViewById(R.id.station1));
//                checkbox.put("station2", (CheckBox)view.findViewById(R.id.station2));
//                checkbox.put("station3", (CheckBox)view.findViewById(R.id.station3));
//                checkbox.put("station4", (CheckBox)view.findViewById(R.id.station4));
//                checkbox.put("station5", (CheckBox)view.findViewById(R.id.station5));
////                checkbox.put("station6", (CheckBox)view.findViewById(R.id.station6));
////                checkbox.put("station7", (CheckBox)view.findViewById(R.id.station7));
////                checkbox.put("station8", (CheckBox)view.findViewById(R.id.station8));
////                checkbox.put("station9", (CheckBox)view.findViewById(R.id.station9));
////                checkbox.put("station10", (CheckBox)view.findViewById(R.id.station10));
////                checkbox.put("station11", (CheckBox)view.findViewById(R.id.station11));
////                checkbox.put("station12", (CheckBox)view.findViewById(R.id.station12));
//
////                checkbox.put("Korean", (CheckBox)view.findViewById(R.id.korean));
////                checkbox.put("taiwanese", (CheckBox)view.findViewById(R.id.taiwanese));
////                checkbox.put("chinese", (CheckBox)view.findViewById(R.id.chinese));
////                checkbox.put("vietnamese", (CheckBox)view.findViewById(R.id.vietnamese));
////                checkbox.put("salishSea1", (CheckBox)view.findViewById(R.id.salishSea1));
////                checkbox.put("salishSea2", (CheckBox)view.findViewById(R.id.salishSea2));
////                checkbox.put("loneWolf1", (CheckBox)view.findViewById(R.id.loneWolf1));
////                checkbox.put("loneWolf2", (CheckBox)view.findViewById(R.id.loneWolf2));
////                checkbox.put("redFawn1", (CheckBox)view.findViewById(R.id.redFawn1));
////                checkbox.put("redFawn2", (CheckBox)view.findViewById(R.id.redFawn2));
////                checkbox.put("protector1", (CheckBox)view.findViewById(R.id.protector1));
////                checkbox.put("protector2", (CheckBox)view.findViewById(R.id.protector2));
////                checkbox.put("ladyHao", (CheckBox)view.findViewById(R.id.ladyHao));


//                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
//                    map.put(snapshot.getKey(), snapshot.getValue().toString());
//                }
//                for (String key : checkbox.keySet()) {
//                    if (map.containsKey(key) && map.get(key).equals("true")) {
//                        checkbox.get(key).setChecked(true);
//                    }
//                }
//                System.out.println("Done? after");
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                System.out.println("Failed");
//            }
//        };
//        ref.child("users").child(currentUser.getUid()).child("QR").addValueEventListener(valueEventListener);

        ref.child("users").child(currentUser.getUid()).child("QR").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("Done? before");
//                    station1=(CheckBox)view.findViewById(R.id.station1);
//                    station2=(CheckBox)view.findViewById(R.id.station2);
//                    station3=(CheckBox)view.findViewById(R.id.station3);
//                    station4=(CheckBox)view.findViewById(R.id.station4);
//                    station5=(CheckBox)view.findViewById(R.id.station5);
//                    station6=(CheckBox)view.findViewById(R.id.station6);
//                    station7=(CheckBox)view.findViewById(R.id.station7);
//                    station8=(CheckBox)view.findViewById(R.id.station8);
//                    station9=(CheckBox)view.findViewById(R.id.station9);
//                    station10=(CheckBox)view.findViewById(R.id.station10);
//                    station11=(CheckBox)view.findViewById(R.id.station11);
//                    station12=(CheckBox)view.findViewById(R.id.station12);
//                    Korean=(CheckBox)view.findViewById(R.id.korean);
//                    taiwanese=(CheckBox)view.findViewById(R.id.taiwanese);
//                    chinese=(CheckBox)view.findViewById(R.id.chinese);
//                    vietnamese=(CheckBox)view.findViewById(R.id.vietnamese);
//                    salishSea1=(CheckBox)view.findViewById(R.id.salishSea1);
//                    salishSea2=(CheckBox)view.findViewById(R.id.salishSea2);
//                    loneWolf1=(CheckBox)view.findViewById(R.id.loneWolf1);
//                    loneWolf2=(CheckBox)view.findViewById(R.id.loneWolf2);
//                    redFawn1=(CheckBox)view.findViewById(R.id.redFawn1);
//                    redFawn2=(CheckBox)view.findViewById(R.id.redFawn2);
//                    protector1=(CheckBox)view.findViewById(R.id.protector1);
//                    protector2=(CheckBox)view.findViewById(R.id.protector2);
//                    ladyHao=(CheckBox)view.findViewById(R.id.ladyHao);

//                    if station1key == "false":


                checkbox.put("station1", (CheckBox)view.findViewById(R.id.station1));
                checkbox.put("station2", (CheckBox)view.findViewById(R.id.station2));
                checkbox.put("station3", (CheckBox)view.findViewById(R.id.station3));
                checkbox.put("station4", (CheckBox)view.findViewById(R.id.station4));
                checkbox.put("station5", (CheckBox)view.findViewById(R.id.station5));
                checkbox.put("station6", (CheckBox)view.findViewById(R.id.station6));
                checkbox.put("station7", (CheckBox)view.findViewById(R.id.station7));
                checkbox.put("station*", (CheckBox)view.findViewById(R.id.station8));
                checkbox.put("station9", (CheckBox)view.findViewById(R.id.station9));
                checkbox.put("station10", (CheckBox)view.findViewById(R.id.station10));
                checkbox.put("station11", (CheckBox)view.findViewById(R.id.station11));
                checkbox.put("station12", (CheckBox)view.findViewById(R.id.station12));
                checkbox.put("Korean", (CheckBox)view.findViewById(R.id.korean));
                checkbox.put("taiwanese", (CheckBox)view.findViewById(R.id.taiwanese));
                checkbox.put("chinese", (CheckBox)view.findViewById(R.id.chinese));
                checkbox.put("vietnamese", (CheckBox)view.findViewById(R.id.vietnamese));
                checkbox.put("salishSea1", (CheckBox)view.findViewById(R.id.salishSea1));
                checkbox.put("salishSea2", (CheckBox)view.findViewById(R.id.salishSea2));
                checkbox.put("loneWolf1", (CheckBox)view.findViewById(R.id.loneWolf1));
                checkbox.put("loneWolf2", (CheckBox)view.findViewById(R.id.loneWolf2));
                checkbox.put("redFawn1", (CheckBox)view.findViewById(R.id.redFawn1));
                checkbox.put("redFawn2", (CheckBox)view.findViewById(R.id.redFawn2));
                checkbox.put("protector1", (CheckBox)view.findViewById(R.id.protector1));
                checkbox.put("protector2", (CheckBox)view.findViewById(R.id.protector2));
                checkbox.put("ladyHao", (CheckBox)view.findViewById(R.id.ladyHao));
//                System.out.println("result"+checkbox.get("station1"));
//                System.out.println("result2"+checkbox.get("station2").toString());

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    map.put(snapshot.getKey(), snapshot.getValue().toString());
                }
                for (String key : checkbox.keySet()) {
                    if (map.containsKey(key) && map.get(key).equals("true")) {
                        checkbox.get(key).setChecked(true);
                    }
                }

//                    test = dataSnapshot;
                System.out.println("inside working?");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("error occurs");
            }
        });
    }

}


