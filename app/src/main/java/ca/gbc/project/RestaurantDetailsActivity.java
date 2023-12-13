package ca.gbc.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class RestaurantDetailsActivity extends AppCompatActivity {

    private TextView restaurantNameTextView;
    private TextView restaurantAddressTextView;
    private TextView restaurantPhonenumberTextView;
    private TextView restaurantDescriptionTextView;
    private TextView restaurantTagsTextView;

    private TextView restaurantRatingTextView;

    private Button editButton;
    private Button deleteButton;

    private RestaurantDao restaurantDao;
    private Restaurant selectedRestaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        restaurantDao = RestaurantDatabase.getDatabase(getApplicationContext()).restaurantDao(); // Replace YourDatabase with your actual database class

        // Initialize TextViews or other views to display restaurant details
        restaurantNameTextView = findViewById(R.id.restaurantNameTextView);
        restaurantAddressTextView = findViewById(R.id.restaurantAddressTextView);
        restaurantPhonenumberTextView = findViewById(R.id.restaurantPhonenumberTextView);
        restaurantDescriptionTextView = findViewById(R.id.restaurantDescriptionTextView);
        restaurantTagsTextView = findViewById(R.id.restaurantTagsTextView);
        restaurantRatingTextView = findViewById(R.id.restaurantRatingTextView);

        if (getIntent().getExtras() != null && getIntent().hasExtra("selectedRestaurant")) {
            Restaurant selectedRestaurant = getIntent().getParcelableExtra("selectedRestaurant");

            if (selectedRestaurant != null) {
                displayRestaurantDetails(selectedRestaurant);
            } else {
                handleNullRestaurantObject();
            }
        } else {
            handleNoRestaurantDetailsAvailable();
        }


        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);

    }


    // Method to display restaurant details in TextViews
    private void displayRestaurantDetails(Restaurant restaurant) {
        if (restaurant != null) {
            restaurantNameTextView.setText("Restaurant Name: " + restaurant.getName());
            restaurantAddressTextView.setText("Address: " + restaurant.getAddress());
            restaurantPhonenumberTextView.setText("Phone Number: " + restaurant.getPhoneNumber());
            restaurantDescriptionTextView.setText("Description: " + restaurant.getDescription());
            restaurantTagsTextView.setText("Tags: " + restaurant.getTags());
            restaurantRatingTextView.setText("Rating: " + String.valueOf(restaurant.getRating()));

        } else {
            handleNullRestaurantObject();
        }
    }
    

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when the back arrow in the toolbar is clicked
        return true;
    }

    // Method to handle null Restaurant object
    private void handleNullRestaurantObject() {
        // For example, display an error message or navigate back
        Toast.makeText(this, "Restaurant details not available", Toast.LENGTH_SHORT).show();
        onBackPressed(); // Go back to the previous activity
    }

    // Method to handle when there are no restaurant details available in the intent
    private void handleNoRestaurantDetailsAvailable() {
        // For example, display an error message or navigate back
        Toast.makeText(this, "No restaurant details found", Toast.LENGTH_SHORT).show();
        onBackPressed(); // Go back to the previous activity
    }

}

