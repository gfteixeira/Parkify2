package com.parkify;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);

                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.order:
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.my_orders:
                                Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.sign_out:
                                Toast toast = Toast.makeText(getApplicationContext(), "User not Logged", Toast.LENGTH_LONG);
                                toast.show();
                                break;

                        }
                        return true;
                    }});
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

    public void sendMessage(View view) {
        Intent intent = new Intent(this, SignActivity.class);
        startActivity(intent);
    }

    public void sendMessageLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
