package com.tw;


import java.util.HashMap;
import java.util.Map;

public class Student {
    String id;
    String name;
    Map<String, Double> scores;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        scores = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getScores() {
        return scores;
    }

    public void setScores(Map<String, Double> scores) {
        this.scores = scores;
    }

    public void addScore(String key, double value){
        scores.put(key, value);
    }
}
