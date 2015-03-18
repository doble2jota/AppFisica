package com.example.javier.appfisica;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class CalculosActivity extends ActionBarActivity {

    private Float rS,lS,cS,vS;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculos);


        //recogemos valores del intent que tenemos q convertir a float
        rS  = Float.parseFloat(this.getIntent().getStringExtra("rSi"));
        lS  = Float.parseFloat(this.getIntent().getStringExtra("lSi"));
        cS  = Float.parseFloat(this.getIntent().getStringExtra("cSi"));
        vS  = Float.parseFloat(this.getIntent().getStringExtra("vSi"));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculos, menu);
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
