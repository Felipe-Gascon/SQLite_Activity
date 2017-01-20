package com.example.felip.ejerciciosqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class BuscaProfesor extends AppCompatActivity {
    Button btn_buscaProf;
    ListView lista;
    DBAdapter dbAdapter;
    ArrayAdapter<String> adaptador;
    ArrayList<String>profesores;
    EditText profesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_profesor);
        dbAdapter = new DBAdapter(this);
        btn_buscaProf = (Button)findViewById(R.id.busca_prof);
        lista = (ListView)this.findViewById(R.id.lv_prof);

        profesor = (EditText)this.findViewById(R.id.et_nombreProf);
        profesores=new ArrayList<String>();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,profesores);
        lista.setAdapter(adaptador);
        btn_buscaProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenar();
            }
        });
    }
    public void llenar(){
        if(profesor.getText().toString().length()!=0  ){
            ArrayList<String>profesores=dbAdapter.loadProfesores(profesor.getText().toString());
            adaptador.clear();
            adaptador.addAll(profesores);

        }
    }
}
