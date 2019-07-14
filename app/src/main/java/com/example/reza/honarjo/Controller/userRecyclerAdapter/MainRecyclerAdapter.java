package com.example.reza.honarjo.Controller.userRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reza.honarjo.Model.users.User;
import com.example.reza.honarjo.R;

import java.util.List;

public class MainRecyclerAdapter{}
//extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>  {
//
//    public MainRecyclerAdapter() { }
//    @NonNull
//    @Override
//    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View contactView = inflater.inflate(R.layout.today_recycler_row, parent, false);
//        return new ViewHolder(contactView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MainRecyclerAdapter.ViewHolder viewHolder, int i) {
//        User insurance = insurances.get(i);
//        TextView expire = viewHolder.expire;
//        TextView name = viewHolder.name;
//        name.setText(String.format("%s %s", insurance.getName(), insurance.getFamily()));
//        //PersianDate pdate = new PersianDate(insurance.getExpireDay().getTime());
//        expire.setText(insurance.getExpireDay().toString());
//    }
//
//    @Override
//    public int getItemCount() {
//        return insurances.size();
//    }
//    class ViewHolder extends RecyclerView.ViewHolder{
//
//        TextView name;
//        TextView expire;
//        ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            name = itemView.findViewById(R.id.today_text_name);
//            expire = itemView.findViewById(R.id.today_text_expire);
//        }
//    }
//}
