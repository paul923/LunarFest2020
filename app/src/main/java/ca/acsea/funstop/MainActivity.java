package ca.acsea.funstop;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    private Event event;
    private Map map;
    private FunStop funStop;
    private Quiz quiz;
    private MyPoint myPoint;
    private About about;
    private QrCodeScanner qrCodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        //Actionbar hide
        getSupportActionBar().hide();


        //Return the FragmentManager for interacting with fragments associated with this activity.
        fragmentManager = getSupportFragmentManager();

        // Initialize page objects
        event = new Event(fragmentManager);
        map = new Map();
        funStop = new FunStop(fragmentManager);
        quiz = new Quiz();
        myPoint = new MyPoint();
        qrCodeScanner=new QrCodeScanner();
        about = new About();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, event).commitAllowingStateLoss();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        transaction = fragmentManager.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_event) {
            transaction.replace(R.id.frameLayout, event).commitAllowingStateLoss();
        } else if (id == R.id.nav_map) {
            transaction.replace(R.id.frameLayout, map).commitAllowingStateLoss();
        } else if (id == R.id.nav_funstop) {
            transaction.replace(R.id.frameLayout, funStop).commitAllowingStateLoss();
        } else if (id == R.id.nav_quiz) {
            transaction.replace(R.id.frameLayout, quiz).commitAllowingStateLoss();
        } else if (id == R.id.nav_point) {
            transaction.replace(R.id.frameLayout, myPoint).commitAllowingStateLoss();
        } else if (id == R.id.nav_about) {
            transaction.replace(R.id.frameLayout, about).commitAllowingStateLoss();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();

    }
}
