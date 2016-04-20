package com.uplock.uplock;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Inicializar alarm manager
    AlarmManager alarmManager;
    TimePicker alarmTimePicker;
    TextView alarmMensaje;
    Context context;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.context = this;

        //inicializar alarm manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //inicializar Time picker
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);

        //inicializar mensaje de actualizacion
        alarmMensaje = (TextView) findViewById(R.id.alarmMensaje);

        //crear instancia de Calendarios
        final Calendar calendar = Calendar.getInstance();

        //inicializar boton AplicarAlarma
        Button btnAplicarAlarma = (Button) findViewById(R.id.btnAplicarAlarma);

        //crear intent en el alarm receiver
        final Intent myIntent = new Intent(context,AlarmReceiver.class);

        //crear evento onClick para establecer alarma
        btnAplicarAlarma.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                //Obtiene la hora y minutos del time picker depende de la version de android que funcion se usara
                int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
                    calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());
                } else {
                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                }

                //Obtenemos hora y munuto en enteros para meterlos en un string
                int hour,minute;
                if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
                    hour = alarmTimePicker.getHour();
                    minute = alarmTimePicker.getMinute();
                } else {
                    hour = alarmTimePicker.getCurrentHour();
                    minute = alarmTimePicker.getCurrentMinute();
                }


                //Convertit la hora y minuto en string
                String hourString = String.valueOf(hour);
                String minuteString = String.valueOf(minute);

                //Convierte a formato de 12 horas
                if(hour > 12)
                {
                    hourString = String.valueOf(hour - 12);
                }
                if(minute < 10)
                {
                    minuteString = "0" + String.valueOf(minute);
                }
                //Metodo para cambiar el mensaje de la app
                actualizarMensaje("Alarma Activada!\nLa alarma sonara a las " + hourString+":"+minuteString );

                //crear pending intent el cual se encarga del delay hasta la fecha indicada en la alarma
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                //establecer alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);


            }
        });

        //inicializar boton btnApagarAlarma
        Button btnApagarAlarma = (Button) findViewById(R.id.btnApagarAlarma);
        //crear evento onClick para Apagar Alarma
        btnApagarAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarMensaje("Alarma Apagada!");

                alarmManager.cancel(pendingIntent);
            }
        });





    }

    private void actualizarMensaje(String mensaje) {
        alarmMensaje.setText(mensaje);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
