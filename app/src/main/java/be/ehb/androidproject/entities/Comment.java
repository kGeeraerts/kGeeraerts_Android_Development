package be.ehb.androidproject.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Comment {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "meme")
    public int meme;

    @ColumnInfo(name = "user")
    public int user;
}
