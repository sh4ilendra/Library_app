package com.example.dr.library_app;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class NavigationDrawer extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    //String email;
    //TextView emailTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

       // emailTV = (TextView) findViewById(R.id.nav_header_email);
        //email=getIntent().getStringExtra("email");
        //emailTV.setText("Welcome !!! "+email);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,new HomeFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home Fragment");

        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home_id:
                        fragmentTransaction= getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new HomeFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Home Fragment");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;
                    case R.id.pages_id:
                        fragmentTransaction= getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new PagesFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Pages Fragment");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;
                    case R.id.people_id:
                        fragmentTransaction= getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new PeopleFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("People Fragment");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;


                }




                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
