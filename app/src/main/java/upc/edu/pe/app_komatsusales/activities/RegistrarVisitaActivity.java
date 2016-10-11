package upc.edu.pe.app_komatsusales.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;
import upc.edu.pe.app_komatsusales.R;
import upc.edu.pe.app_komatsusales.model.Cliente;

import com.androidnetworking.interfaces.JSONObjectRequestListener;

public class RegistrarVisitaActivity extends AppCompatActivity {

    private static final String TAG = "::::: " +RegistrarVisitaActivity.class;
    Button btnBuscarClientePorNombre, btnBuscarClientePorRuc, btnREGISTRAR;

    public String oRSCliente,oRucCliente,oDirCliente;

    TextView txtRazonSocialCliente,txtRucCliente, txtDireccion,txtComentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_visita);

        btnBuscarClientePorNombre   = (Button) findViewById(R.id.btnBuscarCliente);
        btnBuscarClientePorRuc      = (Button) findViewById(R.id.btnBuscarRUC);
        btnREGISTRAR                = (Button) findViewById(R.id.btnRegistrarVisita);

         txtRazonSocialCliente  = (TextView) findViewById(R.id.txtRazonSocialCliente);
         txtRucCliente          = (TextView) findViewById(R.id.txtRucCliente);
         txtDireccion           = (TextView) findViewById(R.id.Direccion);
         txtComentario          = (TextView) findViewById(R.id.txtComentario);

        btnBuscarClientePorNombre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Cliente cliente = new Cliente(txtRazonSocialCliente.toString(),txtRucCliente.toString());
                try {
                    buscarCliente(cliente);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Cliente no existe en la base de datos", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        btnBuscarClientePorRuc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Cliente cliente = new Cliente(txtRazonSocialCliente.toString(),txtRucCliente.toString());
                try {
                    buscarCliente(cliente);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Cliente no existe en la base de datos", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        btnREGISTRAR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                try {
                    registrarVisita(txtComentario.toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Hubo un error al intentar registrar", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });


    }

    public void buscarCliente(Cliente cliente){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Ruc", "");
            jsonObject.put("RazonSocial","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ANRequest request = AndroidNetworking.post("http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/obtenerclientes/")
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
                oRSCliente  =  jsonObjectResult.getJSONArray("response").getJSONObject(0).getString("RazonSocial");
                oRucCliente = jsonObjectResult.getJSONArray("response").getJSONObject(0).getString("Ruc");
                oDirCliente = jsonObjectResult.getJSONArray("response").getJSONObject(0).getString("Direccion");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // quoteTextView.setText(nombre);
            if(oRSCliente.equals(null) || oRucCliente.equals("null") || oRSCliente.equals("") || oRucCliente.equals("")){
                Toast.makeText(getApplicationContext(), "Cliente no esta registrado", Toast.LENGTH_SHORT).show();
            }else{
                txtRazonSocialCliente.setText(oRSCliente);
                txtRucCliente.setText(oRucCliente);
                txtDireccion.setText(oDirCliente);
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
    }

    public void registrarVisita(String str1){

        String mensaje = null;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Descripcion", str1);
            jsonObject.put("UrlGoogleMaps","https://www.google.com.pe/maps/place/Perú/@-9.0828099,-84.0336184,5z");
            jsonObject.put("IdCliente","1");
            jsonObject.put("IdPersonal","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ANRequest request = AndroidNetworking.post("http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/registrarvisita/")
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
                mensaje  =  jsonObjectResult.getJSONObject("result").getString("Mensaje");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // quoteTextView.setText(nombre);
            if(mensaje.equals(null) || mensaje.equals("null") || mensaje.equals("") ){
                Toast.makeText(getApplicationContext(), "Error al intentar registrar", Toast.LENGTH_SHORT).show();
            }else{
                txtRazonSocialCliente.setText("");
                txtRucCliente.setText("");
                txtDireccion.setText("");
                txtComentario.setText("");

                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                Intent nextForm= new Intent(RegistrarVisitaActivity.this, AddCheckInActivity.class);
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
    }
    private void buscarCliente22    (Cliente cliente){

        AndroidNetworking.post("http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/obtenerclientes/")
                .addBodyParameter("Ruc",  "")
                .addBodyParameter("RazonSocial", "")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response : " + response.toString());
                        // Get Quote of Day
                        try{
                            String oRucCliente   = response
                                    .getJSONArray("response")
                                    .getJSONObject(0)
                                    .getString("Descripcion");

                            Log.d(TAG, "oRucCliente : " + oRucCliente.toString());

                        }catch(JSONException e){
                            e.printStackTrace();
                            Log.d(TAG, ":::::..ERROR : " + e.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // Log.d("COD", "Error");
                        System.out.println("::::::::::::::::::ERROR:::::::::::::::::::"+anError.getMessage());
                        System.out.println("::::::::::::::::::ERROR - code:::::::::::::::::::"+anError.getErrorCode());
                        System.out.println("::::::::::::::::::ERROR - code:::::::::::::::::::"+anError.getResponse());
                        System.out.println("::::::::::::::::::ERROR - code:::::::::::::::::::"+anError.getErrorDetail());
                        System.out.println("::::::::::::::::::ERROR - code:::::::::::::::::::"+anError.toString());
                        anError.getMessage();
                    }
                });

    }

}
