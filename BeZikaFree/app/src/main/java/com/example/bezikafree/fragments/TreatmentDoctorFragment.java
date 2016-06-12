package com.example.bezikafree.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bezikafree.NyTimesAPI.QuestDiagnostics;
import com.example.bezikafree.R;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by adao1 on 6/10/2016.
 */
public class TreatmentDoctorFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback{
    private GoogleMap map;

    private MapView mapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_treatment_doctor,container,false);
        initGoogleMaps(v,savedInstanceState);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        map = googleMap;

        // Add a marker in Sydney and move the camera
        String addressName = getAddressName(-34,151);
        LatLng addressLatLng = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(addressLatLng).title(addressName));
        map.moveCamera(CameraUpdateFactory.newLatLng(addressLatLng));
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
        mapView = (MapView) view.findViewById(R.id.input_fragment_centers_mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();// needed to get the map to display immediately
        map = mapView.getMap();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        plotLabLocations();

    }


    public void plotLabLocations(){

        QuestDiagnostics fosterCity = new QuestDiagnostics(37.5533023,-122.2570683,"Quest Diagnostics Foster City", "1098 Foster City Blvd #102, Foster City, CA 94404");
        QuestDiagnostics sanMateo = new QuestDiagnostics(37.5688825,-122.3290554, "Quest Diagnostics San Mateo","127 N San Mateo Dr, San Mateo, CA 94401");
        QuestDiagnostics sanCarlos = new QuestDiagnostics(37.4995998,-122.2534291, "Quest Diagnostics San Carlos", "1100 Laurel St, San Carlos, CA 94070");
        QuestDiagnostics redwoodCity = new QuestDiagnostics(37.4871429,-122.2322049, "Quest Diagnostics Redwood City", "401 Warren St Ste 100, Redwood City, CA 94063");
        QuestDiagnostics hayward = new QuestDiagnostics(37.6503056,-122.1035862, "Quest Diagnostics Hayward", "Southland Mall, 1 Southland Dr #718, Hayward, CA 94545");
        QuestDiagnostics hayward2 = new QuestDiagnostics(37.6741303,-122.0872336, "Quest Diagnostics Hayward", "22331 Mission Blvd, Hayward, CA 94541");
        QuestDiagnostics mountainView = new QuestDiagnostics(37.3668767,-122.0800002, "Quest Diagnostics Mountain View", "205 South Dr, Ste G, Mountain View, CA 94040");
        QuestDiagnostics sunnyvale = new QuestDiagnostics(37.3527092,-122.0493229, "Quest Diagnostics Sunnyvale", "Foothill Medical Dental Center, 877 W Fremont Ave H-2, Sunnyvale, CA 94087");
        QuestDiagnostics sunnyvale2 = new QuestDiagnostics(37.3586435,-122.0280902, "Quest Diagnostics Sunnyvale", "500 E Remington Dr #28, Sunnyvale, CA 94087");
        QuestDiagnostics dalyCity = new QuestDiagnostics(37.6652426,-122.4757306, "Quest Diagnostics Daly City", "901 Campus Dr STE 103, Daly City, CA 94015");
        QuestDiagnostics sanFrancisco = new QuestDiagnostics(37.7460345,-122.4209447, "Quest Diagnostics San Francisco", "1640 Valencia St #1b, San Francisco, CA 94110");
        QuestDiagnostics sanFrancisco2 = new QuestDiagnostics(37.7573924,-122.4193763, "Quest Diagnostics San Francisco", "2480 Mission St #221, San Francisco, CA 94110");
        QuestDiagnostics sanFrancisco3 = new QuestDiagnostics(37.7659781,-122.433108, "Quest Diagnostics San Francisco", "2198 15th St, San Francisco, CA 94114");
        QuestDiagnostics sanFrancisco4 = new QuestDiagnostics(37.7842071,-122.4381828,"Quest Diagnostics San Francisco", "2201 Post St, San Francisco, CA 94115");
        QuestDiagnostics sanFrancisco5 = new QuestDiagnostics(37.7896566,-122.4080073,"Quest Diagnostics San Francisco", "450 Sutter St #2540, San Francisco, CA 94108");
        QuestDiagnostics fremont = new QuestDiagnostics(37.5669508,-121.9730322, "Quest Diagnostics Fremont", "556 Mowry Ave #103, Fremont, CA 94536");
        QuestDiagnostics fremont2 = new QuestDiagnostics(37.5572499,-121.9824448, "Quest Diagnostics Fremont", "2191 Mowry Ave #500B, Fremont, CA 94538");
        QuestDiagnostics fremont3 = new QuestDiagnostics(37.5669546,-121.9729296, "Quest Diagnostics Fremont", "39273 Liberty St, Fremont, CA 94538");
        QuestDiagnostics milbrae = new QuestDiagnostics(37.5975334,-122.386079, "Quest Diagnostics Milbrae", "120 S El Camino Real #5, Millbrae, CA 94030");
        QuestDiagnostics alameda = new QuestDiagnostics(37.7595745,-122.2541735, "Quest Diagnostics Alameda", "2111 Whitehall Pl, Alameda, CA 94501");

        ArrayList<QuestDiagnostics> questArray = new ArrayList<>();
        questArray.add(fosterCity);
        questArray.add(sanMateo);
        questArray.add(sanCarlos);
        questArray.add(redwoodCity);
        questArray.add(hayward);
        questArray.add(hayward2);
        questArray.add(mountainView);
        questArray.add(sunnyvale);
        questArray.add(sunnyvale2);
        questArray.add(dalyCity);
        questArray.add(sanFrancisco);
        questArray.add(sanFrancisco2);
        questArray.add(sanFrancisco3);
        questArray.add(sanFrancisco4);
        questArray.add(sanFrancisco5);
        questArray.add(fremont);
        questArray.add(fremont2);
        questArray.add(fremont3);
        questArray.add(milbrae);
        questArray.add(alameda);


        for(QuestDiagnostics questLab : questArray){
            plotMarkers(questLab);
        }

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(fosterCity.getLatitude(), fosterCity.getLongitude())).zoom(10).build();
        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    public void plotMarkers(QuestDiagnostics questLaboratory){
        // create marker
        double latitude = questLaboratory.getLatitude();
        double longitude = questLaboratory.getLongitude();
        String name = questLaboratory.getName();
        String address = questLaboratory.getAddress();

        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude))
                .title(name)
                .snippet(address);
        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        map.addMarker(marker);
    }

}
