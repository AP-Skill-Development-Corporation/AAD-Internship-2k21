package com.example.exambledatabase.RDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RDao {
    //Data Access Object
    //Create,Read,Update,Delete
    @Insert
    void insert(RTable rTable);

    @Query("Select * from RTable")
    LiveData<List<RTable>> readAll();

    @Update
    void update(RTable rTable);

    @Delete
    void delete(RTable rTable);


}
