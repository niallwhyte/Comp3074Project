package ca.gbc.project;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "restaurants")
public class Restaurant implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String address;
    private String phoneNumber;
    private String description;
    private String tags;

    private float rating;

    public Restaurant(String name, String address, String phoneNumber, String description, String tags, float rating) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.tags = tags;
        this.rating = rating;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    protected Restaurant(Parcel in) {
        name = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        description = in.readString();
        tags = in.readString();
        rating = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phoneNumber);
        dest.writeString(description);
        dest.writeString(tags);
        dest.writeFloat(rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };


}

