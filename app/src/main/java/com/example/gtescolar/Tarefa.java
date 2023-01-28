package com.example.gtescolar;

public class Tarefa {
    private int id;
    private String type;
    private String discipline;
    private String title;
    private String year;
    private String data;
    private String evaluate;
    private String done;
    private String semester;

    public Tarefa(int id, String type, String discipline, String title, String year, String data, String evaluate, String done, String semester) {
        this.id = id;
        this.type = type;
        this.discipline = discipline;
        this.title = title;
        this.year = year;
        this.data = data;
        this.evaluate = evaluate;
        this.done = done;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getData() {
        return data;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public String getDone() {
        return done;
    }

    public String getSemester() {
        return semester;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
