package ca.gbc.project;

import android.content.Intent;
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

    private RestaurantAdapter adapter;

    private Button editButton;
    private Button deleteButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Restaurant selectedRestaurant = getIntent().getParcelableExtra("selectedRestaurant");

                if (selectedRestaurant != null) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RestaurantDatabase db = RestaurantDatabase.getDatabase(RestaurantDetailsActivity.this);
                            db.restaurantDao().deleteRestaurant(selectedRestaurant);
                            if (adapter != null) {
                                adapter.removeRestaurant(selectedRestaurant);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }).start();


                    finish();
                } else {

                    handleNullRestaurantObject();
                }
            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeView = new Intent(RestaurantDetailsActivity.this, AddEditRestaurantActivity.class);
                startActivity(changeView);
            }
        });

    }

    public void deleteRestaurant(long restaurantId){
        new Thread(() -> {
            RestaurantDatabase db = RestaurantDatabase.getDatabase(this);
            RestaurantDao rd = db.restaurantDao();

            runOnUiThread(() -> {
                Toast.makeText(this, "Restaurant deleted", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }

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
        onBackPressed();
        return true;
    }


    private void handleNullRestaurantObject() {

        Toast.makeText(this, "Restaurant details not available", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }


    private void handleNoRestaurantDetailsAvailable() {

        Toast.makeText(this, "No restaurant details found", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }





}

