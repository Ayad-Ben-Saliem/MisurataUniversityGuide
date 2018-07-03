package kse.edu.misuratauniversityguide;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private final static Intent RESULT_INTENT = new Intent();
    public final static String REASON_RESULT_TAG = "REASON";

    private LatLng position = new LatLng(32.378929, 15.093145);
    private String title = "KSE for Training and Development";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            String msg = "Finishing test case since Google Play Services are not available";
            Log.d("MapsTAG", msg);
            RESULT_INTENT.putExtra(REASON_RESULT_TAG, msg);
            setResult(RESULT_CANCELED);
            finish();
        }

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Faculties faculty = (Faculties) getIntent().getExtras().get("Faculty");

        switch (faculty) {
            case FACULTY_OF_LAW:
            case FACULTY_OF_ARTS:
            case FACULTY_OF_NURSING:
            case FACULTY_OF_SCIENCE:
            case FACULTY_OF_MEDICINE:
            case FACULTY_OF_ECONOMICS:
            case FACULTY_OF_EDUCATION:
            case FACULTY_OF_ENGINEERING:
                position = new LatLng(Common.ENG_LATITUDE, Common.ENG_LONGITUDE);
                title = "FACULTY OF ENGINEERING";
                break;
            case FACULTY_OF_INFORMATION_TECHNOLOGY:
                position = new LatLng(Common.IT_LATITUDE, Common.IT_LONGITUDE);
                title = "FACULTY OF INFORMATION TECHNOLOGY";
                break;
            case FACULTY_OF_DENTISTRY_AND_ORAL_SURGERY:
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(position).title(title));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position).zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result, 0).show();
            }
            return false;
        }
        return true;
    }
}
