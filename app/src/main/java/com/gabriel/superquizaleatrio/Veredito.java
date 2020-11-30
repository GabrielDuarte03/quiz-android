package com.gabriel.superquizaleatrio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class Veredito extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veredito);
        Boolean value = false;
        int nmr = 0;
        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            value = extras.getBoolean("acertou");
            nmr = extras.getInt("nmrAleatorio");
        }
        Acertos acertos = new Acertos();
        if(value) {
            ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.main);
            ImageView img = (ImageView) findViewById(R.id.img);
            TextView veredito = (TextView) findViewById(R.id.veredito);
            veredito.setText("ACERTOU :)");
            layout.setBackgroundColor(Color.parseColor("#13921A"));
            img.setImageResource(R.drawable.circuloverde);

            acertos.setNmrAcertos((acertos.getNmrAcertos() + 1));
        }else{
            acertos.setNmrErros((acertos.getNmrAcertos() + 1));
        }

        int finalNmr = nmr;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

                if(acertos.getNmrAcertos()+acertos.getNmrErros()==5){

                    Intent intent = new Intent(getApplicationContext(),Final.class);
                    startActivity(intent);
                    finish();

                }else{

                    Intent intent = new Intent(getApplicationContext(),Jogo.class);
                    intent.putExtra("nmrAleatorio", finalNmr);
                    startActivity(intent);
                    finish();

                }


            }
        },5000);


    }
}