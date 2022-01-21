package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Ofertas extends AppCompatActivity {

    WebView oferta;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent acasa=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(acasa);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas);
       //volver a home



        oferta = (WebView)findViewById(R.id.web);

        oferta.setWebViewClient(new WebViewClient());

        WebSettings webSettings = oferta.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url="https://www.midiplomado.com.mx/wp/ofertas";
        oferta.loadUrl(url);

        oferta.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (oferta.canGoBack()) {
                                oferta.goBack();
                            }

                            return true;
                    }

                }
                return false;
            }
        });
       // String URL = "https://www.midiplomado.com.mx/wp/shop/";
    }


}