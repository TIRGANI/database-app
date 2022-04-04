package com.example.testappv00;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import adress.Ip;
import beans.Star;

public class AddEtudiant extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Donnees : ";
    private EditText nom;
    private EditText prenom;
    private Spinner ville;
    private RadioButton m;
    private RadioButton f;
    private Button add,listEtudiants;
    RequestQueue requestQueue;

    String insertUrl = "http://"+ Ip.ip+":8080/Projet01/ws/createEtudiant.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);
        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        ville = (Spinner) findViewById(R.id.ville);
        add = (Button) findViewById(R.id.add);
        listEtudiants = (Button) findViewById(R.id.listEtudiants);
        m = (RadioButton) findViewById(R.id.m);
        f = (RadioButton) findViewById(R.id.f);
        add.setOnClickListener(this);
        listEtudiants.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        if (v == add) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest request = new StringRequest(Request.Method.POST,
                    insertUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //
                    Toast toast = Toast.makeText(AddEtudiant.this, "bien Ajouter", Toast.LENGTH_SHORT);
                    toast.show();
                    //
                    Log.d(TAG, response);
                    Type type = new TypeToken<Collection<Star>>(){}.getType();
                    Collection<Star> etudiants = new Gson().fromJson(response, type);
                    for(Star e : etudiants){
                        Log.d(TAG, e.toString());
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    String sexe = "";
                    if(m.isChecked())
                        sexe = "homme";
                    else
                        sexe = "femme";
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("nom", nom.getText().toString());
                    params.put("prenom", prenom.getText().toString());
                    params.put("ville", ville.getSelectedItem().toString());
                    params.put("sexe", sexe);

                    return params;
                }
            };
            requestQueue.add(request);
        }
        if(v==listEtudiants)
        {

            Intent intent = new Intent(AddEtudiant.this,MainActivity.class);
            AddEtudiant.this.startActivity(intent);

            this.finish();
        }
    }

}