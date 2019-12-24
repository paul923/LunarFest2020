package ca.acsea.funstop.sponsorquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ca.acsea.funstop.R;

public class QuizStart extends Fragment {

    View view;
    Button startButton;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    FirebaseUser currentUser;
    DatabaseReference ref;
    long cutoff;
    private int index;


    public QuizStart(){
        //empty constructor
    }

    public QuizStart(FragmentManager fm, FirebaseUser user, DatabaseReference ref){
        this.fragmentManager = fm;
        this.currentUser = user;
        this.ref = ref;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Changes the actionbar's Title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Quiz");

        getIndex();
        view = inflater.inflate(R.layout.fragment_quiz_start, container, false);
        transaction = fragmentManager.beginTransaction();
        startButton = view.findViewById(R.id.quiz_start_button);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Data retrieve
                getCutoff();
                long currentTime = new Date().getTime();
                //TODO: change 0 back to cutoff
                if(currentTime > cutoff) {
                    recordTime();
                    transaction.replace(R.id.frameLayout, new Quiz(fragmentManager,currentUser, ref, index)).commit();
                }else {
                    long timeLeft = cutoff - new Date().getTime();
                    String message = String.format("Try again in %d hr %d min %d sec",
                            TimeUnit.MILLISECONDS.toHours(timeLeft),
                            TimeUnit.MILLISECONDS.toMinutes(timeLeft),
                            TimeUnit.MILLISECONDS.toSeconds(timeLeft));
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
        //c.add(Calendar.DATE, 1);
        c.add(Calendar.MINUTE, 1);
//        c.set(Calendar.HOUR_OF_DAY,0);
//        c.set(Calendar.MINUTE,0);
//        c.set(Calendar.SECOND,0);
//        c.set(Calendar.MILLISECOND,0);
        nextdate = c.getTime();

        long nextdateTime = nextdate.getTime();
        ref.child("users").child(currentUser.getUid()).child("quiz").child("timeStamp").setValue(currentTime);
        ref.child("users").child(currentUser.getUid()).child("quiz").child("cutoff").setValue(nextdateTime);
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