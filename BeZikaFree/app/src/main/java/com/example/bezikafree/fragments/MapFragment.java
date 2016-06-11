package com.example.bezikafree.fragments;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bezikafree.Coordinate;
import com.example.bezikafree.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Billy on 6/11/16.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mapView;
    private SupportMapFragment mapFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_map,container,false);
        initGoogleMaps(v,savedInstanceState);
//        mapFragment = (SupportMapFragment) getFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        return v;
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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        String addressName = getAddressName(-34,151);
        LatLng addressLatLng = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(addressLatLng).title(addressName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(addressLatLng));
    }

    private String getAddressName(double lat,double lon){
        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(lat, lon, 1);
            if(null!=listAddresses&&listAddresses.size()>0){
                String location = listAddresses.get(0).getAddressLine(0);
                return location;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "N/A No Address Found at these coordinates";
    }

    private void initGoogleMaps(View view, Bundle savedInstanceState){
        mapView = (MapView) view.findViewById(R.id.input_fragment_mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();// needed to get the map to display immediately
        mMap = mapView.getMap();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


        plotMarkers(37.484419, -122.203787);
        drawAmerica();


//        drawPolygon();

    }

    public void plotMarkers(double latitude, double longitude){
        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude))
                .title(getAddressName(latitude,longitude))
                .snippet("OUTBREAK STUFF TEST STUFF");
        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        mMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude)).zoom(2).build();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }


    public void drawPolygon(String[] stateCoor){
// Instantiates a new Polygon object and adds points to define a rectangle
        PolygonOptions rectOptions = new PolygonOptions();
        ArrayList<Coordinate> coorList = drawStates(stateCoor);
        for (Coordinate coor : coorList){
            rectOptions.add(new LatLng(coor.getLatitude(),coor.getLongitude()));

        }
        rectOptions.strokeColor(Color.BLACK).fillColor(Color.RED).strokeWidth(3);


// Get back the mutable Polygon
        Polygon polygon = mMap.addPolygon(rectOptions);
//        drawStates();
    }

    private ArrayList<Coordinate> drawStates(String[] stateCoor){
        ArrayList<Coordinate> listOfCoordinates = new ArrayList<>();
//        String[] stateCoor = getResources().getStringArray(R.array.Alaska);
//        Log.i("MAPFRAG", "drawStates: " + stateCoor[0]);
        for (String coor : stateCoor){
            String[] latlng = coor.split(",");
            listOfCoordinates.add(new Coordinate(Double.parseDouble(latlng[0]),Double.parseDouble(latlng[1])));
        }
        return listOfCoordinates;
    }

    private void drawAmerica(){
        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(R.array.USStates);
        int n = ta.length();
        String[][] array = new String[n][];
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            if (id > 0) {
                array[i] = res.getStringArray(id);
                drawPolygon(array[i]);

            } else {
                // something wrong with the XML
            }
        }
        ta.recycle();

    }

}
