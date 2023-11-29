package com.example.myapplication_bos_final;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText eTT7;
    Spinner sp;
    String nombre, nombrePersonaje;
    String resultadoHabilidades;
    List<Integer> stats;

    BBDD gestor;

    int i1, i2, i3, i4, i5, i6;

    String[] clases = {"Bardo", "Bárbaro", "Brujo", "Clérigo",
            "Druida", "Explorador", "Guerrero", "Hechicero", "Mago", "Monje", "Paladín",
            "Pícaro"};
    //String[] iconos =  {R.drawable.icono1, R.drawable.icono2, R.drawable.icono3,
                     //R.drawable.icono4, R.drawable.icono5, R.drawable.icono6,
                     //R.drawable.icono7, R.drawable.icono8, R.drawable.icono9,
                     //R.drawable.icono10, R.drawable.icono11, R.drawable.icono12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activyti);
        stats = new ArrayList<>();
        gestor = new BBDD(this, "PERSONAJESDND", null, 1);
        eTT7 = findViewById(R.id.eTT7);
        sp = findViewById(R.id.sp);
        Intent i = getIntent();
        nombre = i.getStringExtra("nombre");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,clases);

        // Especificar el diseño que se utilizará cuando aparece la lista de opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplicar el adaptador al Spinner
        sp.setAdapter(adapter);
    }

    public void presionHabilidades(View view) {
        Intent i1 = new Intent(this, MainHabilidades.class);
        cambiarVentanaHabilidades.launch(i1);
    }

    public void presionEstadisticas(View view) {
        Intent i2 = new Intent(this, Mainestadisticas.class);
        cambiarVentanaEstadisticas.launch(i2);
    }

    public void guardarPersonaje(View view) {
        nombrePersonaje = eTT7.getText().toString();
        String claseSeleccionada = sp.getSelectedItem().toString();
        SQLiteDatabase bd= gestor.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("NOMBREJUGADOR", nombre);
        registro.put("NOMBREPERSONAJE", nombrePersonaje);
        registro.put("CLASE", claseSeleccionada);
        registro.put("FUERZA", i1);
        registro.put("DESTREZA", i2);
        registro.put("CONSTITUCION", i3);
        registro.put("INTELIGENCIA", i4);
        registro.put("SABIDURIA", i5);
        registro.put("CARISMA", i6);
        registro.put("HABILIDADES", resultadoHabilidades);

        bd.insert("PERSONAJESDND", null, registro);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("saludo", "Mensaje de saludo");
        setResult(RESULT_OK, resultIntent);
        finish();


    }

    ActivityResultLauncher<Intent> cambiarVentanaHabilidades = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                resultadoHabilidades = result.getData().getStringExtra("selectedSkills");
                Toast.makeText(MainActivity.this, resultadoHabilidades, Toast.LENGTH_SHORT).show();
            }
        }
    });

    ActivityResultLauncher<Intent> cambiarVentanaEstadisticas = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                stats = result.getData().getIntegerArrayListExtra("statValues");
                if (stats != null && stats.size() == 6) {
                    i1 = stats.get(0);
                    i2 = stats.get(1);
                    i3 = stats.get(2);
                    i4 = stats.get(3);
                    i5 = stats.get(4);
                    i6 = stats.get(5);
                    Toast.makeText(MainActivity.this, String.valueOf(i1) + String.valueOf(i2) + String.valueOf(i3) + String.valueOf(i4) + String.valueOf(i5) + String.valueOf(i6), Toast.LENGTH_LONG).show();
                } else {
                    // Handle the case where stats are null or not the expected size
                    Toast.makeText(MainActivity.this, "Error in stats", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });
}
