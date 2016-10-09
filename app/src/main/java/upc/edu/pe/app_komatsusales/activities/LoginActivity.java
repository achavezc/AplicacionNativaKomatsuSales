package upc.edu.pe.app_komatsusales.activities;


import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import upc.edu.pe.app_komatsusales.R;
import upc.edu.pe.app_komatsusales.model.Personal;
import upc.edu.pe.app_komatsusales.model.Usuario;


public class LoginActivity extends AppCompatActivity {

    private static String LOGIN_URL = "http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/Login";

    Util util = new Util();
    Personal personal = new Personal();
    TextView quoteTextView;
    Button btnLogin ,  btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_login);


        btnLogin  = (Button) findViewById(R.id.btnLogin);
        btnCancel = (Button) findViewById(R.id.btnCancel);


        btnLogin.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v){

            String user   = ((EditText) findViewById(R.id.txtUsuario)).getText().toString();
            String pass   = ((EditText) findViewById(R.id.txtPassword)).getText().toString();
            System.out.println("::::::::::::::::::::::::::Datos iNGRESADOS: " +user +"," +pass);

            quoteTextView = (TextView) findViewById(R.id.quotetextview);

            Usuario usuario = new Usuario(user.toString(), pass.toString());
            RestLogin(LOGIN_URL, usuario);
                System.out.println(":::::::::::::::");

           // if(personal.getNombre()!=null){
                Intent nextForm= new Intent(LoginActivity.this, MainActivity.class);
                startActivity(nextForm);
            //    quoteTextView.setText(personal.getApellidos() + ", "+personal.getNombre());
            //}else{
            //    Toast.makeText(getApplicationContext(), "Credenciales incorrectos", Toast.LENGTH_SHORT).show();
           // }


        }

        });

    }


    public String RestLogin(String url, Usuario user){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("usuario", user.getUsuario());
            jsonObject.put("password", user.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post("http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/login/")
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .addJSONObjectBody(jsonObject)
                //.addPathParameter("usuario","jperez")
                //.addPathParameter("password","jperez")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

        return null;
    }

}

