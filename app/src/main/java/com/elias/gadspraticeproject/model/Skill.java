package com.elias.gadspraticeproject.model;

import com.google.gson.annotations.SerializedName;

public class Skill {
    @SerializedName("name")
    private String name;
    @SerializedName("score")
    private int score;
    @SerializedName("country")
    private String country;
    @SerializedName("badgeUrl")
    private String badgeUrl;

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
    //Getters
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }
}
