package com.example.myapplication_bos_final;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainHabilidades extends AppCompatActivity {

    private List<CheckBox> checkBoxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habilidades);

        checkBoxList = new ArrayList<>();

        // Obtener referencias a las CheckBox
        checkBoxList.add(findViewById(R.id.checkBoxAtletismo));
        checkBoxList.add(findViewById(R.id.checkBoxAcrobacias));
        checkBoxList.add(findViewById(R.id.checkBoxJuegoDeManos));
        checkBoxList.add(findViewById(R.id.checkBoxSigilo));
        checkBoxList.add(findViewById(R.id.checkBoxArcano));
        checkBoxList.add(findViewById(R.id.checkBoxHistoria));
        checkBoxList.add(findViewById(R.id.checkBoxInvestigacion));
        checkBoxList.add(findViewById(R.id.checkBoxNaturaleza));
        checkBoxList.add(findViewById(R.id.checkBoxReligion));
        checkBoxList.add(findViewById(R.id.checkBoxMedicina));
        checkBoxList.add(findViewById(R.id.checkBoxPercepcion));
        checkBoxList.add(findViewById(R.id.checkBoxPerspicacia));
        checkBoxList.add(findViewById(R.id.checkBoxSupervivencia));
        checkBoxList.add(findViewById(R.id.checkBoxTratoDeAnimales));
        checkBoxList.add(findViewById(R.id.checkBoxEngano));
        checkBoxList.add(findViewById(R.id.checkBoxIntimidacion));
        checkBoxList.add(findViewById(R.id.checkBoxInterpretacion));
        checkBoxList.add(findViewById(R.id.checkBoxPersuasion));

        // Configurar acciones para las CheckBox
        for (CheckBox checkBox : checkBoxList) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> checkSkillLimit());
        }
    }

    private void checkSkillLimit() {
        int checkedCount = 0;

        // Contar cuántas CheckBox están marcadas
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                checkedCount++;
            }
        }

        // Verificar si se han marcado un máximo de 3 CheckBox
        if (checkedCount == 3) {
            // Crear un intent para enviar los datos de vuelta a la actividad principal
            Intent resultIntent = new Intent();
            ArrayList<String> selectedSkills = new ArrayList<>();

            // Añadir las habilidades seleccionadas al intent
            for (CheckBox checkBox : checkBoxList) {
                if (checkBox.isChecked()) {
                    selectedSkills.add(checkBox.getText().toString());

                }
            }
            StringBuilder st= new StringBuilder();
            for(String habilidad: selectedSkills){
                st.append(habilidad).append(",");
            }
            // Puedes pasar los datos adicionales si es necesario
            resultIntent.putExtra("selectedSkills", st.toString());
            setResult(RESULT_OK, resultIntent);

            // Finalizar la actividad
            finish();
        }
    }
}
