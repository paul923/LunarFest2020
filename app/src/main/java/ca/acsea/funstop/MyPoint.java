package ca.acsea.funstop;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;


public class MyPoint extends Fragment {
    public static final String NODE_USERS = "users";

    private DatabaseReference databaseReference;
    private String points;
    private FirebaseUser user;

    public MyPoint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_my_point, container, false);

//        //get reference to the list of users in database
//        databaseReference = FirebaseDatabase.getInstance().getReference(NODE_USERS);
//
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //get current user
//                user = FirebaseAuth.getInstance().getCurrentUser();
//                //get points of the user using user id
//                String value = dataSnapshot.child(NODE_USERS).child(user.getUid()).child("MyPoints").getValue(String.class);
//                //get the text view to set the points
//                TextView points = (TextView) getView().findViewById(R.id.points);
//                //set the number of points to text view
//                points.setText(value);
//                //Log.d("MyPoints", "Value is: " + value); //use for debug
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        return view;
    }
}
