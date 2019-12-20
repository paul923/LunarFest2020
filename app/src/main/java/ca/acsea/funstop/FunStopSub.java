package ca.acsea.funstop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FunStopSub extends Fragment implements Serializable {
    View view;
    TextView textView;
    FragmentManager fragmentManager;
    FirebaseUser currentUser;
    DatabaseReference ref;
    CheckBox station1;
    CheckBox station2;
    CheckBox station3;
    CheckBox station4;
    CheckBox station5;
    CheckBox station6;
    CheckBox station7;
    CheckBox station8;
    CheckBox station9;
    CheckBox station10;
    CheckBox station11;
    CheckBox station12;
    CheckBox Korean;
    CheckBox taiwanese;
    CheckBox chinese;
    CheckBox vietnamese;
    CheckBox salishSea1;
    CheckBox salishSea2;
    CheckBox loneWolf1;
    CheckBox loneWolf2;
    CheckBox redFawn1;
    CheckBox redFawn2;
    CheckBox protector1;
    CheckBox protector2;
    CheckBox ladyHao;


    public FunStopSub() {
        System.out.println("Working funstopsub?");

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

        //Changes the actionbar's Title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Fun Stop");
        view=inflater.inflate(R.layout.fragment_fun_stop_sub, container, false);

        init();
        onClickQR();
        // Inflate the layout for this fragment




        return view;

    }



    public void onClickQR(){
        textView = view.findViewById(R.id.qrScanner);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("current user"+currentUser);
                System.out.println("ref"+ref);

                Intent i=new Intent(getActivity(), QrCodeScanner.class);
                startActivity(i);

            }
        });
    }

    public void init() {
            ref.child("users").child(currentUser.getUid()).child("QR").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    station1=(CheckBox)view.findViewById(R.id.station1);
                    station2=(CheckBox)view.findViewById(R.id.station2);
                    station3=(CheckBox)view.findViewById(R.id.station3);
                    station4=(CheckBox)view.findViewById(R.id.station4);
                    station5=(CheckBox)view.findViewById(R.id.station5);
                    station6=(CheckBox)view.findViewById(R.id.station6);
                    station7=(CheckBox)view.findViewById(R.id.station7);
                    station8=(CheckBox)view.findViewById(R.id.station8);
                    station9=(CheckBox)view.findViewById(R.id.station9);
                    station10=(CheckBox)view.findViewById(R.id.station10);
                    station11=(CheckBox)view.findViewById(R.id.station11);
                    station12=(CheckBox)view.findViewById(R.id.station12);
                    Korean=(CheckBox)view.findViewById(R.id.korean);
                    taiwanese=(CheckBox)view.findViewById(R.id.taiwanese);
                    chinese=(CheckBox)view.findViewById(R.id.chinese);
                    vietnamese=(CheckBox)view.findViewById(R.id.vietnamese);
                    salishSea1=(CheckBox)view.findViewById(R.id.salishSea1);
                    salishSea2=(CheckBox)view.findViewById(R.id.salishSea2);
                    loneWolf1=(CheckBox)view.findViewById(R.id.loneWolf1);
                    loneWolf2=(CheckBox)view.findViewById(R.id.loneWolf2);
                    redFawn1=(CheckBox)view.findViewById(R.id.redFawn1);
                    redFawn2=(CheckBox)view.findViewById(R.id.redFawn2);
                    protector1=(CheckBox)view.findViewById(R.id.protector1);
                    protector2=(CheckBox)view.findViewById(R.id.protector2);
                    ladyHao=(CheckBox)view.findViewById(R.id.ladyHao);

                    List<CheckBox> arrayList= Arrays.asList(Korean, chinese, ladyHao, loneWolf1, loneWolf2, protector1, protector2, redFawn1, redFawn2, salishSea1, salishSea2, station1, station10, station11, station12, station2, station3, station4, station5, station6, station7, station8, station9, taiwanese, vietnamese);

                    int i=0;
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        String value = snapshot.getValue().toString();
                        if(i==24) {
                            break;
                        }
                        if(value.equals("true")) {
                            arrayList.get(i).setChecked(true);
                            i++;
                        }else {
                            i++;

                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    System.out.println("error occurs");
                }
            });
    }
}

