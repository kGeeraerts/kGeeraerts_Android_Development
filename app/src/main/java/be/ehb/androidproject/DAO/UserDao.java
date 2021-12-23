package be.ehb.androidproject.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import be.ehb.androidproject.entities.User;
import be.ehb.androidproject.entities.relations.UserWithComments;
import be.ehb.androidproject.entities.relations.UserWithLikes;
import be.ehb.androidproject.entities.relations.UserWithMemes;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM User WHERE uid = :uid")
    public User getUser(int uid);

    @Query("SELECT * FROM User WHERE username = :username")
    public User getUser(String username);

    @Transaction
    @Query("SELECT * FROM User")
    public List<UserWithMemes> getUserWithMemes();

    @Transaction
    @Query("SELECT * FROM User")
    public List<UserWithLikes> getUserWithLikes();

    @Transaction
    @Query("SELECT * FROM User")
    public List<UserWithComments> getUserWithComments();
}
