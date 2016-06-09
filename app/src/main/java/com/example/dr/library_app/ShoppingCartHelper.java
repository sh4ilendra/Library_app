package com.example.dr.library_app;

import android.content.res.Resources;

import java.util.List;
import java.util.Vector;

public class ShoppingCartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<Product> catalog;
    private static List<Product> cart;

    public static List<Product> getCatalog(Resources res){
        if(catalog == null) {
            catalog = new Vector<Product>();
            catalog.add(new Product("Data Structure", res
                    .getDrawable(R.drawable.data_structure),
                    "Data Structure by Deepali Srivastava and S.K. Srivastava"));
            catalog.add(new Product("C", res
                    .getDrawable(R.drawable.c),
                    "C by Jitender Kumar Chhabra"));
            catalog.add(new Product("Linux", res
                    .getDrawable(R.drawable.linux),
                    "Linux by Machtelt Garrels"));
            catalog.add(new Product("Operating System", res
                    .getDrawable(R.drawable.os),
                    "Operating System by Peter B. Galvin"));
            catalog.add(new Product("C++", res
                    .getDrawable(R.drawable.c_plus_plus),
                    "C++ bu Herbert Schildt"));
            catalog.add(new Product("Algorithm", res
                    .getDrawable(R.drawable.algorithm),
                    "Algorithm by Thomas H. Coreman with Ronald L. Rivest"));
            catalog.add(new Product("JAVA", res
                    .getDrawable(R.drawable.java),
                    "Java by Herbert Schildt"));
        }

        return catalog;
    }

    public static List<Product> getCart() {
        if(cart == null) {
            cart = new Vector<Product>();
        }

        return cart;
    }

}
