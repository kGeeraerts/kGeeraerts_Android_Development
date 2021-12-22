package be.ehb.androidproject.entities.relations;


import androidx.room.Entity;

//manytomany relationship to track likes
@Entity(primaryKeys = {"uid", "memeid"})
public class UserMemeLikes {
    public int uid;
    public int memeid;
}
