package ca.acsea.funstop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.navigation.NavigationView;

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

import ca.acsea.funstop.event.Event;
import ca.acsea.funstop.sponsorquiz.QuizStart;


public class FunStopSub extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction transaction;

    FragmentManager fragmentManager = getSupportFragmentManager();

    TextView textView;
    FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
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
    String qrValue = "";
    SharedPreferences prefs;
    int points;
    List<CheckBox> arrayList;
    List<Boolean> arrayListBool;

    // User data instance
    User mUser;
    Event event;
    Map map;
    QuizStart quiz;
    About about;



    public void onCreate(Bundle saveInstanceState){
        setTitle("FunStop");

        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_fun_stop_sub);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        Intent intent = getIntent();

        //TODO: connect user data to other data members
        //Initialize user object
        mUser = (User) intent.getSerializableExtra("user");
        // Initialize page objects
        event = new Event(fragmentManager);
        map = new Map();
        quiz = new QuizStart(fragmentManager, mUser, ref);
        //myPoint = new MyPoint(currentUser);
        //funStop = new FunStop(fragmentManager, currentUser, ref);
        about = new About();


        if (intent.getStringExtra("source").equals("QrCodeScanner")) {
            qrValue = intent.getStringExtra("qrValue");
        }

        prefs = getSharedPreferences("prefs", 0);
        station1 = findViewById(R.id.station1);
        station2 = findViewById(R.id.station2);
        station3 = findViewById(R.id.station3);
        station4 = findViewById(R.id.station4);
        station5 = findViewById(R.id.station5);
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

        station1B = prefs.getBoolean("station1B", false);
        station2B = prefs.getBoolean("station2B", false);
        station3B = prefs.getBoolean("station3B", false);
        station4B = prefs.getBoolean("station4B", false);
        station5B = prefs.getBoolean("station5B", false);
        station6B = prefs.getBoolean("station6B", false);
        station7B = prefs.getBoolean("station7B", false);
        station8B = prefs.getBoolean("station8B", false);
        station9B = prefs.getBoolean("station9B", false);
        station10B = prefs.getBoolean("station10B", false);
        station11B = prefs.getBoolean("station11B", false);
        station12B = prefs.getBoolean("station12B", false);
        KoreanB = prefs.getBoolean("KoreanB", false);
        taiwaneseB = prefs.getBoolean("taiwaneseB", false);
        chineseB = prefs.getBoolean("chineseB", false);
        vietnameseB = prefs.getBoolean("vietnameseB", false);
        salishSea1B = prefs.getBoolean("salishSea1B", false);
        salishSea2B = prefs.getBoolean("salishSea2B", false);
        loneWolf1B = prefs.getBoolean("loneWolf1B", false);
        loneWolf2B = prefs.getBoolean("loneWolf2B", false);
        redFawn1B = prefs.getBoolean("redFawn1B", false);
        redFawn2B = prefs.getBoolean("redFawn2B", false);
        protector1B = prefs.getBoolean("protector1B", false);
        protector2B = prefs.getBoolean("protector2B", false);
        ladyHaoB = prefs.getBoolean("ladyHaoB", false);

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
        points = prefs.getInt("point", 0);


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

    public void onPause() {
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

    public void onBackPressed() {
        Intent intent = new Intent(FunStopSub.this, MainActivity.class);
        startActivity(intent);
    }


    public void save() {
        SharedPreferences.Editor prefsEditor = prefs.edit();

        prefsEditor.putBoolean("KoreanB", KoreanB);
        prefsEditor.putBoolean("chineseB", chineseB);
        prefsEditor.putBoolean("taiwaneseB", taiwaneseB);
        prefsEditor.putBoolean("vietnameseB", vietnameseB);
        prefsEditor.putBoolean("ladyHaoB", ladyHaoB);
        prefsEditor.putBoolean("loneWolf1B", loneWolf1B);
        prefsEditor.putBoolean("loneWolf2B", loneWolf2B);
        prefsEditor.putBoolean("protector1B", protector1B);
        prefsEditor.putBoolean("protector2B", protector2B);
        prefsEditor.putBoolean("redFawn1B", redFawn1B);
        prefsEditor.putBoolean("redFawn2B", redFawn2B);
        prefsEditor.putBoolean("salishSea1B", salishSea1B);
        prefsEditor.putBoolean("salishSea2B", salishSea2B);
        prefsEditor.putBoolean("station1B", station1B);
        prefsEditor.putBoolean("station2B", station2B);
        prefsEditor.putBoolean("station3B", station3B);
        prefsEditor.putBoolean("station4B", station4B);
        prefsEditor.putBoolean("station5B", station5B);
        prefsEditor.putBoolean("station6B", station6B);
        prefsEditor.putBoolean("station7B", station7B);
        prefsEditor.putBoolean("station8B", station8B);
        prefsEditor.putBoolean("station9B", station9B);
        prefsEditor.putBoolean("station10B", station10B);
        prefsEditor.putBoolean("station11B", station11B);
        prefsEditor.putBoolean("station12B", station12B);
        prefsEditor.putInt("points", points);
        prefsEditor.apply();
        ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("point").setValue(points);
    }
    private void updatePoints(int point, String operation){
        if (operation.equals("Add")) {
            points = points + point;
            ref.child(currentUser.getUid()).child("point").setValue(points);
        }

    }
    public void setUp () {
        System.out.println("Station one status:" + station1B);
        int i;
        for (i = 0; i < arrayListBool.size(); i++) {
            arrayListBool.set(i, prefs.getBoolean("element" + i, false));
        }
        System.out.println("Station one status:" + station1B);
        points = prefs.getInt("point", 0);
    }


    private void checkQRCodeValue () {
        switch (qrValue) {
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
                station6B = true;
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



    @Override
    public boolean onNavigationItemSelected (MenuItem item){
        // Handle navigation view item clicks here.
        transaction = fragmentManager.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_event) {
//            transaction.replace(R.id.frameLayout, event).commitAllowingStateLoss();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_map) {

            //intent
            transaction.replace(R.id.frameLayout, map).commitAllowingStateLoss();
        } else if (id == R.id.nav_funstop) {
            Intent intent = new Intent(this, FunStop.class);
            startActivity(intent);

            //transaction.replace(R.id.frameLayout, funStop).commitAllowingStateLoss();
        } else if (id == R.id.nav_quiz) {
            transaction.replace(R.id.frameLayout, quiz).commitAllowingStateLoss();
        } else if (id == R.id.nav_point) {
            Intent intent = new Intent(this, MyPoint.class);
            intent.putExtra("source", "navbar");
            startActivity(intent);
            //  transaction.replace(R.id.frameLayout, myPoint).commitAllowingStateLoss();
        } else if (id == R.id.nav_about) {
            transaction.replace(R.id.frameLayout, about).commitAllowingStateLoss();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}