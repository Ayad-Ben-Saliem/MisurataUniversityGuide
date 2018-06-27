package kse.edu.misuratauniversityguide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import org.awaitility.Awaitility;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    protected FirebaseAuth mAuth;
    protected FirebaseUser currentUser;

    protected Toolbar mainToolbar;
    protected Menu menu;

    // Login Dialog UI References.
    protected Dialog loginDialog;
    protected AutoCompleteTextView loginEmailView;
    protected EditText loginPasswordView;
    protected View loginProgressBarView;
    protected View loginButtonView;
    protected View loginFormView;


    // Register Dialog UI References.
    protected Dialog registerDialog;
    protected AutoCompleteTextView registerEmailView;
    protected EditText registerPasswordView;
    protected EditText registerRePasswordView;
    protected View registerProgressBarView;
    protected View registerButtonView;
    protected View registerFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mainToolbar = findViewById(R.id.toolbar);
        if (mainToolbar != null) {
            configureToolbar();
        }
    }

    private void configureToolbar() {

        if (useToolbar()) {
            mainToolbar.setTitleTextColor(Color.WHITE);
            setSupportActionBar(mainToolbar);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            loginDialog = new Dialog(this, R.style.AppTheme_NoActionBar);
            setupLoginDialog();

            registerDialog = new Dialog(this, R.style.AppTheme_NoActionBar);
            setupRegisterDialog();

        } else {
            mainToolbar.setVisibility(View.GONE);
        }
    }

    protected boolean useToolbar() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_register_menu, menu);
        this.menu = menu;

        currentUser = mAuth.getCurrentUser();
        boolean visible = currentUser == null;

        menu.getItem(0).setVisible(visible); // Login
        menu.getItem(1).setVisible(!visible);// Profile
        menu.getItem(2).setVisible(visible); // Register
        menu.getItem(3).setVisible(!visible);// Logout

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.user_logo);
        mainToolbar.setOverflowIcon(drawable);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.login: {
                loginDialog.show();
            }
            break;
            case R.id.profile:{
                Intent intent = new Intent(this, ProfileActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            break;
            case R.id.register: {
                registerDialog.show();
            }
            break;
            case R.id.logout:{
                mAuth.signOut();
                menu.findItem(R.id.login).setVisible(true);
                menu.findItem(R.id.profile).setVisible(false);
                menu.findItem(R.id.register).setVisible(true);
                menu.findItem(R.id.logout).setVisible(false);
            }
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupLoginDialog() {
        loginDialog.setContentView(R.layout.login_dialog);

        loginEmailView       = loginDialog.findViewById(R.id.emailView);
        loginPasswordView    = loginDialog.findViewById(R.id.passwordView);
        loginProgressBarView = loginDialog.findViewById(R.id.loginProgressBar);
        loginFormView        = loginDialog.findViewById(R.id.loginForm);
        loginButtonView      = loginDialog.findViewById(R.id.loginBtn);

        loginPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) && attemptLogin());
        loginButtonView.setOnClickListener(view -> attemptLogin());

        loginDialog.setOnCancelListener(dialog -> { });
    }

    private boolean attemptLogin() {

        // Reset errors.
        loginEmailView.setError(null);
        loginPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = loginEmailView.getText().toString();
        String password = loginPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!isPasswordValid(password)) {
            focusView = loginPasswordView;
            cancel = true;
        }

        if (!isEmailValid(email)) {
            focusView = loginEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgressBar(loginFormView, loginProgressBarView, true);
            login(email, password, 3);
        }
        return !cancel;
    }

    private void setupRegisterDialog() {
        registerDialog.setContentView(R.layout.register_dialog);

        registerEmailView       = registerDialog.findViewById(R.id.emailView);
        registerPasswordView    = registerDialog.findViewById(R.id.passwordView);
        registerRePasswordView  = registerDialog.findViewById(R.id.rePasswordView);
        registerProgressBarView = registerDialog.findViewById(R.id.registerProgressBar);
        registerFormView        = registerDialog.findViewById(R.id.registerForm);
        registerButtonView      = registerDialog.findViewById(R.id.registerBtn);

        registerPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) && attemptLogin());
        registerButtonView.setOnClickListener(view -> attemptRegister());

        registerDialog.setOnCancelListener(dialog -> { });
    }

    private boolean attemptRegister() {

        // Reset errors.
        registerEmailView.setError(null);
        registerPasswordView.setError(null);
        registerRePasswordView.setError(null);

        // Store values at the time of the register attempt.
        String email = registerEmailView.getText().toString();
        String password = registerPasswordView.getText().toString();
        String rePassword = registerRePasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!isPasswordValid(password)) {
            focusView = registerPasswordView;
            cancel = true;
        }

        if(rePassword.isEmpty() ){
            registerRePasswordView.setError(getString(R.string.error_field_required));
            if(!cancel)
                focusView = registerPasswordView;
            cancel = true;
        }else if(password.compareTo(rePassword) != 0){
            registerRePasswordView.setError(getString(R.string.error_password_mismatch));
            if(!cancel)
                focusView = registerPasswordView;
            cancel = true;
        }

        if (!isEmailValid(email)) {
            focusView = registerEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgressBar(registerFormView, registerProgressBarView, true);
            register(email, password);
        }
        return !cancel;
    }

    private boolean isPasswordValid(String password) {
        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            loginPasswordView.setError(getString(R.string.error_field_required));
            return false;
        } else if (password.length() < 8) {
            loginPasswordView.setError(getString(R.string.error_invalid_password));
            return false;
        }

        return true;
    }

    private boolean isEmailValid(String email) {
        // Check for a valid email address.
        email = email.toLowerCase();
        if (TextUtils.isEmpty(email)) {
            loginEmailView.setError(getString(R.string.error_field_required));
            return false;
        } else if (!email.endsWith(".misuratau.edu.ly")) {
            loginEmailView.setError(getString(R.string.error_invalid_email));
            return false;
        }
        return true;
    }

    private void login(final String email, final String password, final int repeat) {
        if(repeat < 0)
            return;
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                showProgressBar(loginFormView, loginProgressBarView, false);
                menu.findItem(R.id.login).setVisible(false);
                menu.findItem(R.id.profile).setVisible(true);
                menu.findItem(R.id.register).setVisible(false);
                menu.findItem(R.id.logout).setVisible(true);

//                Awaitility.await().until(() -> registerFormView.getVisibility() == View.VISIBLE);
                loginDialog.cancel();
            } else {
                String msg = "Error: " + task.getException().getMessage() + "\nجاري المحاولة مرة أخرى";
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
//                login(email, password, repeat-1);
                showProgressBar(loginFormView, loginProgressBarView, false);
            }
        });
    }

    private void register(String email, String password) {
        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(task -> {
            try {
                List<String> methods = task.getResult().getSignInMethods();
                if (methods != null && methods.isEmpty()) {
                    register(email, password, 3);
                }else {
                    registerEmailView.setError(getString(R.string.error_email_exists));
                    registerEmailView.requestFocus();
                }
            }catch (RuntimeExecutionException | NullPointerException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void register(String email, String password, int repeat){
        if(repeat < 0)
            return;
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                showProgressBar(registerFormView, registerProgressBarView, false);
                menu.findItem(R.id.login).setVisible(false);
                menu.findItem(R.id.profile).setVisible(true);
                menu.findItem(R.id.register).setVisible(false);
                menu.findItem(R.id.logout).setVisible(true);

//                Awaitility.await().until(() -> registerFormView.getVisibility() == View.VISIBLE);
                registerDialog.cancel();
            } else {
                String msg = "Error: " + task.getException().getMessage() + "\nجاري المحاولة مرة أخرى";
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
//                register(email, password, repeat-1);
                showProgressBar(registerFormView, registerProgressBarView, false);
            }
        });
    }

    private void showProgressBar(View formView, View progress, final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        formView.setVisibility(show ? View.GONE : View.VISIBLE);
        formView.animate()
                .setDuration(shortAnimTime)
                .alpha(show ? 0.5f : 1);

        progress.setVisibility(show ? View.VISIBLE : View.GONE);
        progress.animate()
                .setDuration(shortAnimTime)
                .alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progress.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });

        Common.setEnable(formView, !show);

    }
}
