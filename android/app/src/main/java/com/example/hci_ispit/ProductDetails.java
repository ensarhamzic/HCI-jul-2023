package com.example.hci_ispit;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ProductDetails extends AppCompatActivity {

    TextView txtProductDetails;
    ImageView imgProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        txtProductDetails = findViewById(R.id.txtProductDetails);
        imgProduct = findViewById(R.id.imgProductImage);

        Bundle extras = getIntent().getExtras();
        String productName = extras.getString("details");
        String productImage = extras.getString("image");

        txtProductDetails.setText(productName);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(productImage);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imgProduct.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}