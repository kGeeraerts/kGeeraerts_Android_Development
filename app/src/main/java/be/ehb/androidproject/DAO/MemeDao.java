package be.ehb.androidproject.DAO;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import be.ehb.androidproject.entities.Meme;
import be.ehb.androidproject.entities.relations.MemeWithComments;
import be.ehb.androidproject.entities.relations.MemeWithLikes;
import be.ehb.androidproject.entities.relations.UserMemeLikes;


@Dao
public interface MemeDao {

    @Insert
    void insertMeme(Meme meme);

    @Insert(onConflict = IGNORE)
    void insertUserMemeLikes(UserMemeLikes uml);

    @Query("SELECT * FROM Meme WHERE memeid = :memeid")
    public Meme getMeme(int memeid);

    @Query("SELECT * FROM Meme")
    public List<Meme> getAllMemes();

    @Transaction
    @Query("SELECT * FROM Meme WHERE memeid = :memeid")
    public List<MemeWithLikes> getMemeWithLikes(int memeid);

    @Transaction
    @Query("SELECT * FROM Meme WHERE memeid = :memeid")
    public List<MemeWithComments> getMemeWithComments(int memeid);
}
