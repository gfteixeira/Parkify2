package com.parkify;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TimePicker;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    TextView textViewEmail;
    View mHeaderView;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ListView listView = (ListView) findViewById(R.id.listItems);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.nav_view_logged);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);

                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.order:
                                mDrawerLayout.closeDrawers();
                                break;
                            case R.id.my_orders:
                                Intent intent = new Intent(OrderActivity.this, OrdersActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.sign_out:
                                session.deleteUsername();
                                Intent intent2 = new Intent(OrderActivity.this, MainActivity.class);
                                startActivity(intent2);
                                break;
                        }


                        return true;
                    }
                });

        mHeaderView = navigationView.getHeaderView(0);

        session = new Session(OrderActivity.this);

        Intent intent2 = getIntent();
        String a = intent2.getExtras().getString("intentSource");


        if (a != null && a.equals("TimeExit")) {
            Toast.makeText(getApplicationContext(), "Order not possible. Choose a different initial hour or a different parking place.", Toast.LENGTH_LONG).show();
        }

        textViewEmail = (TextView) mHeaderView.findViewById(R.id.loggedUsername);

        textViewEmail.setText(session.getusename());


        populateListView(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view;
                sendMessage(position + 1);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void populateListView(ListView listView) {
        String[] parkingNumbers = {"Place 1", "Place 2", "Place 3", "Place 4", "Place 5", "Place 6", "Place 7", "Place 8"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_parking_numbers, parkingNumbers);
        listView.setAdapter(adapter);
    }


    public void sendMessage(int position) {
        Intent intent = new Intent(this, OrderDateTime.class);
        intent.putExtra("position", position);
        startActivity(intent);

    }
}
