package com.example.dr.library_app;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    String name, password, email, Err,branch,year,phone,username,roll_no;
    TextView nameTV, emailTV, passwordTV, err,rollTV,branchTV,yearTV,phoneTV,usernameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameTV = (TextView) findViewById(R.id.home_name);
        rollTV=(TextView)findViewById(R.id.home_roll_no);
        branchTV=(TextView)findViewById(R.id.home_branch);
        yearTV=(TextView)findViewById(R.id.home_year);
        emailTV = (TextView) findViewById(R.id.home_email);
        phoneTV=(TextView)findViewById(R.id.home_phone);
        usernameTV=(TextView)findViewById(R.id.home_username);
        passwordTV=(TextView)findViewById(R.id.home_password);
        err = (TextView) findViewById(R.id.err);

        name = getIntent().getStringExtra("name");
        roll_no=getIntent().getStringExtra("roll_no");
        branch=getIntent().getStringExtra("branch");
        year=getIntent().getStringExtra("year");
        email=getIntent().getStringExtra("email");
        phone=getIntent().getStringExtra("phone");
        username=getIntent().getStringExtra("username");
        password=getIntent().getStringExtra("password");
        Err = getIntent().getStringExtra("err");

        nameTV.setText("Welcome "+name);
        rollTV.setText("Your Roll No. is "+roll_no);
        branchTV.setText("Your Branch is "+branch);
        yearTV.setText("You are in "+year+" year");
        emailTV.setText("Your email Id is "+email);
        phoneTV.setText("Your contact no. is "+phone);
        usernameTV.setText("Your username is "+username);
        passwordTV.setText("Your password is "+password);
        err.setText(Err);
    }
}