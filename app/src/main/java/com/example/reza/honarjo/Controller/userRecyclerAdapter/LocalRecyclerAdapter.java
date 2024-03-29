package com.example.reza.honarjo.Controller.userRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reza.honarjo.Model.users.ShowingUser;
import com.example.reza.honarjo.R;

import java.util.Collections;
import java.util.List;

public class LocalRecyclerAdapter extends RecyclerView.Adapter<LocalRecyclerAdapter.LocalViewHolder> {


    private final OnItemClickListener listener;

    private final LayoutInflater mInflater;
    private List<ShowingUser> dbUsers = Collections.emptyList(); // Cached copy of words

    public LocalRecyclerAdapter(Context context, OnItemClickListener listener) {
        this.listener = listener;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_row_user, parent, false);
        return new LocalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalViewHolder holder, int position) {
        holder.bind(dbUsers.get(position), listener);
        ShowingUser current = dbUsers.get(position);
        Log.e("Showing user", String.valueOf(current.get_id()));
        holder.username.setText(String.format("- %s %s", String.valueOf(current.getName()), String.valueOf(current.getFamily())));
    }

    public void setDbUsers(List<ShowingUser> users) {
        dbUsers = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dbUsers.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ShowingUser item);
    }

    class LocalViewHolder extends RecyclerView.ViewHolder {
        private final TextView username;

        LocalViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username_view_in_row);

        }

        void bind(ShowingUser dbUsers, LocalRecyclerAdapter.OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(dbUsers));
        }
    }
}
