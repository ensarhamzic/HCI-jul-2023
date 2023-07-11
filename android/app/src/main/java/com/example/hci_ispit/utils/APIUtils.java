package com.example.hci_ispit.utils;

import com.example.hci_ispit.user.UserService;

public class APIUtils {
    public APIUtils() {}

    public static final String API_URL = "http://192.168.84.56:8080/";

    public static UserService getUserService() {
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }
}
