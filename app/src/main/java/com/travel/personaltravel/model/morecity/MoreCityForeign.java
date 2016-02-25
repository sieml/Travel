package com.travel.personaltravel.model.morecity;

import java.util.List;

/**
 * Created by coder
 * time 2015/11/3.
 */

public class MoreCityForeign {
    private String code;
    private Continents continents;
    private List<Destination> destinations;
    private String enName;
    private String id;
    private List<Image> images;
    private String zhName;

    @Override
    public String toString() {
        return "MoreCityForeign{" +
                "JP='" + code + '\'' +
                ", continents=" + continents +
                ", destinations=" + destinations +
                ", enName='" + enName + '\'' +
                ", id='" + id + '\'' +
                ", images=" + images +
                ", zhName='" + zhName + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Continents getContinents() {
        return continents;
    }

    public void setContinents(Continents continents) {
        this.continents = continents;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
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

    public class Continents{

        private String code;
        private String enName;
        private String id;
        private String zhName;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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
    }

}
