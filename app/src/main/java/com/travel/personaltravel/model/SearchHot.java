package com.travel.personaltravel.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Android Studio
 * Author: SieLee
 * Email: siesielee@gmail.com
 * Date: 2015/11/4 [15:33]
 */
public class SearchHot implements Serializable {
    private String query;
    private String zhName;

    public static List<SearchHot> parseJSON(String jsonStr) throws JSONException {
        List<SearchHot> ret=null;
        JSONObject object=new JSONObject(jsonStr);
        JSONArray array=object.getJSONArray("result");

        TypeToken<List<SearchHot>> token = new TypeToken<List<SearchHot>>() {
        };
        Gson gson=new Gson();
        ret = gson.fromJson(array.toString(), token.getType());

        return ret;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    @Override
    public String toString() {
        return "SearchHot{" +
                "query='" + query + '\'' +
                ", zhName='" + zhName + '\'' +
                '}';
    }
}
