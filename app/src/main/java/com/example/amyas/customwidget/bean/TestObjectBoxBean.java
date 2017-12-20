package com.example.amyas.customwidget.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;

/**
 * author: Amyas
 * date: 2017/12/18
 */
@Entity
public class TestObjectBoxBean {
    @Id
    public long id;
    @Index

    public String name;

    @Override
    public String toString() {
        return "TestObjectBoxBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
