package com.hlong.finallt;

public class Nearby1 {
    int id  ;
    String nameStore;
    String location;
    String rating,imageStore;
    private String reviewers;
    int time;
    int distance;


    public Nearby1(int id, String nameStore, String location, String rating, String imageStore, String reviewers, int time, int distance) {
        this.id = id;
        this.nameStore = nameStore;
        this.location = location;
        this.rating = rating;
        this.imageStore = imageStore;
        this.reviewers = reviewers;
        this.time = time;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDistance() {
        return (int) distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getImageStore() {
        return imageStore;
    }

    public void setImageStore(String imageStore) {
        this.imageStore = imageStore;
    }

    public String getReviewers() {
        return reviewers;
    }

    public void setReviewers(String reviewers) {
        this.reviewers = reviewers;
    }
}

