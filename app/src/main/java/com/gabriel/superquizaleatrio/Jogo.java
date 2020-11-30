package com.gabriel.superquizaleatrio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Jogo extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "Jogo";
    private SensorManager sensorManager;
    Sensor accelerometer;
    TextView opcao1, opcao2, timer, pergunta;
    String e = "";
    int aleatorio;
    String [] perguntas = new String[10];
    String [] cima = new String[10];
    String [] baixo = new String[10];
    String [] respostas = new String[10];
    int nmrvelho = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        Bundle extras = getIntent().getExtras();

        if(extras!=null){

            nmrvelho = extras.getInt("nmrAleatorio");
        }

        respostas[0] = "Rio Branco";

        respostas[1] = "América do Sul";

        respostas[2] = "Líbia";

        respostas[3] = "Guatemala";

        respostas[4] = "Arábia Saudita";

        respostas[5] = "37";

        respostas[6] = "Cidade do México";

        respostas[7] = "Rússia";

        respostas[8] = "Casaquistão";

        respostas[9] = "Lucro";


        perguntas[0] = "Qual é a capital do  Acre?";
        cima[0] = "Acre";
        baixo[0] = "Rio Branco";


        perguntas[1] = "O Brasil está localizado em qual continente?";
        cima[1] = "América do Sul";
        baixo[1] = "Europa";

        perguntas[2] = "Qual desses NÃO É uma ilha?";
        cima[2] = "Indonésia";
        baixo[2] = "Líbia";

        perguntas[3] = "Qual é a capital da Guatemala?";
        cima[3] = "Guatemala";
        baixo[3] = "São Tomé";

        perguntas[4] = "Qual desses países NÃO É africano?";
        cima[4] = "África do Sul";
        baixo[4] = "Arábia Saudita";

        perguntas[5] = "Quantos países tem a América?";
        cima[5] = "37";
        baixo[5] = "45";

        perguntas[6] = "Qual a capital do México?";
        cima[6] = "Espano";
        baixo[6] = "Cidade do México";

        perguntas[7] = "Além da Turquia, qual outro país está presente em dois continentes?";
        cima[7] = "Alemanha";
        baixo[7] = "Rússia";

        perguntas[8] = "Qual desses países não é Europeu?";
        cima[8] = "Macedonia";
        baixo[8] = "Casaquistão";

        perguntas[9] = "Qual é a base do sistema capitalista?";
        cima[9] = "Lucro";
        baixo[9] = "Salário";


        opcao1 = (TextView) findViewById(R.id.opcao1);
        opcao2 = (TextView) findViewById(R.id.opcao2);
        timer = (TextView) findViewById(R.id.timer);
        pergunta = (TextView) findViewById(R.id.pergunta);




        Random nmr = new Random();
        int aleatorio = nmr.nextInt(10);

        while ( aleatorio == nmrvelho ){
            aleatorio = nmr.nextInt();
        }
        pergunta.setText(perguntas[aleatorio]);
        opcao1.setText(cima[aleatorio]);
        opcao2.setText(baixo[aleatorio]);



        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);



    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        if(e.equals("")) {
            if (event.values[2] > 8) {

                e = opcao1.getText().toString();


            }
            if (event.values[2] < -10) {
                e = opcao2.getText().toString();
            }
            resposta(e);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void resposta(String resposta){
        Intent veredito = new Intent(this, Veredito.class);
        String CouB = resposta;

        Boolean acertou = false;

        if(CouB.equals(respostas[aleatorio])){


        acertou = true;
        }
        veredito.putExtra("acertou",acertou);
        veredito.putExtra("nmrAleatorio",aleatorio);

        startActivity(veredito);
        finish();


    }

}