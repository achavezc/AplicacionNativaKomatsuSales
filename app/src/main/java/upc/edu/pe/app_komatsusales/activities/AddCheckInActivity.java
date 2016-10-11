package upc.edu.pe.app_komatsusales.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import upc.edu.pe.app_komatsusales.R;

public class AddCheckInActivity extends AppCompatActivity {

    Button btnMenuRegistrarVisita, btnBandejaCotizaciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_check_in);

        btnMenuRegistrarVisita = (Button) findViewById(R.id.btnMenuRegistrarVisita);
        btnBandejaCotizaciones = (Button) findViewById(R.id.btnCotizaciones);

        btnMenuRegistrarVisita.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent nextForm= new Intent(AddCheckInActivity.this, RegistrarVisitaActivity.class);
                startActivity(nextForm);
            }

        });

        btnBandejaCotizaciones.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent nextForm= new Intent(AddCheckInActivity.this, CotizacionesActivity.class);
                startActivity(nextForm);
            }

        });

    }
}
