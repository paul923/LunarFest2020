package ca.acsea.funstop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class Location extends AppCompatActivity {

    private static String TAG = "LogIn";
    private Button btn_location1;
    private Button btn_location2;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private User mUser;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
//        getDataFromFirebase();


        setContentView(R.layout.activity_location);


        Gson gson = new Gson();
        sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String json = sharedPreferences.getString("userObject", "");
        mUser = gson.fromJson(json, User.class);
        System.out.println("what is mUser? on location 1"+mUser);
//
//        mUser = (User) getIntent().getSerializableExtra("user");
//        System.out.println("What is mUSer on location 2"+mUser);
        btn_location1 = findViewById(R.id.btn_location1);
        btn_location1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Globals.getInstance().setData(0);
                Intent intent = new Intent(Location.this, MainActivity.class);
//                intent.putExtra("user", mUser);
                startActivity(intent);
            }
        });

        btn_location2 = findViewById(R.id.btn_location2);
        btn_location2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.getInstance().setData(1);
                Intent intent = new Intent(Location.this, MainActivity.class);
//                intent.putExtra("user", mUser);
                startActivity(intent);
            }
        });
    }
}

