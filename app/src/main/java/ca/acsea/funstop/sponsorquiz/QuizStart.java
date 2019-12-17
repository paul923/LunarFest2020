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

import ca.acsea.funstop.R;

public class QuizStart extends Fragment {

    View view;
    Button startButton;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    public QuizStart(){
        //empty constructor
    }

    public QuizStart(FragmentManager fm){
        this.fragmentManager = fm;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quiz_start, container, false);

        transaction = fragmentManager.beginTransaction();

        startButton = view.findViewById(R.id.quiz_start_button);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                transaction.replace(R.id.frameLayout, new Quiz(fragmentManager)).commit();
            }
        });

        return view;
    }
}
