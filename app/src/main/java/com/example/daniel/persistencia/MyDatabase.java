package com.example.daniel.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by daniel on 17/03/2016.
 */
public class MyDatabase extends SQLiteOpenHelper {
    //CONSTANTES
    private static final int VERSION_DATABASE=1;
    private static final String NOMBRE_DATABASE= "ejemplo.db";
    private static final String TABLA="usuario";
    private static final String KEY_ID ="id";
    private static final String KEY_NOMBRE="nombre";
    private static final String KEY_SUSCRITO="suscrito";
    private static final String KEY_EDAD="edad";
    private static final String KEY_TEMP="temp";
    private static final String [] COLUMNAS={KEY_ID,KEY_NOMBRE,KEY_SUSCRITO,KEY_EDAD,KEY_TEMP};
    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, NOMBRE_DATABASE, factory, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addUsuario(Usuario usuario){

    }
    public Usuario getUsuario(int id){
        return new Usuario();//reemplazar este return...
    }
    public void delUsuario(Usuario usuario){

    }
}
