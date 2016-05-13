package com.example.dr.library_app;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class StudentRegister extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_register);
        final EditText stname = (EditText) findViewById(R.id.stName);
        final EditText stroll = (EditText) findViewById(R.id.stRoll);
        final EditText stbranch = (EditText) findViewById(R.id.stBranch);
        final EditText styear = (EditText) findViewById(R.id.stYear);
        final EditText stemail = (EditText) findViewById(R.id.stEmail);
        final EditText stphone = (EditText) findViewById(R.id.stPhone);
        final EditText stusername = (EditText) findViewById(R.id.stUsername);
        final EditText stpassword = (EditText) findViewById(R.id.stPassword);
        final EditText stcnfrmpass = (EditText) findViewById(R.id.stCnfrmPass);
        final Button stregister = (Button) findViewById(R.id.stRegister);
        stregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name= stname.getText().toString();
                final int roll_no= Integer.parseInt(stroll.getText().toString());
                final String branch= stbranch.getText().toString();
                final int year= Integer.parseInt(styear.getText().toString());
                final String email= stemail.getText().toString();
                final int phone= Integer.parseInt(stphone.getText().toString());
                final String username= stusername.getText().toString();
                final String password= stpassword.getText().toString();
                final String cnfrmpass= stcnfrmpass.getText().toString();

                Response.Listener<String> responseListener= new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse= new JSONObject(response);
                            boolean success= jsonResponse.getBoolean("success");

                            if (success){
                                Intent intent=new Intent(StudentRegister.this, StudentUser.class);
                                startActivity(intent);
                            }else{
                                AlertDialog.Builder builder=new AlertDialog.Builder(StudentRegister.this);
                                builder.setMessage("Registration Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name,roll_no,branch,year,email,phone,username,password,cnfrmpass,responseListener);
                RequestQueue queue = Volley.newRequestQueue(StudentRegister.this);
                queue.add(registerRequest);
            }
        });
    }
}
