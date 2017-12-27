package com.srinu.LgRgSQlite.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.srinu.LgRgSQlite.R;
import com.srinu.LgRgSQlite.adapters.UserRecyclerAdapter;
import com.srinu.LgRgSQlite.models.User;
import com.srinu.LgRgSQlite.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ConquerorsTechnologies on 22/12/17.
 */

public class UsersListActivity extends AppCompatActivity{
    private AppCompatActivity activity = UsersListActivity.this;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UserRecyclerAdapter userRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    private AppCompatTextView appCompatTextViewName;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_list_activity);
        getSupportActionBar().hide();
        initViews();
        initObjects();
    }

    private void initViews(){
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
    }
    private void initObjects(){
        listUsers = new ArrayList<>();
        appCompatTextViewName = (AppCompatTextView) findViewById(R.id.appCompatTextViewName);
        userRecyclerAdapter = new UserRecyclerAdapter(listUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(userRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        appCompatTextViewName.setText(emailFromIntent);

        getDataFromSQLite();
    }

    public void getDataFromSQLite(){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {


                   listUsers.clear();
                   listUsers.addAll(databaseHelper.getAllUser());


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                userRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();

    }
}
