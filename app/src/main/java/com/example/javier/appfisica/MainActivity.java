package com.example.javier.appfisica;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private final int DURACION_SPLASH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);




        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(MainActivity.this, BotoneraMenu.class);

                startActivity(intent);
                finish();
            };
    }, DURACION_SPLASH);
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
        if (id == R.id.btInfo) {
            acerca();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Función para mostrar la opción ACERCA DE de la aplicación.
     */
    public void acerca() {
        AlertDialog.Builder dialogoAcerca = new AlertDialog.Builder(this);

        dialogoAcerca.setTitle(R.string.acerca);
        dialogoAcerca.setMessage(R.string.mensajeAcerca);
        dialogoAcerca.setCancelable(false);
        dialogoAcerca.setNegativeButton(R.string.aceptar, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogoAcerca.show();
    } //Fin acerca()
}
