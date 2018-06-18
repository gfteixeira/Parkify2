package com.parkify;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class OrderDateTimeExit extends AppCompatActivity {

    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private TextView textExit;
    private Button btn_date_exit;
    private Button btn_time_exit;
    public Button btn_confirm;
    public static AppDatabase myAppDB;
    private Session session;
    private int position;
    private DrawerLayout mDrawerLayout;
    TextView textViewEmail;
    View mHeaderView;
    private int hourBegin;
    private String dateBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_date_time_exit);
        ListView listView = (ListView) findViewById(R.id.listOrders);


        myAppDB = AppDatabase.getAppDatabase(getApplicationContext());

        Intent intent2 = getIntent();


        position = intent2.getExtras().getInt("position");
        hourBegin = intent2.getExtras().getInt("hour");
        dateBegin = intent2.getExtras().getString("date");

        session = new Session(com.parkify.OrderDateTimeExit.this);

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
                                Intent intent = new Intent(com.parkify.OrderDateTimeExit.this, OrderActivity.class);
                                intent.putExtra("intentSource", "OrderDateTimeExit");
                                startActivity(intent);
                                break;
                            case R.id.my_orders:
                                Intent intent3 = new Intent(com.parkify.OrderDateTimeExit.this, OrdersActivity.class);
                                startActivity(intent3);
                                break;
                            case R.id.sign_out:
                                session.deleteUsername();
                                Intent intent2 = new Intent(com.parkify.OrderDateTimeExit.this, MainActivity.class);
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


        textExit = (TextView) findViewById(R.id.txt_TextDateTimeExit);

        textExit.setText(dateBegin);

        btn_date_exit = (Button) findViewById(R.id.btnDatePickerExit);
        btn_date_exit.setVisibility(View.GONE);
        btn_time_exit = (Button) findViewById(R.id.btnTimePickerExit);
        btn_confirm = (Button) findViewById(R.id.btnConfirm);


/*
        btn_date_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDateExit();
            }
        });
*/

        btn_time_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTimeExit();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });

        updateTextLabel();
    }

/*    private void updateDateExit() {
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }*/

    private void updateTimeExit() {
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

/*    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabel();
        }
    };*/

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

    @Override
    public void onBackPressed() {

        finish();
        Intent intent3 = new Intent(this, OrderActivity.class);
        intent3.putExtra("intentSource", "Back Press");
        startActivity(intent3);
    }

    private void updateTextLabel() {
        textExit.setText(formatDateTime.format(dateTime.getTime()));
    }

    public void confirmOrder() {
        String m = textExit.getText().toString();
        String[] hours = m.split(" ");
        String[] hour = hours[1].split(":");
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        String d = df.format("dd-MM-yyyy", Calendar.getInstance().getTime()).toString();

        /*Order order = new Order(session.getusename(),position, hours[0], hourBegin, Integer.parseInt(hour[0]));*/
        String actualDate = "'" + dateBegin + "'";

        Order order = new Order(session.getusename(), position, actualDate, hourBegin, Integer.parseInt(hour[0]));

        List<Order> listOrders = myAppDB.orderDao().findOrdersByPlaceAndTime(position, actualDate, hourBegin, Integer.parseInt(hour[0]));
        if (listOrders.size() == 0) {
            myAppDB.orderDao().insert(order);
            Intent intent = new Intent(this, OrdersActivity.class);
            startActivity(intent);
        } else {
            Intent intentElse = new Intent(this, OrderActivity.class);
            intentElse.putExtra("intentSource", "TimeExit");
            startActivity(intentElse);
        }

    }
}
