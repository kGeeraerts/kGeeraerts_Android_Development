package be.ehb.androidproject;

import android.content.Context;

import androidx.room.RoomDatabase;

import be.ehb.androidproject.DAO.CommentDao;
import be.ehb.androidproject.DAO.MemeDao;
import be.ehb.androidproject.DAO.UserDao;
import be.ehb.androidproject.entities.Comment;
import be.ehb.androidproject.entities.Meme;
import be.ehb.androidproject.entities.User;
import be.ehb.androidproject.entities.relations.UserMemeLikes;

@androidx.room.Database(entities = {User.class, Meme.class, Comment.class, UserMemeLikes.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract MemeDao memeDao();
    public abstract CommentDao commentDao();
}
