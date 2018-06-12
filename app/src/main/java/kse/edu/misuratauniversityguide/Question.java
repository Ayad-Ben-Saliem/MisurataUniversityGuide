package kse.edu.misuratauniversityguide;

import java.util.List;

public class Question {

    private final CharSequence question;
    private final List<CharSequence> options;
    private final int answer;

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
}
