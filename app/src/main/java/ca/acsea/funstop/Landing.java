package ca.acsea.funstop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Landing extends AppCompatActivity {

    private Button btn_login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!= null){
            Intent intent = new Intent(Landing.this, MainActivity.class);
            startActivity(intent);
        }else {
            btn_login = findViewById(R.id.login_button);
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Landing.this, Login.class);
                    startActivity(intent);
                }
            });
        }
    }
}