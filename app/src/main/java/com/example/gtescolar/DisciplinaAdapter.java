package com.example.gtescolar;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.ViewHolder> {
    //Attributes
    Context context;
    ArrayList<Tarefa> tarefas;
    ArrayList<String> anos;

    ArrayList<Tarefa> testesList, trabalhosList, seminariosList, testesList2, trabalhosList2, seminariosList2;


    Adapter testesAdapter, trabalhosAdapter, seminariosAdapter, testesAdapter2, trabalhosAdapter2, seminariosAdapter2;

    public DisciplinaAdapter(Context context, ArrayList<Tarefa> tarefas, ArrayList<String> anos) {
        this.context = context;
        this.tarefas = tarefas;
        this.anos = anos;
    }

    //inflate a single_item
    @NonNull
    @Override
    public DisciplinaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);

        return new ViewHolder(view);
    }

    //Setting the single_item views
    @Override
    public void onBindViewHolder(@NonNull DisciplinaAdapter.ViewHolder holder, int position) {
        holder.ano.setText(anos.get(position));

        holder.ano.setOnClickListener(view -> {
            if(holder.semestre.getVisibility() == View.VISIBLE){
                holder.semestre.setVisibility(View.GONE);
                holder.testes.setVisibility(View.GONE);
                holder.seminarios.setVisibility(View.GONE);
                holder.trabalhos.setVisibility(View.GONE);
                holder.recyclerView2.setVisibility(View.GONE);
                holder.recyclerView3.setVisibility(View.GONE);
                holder.recyclerView4.setVisibility(View.GONE);
                holder.semesterCount1.setVisibility(View.GONE);
                holder.testCount.setVisibility(View.GONE);
                holder.seminaryCount.setVisibility(View.GONE);
                holder.workCount.setVisibility(View.GONE);


                holder.semestre2.setVisibility(View.GONE);
                holder.testes2.setVisibility(View.GONE);
                holder.seminarios2.setVisibility(View.GONE);
                holder.trabalhos2.setVisibility(View.GONE);
                holder.recyclerView22.setVisibility(View.GONE);
                holder.recyclerView32.setVisibility(View.GONE);
                holder.recyclerView42.setVisibility(View.GONE);
                holder.semesterCount2.setVisibility(View.GONE);
                holder.testCount2.setVisibility(View.GONE);
                holder.workCount2.setVisibility(View.GONE);
                holder.seminaryCount2.setVisibility(View.GONE);

            }else{
                holder.semestre.setVisibility(View.VISIBLE);
                holder.semestre2.setVisibility(View.VISIBLE);
                holder.semesterCount1.setVisibility(View.VISIBLE);
                holder.semesterCount2.setVisibility(View.VISIBLE);
            }
        });

        holder.semestre.setOnClickListener(view -> {
            if(holder.testes.getVisibility() == View.VISIBLE){
                holder.testes.setVisibility(View.GONE);
                holder.seminarios.setVisibility(View.GONE);
                holder.trabalhos.setVisibility(View.GONE);
                holder.recyclerView2.setVisibility(View.GONE);
                holder.recyclerView3.setVisibility(View.GONE);
                holder.recyclerView4.setVisibility(View.GONE);
                holder.testCount.setVisibility(View.GONE);
                holder.workCount.setVisibility(View.GONE);
                holder.seminaryCount.setVisibility(View.GONE);
            }else{
                holder.testes.setVisibility(View.VISIBLE);
                holder.seminarios.setVisibility(View.VISIBLE);
                holder.trabalhos.setVisibility(View.VISIBLE);
                holder.testCount.setVisibility(View.VISIBLE);
                holder.workCount.setVisibility(View.VISIBLE);
                holder.seminaryCount.setVisibility(View.VISIBLE);
            }

        });

        holder.semestre2.setOnClickListener(view -> {
            if(holder.testes2.getVisibility() == View.VISIBLE){
                holder.testes2.setVisibility(View.GONE);
                holder.seminarios2.setVisibility(View.GONE);
                holder.trabalhos2.setVisibility(View.GONE);
                holder.recyclerView22.setVisibility(View.GONE);
                holder.recyclerView32.setVisibility(View.GONE);
                holder.recyclerView42.setVisibility(View.GONE);
                holder.testCount2.setVisibility(View.GONE);
                holder.workCount2.setVisibility(View.GONE);
                holder.seminaryCount2.setVisibility(View.GONE);
            }else{
                holder.testes2.setVisibility(View.VISIBLE);
                holder.seminarios2.setVisibility(View.VISIBLE);
                holder.trabalhos2.setVisibility(View.VISIBLE);
                holder.testCount2.setVisibility(View.VISIBLE);
                holder.workCount2.setVisibility(View.VISIBLE);
                holder.seminaryCount2.setVisibility(View.VISIBLE);
            }

        });


        holder.testes.setOnClickListener(view -> {
            if(holder.recyclerView2.getVisibility() == View.VISIBLE){
                holder.recyclerView2.setVisibility(View.GONE);
            }else{
                holder.recyclerView2.setVisibility(View.VISIBLE);
            }

        });

        holder.testes2.setOnClickListener(view -> {
            if(holder.recyclerView22.getVisibility() == View.VISIBLE){
                holder.recyclerView22.setVisibility(View.GONE);
            }else{
                holder.recyclerView22.setVisibility(View.VISIBLE);
            }

        });

        holder.trabalhos.setOnClickListener(view -> {
            if(holder.recyclerView3.getVisibility() == View.VISIBLE){
                holder.recyclerView3.setVisibility(View.GONE);
            }else{
                holder.recyclerView3.setVisibility(View.VISIBLE);
            }
        });

        holder.trabalhos2.setOnClickListener(view -> {
            if(holder.recyclerView32.getVisibility() == View.VISIBLE){
                holder.recyclerView32.setVisibility(View.GONE);
            }else{
                holder.recyclerView32.setVisibility(View.VISIBLE);
            }
        });

        holder.seminarios.setOnClickListener(view -> {
            if(holder.recyclerView4.getVisibility() == View.VISIBLE){
                holder.recyclerView4.setVisibility(View.GONE);
            }else{
                holder.recyclerView4.setVisibility(View.VISIBLE);
            }
        });

        holder.seminarios2.setOnClickListener(view -> {
            if(holder.recyclerView42.getVisibility() == View.VISIBLE){
                holder.recyclerView42.setVisibility(View.GONE);
            }else{
                holder.recyclerView42.setVisibility(View.VISIBLE);
            }
        });


        testesList = new ArrayList<>();
        trabalhosList = new ArrayList<>();
        seminariosList = new ArrayList<>();

        testesList2 = new ArrayList<>();
        trabalhosList2 = new ArrayList<>();
        seminariosList2 = new ArrayList<>();

        for(int i = 0; i<tarefas.size();i++){
            if(tarefas.get(i).getSemester().equals("1º semestre")){
                if(tarefas.get(i).getYear().equals(anos.get(position))){
                    if(tarefas.get(i).getType().equals("Teste")){
                        testesList.add(tarefas.get(i));
                    }
                    if(tarefas.get(i).getType().equals("Seminario")){
                        seminariosList.add(tarefas.get(i));
                    }
                    if(tarefas.get(i).getType().equals("Trabalho")){
                        trabalhosList.add(tarefas.get(i));
                    }
                }
            }else{
                if(tarefas.get(i).getType().equals("Teste")){
                    testesList2.add(tarefas.get(i));
                }
                if(tarefas.get(i).getType().equals("Seminario")){
                    seminariosList2.add(tarefas.get(i));
                }
                if(tarefas.get(i).getType().equals("Trabalho")){
                    trabalhosList2.add(tarefas.get(i));
                }
            }



        }

        testesAdapter = new Adapter(context.getApplicationContext(),testesList);
        trabalhosAdapter = new Adapter(context.getApplicationContext(),trabalhosList);
        seminariosAdapter = new Adapter(context.getApplicationContext(),seminariosList);


        holder.recyclerView2.setHasFixedSize(true);
        holder.recyclerView2.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
        holder.recyclerView2.setAdapter(testesAdapter);

        holder.recyclerView3.setHasFixedSize(true);
        holder.recyclerView3.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
        holder.recyclerView3.setAdapter(trabalhosAdapter);

        holder.recyclerView4.setHasFixedSize(true);
        holder.recyclerView4.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
        holder.recyclerView4.setAdapter(seminariosAdapter);

        testesAdapter2 = new Adapter(context.getApplicationContext(),testesList2);
        trabalhosAdapter2 = new Adapter(context.getApplicationContext(),trabalhosList2);
        seminariosAdapter2 = new Adapter(context.getApplicationContext(),seminariosList2);

        holder.recyclerView22.setHasFixedSize(true);
        holder.recyclerView22.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
        holder.recyclerView22.setAdapter(testesAdapter2);

        holder.recyclerView32.setHasFixedSize(true);
        holder.recyclerView32.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
        holder.recyclerView32.setAdapter(trabalhosAdapter2);

        holder.recyclerView42.setHasFixedSize(true);
        holder.recyclerView42.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
        holder.recyclerView42.setAdapter(seminariosAdapter2);

        int count = testesList.size() + trabalhosList.size() + seminariosList.size() +
                testesList2.size() + trabalhosList2.size() + seminariosList2.size();

        holder.yearCount.setText(String.valueOf(count));

        count = testesList.size() + trabalhosList.size() + seminariosList.size();
        holder.semesterCount1.setText(String.valueOf(count));

        count = testesList2.size() + trabalhosList2.size() + seminariosList2.size();
        holder.semesterCount2.setText(String.valueOf(count));

        holder.testCount.setText(String.valueOf(testesList.size()));
        holder.workCount.setText(String.valueOf(trabalhosList.size()));
        holder.seminaryCount.setText(String.valueOf(seminariosList.size()));
        holder.testCount2.setText(String.valueOf(testesList2.size()));
        holder.workCount2.setText(String.valueOf(trabalhosList2.size()));
        holder.seminaryCount2.setText(String.valueOf(seminariosList2.size()));


    }

    //DB size
    @Override
    public int getItemCount() {
        return anos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ano, testes, seminarios, trabalhos,testes2, seminarios2, trabalhos2, semestre,
                semestre2, yearCount, semesterCount1, semesterCount2, testCount, workCount, seminaryCount,
                testCount2, workCount2, seminaryCount2;
        RecyclerView recyclerView2, recyclerView3, recyclerView4, recyclerView22, recyclerView32, recyclerView42;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ano = itemView.findViewById(R.id.ano);

            semestre = itemView.findViewById(R.id.semestre);
            testes = itemView.findViewById(R.id.testes);
            trabalhos = itemView.findViewById(R.id.trabalhos);
            seminarios = itemView.findViewById(R.id.seminarios);
            recyclerView2 = itemView.findViewById(R.id.recyclerView2);
            recyclerView3 = itemView.findViewById(R.id.recyclerView3);
            recyclerView4 = itemView.findViewById(R.id.recyclerView4);

            semestre2 = itemView.findViewById(R.id.semestre2);
            testes2 = itemView.findViewById(R.id.testes2);
            trabalhos2 = itemView.findViewById(R.id.trabalhos2);
            seminarios2 = itemView.findViewById(R.id.seminarios2);
            recyclerView22 = itemView.findViewById(R.id.recyclerView22);
            recyclerView32 = itemView.findViewById(R.id.recyclerView32);
            recyclerView42 = itemView.findViewById(R.id.recyclerView42);

            yearCount = itemView.findViewById(R.id.yearCount);
            semesterCount1= itemView.findViewById(R.id.SemesterCount1);
            semesterCount2 = itemView.findViewById(R.id.SemesterCount2);
            testCount = itemView.findViewById(R.id.testCount);
            testCount2 = itemView.findViewById(R.id.testeCount2);
            workCount = itemView.findViewById(R.id.trabalhoCount);
            workCount2 = itemView.findViewById(R.id.trabalhoCount2);
            seminaryCount = itemView.findViewById(R.id.seminarioCount);
            seminaryCount2 = itemView.findViewById(R.id.seminarioCount2);

        }

    }
}
