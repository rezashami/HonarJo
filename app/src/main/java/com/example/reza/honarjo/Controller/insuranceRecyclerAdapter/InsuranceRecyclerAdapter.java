package com.example.reza.honarjo.Controller.insuranceRecyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reza.honarjo.Model.Insurance;
import com.example.reza.honarjo.R;

import java.util.Collections;
import java.util.List;

import static java.lang.String.valueOf;

public class InsuranceRecyclerAdapter extends RecyclerView.Adapter<InsuranceRecyclerAdapter.LocalViewHolder> {

    private final OnInsuranceClickListener listener;

    public interface OnInsuranceClickListener {
        void onItemClick(Insurance item);
    }

    private final LayoutInflater mInflater;
    private List<Insurance> insurances = Collections.emptyList();

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

    private String toPersianNumber(String text) {
        String[] persianNumbers = new String[]{"۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹"};
        if (text.length() == 0) {
            return "";
        }
        StringBuilder out = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if ('0' <= c && c <= '9') {
                int number = Integer.parseInt(valueOf(c));
                out.append(persianNumbers[number]);
            } else {
                out.append(c);
            }
        }

        return out.toString();
    }

    private String getDayString(List<Integer> input) {
        if (input == null ||input.size()==0)
            return "ثبت نشده";
        String month = input.get(1) < 10 ? "0" + input.get(1) : input.get(1).toString();
        String day = input.get(0) < 10 ? "0" + input.get(0) : input.get(0).toString();
        return toPersianNumber(input.get(2).toString() + " - " + month + " - " + day);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InsuranceRecyclerAdapter.LocalViewHolder localViewHolder, int i) {
        localViewHolder.bind(insurances.get(i), listener);
        Insurance current = insurances.get(i);
        localViewHolder.name_family.setText(String.valueOf(current.getName()) + " " + String.valueOf(current.getFamily()));
        localViewHolder.expireDay.setText(getDayString(current.getExpireDay()));
    }

    public void setInsurances(List<Insurance> users) {
        insurances = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return insurances.size();
    }

    class LocalViewHolder extends RecyclerView.ViewHolder {
        private final TextView name_family;
        private final TextView expireDay;

        LocalViewHolder(@NonNull View itemView) {
            super(itemView);
            name_family = itemView.findViewById(R.id.insurance_user_name_in_row);
            expireDay = itemView.findViewById(R.id.insurance_expire_day_in_row);
            ;
        }

        void bind(Insurance dbUsers, InsuranceRecyclerAdapter.OnInsuranceClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(dbUsers));
        }
    }
}
