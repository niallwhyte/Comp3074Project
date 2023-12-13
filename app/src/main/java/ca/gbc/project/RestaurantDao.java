package ca.gbc.project;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RestaurantDao {

    @Query("SELECT * FROM restaurants")
    List<Restaurant> getAllRestaurants();
    @Insert
    void insertRestaurant(Restaurant restaurant);

    @Delete
    void deleteRestaurant(Restaurant restaurant);

    @Query("DELETE FROM restaurants WHERE id = :restaurantId")
    void deleteRestaurantById(long restaurantId);
}
