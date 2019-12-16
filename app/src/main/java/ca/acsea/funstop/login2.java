package ca.acsea.funstop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login2 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

//        mAuth = FirebaseAuth.getInstance();
//        String uid = mAuth.getCurrentUser().getUid();
    }
}

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }
////    mAuth.createUserWithEmailAndPassword(email, password)
////            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
////        @Override
////        public void onComplete(@NonNull Task<AuthResult> task) {
////            if (task.isSuccessful()) {
////                // Sign in success, update UI with the signed-in user's information
////                Log.d(TAG, "createUserWithEmail:success");
////                FirebaseUser user = mAuth.getCurrentUser();
////                updateUI(user);
////            } else {
////                // If sign in fails, display a message to the user.
////                Log.w(TAG, "createUserWithEmail:failure", task.getException());
////                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
////                        Toast.LENGTH_SHORT).show();
////                updateUI(null);
////            }
////
////            // ...
////        }
////    });
//
//
//
//}

//    public void  updateUI(FirebaseUser account){
//        if(account != null){
//            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
//            startActivity(new Intent(this,AnotherActivity.class));
//        }else {
//            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
//        }
//    }
