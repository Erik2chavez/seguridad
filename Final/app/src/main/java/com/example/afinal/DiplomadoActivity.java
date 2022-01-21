package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;

import java.util.Properties;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;


public class DiplomadoActivity extends AppCompatActivity {

    TextView tit, tx;
    Button sendm;

    RequestQueue rq;
    JsonRequest jrq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diplomado);
    //    comprar=(Button)findViewById(R.id.comprar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //asignarValores();
        tit=(TextView)findViewById(R.id.titulo);
        tx=(TextView)findViewById(R.id.contenido);
        sendm=(Button)findViewById(R.id.enviar_notify);
        rq= Volley.newRequestQueue(getApplicationContext());


        sendm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mandar();
            }
        });

    }

    private void mandar() {
        String titulo,contenido;
        titulo= tit.getText().toString();
        contenido= tx.getText().toString();
        if (tit.getText().toString().trim().length()>0){
            if (tx.getText().toString().trim().length()>0) {
                String url="https://determinism-stake.000webhostapp.com/generar_notificacion.php?titulo="+tit.getText().toString()+"&mensaje="+tx.getText().toString();
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, null, null);
                rq.add(jrq);
                Toast.makeText(getApplicationContext(),"Notificacion enviada",Toast.LENGTH_LONG).show();

                tit.setText("");
                tx.setText("");
            } else {
                Toast.makeText(getApplicationContext(),"Contenido vacio",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(),"Titulo vacio",Toast.LENGTH_LONG).show();
        }



    }

}