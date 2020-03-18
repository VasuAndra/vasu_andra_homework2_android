package com.example.tema2;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {


    @PrimaryKey
    @NonNull
    public String name;

    public Integer mark;

}