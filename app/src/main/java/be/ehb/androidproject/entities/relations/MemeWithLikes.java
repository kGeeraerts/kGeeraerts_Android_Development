package be.ehb.androidproject.entities.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import be.ehb.androidproject.entities.Meme;
import be.ehb.androidproject.entities.User;

public class MemeWithLikes {
    @Embedded
    public Meme meme;
    @Relation(
            parentColumn = "memeid",
            entityColumn = "uid",
            associateBy = @Junction(UserMemeLikes.class)
    )
    public List<User> users;
}
