package ca.gbc.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
//
//public class RestaurantListActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private RestaurantAdapter adapter;
//    private List<Restaurant> restaurantList;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.restaurant_list);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//
//
//        // Assuming you have a RecyclerView in your layout for displaying the list
//        recyclerView = findViewById(R.id.recyclerView);
//
//        // Set up RecyclerView layout manager and adapter
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        restaurantList = fetchRestaurantData(); // Fetch restaurant data from your database or source
//        adapter = new RestaurantAdapter(restaurantList);
//        recyclerView.setAdapter(adapter);
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed(); // Go back when the back arrow in the toolbar is clicked
//        return true;
//    }
//
//
//
//    // This is a placeholder method, replace it with your logic to fetch data from the database or source
//    private List<Restaurant> fetchRestaurantData() {
//        // Simulated list of restaurants, replace with your database retrieval logic
//        List<Restaurant> restaurants = new ArrayList<>();
//        restaurants.add(new Restaurant("Restaurant 1", "Address 1", "1234567890", "Description 1", "Tag1, Tag2", 3));
//        restaurants.add(new Restaurant("Restaurant 2", "Address 2", "0987654321", "Description 2", "Tag3, Tag4", 4));
//        // Add more restaurants as needed
//
//        return restaurants;
//    }
//}
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

        // Assuming you have a RecyclerView in your layout for displaying the list
        recyclerView = findViewById(R.id.recyclerView);

        // Set up RecyclerView layout manager and adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantList = fetchRestaurantData(); // Fetch restaurant data from your database or source
        adapter = new RestaurantAdapter(restaurantList);
        recyclerView.setAdapter(adapter);

        // Set item click listener for the adapter
        adapter.setOnItemClickListener(restaurant -> {
            // Handle item click - navigate to RestaurantDetailsActivity with details of the selected restaurant
            Intent intent = new Intent(RestaurantListActivity.this, RestaurantDetailsActivity.class);
            intent.putExtra("selectedRestaurant", restaurant);
            startActivity(intent);
        });
    }

    // Placeholder method, replace it with your logic to fetch data from the database or source
    private List<Restaurant> fetchRestaurantData() {
        // Simulated list of restaurants, replace with your database retrieval logic
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Restaurant 1", "Address 1", "1234567890", "Description 1", "Tag1, Tag2", 3));
        restaurants.add(new Restaurant("Restaurant 2", "Address 2", "0987654321", "Description 2", "Tag3, Tag4", 4));
        // Add more restaurants as needed

        return restaurants;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when the back arrow in the toolbar is clicked
        return true;
    }
}

