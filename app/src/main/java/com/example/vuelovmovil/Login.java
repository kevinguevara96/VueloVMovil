package com.example.vuelovmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText correo;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        correo = findViewById(R.id.edCorreo);
        password = findViewById(R.id.edPassword);
        final AppCompatButton loginBtn = findViewById(R.id.loginBtn);
        final TextView singUpBtn = findViewById(R.id.signUpBtn);


        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //going to signup activity
                startActivity(new Intent(Login.this,Register.class));
                finish();

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //handle your login....
                if(TextUtils.isEmpty(correo.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(Login.this, "Correo / Password Required", Toast.LENGTH_SHORT).show();
                }else{
                    //proceed to Login
                    login();
                }

                //then open MainActivity after succesfully login
               // startActivity(new Intent(Login.this, MainActivity.class));
                //finish();

            }
        });
    }


    public void login(){

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCorreo(correo.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()){
                    Toast.makeText(Login.this, "Login succesfull", Toast.LENGTH_SHORT).show();
                    LoginResponse loginResponse = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            //Aqui es despues del login manda al menu principal
                        startActivity(new Intent(Login.this,DashboardActivity.class).putExtra("data",loginResponse.getEmail()));
                           // startActivity(new Intent(Login.this,TableroActivity.class));

                        }
                    },700);
                }else{
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }




}