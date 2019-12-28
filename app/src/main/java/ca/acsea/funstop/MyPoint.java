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
import androidx.core.view.GravityCompat;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.gson.Gson;

import org.w3c.dom.Text;
import java.io.Serializable;

import ca.acsea.funstop.event.Event;
import ca.acsea.funstop.sponsorquiz.QuizStart;
public class MyPoint extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String pointKey = "points";
    public static final String sharePreKey = "prefs";
    FragmentTransaction transaction;
    FragmentManager fragmentManager = getSupportFragmentManager();

    TextView test;

    Button redeembtn;
    int points;
    boolean joinDraw;
    String qrValue = "";
    SharedPreferences sharedPreferences;
    Button transactionBtn;

    String historyMessage;

    //User Data Instance
    private User mUser;
    Event event;
    Map map;
    QuizStart quiz;
    About about;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private AlertDialog.Builder alertDialog;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();


    public void onCreate(Bundle saveInstanceState) {
        setTitle("My Point");
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_my_point);

        Intent intent = getIntent();
        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userObject", "");
        String testing = sharedPreferences.getString("test", "");
        historyMessage = sharedPreferences.getString("history", "");
        mUser = gson.fromJson(json, User.class);

        System.out.println("test : " + testing);

        System.out.println(intent.getStringExtra("source"));

        if (intent.getStringExtra("source").equals("QrCodeScanner")) {
            qrValue = intent.getStringExtra("qrValue");
        }


        event = new Event(fragmentManager);
        map = new Map();
        quiz = new QuizStart(fragmentManager, mUser, db);
        //myPoint = new MyPoint(currentUser);
        //funStop = new FunStop(fragmentManager, currentUser, ref);
        about = new About(fragmentManager);






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



        //Point history button
        transactionBtn = (Button) findViewById(R.id.point_transaction);
        onPressTransaction();

        //Initialize AlertDialog
        alertDialog = new AlertDialog.Builder(MyPoint.this);
        alertDialog.setTitle("Transaction History");




        test = findViewById(R.id.test);
        getPoints();

        checkQrValue();
        checkPoint();
        redeembtn = findViewById(R.id.redeembtn);
        redeembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyPoint.this, QrCodeScanner.class);
                i.putExtra("previous", "MyPoints");
                Bundle bundle = new Bundle();
                startActivity(i);
            }
        });
    }


    /**
     * Transaction button pressed event handler
     */
    public void onPressTransaction(){
        transactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.setMessage(historyMessage);
                alertDialog.setTitle("Transaction History")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();
            }
        });
    }



    public void onPause() {
        super.onPause();
//        SharedPreferences.Editor prefEditor = this.getSharedPreferences("prefs",0).edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(mUser);
//        prefEditor.putString("userObject", json);
//        prefEditor.apply();
    }



    private void checkPoint() {
        if (points >= 150 && !joinDraw) {
            new AlertDialog.Builder(MyPoint.this).setTitle("Congrats")
                    .setMessage("You have reached 150 points. Would you like to join the draw for a $200 Visa Gift Card? It'll" +
                            "cost 150 points.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            addToPool();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Cancel
                        }
                    })
                    .show();
        }
    }


    public void onBackPressed() {
        Intent intent = new Intent(MyPoint.this, MainActivity.class);
        startActivity(intent);
    }


    private void savePoint(){
        SharedPreferences.Editor prefEditor = this.getSharedPreferences("prefs",0).edit();
        Gson gson = new Gson();
        String json = gson.toJson(mUser);
        prefEditor.putString("userObject", json);
        prefEditor.putString("history", historyMessage);
        prefEditor.putInt(pointKey, points);
        prefEditor.putBoolean("joinDraw", joinDraw);
        prefEditor.apply();
        System.out.println("in save point addinh");
//        db.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("point").setValue(points);
//        db.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("joinDraw").setValue(joinDraw);
    }


    private void getPoints() {
        SharedPreferences pref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        points = (int) mUser.getPoint();
        System.out.println("What si points on myPoint" + points);
        System.out.println("Current points  in get points before adding: " + points);
        test.setText(String.valueOf(mUser.getPoint()));
        joinDraw = pref.getBoolean("joinDraw", false);
    }

    public void checkQrValue() {
        getPoints();
        switch (qrValue) {
            case "R_200PT":
                modifyPoints(200, "Reduce");
                break;
            case "R_150PT":
                modifyPoints(150, "Reduce");
                break;
            case "R_100PT":
                modifyPoints(100, "Reduce");
                break;
            case "R_50PT":
                modifyPoints(50, "Reduce");
                break;
            case "R_20PT":
                modifyPoints(20, "Reduce");
                break;
            case "R_10PT":
                System.out.println("working");
                modifyPoints(10, "Reduce");
                break;
            case "A_50PT":
                modifyPoints(50, "Add");
                break;
            case "A_40PT":
                modifyPoints(40, "Add");
                break;
            case "A_10PT":
                modifyPoints(10, "Add");
                break;
            case "A_5PT":
                modifyPoints(5, "Add");
                break;
        }
    }

    private void modifyPoints(int point, String operation) {
        if (operation.equalsIgnoreCase("Add")) {
            mUser.setPoint(mUser.getPoint() + point);
            test.setText(String.valueOf(mUser.getPoint()));
            historyMessage.concat("+ " + point + "\n");
        } else if (operation.equalsIgnoreCase("Reduce")) {
//            points = points - point;
            if(mUser.getPoint()>=point) {
                mUser.setPoint(mUser.getPoint() - point);
                test.setText(String.valueOf(mUser.getPoint()));
                historyMessage.concat("- " + point + "\n");
            }else {
                Toast.makeText(this,"Not enough point!",Toast.LENGTH_SHORT).show();
            }

        }
        savePoint();
    }


    private void addToPool() {
        db.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("joinDraw").setValue("Yes");
        joinDraw = true;
        modifyPoints(150, "Reduce");
        db.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("point").setValue(points);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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