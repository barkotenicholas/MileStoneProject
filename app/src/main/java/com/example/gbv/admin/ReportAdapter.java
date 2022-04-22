package com.example.gbv.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gbv.databinding.ItemReportBinding;
import com.example.gbv.models.Reports;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder>{

    ArrayList<Reports> arrayList;


    public ReportAdapter(ArrayList<Reports> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemReportBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reports reports = arrayList.get(position);
        holder.binding.courseinfo.setText(reports.getDate());
        holder.binding.notebody.setText(reports.getDesc());
        holder.binding.notetitle.setText(reports.getLocation());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ItemReportBinding binding;

        public ViewHolder(@NonNull ItemReportBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
