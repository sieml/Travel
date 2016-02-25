package com.travel.personaltravel.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travel.personaltravel.model.City;
import com.travel.personaltravel.model.SearchResult;
import com.travel.personaltravel.model.Trip.Album;
import com.travel.personaltravel.model.Trip.SpotItem;
import com.travel.personaltravel.model.morecity.MoreCityForeign;
import com.travel.personaltravel.model.morecity.MoreCityInland;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by coder
 * time 2015/11/3.
 */
public final class ParseUtils {
    private static Gson gson = new Gson();

    private ParseUtils() {

    }

    /**
     * 获取城市的信息
     *
     * @param json
     * @return
     */
    public static List<City> getCitys(String json) {
        List<City> ret = null;
        if (json != null) {
            ret = new LinkedList<City>();
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray("result");
                City city = null;
                for (int i = 0; i < result.length(); i++) {
                    JSONObject item = result.getJSONObject(i);
                    city = gson.fromJson(item.toString(), City.class);
                    ret.add(city);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 获取全部的城市的信息
     *
     * @param json
     * @return
     */
    public static List<MoreCityInland> getMoreCityInland(String json) {
        List<MoreCityInland> ret = null;
        if (json != null) {
            ret = new LinkedList<>();
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                MoreCityInland moreCityInland = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    moreCityInland = gson.fromJson(item.toString(), MoreCityInland.class);
                    ret.add(moreCityInland);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static List<MoreCityForeign> getMoreCityForeign(String json) {
        List<MoreCityForeign> ret = null;
        if (json != null) {
            ret = new LinkedList<>();
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                MoreCityForeign moreCityForeign = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    moreCityForeign = gson.fromJson(item.toString(), MoreCityForeign.class);
                    ret.add(moreCityForeign);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return ret;
    }

    /**
     * 获取搜索结果
     */
    public static List<SearchResult> getSearchResult(String json) {
        List<SearchResult> ret = null;

        if (json != null) {
            ret = new LinkedList<>();
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject obj = jsonObject.getJSONObject("result");
                JSONArray array1 = obj.getJSONArray("vs");

                //开始解析
                ret.add(new SearchResult());
                List<SearchResult> lists = new LinkedList<>();
                TypeToken<List<SearchResult>> token = new TypeToken<List<SearchResult>>() {
                };
                lists = gson.fromJson(array1.toString(), token.getType());
                ret.addAll(lists);
                ret.add(new SearchResult());
//                Log.d("dd","list = "+lists);

                lists.clear();
                JSONArray array2 = obj.getJSONArray("restaurant");
                lists = gson.fromJson(array2.toString(), token.getType());
                ret.addAll(lists);
                ret.add(new SearchResult());
//                Log.d("dd", "list = " + lists);

                lists.clear();
                JSONArray array3 = obj.getJSONArray("shopping");
                lists = gson.fromJson(array3.toString(), token.getType());
                ret.addAll(lists);
                ret.add(new SearchResult());
//                Log.d("dd", "list = " + lists);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static LinkedList<Album> getAlbums(String json) {
        LinkedList<Album> ret = null;
        if (json != null) {
            ret = new LinkedList<>();
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject result = jsonObject.getJSONObject("result");
                JSONArray album = result.getJSONArray("album");
                Album item = null;
                for (int i = 0; i < album.length(); i++) {
                    item = gson.fromJson(album.getJSONObject(i).toString(), Album.class);
                    ret.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 获取景点处的item的list数据
     *
     * @param json
     * @return
     */
    public static List<SpotItem> getSpotItem(String json) {
        List<SpotItem> ret = null;
        if (json != null) {
            ret = new LinkedList<>();
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray("result");
                for (int i = 0; i < result.length(); i++) {
                    JSONObject item = result.getJSONObject(i);
                    SpotItem spotItem = gson.fromJson(item.toString(), SpotItem.class);
                    ret.add(spotItem);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
