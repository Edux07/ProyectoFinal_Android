package com.example.myapplication_bos_final;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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
    Spinner spinner;
    String nombre, nombrePersonaje;
    String resultadoHabilidades;
    List<Integer> stats;

    BBDD gestor;

    int i1, i2, i3, i4, i5, i6;

    String[] clases = {"Bardo", "Bárbaro", "Brujo", "Clérigo", "Druida", "Explorador", "Guerrero", "Hechicero", "Mago", "Monje", "Paladín", "Pícaro"};
    int[] iconos = {R.drawable.icono1, R.drawable.icono2, R.drawable.icono3, R.drawable.icono4, R.drawable.icono5, R.drawable.icono6, R.drawable.icono7, R.drawable.icono8, R.drawable.icono9, R.drawable.icono10, R.drawable.icono11, R.drawable.icono12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activyti);
        stats = new ArrayList<>();
        gestor = new BBDD(this, "PERSONAJESDND", null, 1);
        eTT7 = findViewById(R.id.eTT7);
        spinner = findViewById(R.id.spinner);
        Puntoextra adaptador1=new Puntoextra();
        spinner.setAdapter(adaptador1);
        Intent i = getIntent();
        nombre = i.getStringExtra("nombre");
    }
    class Puntoextra extends BaseAdapter {
        @Override
        public int getCount() {
            return clases.length;
        }

        @Override
        public Object getItem(int position) {
            return clases[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            convertView = inflater.inflate(R.layout.itemspinner, null);
            ImageView iv1 = convertView.findViewById(R.id.iconos);
            TextView tv1 = convertView.findViewById(R.id.clases);
            iv1.setImageResource(iconos[position]);
            tv1.setText(clases[position]);
            return convertView;
        }
    }
    // Método para manejar el botón de Estadísticas
    public void presionHabilidades(View view) {
        Intent i1 = new Intent(this, MainHabilidades.class);
        cambiarVentanaHabilidades.launch(i1);
        // Ocultar el botón después de hacer clic
        Button btnHabilidades = findViewById(R.id.Habilidades);
        btnHabilidades.setVisibility(View.INVISIBLE);
    }

    public void presionEstadisticas(View view) {
        Intent i2 = new Intent(this, Mainestadisticas.class);
        cambiarVentanaEstadisticas.launch(i2);
        // Ocultar el botón después de hacer clic
        Button btnEstadisticas = findViewById(R.id.Estadisticas);
        btnEstadisticas.setVisibility(View.INVISIBLE);
    }


    // Método para guardar el personaje en la base de datos
    public void guardarPersonaje(View view) {
        // Obtener datos del formulario
        nombrePersonaje = eTT7.getText().toString();
        String claseSeleccionada = spinner.getSelectedItem().toString();
        // Acceder a la base de datos
        SQLiteDatabase bd= gestor.getWritableDatabase();
        // Crear un registro con los datos del personaje
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
        // Insertar el registro en la base de datos
        bd.insert("PERSONAJESDND", null, registro);
        // Enviar un resultado de vuelta a la actividad anterior
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
                // Obtener valores de estadísticas desde la actividad de Estadísticas
                stats = result.getData().getIntegerArrayListExtra("statValues");

                // Verificar si los valores son válidos
                if (stats != null && stats.size() == 6) {
                    i1 = stats.get(0);
                    i2 = stats.get(1);
                    i3 = stats.get(2);
                    i4 = stats.get(3);
                    i5 = stats.get(4);
                    i6 = stats.get(5);

                    // Mostrar los valores de estadísticas en un Toast
                    Toast.makeText(MainActivity.this, String.valueOf(i1) + String.valueOf(i2) + String.valueOf(i3) + String.valueOf(i4) + String.valueOf(i5) + String.valueOf(i6), Toast.LENGTH_LONG).show();
                } else {
                    // Manejar el caso donde las estadísticas no son válidas
                    Toast.makeText(MainActivity.this, "Error in stats", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });
}
