package com.example.dr.library_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class MemberList extends Fragment {



    private static final String JSON_URL = "http://libraryphp-shailu.rhcloud.com/members.php";

    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_NAME = "name";
    private static final String TAG_BRANCH ="branch";
    private static final String TAG_ROLL ="roll_no";
    private static final String TAG_SERIAL ="id";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            View view=inflater.inflate(R.layout.details_member, container, false);

            list = (ListView)view.findViewById(R.id.member_list);
            personList = new ArrayList<HashMap<String,String>>();
            getData();
            return view;
        }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost(JSON_URL);

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String email = c.getString(TAG_EMAIL);
                String name = c.getString(TAG_NAME);
                String branch = c.getString(TAG_BRANCH);
                String roll_no=c.getString(TAG_ROLL);

                HashMap<String,String> persons = new HashMap<String,String>();
                persons.put(TAG_EMAIL,email);
                persons.put(TAG_NAME,name);
                persons.put(TAG_BRANCH,branch);
                persons.put(TAG_ROLL,roll_no);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), personList, R.layout.list_members,
                    new String[]{TAG_NAME,TAG_EMAIL,TAG_ROLL,TAG_BRANCH},
                    new int[]{R.id.member_name, R.id.member_email, R.id.member_roll_no,R.id.member_branch}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }









}
