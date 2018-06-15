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

public class OrderActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    TextView textViewEmail;
    View mHeaderView;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        /*mHeaderView =  mNavigationView.getHeaderView(0);


        Intent intent = getIntent();

        textViewEmail = (TextView) mHeaderView.findViewById(R.id.loggedUsername);


        textViewEmail.setText(intent.getExtras().getString("mail"));*/

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
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.nav_view_logged);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
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

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here
                        }

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }});

        mHeaderView =  navigationView.getHeaderView(0);


        Intent intent = getIntent();

        textViewEmail = (TextView) mHeaderView.findViewById(R.id.loggedUsername);


        textViewEmail.setText(intent.getExtras().getString("mail"));


        populateListView(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView textView = (TextView) view;
                String msg = "You clicked # " + position
                        + ", which is string: " + textView.getText().toString();
               /* Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();*/
                /*timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
                int hour = timePicker1.getCurrentHour();
                int min = timePicker1.getCurrentMinute();*/
                sendMessage();
            }
        });

        session = new Session(OrderActivity.this);
/*
       String a= session.getusename();
*/

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


    private void populateListView(ListView listView){
        String[] parkingNumbers = {"1","2","3","4","5","6","7","8"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listview_parking_numbers,parkingNumbers);
        listView.setAdapter(adapter);
    }


    public void sendMessage() {
        Intent intent = new Intent(this, DateOrderActivity.class);
        startActivity(intent);
    }
}
