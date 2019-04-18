package com.example.reza.honarjo.Controller.todayRecycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reza.honarjo.Model.Insurance;
import com.example.reza.honarjo.R;

import java.util.List;

import saman.zamani.persiandate.PersianDate;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {


    private List<Insurance> insurances;

    public RecyclerAdapter(List<Insurance> mInsurances) {
        insurances = mInsurances;
    }
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.today_recycler_row, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder viewHolder, int i) {
        Insurance insurance = insurances.get(i);
        TextView expire = viewHolder.expire;
        TextView name = viewHolder.name;
        expire.setText(insurance.getName() + " "+insurance.getFamily());
        PersianDate pdate = new PersianDate(insurance.getExpireDay().getTime());
        name.setText(pdate.toString());
    }

    @Override
    public int getItemCount() {
        return insurances.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView expire;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.today_text_name);
            expire = itemView.findViewById(R.id.today_text_expire);
        }
    }
}
