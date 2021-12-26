package be.ehb.androidproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

import be.ehb.androidproject.entities.User;

public class MainActivity extends AppCompatActivity {

    private static final int image = 1;
    public Bitmap newMemeImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomMenu = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomMenu, navController);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == image){
            Uri imageUri = data.getData();
            try {
                newMemeImage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                System.out.println(newMemeImage);
            }catch (IOException i){
                System.out.println(i);
            }
        }
    }

    public void reloadComments(int memeid) {
        System.out.println("Comment added");
        memeCommentsFragment mcf = new memeCommentsFragment();
        mcf.memeid = memeid;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, mcf)
                .commit();
    }
}