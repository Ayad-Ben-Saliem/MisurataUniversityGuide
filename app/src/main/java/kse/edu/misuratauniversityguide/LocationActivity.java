package kse.edu.misuratauniversityguide;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends FragmentActivity
implements OnMapReadyCallback,
           GoogleApiClient.ConnectionCallbacks,
           GoogleApiClient.OnConnectionFailedListener,
           LocationListener{

    private LatLng position;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

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
                position = new LatLng(32.352077, 15.067864);
                title = "FACULTY OF ENGINEERING";
                break;
            case FACULTY_OF_INFORMATION_TECHNOLOGY:
                position = new LatLng(32.352285, 15.067650);
                title = "FACULTY OF INFORMATION TECHNOLOGY";
                break;
            case FACULTY_OF_DENTISTRY_AND_ORAL_SURGERY:
        }

        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap map = googleMap;

        map.addMarker(new MarkerOptions().position(position).title(title));
        map.moveCamera(CameraUpdateFactory.newLatLng(position));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    /**
     * Called when pointer capture is enabled or disabled for the current window.
     *
     * @param hasCapture True if the window has pointer capture.
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
