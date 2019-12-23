package ca.acsea.funstop;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class MyPoint extends AppCompatActivity{
    public static final String pointKey = "points";
    public static final String sharePreKey = "prefs";
    FirebaseUser user;
    private DatabaseReference db;
    EditText test;
    Button redeembtn;
    int points;
    boolean joinDraw;
    String qrValue= "";
    SharedPreferences sharedPreferences;
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        Intent intent = getIntent();
        sharedPreferences = this.getSharedPreferences(sharePreKey, Context.MODE_PRIVATE);

        if(intent.getStringExtra("source").equals("QrCodeScanner")){
            qrValue = intent.getStringExtra("qrValue");
        }
       // qrValue =  intent.getExtras().getString("qrValue");
        setContentView(R.layout.fragment_my_point);
        db= FirebaseDatabase.getInstance().getReference();

        test = findViewById(R.id.test);
        getPoints();
        checkQrValue();
        checkPoint();
        redeembtn = findViewById(R.id.redeembtn);
        redeembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyPoint.this, QrCodeScanner.class);
                i.putExtra("previous", "MyPoints");
                Bundle bundle = new Bundle();
                startActivity(i);
            }
        });
    }

    public void onPause(){
        super.onPause();
        savePoint();

    }



    private void getPoints(){
        SharedPreferences pref = this.getActivity().getSharedPreferences("prefs",0);
        points = pref.getInt("point", points);
        EditText pointText = getView().findViewById(R.id.text);
        pointText.setText(String.valueOf(points));
        joinDraw = pref.getBoolean("joinDraw", false);
    }

    private void savePoint(){
    SharedPreferences.Editor prefEditor = this.getSharedPreferences(sharePreKey,0).edit();
        prefEditor.putInt(pointKey, points);
        prefEditor.putBoolean("joinDraw", joinDraw);
        prefEditor.apply();
        System.out.println("in save point addinh");
        db.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("point").setValue(points);
        db.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("joinDraw").setValue(joinDraw);
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
  
}


