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

    public Comment(String content, int meme, int user) {
        this.content = content;
        this.meme = meme;
        this.user = user;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMeme() {
        return meme;
    }

    public void setMeme(int meme) {
        this.meme = meme;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
