package ca.gbc.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Restaurant> restaurants = fetchRestaurantData();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapter = new RestaurantAdapter(restaurants);
                        recyclerView.setAdapter(adapter);

                        adapter.setOnItemClickListener(restaurant -> {
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
        RestaurantDatabase db = RestaurantDatabase.getDatabase(this);
        List<Restaurant> restaurants = db.restaurantDao().getAllRestaurants();

        return restaurants;
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

