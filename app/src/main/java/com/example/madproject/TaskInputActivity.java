package com.example.madproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TaskInputActivity extends AppCompatActivity {

    TextView selectDateTimeTextView;
    TextView selectedDateTimeTextView;
    DatePicker datePicker;
    TimePicker timePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_input);

        selectDateTimeTextView = findViewById(R.id.selectDateTime);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        selectedDateTimeTextView = findViewById(R.id.selectedDateTimeTextView);

        selectDateTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerDialog();
            }
        });
    }

    private void showDateTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();

        // Date Picker Dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(
                                TaskInputActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                        calendar.set(Calendar.MINUTE, minute);

                                        // Format the selected date and time
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
                                        //String selectedDateTime = sdf.format(calendar.getTime());
                                        String selectedDateTime = "Date: " + dayOfMonth+ "/" +(monthOfYear+1)+ "/" +year+ "  Time: " +(sdf.format(calendar.getTime()));
                                        selectedDateTimeTextView.setText(selectedDateTime);
                                        selectedDateTimeTextView.setVisibility(View.VISIBLE);
                                    }
                                },
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                false // 24-hour format
                        );

                        timePickerDialog.show();
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }
}
