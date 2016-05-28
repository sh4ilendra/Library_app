package com.example.dr.library_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private static final String[] IMAGES = new String[]{
            "http://4.bp.blogspot.com/-LwDlh5jrDac/UcwJz06FdUI/AAAAAAAAAFM/GOKDuI_iD_E/s1600/operating-system-concepts-by-galvin.jpg",
            "http://ecx.images-amazon.com/images/I/51fQw8OX4-L._SX303_BO1,204,203,200_.jpg",
            "http://highered.mcgraw-hill.com/sites/dl/free/0070131511/cover/cormen-lg_cover.jpg",
            "http://www.gatecounsellor.com/books/images/programming-with-c-3-edition-9780070145900.jpg",
            "http://images.contentreserve.com/ImageType-100/0018-1/%7B9E60640B-CB0B-40A5-8AC0-77089E20028C%7DImg100.jpg",
            "http://img.docstoccdn.com/thumb/orig/121637281.png"

    };
    private ViewPager pager;
    String username;



    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        // Inflate the layout for this fragment
        pager = (ViewPager)view.findViewById(R.id.pager);
        ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        username=getActivity().getIntent().getStringExtra("username");

        if(username!=null) {
            pagerAdapter.addAll(Arrays.asList(IMAGES));
            pager.setAdapter(pagerAdapter);
            CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
            indicator.setViewPager(pager);
        }
        else
        {
            getActivity().getFragmentManager().popBackStackImmediate();
            getActivity().finish();
            Toast.makeText(getActivity().getApplicationContext(), "Incorrect Username or password !!! ", Toast.LENGTH_LONG).show();
        }
            return view;

    }
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.getActivity().onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }
}