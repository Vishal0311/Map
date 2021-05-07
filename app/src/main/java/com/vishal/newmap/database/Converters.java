package com.vishal.newmap.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.vishal.newmap.SimpleLocation;

public class Converters {
    Gson gson=new Gson();
    @TypeConverter
    public String fromSimpleLocation(SimpleLocation location){

        String json=gson.toJson(location);
        return json;
    }


    @TypeConverter
    public SimpleLocation toSimpleLocation(String locationString){

        SimpleLocation loc=gson.fromJson(locationString, SimpleLocation.class);

        return loc;
    }

// this will provide date
//    @TypeConverter
//    public Date fromTimestamp(Long value) {
//        return value == null ? null : new Date(value);
//    }
}
