package com.travel.personaltravel.constant;

/**
 * Created by Android Studio
 * Author: SieLee
 * Email: siesielee@gmail.com
 * Date: 2016/2/24 [0:09]
 *
 * @Description: TODO
 */
public class SieConstant {
    //扉页的共享配置文件的名字
    public static final String Splash_Share = "Splash_Share";
    //欢迎页显示的版本,根据版本判断,是否显示欢迎页
    public static final String App_Version = "Version";

    //扉页的图片网址
    public static final String Url_Splash_Pic = "http://7sbm17.com1.z0.glb.clouddn.com/lvxingpai-cover-20151009-1080-1920.png?imageView/1/w/720/h/1280/q/85/format/jpg/interlace/1";

    //城市详情
    public static final String Url_City_Detail = "http://api.lvxingpai.com/app/geo/localities/%s?noteCnt=3&imgWidth=480";

    //城市详情中的达人评价表
    public static final String Url_City_CommentList = "http://api.lvxingpai.com/app/users/experts?zone=%E4%B8%89%E4%BA%9A";

    //美食介绍
    public static final String Url_Food_Content = "http://api.lvxingpai.com/app/guides/locality/%s/restaurant";

    //美食介绍文字的点击网址
    public static final String Url_Food_Guide = "http://h5.taozilvxing.com/city/dining.php?tid=%s";


    //美食下面的ListView
    public static final String Url_Food_ListView = "http://api.lvxingpai.com/app/poi/restaurants?locality=%s&page=%d&pageSize=15&imgWidth=300";

    // WebView跳转的标记
    public static final String WebView_Tag = "webViewTag";

    //购物介绍
    public static final String Url_Shop_Content = "http://api.lvxingpai.com/app/guides/locality/%s/shopping";

    // 购物的介绍文字的点击网址
    public static final String Url_Shopping_Guide = "http://h5.taozilvxing.com/city/shopping.php?tid=%s";

    //TODO-----以下接口是搜索接口--->----组员 siequn 添加-------请不要修改--------------------------------------------------------------------------------------------
    public static final String INTENT_CITY_ID = "cityID";

    public static final String INTENT_CITY_NAME = "cityNAME";
    /**
     * 底部导航栏"搜索" 搜索的结果显示列表
     * 上海
     * http://api.lvxingpai.com/app/search?keyword=%E4%B8%8A%E6%B5%B7&loc=true&vs=true&hotel=true&restaurant=true&shopping=true&imgWidth=100
     * 火锅
     * http://api.lvxingpai.com/app/search?keyword=%E7%81%AB%E9%94%85&loc=true&vs=true&hotel=true&restaurant=true&shopping=true&imgWidth=100
     * 羽绒服
     * http://api.lvxingpai.com/app/search?keyword=%E7%BE%BD%E7%BB%92%E6%9C%8D&loc=true&vs=true&hotel=true&restaurant=true&shopping=true&imgWidth=100
     * 苏州园林
     * http://api.lvxingpai.com/app/search?keyword=%E8%8B%8F%E5%B7%9E%E5%9B%AD%E6%9E%97&loc=true&vs=true&hotel=true&restaurant=true&shopping=true&imgWidth=100
     */

    public static final String SEARCH_CITY_LIST = "http://api.lvxingpai.com/app/search?keyword=%s&loc=true&vs=true&hotel=true&restaurant=true&shopping=true&imgWidth=100";
    /**
     * "城市" 详情,所需参数: 城市ID
     */
    public static final String SEARCH_CITY_DETAIL = "http://api.lvxingpai.com/app/geo/localities/%s?noteCnt=3&imgWidth=480";
    /**
     * 获取gridview中的全部图片
     *
     * @param cityId
     * @return
     */
    public static final String CITY_LOCAL_EXPERT = "http://api.lvxingpai.com/app/users/experts?zone=%s";
    /**
     * 热门搜搜 tag标签列表
     */
    public static final String SEARCH_CIRT_HOT_LIST = "http://api.lvxingpai.com/app/search/hot-queries";
    /**
     * "景点" 详情, 参数: 景点id
     */
    public static final String SEARCH_VIEWSPOT_DETAL = "http://api.lvxingpai.com/app/poi/viewspots/%s";
    /**
     * "美食" 详情, 参数: 美食id
     */
    public static final String SEARCH_FOOD_DETAIL = "http://api.lvxingpai.com/app/poi/restaurants/%s";
    /**
     * "购物" 详情, 参数: 购物id
     */
    public static final String SEARCH_SHOPPING_DETAIL = "http://api.lvxingpai.com/app/poi/shopping/%s";
    /**
     * "游记" 列表, 参数: 城市query
     */
    public static final String CITY_TRAVEL_NOTE = "http://api.lvxingpai.com/app/travelnotes?query=%s&sortby=posttime&sort=desc&page=%d&pageSize=15&imgWidth=200";
    /**
     * "游记item" 列表, 参数id
     */
    public static final String CITY_TRAVEL_NOTE_WEBVIEW = "http://h5.taozilvxing.com/dayDetail.php?id=%s";
    //TODO--------以上接口是搜索接口------>-------组员 siequn 添加------------------------------------------------------------------------------------------------------
    /**
     * 推荐的国外的城市的json数据
     */
    public static String pathForeignRecommend = "http://api.lvxingpai.com/app/geo/localities/recommendations?abroad=true";
    /**
     * 推荐的国内的城市的json数据
     */
    public static String pathInlandRecommend = "http://api.lvxingpai.com/app/geo/localities/recommendations?abroad=false";
    /**
     * 所有国内城市的json数据
     */
    public static String pathInlandAll = "http://api.lvxingpai.com/app/geo/localities/domestic?groupBy=true&imgWidth=450";
    /**
     * 所有国外城市的json数据
     */
    public static String pathForeignAll = "http://api.lvxingpai.com/app/geo/localities/abroad?groupBy=true&imgWidth=300";
    /**
     * 城市id
     */

