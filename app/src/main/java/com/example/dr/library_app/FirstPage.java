package com.example.dr.library_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class FirstPage extends AppCompatActivity {

    private Button stud,fac,lib;
    public void studAuth(){
        stud=(Button)findViewById(R.id.std_button);
        stud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fpage=new Intent(FirstPage.this,Login.class);
                startActivity(fpage);
            }
        });

    }
    public void facAuth(){
        fac=(Button)findViewById(R.id.fac_button);
        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fpage2=new Intent(FirstPage.this,FacultyUser.class);
                startActivity(fpage2);
            }
        });

    }
    public void libAuth(){
        lib=(Button)findViewById(R.id.lib_button);
        lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fpage3=new Intent(FirstPage.this,LibrarianUser.class);
                startActivity(fpage3);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        studAuth();
        facAuth();
        libAuth();
    }
}
