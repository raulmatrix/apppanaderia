package com.example.panaderiaincos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getSupportActionBar().hide();
    }

    public void producto(View v){
        Intent it = new Intent(getApplicationContext(),CrearProducto.class);
        startActivity(it);
    }
}