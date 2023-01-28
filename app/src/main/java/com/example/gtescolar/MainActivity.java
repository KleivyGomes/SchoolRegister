package com.example.gtescolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    DisciplinaAdapter disciplinaAdapter;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    MyDatabaseHelper mydb;
    ArrayList<Tarefa> database;
    ArrayList<String> years;

    SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new ArrayList<>();
        mydb = new MyDatabaseHelper(this);
        years = new ArrayList<>();

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        });



        DataInArrayList();

        OrderDB();

        for (int i = 0; i < database.size(); i++) {
            if (!years.contains(database.get(i).getYear())) {
                years.add(database.get(i).getYear());
            }
        }
        Collections.sort(years);

        disciplinaAdapter = new DisciplinaAdapter(this, database, years);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(disciplinaAdapter);
    }

    void OrderDB(){
        Collections.sort(database, new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa tarefa, Tarefa t1) {

                Date d1 = null;
                try {
                    d1 = formataData.parse(tarefa.getData());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date d2 = null;
                try {
                    d2 = formataData.parse(t1.getData());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (d1.compareTo(d2) < 0){
                    return -1;
                }else if (d1.compareTo(d2) > 0){
                    return +1;
                } else {
                    return 0;
                }
            }
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id;
        switch (item.getItemId()){
            case 202:
                id = disciplinaAdapter.testesList.get(item.getGroupId()).getId();
                disciplinaAdapter.testesList.remove(item.getGroupId());
                return apagar(id);

            case 204:
                id = disciplinaAdapter.trabalhosList.get(item.getGroupId()).getId();
                disciplinaAdapter.trabalhosList.remove(item.getGroupId());
                return apagar(id);

            case 206:
                id = disciplinaAdapter.seminariosList.get(item.getGroupId()).getId();
                disciplinaAdapter.seminariosList.remove(item.getGroupId());
                return apagar(id);
            case 208:
                id = disciplinaAdapter.testesList2.get(item.getGroupId()).getId();
                disciplinaAdapter.testesList2.remove(item.getGroupId());
                return apagar(id);

            case 210:
                id = disciplinaAdapter.trabalhosList2.get(item.getGroupId()).getId();
                disciplinaAdapter.trabalhosList2.remove(item.getGroupId());
                return apagar(id);

            case 212:
                id = disciplinaAdapter.seminariosList2.get(item.getGroupId()).getId();
                disciplinaAdapter.seminariosList2.remove(item.getGroupId());
                return apagar(id);
            default:
                return super.onContextItemSelected(item);
        }
    }
    boolean apagar(int id){
        boolean result = mydb.deleteData(id);
        for(int i=0;i<database.size();i++){
            if(database.get(i).getId() == id){
                database.remove(i);
            }
        }
        years.clear();
        for (int i = 0; i < database.size(); i++) {
            if (!years.contains(database.get(i).getYear())) {
                years.add(database.get(i).getYear());
            }
        }

        if(result){
            displayToast(getString(R.string.deleteMessage));
        }else{
            displayToast(getString(R.string.FailDeleteMessage));
        }

        disciplinaAdapter.notifyDataSetChanged();
        disciplinaAdapter.testesAdapter.notifyDataSetChanged();
        return true;
    }

    void DataInArrayList() {
        Cursor cursor = mydb.ReadAllData();
        if (cursor.getCount() == 0) {
            displayToast(getString(R.string.dbEmpty));
        } else {
            while (cursor.moveToNext()) {
                Tarefa tarefa = new Tarefa(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7),cursor.getString(8));
                database.add(tarefa);
            }
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}