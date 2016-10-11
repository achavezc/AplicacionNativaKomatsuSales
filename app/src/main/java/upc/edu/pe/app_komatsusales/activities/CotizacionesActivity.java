package upc.edu.pe.app_komatsusales.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import org.json.JSONArray;

import okhttp3.Response;
import upc.edu.pe.app_komatsusales.R;

public class CotizacionesActivity extends AppCompatActivity {

    private static final String TAG = "::::: " +CotizacionesActivity.class;

    String idVendedor, numCotizacion, fEmision, estado, nomPersonal, dni;
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizaciones);

        test            = (TextView) findViewById(R.id.txtTest);

        idVendedor      ="";
        numCotizacion   ="";
        fEmision        ="\\/Date(1468731600000-0500)\\/";
        estado          ="";
        nomPersonal     ="";
        dni             ="";
        try {
            ObtenerCotizacionesPorVendedor(idVendedor, numCotizacion, fEmision,estado,nomPersonal,dni);
        } catch (Exception e) {
            System.out.println("::::::::::::::::: "+e.getMessage());
            e.printStackTrace();
        }

    }

    public void ObtenerCotizacionesPorVendedor1(String vend, String str1,String str2,String str3,String str4,String str5 ){

        String mensaje = null;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("NumeroCotizacion", str1);
            jsonObject.put("FechaEmision",str2);
            jsonObject.put("Estado",str3);
            jsonObject.put("NombrePersonal",str4);
            jsonObject.put("DNI",str5);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ANRequest request = AndroidNetworking.post("http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/obtenercotizaciones/")
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
                mensaje  = null;// jsonObjectResult.getJSONObject("result").getString("Mensaje");
                test.setText(jsonObjectResult.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // quoteTextView.setText(nombre);
            if(mensaje.equals(null) || mensaje.equals("null") || mensaje.equals("") ){
                Toast.makeText(getApplicationContext(), "Error al intentar registrar", Toast.LENGTH_SHORT).show();
            }else{

                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();

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


    public void ObtenerCotizacionesPorVendedor(String vend, String str1,String str2,String str3,String str4,String str5 ){

        String mensaje = null;

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("NumeroCotizacion", str1);
            jsonObject.put("FechaEmision",str2);
            jsonObject.put("Estado",str3);
            jsonObject.put("NombrePersonal",str4);
            jsonObject.put("DNI",str5);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ANRequest request = AndroidNetworking.post("http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/obtenercotizaciones/")
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .addJSONObjectBody(jsonObject)
                .build();
        ANResponse<JSONArray> response = request.executeForJSONArray();

        if (response.isSuccess()) {
            JSONArray jsonObjectResult = response.getResult();

            Log.d(TAG, "response : " + jsonObjectResult.toString());
            Response okHttpResponse = response.getOkHttpResponse();
            Log.d(TAG, "headers : " + okHttpResponse.headers().toString());

            mensaje = jsonObjectResult.toString();

            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
            // quoteTextView.setText(nombre);
            if(mensaje.equals(null) || mensaje.equals("null") || mensaje.equals("") ){
                Toast.makeText(getApplicationContext(), "Cliente no esta registrado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Ëxitos", Toast.LENGTH_SHORT).show();
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
}
