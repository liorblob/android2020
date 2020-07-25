package com.example.stackoverflow.model;

public class Search {
    private long searchID;
    private String searchKey;
    private float locationX;
    private float locationY;

    public Search(){}

    public Search(long searchID, String searchKey, float locationX, float locationY) {
        setSearchID(searchID);
        setSearchKey(searchKey);
        setLocationX(locationX);
        setLocationY(locationY);
    }

    public long getSearchID() {
        return searchID;
    }

    public void setSearchID(long searchID) {
        this.searchID = searchID;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }
}
