package com.etologic.fintonictestchallenge.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hero {

    @SerializedName ("name")
    @Expose
    private String name;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("realName")
    @Expose
    private String realName;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("power")
    @Expose
    private String power;
    @SerializedName("abilities")
    @Expose
    private String abilities;
    @SerializedName("groups")
    @Expose
    private String groups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Hero(String name,
                String photo,
                String realName,
                String height,
                String power,
                String abilities,
                String groups) {
        this.name = name;
        this.photo = photo;
        this.realName = realName;
        this.height = height;
        this.power = power;
        this.abilities = abilities;
        this.groups = groups;
    }
}
