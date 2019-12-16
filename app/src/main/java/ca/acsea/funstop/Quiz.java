package ca.acsea.funstop;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Quiz extends Fragment {

    View view;

    public Quiz() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quiz, container, false);

        String question = "This is a test question";
        String[] data = {"option1", "option2", "option3", "option4"};
        List<String> options = Arrays.asList(data);
        TextView questionBox = view.findViewById(R.id.questionBox);

        // Inflate the layout for this fragment
        return view;
    }

//    public ArrayList<Question> createQuestions(){
//
//    }
}
