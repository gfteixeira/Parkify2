package com.parkify;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    TextView textViewEmail;
    View mHeaderView;
    private Session session;
    public static AppDatabase myAppDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_orders);

        myAppDB = AppDatabase.getAppDatabase(getApplicationContext());
        ListView listView = (ListView) findViewById(R.id.listOrders);


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
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.order:
                                Intent intent = new Intent(com.parkify.OrdersActivity.this, OrderActivity.class);
                                intent.putExtra("intentSource", "Orders");
                                startActivity(intent);
                                break;
                            case R.id.my_orders:
                                mDrawerLayout.closeDrawers();
                                break;
                            case R.id.sign_out:
                                session.deleteUsername();
                                Intent intent2 = new Intent(com.parkify.OrdersActivity.this, MainActivity.class);
                                startActivity(intent2);
                                break;

                        }


                        return true;
                    }
                });

        mHeaderView = navigationView.getHeaderView(0);


        Intent intent = getIntent();

        textViewEmail = (TextView) mHeaderView.findViewById(R.id.loggedUsername);

        session = new Session(com.parkify.OrdersActivity.this);


        textViewEmail.setText(session.getusename());

        populateListView(listView);


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
        List<Order> a = myAppDB.orderDao().findAllByName(session.getusename());

        Order[] orders = a.toArray(new Order[a.size()]);
        ;

        String[] b = new String[a.size()];

        for (int i = 0; i < a.size(); i++) {
            b[i] = "Place: " + orders[i].getNumber() + " Date: " + orders[i].getDate() + " Hours of Order: " + orders[i].getBeginTime() + "-" + orders[i].getEndTime();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_parking_numbers, b);
        listView.setAdapter(adapter);
    }


}


