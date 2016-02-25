package com.travel.personaltravel.model.Trip;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Android Studio
 * Author: SieLee
 * Email: siesielee@gmail.com
 * Date: 2015/11/6 [11:08]
 */
public class TravelNote {


    /**
     * authorAvatar :
     * authorName : 央金
     * detailUrl : http://h5.taozilvxing.com/dayDetail.php?id=54b56bef5a2df6bba951fdde
     * essence : false
     * id : 54b56bef5a2df6bba951fdde
     * images : [{"caption":"","full":"http://images.taozilvxing.com/d2366f68cbc46715c03cb266f6f2e8a4?imageView2/2/w/1200","thumb":"http://images.taozilvxing.com/d2366f68cbc46715c03cb266f6f2e8a4?imageView2/2/w/200","url":"http://images.taozilvxing.com/d2366f68cbc46715c03cb266f6f2e8a4?imageView2/2/w/200"}]
     * publishTime : 1334122407000
     * source :
     * summary : ...由于种种原因，杭州没去成，在上海呆了几天。虽然是个老掉牙的旅游地，但还是有很多人去哦，所以分享下~~~（我是央金，央金是我~飘过）地点：南京路-外滩-东方明珠-上海美术馆-城隍庙-上海博物馆-夜游黄浦江-锦江乐园-田子坊-人民广场第一天，由于我在周庄，所以捏，先带闺蜜到周庄玩一圈。起个大早，告诉大家...
     * title : 上海上海！！
     */

    private String authorAvatar;
    private String authorName;
    private String detailUrl;
    private boolean essence;
    private String id;
    private long publishTime;
    private String source;
    private String summary;
    private String title;
    /**
     * caption :
     * full : http://images.taozilvxing.com/d2366f68cbc46715c03cb266f6f2e8a4?imageView2/2/w/1200
     * thumb : http://images.taozilvxing.com/d2366f68cbc46715c03cb266f6f2e8a4?imageView2/2/w/200
     * url : http://images.taozilvxing.com/d2366f68cbc46715c03cb266f6f2e8a4?imageView2/2/w/200
     */

    private List<ImagesEntity> images;

    public static List<TravelNote> parseJSON(String json) throws JSONException {
        List<TravelNote> ret = null;
        JSONObject object = new JSONObject(json);
        JSONArray array = object.getJSONArray("result");
        Gson gson = new Gson();
        TypeToken<List<TravelNote>> typeToken = new TypeToken<List<TravelNote>>(){};
        ret = gson.fromJson(array.toString(), typeToken.getType());

        return ret;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public void setEssence(boolean essence) {
        this.essence = essence;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImages(List<ImagesEntity> images) {
        this.images = images;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public boolean isEssence() {
        return essence;
    }

    public String getId() {
        return id;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public String getSource() {
        return source;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public List<ImagesEntity> getImages() {
        return images;
    }

    public static class ImagesEntity {
        private String caption;
        private String full;
        private String thumb;
        private String url;

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCaption() {
            return caption;
        }

        public String getFull() {
            return full;
        }

        public String getThumb() {
            return thumb;
        }

        public String getUrl() {
            return url;
        }
    }

    @Override
    public String toString() {
        return "TravelNote{" +
                "authorAvatar='" + authorAvatar + '\'' +
                ", authorName='" + authorName + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", essence=" + essence +
                ", id='" + id + '\'' +
                ", publishTime=" + publishTime +
                ", source='" + source + '\'' +
                ", summary='" + summary + '\'' +
                ", title='" + title + '\'' +
                ", images=" + images +
                '}';
    }
}
