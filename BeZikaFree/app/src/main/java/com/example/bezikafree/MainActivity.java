package com.example.bezikafree;

import android.animation.PropertyValuesHolder;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bezikafree.fragments.EffectsFragment;
import com.example.bezikafree.fragments.MapFragment;
import com.example.bezikafree.fragments.NewsFragment;
import com.example.bezikafree.fragments.OtherFragment;
import com.example.bezikafree.fragments.OverviewFragment;
import com.example.bezikafree.fragments.PreventBitesFragment;
import com.example.bezikafree.fragments.PreventionControlFragment;
import com.example.bezikafree.fragments.PreventionSafeSexFragment;
import com.example.bezikafree.fragments.PreventionTravelFragment;
import com.example.bezikafree.fragments.SymptomsFragment;
import com.example.bezikafree.fragments.TransmissionFragment;
import com.example.bezikafree.fragments.TreatmentCureFragment;
import com.example.bezikafree.fragments.TreatmentDoctorFragment;

public class MainActivity extends AppCompatActivity {

    MapFragment mapFragment;
    NewsFragment newsFragment;
    OverviewFragment overviewFragment;
    TransmissionFragment transmissionFragment;
    SymptomsFragment symptomsFragment;
    EffectsFragment effectsFragment;
    PreventBitesFragment preventBitesFragment;
    PreventionControlFragment preventionControlFragment;
    PreventionSafeSexFragment preventionSafeSexFragment;
    PreventionTravelFragment preventionTravelFragment;
    TreatmentCureFragment treatmentCureFragment;
    TreatmentDoctorFragment treatmentDoctorFragment;
    OtherFragment otherFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView titleTV;
    Toolbar toolbar;
    private DrawerLayout drawer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout_id);
        titleTV = (TextView)findViewById(R.id.title_text_id);
        navigationView = (NavigationView)findViewById(R.id.nvView_id);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);

        initializeFragments();
        setUpDrawerContent(navigationView);
        initFragmentManager();

        setActionBarDrawer();


        if(savedInstanceState == null) { openScreen(); }
    }

    private void initFragmentManager(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    private void openScreen(){
        fragmentTransaction.replace(R.id.fragment_container_id, mapFragment);
        toolbar.setTitle("Zika Outbreak Map");
        fragmentTransaction.commit();
    }

    private void initializeFragments(){
        mapFragment = new MapFragment();
        newsFragment = new NewsFragment();
        overviewFragment = new OverviewFragment();
        transmissionFragment = new TransmissionFragment();
        symptomsFragment = new SymptomsFragment();
        effectsFragment = new EffectsFragment();
        preventBitesFragment = new PreventBitesFragment();
        preventionControlFragment = new PreventionControlFragment();
        preventionSafeSexFragment = new PreventionSafeSexFragment();
        preventionTravelFragment = new PreventionTravelFragment();
        treatmentCureFragment = new TreatmentCureFragment();
        treatmentDoctorFragment = new TreatmentDoctorFragment();
        otherFragment = new OtherFragment();
    }

    private void setUpDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    public void selectDrawerItem(MenuItem menuItem){
        initFragmentManager();
        fragmentTransaction.addToBackStack(null);
        switch (menuItem.getItemId()){
            case R.id.drawer_map_id:
                fragmentTransaction.replace(R.id.fragment_container_id, mapFragment);
                //titleTV.setText("Zika Outbreak Map");
                break;
            case R.id.drawer_news_id:
                fragmentTransaction.replace(R.id.fragment_container_id, newsFragment);
                //titleTV.setText("Recent News");
                break;
            case R.id.drawer_overview_id:
                fragmentTransaction.replace(R.id.fragment_container_id, overviewFragment);
                //titleTV.setText("Overview");
                break;
            case R.id.drawer_transmission_id:
                fragmentTransaction.replace(R.id.fragment_container_id, transmissionFragment);
                //titleTV.setText("Transmission");
                break;
            case R.id.drawer_symptoms_id:
                fragmentTransaction.replace(R.id.fragment_container_id, symptomsFragment);
                //titleTV.setText("Symptoms");
                break;
            case R.id.drawer_effects_id:
                fragmentTransaction.replace(R.id.fragment_container_id, effectsFragment);
                //titleTV.setText("Effects");
                break;
            case R.id.drawer_prevention_bites_id:
                fragmentTransaction.replace(R.id.fragment_container_id, preventBitesFragment);
                //titleTV.setText("Prevent Bites");
                break;
            case R.id.drawer_prevention_control_id:
                fragmentTransaction.replace(R.id.fragment_container_id, preventionControlFragment);
                //titleTV.setText("Control/Limit Mosquitos");
                break;
            case R.id.drawer_prevention_safesex_id:
                fragmentTransaction.replace(R.id.fragment_container_id, preventionSafeSexFragment);
                //titleTV.setText("Safe Sex");
                break;
            case R.id.drawer_prevention_travel_id:
                fragmentTransaction.replace(R.id.fragment_container_id, preventionTravelFragment);
                //titleTV.setText("Travel Safely");
                break;
            case R.id.drawer_treatment_cure_id:
                fragmentTransaction.replace(R.id.fragment_container_id, treatmentCureFragment);
                //titleTV.setText("Cure/Treat Symptoms");
                break;
            case R.id.drawer_doctor_id:
                fragmentTransaction.replace(R.id.fragment_container_id, treatmentDoctorFragment);
                //titleTV.setText("Get Tested");
                break;
            case R.id.drawer_other_id:
                fragmentTransaction.replace(R.id.fragment_container_id, otherFragment);
                //titleTV.setText("Other");
                break;
            default: break;
        }
        fragmentTransaction.commit();
        menuItem.setChecked(true);
        toolbar.setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }


    private void setActionBarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout_id);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
