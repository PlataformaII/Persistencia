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

public class Principal extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton btn;
    private EditText edtTxtNombre,edtTxtEdad,edtTxtTemperatura;
    private CheckBox chkBoxSuscrito;
    private Button btnGuardar,btnCargar;
    //se pueden cargar los datos
    private SharedPreferences pref;
    //para guardar los datos.
    private SharedPreferences.Editor edit;
    public static final int PRIVADO=0;

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

        pref=getPreferences(PRIVADO);
        edit=pref.edit();

        cargarPreferencias();

    }

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
        guardarPreferencias();
    }else{
        cargarPreferencias();
    }
        //aquí iniciamos un método de persistencia
    }
    public void guardarPreferencias(){
        edit.putString("nombre",edtTxtNombre.getText().toString());
        edit.putBoolean("suscrito", chkBoxSuscrito.isChecked());
        edit.putInt("edad",Integer.parseInt(edtTxtEdad.getText().toString()));
        edit.putFloat("temp",Float.parseFloat(edtTxtTemperatura.getText().toString()));
        edit.commit();
    }
    public void cargarPreferencias(){
        edtTxtNombre.setText(pref.getString("nombre","Sutano"));
        chkBoxSuscrito.setChecked(pref.getBoolean("suscrito", false));
        edtTxtEdad.setText("" + pref.getInt("edad", 0));
        edtTxtTemperatura.setText(""+pref.getFloat("temp",0f));
    }

    //en este método guardamos preferencias.
    @Override
    protected void onStop() {
        super.onStop();
        guardarPreferencias();
    }
}
