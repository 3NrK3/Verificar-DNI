package com.jcolladosp.verificardni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public TextView letrag;
    public TextView titulo;
    public Button boton;
    public char letra;
    public EditText campo;
    public EditText eletra;
    public boolean modoVeri = false;
    private Switch mySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListeners();
        letrag = (TextView) findViewById(R.id.txLetra);
        titulo = (TextView) findViewById(R.id.txTitulo);
        campo = (EditText) findViewById(R.id.editText);
        eletra = (EditText) findViewById(R.id.edLetra);
        boton = (Button) findViewById(R.id.btGenerar);
        mySwitch = (Switch) findViewById(R.id.switch1);

        mySwitch.setChecked(false);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    titulo.setText("Verificar el DNI");
                    boton.setText("verificar!");
                    modoVeri = true;
                    eletra.setVisibility(View.VISIBLE);
                } else {
                    titulo.setText("Generar la letra del DNI");
                    boton.setText("generar!");
                    modoVeri=false;
                    eletra.setVisibility(View.INVISIBLE);
                }

            }

         });}

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }


        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();

            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    public void calculaLetra(int dni) {
        if (comprobarDNI(dni)) {

            String juegoCaracteres = "TRWAGMYFPDXBNJZSQVHLCKET";
            int modulo = dni % 23;
            letra = juegoCaracteres.charAt(modulo);
        }

    }

    public boolean comprobarDNI(int dni) {

        if (dni > 0) {
            int length = (int) (Math.log10(dni) + 1);
            if (length == 8) {
                return true;
            } else {
                mostrarError(1);

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

    public void mostrarError(int i) {
        if(i==1){
        Snackbar
                .make(letrag, "Longitud del DNI incorrecta", Snackbar.LENGTH_LONG)
                .setAction("Limpiar campo de texto", errorSnack)
                .show();

        letrag.setText("");}
        else{
            Snackbar
                    .make(letrag, "Introduce la letra en su campo", Snackbar.LENGTH_LONG)
                    .show();
            letrag.setText("");
        }
    }

    private void setListeners() {
        Button boton = (Button) findViewById(R.id.btGenerar);
        boton.setOnClickListener(this);
    }

    public void generador() {
        hideKeyboard();
        if (campo.getText().toString().equals("")) {
            mostrarError(1);
        } else {

            int dni = Integer.parseInt(campo.getText().toString());
            if (comprobarDNI(dni)) {
                calculaLetra(dni);
                letrag.setText("La letra del DNI " + dni + " es: " + letra);
            }
        }
    }

    public void verificador() {
        hideKeyboard();
        int dni2 = Integer.parseInt(campo.getText().toString());
        if (campo.getText().toString().equals("") || !comprobarDNI(dni2)) {
            mostrarError(1);
        }
        else if(eletra.getText().toString().equals("")){
            mostrarError(2);
        }
        else {

            int dni = Integer.parseInt(campo.getText().toString());
            if (comprobarDNI(dni)) {
                calculaLetra(dni);
                String letrauser = letra + "";
                if (eletra.getText().toString().toUpperCase().equals(letrauser)){
                    letrag.setText("El DNI es correcto");
            }

        else{
            letrag.setText("El DNI es incorrecto");
        }
    }}}

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btGenerar) {
            if (!modoVeri) {
                generador();
            } else {
                verificador();
            }
        }
    }

}

