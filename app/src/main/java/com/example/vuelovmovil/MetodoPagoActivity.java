package com.example.vuelovmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MetodoPagoActivity extends AppCompatActivity {

    Button bancoPichincha;
    Button dinnersClub;
    Button masterCard;
    Button payPal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_pago);

        bancoPichincha = findViewById(R.id.bancoPichincha);
        dinnersClub = findViewById(R.id.bancoPichincha);
        masterCard = findViewById(R.id.masterCard);
        payPal = findViewById(R.id.payPal);

        //Metodos de los botones para enviar al formulario del principio
        bancoPichincha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Manda al inicio
                startActivity(new Intent(MetodoPagoActivity.this,NotificationActivity.class));
                Toast.makeText(getApplicationContext(), "Reserva Guardada", Toast.LENGTH_SHORT).show();

            }
        });


        //otro
        dinnersClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MetodoPagoActivity.this,NotificationActivity.class));
                Toast.makeText(getApplicationContext(), "Reserva Guardada", Toast.LENGTH_SHORT).show();

            }
        });

        //otro
        masterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MetodoPagoActivity.this,NotificationActivity.class));
                Toast.makeText(getApplicationContext(), "Reserva Guardada", Toast.LENGTH_SHORT).show();

            }
        });

        //ultimo
        payPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MetodoPagoActivity.this,NotificationActivity.class));
                Toast.makeText(getApplicationContext(), "Reserva Guardada", Toast.LENGTH_SHORT).show();

            }
        });



    }




}