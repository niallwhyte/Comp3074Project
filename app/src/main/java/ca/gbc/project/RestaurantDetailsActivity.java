package ca.gbc.project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private TextView restaurantNameTextView;
    private TextView restaurantAddressTextView;
    private TextView restaurantPhonenumberTextView;
    private TextView restaurantDescriptionTextView;
    private TextView restaurantTagsTextView;

    private TextView restaurantRatingTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize TextViews or other views to display restaurant details
        restaurantNameTextView = findViewById(R.id.restaurantNameTextView);
        restaurantAddressTextView = findViewById(R.id.restaurantAddressTextView);
        restaurantPhonenumberTextView = findViewById(R.id.restaurantPhonenumberTextView);
        restaurantDescriptionTextView = findViewById(R.id.restaurantDescriptionTextView);
        restaurantTagsTextView = findViewById(R.id.restaurantTagsTextView);
        restaurantRatingTextView = findViewById(R.id.restaurantRatingTextView);


        // Retrieve selected restaurant details from Intent
        if (getIntent().getExtras() != null && getIntent().hasExtra("selectedRestaurant")) {
            Restaurant selectedRestaurant = getIntent().getParcelableExtra("selectedRestaurant");
            displayRestaurantDetails(selectedRestaurant);
        } else {
            // Handle when there are no restaurant details available
        }
    }

    // Method to display restaurant details in TextViews
    private void displayRestaurantDetails(Restaurant restaurant) {
        restaurantNameTextView.setText(restaurant.getName());
        restaurantAddressTextView.setText(restaurant.getAddress());
        restaurantPhonenumberTextView.setText(restaurant.getPhoneNumber());
        restaurantDescriptionTextView.setText(restaurant.getDescription());
        restaurantTagsTextView.setText(restaurant.getTags());
        restaurantRatingTextView.setText(restaurant.getTags());
        // Set other details similarly...
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when the back arrow in the toolbar is clicked
        return true;
    }
}

