package com.example.mydatabookindividual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    String currentTitle;
    ActionBar ab;
    public  ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setNavigationDrawer();
        this.getSharedPreferences("hi",0);

    }

    public void setNavigationDrawer(){
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ab = getSupportActionBar();
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        ab.setDisplayHomeAsUpEnabled(true);
        // Set the list's click listener
       navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected( MenuItem item) {
               Fragment frag = null;
               int itemId = item.getItemId();
               if(itemId == R.id.bio){
                   frag = new BioFragment();
               }
               else if(itemId == R.id.vaccination){
                   frag = new VaccinationFragment();
               }
               else if(itemId == R.id.vaccination){
                   frag = new VaccinationFragment();
               }
               else if(itemId == R.id.anniversary){
                   frag = new AnniversaryFragment();
               }
               else if(itemId == R.id.about){
                  Intent i = new Intent(MainActivity.this,AboutActivity.class);
                  startActivity(i);
               }
               if (frag != null){
                   FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                   transaction.replace(R.id.content_frame,frag);
                   transaction.commit();
                   drawerLayout.closeDrawers();
                   return  true;
               }
               return false;
           }
       });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync toggle state so the indicator is shown properly.
        //  Have to call in onPostCreate()
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}