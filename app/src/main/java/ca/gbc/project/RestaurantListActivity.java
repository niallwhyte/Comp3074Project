package ca.gbc.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        restaurantList = fetchRestaurantData(); // Fetch restaurant data from your database or source
//        adapter = new RestaurantAdapter(restaurantList);
//        recyclerView.setAdapter(adapter);
//
//        // Set item click listener for the adapter
//        adapter.setOnItemClickListener(restaurant -> {
//            // Handle item click - navigate to RestaurantDetailsActivity with details of the selected restaurant
//            Intent intent = new Intent(RestaurantListActivity.this, RestaurantDetailsActivity.class);
//            intent.putExtra("selectedRestaurant", restaurant);
//            startActivity(intent);
//        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Restaurant> restaurants = fetchRestaurantData();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update UI with fetched restaurants
                        adapter = new RestaurantAdapter(restaurants); // Assuming you have an adapter
                        recyclerView.setAdapter(adapter);

                        // Set item click listener for the adapter
                        adapter.setOnItemClickListener(restaurant -> {
                            // Handle item click - navigate to RestaurantDetailsActivity with details of the selected restaurant
                            if (restaurant != null) {
                                Log.d("ItemClicked", "Restaurant Name: " + restaurant.getName());
                                Intent intent = new Intent(RestaurantListActivity.this, RestaurantDetailsActivity.class);
                                intent.putExtra("selectedRestaurant", restaurant);
                                startActivity(intent);
                            } else {
                                Log.e("ItemClicked", "Restaurant object is null");
                            }
                        });
                    }
                });
            }
        }).start();

    }

    private List<Restaurant> fetchRestaurantData() {
        RestaurantDatabase db = RestaurantDatabase.getDatabase(this); // Get an instance of your Room Database
        List<Restaurant> restaurants = db.restaurantDao().getAllRestaurants(); // Fetch restaurants from the database using the DAO

        return restaurants;
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when the back arrow in the toolbar is clicked
        return true;
    }
}

