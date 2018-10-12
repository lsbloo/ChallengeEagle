package com.example.osvaldoairon.challengeeeagle.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Recipe implements Serializable {

    private String name_dish;
    private String description;
    private String how_to_make;

    private Bitmap bitmap_img;
    private byte[] byte_img;

    private String user_mask;

    private long id;

    public String getDescription() {
        return description;
    }

    public Bitmap getBitmap_img() {
        return bitmap_img;
    }

    public String getName_dish() {
        return name_dish;
    }

    public String getHow_to_make() {
        return how_to_make;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBitmap_img(Bitmap bitmap_img) {
        this.bitmap_img = bitmap_img;
    }

    public void setHow_to_make(String how_to_make) {
        this.how_to_make = how_to_make;
    }

    public void setName_dish(String name_dish) {
        this.name_dish = name_dish;
    }

    public byte[] getByte_img() {
        return byte_img;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setByte_img(byte[] byte_img) {
        this.byte_img = byte_img;
    }

    public String getUser_mask() {
        return user_mask;
    }

    public void setUser_mask(String user_mask) {
        this.user_mask = user_mask;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Recipe(){
        /**
         * Default Constructor
         */
    }
    public Recipe(String user_mask,String name_dish,String description,String how_to_make,Bitmap bitmap ,byte[] bite){
        setBitmap_img(bitmap);
        setByte_img(bite);
        setDescription(description);
        setName_dish(name_dish);
        setHow_to_make(how_to_make);
        setUser_mask(user_mask);

    }
}
