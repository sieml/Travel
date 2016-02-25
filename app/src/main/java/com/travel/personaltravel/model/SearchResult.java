package com.travel.personaltravel.model;

/**
 * Created by Android Studio
 * Author: SieLee
 * Email: siesielee@gmail.com
 * Date: 2015/11/3 [16:27]
 */

import java.util.List;

/**
 * 搜索的结果 列表
 * URL: SEARCH_CITY_LIST
 */
public class SearchResult {


    /**
     * address : 朝阳区建国门外大街8号秀水街6-7楼
     * enName :
     * id : 54ae6b745c142faec2f782d8
     * images : [{"caption":"","full":"http://images.taozilvxing.com/e165be6f5551746b9bd4e296c6286bd0?imageView2/2/w/1200","height":81,"thumb":"http://images.taozilvxing.com/e165be6f5551746b9bd4e296c6286bd0?imageView2/2/w/200","url":"http://images.taozilvxing.com/e165be6f5551746b9bd4e296c6286bd0?imageView2/2/w/100","width":100}]
     * locality : {"enName":"","id":"5473ccd7b8ce043a64108c46","zhName":"北京"}
     * location : {"coordinates":[116.44964,39.9089]}
     * price : 100.0
     * priceDesc : 人均￥100
     * rank : 130
     * rating : 0.7
     * style : ["烤鸭"]
     * type : restaurant
     * zhName : 北京烤鸭
     */

    private String address;
    private String enName;
    private String id;
    /**
     * enName :
     * id : 5473ccd7b8ce043a64108c46
     * zhName : 北京
     */

    private LocalityEntity locality;
    private LocationEntity location;
    private double price;
    private String priceDesc;
    private int rank;
    private double rating;
    private String type;
    private String zhName;
    /**
     * caption :
     * full : http://images.taozilvxing.com/e165be6f5551746b9bd4e296c6286bd0?imageView2/2/w/1200
     * height : 81
     * thumb : http://images.taozilvxing.com/e165be6f5551746b9bd4e296c6286bd0?imageView2/2/w/200
     * url : http://images.taozilvxing.com/e165be6f5551746b9bd4e296c6286bd0?imageView2/2/w/100
     * width : 100
     */

    private List<ImagesEntity> images;
    private List<String> style;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocality(LocalityEntity locality) {
        this.locality = locality;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPriceDesc(String priceDesc) {
        this.priceDesc = priceDesc;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public void setImages(List<ImagesEntity> images) {
        this.images = images;
    }

    public void setStyle(List<String> style) {
        this.style = style;
    }

    public String getAddress() {
        return address;
    }

    public String getEnName() {
        return enName;
    }

    public String getId() {
        return id;
    }

    public LocalityEntity getLocality() {
        return locality;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceDesc() {
        return priceDesc;
    }

    public int getRank() {
        return rank;
    }

    public double getRating() {
        return rating;
    }

    public String getType() {
        return type;
    }

    public String getZhName() {
        return zhName;
    }

    public List<ImagesEntity> getImages() {
        return images;
    }

    public List<String> getStyle() {
        return style;
    }

    public static class LocalityEntity {
        private String enName;
        private String id;
        private String zhName;

        public void setEnName(String enName) {
            this.enName = enName;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setZhName(String zhName) {
            this.zhName = zhName;
        }

        public String getEnName() {
            return enName;
        }

        public String getId() {
            return id;
        }

        public String getZhName() {
            return zhName;
        }
    }

    public static class LocationEntity {
        private List<Double> coordinates;

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }
    }

    public static class ImagesEntity {
        private String caption;
        private String full;
        private int height;
        private String thumb;
        private String url;
        private int width;

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getCaption() {
            return caption;
        }

        public String getFull() {
            return full;
        }

        public int getHeight() {
            return height;
        }

        public String getThumb() {
            return thumb;
        }

        public String getUrl() {
            return url;
        }

        public int getWidth() {
            return width;
        }
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "address='" + address + '\'' +
                ", enName='" + enName + '\'' +
                ", id='" + id + '\'' +
                ", locality=" + locality +
                ", location=" + location +
                ", price=" + price +
                ", priceDesc='" + priceDesc + '\'' +
                ", rank=" + rank +
                ", rating=" + rating +
                ", type='" + type + '\'' +
                ", zhName='" + zhName + '\'' +
                ", images=" + images +
                ", style=" + style +
                '}';
    }
}
