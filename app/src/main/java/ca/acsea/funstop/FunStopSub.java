package ca.acsea.funstop;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunStopSub extends AppCompatActivity{
    TextView textView;
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
    Boolean station1B;
    Boolean station2B;
    Boolean station3B;
    Boolean station4B;
    Boolean station5B;
    Boolean station6B;
    Boolean station7B;
    Boolean station8B;
    Boolean station9B;
    Boolean station10B;
    Boolean station11B;
    Boolean station12B;
    Boolean KoreanB;
    Boolean taiwaneseB;
    Boolean chineseB;
    Boolean vietnameseB;
    Boolean salishSea1B;
    Boolean salishSea2B;
    Boolean loneWolf1B;
    Boolean loneWolf2B;
    Boolean redFawn1B;
    Boolean redFawn2B;
    Boolean protector1B;
    Boolean protector2B;
    Boolean ladyHaoB;
    String qrValue="";
    SharedPreferences prefs;
    int points;
    List<CheckBox> arrayList;
    List<Boolean> arrayListBool;
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_fun_stop_sub);
        Intent intent = getIntent();

        if(intent.getStringExtra("source").equals("QrCodeScanner")){
            qrValue = intent.getStringExtra("qrValue");
        }

        prefs = getSharedPreferences( "prefs",0);
        station1 = findViewById(R.id.station1);
        station2 = findViewById(R.id.station2);
        station3 =  findViewById(R.id.station3);
        station4 = findViewById(R.id.station4);
        station5 =  findViewById(R.id.station5);
        station6 = findViewById(R.id.station6);
        station7 = findViewById(R.id.station7);
        station8 = findViewById(R.id.station8);
        station9 = findViewById(R.id.station9);
        station10 = findViewById(R.id.station10);
        station11 = findViewById(R.id.station11);
        station12 = findViewById(R.id.station12);
        Korean = findViewById(R.id.korean);
        taiwanese = findViewById(R.id.taiwanese);
        chinese = findViewById(R.id.chinese);
        vietnamese = findViewById(R.id.vietnamese);
        salishSea1 = findViewById(R.id.salishSea1);
        salishSea2 = findViewById(R.id.salishSea2);
        loneWolf1 = findViewById(R.id.loneWolf1);
        loneWolf2 = findViewById(R.id.loneWolf2);
        redFawn1 = findViewById(R.id.redFawn1);
        redFawn2 = findViewById(R.id.redFawn2);
        protector1 = findViewById(R.id.protector1);
        protector2 = findViewById(R.id.protector2);
        ladyHao = findViewById(R.id.ladyHao);



        // arrayList = Arrays.asList(Korean, chinese, ladyHao, loneWolf1);
        arrayList = new ArrayList<CheckBox>();
        arrayList.add(Korean);
        arrayList.add(chinese);
        arrayList.add(taiwanese);
        arrayList.add(vietnamese);
        arrayList.add(ladyHao);
        arrayList.add(loneWolf1);
        arrayList.add(loneWolf2);
        arrayList.add(protector1);
        arrayList.add(protector2);
        arrayList.add(redFawn1);
        arrayList.add(redFawn2);
        arrayList.add(salishSea1);
        arrayList.add(salishSea2);
        arrayList.add(station1);
        arrayList.add(station2);
        arrayList.add(station3);
        arrayList.add(station4);
        arrayList.add(station5);
        arrayList.add(station6);
        arrayList.add(station7);
        arrayList.add(station8);
        arrayList.add(station9);
        arrayList.add(station10);
        arrayList.add(station11);
        arrayList.add(station12);

        arrayListBool = new ArrayList<Boolean>();
        arrayListBool.add(KoreanB);
        arrayListBool.add(chineseB);
        arrayListBool.add(taiwaneseB);
        arrayListBool.add(vietnameseB);
        arrayListBool.add(ladyHaoB);
        arrayListBool.add(loneWolf1B);
        arrayListBool.add(loneWolf2B);
        arrayListBool.add(protector1B);
        arrayListBool.add(protector2B);
        arrayListBool.add(redFawn1B);
        arrayListBool.add(redFawn2B);
        arrayListBool.add(salishSea1B);
        arrayListBool.add(salishSea2B);
        arrayListBool.add(station1B);
        arrayListBool.add(station2B);
        arrayListBool.add(station3B);
        arrayListBool.add(station4B);
        arrayListBool.add(station5B);
        arrayListBool.add(station6B);
        arrayListBool.add(station7B);
        arrayListBool.add(station8B);
        arrayListBool.add(station9B);
        arrayListBool.add(station10B);
        arrayListBool.add(station11B);
        arrayListBool.add(station12B);

        setUp();

        int i;
        for (i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).setEnabled(false);
            if (arrayListBool.get(i)) {
                arrayList.get(i).setChecked(true);
            }
        }
        checkQRCodeValue();
        onClickQR();
    }

    public void onPause(){
        super.onPause();
        save();
    }

    public void onClickQR() {
        textView = findViewById(R.id.qrScanner);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("current user" + currentUser);
                System.out.println("ref" + ref);

                Intent i = new Intent(FunStopSub.this, QrCodeScanner.class);
                i.putExtra("previous", "FunStopSub");
                startActivity(i);

            }
        });
    }


    public void setUp(){
        System.out.println("Station one status:" + station1B);
        int i;
        for (i = 0; i < arrayListBool.size(); i++) {
            arrayListBool.set(i, prefs.getBoolean("element" + i, false));
        }
        System.out.println("Station one status:" + station1B);
        points = prefs.getInt("point", 0);
    }

    public void save(){
        SharedPreferences.Editor prefsEditor = prefs.edit();
        int i;
        for (i = 0; i < arrayListBool.size(); i++) {
            prefsEditor.putBoolean("element" + i, arrayListBool.get(i));
            prefsEditor.apply();
        }
        prefsEditor.putInt("points",points);
        prefsEditor.apply();
        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("point").setValue(points);
    }

    private void updatePoints(int point, String operation){
        if(operation.equals("Add")){
            points = points + point;
            FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("point").setValue(points);
        }
    }


    private void checkQRCodeValue(){
        switch(qrValue){
            case "station1":
                updatePoints(10, "Add");
                station1.setChecked(true);
                station1B = true;
                break;
            case "station2":
                updatePoints(10, "Add");
                station2.setChecked(true);
                station2B = true;
                break;
            case "station3":
                updatePoints(10, "Add");
                station3.setChecked(true);
                station3B = true;
                break;
            case "station4":
                updatePoints(10, "Add");
                station4.setChecked(true);
                station4B = true;
                break;
            case "station5":
                updatePoints(10, "Add");
                station5.setChecked(true);
                station5B = true;
                break;
            case "station6":
                updatePoints(10, "Add");
                station6.setChecked(true);
                station6B= true;
                break;
            case "station7":
                updatePoints(10, "Add");
                station7.setChecked(true);
                station7B = true;
                break;
            case "station8":
                updatePoints(10, "Add");
                station8.setChecked(true);
                station8B = true;
                break;
            case "station9":
                updatePoints(10, "Add");
                station9.setChecked(true);
                station9B = true;
                break;
            case "station10":
                updatePoints(10, "Add");
                station10.setChecked(true);
                station10B = true;
                break;
            case "station11":
                updatePoints(10, "Add");
                station11.setChecked(true);
                station11B = true;
                break;
            case "station12":
                updatePoints(10, "Add");
                station12.setChecked(true);
                station12B = true;
                break;
            case "ladyHao":
                updatePoints(40, "Add");
                ladyHao.setChecked(true);
                ladyHaoB = true;
                break;
            case "chinese":
                updatePoints(10, "Add");
                chinese.setChecked(true);
                chineseB = true;
                break;
            case "Korean":
                updatePoints(10, "Add");
                Korean.setChecked(true);
                KoreanB = true;
                break;
            case "taiwanese":
                updatePoints(10, "Add");
                taiwanese.setChecked(true);
                taiwaneseB = true;
                break;
            case "vietnamese":
                updatePoints(10, "Add");
                vietnamese.setChecked(true);
                vietnameseB = true;
                break;
            case "loneWolf1":
                updatePoints(5, "Add");
                loneWolf1.setChecked(true);
                loneWolf1B = true;
                break;
            case "loneWolf2":
                updatePoints(5, "Add");
                loneWolf2.setChecked(true);
                loneWolf2B = true;
                break;
            case "protector1":
                updatePoints(5, "Add");
                protector1.setChecked(true);
                protector1B = true;
                break;
            case "protector2":
                updatePoints(5, "Add");
                protector2.setChecked(true);
                protector2B = true;
                break;
            case "redFawn1":
                updatePoints(5, "Add");
                redFawn1.setChecked(true);
                redFawn1B = true;
                break;
            case "redFawn2":
                updatePoints(5, "Add");
                redFawn2.setChecked(true);
                redFawn2B = true;
                break;
            case "salishSea1":
                updatePoints(5, "Add");
                salishSea1.setChecked(true);
                salishSea1B = true;
                break;
            case "salish Sea 2":
                updatePoints(5, "Add");
                salishSea2.setChecked(true);
                salishSea2B = true;
                break;
        }
    }
}
