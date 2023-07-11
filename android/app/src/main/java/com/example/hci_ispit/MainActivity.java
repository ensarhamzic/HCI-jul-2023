package com.example.hci_ispit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hci_ispit.user.User;
import com.example.hci_ispit.user.UserService;
import com.example.hci_ispit.utils.APIUtils;
import com.example.hci_ispit.utils.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userService = APIUtils.getUserService();

        etUsername = findViewById(R.id.edtUUsername);
        etPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            login();
        });
    }

    public void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        LoginRequest loginRequest = new LoginRequest(username, password);

        Call<User> call = userService.login(loginRequest);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = response.body();

                Toast.makeText(MainActivity.this, "Welcome " + user.getName() + " " + user.getSurname(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}