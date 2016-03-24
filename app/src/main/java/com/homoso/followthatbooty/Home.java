package com.homoso.followthatbooty;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Home extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    CoordinatorLayout layout;
    ViewPager viewPager;
    HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        setToolbar();
        setNavigationDrawer();

        viewPager = (ViewPager) findViewById(R.id.vpHome);
        setupViewPager(viewPager);
    }

    /*
    **Initializations.
    * */

    private void init(){
        layout= (CoordinatorLayout) findViewById(R.id.clHome);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new HomeAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentStory());
        viewPager.setAdapter(adapter);

    }

    private void setToolbar(){
        toolbar = (Toolbar) findViewById(R.id.tbHome);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
    }

    /*
    **Display Assets..
    * */

    private void showSnackBar(String message){
        if(message!=null)
            Snackbar.make(layout, message, Snackbar.LENGTH_SHORT).show();
    }

    /*
    **Overriding events.
    * */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_search:
                showSnackBar("Search");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNavigationDrawer(){
        navigationView = (NavigationView) findViewById(R.id.nvHome);
        navigationView.setItemIconTintList(null);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()){
                    case R.id.action_home:
                        adapter = new HomeAdapter(getSupportFragmentManager());
                        adapter.addFragment(new FragmentStory());
                        viewPager.setAdapter(adapter);
                        toolbar.setTitle("Home");
                        break;
                }
                return true;
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.dlHome);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer, R.string.close_drawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


}