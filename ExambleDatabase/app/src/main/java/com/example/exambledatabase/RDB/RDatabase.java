package com.example.exambledatabase.RDB;

import android.content.Context;
import android.net.rtp.RtpStream;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RTable.class},version = 1,exportSchema = false)
public abstract class RDatabase extends RoomDatabase {
    public abstract RDao rDao();
    public static RDatabase rDatabase;

    public static synchronized RDatabase getrDatabase(Context context){
        if (rDatabase==null){
            rDatabase = Room.databaseBuilder(context,RDatabase.class,"MYROOM")
                    .allowMainThreadQueries()
                    .build();
        }
        return  rDatabase;
    }


}
