package com.vishal.newmap.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.vishal.newmap.SimpleLocation;

@Entity(tableName = "locations")
public class LocationEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public double writeTs;
    public SimpleLocation location;

}
