package ca.acsea.funstop;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MyPoint extends Fragment {
    FirebaseUser user;
    private DatabaseReference db;
    EditText test;
    Button redeembtn;
    int points =150;
    boolean joinDraw;
    String qrValue;
    public MyPoint() {
        // Required empty public constructor
    }
    public MyPoint(FirebaseUser u) {
        // Required empty public constructor
        this.user = u;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Changes the actionbar's Title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Point");
        // Inflate the layout for this fragment
        db= FirebaseDatabase.getInstance().getReference();
        System.out.println("DONE??"+user.getUid());
        // read();
        qrValue = getArguments().getString("qrValue");
        checkQrValue();
        getPoints();
        View view =inflater.inflate(R.layout.fragment_my_point, container, false);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        getPoints();
    }
    @Override
    public void onStop(){
        super.onStop();
        savePoint();
    }
    private void savePoint(){
        SharedPreferences.Editor prefEditor = this.getActivity().getSharedPreferences("prefs",0).edit();
        prefEditor.putInt("point", points);
        prefEditor.putBoolean("joinDraw", joinDraw);
        prefEditor.apply();
        db.child(user.getUid()).child("point").setValue(points);
        db.child(user.getUid()).child("joinDraw").setValue(joinDraw);
    }

    private void getPoints(){
        SharedPreferences pref = this.getActivity().getSharedPreferences("prefs",0);
        points = pref.getInt("point", points);
        EditText pointText = getView().findViewById(R.id.text);
        pointText.setText(String.valueOf(points));
        joinDraw = pref.getBoolean("joinDraw", false);
    }

    public void onClickQR(){
        redeembtn = getView().findViewById(R.id.qrScanner);
        Intent i=new Intent(getActivity(), QrCodeScanner.class);
        startActivity(i);
    }

    public void checkQrValue(){
        switch (qrValue){
            case "R_200OPT":
                modifyPoints(200, "Reduce");
            case "R_150PT":
                modifyPoints(150, "Reduce");
            case "R_100PT":
                modifyPoints(100, "Reduce");
            case "R_50PT":
                modifyPoints(50, "Reduce");
            case "R_20PT":
                modifyPoints(20, "Reduce");
            case "R_10PT":
                modifyPoints(10, "Reduce");
            case "A_50PT":
                modifyPoints(50, "Add");
            case "A_40PT":
                modifyPoints(40, "Add");
            case "A_10PT":
                modifyPoints(10, "Add");
            case "A_5PT":
                modifyPoints(5, "Add");
        }
    }

    private void modifyPoints(int point, String operation){
        if(operation.equalsIgnoreCase("Add")){
            points = points + point;
        }
        if(operation.equalsIgnoreCase("Reduce")){
            points = points - point;
        }
        savePoint();
    }
    private void checkPoint(){
        if(points >= 150 && !joinDraw){
            new AlertDialog.Builder(MyPoint.this.getContext()).setTitle("Congrats")
                    .setMessage("You have reached 150 points. Would you like to join the draw for a $200 Visa Gift Card? It'll" +
                            "cost 150 points.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            addToPool();
                        }})
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Cancel
                        }})
                    .show();
        }
    }

    private void addToPool(){
        db.child(user.getUid()).child("JoinDraw").setValue("Yes");
        joinDraw = true;
        modifyPoints(150, "Reduce");
        db.child(user.getUid()).child("point").setValue(points);
    }
 /*   private void read() {
//        final String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.child("users").child(user.getUid()).child("point").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println("datasnapshot"+dataSnapshot.getValue());
                EditText test=getView().findViewById(R.id.test);
                test.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("working2");
            }
        });
    }*/
}