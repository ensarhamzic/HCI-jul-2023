package com.example.hci_ispit.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.hci_ispit.ProductActivity;
import com.example.hci_ispit.ProductDetails;
import com.example.hci_ispit.R;
import com.example.hci_ispit.user.Product;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> users;

    public ProductAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.users = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.product_list, parent, false);

        TextView txtSubjectId = (TextView) rowView.findViewById(R.id.txtSubjectId);
        TextView txtSubjectName = (TextView) rowView.findViewById(R.id.txtSubjectName);
        TextView txtSubjectSemester = (TextView) rowView.findViewById(R.id.txtSubjectSemester);
        TextView txtSubjectPrice = (TextView) rowView.findViewById(R.id.txtProductPrice);
        TextView txtProductQuantity = (TextView) rowView.findViewById(R.id.txtProductQuantity);
        ImageView imgProduct = (ImageView) rowView.findViewById(R.id.imgProductImage);

        txtSubjectId.setText("Product ID:" + users.get(pos).getId().toString());
        txtSubjectName.setText("Product Name: " + users.get(pos).getName());
        txtSubjectSemester.setText("Description: " + users.get(pos).getDescription());
        txtSubjectPrice.setText("Price: " + users.get(pos).getPrice().toString());
        txtProductQuantity.setText("Quantity: " + users.get(pos).getQuantity().toString());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(users.get(pos).getImage());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imgProduct.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("details", String.valueOf(users.get(pos).getDescription()));
                intent.putExtra("image", String.valueOf(users.get(pos).getImage()));
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
