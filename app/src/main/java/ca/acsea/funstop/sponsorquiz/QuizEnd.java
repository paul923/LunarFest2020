package ca.acsea.funstop.sponsorquiz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.acsea.funstop.R;

public class QuizEnd extends Fragment {
    View view;

    public QuizEnd(){
        //Empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Changes the actionbar's Title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Quiz");


        view = inflater.inflate(R.layout.fragment_quiz_end, container, false);

        return view;
    }
}