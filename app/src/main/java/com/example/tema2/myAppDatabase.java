package com.example.tema2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1)
public abstract class myAppDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();
}