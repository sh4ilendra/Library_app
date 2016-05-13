package com.example.dr.library_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by DR on 5/9/2016.
 */
public class FirstPage extends AppCompatActivity {
    private static Button stBtLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        onButtonClickListener();
    }

    public void onButtonClickListener(){

        stBtLogin = (Button) findViewById(R.id.std_button);
        stBtLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent stIntent=new Intent(getApplicationContext(),StudentUser.class);
                        startActivity(stIntent);
                    }
                }
        );
    }
}
