package com.example.hci_ispit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.hci_ispit.user.Product;
import com.example.hci_ispit.user.UserService;
import com.example.hci_ispit.utils.APIUtils;
import com.example.hci_ispit.utils.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    ListView listView;
    UserService userService;
    List<Product> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Bundle extras = getIntent().getExtras();


        listView = findViewById(R.id.listView);

        userService = APIUtils.getUserService();


        getProducts();
    }

    public void getProducts(){
        Call<List<Product>> call = userService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new ProductAdapter(ProductActivity.this, R.layout.product_list, list));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

}