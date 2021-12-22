package be.ehb.androidproject.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import be.ehb.androidproject.entities.Comment;
import be.ehb.androidproject.entities.Meme;

public class MemeWithComments {
    @Embedded
    public Meme meme;
    @Relation(
            parentColumn = "memeid",
            entityColumn = "meme"
    )
    public List<Comment> comments;
}
