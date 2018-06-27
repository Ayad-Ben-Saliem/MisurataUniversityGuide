package kse.edu.misuratauniversityguide;

import android.util.Log;

import java.util.List;

public class Question {

    private final CharSequence question;
    private final List<CharSequence> options;
    private final int answer;
    private int choice;
    private boolean submitted;

    public Question(CharSequence question, List<CharSequence> options, int answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public CharSequence getQuestion() {
        return question;
    }

    public List<CharSequence> getOptions() {
        return options;
    }

    public int getAnswer() {
        return answer;
    }

    public void submit(int choice){
        if(!submitted) {
            this.choice = choice;
            submitted = true;
            Log.i("QUESTION", "submit " + choice);
        }
    }

    public boolean isSubmitted(){
        return submitted;
    }

    public int getChoice() {
        return choice;
    }
}
