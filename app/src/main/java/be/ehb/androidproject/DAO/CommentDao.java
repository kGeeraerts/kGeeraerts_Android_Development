package be.ehb.androidproject.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import be.ehb.androidproject.entities.Comment;

@Dao
public interface CommentDao {
    @Insert
    void insertComment(Comment comment);

    @Query("SELECT * FROM Comment WHERE uid = :uid")
    public Comment getComment(int uid);
}
