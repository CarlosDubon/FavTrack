package com.bonusteam.favtrack.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bonusteam.favtrack.R;
import com.bonusteam.favtrack.utilities.SharedPreference;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.bonusteam.favtrack.adapters.MenuModel;
import com.bonusteam.favtrack.fragments.BooksFragment;
import com.bonusteam.favtrack.fragments.DietsFragment;
import com.bonusteam.favtrack.fragments.MoviesFragment;
import com.bonusteam.favtrack.fragments.RutinesFragment;
import com.bonusteam.favtrack.fragments.SeriesFragment;
import com.bonusteam.favtrack.fragments.SettingsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FavTrack extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    private List<MenuModel> headerList = new ArrayList<>();
    private HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    private String health_title, rutine_menu_title, diet_menu_title, entertainment_menu_title, books_menu_title, movies_menu_title,series_menu_title,settings_menu_title,logout_menu_title;
    private List<MenuModel> childModelsList;



    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_track);

        SharedPreference.init(getApplicationContext());
        if(SharedPreference.checkLogin()){
            finishAffinity();
            Log.d(TAG, "onCreate: No login");
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareMenuData() {

        get_menu_titles();
        MenuModel menuModel = new MenuModel(health_title, true, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel(rutine_menu_title, false, false);
        childModelsList.add(childModel);
        childModel = new MenuModel(diet_menu_title, false, false);
        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        menuModel = new MenuModel(entertainment_menu_title +"", true, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        childModelsList = new ArrayList<>();
        childModel = new MenuModel(books_menu_title, false, false);
        childModelsList.add(childModel);
        childModel = new MenuModel(series_menu_title, false, false);
        childModelsList.add(childModel);
        childModel = new MenuModel(movies_menu_title, false, false);
        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        menuModel = new MenuModel(settings_menu_title, false, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        menuModel = new MenuModel(logout_menu_title, false, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }




//        menuModel = new MenuModel(categorias_menu_title, true, true); //Menu of Java Tutorials
//        headerList.add(menuModel);
//        childModelsList = new ArrayList<>();
//        //todo ESTE LIVEDATA ES PARA OBTENER LAS CATEGORIAS DE LA API PARA LLENAR LOS HIJOS DEL PADRE "categorias" si no se entiende preguntarle a Miguel Aviles
//        listLiveData = viewModel.getCategories();
//        listLiveData.observe(this, new Observer<List<String>>() {
//            @Override
//            public void onChanged(@Nullable List<String> strings) {
//                childModelsList.clear();
//                for (String c : strings) {
//                    MenuModel childModel = new MenuModel(c, false, false);
//                    childModelsList.add(childModel);
//                }
//            }
//        });
//        if (menuModel.hasChildren) {
//            childList.put(menuModel, childModelsList);
//        }


    }

    //Setting adapter for the expandable list and making the visuals appear, + adding what would happen when selected
    private void populateExpandableList() {

        expandableListAdapter = new com.bonusteam.favtrack.adapters.ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        //Click listener for parent option
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                get_menu_titles();
                Fragment fragment;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {


                        if (headerList.get(groupPosition).menuName.equals(settings_menu_title)) {
//                            fragment = new NewPasword();
//                            transaction.replace(R.id.content, fragment);
//                            transaction.addToBackStack(null);
//                            transaction.commit();
                        }


                        if (headerList.get(groupPosition).menuName.equals(logout_menu_title)) {
//                            fragment = new NewPasword();
//                            transaction.replace(R.id.content, fragment);
//                            transaction.addToBackStack(null);
//                            transaction.commit();
                        }

                        onBackPressed();
                    }
                }
                return false;
            }
        });
        //Click listener for any child option
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Fragment fragment = new Fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                if (childList.get(headerList.get(groupPosition)) != null) {
//                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
//                    String category = childList.get(headerList.get(groupPosition)).get(childPosition).menuName;
//                    if (listLiveData.getValue().contains(category)) {
//                        fragment = Games.newInstance(category);
//                        transaction.replace(R.id.content, fragment);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
//
//                    }
//                    onBackPressed();
//                }
                if (childList.get(headerList.get(groupPosition)) != null) {
                    if (childList.get(headerList.get(groupPosition)).get(childPosition).menuName.equals(rutine_menu_title)) {
                            fragment = new RutinesFragment();
                            transaction.replace(R.id.container, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                    }
                    if (childList.get(headerList.get(groupPosition)).get(childPosition).menuName.equals(diet_menu_title)) {
                            fragment = new DietsFragment();
                            transaction.replace(R.id.container, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                    }
                    if (childList.get(headerList.get(groupPosition)).get(childPosition).menuName.equals(books_menu_title)) {
                            fragment = new BooksFragment();
                            transaction.replace(R.id.container, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                    }
                    if (childList.get(headerList.get(groupPosition)).get(childPosition).menuName.equals(series_menu_title)) {
                            fragment = new SeriesFragment();
                            transaction.replace(R.id.container, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                    }
                    if (childList.get(headerList.get(groupPosition)).get(childPosition).menuName.equals(movies_menu_title)) {
                            fragment = new MoviesFragment();
                            transaction.replace(R.id.container, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                    }
                    onBackPressed();

                }
                return false;
            }
        });
    }
    public void get_menu_titles() {
        health_title =getString(R.string.health_menu_title);
        rutine_menu_title = getString(R.string.rutines_menu_title);
        diet_menu_title = getString(R.string.diets_menu_title);
        entertainment_menu_title =getString(R.string.entertainment_menu_title);
        books_menu_title = getString(R.string.books_menu_title);
        series_menu_title = getString(R.string.series_menu_title);
        movies_menu_title =getString(R.string.movies_menu_title);
        settings_menu_title=getString(R.string.settings_menu_title);
        logout_menu_title=getString(R.string.logout_menu_title);
    }
}

