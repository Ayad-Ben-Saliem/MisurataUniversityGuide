package kse.edu.misuratauniversityguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;


public class QuickTestFragment extends Fragment {

    TextView questionTV;
    RadioButton choiceRB1;
    RadioButton choiceRB2;
    RadioButton choiceRB3;


    public QuickTestFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
