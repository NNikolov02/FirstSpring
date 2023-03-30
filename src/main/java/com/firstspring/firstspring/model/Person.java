package com.firstspring.firstspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Person {

    @Id
    @GeneratedValue
    public UUID id;

    public String name;
    public int age;


}
