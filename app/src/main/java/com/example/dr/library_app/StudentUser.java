package com.example.dr.library_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class StudentUser extends AppCompatActivity {
    public Button stregister;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_user);
    }
    public void studAuth(){
        stregister=(Button)findViewById(R.id.stRegister);
        stregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stIntent = new Intent(StudentUser.this, StudentRegister.class);
                startActivity(stIntent);
            }
        });

    }
}
