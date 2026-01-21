package com.hololeenko.task_4.model.entity;

public class AbstractEntity {

    private int id;

    protected AbstractEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }



}
