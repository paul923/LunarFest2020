package ca.acsea.funstop.sponsorquiz;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ca.acsea.funstop.R;
import ca.acsea.funstop.User;

import static android.content.Context.MODE_PRIVATE;

public class QuizStart extends Fragment {

    View view;
    Button startButton;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    FirebaseUser currentUser;
    DatabaseReference ref;
    long cutoff;
    private int index;
    private User mUser;
    private SharedPreferences sharedPreferences;


    public QuizStart(){
        //empty constructor
    }

    public QuizStart(FragmentManager fm, User user, DatabaseReference ref){
        this.fragmentManager = fm;
        this.mUser = user;
        this.ref = ref;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Gson gson = new Gson();
        sharedPreferences = getActivity().getSharedPreferences("prefs", MODE_PRIVATE);
        String json = sharedPreferences.getString("userObject", "");
        mUser = gson.fromJson(json, User.class);

        //Changes the actionbar's Title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#e6b773'>Daily quiz </font>"));

//        getIndex();
        view = inflater.inflate(R.layout.fragment_quiz_start, container, false);
        transaction = fragmentManager.beginTransaction();
        startButton = view.findViewById(R.id.quiz_start_button);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Data retrieve
                cutoff = mUser.getQuizCutoff();
                long currentTime = new Date().getTime();
//                //TODO: change 0 back to cutoff
                if(currentTime > cutoff) {
                    recordTime();
                    transaction.replace(R.id.frameLayout, new Quiz(fragmentManager,mUser, ref)).commit();
                    save();

                }else {
                    long timeLeft = cutoff - new Date().getTime();
                    String message = String.format("Try again in %d hr %d min %d sec",
                            TimeUnit.MILLISECONDS.toHours(timeLeft),
                            TimeUnit.MILLISECONDS.toMinutes(timeLeft) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeLeft)),
                            TimeUnit.MILLISECONDS.toSeconds(timeLeft) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeLeft)));
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    public void recordTime(){
        long currentTime = new Date().getTime();

        Date nextdate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(nextdate);
        c.add(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        nextdate = c.getTime();

        long nextdateTime = nextdate.getTime();
//        ref.child("users").child(currentUser.getUid()).child("quiz").child("cutoff").setValue(nextdateTime);
        mUser.setQuizCutoff(nextdateTime);
    }

    private void getCutoff() {

        ref.child("users").child(currentUser.getUid()).child("quiz").child("cutoff").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot != null){
                    cutoff = (long) dataSnapshot.getValue();
                } else {
                    cutoff = 0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("on cancelled");
            }
        });
    }


    public void save() {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mUser);
        prefsEditor.putString("userObject", json);
        prefsEditor.commit();
    }


    public void getIndex(){
        ref.child("users").child(currentUser.getUid()).child("quiz").child("questionNo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null)
                    index = Long.valueOf((long)dataSnapshot.getValue()).intValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("on cancelled");
            }
        });
    }

}
