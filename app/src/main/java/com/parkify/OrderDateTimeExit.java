package com.parkify;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_date_time_exit);
        ListView listView = (ListView) findViewById(R.id.listOrders);


        myAppDB = AppDatabase.getAppDatabase(getApplicationContext());

        Intent intent2 = getIntent();


        position = intent2.getExtras().getInt("position");

        session = new Session(com.parkify.OrderDateTimeExit.this);



        textExit = (TextView)findViewById(R.id.txt_TextDateTimeExit);
        btn_date_exit = (Button)findViewById(R.id.btnDatePickerExit);
        btn_time_exit = (Button)findViewById(R.id.btnTimePickerExit);
        btn_confirm = (Button)findViewById(R.id.btnConfirm);

        btn_date_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDateExit();
            }
        });

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

    private void updateDateExit(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTimeExit(){
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE),true).show();
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

    private void updateTextLabel(){
        textExit.setText(formatDateTime.format(dateTime.getTime()));
    }

    public void confirmOrder(){
        Order order = new Order(session.getusename(),position, Calendar.getInstance().getTime(), GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY), GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY), "2");

        myAppDB.orderDao().insert(order);
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }
}
