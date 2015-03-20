package com.example.javier.appfisica;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;

// actividad que contiene los ciruitos



public class CircuitosActivity extends ActionBarActivity {

    //DECLARAMOS LA VARIABLES DE LOS EDIT TEXT

    private EditText rS,lS,cS,vS,rP,lP,cP,vP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuitos);

        //Rellenamos spiners

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        String []opciones={" V"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);


        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        String []opciones2={"µA","nA","mA","A"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones2);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        String []opciones3={"µH","nH","mH","H"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones3);
        spinner3.setAdapter(adapter3);


        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        String []opciones4={"µC","nC","mC","C"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones4);
        spinner4.setAdapter(adapter4);
        //TABS PARTE SUPERIOR

        Resources res= getResources();
        TabHost tabs = (TabHost)findViewById(android.R.id.tabhost);

        tabs.setup();
        TabHost.TabSpec spec3 = tabs.newTabSpec("Serie");
        spec3.setContent(R.id.tabc1);
        spec3.setIndicator("serie");
        tabs.addTab(spec3);

        tabs.setup();
        TabHost.TabSpec spec4 = tabs.newTabSpec("Paralelo");
        spec4.setContent(R.id.tabc2);
        spec4.setIndicator("Paralelo");
        tabs.addTab(spec4);

        tabs.setup();
        TabHost.TabSpec spec5 = tabs.newTabSpec("Combinado");
        spec5.setContent(R.id.tabc3);
        spec5.setIndicator("Combinado");
        tabs.addTab(spec5);

        //declaramos botones
        Button botonS = (Button) findViewById(R.id.calcularC1);
        Button botonP = (Button) findViewById(R.id.calcularC2);

        //declaramos edit text
        rS=(EditText)findViewById(R.id.rS);
        lS=(EditText)findViewById(R.id.lS);
        cS=(EditText)findViewById(R.id.cS);
        vS=(EditText)findViewById(R.id.vS);

        rP=(EditText)findViewById(R.id.rP);
        lP=(EditText)findViewById(R.id.lP);
        cP=(EditText)findViewById(R.id.cP);
        vP=(EditText)findViewById(R.id.vP);


        //boton calculos de c Serie
        botonS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(CircuitosActivity.this, CalculosActivity.class);
                Editable s= rS.getText();

                i.putExtra("rSi",rS.getText().toString());
                i.putExtra("vSi",vS.getText().toString());
                i.putExtra("cSi",cS.getText().toString());
                i.putExtra("lSi",lS.getText().toString());

                startActivity(i);
            }

        });

        //boton calculos de c Paralelo
        botonP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i2= new Intent(CircuitosActivity.this, CalculosActivity.class);
                startActivity(i2);
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_circuitos, menu);
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
