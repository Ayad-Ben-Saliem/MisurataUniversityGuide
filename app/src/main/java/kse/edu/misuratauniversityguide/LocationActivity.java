package kse.edu.misuratauniversityguide;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;


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
                position = new LatLng(Common.ENG_LATITUDE, Common.ENG_LONGITUDE);
                title = "FACULTY OF ENGINEERING";
                break;
            case FACULTY_OF_INFORMATION_TECHNOLOGY:
                position = new LatLng(Common.IT_LATITUDE, Common.IT_LONGITUDE);
                title = "FACULTY OF INFORMATION TECHNOLOGY";
                break;
            case FACULTY_OF_DENTISTRY_AND_ORAL_SURGERY:
        }

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("TAG_TAG", "Here");
        googleMap.addMarker(new MarkerOptions().position(position).title(title));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) { }

    @Override
    public void onConnectionSuspended(int i) { }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { }

    @Override
    public void onLocationChanged(Location location) { }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) { }
}
