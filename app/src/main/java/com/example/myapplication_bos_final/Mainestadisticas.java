package com.example.myapplication_bos_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mainestadisticas extends AppCompatActivity {

    private EditText[] statEditTexts;
    private Button[] statButtons;
    private List<Integer> statValues;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadisticas);
        statValues = new ArrayList<>();

        statEditTexts = new EditText[6];
        statButtons = new Button[6];

        // Obtener referencias a los EditText y Buttons
        statEditTexts[0] = findViewById(R.id.NumerinFuerza);
        statEditTexts[1] = findViewById(R.id.NumerinDestreza);
        statEditTexts[2] = findViewById(R.id.NumerinConstitucion);
        statEditTexts[3] = findViewById(R.id.NumerinInteligencia);
        statEditTexts[4] = findViewById(R.id.NumerinSabiduria);
        statEditTexts[5] = findViewById(R.id.NumerinCarisma);

        statButtons[0] = findViewById(R.id.btnFuerza);
        statButtons[1] = findViewById(R.id.btnDestreza);
        statButtons[2] = findViewById(R.id.btnConstitucion);
        statButtons[3] = findViewById(R.id.btnInteligencia);
        statButtons[4] = findViewById(R.id.btnSabiduria);
        statButtons[5] = findViewById(R.id.btnCarisma);

        // Configurar acciones para los Buttons
        for (int i = 0; i < statButtons.length; i++) {
            final int index = i;
            statButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    generarPuntuacion(index);
                }
            });
        }
    }

    private void generarPuntuacion(int index) {
        // Generar tres números del 1 al 6 y sumarlos
        Random random = new Random();
        int suma = random.nextInt(6) + 1 + random.nextInt(6) + 1 + random.nextInt(6) + 1;

        statValues.add(suma);
        // Mostrar la puntuación en el EditText correspondiente
        statEditTexts[index].setText(String.valueOf(suma));

        // Desactivar el botón para que no se pueda pulsar de nuevo
        statButtons[index].setEnabled(false);

        // Verificar si todas las estadísticas han sido generadas
        contador++;
        if (contador == 6) {
            enviarStats();
        }
    }

    private void enviarStats() {
        // Crear un intent para enviar los datos de vuelta a la actividad principal
        Intent intent = new Intent();
        intent.putIntegerArrayListExtra("statValues", new ArrayList<>(statValues));

        // Establecer el resultado y enviar el intent
        setResult(RESULT_OK, intent);

        // Finalizar la actividad
        finish();
    }
}
