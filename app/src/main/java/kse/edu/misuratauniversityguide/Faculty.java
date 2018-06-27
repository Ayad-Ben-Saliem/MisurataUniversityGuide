package kse.edu.misuratauniversityguide;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Faculty extends BaseActivity {

    private Faculties faculty;

    ImageButton button;
    LinearLayout rootView;

    private ImageButton aboutBtn;
    private ImageButton departmentBtn;
    private ImageButton locationBtn;
    private ImageButton resultsBtn;
    private ImageButton facebookBtn;
    private ImageButton askBtn;
    private ImageButton testBtn;

    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_layout);

        faculty = (Faculties) getIntent().getExtras().get(Common.FACULTY);

        rootView = findViewById(R.id.faculty_layout);
        aboutBtn = findViewById(R.id.aboutBtn);
        departmentBtn = findViewById(R.id.departmentsBtn);
        locationBtn = findViewById(R.id.locationBtn);
        resultsBtn = findViewById(R.id.studentResultBtn);
        facebookBtn = findViewById(R.id.facebookBtn);
        askBtn = findViewById(R.id.askBtn);
        testBtn = findViewById(R.id.testBtn);

        Random random = new Random();
        doBounceAnimation(aboutBtn     , -1000, 0, 1000 + random.nextInt(1000));
        doBounceAnimation(departmentBtn, -1000, 0, 1000 + random.nextInt(1000));
        doBounceAnimation(locationBtn  , -1000, 0, 1000 + random.nextInt(1000));
        doBounceAnimation(resultsBtn   , -1000, 0, 1000 + random.nextInt(1000));
        doBounceAnimation(facebookBtn  , -1000, 0, 1000 + random.nextInt(1000));
        doBounceAnimation(askBtn       , -1000, 0, 1000 + random.nextInt(1000));
        doBounceAnimation(testBtn      , -1000, 0, 1000 + random.nextInt(1000));


