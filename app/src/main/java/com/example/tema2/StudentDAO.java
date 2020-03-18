package com.example.tema2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDAO {
    @Query("SELECT * FROM Student")
    List<Student> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Student... students);

    @Delete
    void delete(Student student);

    @Query("SELECT * FROM Student WHERE name = :name")
    Student findByName(String name);
}
