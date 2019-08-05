package com.example.reza.honarjo.Controller.loggerRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reza.honarjo.Model.logger.DBLogger;
import com.example.reza.honarjo.R;

import java.util.Collections;
import java.util.List;

public class LoggerRecyclerAdapter extends RecyclerView.Adapter<LoggerRecyclerAdapter.LoggerViewHolder> {

    private final OnItemClickListener listener;
    private final LayoutInflater mInflater;
    private List<DBLogger> reports = Collections.emptyList();


    public LoggerRecyclerAdapter(Context context, OnItemClickListener listener) {
        this.listener = listener;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setLogs(List<DBLogger> logs) {
        reports = logs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoggerRecyclerAdapter.LoggerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recycler_row_logger, viewGroup, false);
        return new LoggerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LoggerRecyclerAdapter.LoggerViewHolder holder, int i) {
        holder.bind(reports.get(i), listener);
        DBLogger current = reports.get(i);
        holder.brief.setText(current.getHeader());
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public interface OnItemClickListener {
        void onItemClick(DBLogger item);
    }

    class LoggerViewHolder extends RecyclerView.ViewHolder {
        private final TextView brief;

        LoggerViewHolder(@NonNull View itemView) {
            super(itemView);
            brief = itemView.findViewById(R.id.logger_show_brief);
        }

        void bind(DBLogger logger, OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(logger));
        }
    }
}
