package be.ehb.androidproject.entities.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import be.ehb.androidproject.entities.Meme;
import be.ehb.androidproject.entities.User;

public class UserWithLikes {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "uid",
            entityColumn = "memeid",
            associateBy = @Junction(UserMemeLikes.class)
    )
    public List<Meme> memes;
}
