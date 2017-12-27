package com.srinu.LgRgSQlite.adapters;

import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srinu.LgRgSQlite.R;
import com.srinu.LgRgSQlite.models.User;

import java.util.List;

/**
 * Created by ConquerorsTechnologies on 22/12/17.
 */

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>{
    private List<User> listUsers;

    public UserRecyclerAdapter(List<User> listUsers){
        this.listUsers = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_recycler,parent,false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewName.setText(listUsers.get(position).getName());
        holder.textViewEmail.setText(listUsers.get(position).getEmail());
        holder.textViewPassword.setText(listUsers.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;

        public UserViewHolder(View itemView) {
            super(itemView);
            textViewName  = (AppCompatTextView) itemView.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) itemView.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView) itemView.findViewById(R.id.textViewPassword);
        }
    }
}
