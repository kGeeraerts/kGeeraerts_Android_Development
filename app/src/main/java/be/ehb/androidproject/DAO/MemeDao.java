package be.ehb.androidproject.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import be.ehb.androidproject.entities.Meme;
import be.ehb.androidproject.entities.relations.MemeWithComments;
import be.ehb.androidproject.entities.relations.MemeWithLikes;


@Dao
public interface MemeDao {

    @Insert
    void insertUser(Meme meme);

    @Query("SELECT * FROM Meme WHERE memeid = :memeid")
    public Meme getMeme(int memeid);

    @Transaction
    @Query("SELECT * FROM Meme")
    public List<MemeWithLikes> getMemeWithLikes();

    @Transaction
    @Query("SELECT * FROM Meme")
    public List<MemeWithComments> getMemeWithComments();
}
