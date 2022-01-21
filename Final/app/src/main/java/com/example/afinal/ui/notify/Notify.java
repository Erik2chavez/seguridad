package com.example.afinal.ui.notify;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.afinal.DiplomadoActivity;
import com.example.afinal.R;

public class Notify extends Fragment {

    private NotifyViewModel notifyViewModel;
    int acum=0;
    RequestQueue rq;
    JsonRequest jrq;
    ImageButton ib;
    EditText txtUser,txtPwd;
    Button btnRegistrar;

    public static Notify newInstance() {
        return new Notify();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.notify_fragment, container, false);

        txtUser=(EditText) view.findViewById(R.id.txtuser);
        txtPwd=(EditText) view.findViewById(R.id.txtpwd);
        btnRegistrar=(Button) view.findViewById(R.id.btnregistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesion_admin();
            }
        });

        //ib=(ImageButton)view.findViewById(R.id.imbs);
        /*ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin();
            }
        });
        rq= Volley.newRequestQueue(getContext());
*/
        return view;
    }

    private void sesion_admin() {
        String usuario,pwd;
        usuario=txtUser.getText().toString();
        pwd=txtPwd.getText().toString();

        if (usuario.equals("MiDi")){
            if (pwd.equals("admin123")) {
                Intent i = new Intent(getContext(), DiplomadoActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getContext(),"Contraseña Incorrecta",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getContext(),"Usuario Incorrecto",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notifyViewModel = ViewModelProviders.of(this).get(NotifyViewModel.class);
        // TODO: Use the ViewModel
    }
    public void admin(){
        acum++;
        if (acum>8){
            String url="https://determinism-stake.000webhostapp.com/generar_notificacion.php?titulo=%22Prueba%22&mensaje=%22funciona%20el%20envio%20de%20notificaciones%22";
            jrq = new JsonObjectRequest(Request.Method.GET, url, null, null, null);
            rq.add(jrq);
        }
    }

}