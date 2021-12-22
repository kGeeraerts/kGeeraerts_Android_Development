package be.ehb.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import be.ehb.androidproject.entities.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomMenu = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomMenu, navController);

        Database db = Room.databaseBuilder(getApplicationContext(),Database.class, "database").build();
    }
}