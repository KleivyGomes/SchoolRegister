package com.example.gtescolar;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    Context context;
    ArrayList<Tarefa> list;

    public Adapter(Context context, ArrayList<Tarefa> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single, parent, false);

        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.discipline.setText("Disc: " + list.get(position).getDiscipline());
        holder.title.setText("Título: " + list.get(position).getTitle());
        holder.type.setText("Tipo: " + list.get(position).getEvaluate());
        holder.data.setText("Data: " + list.get(position).getData());
        holder.done.setChecked(Boolean.valueOf(list.get(position).getDone()));
        holder.done.setOnCheckedChangeListener((compoundButton, b) -> {
            if(holder.done.isChecked()){
                list.get(position).setDone(String.valueOf(true));
                int id = list.get(position).getId();
                MyDatabaseHelper mydb = new MyDatabaseHelper(context);
                mydb.updateRow(id,true);
            }else{
                list.get(position).setDone(String.valueOf(false));
                int id = list.get(position).getId();
                MyDatabaseHelper mydb = new MyDatabaseHelper(context);
                mydb.updateRow(id,false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView discipline, title, type, data;
        CheckBox done;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            discipline = itemView.findViewById(R.id.disciplina);
            title = itemView.findViewById(R.id.titulo);
            type = itemView.findViewById(R.id.tipo);
            data = itemView.findViewById(R.id.data);
            done = itemView.findViewById(R.id.feito);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            if(list.get(0).getSemester().equals("1º semestre")){
                if(list.get(0).getType().equals("Teste")){
                    contextMenu.add(this.getAdapterPosition(), 202 , 11, "Delete Teste");
                }else if(list.get(0).getType().equals("Trabalho")) {
                    contextMenu.add(this.getAdapterPosition(), 204 , 13, "Delete Trabalho");
                }else {
                    contextMenu.add(this.getAdapterPosition(), 206 , 15, "Delete Seminario");
                }
            }else{
                if(list.get(0).getType().equals("Teste")){
                    contextMenu.add(this.getAdapterPosition(), 208 , 17, "Delete Teste");
                }else if(list.get(0).getType().equals("Trabalho")) {
                    contextMenu.add(this.getAdapterPosition(), 210 , 19, "Delete Trabalho");
                }else {
                    contextMenu.add(this.getAdapterPosition(), 212 , 21, "Delete Seminario");
                }
            }

        }


    }
}
