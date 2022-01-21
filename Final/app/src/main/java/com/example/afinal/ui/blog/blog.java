package com.example.afinal.ui.blog;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.afinal.R;

public class blog extends Fragment {

    private BlogViewModel blogViewModel;
    WebView fb;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root;
        root=inflater.inflate(R.layout.blog_fragment, container, false);


        fb=(WebView)root.findViewById(R.id.wvfb);
        fb.setWebViewClient(new WebViewClient());
        WebSettings webSettings= fb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        fb.loadUrl("https://www.facebook." +
                "com/pages/category/Education-Website/midiplomado/posts/");
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        blogViewModel  =
                ViewModelProviders.of(this).get(BlogViewModel.class);


        fb.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                     switch (keyCode) {
                         case KeyEvent.KEYCODE_BACK:
                             if (fb.canGoBack()) {
                                 fb.goBack();
                             }

                             return true;
                     }
                }
                return false;
            }
        });
    }

}