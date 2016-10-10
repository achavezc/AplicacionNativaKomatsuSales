package upc.edu.pe.app_komatsusales.activities;


import android.database.Cursor;
import android.os.StrictMode;
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
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import okhttp3.Response;
import upc.edu.pe.app_komatsusales.R;
import upc.edu.pe.app_komatsusales.model.Personal;
import upc.edu.pe.app_komatsusales.model.Usuario;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "::::: " +LoginActivity.class;
    private static String LOGIN_URL = "http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/Login";

    Util util = new Util();
    Personal personal = new Personal();
    TextView quoteTextView;
    Button btnLogin ,  btnCancel;
    public String nombre;

    Boolean dValidator = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

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

            try {
                RestLogin(usuario);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Credenciales incorrectos", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }



        }

        });

    }


    public Boolean RestLogin(Usuario user){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("usuario", user.getUsuario());
            jsonObject.put("password", user.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ANRequest request = AndroidNetworking.post("http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/login/")
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .addJSONObjectBody(jsonObject)
                .build();
        ANResponse<JSONObject> response = request.executeForJSONObject();
        if (response.isSuccess()) {
            JSONObject jsonObjectResult = response.getResult();

            Log.d(TAG, "response : " + jsonObjectResult.toString());
            Response okHttpResponse = response.getOkHttpResponse();
            Log.d(TAG, "headers : " + okHttpResponse.headers().toString());

            try {
                nombre = jsonObjectResult.getJSONObject("response").getString("Nombre").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
           // quoteTextView.setText(nombre);
            if(nombre.equals(null) || nombre.equals("null") || nombre.equals("") ){
                dValidator=false;
                Toast.makeText(getApplicationContext(), "Credenciales incorrectos", Toast.LENGTH_SHORT).show();
            }else{
                dValidator=true;
                Intent nextForm= new Intent(LoginActivity.this, MainActivity.class);
                startActivity(nextForm);
             }

        } else {
            ANError error = response.getError();
            // Handle Error
        }
                /*
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });*/

        return dValidator;
    }

}

