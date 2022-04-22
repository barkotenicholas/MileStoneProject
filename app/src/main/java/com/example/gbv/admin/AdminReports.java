package com.example.gbv.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.gbv.R;
import com.example.gbv.databinding.ActivityAdminReportsBinding;
import com.example.gbv.models.Reports;

import java.util.ArrayList;

public class AdminReports extends AppCompatActivity {

    ActivityAdminReportsBinding binding;
    ReportAdapter reportAdapter;
    ArrayList<Reports> reports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminReportsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reports = new ArrayList<>();

        reports.add(new Reports("Diana","12/5/2018","Nakuru","I was assulted by so and so","325412"));

        reportAdapter = new ReportAdapter(reports);


        addDataToRecyler();

    }

    private void addDataToRecyler() {


        binding.recylcer.setAdapter(reportAdapter);
        binding.recylcer.setLayoutManager(new LinearLayoutManager(AdminReports.this));

    }
}