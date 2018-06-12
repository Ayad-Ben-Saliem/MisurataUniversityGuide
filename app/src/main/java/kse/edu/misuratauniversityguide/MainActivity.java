package kse.edu.misuratauniversityguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    Intent facultyIntent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(getString(R.string.app_name));

        facultyIntent = new Intent(this, Faculty.class);
    }

    public void informationTechnology(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_INFORMATION_TECHNOLOGY) );
    }

    public void engineering(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_ENGINEERING) );
    }

    public void arts(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_ARTS) );
    }

    public void dentistry(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_DENTISTRY_AND_ORAL_SURGERY) );
    }

    public void economics(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_ECONOMICS) );
    }

    public void science(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_SCIENCE) );
    }

    public void medicine(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_MEDICINE) );
    }

    public void education(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_EDUCATION) );
    }

    public void low(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_LAW) );
    }

    public void nursing(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_NURSING) );
    }

    public void pharmacy(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_PHARMACY) );
    }

    public void agriculture(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_AGRICULTURE) );
    }

    public void arts_and_media(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_ARTS_AND_MEDIA) );
    }

    public void islamic_studies(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_ISLAMIC_STUDIES) );
    }

    public void physical_education(View view) {
        startActivity( facultyIntent.putExtra(Common.FACULTY, Faculties.FACULTY_OF_PHYSICAL_EDUCATION) );
    }
}
