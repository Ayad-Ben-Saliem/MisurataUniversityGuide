package kse.edu.misuratauniversityguide;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Common {

    public final static String FACULTY = "FACULTY";

    private final static String FACEBOOK_URL = "https://www.facebook.com/";
    private final static String FACEBOOK_APP_PACKAGE_NAME = "com.facebook.katana";
    public  final static String FACEBOOK_SDK_PACKAGE_NAME = "com.facebook.android";
    public  final static String FACEBOOK_MESSENGER_PACKAGE_NAME = "com.facebook.orca";
    public  final static String FACEBOOK_EXAMPLE_PACKAGE_NAME = "com.example.facebook";

    public final static String IT_QUESTIONS = "IT Questions";
    public final static String ENG_QUESTIONS = "ENG Questions";

    static boolean isPackageExists(Context c, String targetPackage) {

        PackageManager pm = c.getPackageManager();
        try {
            pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

    //method to get the right URL to use in the intent
    private static String getFacebookPageURL(Context context,String pageID) {
        PackageManager pm = context.getPackageManager();
        try {
            int versionCode = pm.getPackageInfo(FACEBOOK_APP_PACKAGE_NAME, 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL + pageID;
            } else { //older versions of fb app
                return "fb://page/" + pageID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL + pageID; //normal web url
        }
    }

    static Uri getFacebookUri(Context context, Faculties faculty){
        Uri facebookUri = null;
        switch (faculty) {
            case FACULTY_OF_LAW:
                facebookUri = Uri.parse( Common.getFacebookPageURL(context, "KSE.edu") );
                break;
            case FACULTY_OF_ARTS:
                facebookUri = Uri.parse( Common.getFacebookPageURL(context, "KSE.edu") );
                break;
            case FACULTY_OF_NURSING:
                facebookUri = Uri.parse( Common.getFacebookPageURL(context, "KSE.edu") );
                break;
            case FACULTY_OF_SCIENCE:
                facebookUri = Uri.parse( Common.getFacebookPageURL(context, "KSE.edu") );
                break;
            case FACULTY_OF_MEDICINE:
                facebookUri = Uri.parse( Common.getFacebookPageURL(context, "KSE.edu") );
                break;
            case FACULTY_OF_ECONOMICS:
                facebookUri = Uri.parse( Common.getFacebookPageURL(context, "KSE.edu") );
                break;
            case FACULTY_OF_EDUCATION:
                facebookUri = Uri.parse( Common.getFacebookPageURL(context, "KSE.edu") );
                break;
            case FACULTY_OF_ENGINEERING:
                facebookUri = Uri.parse( Common.getFacebookPageURL(context, "KSE.edu") );
                break;
            case FACULTY_OF_INFORMATION_TECHNOLOGY:
                facebookUri = Uri.parse( Common.getFacebookPageURL(context, "KSE.edu") );
                break;
            case FACULTY_OF_DENTISTRY_AND_ORAL_SURGERY:
                facebookUri = Uri.parse( Common.getFacebookPageURL(context, "KSE") );
                break;
        }
        return facebookUri;
    }

    private static int questionNumber = 20;
    private static int requiredQuestion;

    public static class NotSupportedException extends RuntimeException{}


    public static QuestionView getQuestion(Context context){

        return new QuestionView(context, "Are You raddy?", "Yes", "No");
    }

    public static List<QuestionView> getQuestions(Context context, int requiredQuestion){

        AtomicBoolean wait = new AtomicBoolean(true);
        List<QuestionView> questionViews = new ArrayList<>();

        if( FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            firestore.collection(Common.IT_QUESTIONS).get().addOnCompleteListener(task ->{
                if(task.isSuccessful()) {
                    List<DocumentSnapshot> documents = task.getResult().getDocuments();
                    for (DocumentSnapshot document : documents) {
                        QuestionView questionView = new QuestionView(context);
                        questionView.setQuestion((String)document.get("Question"));
                        questionView.setQuestion((String)document.get("Question"));
                        questionView.setQuestion((String)document.get("Question"));
                        questionView.setQuestion((String)document.get("Question"));
                        questionView.setAnswers((String)document.get("Question"));
                    }
                    wait.set(false);
                }else {

                }
            });
        }

        while (wait.get());
        return questionViews;
    }

    public static void setEnable(View view, boolean enable){
        if(view != null) {
            view.setEnabled(enable);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    setEnable(viewGroup.getChildAt(i), enable);
                }
            }
        }
    }
}
