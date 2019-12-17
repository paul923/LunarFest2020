package ca.acsea.funstop.sponsorquiz;

import java.util.List;

public class Question {
    String imgRef;
    String mQuestion;
    String answer;
    List<String> optionList;

    public Question(String question, String answer, List<String> list){
        this.mQuestion = question;
        this.answer = answer;
        this.optionList = list;

    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getOptionList() {
        return optionList;
    }
}
