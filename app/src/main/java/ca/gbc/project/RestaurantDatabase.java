package ca.gbc.project;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Restaurant.class}, version = 1, exportSchema = false)
public abstract class RestaurantDatabase extends RoomDatabase {
    public abstract RestaurantDao restaurantDao();

    private static volatile RestaurantDatabase INSTANCE;

    public static RestaurantDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RestaurantDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RestaurantDatabase.class, "restaurant_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
