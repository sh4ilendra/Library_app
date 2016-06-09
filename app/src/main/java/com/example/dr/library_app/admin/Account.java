package com.example.dr.library_app.admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dr.library_app.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {

    String name;
    String password;
    String email;
    String phone;
    String username;
    TextView nameTV;
    TextView emailTV;
    TextView passwordTV;
    TextView phoneTV;
    TextView usernameTV;



    public Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.admin_account, container, false);

        nameTV = (TextView)view.findViewById(R.id.admin_name);
        emailTV = (TextView) view.findViewById(R.id.admin_email);
        phoneTV=(TextView)view.findViewById(R.id.admin_phone);
        usernameTV=(TextView)view.findViewById(R.id.admin_username);
        passwordTV=(TextView)view.findViewById(R.id.admin_password);


        name = getActivity().getIntent().getStringExtra("name");
        email=getActivity().getIntent().getStringExtra("email");
        phone=getActivity().getIntent().getStringExtra("phone");
        username=getActivity().getIntent().getStringExtra("username");
        password=getActivity().getIntent().getStringExtra("password");


        if(username!=null) {
            nameTV.setText("Welcome " + name);
            emailTV.setText("Your email Id is " + email);
            phoneTV.setText("Your contact no. is " + phone);
            usernameTV.setText("Your username is " + username);
            passwordTV.setText("Your password is " + password);
        }
        else
        {
            getActivity().getFragmentManager().popBackStackImmediate();
            getActivity().finish();
            Toast.makeText(getActivity().getApplicationContext(), "Incorrect Username or password !!! ", Toast.LENGTH_LONG).show();

        }
        return view;
    }

}
