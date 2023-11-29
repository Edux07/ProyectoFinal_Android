package com.example.myapplication_bos_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Mainpersonaje extends AppCompatActivity {
    private EditText editarnombres;
    private Button btnCreateCharacter;
    BBDD gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_personaje);

        editarnombres = findViewById(R.id.Nombre);
        btnCreateCharacter = findViewById(R.id.button_Crear);
        editarnombres.setText(getIntent().getStringExtra("nombre"));
        gestor= new BBDD(this, "PERSONAJESDND", null, 1);

    }

    public void crearPersonaje(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        String nombre = editarnombres.getText().toString().toUpperCase();
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Introduzca el nombre de su personaje", Toast.LENGTH_SHORT).show();
        } else {
            intent.putExtra("nombre", editarnombres.getText().toString());
            MandarDatos.launch(intent);
        }
    }

    ActivityResultLauncher<Intent> MandarDatos = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {


        }
    });
}
