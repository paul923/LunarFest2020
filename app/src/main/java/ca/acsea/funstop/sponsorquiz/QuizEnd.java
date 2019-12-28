package ca.acsea.funstop.sponsorquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import com.google.gson.Gson;

import ca.acsea.funstop.MainActivity;
import ca.acsea.funstop.MyPoint;
import ca.acsea.funstop.R;
import ca.acsea.funstop.User;

import static android.content.Context.MODE_PRIVATE;

public class QuizEnd extends Fragment {
    View view;
    Button mButton;
    SharedPreferences prefs;
    User mUser;
    int points;

    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    public QuizEnd(){
        //Empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Changes the actionbar's Title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Quiz");
        prefs = getActivity().getSharedPreferences("prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("userObject", "");
        mUser = gson.fromJson(json, User.class);
        view = inflater.inflate(R.layout.fragment_quiz_end, container, false);

        mButton = (Button) view.findViewById(R.id.endButton);
        System.out.println("points in quizend " +mUser.getPoint());
        buttonClick();

        return view;
    }

    public void buttonClick(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                SharedPreferences.Editor prefsEditor = prefs.edit();
//                prefsEditor.putInt("points", points);
//                Gson gson = new Gson();
//                String json = gson.toJson(mUser);
//                prefsEditor.putString("userObject", json);
//                prefsEditor.commit();
//                getActivity().recreate();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}