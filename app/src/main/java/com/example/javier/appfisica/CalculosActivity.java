package com.example.javier.appfisica;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CalculosActivity extends ActionBarActivity {


    LinearLayout ll = (LinearLayout) findViewById(R.id.contenedor);

    TextView impedanciaTotal= (TextView) ll.findViewById(R.id.impedanciaT);



    private Float rS,lS,cS,vS;
    final float hz= 50;
    float xL; //reactancia inductiva
    float xC; //reactancia capacitiva
    float x;  //xtriagulo
    float zT;  //impedancia total
    float angulo;
    final float w= 2 * (float) Math.PI* hz;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculos);


        //recogemos valores del intent que tenemos q convertir a float
        rS  = Float.parseFloat(this.getIntent().getStringExtra("rSi"));
        lS  = Float.parseFloat(this.getIntent().getStringExtra("lSi"));
        cS  = Float.parseFloat(this.getIntent().getStringExtra("cSi"));
        vS  = Float.parseFloat(this.getIntent().getStringExtra("vSi"));
        //calculamos reactancia induztiva
        xL=w*lS;
        xC=(1/(w*cS));
        Log.e("2222","reactancia induztiva "+xL);
        Log.e("2222","reactancia reactiva "+xC);
        x=xL-xC;
        Log.e("2222","x "+x);
        zT=(float)Math.pow(rS, 2)+ (float)Math.pow(x, 2);
        zT=(float)Math.sqrt(zT);
        Log.e("2222","impedancia total "+zT);
        Log.e("2222","rs "+rS);
        //calculamos el angulo
        angulo= (float) Math.atan(x/rS);
        angulo= (float) Math.toDegrees(angulo);

        Log.e("2222","angulo "+angulo);
        //añadimos al textview
        impedanciaTotal.setText(zT+"  "+angulo+"º");

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
