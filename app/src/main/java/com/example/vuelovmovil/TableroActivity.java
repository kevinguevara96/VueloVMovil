package com.example.vuelovmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.vuelovmovil.Interface.JsonPlaceHolderApi;
import com.example.vuelovmovil.Model.Promociones;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TableroActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);

mJsonTxtView = findViewById(R.id.jsonText);

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
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

        getPromociones();
    }


    private void getPromociones(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vuelo-v.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Promociones>> call = jsonPlaceHolderApi.getPromociones();

        call.enqueue(new Callback<List<Promociones>>() {
            @Override
            public void onResponse(Call<List<Promociones>> call, Response<List<Promociones>> response) {
                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
                }

                List<Promociones> promocionesList = response.body();

                for(Promociones promocion: promocionesList){
                    String content = "";
                   // content += "id:"+promocion.getId() + "\n";
                    content += "            OFERTA DISPONIBLE!" + "\n";
                    content += ""+promocion.getDescripcion() + "\n";
                    content += "APROVECHA DESCUENTO : "+promocion.getDescuento() + "%"+"\n";

                   // content += "vueloid:"+promocion.getVuelo().getId() + "\n";
                   // content += "fechaCreacion:"+promocion.getVuelo().getFechaCreacion() + "\n";
                    content += "PRECIO : $"+promocion.getVuelo().getPrecio() + "\n";

                    //content += "observacion:"+promocion.getVuelo().getObservacion() + "\n";
                    content += "SALIDA : "+promocion.getVuelo().getFechaVuelo() + "\n";
                    //content += "estado:"+promocion.getVuelo().isEstado() + "\n";

                    //content += "horarioResponseid:"+promocion.getVuelo().getHorarioResponse().getId() + "\n";
                    //content += "fechaInicio:"+promocion.getVuelo().getHorarioResponse().getFechaInicio() + "\n";
                    //content += "fechaFin:"+promocion.getVuelo().getHorarioResponse().getFechaFin() + "\n";

                   // content += "rutaResponseid:"+promocion.getVuelo().getRutaResponse().getId() + "\n";
                    content += "RUTA VIAJE : "+promocion.getVuelo().getRutaResponse().getDescripcion() + "\n";
                    content += "DESDE : "+promocion.getVuelo().getRutaResponse().getOrigen() + "\n";
                    content += "HASTA : "+promocion.getVuelo().getRutaResponse().getDestino() + "\n";
                    content += "              COMPRAR BOLETO" + "\n\n";

                    //content += "tipoVueloResponseid:"+promocion.getVuelo().getTipoVueloResponse().getId() + "\n";
                    //content += "nombre:"+promocion.getVuelo().getTipoVueloResponse().getNombre() + "\n\n";

                    mJsonTxtView.append(content);

                    Log.d("mensjae",content);

                }
            }

            @Override
            public void onFailure(Call<List<Promociones>> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());
            }
        });
    }
}