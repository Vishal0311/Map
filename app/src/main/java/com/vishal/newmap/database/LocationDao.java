package com.vishal.newmap.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert
    void insert(LocationEntity locationEntity);

    @Query("DELETE FROM locations")
    void deleteAll();

    @Query("SELECT * FROM locations")
    LiveData<List<LocationEntity>> getAll();

    @Query("SELECT * FROM locations ORDER BY id DESC LIMIT 1")
    LiveData<LocationEntity>getCurrentLocation();


}
