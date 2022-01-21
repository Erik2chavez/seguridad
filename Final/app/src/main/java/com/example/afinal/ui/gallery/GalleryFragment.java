package com.example.afinal.ui.gallery;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.afinal.DiplomadoActivity;
import com.example.afinal.MainActivity;
import com.example.afinal.R;
import com.google.android.datatransport.Event;

import java.net.URL;

public class GalleryFragment extends Fragment {
    ImageButton iv1;
    WebView wv1;
    private GalleryViewModel galleryViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);


        wv1 = (WebView)root.findViewById(R.id.txt_web);


        WebSettings webSettings = wv1.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String URL = "https://www.midiplomado.com.mx/wp/shop/";
        wv1.loadUrl(URL);



        wv1.setWebViewClient(new WebViewClient(){ @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //Verifica si es .pdf

            if (url.toLowerCase().endsWith(".pdf")){
                //Crea un Intent para abrir un archivo con MIME TYPE application/pdf
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(url), "application/pdf");
                try{
                    view.getContext().startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    //Error!
                }
            } else {  //Si no es .pdf simplemente carga la url en el WebView.
                wv1.loadUrl(url);
            }

            return true;
        }
        });
        wv1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (wv1.canGoBack()) {
                                wv1.goBack();
                            }

                            return true;
                    }

                }
                return false;
            }
        });

       // wv1.loadUrl(URL);

        return root;

        }

    }


