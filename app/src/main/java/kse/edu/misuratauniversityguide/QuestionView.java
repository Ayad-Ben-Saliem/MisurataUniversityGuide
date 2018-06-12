package kse.edu.misuratauniversityguide;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class QuestionView extends LinearLayout {

    class NoAnswerCheckedException extends RuntimeException {};

    private TextView question;
    private RadioGroup answers;

    private int answer;

    public QuestionView(Context context){
        this(context, null, (String)null);
    }

    public QuestionView(Context context, String question, String ... answers){
        super(context);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setPaddingRelative(16, 16, 16, 16);

        setQuestion(question);
        setAnswers(answers);
        addView(this.question);
        addView(this.answers);
    }

    public void setQuestion(CharSequence question) {
        this.question = new TextView(getContext());
        this.question.setText(question);
        this.question.setGravity(Gravity.CENTER);
    }

    public void setAnswers(String ... answers) {
        this.answers = new RadioGroup(getContext());
        for (String answer : answers) {
            RadioButton rb = new RadioButton(getContext());
            rb.setText(answer);
            this.answers.addView(rb);
        }
        this.answers.setPaddingRelative(64, 0, 0, 0);
        answer = new Random().nextInt(this.answers.getChildCount()) + 1;
    }

    public boolean getAnswer(int answer){
        return this.answer == answer;
    }

    public boolean getAnswer() throws NoAnswerCheckedException  {
        return getAnswer(this.answers.getCheckedRadioButtonId());
    }
}
