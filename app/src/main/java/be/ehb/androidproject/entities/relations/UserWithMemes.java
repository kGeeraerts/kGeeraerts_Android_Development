package be.ehb.androidproject.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import be.ehb.androidproject.entities.Meme;
import be.ehb.androidproject.entities.User;

public class UserWithMemes {
    @Embedded public User user;
            @Relation(
                    parentColumn = "uid",
                    entityColumn = "creator"
            )
    public List<Meme> memes;
}
