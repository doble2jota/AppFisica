package com.example.javier.appfisica;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class CircuitosActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuitos);


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
