package ca.acsea.funstop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Location extends AppCompatActivity {

    private static String TAG = "LogIn";
    private Button btn_location1;
    private Button btn_location2;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mUser = (User) getIntent().getSerializableExtra("user");
        System.out.println(mUser.getLot10());

        btn_location1 = findViewById(R.id.btn_location1);
        btn_location1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.getInstance().setData(0);
                Intent intent = new Intent(Location.this, MainActivity.class);
                intent.putExtra("user", mUser);
                startActivity(intent);
            }
        });

        btn_location2 = findViewById(R.id.btn_location2);
        btn_location2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.getInstance().setData(1);
                Intent intent = new Intent(Location.this, MainActivity.class);
                intent.putExtra("user", mUser);
                startActivity(intent);
            }
        });
    }
}