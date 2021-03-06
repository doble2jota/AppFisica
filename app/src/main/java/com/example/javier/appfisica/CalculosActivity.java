package com.example.javier.appfisica;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private TextView tenR_1;
    private TextView tenR_2;
    private TextView tenC;
    private TextView tenL;
    private TextView potdisipada;




    private double rS;
    private double lS;
    private double cS;
    private double vS;
    private double rP;
    private double lP;
    private double cP;
    private double vP;
    private double rC;
    private double rC_1;
    private double rC_2;
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
    double zAB; //Impedancia combinada en el paralelo
    private double realAB, imaginarioAB, realTotal, imaginarioTotal; //Combinado

    private double xLp,xCp,iRp,iLp,iCp,angR,angL,angC,intensidadTotalx,intensidadTotaly,intensidadModulo,anguloFi; // reactncia inductiva del L del paralelo

    float angulo;
    float anguloAB;
    float pot, pot1,pot2;

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
    String sp10_1;
    String sp10_2;
    String sp11;
    String sp12;

    final float w = 2 * (float) Math.PI* hz;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        //try para comparar , puesto que si hay nulls los if no funcionan

        try{
            setContentView(R.layout.activity_calculos_sp1);
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

                TextView alfa=(TextView)findViewById(R.id.alfa);

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

                TextView moduloz= (TextView)findViewById(R.id.zmodulo);

                impT.setText(" " + df.format(zT)+"   ");
                alfa.setText(" "+df.format(angulo)+"º" );
                moduloz.setText(" "+df.format( zT*Math.cos(Math.toRadians(angulo))) +" + j "+df.format(zT*Math.sin(Math.toRadians(angulo))) );

                //Calculamos la intensidad que circula por el circuito
                iTotalserie=(vS/zT);
                angulointensidad=0-angulo;
                //añadimos corriente

                corrT.setText(df.format(iTotalserie)+"   ");
                TextView alfai= (TextView)findViewById(R.id.alfaI2);
                alfai.setText(df.format(angulointensidad)+"º");

                TextView moduloI= (TextView)findViewById(R.id.Imodulo);
                moduloI.setText(" "+df.format( iTotalserie*Math.cos(Math.toRadians(angulointensidad))) +" + j "+df.format(iTotalserie*Math.sin(Math.toRadians(angulointensidad))) );


                //calculamos las tensiones
                tensionRS = (rS * iTotalserie);
                tensionCS = (xC*iTotalserie);
                anguloTensionCS = (-90+(-angulo));
                tensionLS =(xL * iTotalserie);
                anguloTensionLS=(90+(-angulo));

                tenR.setText(" " + df.format(tensionRS) + " "+ df.format(-angulo)+"º");
                tenC.setText(" " + df.format(tensionCS) + " "+ df.format(anguloTensionCS)+"º");
                tenL.setText(" " + df.format(tensionLS) + " "+ df.format(anguloTensionLS)+"º");

                //potencias disipadas


                pot= (float) (rS*Math.pow((iTotalserie/Math.sqrt(2)),2));

                potdisipada=(TextView)findViewById(R.id.potenciadisipadaS);
                potdisipada.setText(df.format(pot)+ " W");



                //captura de excepción
            }}catch (NullPointerException e){
            // try para el circuito paralelo
            try {if((this.getIntent().getStringExtra("paralelo").equals("PARALELO"))) {

                setContentView(R.layout.activity_calculos_sp2);

                DecimalFormat df= new DecimalFormat("0.000");

                TextView intR=(TextView)findViewById(R.id.intensidadR);
                TextView intC=(TextView)findViewById(R.id.intensidadC);
                TextView intL=(TextView)findViewById(R.id.intensidadL);
                TextView anguC=(TextView)findViewById(R.id.anguloC);
                TextView anguL=(TextView)findViewById(R.id.anguloL);
                TextView anguR=(TextView)findViewById(R.id.anguloR);
                TextView anguiT=(TextView)findViewById(R.id.anguiT);
                TextView iC=(TextView)findViewById(R.id.iC);
                TextView iL=(TextView)findViewById(R.id.iL);
                TextView iR=(TextView)findViewById(R.id.iR);
                TextView impP=(TextView)findViewById(R.id.impedanciaP);
                TextView anguloimpe=(TextView)findViewById(R.id.anguloImpe);
                TextView impedanciaCosSen=(TextView)findViewById(R.id.impedanciaCosSen);
                TextView tensionR=(TextView)findViewById(R.id.tensionR);
                TextView tensionL=(TextView)findViewById(R.id.tensionL);
                TextView tensionC=(TextView)findViewById(R.id.tensionC);



                TextView intT=(TextView)findViewById(R.id.intensidadTotal);
                TextView comportamiento=(TextView)findViewById(R.id.comportamiento);
                TextView intensidadMod=(TextView)findViewById(R.id.intensidadMod);


                rP = Float.parseFloat(this.getIntent().getStringExtra("rPi"));
                lP = Float.parseFloat(this.getIntent().getStringExtra("lPi"));
                cP = Float.parseFloat(this.getIntent().getStringExtra("cPi"));
                vP = Float.parseFloat(this.getIntent().getStringExtra("vPi"));
                sp5 = this.getIntent().getStringExtra("sp5");
                sp6 = this.getIntent().getStringExtra("sp6");
                sp7 = this.getIntent().getStringExtra("sp7");
                sp8 = this.getIntent().getStringExtra("sp8");
                Log.e("dentro try2"," "+rP);


                if(sp5.equals("µV")){ vP=vP*0.000001;}
                if(sp5.equals("nV")){ vP=vP*0.000000001;}
                if(sp5.equals("mV")){ vP=vP*0.001;}
                //capacidad
                if(sp8.equals("µF")){ cP=cP*0.000001;}
                if(sp8.equals("nF")){ cP=cP*0.000000001;}
                if(sp8.equals("mF")){ cP=cP*0.001;}
                //inductancia
                if(sp7.equals("µH")){ lP=lP*0.000001; }
                if(sp7.equals("nH")){ lP=lP*0.000000001;}
                if(sp7.equals("mH")){ lP=lP*0.001;}
                //REsistencia
                if(sp6.equals("µΩ")){ rS=rS*0.000001;}
                if(sp6.equals("nΩ")){rS=rS*0.000000001;}
                if(sp6.equals("mΩ")){ rS=rS*0.001;}


                //calculos

                xLp=2 * (float) Math.PI* 50* lP; // angulo 90º
                Log.e("lp","Lp "+lP);
                Log.e("Xlp","xLp "+xLp);
                xCp=1/(2*(float) Math.PI* 50* cP); //angulo -90º
                Log.e("Xcp","xCp "+xCp);
                //intensidades parciales

                iRp=(vP/rP);
                angR= 0;


                intR.setText(" " + df.format(iRp) );
                anguR.setText( df.format(angR)+"º");
                iR.setText(" "+df.format( iRp*Math.cos(Math.toRadians(angR))) +" + j "+df.format(iRp*Math.sin(Math.toRadians(angR))) );



                iLp=(vP/xLp);
                angL= -90;
                intL.setText(" " + df.format(iLp) );
                anguL.setText( df.format(angL)+"º");
                iL.setText(" "+df.format( iLp*Math.cos(Math.toRadians(angL))) +" + j "+df.format(iLp*Math.sin(Math.toRadians(angL))) );


                iCp=(vP/xCp);
                angC= 90;
                intC.setText(" " + df.format(iCp) );
                anguC.setText( df.format(angC)+"º");
                iC.setText(" "+df.format( iCp*Math.cos(Math.toRadians(angC))) +" + j "+df.format(iCp*Math.sin(Math.toRadians(angC))) );
                //intensidad totales

                intensidadTotalx=iRp;
                intensidadTotaly=-iLp+iCp;
                intensidadMod.setText(" " + df.format(intensidadTotalx) + "+  j " + df.format(intensidadTotaly));

                if(iLp>iCp){comportamiento.setText("Inductivo");}else{comportamiento.setText("Capacitivo");}

                //intensidad modulo
                intensidadModulo=Math.sqrt(Math.pow(intensidadTotalx,2)+Math.pow(intensidadTotaly,2));
                anguloFi=(float) Math.atan(intensidadTotaly/intensidadTotalx);

                anguloFi= (float) Math.toDegrees(anguloFi);

                intT.setText(" " + df.format(intensidadModulo));
                anguiT.setText(" "+ df.format(anguloFi)+ "º" );
                TextView potdisipadaP;
                potdisipadaP=(TextView)findViewById(R.id.potenciadisipadaP);
                potdisipadaP.setText(df.format(pot)+ " W");

                pot= (float) (rC*Math.pow((intensidadTotalx/Math.sqrt(2)),2));

                float impedanciap= (float) (vP/intensidadModulo);
                impP.setText(" "+impedanciap  );
                anguloimpe.setText(" "+ df.format(-anguloFi));

                impedanciaCosSen.setText(" "+df.format( impedanciap*Math.cos(Math.toRadians(-anguloFi))) +" + j "+df.format(impedanciap*Math.sin(Math.toRadians(-anguloFi))) );
                tensionR.setText(" "+ df.format(iRp*impedanciap));
                tensionC.setText(" "+ df.format(iCp*impedanciap));
                tensionL.setText(" "+ df.format(iLp*impedanciap));

            }}catch (NullPointerException e1){

                // try para el circuito combinado
                try {if((this.getIntent().getStringExtra("comb").equals("COMBINADO"))) {

                    setContentView(R.layout.activity_calculos_c1);


                    DecimalFormat df= new DecimalFormat("0.000");

                    impT=(TextView)findViewById(R.id.impedanciat);

                    corrT=(TextView)findViewById(R.id.corrienteTotal);
                    tenR_1=(TextView)findViewById(R.id.tensionR1);
                    tenR_2=(TextView)findViewById(R.id.tensionR2);
                    tenC=(TextView)findViewById(R.id.tensionC);
                    tenL=(TextView)findViewById(R.id.tensionL);
                    TextView Z= (TextView)findViewById((R.id.impedanciat));
                    TextView alfaZ= (TextView)findViewById((R.id.alfatt));
                    TextView modulozT= (TextView)findViewById((R.id.moduloZt));
                    TextView corrienteTotal= (TextView)findViewById((R.id.corrienteTotal));
                    TextView alfaILR1= (TextView)findViewById((R.id.alfailr1));
                    TextView ilr1= (TextView)findViewById((R.id.Ilr1));
                    TextView icbarras= (TextView)findViewById((R.id.ICbarras));
                    TextView alfaic= (TextView)findViewById((R.id.alfaIC));
                    TextView icmodulo= (TextView)findViewById((R.id.ICmodulo));
                    TextView ir2barra= (TextView)findViewById((R.id.IR2barra));
                    TextView alfalr2= (TextView)findViewById((R.id.alfaIr2));
                    TextView ir2modulo= (TextView)findViewById((R.id.IR2modulo));



                    rC_1 = Float.parseFloat(this.getIntent().getStringExtra("rCi_1"));
                    rC_2 = Float.parseFloat(this.getIntent().getStringExtra("rCi_2"));
                    lC = Float.parseFloat(this.getIntent().getStringExtra("lCi"));
                    cC = Float.parseFloat(this.getIntent().getStringExtra("cCi"));
                    vC = Float.parseFloat(this.getIntent().getStringExtra("vCi"));
                    sp9 = this.getIntent().getStringExtra("sp9");
                    sp10_1 = this.getIntent().getStringExtra("sp10_1");
                    sp10_2 = this.getIntent().getStringExtra("sp10_2");
                    sp11 = this.getIntent().getStringExtra("sp11");
                    sp12 = this.getIntent().getStringExtra("sp12");
                    Log.e("dentro try3"," "+rC);
                    if(sp9.equals("µV")){ vC=vC*0.000001;}
                    if(sp9.equals("nV")){ vC=vC*0.000000001;}
                    if(sp9.equals("mV")){ vC=vC*0.001;}
                    //capacidad
                    if(sp12.equals("µF")){ cC=cC*0.000001;}
                    if(sp12.equals("nF")){ cC=cC*0.000000001;}
                    if(sp12.equals("mF")){ cC=cC*0.001;}
                    //inductancia
                    if(sp11.equals("µH")){ lC=lC*0.000001; }
                    if(sp11.equals("nH")){ lC=lC*0.000000001;}
                    if(sp11.equals("mH")){ lC=lC*0.001;}
                    //Resistencia R1
                    if(sp10_1.equals("µΩ")){ rC_1=rC_1*0.000001;}
                    if(sp10_1.equals("nΩ")){rC_1=rC_1*0.000000001;}
                    if(sp10_1.equals("mΩ")){ rC_1=rC_1*0.001;}
                    //Resistencia R2
                    if(sp10_2.equals("µΩ")){ rC_2=rC_2*0.000001;}
                    if(sp10_2.equals("nΩ")){rC_2=rC_2*0.000000001;}
                    if(sp10_2.equals("mΩ")){ rC_2=rC_2*0.001;}

                    //Calculos

                    //Inductancia
                    xL=w*lC;

                    //Reactancia
                    xC=(1/(w*cC));

                    Log.e("XC"," "+rC_2);


                    //Impedancia en el paralelo

                    zAB = xC*rC_2/((float)Math.sqrt((float)Math.pow(xC, 2)+(float)Math.pow(rC_2, 2)));

                    anguloAB=  (float) Math.atan(-xC/rC_2);
                    anguloAB= (float) Math.toDegrees(anguloAB);
                    anguloAB=   -90 - anguloAB;


                    //Impedancia en R2 y C
                    //impT.setText(" " + df.format(zAB)+" ");
                    //alfaZ.setText(df.format(anguloAB)+"º");

                    //Impedancia total

                    Log.e("angulo"," "+anguloAB);
                    realAB=zAB * (float) Math.cos(Math.toRadians(anguloAB));

                    Log.e("realab"," "+ realAB);
                    imaginarioAB=zAB * (float) Math.sin(Math.toRadians(anguloAB));
                    Log.e("realab"," "+imaginarioAB);

                    realTotal = realAB + rC_1;

                    Log.e("total"," "+realTotal);
                    imaginarioTotal = xL + imaginarioAB;
                    Log.e("xl"," "+xL);
                    Log.e("total"," "+imaginarioTotal);

                    zT = (float)Math.sqrt((float)Math.pow(realTotal, 2)+(float)Math.pow(imaginarioTotal, 2));
                    angulo = (float) Math.atan(imaginarioTotal/realTotal);
                    angulo= (float) Math.toDegrees(angulo);


                    //añadimos al textview

                    //Impedancia total
                    impT.setText(" " + df.format(zT));
                    alfaZ.setText(df.format(angulo)+"º");

                    modulozT.setText(" "+df.format( zT*Math.cos(Math.toRadians(angulo))) +" + j "+df.format(zT*Math.sin(Math.toRadians(angulo))) );

                    //Intensidad que pasa por L y R1

                    double iLR, anguloLR;
                    iLR = vC / zT;
                    anguloLR = 0 - angulo;

                    //añadimos al textview

                    //intensidad en R1 y L
                    corrienteTotal.setText(df.format(iLR)+" ");
                    alfaILR1.setText(df.format(anguloLR)+"º");
                    ilr1.setText(" "+df.format( zT*Math.cos(Math.toRadians(angulo))) +" + j "+df.format(zT*Math.sin(Math.toRadians(angulo))) );


                    //Tension en R1 y L
                    double tensionR1, tensionL, angulotR1, angulotL;
                    tensionR1 = iLR * rC_1;
                    tensionL = iLR * xL;
                    angulotR1 = 0 + anguloLR;
                    angulotL = 90 + anguloLR;
                    tenR_1.setText(df.format(tensionR1)+" "+df.format(angulotR1)+"º");
                    tenL.setText(df.format(tensionL)+" "+df.format(angulotL)+"º");

                    //Intensidad que pasa por R2
                    double iR2, anguloR2;

                    iR2= iLR * zAB / rC_2;
                    anguloR2 = (anguloLR + anguloAB) - (0);

                    //Intensidad en R2
                    ir2barra.setText(df.format(iR2)+" ");
                    alfalr2.setText(df.format(anguloR2)+"º");
                    ir2modulo.setText(" "+df.format( iR2*Math.cos(Math.toRadians(anguloR2))) +" + j "+df.format(iR2*Math.sin(Math.toRadians(anguloR2))) );

                    //Tension en R2

                    double tensionR2, angulotR2;
                    tensionR2 = iR2 * rC_2;
                    angulotR2 = 0 + anguloR2;
                    tenR_2.setText(df.format(tensionR2)+" "+df.format(angulotR2)+"º");


                    //Intensidad que pasa por C

                    double iC, anguloC;

                    iC= iLR * zAB / xC;
                    anguloC = (anguloLR + anguloAB) - (-90);

                    //Intensidad en C
                    //corrT.setText(df.format(iC)+" ");
                    icbarras.setText(df.format(iC));
                    alfaic.setText(df.format(anguloC)+"º");
                    icmodulo.setText(" "+df.format( iC*Math.cos(Math.toRadians(anguloC))) +" + j "+df.format(iC*Math.sin(Math.toRadians(anguloC))) );

                    //Tension en C
                    double tensionC, angulotC;
                    tensionC = iC * xC;
                    angulotR2 = -90 + anguloC;

                    //¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿ES ESTE ANGULO????????????????????????????
                    tenC.setText(df.format(tensionC)+" "+df.format(angulotR2)+"º");


                    pot2= (float) (rC_2*Math.pow((iR2/Math.sqrt(2)),2));
                    pot1= (float) (rC_1*Math.pow((iLR/Math.sqrt(2)),2));

                    pot= pot1+pot2;
                    TextView potdisipadaC;
                    potdisipadaC=(TextView)findViewById(R.id.potenciadisipadaC);
                    potdisipadaC.setText(df.format(pot)+ " W");

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
        if (id ==  R.id.btInfo) {
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