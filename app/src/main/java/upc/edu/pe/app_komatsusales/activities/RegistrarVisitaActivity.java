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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import okhttp3.Response;
import upc.edu.pe.app_komatsusales.R;
import upc.edu.pe.app_komatsusales.model.Cliente;

public class RegistrarVisitaActivity extends AppCompatActivity {

    private static final String TAG = "::::: " +RegistrarVisitaActivity.class;
    Button btnBuscarClientePorNombre, btnBuscarClientePorRuc;
    public String oRSCliente,oRucCliente;
    TextView txtRazonSocialCliente,txtRucCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_visita);

        btnBuscarClientePorNombre   = (Button) findViewById(R.id.btnBuscarCliente);
        btnBuscarClientePorRuc      = (Button) findViewById(R.id.btnBuscarRUC);

         txtRazonSocialCliente  = (TextView) findViewById(R.id.txtRazonSocialCliente);
         txtRucCliente          = (TextView) findViewById(R.id.txtRucCliente);

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
    }


    public void buscarCliente(Cliente cliente){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Ruc", cliente.getRuc());
            jsonObject.put("RazonSocial", cliente.getRazonSocial());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post("http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/obtenerclientes/")
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .addJSONObjectBody(jsonObject)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

        @Override
        public void onResponse(JSONObject response) {

            //JSONArray jsonObjectResult = response.getResult();

            Log.d(TAG, "response : " + response.toString());

            try {
                oRSCliente  = response.getJSONArray("response").getJSONObject(1).getString("RazonSocial");
                oRucCliente = response.getJSONArray("response").getJSONObject(1).getString("Ruc");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // quoteTextView.setText(nombre);
            if(oRSCliente.equals(null) || oRucCliente.equals("null") || oRSCliente.equals("") || oRucCliente.equals("")){
                Toast.makeText(getApplicationContext(), "Cliente no esta registrado", Toast.LENGTH_SHORT).show();
            }else{
                btnBuscarClientePorNombre.setText(oRSCliente);
                txtRucCliente.setText(oRucCliente);
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
    public void registrarVisita(Cliente cliente){

    }

}
