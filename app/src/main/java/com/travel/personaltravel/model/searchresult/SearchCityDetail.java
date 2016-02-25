package com.travel.personaltravel.model.searchresult;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Android Studio
 * Author: SieLee
 * Email: siesielee@gmail.com
 * Date: 2015/11/5 [15:34]
 */
public class SearchCityDetail {


    /**
     * desc : 上海，又称“上海滩”，是一座极具现代化而又不为展示中国经济发展和改革开放新成就的窗口，并于2010年成功举办了第41届世界博览会。
     * enName : shanghai
     * id : 5473ccd7b8ce043a64108c4d
     * imageCnt : 6
     * images : [{"caption":"","full":"http://images.taozilvxing.com/28c2d1ef35c12100e99fecddb63c436a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!960x640a0a8/thumbnail/1200","height":640,"thumb":"http://images.taozilvxing.com/28c2d1ef35c12100e99fecddb63c436a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!960x640a0a8/thumbnail/200","url":"http://images.taozilvxing.com/28c2d1ef35c12100e99fecddb63c436a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!960x640a0a8/thumbnail/480","width":960},{"caption":"","full":"http://images.taozilvxing.com/79e0a788f2b46e52227ac3d74561e6a9?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!2048x1238a0a118/thumbnail/1200","height":1238,"thumb":"http://images.taozilvxing.com/79e0a788f2b46e52227ac3d74561e6a9?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!2048x1238a0a118/thumbnail/200","url":"http://images.taozilvxing.com/79e0a788f2b46e52227ac3d74561e6a9?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!2048x1238a0a118/thumbnail/480","width":2048},{"caption":"","full":"http://images.taozilvxing.com/6fe1614eb9831ee6f015868abce103be?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!800x482a0a0/thumbnail/1200","height":482,"thumb":"http://images.taozilvxing.com/6fe1614eb9831ee6f015868abce103be?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!800x482a0a0/thumbnail/200","url":"http://images.taozilvxing.com/6fe1614eb9831ee6f015868abce103be?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!800x482a0a0/thumbnail/480","width":800},{"caption":"","full":"http://images.taozilvxing.com/41bdd9f408b00d58e805c55e1ebc3e6b?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!698x406a2a0/thumbnail/1200","height":406,"thumb":"http://images.taozilvxing.com/41bdd9f408b00d58e805c55e1ebc3e6b?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!698x406a2a0/thumbnail/200","url":"http://images.taozilvxing.com/41bdd9f408b00d58e805c55e1ebc3e6b?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!698x406a2a0/thumbnail/480","width":698},{"caption":"","full":"http://images.taozilvxing.com/25094bcc056112ed8ba76a287c2312eb?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!798x492a2a0/thumbnail/1200","height":492,"thumb":"http://images.taozilvxing.com/25094bcc056112ed8ba76a287c2312eb?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!798x492a2a0/thumbnail/200","url":"http://images.taozilvxing.com/25094bcc056112ed8ba76a287c2312eb?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!798x492a2a0/thumbnail/480","width":798},{"caption":"","full":"http://images.taozilvxing.com/766d68f7b0f44a111fb53f3135ae9039?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!800x522a0a4/thumbnail/1200","height":522,"thumb":"http://images.taozilvxing.com/766d68f7b0f44a111fb53f3135ae9039?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!800x522a0a4/thumbnail/200","url":"http://images.taozilvxing.com/766d68f7b0f44a111fb53f3135ae9039?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!800x522a0a4/thumbnail/480","width":800}]
     * isFavorite : false
     * isVote : false
     * location : {"coordinates":[121.474101,31.2378]}
     * playGuide : http://h5.taozilvxing.com/city/items.php?tid=5473ccd7b8ce043a64108c4d
     * shoppingTitles : 梨膏糖 五香豆 凤尾鱼罐头 嘉定竹刻 上海织绣 上海邵万生黄泥螺
     * timeCostDesc : 3-7天
     * travelMonth : 上海位于长江注意防寒保暖；6、7月份是上海的梅雨季节，外出要注意带伞。
     * traveled : false
     * voteCnt : 4
     * zhName : 上海
     */

