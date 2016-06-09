package com.example.dr.library_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * A simple {@link Fragment} subclass.
 */
public class TermsAndConditions extends Fragment {
    View view;


    public TermsAndConditions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_terms_conditions, container, false);
        // Inflate the layout for this fragment
        try {
            PlayWithRawFiles();
        } catch (IOException e) {
            Toast.makeText(getActivity(),
                    "Problems: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return view;
    }
    public void PlayWithRawFiles() throws IOException {
        String str="";
        StringBuffer buf = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.terms_conditions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is!=null) {
            while ((str = reader.readLine()) != null) {
                buf.append(str + "\n" );
            }
        }
        is.close();
        TextView tv=(TextView)view.findViewById(R.id.tv1);
        tv.setText(buf.toString());


    }//
}