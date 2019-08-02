package com.example.reza.honarjo.Controller.insuranceRecyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reza.honarjo.Model.alarm.DBAlarm;
import com.example.reza.honarjo.R;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.example.reza.honarjo.Controller.timeConverter.TimeConverter.getPersianDashedTime;

public class InsuranceRecyclerAdapter extends RecyclerView.Adapter<InsuranceRecyclerAdapter.LocalViewHolder> {

    private final OnInsuranceClickListener listener;

    public interface OnInsuranceClickListener {
        void onItemClick(DBAlarm item);
    }

    private final LayoutInflater mInflater;
    private List<DBAlarm> insurances = Collections.emptyList();

    public InsuranceRecyclerAdapter(Context context, OnInsuranceClickListener mListener) {
        mInflater = LayoutInflater.from(context);
        this.listener = mListener;
    }

    @NonNull
    @Override
    public InsuranceRecyclerAdapter.LocalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recycler_row_insurance, viewGroup, false);
        return new LocalViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InsuranceRecyclerAdapter.LocalViewHolder localViewHolder, int i) {
        localViewHolder.bind(insurances.get(i), listener);
        DBAlarm current = insurances.get(i);
        if (current.getMyDate() == null)
            localViewHolder.expireDay.setText("ثبت نشده");
        else{
            if (current.getMyDate().before(new Date()))
                localViewHolder.expireDay.setText("منقضی شده");
            else
                localViewHolder.expireDay.setText(getPersianDashedTime(current.getMyDate()));
        }
        localViewHolder.userCount.setText(String.valueOf(current.getUsers().size()));
    }

    public void setInsurances(List<DBAlarm> users) {
        insurances = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return insurances.size();
    }

    class LocalViewHolder extends RecyclerView.ViewHolder {
        private final TextView userCount;
        private final TextView expireDay;

        LocalViewHolder(@NonNull View itemView) {
            super(itemView);
            userCount = itemView.findViewById(R.id.insurance_user_count_in_row);
            expireDay = itemView.findViewById(R.id.insurance_expire_day_in_row);
        }

        void bind(DBAlarm dbAlarm, InsuranceRecyclerAdapter.OnInsuranceClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(dbAlarm));
        }
    }
}
