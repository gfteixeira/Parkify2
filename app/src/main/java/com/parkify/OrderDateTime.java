package com.parkify;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class OrderDateTime extends AppCompatActivity {

    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private TextView text;
    private Button btn_date;
    private Button btn_time;
    public Button btn_next;
    private int position;
    private DrawerLayout mDrawerLayout;
    TextView textViewEmail;
    View mHeaderView;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_date_time);

        Intent intent2 = getIntent();
        position = intent2.getExtras().getInt("position");

        session = new Session(com.parkify.OrderDateTime.this);


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
                                Intent intent = new Intent(com.parkify.OrderDateTime.this, OrderActivity.class);
                                intent.putExtra("intentSource", "OrderDateTime");
                                startActivity(intent);
                                break;
                            case R.id.my_orders:
                                Intent intent3 = new Intent(com.parkify.OrderDateTime.this, OrdersActivity.class);
                                startActivity(intent3);
                                break;
                            case R.id.sign_out:
                                session.deleteUsername();
                                Intent intent2 = new Intent(com.parkify.OrderDateTime.this, MainActivity.class);
                                startActivity(intent2);
                                break;

                        }

                        return true;
                    }
                });

        mHeaderView = navigationView.getHeaderView(0);


        Intent intent = getIntent();

        textViewEmail = (TextView) mHeaderView.findViewById(R.id.loggedUsername);

        textViewEmail.setText(session.getusename());

        text = (TextView) findViewById(R.id.txt_TextDateTimeExit);
        btn_date = (Button) findViewById(R.id.btnDatePickerExit);
        btn_time = (Button) findViewById(R.id.btnTimePickerExit);
        btn_next = (Button) findViewById(R.id.btnNext);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextStep();
            }
        });

        updateTextLabel();
    }

    private void updateDate() {
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTime() {
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            updateTextLabel();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateTextLabel() {
        text.setText(formatDateTime.format(dateTime.getTime()));
    }

    public void nextStep() {
        String m = text.getText().toString();
        String[] hours = m.split(" ");
        String[] hour = hours[1].split(":");
        Intent intent = new Intent(this, OrderDateTimeExit.class);
        intent.putExtra("position", position);
        intent.putExtra("hour", Integer.parseInt(hour[0]));
        intent.putExtra("date",hours[0] );
        startActivity(intent);
    }

}
