package com.hololeenko.task_4.model.entity;

public class AbstractEntity {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }

        AbstractEntity that = (AbstractEntity) o;

        return id == that.id ;

    }

    @Override
    public int hashCode() {
        int total = 31;
        total = total * 31 + id;
        return total;
    }
}
