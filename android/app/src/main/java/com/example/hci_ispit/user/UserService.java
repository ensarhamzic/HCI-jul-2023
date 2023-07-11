package com.example.hci_ispit.user;

import com.example.hci_ispit.utils.LoginRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("user/login")
    Call<User> login(@Body LoginRequest request);

    @GET("products")
    Call<List<Product>> getProducts();
}
