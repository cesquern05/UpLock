package com.uplock.uplock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import java.util.Random;

/**
 * Created by cesquer on 07/05/2016.
 */
public class Pop extends Activity {


    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = this;

        setContentView(R.layout.popuppuzzle);
        TextView lblNum1 = (TextView) findViewById(R.id.lblNum1);
        TextView lblOperador = (TextView) findViewById(R.id.lblOperador);
        TextView lblNum2 = (TextView) findViewById(R.id.lblNum2);
        Button btnRespuesta1 = (Button) findViewById(R.id.btnRespuesta1);
        Button btnRespuesta2 = (Button) findViewById(R.id.btnRespuesta2);
        final TextView mensaje = (TextView) findViewById(R.id.alarmMensaje);


        final Intent myIntent = new Intent(context,AlarmReceiver.class);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        Random rnd = new Random();
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        final int Numero1,Numero2,Operador,PosicionCorrecta,Respuesta;

        Numero1 = rnd.nextInt(100);
        Log.e("Numero1",String.valueOf(Numero1));
        Numero2 = rnd.nextInt(100);
        Log.e("Numero2", String.valueOf(Numero2));
        Operador = rnd.nextInt(2);
        Log.e("Operador", String.valueOf(Operador));
        PosicionCorrecta=rnd.nextInt(2);

        lblNum1.setText(String.valueOf(Numero1));

        if(Operador==0) {
            lblOperador.setText("-");
            Respuesta = Numero1 - Numero2;
        }
        else {
            lblOperador.setText("+");
            Respuesta = Numero1 + Numero2;
        }

        lblNum2.setText(String.valueOf(Numero2));

        if(PosicionCorrecta==0) {
            btnRespuesta1.setText(String.valueOf(Respuesta));
            btnRespuesta2.setText(String.valueOf(rnd.nextInt(100)));
        }
        else {
            btnRespuesta2.setText(String.valueOf(Respuesta));
            btnRespuesta1.setText(String.valueOf(rnd.nextInt(100)));
        }

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        btnRespuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PosicionCorrecta == 0) {
                    finish();
                    myIntent.putExtra("extra", "alarmOFF");
                    sendBroadcast(myIntent);
                    MainActivity ma = new MainActivity();
                    ma.alarmMensaje.setText("Alarma Apagada!");
                }
            }
        });

        btnRespuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PosicionCorrecta == 1) {
                    finish();
                    myIntent.putExtra("extra", "alarmOFF");
                    sendBroadcast(myIntent);;
                    MainActivity ma = new MainActivity();
                    ma.alarmMensaje.setText("Alarma Apagada!");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
