package com.example.felip.ejerciciosqllite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listado;
    Button btn_alum,btn_prof,btn_borrar,btn_busqueda;
    DBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_alum=(Button)findViewById(R.id.btn_alum);
        btn_prof=(Button)findViewById(R.id.btn_prof);
        btn_borrar=(Button)findViewById(R.id.btn_borrado);
        btn_busqueda=(Button)findViewById(R.id.btn_busca);
        dbAdapter = new DBAdapter(this);

        btn_alum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_alum = new Intent(MainActivity.this,Alumnos.class);
                startActivityForResult(intent_alum,1);
            }
        });

        btn_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_prof = new Intent(MainActivity.this,Profesores.class);
                startActivity(intent_prof);
            }
        });
        btn_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.deleteDatabase("dbregistro.db");
            }
        });

        btn_busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_busq = new Intent(MainActivity.this,Buscador.class);
               startActivity(intent_busq);

            }
        });

    }
}
