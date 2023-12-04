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

        // Inicialización de variables y referencias a elementos de la interfaz
        editarnombres = findViewById(R.id.Nombre);
        btnCreateCharacter = findViewById(R.id.button_Crear);
        editarnombres.setText(getIntent().getStringExtra("nombre"));

        // Inicializar el objeto para manejar la base de datos
        gestor = new BBDD(this, "PERSONAJESDND", null, 1);
    }

    // Método para crear un nuevo personaje
    public void crearPersonaje(View view) {
        // Crear un intent para ir a la actividad principal (MainActivity)
        Intent intent = new Intent(this, MainActivity.class);

        // Obtener el nombre del personaje del EditText
        String nombre = editarnombres.getText().toString().toUpperCase();

        // Verificar si el campo de nombre está vacío
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Introduzca el nombre de su personaje", Toast.LENGTH_SHORT).show();
        } else {
            // Agregar el nombre al intent y lanzar la actividad
            intent.putExtra("nombre", editarnombres.getText().toString());
            MandarDatos.launch(intent);
        }
    }

    // Manejador de resultados para la actividad principal (MainActivity)
    ActivityResultLauncher<Intent> MandarDatos = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            // Puedes realizar acciones adicionales si es necesario después de que la actividad principal finalice
        }
    });
}
