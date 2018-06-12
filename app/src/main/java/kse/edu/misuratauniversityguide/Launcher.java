package kse.edu.misuratauniversityguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Launcher extends AppCompatActivity {

    boolean exit = false;
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(exit){
            executor.schedule(this::finish, 2, TimeUnit.SECONDS);
        }else {
            exit = true;
            executor.schedule(() -> startActivity(new Intent(this, MainActivity.class)), 3, TimeUnit.SECONDS);
        }
    }
}
