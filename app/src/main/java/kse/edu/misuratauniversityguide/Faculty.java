package kse.edu.misuratauniversityguide;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;

import java.util.List;
import java.util.Random;

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
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.quick_test_layout);
        dialog.show();

        final List<QuestionView> questionViews = Common.getQuestions(this, 5);

        ImageButton nextBtn = dialog.findViewById(R.id.nextBtn);
        ImageButton previousBtn = dialog.findViewById(R.id.previousBtn);

        nextBtn.setEnabled(false);
        previousBtn.setEnabled(false);
        
        Button submit = dialog.findViewById(R.id.submitBtn);
        submit.setOnClickListener(v -> {

        });
    }

    private void doBounceAnimation(View targetView, int from, int to, int duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationX", from, to, 0);
        animator.setInterpolator(new EasingInterpolator(Ease.QUAD_IN_OUT));
        animator.setDuration(duration);
        animator.start();
    }
}
