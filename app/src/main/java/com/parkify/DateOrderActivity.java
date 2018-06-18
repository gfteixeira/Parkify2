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

public class DateOrderActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

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
                DateOrderActivity.this, DateOrderActivity.this, year, month, day);

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


}