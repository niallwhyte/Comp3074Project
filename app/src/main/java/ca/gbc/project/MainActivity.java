package ca.gbc.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Implement the logic for handling menu options
    // Example:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addEditRemoveBtn = findViewById(R.id.add_edit_remove_button);
        Button viewListBtn = findViewById(R.id.view_list_button);
        Button aboutBtn = findViewById(R.id.about_button);

        addEditRemoveBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditRestaurantActivity.class);
            startActivity(intent);
        });

        viewListBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
            startActivity(intent);
        });

        aboutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }
}