    public static final String ACTION_EXTRA = "cityId";

    public static final String ACTION_EXTRA_PAGE = "page";

    public static final String ACTION_EXTRA_CITY_NAME = "cityName";

    public static String getalbumPath(String cityId) {
        return "http://api.lvxingpai.com/app/geo/localities/" + cityId + "/albums?imgWidth=284";
    }

    /**
     * 获取点击景点后的listView中的数据路径
     *
     * @param cityId
     * @return
     */
    public static String getSpotDataPath(String cityId, int page) {
        return "http://api.lvxingpai.com/app/poi/viewspots?locality=" + cityId + "&page=" + page + "&pageSize=15&imgWidth=300";
    }

    /**
     * 获取景点中的item点击之后的数据
     *
     * @param cityId
     * @return
     */
    public static String getSpotListViewItemPath(String cityId) {
        return "http://api.lvxingpai.com/app/poi/viewspots/" + cityId;
    }
    /**
     * 基础路径
     */
    public static final String baseUrl = "http://api.lvxingpai.com";

    /**
     * 刪除旅遊計劃http://api.lvxingpai.com/app/guides/563730e47a2b5a0001b4018d
     */
    public static final String delPlan = "/app/guides/%s";
    /**
     * 用户信息 userid 206502
     */
    public static final String userurl = "/app/users/%s";
    /**
     * 我的主页  userId 206502  page 0
     */
    public static final String homeUrl = "/app/users/%s/guides?page=%d&pageSize=15";
    /**
     * 旅行计划详情 userId  206502  guideid 5636d2d07a2b5a0001b3e471
     */
    public static final String travelPlan = "/app/users/%s/guides/%s?imgWidth=300";
    /**
     * 景点详情
     */
    public static final String featureUrl = "/app/poi/viewspots/547bfe2eb8ce043eb2d88efb";

    /**
     * 美食详情
     */
    public static final String foodUrl = "/app/poi/restaurants/54ae69f85c142faec2f70cdc";

    /**
     * 收集的美食推荐
     */
    //   介绍 locid 546f2da8b8ce0440eddb2891
    public static final String introRestaurant = " /app/guides/locality/%s/restaurant ";

    //推荐列表  locid 546f2da8b8ce0440eddb2891  page 0
    public static final String listRestaurant = " /app/poi/restaurants?locality=%s&page=%d&pageSize=15&imgWidth=450";

    /**
     * 搜索美食 keyword=%E6%8B%89%E9%9D%A2  locId =546f2da8b8ce0440eddb2891 page=0
     */
    public static final String searchRes = " /app/search?keyword=%s&restaurant=true&locId=%d&page=%d&pageSize=15&imgWidth=300";

    /**
     * 购物详情
     */
    public static final String ShopUrl = "/app/poi/shopping/54ae92885c142faec2fb8412";

    /**
     * 收集购物推荐
     */
    public static final String introShop = "/app/guides/locality/546f2da8b8ce0440eddb2891/shopping";
    public static final String listShop = "   /app/poi/shopping?locality=546f2da8b8ce0440eddb2891&page=0&pageSize=15&imgWidth=450 ";

    /**
     * 搜索购物  keyword=%E9%87%8D%E7%99%BE locid 5473ccd6b8ce043a64108c08 page 0
     */
    public static final String SearchShop = " /app/search?keyword=%s&shopping=true&locId=%s&page=%d&pageSize=15&imgWidth=300 ";

    /**
     * 联系人详情
     */
    public static final String linkman = "/app/users/206506";
    public static final String linkmanAlbum = "/app/users/206506/albums ";
}
