package com.example.javier.appfisica;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

// actividad que contiene los ciruitos



public class CircuitosActivity extends ActionBarActivity {

    //DECLARAMOS LA VARIABLES DE LOS EDIT TEXT

    private EditText rS,lS,cS,vS,rP,lP,cP,vP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuitos);

        //Rellenamos spiners

        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        String []opciones={"V","mV","µV","nV"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);


        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        String []opciones2={"Ω","mΩ","µΩ","nΩ"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones2);
        spinner2.setAdapter(adapter2);

        final Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        String []opciones3={"H","mH","µH","nH"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones3);
        spinner3.setAdapter(adapter3);


        final Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);

        String []opciones4={"F","mF","µF","nF"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones4);
        spinner4.setAdapter(adapter4);

        final Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);

        String []opciones5={"V","mV","µV","nV"};
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones5);
        spinner5.setAdapter(adapter5);


        final Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
        String []opciones6={"Ω","mΩ","µΩ","nΩ"};
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones6);
        spinner6.setAdapter(adapter6);

        final Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        String []opciones7={"H","mH","µH","nH"};
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones7);
        spinner7.setAdapter(adapter7);


        final Spinner spinner8 = (Spinner) findViewById(R.id.spinner8);

        String []opciones8={"F","mF","µF","nF"};
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones8);
        spinner8.setAdapter(adapter8);


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

                if (((vS.getText().toString()).equals("")) || ((rS.getText().toString()).equals("")) || ((cS.getText().toString()).equals("")) || ((lS.getText().toString()).equals(""))) {

                    Toast.makeText(getApplicationContext(), "Rellene todos los campos por favor",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else {


                    Intent i = new Intent(CircuitosActivity.this, CalculosActivity.class);
                    Editable s = rS.getText();

                    Log.e("SPINER", spinner1.getSelectedItem().toString());

                    i.putExtra("rSi", rS.getText().toString());
                    i.putExtra("vSi", vS.getText().toString());
                    i.putExtra("cSi", cS.getText().toString());
                    i.putExtra("lSi", lS.getText().toString());

                    //spinner1
                    i.putExtra("sp1", spinner1.getSelectedItem().toString());

                    //spinner2
                    i.putExtra("sp2", spinner2.getSelectedItem().toString());

                    //spinner3
                    i.putExtra("sp3", spinner3.getSelectedItem().toString());

                    //spinner4
                    i.putExtra("sp4", spinner4.getSelectedItem().toString());


                    startActivity(i);
                }
            }
        });

        //boton calculos de c Paralelo
        botonP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (((vP.getText().toString()).equals("")) || ((rP.getText().toString()).equals("")) || ((cP.getText().toString()).equals("")) || ((lP.getText().toString()).equals(""))) {

                    Toast.makeText(getApplicationContext(), "Rellene todos los campos por favor",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else {


                    Intent i2 = new Intent(CircuitosActivity.this, CalculosActivity.class);

                    Editable p = rP.getText();

                    Log.e("SPINER", spinner5.getSelectedItem().toString());

                    i2.putExtra("rPi", rP.getText().toString());
                    i2.putExtra("vPi", vP.getText().toString());
                    i2.putExtra("cPi", cP.getText().toString());
                    i2.putExtra("lPi", lP.getText().toString());

                    //spinner1
                    i2.putExtra("sp5", spinner5.getSelectedItem().toString());

                    //spinner2
                    i2.putExtra("sp6", spinner6.getSelectedItem().toString());

                    //spinner3
                    i2.putExtra("sp7", spinner7.getSelectedItem().toString());

                    //spinner4
                    i2.putExtra("sp8", spinner8.getSelectedItem().toString());


                    startActivity(i2);
                }
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
