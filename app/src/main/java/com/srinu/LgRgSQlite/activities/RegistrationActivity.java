package com.srinu.LgRgSQlite.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.srinu.LgRgSQlite.R;
import com.srinu.LgRgSQlite.models.User;
import com.srinu.LgRgSQlite.sql.DatabaseHelper;

/**
 * Created by ConquerorsTechnologies on 22/12/17.
 */

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity = RegistrationActivity.this;
    private NestedScrollView nestedScrollView;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputLayoutConfirmPassword;

    private User user;
    private DatabaseHelper databaseHelper;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputLayoutConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
    }
    private void initListeners(){
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }
    private void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    private void postDataToSQLite(){
        try{
            if(!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            databaseHelper.addUser(user);
            Snackbar.make(nestedScrollView, getString(R.string.success_message),Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        }else{
                Snackbar.make(nestedScrollView, getString(R.string.text_already_member),Snackbar.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputLayoutConfirmPassword.setText(null);
    }


}
