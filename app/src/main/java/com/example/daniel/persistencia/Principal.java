package com.example.daniel.persistencia;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Principal extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton btn;
    private EditText edtTxtNombre,edtTxtEdad,edtTxtTemperatura;
    private CheckBox chkBoxSuscrito;
    private Button btnGuardar,btnCargar;
    //se pueden cargar los datos
    private SharedPreferences pref;
    //para guardar los datos.
    private SharedPreferences.Editor edit;
    private File archivo;
    //COSNTANTES
    public static final int PRIVADO=0;
    public static final String NOMBREKEY="nombre";
    public static final String SUSCRITOKEY="suscrito";
    public static final String EDADKEY="edad";
    public static final String TEMPKEY="temp";
    public static final String NOMBREValorPD="Fulanito";
    public static final boolean SUSCRITOValorPD=false;
    public static final int EDADValorPD=0;
    public static final float TEMPValorPD=0f;
    public static final String CADENAVACIA="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.isInEditMode();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        edtTxtEdad=(EditText)findViewById(R.id.edtTxtEdad);
        edtTxtNombre=(EditText)findViewById(R.id.edtTxtNombre);
        edtTxtTemperatura=(EditText)findViewById(R.id.edtTxtTemp);
        chkBoxSuscrito=(CheckBox)findViewById(R.id.checkBoxSuscrito);
        btnCargar=(Button)findViewById(R.id.btnCargar);
        btnGuardar=(Button)findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(this);
        btnCargar.setOnClickListener(this);

        /*
        pref=getPreferences(PRIVADO);
        edit=pref.edit();
        cargarPreferencias();*/
        archivo = new File(getFilesDir(),"archivo.txt");
        cargarArchivo();
    }//fin del método onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
    if (v==btnGuardar){
        //guardarPreferencias();
        guardarArchivo();
    }else if (v==btnCargar){
        //cargarPreferencias();
        cargarArchivo();
    }
        //aquí iniciamos un método de persistencia
    }//fin del método onClick

    public void clickLimpiar(View v){
        edtTxtNombre.setText(CADENAVACIA);
        chkBoxSuscrito.setChecked(SUSCRITOValorPD);
        edtTxtEdad.setText(CADENAVACIA);
        edtTxtTemperatura.setText(CADENAVACIA);
    }//fin del método clickLimpiar
    /*
    public void guardarPreferencias(){
        edit.putString(NOMBREKEY,edtTxtNombre.getText().toString());
        edit.putBoolean(SUSCRITOKEY, chkBoxSuscrito.isChecked());
        edit.putInt(EDADKEY, Integer.parseInt(edtTxtEdad.getText().toString()));
        edit.putFloat(TEMPKEY, Float.parseFloat(edtTxtTemperatura.getText().toString()));
        edit.commit();
    }
    public void cargarPreferencias(){
        edtTxtNombre.setText(pref.getString(NOMBREKEY, NOMBREValorPD));
        chkBoxSuscrito.setChecked(pref.getBoolean(SUSCRITOKEY, SUSCRITOValorPD));
        edtTxtEdad.setText(CADENAVACIA + pref.getInt(EDADKEY, EDADValorPD));
        edtTxtTemperatura.setText(CADENAVACIA + pref.getFloat(TEMPKEY, TEMPValorPD));
    }*/

    private void cargarArchivo() {
        Toast.makeText(getApplicationContext(),"A ver si funciona",Toast.LENGTH_SHORT).show();
    try {
        BufferedReader bf= new BufferedReader(new FileReader(archivo));
        edtTxtNombre.setText(bf.readLine());
        boolean b=bf.readLine().equals("1");
        chkBoxSuscrito.setChecked(b);
        edtTxtEdad.setText(bf.readLine());
        edtTxtTemperatura.setText(bf.readLine());
        bf.close();
    }catch (FileNotFoundException e){
        Toast.makeText(getApplicationContext(),"No se encontro archivo", Toast.LENGTH_SHORT).show();
        edtTxtNombre.setText(NOMBREValorPD);
        chkBoxSuscrito.setChecked(SUSCRITOValorPD);
        edtTxtEdad.setText(CADENAVACIA+EDADValorPD);
        edtTxtTemperatura.setText(CADENAVACIA+TEMPValorPD);
    }catch (IOException e){
        e.printStackTrace();
    }
    }//fin del método cargarArchivo()

    public void guardarArchivo(){
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter(archivo));
            bw.write(edtTxtNombre.getText().toString());
            bw.newLine();
            if (chkBoxSuscrito.isChecked()){
                bw.write("1");
            }else {
                bw.write("0");
            }bw.newLine();
            bw.write(edtTxtEdad.getText().toString());
            bw.newLine();
            bw.write(edtTxtTemperatura.getText().toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //en este método guardamos preferencias.
    @Override
    protected void onStop() {
        super.onStop();
        //guardarPreferencias();
        guardarArchivo();
    }//fin del metodo onStop
}//fin de la clase Principal
