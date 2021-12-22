package be.ehb.androidproject.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Meme {
    @PrimaryKey
    public int memeid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] image;

    @ColumnInfo(name = "creator")
    public int creator;
}
