package com.travel.personaltravel.model;

/**
 * Created by on
 * Author: Zern
 * DATE:2015/11/4
 * Time: 0:06
 * email:AndroidZern@163.com
 */
public class CityDetailImg {
    private String caption ;
    private String full ;
    private int height ;
    private String thumb ;
    private String url ;
    private int width ;

    @Override
    public String toString() {
        return "CityDetailImg{" +
                "caption='" + caption + '\'' +
                ", full='" + full + '\'' +
                ", height=" + height +
                ", thumb='" + thumb + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                '}';
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

    public CityDetailImg(String caption, String full, int height, String thumb, String url, int width) {
        this.caption = caption;
        this.full = full;
        this.height = height;
        this.thumb = thumb;
        this.url = url;
        this.width = width;
    }
}
