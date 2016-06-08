package com.example.dr.library_app.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dr.library_app.AboutUs;
import com.example.dr.library_app.AddToWishlist;
import com.example.dr.library_app.HelpCenter;
import com.example.dr.library_app.Home;
import com.example.dr.library_app.HomeFragment;
import com.example.dr.library_app.MemberList;
import com.example.dr.library_app.NewDisplayActivity;
import com.example.dr.library_app.PeopleFragment;
import com.example.dr.library_app.R;
import com.example.dr.library_app.TermsAndConditions;

public class NavigationDrawer extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    String email,name;
    TextView emailTV,userTV;
    public static final String BOOK_DETAIL_KEY = "book";
    private ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_admin);
        progress = (ProgressBar) findViewById(R.id.progress);


        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.admin_drawer_layout);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.admin_container,new Home());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");

        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_id_admin:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.admin_container, new Home());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Home");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;
                    case R.id.pages_id_admin:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.admin_container, new AddToWishlist());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Add to Wishlist");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;

                    case R.id.people_id_admin:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.admin_container, new MemberList());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Members List");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;
                    case R.id.account_id_admin:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.admin_container, new Account());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Account Details");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;
                    case R.id.aboutus_id_admin:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.admin_container, new AboutUs());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("About Us");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;
                    case R.id.logout_id_admin:
                        logout();
                        break;
                    case R.id.terms_id_admin:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.admin_container, new TermsAndConditions());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Terms And Conditions");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.help_id_admin:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.admin_container, new HelpCenter());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Help Center");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
        View header=navigationView.getHeaderView(0);
        userTV = (TextView) header.findViewById(R.id.nav_header_text);
        name=getIntent().getStringExtra("name");
        userTV.setText("Welcome !!! " + name);
        emailTV = (TextView) header.findViewById(R.id.nav_header_email);
        email=getIntent().getStringExtra("email");
        emailTV.setText(email);

    }
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(getApplicationContext(), LibrarianUser.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        Toast.makeText(getApplicationContext(), "Logged out successfully !!!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Fetch the data remotely
                Intent intent = new Intent(NavigationDrawer.this,NewDisplayActivity.class);
                startActivity(intent);
                NewDisplayActivity.fetchBooks(query);
                // Reset SearchView
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);
                searchItem.collapseActionView();

                // Set activity title to search query
                NavigationDrawer.this.setTitle(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuLogout) {
            logout();
        }

        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(getApplicationContext(), LibrarianUser.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        Toast.makeText(getApplicationContext(), "Logged out successfully !!!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}