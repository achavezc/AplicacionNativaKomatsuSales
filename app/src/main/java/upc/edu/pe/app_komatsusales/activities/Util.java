package upc.edu.pe.app_komatsusales.activities;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.HashMap;
import android.util.*;

import upc.edu.pe.app_komatsusales.model.Personal;
import upc.edu.pe.app_komatsusales.model.Usuario;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by Alicia on 28/09/2016.
 */

public class Util  extends Application {

    public static final String KEY_USERNAME = "usuario";
    public static final String KEY_PASSWORD = "password";

    Personal personal = new Personal();

    public void RestLogin(String url, Usuario user){

        String QOD_URL = url;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(KEY_USERNAME, user.getUsuario());
            jsonObject.put(KEY_PASSWORD, user.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(QOD_URL)
                .addJSONObjectBody(jsonObject)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    // do anything with response
                    //  Log.d(TAG, "id : " + user.id);
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            String tex = response.getString("Telefono");
                            /*
                            personal.setApellidos(response.getString("Apellidos"));
                            personal.setCargo(response.getString("Cargo"));
                            personal.setDNI(response.getString("DNI"));
                            personal.setEmail(response.getString("Email"));
                            personal.setIdCargo(response.getString("IdCargo"));
                            personal.setIdPersonal(response.getString("IdPersonal"));
                            personal.setNombre(response.getString("Nombre"));
                            personal.setSexo(response.getString("Sexo"));
                            personal.setTelefono(response.getString("Telefono"));*/
                           // quoteTextView.setText(quote);
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        // Log.d("COD", "Error");
                        System.out.println("::::::::::::::::::ERROR:::::::::::::::::::"+anError.getMessage());
                        anError.getMessage();
                    }
                });

    }
}
