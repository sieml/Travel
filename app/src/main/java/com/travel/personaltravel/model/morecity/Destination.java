package com.travel.personaltravel.model.morecity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Transient;

import java.util.List;

/**
 * Created by coder
 * time 2015/11/3.
 */
@Table(name = "Destination")
public class Destination {
    @Column(column = "enName")
    private String enName;
    @Id(column = "id")
    private String id;
    @Column(column = "zhName")
    private String zhName;
    @Transient()
    private List<Image> images;
    @Transient()
    private MoreCityInland.Location location;
    public Destination(String enName, String id, String zhName, List<Image> images, MoreCityInland.Location location) {
        this.enName = enName;
        this.id = id;
        this.zhName = zhName;
        this.images = images;
        this.location = location;
    }

    public Destination() {

    }

    @Override
    public String toString() {
        return "Destination{" +
                "enName='" + enName + '\'' +
                ", id='" + id + '\'' +
                ", zhName='" + zhName + '\'' +
                ", images=" + images +
                ", location=" + location +
                '}';
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

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public MoreCityInland.Location getLocation() {
        return location;
    }

    public void setLocation(MoreCityInland.Location location) {
        this.location = location;
    }
}
