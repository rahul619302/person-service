package com.cg.person.service.starter.pojo;

import javax.persistence.*;

@Entity
@Table(name = "Person_Tbl")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Person_Id")
    private int id;
    @Column(name = "Person_Name")
    private String name;
    @Column(name = "Person_Type")
    private String type;

    public Person() {
    }

    public Person(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
