package ca.acsea.funstop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MyPoint extends Fragment {

    private DatabaseReference db;
    EditText test;
    public MyPoint() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        db= FirebaseDatabase.getInstance().getReference();

        read();
        View view =inflater.inflate(R.layout.fragment_my_point, container, false);
        return view;
    }

    private void read() {
        final String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.child("users").child(userId).child("point").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println("datasnapshot"+dataSnapshot.getValue());
                EditText test=getView().findViewById(R.id.test);
                test.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("working2");
            }
        });
    }
}