//        Animation animation = new TranslateAnimation(-rootView.getWidth(), 0, 0, 0);
//        animation.setDuration(1000);
//        animation.setFillAfter(true);
        //rootView.startAnimation(animation);

        switch (faculty) {
            case FACULTY_OF_LAW:
                getSupportActionBar().setTitle(R.string.faculty_of_low);
                break;
            case FACULTY_OF_ARTS:
                getSupportActionBar().setTitle(R.string.faculty_of_arts);
                break;
            case FACULTY_OF_NURSING:
                getSupportActionBar().setTitle(R.string.faculty_of_nursing);
                break;
            case FACULTY_OF_SCIENCE:
                getSupportActionBar().setTitle(R.string.faculty_of_science);
                break;
            case FACULTY_OF_MEDICINE:
                getSupportActionBar().setTitle(R.string.faculty_of_medicine);
                break;
            case FACULTY_OF_ECONOMICS:
                getSupportActionBar().setTitle(R.string.faculty_of_economics);
                break;
            case FACULTY_OF_EDUCATION:
                getSupportActionBar().setTitle(R.string.faculty_of_education);
                break;
            case FACULTY_OF_ENGINEERING:
                getSupportActionBar().setTitle(R.string.faculty_of_engineering);
                ((ImageView)findViewById(R.id.faculty_logo)).setImageResource(R.drawable.eng_logo);
                break;
            case FACULTY_OF_INFORMATION_TECHNOLOGY:
                getSupportActionBar().setTitle(R.string.faculty_of_information_technology);
                ((ImageView)findViewById(R.id.faculty_logo)).setImageResource(R.drawable.it_logo);
                break;
            case FACULTY_OF_DENTISTRY_AND_ORAL_SURGERY:
                getSupportActionBar().setTitle(R.string.faculty_of_dentistry);
                break;
            case FACULTY_OF_PHARMACY:
                getSupportActionBar().setTitle(R.string.faculty_of_pharmacy);
                break;
            case FACULTY_OF_AGRICULTURE:
                getSupportActionBar().setTitle(R.string.faculty_of_agriculture);
                break;
            case FACULTY_OF_ARTS_AND_MEDIA:
                getSupportActionBar().setTitle(R.string.faculty_of_arts_and_media);
                break;
            case FACULTY_OF_ISLAMIC_STUDIES:
                getSupportActionBar().setTitle(R.string.faculty_of_islamic_studies);
                break;
            case FACULTY_OF_PHYSICAL_EDUCATION:
                getSupportActionBar().setTitle(R.string.faculty_of_physical_education);
                break;
        }
    }

    public void about(View view){

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.about_layout);
        switch (faculty) {
            case FACULTY_OF_LAW:
                break;
            case FACULTY_OF_ARTS:
                break;
            case FACULTY_OF_NURSING:
                break;
            case FACULTY_OF_SCIENCE:
                break;
            case FACULTY_OF_MEDICINE:
                break;
            case FACULTY_OF_ECONOMICS:
                break;
            case FACULTY_OF_EDUCATION:
                break;
            case FACULTY_OF_ENGINEERING:
                ((ImageView) dialog.findViewById(R.id.imageView)).setImageResource(R.drawable.eng_logo);
                ((TextView) dialog.findViewById(R.id.textView)).setText(getString(R.string.about_eng_faculty));
                break;
            case FACULTY_OF_INFORMATION_TECHNOLOGY:
                ((ImageView) dialog.findViewById(R.id.imageView)).setImageResource(R.drawable.it_logo);
                ((TextView) dialog.findViewById(R.id.textView)).setText(getString(R.string.about_it_faculty));
                break;
            case FACULTY_OF_DENTISTRY_AND_ORAL_SURGERY:
                break;
        }
        dialog.show();
    }

    public void departments(View view){

        Dialog departmentsDialog = new Dialog(this);
        departmentsDialog.setTitle(R.string.departments);
        departmentsDialog.setContentView(R.layout.departments_layout);
        LinearLayout departmentLayout = departmentsDialog.findViewById(R.id.departmentLayout);

        String[] departments = null;
        String[] descriptions = null;

        switch (faculty) {
            case FACULTY_OF_LAW:
                departments = getResources().getStringArray(R.array.low_departments);
                descriptions = getResources().getStringArray(R.array.low_departments_description);
                break;
            case FACULTY_OF_ARTS:
                departments = getResources().getStringArray(R.array.arts_departments);
                descriptions = getResources().getStringArray(R.array.arts_departments_description);
                break;
            case FACULTY_OF_SCIENCE:
                departments = getResources().getStringArray(R.array.sci_departments);
                descriptions = getResources().getStringArray(R.array.sci_departments_description);
                break;
            case FACULTY_OF_MEDICINE:
                departments = getResources().getStringArray(R.array.human_med_departments);
                descriptions = getResources().getStringArray(R.array.human_med_departments_description);
                break;
            case FACULTY_OF_ECONOMICS:
                departments = getResources().getStringArray(R.array.eco_poli_departments);
                descriptions = getResources().getStringArray(R.array.eco_poli_departments_description);
                break;
            case FACULTY_OF_EDUCATION:
                departments = getResources().getStringArray(R.array.edu_departments);
                descriptions = getResources().getStringArray(R.array.edu_departments_description);
                break;
            case FACULTY_OF_ENGINEERING:
                departments = getResources().getStringArray(R.array.eng_departments);
                descriptions = getResources().getStringArray(R.array.eng_departments_description);
                break;
            case FACULTY_OF_INFORMATION_TECHNOLOGY:
                departments = getResources().getStringArray(R.array.it_departments);
                descriptions = getResources().getStringArray(R.array.it_departments_description);
                break;
            case FACULTY_OF_DENTISTRY_AND_ORAL_SURGERY:
                departments = getResources().getStringArray(R.array.dentistry_and_oral_surgery_departments);
                descriptions = getResources().getStringArray(R.array.dentistry_and_oral_surgery_departments_description);
                break;
            case FACULTY_OF_PHYSICAL_EDUCATION:
                departments = getResources().getStringArray(R.array.physical_education_departments);
                descriptions = getResources().getStringArray(R.array.physical_education_departments_description);
                break;
            case FACULTY_OF_ISLAMIC_STUDIES:
                departments = getResources().getStringArray(R.array.islamic_studies_departments);
                descriptions = getResources().getStringArray(R.array.islamic_studies_departments_description);
                break;
            case FACULTY_OF_ARTS_AND_MEDIA:
                departments = getResources().getStringArray(R.array.arts_and_media_departments);
                descriptions = getResources().getStringArray(R.array.arts_and_media_departments_description);
                break;
            case FACULTY_OF_AGRICULTURE:
                departments = getResources().getStringArray(R.array.agriculture_departments);
                descriptions = getResources().getStringArray(R.array.agriculture_departments_description);
                break;
            case FACULTY_OF_NURSING:
                departments = getResources().getStringArray(R.array.nursing_departments);
                descriptions = getResources().getStringArray(R.array.nursing_departments_description);
                break;
            case FACULTY_OF_PHARMACY:
                departments = getResources().getStringArray(R.array.pharmacy_departments);
                descriptions = getResources().getStringArray(R.array.pharmacy_departments_description);
                break;
        }

        for (int i = 0; i < departments.length; i++) {
            departmentLayout.addView(new Department(this, departments[i], descriptions[i]));
        }

        departmentsDialog.show();
    }

    public void location(View view){
        startActivity(new Intent(this, LocationActivity.class).putExtra("Faculty", faculty));
    }

    public void studentResult(View view){
        currentUser = mAuth.getCurrentUser();

        if(currentUser != null){

            Dialog dialog = new Dialog(this, R.style.AppTheme_NoActionBar);
            dialog.setContentView(R.layout.result_layout);
            LinearLayout titlesLayout = dialog.findViewById(R.id.titlesLayout);
            ListView listView = dialog.findViewById(R.id.listView);
            ProgressBar progressBar = dialog.findViewById(R.id.progressBar);
            dialog.show();

            firestore.collection(Common.RESULTS_COLLECTION_NAME).document(currentUser.getUid()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    Map<String, Object> data = document.getData();
                    List<String> keys = new ArrayList<>(data.keySet());

                    titlesLayout.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);

                    listView.setAdapter(new ArrayAdapter(Faculty.this, R.layout.result_layout){
                        @Override
                        public int getCount() {
                            return data.size();
                        }

                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            String key = keys.get(position);

                            View item = getLayoutInflater().inflate(R.layout.result_row, parent, false);

                            TextView subjectTV = item.findViewById(R.id.subjectTV);
                            subjectTV.setText(key);

                            TextView scoreTV = item.findViewById(R.id.scoreTV);
                            scoreTV.setText(String.valueOf(data.get(key)));

                            return item;
                        }
                    });
                }
            });
        }
    }

    public void facebook(View view){
        Uri facebookUri = Common.getFacebookUri(this, faculty);
        if(facebookUri != null) {
            startActivity( new Intent(Intent.ACTION_VIEW, facebookUri));
        }
    }

    public void sendMessage(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "ayad@eng.misuratau.edu.ly");
        startActivity(emailIntent);
    }


    public void test(View view){
        switch (faculty) {
            case FACULTY_OF_LAW:
            case FACULTY_OF_ARTS:
            case FACULTY_OF_SCIENCE:
            case FACULTY_OF_MEDICINE:
            case FACULTY_OF_ECONOMICS:
            case FACULTY_OF_EDUCATION:
            case FACULTY_OF_ENGINEERING:
            case FACULTY_OF_DENTISTRY_AND_ORAL_SURGERY:
            case FACULTY_OF_PHYSICAL_EDUCATION:
            case FACULTY_OF_ISLAMIC_STUDIES:
            case FACULTY_OF_ARTS_AND_MEDIA:
            case FACULTY_OF_AGRICULTURE:
            case FACULTY_OF_NURSING:
            case FACULTY_OF_PHARMACY:
                break;
            case FACULTY_OF_INFORMATION_TECHNOLOGY:
                test(Common.IT_QUESTIONS);
                break;
        }
    }

    public void test(String questionCollectionName) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.quick_test_layout);
        dialog.setTitle(R.string.test_your_self);
        dialog.show();

        TextView questionTV = dialog.findViewById(R.id.questionTV);
        RadioGroup choiceGroup = dialog.findViewById(R.id.radioGroup);
        AppCompatRadioButton choice1 = dialog.findViewById(R.id.choice1);
        AppCompatRadioButton choice2 = dialog.findViewById(R.id.choice2);
        AppCompatRadioButton choice3 = dialog.findViewById(R.id.choice3);
        ConstraintLayout constraintLayout = dialog.findViewById(R.id.constraintLayout);
        ImageButton nextBtn = dialog.findViewById(R.id.nextBtn);
        ImageButton previousBtn = dialog.findViewById(R.id.previousBtn);
        Button submit = dialog.findViewById(R.id.submitBtn);
        TextView resultTV = dialog.findViewById(R.id.resultTV);
        ProgressBar progressBar = dialog.findViewById(R.id.progressBar);

        final List<Question> questions = new ArrayList<>();

        firestore.collection(questionCollectionName).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<DocumentSnapshot> documents = task.getResult().getDocuments();
                List<Integer> indexes = new ArrayList<>();
                Random random = new Random();
                while (indexes.size() < 5) {
                    int index = random.nextInt(documents.size());
                    if (!indexes.contains(index))
                        indexes.add(index);
                }
                for (int index : indexes) {
                    String q = (String) documents.get(index).get(Common.QUESTION);
                    List<CharSequence> choices = new ArrayList<>();
                    choices.add((String) documents.get(index).get(Common.CHOICE1));
                    choices.add((String) documents.get(index).get(Common.CHOICE2));
                    choices.add((String) documents.get(index).get(Common.CHOICE3));
                    Long ans = (Long) documents.get(index).get(Common.ANSWER);
                    questions.add(new Question(q, choices, ans.intValue()));
                }

                questionTV.setVisibility(View.VISIBLE);
                choiceGroup.setVisibility(View.VISIBLE);
                constraintLayout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

                setQuestion(questionTV, choice1, choice2, choice3, questions.get(0));
            } else {
                dialog.cancel();
            }
        });

        final int[] index = {0};
        AtomicInteger result = new AtomicInteger();
        AtomicInteger submittedQuestions = new AtomicInteger();

        submit.setOnClickListener(v -> {
            Question question = questions.get(index[0]);
            if (!question.isSubmitted()) {
                if (choice1.isChecked() || choice2.isChecked() || choice3.isChecked()) {
                    boolean answer =
                            (choice1.isChecked() && question.getAnswer() == 1) ||
                                    (choice2.isChecked() && question.getAnswer() == 2) ||
                                    (choice3.isChecked() && question.getAnswer() == 3);
                    if (answer)
                        result.getAndIncrement();
                    submittedQuestions.getAndIncrement();
                    if (submittedQuestions.get() == questions.size()) {
                        resultTV.setText(String.valueOf(result));
                    }
                    if (choice1.isChecked()) {
                        question.submit(1);
                    } else if (choice2.isChecked()) {
                        question.submit(2);
                    } else if (choice3.isChecked()) {
                        question.submit(3);
                    }

                    choice1.setEnabled(false);
                    choice2.setEnabled(false);
                    choice3.setEnabled(false);

                    resultTV.setText(result.toString());
                }
            }
        });

        nextBtn.setOnClickListener(v -> {
            if (index[0] < (questions.size() - 1)) {
                setQuestion(questionTV, choice1, choice2, choice3, questions.get(++index[0]));
            }
        });


        previousBtn.setOnClickListener(v -> {
            if (index[0] > 0) {
                setQuestion(questionTV, choice1, choice2, choice3, questions.get(--index[0]));
            }
        });
    }

    private void setQuestion(TextView questionTV,
                             AppCompatRadioButton choice1,
                             AppCompatRadioButton choice2,
                             AppCompatRadioButton choice3,
                             Question question) {

        questionTV.setText(question.getQuestion());
        choice1.setText(question.getOptions().get(0));
        choice2.setText(question.getOptions().get(1));
        choice3.setText(question.getOptions().get(2));

        if(question.isSubmitted()) {
            int choice = question.getChoice();
            if(choice == 1)
                choice1.setChecked(true);
            else if(choice == 2)
                choice2.setChecked(true);
            else if(choice == 3)
                choice3.setChecked(true);
            choice1.setEnabled(false);
            choice2.setEnabled(false);
            choice3.setEnabled(false);
        } else {
            choice1.setEnabled(true);
            choice2.setEnabled(true);
            choice3.setEnabled(true);

            choice1.setChecked(false);
            choice2.setChecked(false);
            choice3.setChecked(false);
        }
    }

    private void doBounceAnimation(View targetView, int from, int to, int duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationX", from, to, 0);
        animator.setInterpolator(new EasingInterpolator(Ease.QUAD_IN_OUT));
        animator.setDuration(duration);
        animator.start();
    }
}
