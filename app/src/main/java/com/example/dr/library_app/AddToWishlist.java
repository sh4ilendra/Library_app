package com.example.dr.library_app;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddToWishlist extends Fragment {
    Toolbar toolbar;
    private ViewPager pager;
    private ListView GetAllBooksListView;
    private JSONArray jsonArray;


    public AddToWishlist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_to_wishlist, container, false);
        this.GetAllBooksListView = (ListView) view.findViewById(R.id.GetAllBooksListview);
        new GetAllBooksTask().execute(new ApiConnector());
        this.GetAllBooksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    JSONObject bookClicked = jsonArray.getJSONObject(position);

                    Intent showDetails = new Intent(getActivity(), BooksDetailsActivity.class);
                    showDetails.putExtra("BookID", bookClicked.getInt("id"));
                    startActivity(showDetails);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
    public void setListAdapter(JSONArray jsonArray)
    {
        this.jsonArray = jsonArray;
        this.GetAllBooksListView.setAdapter(new GetAllBooksListViewAdapter(jsonArray, getActivity()));
    }

    private class GetAllBooksTask extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {

            return params[0].GetAllBooks();}

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            setListAdapter(jsonArray);

        }
    }
}
