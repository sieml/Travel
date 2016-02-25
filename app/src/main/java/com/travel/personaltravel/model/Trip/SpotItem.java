package com.travel.personaltravel.model.Trip;

import com.travel.personaltravel.model.morecity.Image;

import java.util.List;

/**
 * Created by coder
 * time 2015/11/5.
 */
public class SpotItem {
    private List<Image> images;
    private String zhName;

    @Override
    public String toString() {
        return "SpotItem{" +
                "images=" + images +
                ", zhName='" + zhName + '\'' +
                '}';
    }



    public SpotItem() {
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
