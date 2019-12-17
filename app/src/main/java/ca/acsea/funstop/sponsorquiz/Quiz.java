package ca.acsea.funstop.sponsorquiz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ca.acsea.funstop.R;


public class Quiz extends Fragment {

    View view;
    static int index = 0;

    public Quiz() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quiz, container, false);

        List<Question> qList = createQuestions();


        //Collections.shuffle(optionlist);

        //TODO: proceeds to the next question after clicking on button
        TextView questionBox = view.findViewById(R.id.questionBox);
        Question mQuestion = qList.get(index);
        questionBox.setText(mQuestion.getQuestion());
        generateOptions(mQuestion.getOptionList(), mQuestion.getAnswer());




        // Inflate the layout for this fragment
        return view;
    }

    public void generateOptions(List<String> list, final String answer){
        for(int i = 0; i < list.size(); i++){
            final Button btn = new Button(getActivity());
            btn.setId(i);
            btn.setText(list.get(i));
            LinearLayout mLayout = (LinearLayout) view.findViewById(R.id.optionBox);
            btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if (btn.getText().equals(answer)){
                        Toast.makeText(getContext(), "correct", Toast.LENGTH_SHORT).show();
                        index++;
                    }
                    else
                        Toast.makeText(getContext(),"WRONG!!!!",Toast.LENGTH_SHORT).show();
                }
            });
            mLayout.addView(btn);
        }
    }

    public ArrayList<Question> createQuestions(){
        String question1 = "What is this question 1?";
        List<String> answer1 = Arrays.asList("option1", "option2", "batman", "option4");

        String question2 = "What is this question 2?";
        List<String> answer2 = Arrays.asList("option1", "option2", "spiderman", "option4");

        String question3 = "What is this question 3?";
        List<String> answer3 = Arrays.asList("superman", "option2", "option3", "option4");

        String question4 = "What is this question 4?";
        List<String> answer4 = Arrays.asList("option1", "ironman", "option3", "option4");

        String question5 = "What is this question 5?";
        List<String> answer5 = Arrays.asList("option1", "option2", "option3", "option4");


        Question mQuestion1 = new Question(question1, answer1.get(2), answer1);
        Question mQuestion2 = new Question(question2, answer1.get(2), answer2);
        Question mQuestion3 = new Question(question3, answer1.get(0), answer3);
        Question mQuestion4 = new Question(question4, answer1.get(1), answer4);
        Question mQuestion5 = new Question(question5, answer1.get(1), answer5);

        ArrayList<Question> list = new ArrayList<>();
        list.add(mQuestion1);
        list.add(mQuestion2);
        list.add(mQuestion3);
        list.add(mQuestion4);
        list.add(mQuestion5);

        return list;
    }
}
