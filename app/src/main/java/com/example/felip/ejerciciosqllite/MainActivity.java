package com.example.felip.ejerciciosqllite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_alum,btn_prof,btn_borrar;
    DBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_alum=(Button)findViewById(R.id.btn_alum);
        btn_prof=(Button)findViewById(R.id.btn_prof);
        btn_borrar=(Button)findViewById(R.id.btn_borrado);
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
    }
}
