package com.example.felip.ejerciciosqllite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Buscador extends MainActivity{

    Button btn_busca;
    ListView lista;
    DBAdapter dbAdapter;
    ArrayAdapter<String> adaptador;
    ArrayList<String>alumnos;
    EditText alumno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

        dbAdapter = new DBAdapter(this);

        btn_busca = (Button)findViewById(R.id.btn_busc);
        lista = (ListView)findViewById(R.id.lv_listado);

        alumno =(EditText)findViewById(R.id.et_nameAlum);

        alumnos = new ArrayList<String>();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,alumnos );
        lista.setAdapter(adaptador);

        btn_busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenar();
            }
        });

    }

    public void llenar(){

        if(alumno.getText().toString().length()!=0 ){
            ArrayList<String>alumnos = dbAdapter.loadEstudiantePorNombre(alumno.getText().toString());


            adaptador.clear();
            adaptador.addAll(alumnos);

        }
    }
}
