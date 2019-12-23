package ca.acsea.funstop.sponsorquiz;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.acsea.funstop.R;


public class Quiz extends Fragment {


    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Question currentQuestion;
    private List<Question> qList;
    private View view;
    private TextView questionBox;
    private Button buttonA, buttonB, buttonC, buttonD;
    private int index;
    private DatabaseReference ref;
    private FirebaseUser currentUser;
    private long point;

    public Quiz() {
        // Required empty public constructor
    }

    public Quiz(FragmentManager fm, FirebaseUser user, DatabaseReference ref, int i){
        this.fragmentManager = fm;
        this.currentUser = user;
        this.ref = ref;
        this.index = i;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Changes the actionbar's Title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Quiz");
        view = inflater.inflate(R.layout.fragment_quiz, container, false);
        getPoint();

        transaction = fragmentManager.beginTransaction();

        //TODO: proceeds to the next question after clicking on button
        questionBox = (TextView) view.findViewById(R.id.questionBox);
        buttonA = (Button) view.findViewById(R.id.buttonA);
        buttonB = (Button) view.findViewById(R.id.buttonB);
        buttonC = (Button) view.findViewById(R.id.buttonC);
        buttonD = (Button) view.findViewById(R.id.buttonD);

        init();


        // Inflate the layout for this fragment
        return view;
    }

    public void init(){
        qList = createQuestions();
        //Collections.shuffle(qList);
        currentQuestion = qList.get(index);
        questionBox.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptionList().get(0));
        buttonB.setText(currentQuestion.getOptionList().get(1));
        buttonC.setText(currentQuestion.getOptionList().get(2));
        buttonD.setText(currentQuestion.getOptionList().get(3));
        handleClick();
    }

    public void handleClick(){
        for(final Button btn: Arrays.asList(buttonA, buttonB, buttonC, buttonD)) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    enableButton(false);
                    if (btn.getText().equals(currentQuestion.getAnswer())) {
                        Toast.makeText(getContext(), "correct", Toast.LENGTH_SHORT).show();
                        colorButton(btn, Color.GREEN);
                        addPoints(10);
                    } else {
                        Toast.makeText(getContext(), "WRONG!!!!", Toast.LENGTH_SHORT).show();
                        colorButton(btn, Color.RED);
                        addPoints(5);
                    }
                }
            });
        }
    }

    public void addPoints(int value){
        point += value;
        ref.child("users").child(currentUser.getUid()).child("point").setValue(point);
        //Question number decision
        ++index;
        if(index < qList.size() - 1)
            ref.child("users").child(currentUser.getUid()).child("quiz").child("questionNo").setValue(++index);
        else
            index = 0;
    }

    /**
     * Enabling or disabling all the buttons depends the value
     * @param value true will enable, false will disable
     */
    public void enableButton(boolean value){
        for(Button btn: Arrays.asList(buttonA, buttonB, buttonC, buttonD)){
            btn.setEnabled(value);
        }
    }

    /**
     * Changes the color of the button with correct result
     * @param btn
     */
    public void colorButton(final Button btn, int color){
        btn.setBackgroundColor(color);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btn.setBackgroundColor(Color.WHITE);
                enableButton(true);
//                nextQuestion();
                transaction.replace(R.id.frameLayout, new QuizEnd()).commit();
            }
        }, 2000);
    }

    public ArrayList<Question> createQuestions(){
        String question1 = "What is this question 1?";
        List<String> answer1 = Arrays.asList("option1", "option2", "answer", "option4");

        String question2 = "What is this question 2. pick answer?";
        List<String> answer2 = Arrays.asList("option1", "option2", "answer", "option4");

        String question3 = "What is this question 3?";
        List<String> answer3 = Arrays.asList("answer", "option2", "option3", "option4");

        String question4 = "What is this question 4?";
        List<String> answer4 = Arrays.asList("option1", "answer", "option3", "option4");

        String question5 = "What is this question 5?";
        List<String> answer5 = Arrays.asList("option1", "answer", "option3", "option4");


        Question mQuestion1 = new Question(question1, answer1.get(2), answer1);
        Question mQuestion2 = new Question(question2, answer2.get(2), answer2);
        Question mQuestion3 = new Question(question3, answer3.get(0), answer3);
        Question mQuestion4 = new Question(question4, answer4.get(1), answer4);
        Question mQuestion5 = new Question(question5, answer5.get(1), answer5);

        ArrayList<Question> list = new ArrayList<>();
        list.add(mQuestion1);
        list.add(mQuestion2);
        list.add(mQuestion3);
        list.add(mQuestion4);
        list.add(mQuestion5);

        return list;
    }

    public void getPoint(){
        ref.child("users").child(currentUser.getUid()).child("point").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                point = (long) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("on cancelled");
            }
        });
    }




    public void nextQuestion(){
        try{
            if(index < qList.size()-1){
                index++;
                currentQuestion = qList.get(index);
                questionBox.setText(currentQuestion.getQuestion());
                buttonA.setText(currentQuestion.getOptionList().get(0));
                buttonB.setText(currentQuestion.getOptionList().get(1));
                buttonC.setText(currentQuestion.getOptionList().get(2));
                buttonD.setText(currentQuestion.getOptionList().get(3));
            }else{
                transaction.replace(R.id.frameLayout, new QuizEnd()).commit();
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }
    }


}
