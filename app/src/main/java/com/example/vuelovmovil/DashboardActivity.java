package com.example.vuelovmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vuelovmovil.RespuestaBoleto.BoletoResponseObjectClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {

    Pago pago;
    Maletas maletas;
    TipoAsiento tipoAsiento;
    Asientos asientos;
    TextView edtEmail;
    Button reservaBtn;

    //Implementacion de los campos
    TextView tvSelectDates1;

    EditText edMaletas;
    EditText edAsiento;
    EditText edtipoAsiento;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        edMaletas = findViewById(R.id.edMaletas);
        edAsiento = findViewById(R.id.edAsiento);
        edAsiento = findViewById(R.id.edtipoAsiento);

        //fechas aqui
        //fechas
        tvSelectDates1 = findViewById(R.id.tvSelectDates1);
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvSelectDates1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(DashboardActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        tvSelectDates1.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });


        //fechas termina aqui

        reservaBtn = findViewById(R.id.reservaBtn);


        edtEmail = findViewById(R.id.edtEmail);

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            String passedEmail = intent.getStringExtra("data");
            edtEmail.setText("Bienvenido Usuario: "+passedEmail);
        }

        //Llama el boton que inserta la reserva
        reservaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarReserva();

                startActivity(new Intent(DashboardActivity.this,MetodoPagoActivity.class));



            }
        });

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),TableroActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.notifications:
                        startActivity(new Intent(getApplicationContext(),NotificationActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),AboutActitivy.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.informacion:
                        startActivity(new Intent(getApplicationContext(),InfoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



    }

    public void insertarReserva(){

//Inicializa el retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vuelo-v.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pago = new Pago(true,"masterCard",10);
        maletas = new Maletas("40",10);
        List<Maletas> ml = new ArrayList<>();
        ml.add(maletas);
        tipoAsiento = new TipoAsiento(0,"Clase Media");
        asientos = new Asientos(211,"Vuelo Comercial",10,tipoAsiento);
        List<Asientos> as = new ArrayList<>();
        as.add(asientos);

        BoletoObjectClass boletoObjectClass = new BoletoObjectClass(as,"2022-06-15T21:32:34.979Z",ml,pago,1,"qrcodigos",13);

        BoletoRetrofitCliente boletoRetrofitCliente = retrofit.create(BoletoRetrofitCliente.class);

        Call<BoletoResponseObjectClass> res =boletoRetrofitCliente.GetPostsValue(boletoObjectClass);
        res.enqueue(new Callback<BoletoResponseObjectClass>() {
            @Override
            public void onResponse(Call<BoletoResponseObjectClass> call, Response<BoletoResponseObjectClass> response) {

                //String nombre = response.body().toString();


                Log.e("nombre","insertado");
            }

            @Override
            public void onFailure(Call<BoletoResponseObjectClass> call, Throwable t) {
                Log.e("response","failed");
            }
        });




    }
}