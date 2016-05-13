package com.example.dr.library_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by DR on 5/9/2016.
 */
public class StudentUser extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_user);

        final EditText stusername = (EditText) findViewById(R.id.stUsername);
        final EditText stpassword = (EditText) findViewById(R.id.stPassword);
        final Button stregister = (Button) findViewById(R.id.stLogin);
        final TextView stRegisterlink = (TextView) findViewById(R.id.stRegisterHere);
        stRegisterlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), StudentRegister.class);
                startActivity(registerIntent);
            }
        });
    }
}
