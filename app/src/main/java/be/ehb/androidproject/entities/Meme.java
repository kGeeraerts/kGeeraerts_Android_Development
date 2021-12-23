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

    public Meme(String title, byte[] image, int creator) {
        this.title = title;
        this.image = image;
        this.creator = creator;
    }

    public int getMemeid() {
        return memeid;
    }

    public void setMemeid(int memeid) {
        this.memeid = memeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }
}
