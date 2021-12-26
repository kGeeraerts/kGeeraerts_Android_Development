package be.ehb.androidproject;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.mindrot.jbcrypt.BCrypt;

import java.util.concurrent.Executors;

import be.ehb.androidproject.DAO.CommentDao;
import be.ehb.androidproject.DAO.MemeDao;
import be.ehb.androidproject.DAO.UserDao;
import be.ehb.androidproject.entities.Comment;
import be.ehb.androidproject.entities.Meme;
import be.ehb.androidproject.entities.User;
import be.ehb.androidproject.entities.relations.UserMemeLikes;

@androidx.room.Database(entities = {User.class, Meme.class, Comment.class, UserMemeLikes.class}, version = 3)
public abstract class Database extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract MemeDao memeDao();
    public abstract CommentDao commentDao();

    private static final String DB_NAME = "appDatabase";
    private static Database instance;

    public static synchronized Database getInstance(Context context) {
        if (instance == null){
            instance = buildDB(context);
        }
        return instance;
    }

    private static Database buildDB(final Context context){
        return Room.databaseBuilder(context,
                Database.class,
                DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {

                            @Override
                            public void run() {
                                User user1 = new User("StartUser", "12345");
                                User user2 = new User("SupportingCast", "12345");
                                getInstance(context).userDao().insertUser(user1);
                                getInstance(context).userDao().insertUser(user2);
                            }
                        });
                    }
                })
                .build();
    }
}
