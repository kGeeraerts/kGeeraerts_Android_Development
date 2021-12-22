package be.ehb.androidproject.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import be.ehb.androidproject.entities.Comment;
import be.ehb.androidproject.entities.User;

public class UserWithComments {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "uid",
            entityColumn = "user"
    )
    public List<Comment> comments;
}
