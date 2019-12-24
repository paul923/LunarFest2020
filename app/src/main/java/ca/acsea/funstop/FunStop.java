package ca.acsea.funstop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
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

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ca.acsea.funstop.event.Event;
import ca.acsea.funstop.sponsorquiz.QuizStart;


public class FunStop extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button btnStart;
    FragmentTransaction transaction;
    FragmentManager fragmentManager = getSupportFragmentManager();

    FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference ref= FirebaseDatabase.getInstance().getReference();

    // Initialize page objects
    Event event = new Event(fragmentManager);
    Map map = new Map();
    QuizStart quiz = new QuizStart(fragmentManager, currentUser, ref);
    About about = new About(fragmentManager);

    public void onCreate(Bundle saveInstanceState){
        setTitle("FunStop");
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_fun_stop);

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

        btnStart = findViewById(R.id.startbtn);
        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FunStop.this, FunStopSub.class);
                intent.putExtra("source", "FunStop");
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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