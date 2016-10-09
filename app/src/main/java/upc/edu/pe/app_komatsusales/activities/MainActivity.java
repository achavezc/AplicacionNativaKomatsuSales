package upc.edu.pe.app_komatsusales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import upc.edu.pe.app_komatsusales.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View v){

        switch (v.getId()){
            case R.id.btnAddCheckIn:

                    Intent i = new Intent(this, AddCheckInActivity.class);
                    startActivity(i);
                break;

            case R.id.btnFollowCheckIn:

                Intent intentCheckIn = new Intent(this, CheckInActivity.class);
                startActivity(intentCheckIn);
                break;

            case R.id.btnMapa:

                Intent intentMapa = new Intent(this, MapsActivity.class);
                startActivity(intentMapa);
                break;
            default:
                break;

        }

    }

}
