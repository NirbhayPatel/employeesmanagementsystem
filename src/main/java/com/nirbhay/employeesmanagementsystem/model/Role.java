package com.nirbhay.employeesmanagementsystem.model;

import javax.persistence.*;

/*
 * This Entity Role class refers to the role table
 * */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public Role(String name) {
        super();
        this.name = name;
    }
    public Role(){

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
