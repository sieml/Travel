package com.travel.personaltravel.model.morecity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by coder
 * time 2015/11/3.
 */
@Table(name = "Image")
public class Image {
    @Id(column = "id")
    private String id;
    @Column(column = "full")
    private String full;
    @Column(column = "height")
    private int height;
    @Column(column = "thumb")
    private String thumb;
    @Column(column = "url")
    private String url;
    @Column(column = "width")
    private int width;
    @Column(column = "caption")
    private String caption;

    public Image() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "full='" + full + '\'' +
                ", height=" + height +
                ", thumb='" + thumb + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", caption='" + caption + '\'' +
                '}';
    }

    public Image(String full, int height, String thumb, String url, int width, String caption) {
        this.full = full;
        this.height = height;
        this.thumb = thumb;
        this.url = url;
        this.width = width;
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
