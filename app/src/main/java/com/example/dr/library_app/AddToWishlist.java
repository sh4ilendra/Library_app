package com.example.dr.library_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class AddToWishlist extends Fragment {

    private List<Product> mProductList;

    /** Called when the activity is first created. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_to_wishlist, container, false);

        // Obtain a reference to the product catalog
        mProductList = ShoppingCartHelper.getCatalog(getResources());

        // Create the list
        ListView listViewCatalog = (ListView) view.findViewById(R.id.ListViewCatalog);
        listViewCatalog.setAdapter(new ProductAdapter(mProductList, getLayoutInflater(savedInstanceState), false));

        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getActivity(),ProductDetailsActivity.class);
                productDetailsIntent.putExtra(ShoppingCartHelper.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

        Button viewShoppingCart = (Button) view.findViewById(R.id.ButtonViewCart);
        viewShoppingCart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewShoppingCartIntent = new Intent(getActivity(), ShoppingCartActivity.class);
                startActivity(viewShoppingCartIntent);
            }
        });

        return view;
    }
}
