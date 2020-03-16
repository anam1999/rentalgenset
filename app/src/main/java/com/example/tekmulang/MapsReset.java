package com.example.tekmulang;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsReset extends Fragment {
    GoogleMap googleMap;
    MapView mapView;

    public MapsReset() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.activity_maps, container, false);
        mapView = (MapView) mView.findViewById(R.id.mapV);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        try{
            MapsInitializer.initialize(getActivity().getApplicationContext());
        }catch (Exception e){
            e.printStackTrace();
        }
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap gMap) {
                googleMap=gMap;
                googleMap.addMarker(new MarkerOptions().position(new LatLng(-8.1844859,113.6680747)).title("Kabupaten Jember").snippet("Status : PETA JARINGAN")).showInfoWindow();
                CameraPosition home = CameraPosition.builder().target(new LatLng(-8.1844859,113.6680747)).zoom(10).bearing(0).tilt(45).build();
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(home));
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        getActivity().startActivity(new Intent(getActivity(),PilihReset.class));
                        return false;
                    }
                });
            }
        });

        return mView;
    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onLowMemory(){
        super.onLowMemory();
        mapView.onLowMemory();
    }

}


