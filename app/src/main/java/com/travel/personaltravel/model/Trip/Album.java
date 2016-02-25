package com.travel.personaltravel.model.Trip;

/**
 * Created by coder
 * time 2015/11/4.
 */
public class Album {

    /**
     * originUrl : http://images.taozilvxing.com/3f38114a0c5fdcac0b702ce11f015a53?imageView2/2/w/960
     * url : http://images.taozilvxing.com/3f38114a0c5fdcac0b702ce11f015a53?imageView2/2/w/284
     */

    private String originUrl;
    private String url;

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Album{" +
                "originUrl='" + originUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