    private String desc;
    private String enName;
    private String id;
    private int imageCnt;
    private boolean isFavorite;
    private boolean isVote;
    private LocationEntity location;
    private String playGuide;
    private String shoppingTitles;
    private String timeCostDesc;
    private String travelMonth;
    private boolean traveled;
    private int voteCnt;
    private String zhName;
    /**
     * caption :
     * full : http://images.taozilvxing.com/28c2d1ef35c12100e99fecddb63c436a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!960x640a0a8/thumbnail/1200
     * height : 640
     * thumb : http://images.taozilvxing.com/28c2d1ef35c12100e99fecddb63c436a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!960x640a0a8/thumbnail/200
     * url : http://images.taozilvxing.com/28c2d1ef35c12100e99fecddb63c436a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!960x640a0a8/thumbnail/480
     * width : 960
     */


    public static SearchCityDetail parseJSON(String json) throws JSONException {
        SearchCityDetail ret = null;
        TypeToken<SearchCityDetail> token = new TypeToken<SearchCityDetail>() {
        };

        //gson 解析
        JSONObject object = new JSONObject(json);
        JSONObject result = object.getJSONObject("result");
        Gson gson = new Gson();
        ret = gson.fromJson(result.toString(), token.getType());

        return ret;
    }
    private List<ImagesEntity> images;

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageCnt(int imageCnt) {
        this.imageCnt = imageCnt;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void setIsVote(boolean isVote) {
        this.isVote = isVote;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public void setPlayGuide(String playGuide) {
        this.playGuide = playGuide;
    }

    public void setShoppingTitles(String shoppingTitles) {
        this.shoppingTitles = shoppingTitles;
    }

    public void setTimeCostDesc(String timeCostDesc) {
        this.timeCostDesc = timeCostDesc;
    }

    public void setTravelMonth(String travelMonth) {
        this.travelMonth = travelMonth;
    }

    public void setTraveled(boolean traveled) {
        this.traveled = traveled;
    }

    public void setVoteCnt(int voteCnt) {
        this.voteCnt = voteCnt;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public void setImages(List<ImagesEntity> images) {
        this.images = images;
    }

    public String getDesc() {
        return desc;
    }

    public String getEnName() {
        return enName;
    }

    public String getId() {
        return id;
    }

    public int getImageCnt() {
        return imageCnt;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public boolean isIsVote() {
        return isVote;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public String getPlayGuide() {
        return playGuide;
    }

    public String getShoppingTitles() {
        return shoppingTitles;
    }

    public String getTimeCostDesc() {
        return timeCostDesc;
    }

    public String getTravelMonth() {
        return travelMonth;
    }

    public boolean isTraveled() {
        return traveled;
    }

    public int getVoteCnt() {
        return voteCnt;
    }

    public String getZhName() {
        return zhName;
    }

    public List<ImagesEntity> getImages() {
        return images;
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
        return "SearchCityDetail{" +
                "desc='" + desc + '\'' +
                ", enName='" + enName + '\'' +
                ", id='" + id + '\'' +
                ", imageCnt=" + imageCnt +
                ", isFavorite=" + isFavorite +
                ", isVote=" + isVote +
                ", location=" + location +
                ", playGuide='" + playGuide + '\'' +
                ", shoppingTitles='" + shoppingTitles + '\'' +
                ", timeCostDesc='" + timeCostDesc + '\'' +
                ", travelMonth='" + travelMonth + '\'' +
                ", traveled=" + traveled +
                ", voteCnt=" + voteCnt +
                ", zhName='" + zhName + '\'' +
                ", images=" + images +
                '}';
    }
}
