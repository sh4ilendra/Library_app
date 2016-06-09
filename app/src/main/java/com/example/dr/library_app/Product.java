package com.example.dr.library_app;
import android.graphics.drawable.Drawable;

public class Product {

    public String title;
    public Drawable productImage;
    public String description;
    public boolean selected;

    public Product(String title, Drawable productImage, String description
                   ) {
        this.title = title;
        this.productImage = productImage;
        this.description = description;
    }

}