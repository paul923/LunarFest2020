package ca.acsea.funstop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;


public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    public static final String NODE_USERS = "users";
    private static String TAG = "LogIn";
    private static final int RC_SIGN_IN = 9001;
    private EditText emailInput;
    private EditText passwordInput;
    private Button submitBtn;
    private SignInButton googleSignInBtn;
    private GoogleApiClient googleApiClient;
    private GoogleSignInClient mGoogleSignInClient;
    private String email;
    private String password;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //set up google sign in options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        mAuth = FirebaseAuth.getInstance();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);/////
        mDatabase = FirebaseDatabase.getInstance();


        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        submitBtn = findViewById(R.id.submit);

        googleSignInBtn = findViewById(R.id.google_sign_in_button);
        googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();

                if(TextUtils.isEmpty(email)) {
                    emailInput.setError("Please enter your email");
                    return;
                } else if(TextUtils.isEmpty(password)){
                    passwordInput.setError("Please enter your password");
                    return;
                }
                createUser(email,password);
            }
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        //Authentication with google
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        final AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithCredential:success");

                            DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference();
                            dbUsers.child(mAuth.getCurrentUser().getUid()).child("email").setValue(mAuth.getCurrentUser().getEmail());

                            //move to next activity
                            Toast.makeText(Login.this, "Sign In Success", Toast.LENGTH_LONG).show();
                            Intent submit_intent = new Intent(Login.this, MainActivity.class);
                            startActivity(submit_intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Login.this, "Sign In Failed", Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }

    public void googleSignIn(){
        //Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

        System.out.println("\n googleLogin \n");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("\n OnActivityResult\n");

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                Log.d(TAG, task.getResult(ApiException.class).toString());
//                // Google Sign In was successful, authenticate with Firebase
//                //after user connect their account  it doesnt sign user in
//                //user is signed out after exiting the app
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//
//                firebaseAuthWithGoogle(account);
//
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//                // Log.w(TAG, "Google sign in failed", e);
//                Toast.makeText(Login.this, "Google sign in failed, please use another method", Toast.LENGTH_LONG).show();
//                // ...
//            }
//        }
        if (requestCode == RC_SIGN_IN){
            System.out.println("\n requestCode arrived\n");
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            System.out.println("\n 구글 사인인 결과: \n" + result + "/n");
            System.out.println("\n 구글 사인인 결과(boolben): \n" + result.isSuccess() + "/n");
            System.out.println(result.getStatus());

            if(result.isSuccess()){
                System.out.println("\n result succeeds\n");
                GoogleSignInAccount account = result.getSignInAccount();
                resultLogin(account);
            }
        }
    }

    private void resultLogin(final GoogleSignInAccount account) {
        System.out.println("\n resultLogin \n");
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    System.out.println("\n resultLogin : success\n");
                    Toast.makeText(Login.this, "google login succeeds", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "google login fails", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void createUser(final String email, final String password){
        // create user with email and password
        final User user = new User(email);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                System.out.println("createUser method starts");
                if(task.isSuccessful()){
                    System.out.println("create user / task is successful");
      new AlertDialog.Builder(Login.this).setTitle("Create New Account");

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    FirebaseUser mUser = mAuth.getCurrentUser();
                    ref.child("users").child(mUser.getUid()).setValue(user);
                    new AlertDialog.Builder(Login.this).setTitle("Create New Account")

                            .setMessage("There is no such account. Do you want to create new account with the input id and password?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // OK
                                    Toast.makeText(Login.this, "New account is created.", Toast.LENGTH_SHORT).show();
                                    InitValues(); // init values
                                    signIn(email, password);
                                    finish();
                                }})
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // Cancel
                                    signIn(email,password);
                                    Toast.makeText(Login.this, "It is canceled.", Toast.LENGTH_SHORT).show();
                                }})
                            .show();

                }else{
                    System.out.println("create user / task is failed");
                    signIn(email, password);
                    //Toast.makeText(Login.this, "CreateUser method is failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void signIn(String email, String password){
        //sign in
        System.out.println("\n start sign in \n");
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // update user email in database
                    System.out.println("sign-in task is successful");

                    DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference(NODE_USERS);
                    //InitValues();

                    Intent submit_intent = new Intent(Login.this, MainActivity.class);
                    startActivity(submit_intent);

                }else{
                    Log.println(Log.VERBOSE,"Login Error", task.getException().getMessage());
                    Toast.makeText(Login.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void InitValues() {
        System.out.println("initing values");
        DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference(NODE_USERS);
        String currentUser = mAuth.getCurrentUser().getUid();
        dbUsers.child(currentUser).child("email").setValue(mAuth.getCurrentUser().getEmail());
        dbUsers.child(currentUser).child("point").setValue(0); //initialize point
        dbUsers.child(currentUser).child("quiz").child("cutoff").setValue(0);
        dbUsers.child(currentUser).child("QR").child("korean").setValue(false);
        dbUsers.child(currentUser).child("QR").child("chinese").setValue(false);
        dbUsers.child(currentUser).child("QR").child("ladyHao").setValue(false);
        dbUsers.child(currentUser).child("QR").child("loneWolf1").setValue(false);
        dbUsers.child(currentUser).child("QR").child("loneWolf2").setValue(false);
        dbUsers.child(currentUser).child("QR").child("protector1").setValue(false);
        dbUsers.child(currentUser).child("QR").child("protector2").setValue(false);
        dbUsers.child(currentUser).child("QR").child("redFawn1").setValue(false);
        dbUsers.child(currentUser).child("QR").child("redFawn2").setValue(false);
        dbUsers.child(currentUser).child("QR").child("salishSea1").setValue(false);
        dbUsers.child(currentUser).child("QR").child("salishSea2").setValue(false);
        dbUsers.child(currentUser).child("QR").child("taiwanese").setValue(false);
        dbUsers.child(currentUser).child("QR").child("vietnamese").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station1").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station2").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station3").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station4").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station5").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station6").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station7").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station8").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station9").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station10").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station11").setValue(false);
        dbUsers.child(currentUser).child("QR").child("station12").setValue(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account != null || currentUser!= null){

            // move to next activity
//            Intent i = new Intent(Login.this, MainActivity.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(i);

        }else{
            Toast.makeText(Login.this, "Please Sign In", Toast.LENGTH_LONG).show();
        }
    }


}