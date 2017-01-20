package com.example.felip.ejerciciosqllite;

/**
 * Created by felip on 13/12/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;


public class DBAdapter  {

    // Definiciones y constantes

    private static final String DATABASE_NAME = "dbregistro.db";
    private static final String Tabla1 = "Alumnos";
    private static final String Tabla2 = "Profesores";
    private static final int DATABASE_VERSION = 1;

    private static final String NOMBRE = "Nombre";
    private static final String EDAD = "Edad";
    private static final String CICLO = "Ciclo";
    private static final String NOTA_MEDIA = "NMedia";


    private static final String NOMBRE_PROF = "Profesor";
    private static final String EDAD_PROF = "EdadProf";
    private static final String CICLO_PROF = "Ciclo_Prof";
    private static final String Curso_Tutor = "Curso_Tutor";
    private static final String DESPACHO = "Despacho";

    private static final String DATABASE_CREATE = "CREATE TABLE "+Tabla1+" (_id integer primary key autoincrement, Nombre text," +
            "Edad integer, Ciclo text, NMedia double ); " + Tabla2+"(_id integer primary key autoincrement,Profesor,EdadProf,Ciclo_Prof, Curso_Tutor," +
            "Despacho);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS"+Tabla1+Tabla2+";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;





    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        open();

    }


    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }



    public void insertaAlumno(String n, int e, String c, double nm ){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();

        newValues.put(NOMBRE,n);
        newValues.put(EDAD,e);
        newValues.put(CICLO,c);
        newValues.put(NOTA_MEDIA,nm);

        db.insert(Tabla1,null,newValues);
    }

    public void insertaProfesor(String p, int e, String cp, String ct, String d ){
        //Creamos un nuevo regisro de valores a insertar
        ContentValues newValues = new ContentValues();
        newValues.put(CICLO_PROF,cp);

        newValues.put(NOMBRE_PROF,p);
        newValues.put(EDAD_PROF,e);
        newValues.put(Curso_Tutor,ct);
        newValues.put(DESPACHO,d);

        db.insert(Tabla2,null,newValues);
    }
    public ArrayList<String> loadEstudiantePorNombre(String nombre){
        ArrayList<String>estudiantes = new ArrayList<String>();
        Cursor cursorEstNom = db.rawQuery("SELECT * FROM "+Tabla1+" WHERE "+NOMBRE+" = '"+nombre,null );

        if(cursorEstNom != null && cursorEstNom.moveToFirst()){
            do{
                estudiantes.add("Alumno "+cursorEstNom.getString(1)+" ID "+cursorEstNom.getString(0));
            }while(cursorEstNom.moveToNext());
        }

        return estudiantes;

    }

    public ArrayList<String>loadProfesores(String nombre){
        ArrayList<String>profesores =  new ArrayList<String>();
        Cursor cursorProf = db.rawQuery("SELECT * FROM "+Tabla2+" WHERE "+NOMBRE_PROF+" = '"+nombre+"'" ,null );

        if(cursorProf!=null && cursorProf.moveToFirst()){
            do {
                profesores.add("Profesor: "+cursorProf.getString(1)+" ID: "+cursorProf.getString(0));
            }while(cursorProf.moveToNext());
        }
        return profesores;
    }

    public void deleteAlumno(String nombre){
        dbHelper.getWritableDatabase().delete("Alumnos","Nombre"+nombre+"'",null);
    }
    public void deleteProfesor(String nom){
        dbHelper.getWritableDatabase().delete("Profesores",NOMBRE_PROF+nom+"'",null);
    }
    public void deleteDatabase(String f){
        context.deleteDatabase(f);
    }
    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            onCreate(db);
        }

    }
}
