package com.example.gtescolar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText titleEdit, discipline;
    private Spinner spinnerType, spinnerEvaluateType, spinnerYear, spinnerSemester, spinnerTitle;
    private DatePicker datePicker;
    private Button addConfirm;
    private String type,evaluation, ano, title, semester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        spinnerType = findViewById(R.id.addTipo);
        discipline = findViewById(R.id.addDisciplina);
        titleEdit = findViewById(R.id.addTitulo);
        spinnerTitle = findViewById(R.id.add_titulo);
        datePicker = findViewById(R.id.datePicker1);
        spinnerEvaluateType = findViewById(R.id.addTipoAvaliacao);
        addConfirm = findViewById(R.id.addConfirm);
        spinnerYear = findViewById(R.id.addAno);
        spinnerSemester = findViewById(R.id.addSemestre);

        // Type Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerType.setAdapter(adapter);

        spinnerType.setOnItemSelectedListener(this);


        // Years Spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.anos_array, android.R.layout.simple_spinner_item);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerYear.setAdapter(adapter2);

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    ano = getString(R.string.ano1);
                }
                if(i==1){
                    ano = getString(R.string.ano2);
                }
                if(i==2){
                    ano = getString(R.string.ano3);
                }
                if(i==3){
                    ano = getString(R.string.ano4);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ano = getString(R.string.ano1);
            }
        });

        // Semester Spinner
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.semestres_array, android.R.layout.simple_spinner_item);

        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSemester.setAdapter(adapter4);

        spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    semester = getString(R.string.semestre1);
                }
                if(i==1){
                    semester = getString(R.string.semestre2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                semester = getString(R.string.semestre1);
            }
        });

        // Title Spinner
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.titulo_array, android.R.layout.simple_spinner_item);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTitle.setAdapter(adapter3);

        spinnerTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    title = getString(R.string.teste1);
                }
                if(i==1){
                    title = getString(R.string.teste2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                title = getString(R.string.teste1);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Event Click
        addConfirm.setOnClickListener(view -> {
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
            Integer mes = datePicker.getMonth() + 1;
            String data = datePicker.getDayOfMonth() + "/" + mes + "/" + datePicker.getYear();
            String tt;
            if(type== getString(R.string.teste)){
                tt = title;
            }else{
                tt = titleEdit.getText().toString();
            }
            if(discipline.getText().toString().isEmpty()){
                displayToast(getString(R.string.EmptyViewMessage));
            }else{
                boolean result = myDatabaseHelper.addTarefa(type,
                        discipline.getText().toString().trim(),
                        tt.trim(),
                        ano.trim(),
                        data.trim(),
                        evaluation.trim(),
                        semester);
                if(result == true){
                    displayToast(getString(R.string.agendaMessage));
                }else{
                    displayToast(getString(R.string.FailInsertMessage));
                }
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }

        });
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i == 0){
            type = getString(R.string.teste);
            spinnerTitle.setVisibility(View.VISIBLE);
            titleEdit.setVisibility(View.GONE);

            //
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.types_test_evaluation_array, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerEvaluateType.setAdapter(adapter);

            spinnerEvaluateType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                    if(j==0){
                        evaluation = getString(R.string.continua);
                    }
                    if(j==1){
                        evaluation = getString(R.string.exame);
                    }
                    if(j==2){
                        evaluation = getString(R.string.recurso);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    evaluation = getString(R.string.continua);
                }
            });
        }
        if(i == 1 ){
            type = getString(R.string.trabalho);
            spinnerTitle.setVisibility(View.GONE);
            titleEdit.setVisibility(View.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.types_work_evaluation_array, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerEvaluateType.setAdapter(adapter);

            spinnerEvaluateType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                    if(j==0){
                        evaluation = getString(R.string.individual);
                    }
                    if(j==1){
                        evaluation = getString(R.string.grupo);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    evaluation = getString(R.string.individual);
                }
            });
        }
        if(i == 2){
            type = getString(R.string.seminario);
            spinnerTitle.setVisibility(View.GONE);
            titleEdit.setVisibility(View.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.types_work_evaluation_array, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerEvaluateType.setAdapter(adapter);

            spinnerEvaluateType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                    if(j==0){
                        evaluation = getString(R.string.individual);
                    }
                    if(j==1){
                        evaluation = getString(R.string.grupo);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    evaluation = getString(R.string.individual);
                }
            });

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        type = getString(R.string.teste);
    }

}