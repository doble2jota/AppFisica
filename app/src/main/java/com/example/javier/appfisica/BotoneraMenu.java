package com.example.javier.appfisica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class BotoneraMenu extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botonera_menu);

        Button button1 = (Button) findViewById(R.id.bteoria);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent act = new Intent(BotoneraMenu.this, TeoriaActivity.class);
                startActivity(act);
            }

        });

        Button button2 = (Button) findViewById(R.id.bcircuitos);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent act2 = new Intent(BotoneraMenu.this, CircuitosActivity.class);
                startActivity(act2);
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_botonera_menu, menu);
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

}
