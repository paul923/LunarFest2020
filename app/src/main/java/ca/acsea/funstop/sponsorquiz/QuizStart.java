package ca.acsea.funstop.sponsorquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import ca.acsea.funstop.R;

public class QuizStart extends Fragment {

    View view;
    Button startButton;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    FirebaseUser currentUser;
    DatabaseReference ref;
    long cutoff;


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
        view = inflater.inflate(R.layout.fragment_quiz_start, container, false);
        transaction = fragmentManager.beginTransaction();
        startButton = view.findViewById(R.id.quiz_start_button);

        // Data retrieve
        getCutoff();
        System.out.println(cutoff);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long currentTime = new Date().getTime();
                //TODO: change 0 back to cutoff
                if(currentTime > 0) {
                    recordTime();
                    transaction.replace(R.id.frameLayout, new Quiz(fragmentManager,currentUser, ref)).commit();
                }else {
                    Toast.makeText(getContext(), "Try again tmrw", Toast.LENGTH_SHORT).show();
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
        ref.child("users").child(currentUser.getUid()).child("quiz").child("timeStamp").setValue(currentTime);
        ref.child("users").child(currentUser.getUid()).child("quiz").child("cutoff").setValue(nextdateTime);
    }

    private void getCutoff() {
        ref.child("users").child(currentUser.getUid()).child("quiz").child("cutoff").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cutoff = (long) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("on cancelled");
            }
        });
    }

}
