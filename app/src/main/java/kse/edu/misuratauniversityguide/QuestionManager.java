package kse.edu.misuratauniversityguide;

import android.support.annotation.NonNull;

import java.util.Random;
import java.util.Vector;

/**
 * Created by ayad on 14/03/18.
 */

public class QuestionManager {

    public enum Type{
        YES_NO,
        MCQ,
        ESSAY
    }

    private Vector<Type> questionTypes;

    public void addQuestionType(Type type){
        if(!questionTypes.contains(type))
            questionTypes.add(type);
    }

    public Question getQuestion(){
        int i = new Random().nextInt() % Type.values().length;
        Type type = Type.values()[i];

        return getQuestion(type);
    }


    public Question getQuestion(Type type){
        if(type == Type.YES_NO)
            return new YesNoQuestion("Demo Question");
        else if (type == Type.MCQ)
            return new MCQuestion("Demo Question");
        else
            return new YesNoQuestion("Demo Question");
    }

    private class EmptyQuestionException extends RuntimeException {}

    private abstract class Question {
        private final String question;
        private final Type type;

        Question(@NonNull String question, Type type){
            if(type == null)
                throw new NullPointerException();
            if(question.isEmpty())
                throw new QuestionManager.EmptyQuestionException();
            this.question = question;
            this.type = type;
        }

        public String getQuestion() {
            return question;
        }

        public Type getType() {
            return type;
        }
    }

    private class YesNoQuestion extends Question{
        YesNoQuestion(String question){
            super(question, Type.YES_NO);
        }
    }

    private class MCQuestion extends Question{
        private Vector<String> choice;

        MCQuestion(String question){
            super(question, Type.MCQ);
        }

        public Vector<String> getChoice() {
            return choice;
        }
    }

    private class EssayQuestion extends Question{
        EssayQuestion(String question){
            super(question, Type.ESSAY);
        }
    }
}
