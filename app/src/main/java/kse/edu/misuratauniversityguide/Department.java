package kse.edu.misuratauniversityguide;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Department extends LinearLayout {

    Button button;
    TextView description;

    private static Department visibleDepartment;

    private void init(String departmentName,
                      String departmentDescription){
        super.setOrientation(VERTICAL);

        button = new Button(getContext());
        button.setText(departmentName);
        button.setTextColor( getResources().getColor(R.color.white) );
        button.setTextAlignment(Button.TEXT_ALIGNMENT_CENTER);
        button.setBackgroundColor( getResources().getColor(R.color.primaryColor) );
        this.addView(button);

        description = new TextView(getContext());
        description.setText(departmentDescription);
        description.setTextColor(getResources().getColor(R.color.black));
        description.setTextAlignment(Button.TEXT_ALIGNMENT_CENTER);
        description.setVisibility(GONE);
        addView(description);

        final LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                                                     LayoutParams.WRAP_CONTENT);

        button.setOnClickListener(v -> {
            if(description.getVisibility() == VISIBLE)
                description.setVisibility(GONE);
            else if(description.getVisibility() == GONE){
                if(visibleDepartment != null && visibleDepartment != Department.this)
                    visibleDepartment.description.setVisibility(GONE);
                description.setVisibility(VISIBLE);
                visibleDepartment = Department.this;
            }

        });

        setLayoutParams(layoutParams);
        super.setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setPadding(4, 4, 4, 4);
    }

    public Department(Context context,
                      String departmentName,
                      String departmentDescription) {
        super(context);
        init(departmentName, departmentDescription);
    }

    public Department(Context context,
                      @Nullable AttributeSet attribute,
                      String departmentName,
                      String departmentDescription) {
        super(context, attribute);
        init(departmentName, departmentDescription);
    }

    public Department(Context context,
                      @Nullable AttributeSet attribute,
                      @StyleRes int defStyleAttr,
                      String departmentName,
                      String departmentDescription) {
        super(context, attribute, defStyleAttr);
        init(departmentName, departmentDescription);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Department(Context context,
                      @Nullable AttributeSet attribute,
                      int defStyleAttr,
                      @StyleRes int defStyleRes,
                      String departmentName,
                      String departmentDescription) {
        super(context, attribute, defStyleAttr, defStyleRes);
        init(departmentName, departmentDescription);
    }

    public Department(Context context,
                      @Nullable AttributeSet attribute,
                      @StyleRes int defStyleRes){
        super(context, attribute, defStyleRes);
        init(null, null);
    }
}
