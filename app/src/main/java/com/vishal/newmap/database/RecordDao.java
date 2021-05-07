package com.vishal.newmap.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  RecordDao {
    @Query("SELECT * FROM LocationEntity")
    List<LocationEntity> getAll();


    @Insert
    void insertAll(LocationEntity users);

    @Delete
    void delete(LocationEntity locationEntity);

}
