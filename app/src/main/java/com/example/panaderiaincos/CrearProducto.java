package com.example.panaderiaincos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class CrearProducto extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {


    EditText nombProducto,preVenta;
    Spinner categoria, presentacion;

    Button registrar;

    //adicionando elementos recepcion
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);

        nombProducto = (EditText) findViewById(R.id.txtnompro);
        preVenta = (EditText) findViewById(R.id.txtpreventa);

        categoria = (Spinner) findViewById(R.id.spincategoria);
        presentacion = (Spinner) findViewById(R.id.spinpresentacion);

        registrar = (Button) findViewById(R.id.btnguardarpro);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWSProducto();
            }
        });

    }

    public void cargarWSProducto(){
        //http://localhost:9095/proyectopanaderia/conexremotas/conexregistrar.php?nombre=bizcocho&categoria=pan&precio=12&presentacion=unidad
        String url = "http://192.168.91.236:9095/proyectopanaderia/conexremotas/conexregistrar.php?nombre="+nombProducto.getText().toString()+"&categoria="+categoria.getSelectedItem().toString()+"&precio="+preVenta.getText().toString()+"&presentacion="+ presentacion.getSelectedItem().toString();
        url= url.replace(" ","%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);

        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se pudo registrar nada de nada"+error.toString(),Toast.LENGTH_LONG).show();

    }


    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(),"Registro exitosamente!",Toast.LENGTH_SHORT).show();
        nombProducto.setText("");
        //categoria.set
        preVenta.setText("");
        //presentacion.setText("");

    }
}