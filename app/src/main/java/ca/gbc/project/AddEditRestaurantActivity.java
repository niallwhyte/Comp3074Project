package ca.gbc.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddEditRestaurantActivity extends AppCompatActivity {
    EditText nameEditText, addressEditText, phoneEditText, descriptionEditText, tagsEditText;

    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_restaurant_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ratingBar = findViewById(R.id.ratingBar);
        nameEditText = findViewById(R.id.restaurant_name);
        addressEditText = findViewById(R.id.restaurant_address);
        phoneEditText = findViewById(R.id.restaurant_phone);
        descriptionEditText = findViewById(R.id.restaurant_description);
        tagsEditText = findViewById(R.id.restaurant_tags);

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String address = addressEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String tags = tagsEditText.getText().toString();
            float rating = ratingBar.getRating();

            Restaurant restaurant = new Restaurant(name, address, phone, description, tags, rating);

            RestaurantDatabase db = RestaurantDatabase.getDatabase(this);

            new Thread(() -> {
                db.restaurantDao().insertRestaurant(restaurant);
            }).start();

            Intent intent = new Intent(AddEditRestaurantActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
