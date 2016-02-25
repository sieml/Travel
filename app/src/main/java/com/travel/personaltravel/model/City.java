package com.travel.personaltravel.model;

import com.travel.personaltravel.model.morecity.Image;

import java.util.List;

/**
 * Created by coder
 * time 2015/11/3.
 */
public class City {
    private String enName;
    private String id;
    private List<Image> images;
    private String zhName;

    public City() {
    }

    public City(String enName, String id, List<Image> images, String zhName) {
        this.enName = enName;
        this.id = id;
        this.images = images;
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

}

