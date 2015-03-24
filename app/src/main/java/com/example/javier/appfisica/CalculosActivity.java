package com.example.javier.appfisica;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;


public class CalculosActivity extends ActionBarActivity {

    private TextView impT;
    private TextView corrT;
    private TextView tenR;
    private TextView tenC;
    private TextView tenL;


    private double rS;
    private double lS;
    private double cS;
    private double vS;
    private double rP;
    private double lP;
    private double cP;
    private double vP;
    private double rC;
    private double lC;
    private double cC;
    private double vC;
    final float hz= 50;
    double xL; //reactancia inductiva
    double xC; //reactancia capacitiva
    double x;  //xtriagulo
    double iTotalserie; // intensidad total del circuito
    double angulointensidad; //angulo de intensidad
    double anguloTensionCS;
    double anguloTensionLS;
    double tensionRS;
    double tensionCS;
    double tensionLS;

    double zT;  //impedancia total
    float angulo;
    String sp1;
    String sp2;
    String sp3;
    String sp4;
    String sp5;
    String sp6;
    String sp7;
    String sp8;
    String sp9;
    String sp10;
    String sp11;
    String sp12;

    final float w= 2 * (float) Math.PI* hz;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculos2);



        //try para comparar , puesto que si hay nulls los if no funcionan

        try{
            //dentro de este if van las operaciones relativas al circuito serie
            if((this.getIntent().getStringExtra("serie").equals("SERIE"))) {

                DecimalFormat df= new DecimalFormat("0.000");

                corrT=(TextView)findViewById(R.id.corrienteTotal);
                impT=(TextView)findViewById(R.id.impedanciat);
                tenR=(TextView)findViewById(R.id.tensionR);
                tenC=(TextView)findViewById(R.id.tensionC);
                tenL=(TextView)findViewById(R.id.tensionL);


                //recogemos valores del intent que tenemos q convertir a float
                rS = Float.parseFloat(this.getIntent().getStringExtra("rSi"));
                lS = Float.parseFloat(this.getIntent().getStringExtra("lSi"));
                cS = Float.parseFloat(this.getIntent().getStringExtra("cSi"));
                vS = Float.parseFloat(this.getIntent().getStringExtra("vSi"));

                sp1 = this.getIntent().getStringExtra("sp1");
                sp2 = this.getIntent().getStringExtra("sp2");
                sp3 = this.getIntent().getStringExtra("sp3");
                sp4 = this.getIntent().getStringExtra("sp4");

                if(sp1.equals("µV")){ vS=vS*0.000001;}
                if(sp1.equals("nV")){ vS=vS*0.000000001;}
                if(sp1.equals("mV")){ vS=vS*0.001;}
                //capacidad
                if(sp4.equals("µF")){ cS=cS*0.000001;}
                if(sp4.equals("nF")){ cS=cS*0.000000001;}
                if(sp4.equals("mF")){ cS=cS*0.001;}
                //inductancia
                if(sp3.equals("µH")){ lS=lS*0.000001; }
                if(sp3.equals("nH")){ lS=lS*0.000000001;}
                if(sp3.equals("mH")){ lS=lS*0.001;}
                //REsistencia
                if(sp2.equals("µΩ")){ rS=rS*0.000001;}
                if(sp2.equals("nΩ")){rS=rS*0.000000001;}
                if(sp2.equals("mΩ")){ rS=rS*0.001;}

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

                impT.setText(" " + df.format(zT)+" "+df.format(angulo)+"º");


                //Calculamos la intensidad que circula por el circuito
                iTotalserie=(vS/zT);
                angulointensidad=0-angulo;
                //añadimos corriente

                corrT.setText(df.format(iTotalserie)+" "+df.format(angulointensidad)+"º");

                //calculamos las tensiones
                tensionRS = (rS * iTotalserie);
                tensionCS = (xC*iTotalserie);
                anguloTensionCS = (-90+(-angulo));
                tensionLS =(xL * iTotalserie);
                anguloTensionLS=(90+(-angulo));

                tenR.setText(" " + df.format(tensionRS) + " "+ df.format(-angulo)+"º");
                tenC.setText(" " + df.format(tensionCS) + " "+ df.format(anguloTensionCS)+"º");
                tenL.setText(" " + df.format(tensionLS) + " "+ df.format(anguloTensionLS)+"º");




                //captura de excepción
            }}catch (NullPointerException e){
            // try para el circuito paralelo
            try {if((this.getIntent().getStringExtra("paralelo").equals("PARALELO"))) {
                rP = Float.parseFloat(this.getIntent().getStringExtra("rPi"));
                lP = Float.parseFloat(this.getIntent().getStringExtra("lPi"));
                cP = Float.parseFloat(this.getIntent().getStringExtra("cPi"));
                vP = Float.parseFloat(this.getIntent().getStringExtra("vPi"));
                sp5 = this.getIntent().getStringExtra("sp5");
                sp6 = this.getIntent().getStringExtra("sp6");
                sp7 = this.getIntent().getStringExtra("sp7");
                sp8 = this.getIntent().getStringExtra("sp8");
                Log.e("dentro try2"," "+rP);
                if(sp5.equals("µV")){ vS=vS*0.000001;}
                if(sp5.equals("nV")){ vS=vS*0.000000001;}
                if(sp5.equals("mV")){ vS=vS*0.001;}
                //capacidad
                if(sp8.equals("µF")){ cS=cS*0.000001;}
                if(sp8.equals("nF")){ cS=cS*0.000000001;}
                if(sp8.equals("mF")){ cS=cS*0.001;}
                //inductancia
                if(sp7.equals("µH")){ lS=lS*0.000001; }
                if(sp7.equals("nH")){ lS=lS*0.000000001;}
                if(sp7.equals("mH")){ lS=lS*0.001;}
                //REsistencia
                if(sp6.equals("µΩ")){ rS=rS*0.000001;}
                if(sp6.equals("nΩ")){rS=rS*0.000000001;}
                if(sp6.equals("mΩ")){ rS=rS*0.001;}


            }}catch (NullPointerException e1){

                // try para el circuito combinado
                try {if((this.getIntent().getStringExtra("comb").equals("COMBINADO"))) {
                    rC = Float.parseFloat(this.getIntent().getStringExtra("rCi"));
                    lC = Float.parseFloat(this.getIntent().getStringExtra("lCi"));
                    cC = Float.parseFloat(this.getIntent().getStringExtra("cCi"));
                    vC = Float.parseFloat(this.getIntent().getStringExtra("vCi"));
                    sp9 = this.getIntent().getStringExtra("sp9");
                    sp10 = this.getIntent().getStringExtra("sp10");
                    sp11 = this.getIntent().getStringExtra("sp11");
                    sp12 = this.getIntent().getStringExtra("sp12");
                    Log.e("dentro try3"," "+rC);
                    if(sp9.equals("µV")){ vS=vS*0.000001;}
                    if(sp9.equals("nV")){ vS=vS*0.000000001;}
                    if(sp9.equals("mV")){ vS=vS*0.001;}
                    //capacidad
                    if(sp12.equals("µF")){ cS=cS*0.000001;}
                    if(sp12.equals("nF")){ cS=cS*0.000000001;}
                    if(sp12.equals("mF")){ cS=cS*0.001;}
                    //inductancia
                    if(sp11.equals("µH")){ lS=lS*0.000001; }
                    if(sp11.equals("nH")){ lS=lS*0.000000001;}
                    if(sp11.equals("mH")){ lS=lS*0.001;}
                    //REsistencia
                    if(sp10.equals("µΩ")){ rS=rS*0.000001;}
                    if(sp10.equals("nΩ")){rS=rS*0.000000001;}
                    if(sp10.equals("mΩ")){ rS=rS*0.001;}
                }}catch (NullPointerException e2){

                }
            }
        }











        //para voltios
        /*if(sp1.equals("µV")||sp5.equals("µV")||sp9.equals("µV")){
            vS=vS*0.000001;
        }
        if(sp1.equals("nV")||sp5.equals("nV")||sp9.equals("nV")){
            vS=vS*0.000000001;
        }
        if(sp1.equals("mV")||sp5.equals("mV")||sp9.equals("mV")){
            vS=vS*0.001;
        }
        //capacidad
        if(sp4.equals("µF")||sp8.equals("µF")||sp12.equals("µF")){
            cS=cS*0.000001;
        }
        if(sp4.equals("nF")||sp8.equals("nF")||sp12.equals("nF")){
            cS=cS*0.000000001;
        }
        if(sp4.equals("mF")||sp8.equals("mF")||sp12.equals("mF")){
            cS=cS*0.001;
        }
        //inductancia
        if(sp3.equals("µH")||sp7.equals("µH")||sp11.equals("µH")){
            lS=lS*0.000001;
        }
        if(sp3.equals("nH")||sp7.equals("nH")||sp11.equals("nH")){
            lS=lS*0.000000001;
        }
        if(sp3.equals("mH")||sp7.equals("mH")||sp11.equals("mH")){
            lS=lS*0.001;
        }
        //REsistencia
        if(sp2.equals("µΩ")||sp6.equals("µΩ")||sp7.equals("µΩ")){
            rS=rS*0.000001;
        }
        if(sp2.equals("nΩ")||sp6.equals("nΩ")||sp7.equals("nΩ")){
            rS=rS*0.000000001;
        }
        if(sp2.equals("mΩ")||sp6.equals("mΩ")||sp7.equals("mΩ")){
            rS=rS*0.001;
        }
*/
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
