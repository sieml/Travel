package com.travel.personaltravel.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/3.
 */
public class Plan  {
    String  id;

    String     title;
    String      updateTime;
    String     dayCnt;
    List<Img> images;
    String    summary;

    public static class Img{
        String   thumb;

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

    }

    public List<Img> getImages() {
        return images;

    }

    public void setImages(List<Img> images) {
        this.images = images;
    }

    public static List<Plan> getListPlan(String json){
        List<Plan> lists=new ArrayList<>();

        try {
            JSONArray array=new JSONObject(json).getJSONArray("result");
            TypeToken<List<Plan>> token= new TypeToken<List<Plan>>(){};
            Gson gson= new Gson();

            //开始解析核心内容
          lists=gson.fromJson(array.toString(), token.getType());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return lists;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDayCnt() {
        return dayCnt;
    }

    public void setDayCnt(String dayCnt) {
        this.dayCnt = dayCnt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
