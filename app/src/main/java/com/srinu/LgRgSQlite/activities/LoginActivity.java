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
import android.widget.Toast;

import com.srinu.LgRgSQlite.R;
import com.srinu.LgRgSQlite.models.User;
import com.srinu.LgRgSQlite.sql.DatabaseHelper;

/**
 * Created by ConquerorsTechnologies on 22/12/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity = LoginActivity.this;

    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView appCompatTextViewLinkRegister;

    private NestedScrollView nestedScrollView;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private User user;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        appCompatTextViewLinkRegister = (AppCompatTextView) findViewById(R.id.appCompatTextViewLinkRegister);

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);


    }

    private void initListeners(){
        appCompatButtonLogin.setOnClickListener(this);
        appCompatTextViewLinkRegister.setOnClickListener(this);
    }
    private void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
    }

    public void onClick(View v){
          switch(v.getId()){
              case R.id.appCompatButtonLogin:
                  verifyFromSQLite();
                  /*Intent usersListIntent = new Intent(getApplicationContext(),UsersListActivity.class);
                  startActivity(usersListIntent);*/
                  break;
              case R.id.appCompatTextViewLinkRegister:
                  Intent registrationIntent = new Intent(getApplicationContext(),RegistrationActivity.class);
                  startActivity(registrationIntent);
                  break;

          }
    }
    public void verifyFromSQLite(){
        if(databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){
            Intent usersListIntent = new Intent(getApplicationContext(),UsersListActivity.class);
            usersListIntent.putExtra("EMAIL",textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();

            startActivity(usersListIntent);
        }else{
            Snackbar.make(nestedScrollView,getString(R.string.error_valid_email_password),Snackbar.LENGTH_SHORT).show();
        }


    }
    private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

}
