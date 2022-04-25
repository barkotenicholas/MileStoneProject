package com.example.gbv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.example.gbv.databinding.ActivityReportBinding;
import com.example.gbv.models.Reports;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Report extends AppCompatActivity {

    ActivityReportBinding binding;
    final Calendar myCalendar = Calendar.getInstance();



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityReportBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
             updateLabel();
        };

        binding.date.setEndIconOnClickListener(view -> new DatePickerDialog(Report.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        binding.report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collectData();
            }
        });

    }

    private void collectData() {

        String name = binding.username.getText().toString();
        String idno = binding.idnumber.getText().toString();
        String date = binding.pickdate.getText().toString();
        String location = binding.local.getText().toString();
        String desc = binding.descr.getText().toString();

        Reports report = new Reports(name,idno,date,location,desc);
        Log.d("TAG", "collectData: "+name+" "+idno+""+date+""+location+""+desc);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.pickdate.setText(dateFormat.format(myCalendar.getTime()));
    }
}