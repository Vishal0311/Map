package com.vishal.newmap.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert
    void insertAll(LocationEntity locationEntity);

    @Query("DELETE FROM locations")
    void deleteAll();

    @Query("SELECT * FROM locations")
    LiveData<List<LocationEntity>> getAll();


}
