package com.parkify;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateOrderActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dateorder);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(
                DateOrderActivity.this, DateOrderActivity.this, year, month  , day);

        ((Button) findViewById(R.id.picDate))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePickerDialog.show();
                    }
                });

    }

    @Override
    public void onDateSet(DatePicker view, int yearS, int monthS, int dayOfMonth) {
        day = dayOfMonth;
        month = monthS;
        year = yearS;
        Button datePickerButton = ((Button) findViewById(R.id.picDate));
        datePickerButton.setText(monthS + " / " + (monthS + 1) + " / "
                + yearS);
    }

/*        public void datePicker(View view){

            DatePickerFragment fragment = new DatePickerFragment();
            DialogFragment f = new DialogFragment();
            f.show(getSupportFragmentManager(),"s");
        }

        *//**
         * To set date on TextView
         * @param calendar
         *//*
        private void setDate(final Calendar calendar) {
            final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

            ((TextView) findViewById(R.id.showDate))
                    .setText(dateFormat.format(calendar.getTime()));

        }

        *//**
         * To receive a callback when the user sets the date.
         * @param view
         * @param year
         * @param month
         * @param day
         *//*

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar cal = new GregorianCalendar(year, month, day);
            setDate(cal);
        }

        *//**
         * Create a DatePickerFragment class that extends DialogFragment.
         * Define the onCreateDialog() method to return an instance of DatePickerDialog
         *//*
        public static class DatePickerFragment extends DialogFragment {

            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                return new DatePickerDialog(getActivity(),
                        (DatePickerDialog.OnDateSetListener)
                                getActivity(), year, month, day);
            }


        }*/


}