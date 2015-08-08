package com.jcolladosp.verificardni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    public TextView letrag;
    public char letra;
    public EditText campo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListeners();
        letrag = (TextView) findViewById(R.id.txLetra);
        campo = (EditText) findViewById(R.id.editText);
        campo.max
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

     public void calculaLetra(int dni)
    {
        if(comprobarDNI(dni)){

        String juegoCaracteres="TRWAGMYFPDXBNJZSQVHLCKET";
        int modulo= dni % 23;
        letra = juegoCaracteres.charAt(modulo);
        }

    }
    public boolean comprobarDNI(int dni){

        if(dni >0){
            int length = (int)(Math.log10(dni)+1);
            if(length == 8){return true;}
            else{
                      mostrarError();

            return false;
            }
        }
        return false;
    }

    final View.OnClickListener errorSnack = new View.OnClickListener() {
        public void onClick(View v) {
            campo.setText("");
        }
    };

    public void mostrarError(){
        Snackbar
                .make(letrag, "Longitud del DNI incorrecta", Snackbar.LENGTH_LONG)
                .setAction("Limpiar campo de texto",errorSnack)
                .show();

        letrag.setText("");
    }
    private void setListeners() {
        Button boton = (Button) findViewById(R.id.btGenerar);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btGenerar) {
            hideKeyboard();
            if (campo.getText().toString().equals("")){mostrarError();}
            else{

                int dni = Integer.parseInt(campo.getText().toString());
                if (comprobarDNI(dni)){
            calculaLetra(dni);
            letrag.setText("La letra del DNI " + dni +" es: " + letra);}}
        }
    }
}
