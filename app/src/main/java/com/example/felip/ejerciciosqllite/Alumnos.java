package com.example.felip.ejerciciosqllite;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Alumnos extends MainActivity {
    private DBAdapter dbAdapter;
    Button btn_guarda,btn_borraR,btn_borraDB;
    EditText et_alum,et_edad,et_ciclo,et_notMed,input;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);
        et_alum=(EditText)findViewById(R.id.et_nomA);
        et_edad = (EditText)findViewById(R.id.et_edadA);
        et_ciclo = (EditText)findViewById(R.id.et_cicloA);
        et_notMed = (EditText)findViewById(R.id.et_notaMedia);
        btn_guarda = (Button) findViewById(R.id.btn_guardar);
        btn_borraR = (Button)findViewById(R.id.btn_deleteId);
        btn_borraDB = (Button)findViewById(R.id.btn_borrado);
        dbAdapter= new DBAdapter(this);
        dbAdapter.open();

       /* btn_borraR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarRegistro();
            }
        });*/


        btn_guarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomAlumn= et_alum.getText().toString();
                int edadAlum = Integer.parseInt(et_edad.getText().toString());
                String ciclo = et_ciclo.getText().toString();
                double nota = Double.parseDouble(et_notMed.getText().toString());


                dbAdapter.insertaAlumno(nomAlumn,edadAlum,ciclo,nota);
                et_alum.setText("");
                et_edad.setText("");
                et_notMed.setText("");
                et_ciclo.setText("");

            }
        });


    }




  /*  public void borrarRegistro(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Borrar Registro");
        builder.setMessage("Introduzca la Id del registro que desea borrar");
        input = new EditText(this);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int id = Integer.parseInt(input.getText().toString());

                dbAdapter.deleteAlumno(id);
            }
        });
    }*/
}
