package com.travel.personaltravel.model.morecity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Android Studio
 * Author: SieLee
 * Email: siesielee@gmail.com
 * Date: 2015/11/4 [13:28]
 */
public class SearchFirstCity {
    String id;
    String zhName;

    public List<Imgs> getImages() {
        return images;
    }

    public void setImages(List<Imgs> images) {
        this.images = images;
    }

    List<Imgs> images;
    Location location;

    public static class Location {
        public List<String> coordinates;
    }

    public static SearchFirstCity parseJson(String jsonStr) throws JSONException {

        JSONObject json=new JSONObject(jsonStr);
        JSONObject obj = json.getJSONObject("result");
        JSONArray locality = obj.getJSONArray("locality");
        JSONObject jsonObject = locality.getJSONObject(0);

        SearchFirstCity t = null;
        Gson gson = new Gson();
        t = gson.fromJson(jsonObject.toString(), SearchFirstCity.class);
        return t;
    }


    public static class Imgs {
        String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
