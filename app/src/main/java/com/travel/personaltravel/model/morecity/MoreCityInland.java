package com.travel.personaltravel.model.morecity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by coder
 * time 2015/11/3.
 */
public class MoreCityInland {
    private String enName;
    private String id;
    private String pinyin;
    private String zhName;
    private List<Destination> destinations;

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

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        return "MoreCityInland{" +
                "enName='" + enName + '\'' +
                ", id='" + id + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", zhName='" + zhName + '\'' +
                ", destinations=" + destinations +
                '}';
    }

    public MoreCityInland() {
    }

    public MoreCityInland(String enName, String id, String pinyin, String zhName, List<Destination> destinations) {

        this.enName = enName;
        this.id = id;
        this.pinyin = pinyin;
        this.zhName = zhName;
        this.destinations = destinations;
    }
    @Table(name = "Location")
    public class Location {
        @Column(column = "coordinates")
        private List<Double> coordinates;

        @Override
        public String toString() {
            return "Location{" +
                    "coordinates=" + coordinates +
                    '}';
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }
    }
}
