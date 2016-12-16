package com.example.felip.ejerciciosqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profesores extends MainActivity {
    EditText et_prof,et_edad,et_cicloProf,et_cursTut,et_des;
    Button btn_guardar;
    DBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        et_prof=(EditText)findViewById(R.id.et_nomP);
        et_edad=(EditText)findViewById(R.id.et_edadP);
        et_cicloProf=(EditText)findViewById(R.id.et_cicloP);
        et_cursTut=(EditText)findViewById(R.id.et_cursoTut);
        et_des=(EditText)findViewById(R.id.et_despa);
        btn_guardar=(Button)findViewById(R.id.btn_guarda);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardaDatos();



            }
        });



    }

    public void guardaDatos(){
        String nomProf = et_prof.getText().toString();
        int edadProf = Integer.parseInt(et_edad.getText().toString());
        String cicloProf = et_cicloProf.getText().toString();
        String cursoTut = et_cursTut.getText().toString();
        String despa = et_des.getText().toString();

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        dbAdapter.insertaProfesor(nomProf,edadProf,cicloProf,cursoTut,despa);
    }
}
