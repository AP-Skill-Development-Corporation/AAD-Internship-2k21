package com.example.exambledatabase.RDB;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RTable {

    @NonNull
    @PrimaryKey
    String sroll;

    String sname,snumber;

    @NonNull
    public String getSroll() {
        return sroll;
    }

    public void setSroll(@NonNull String sroll) {
        this.sroll = sroll;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }


}
