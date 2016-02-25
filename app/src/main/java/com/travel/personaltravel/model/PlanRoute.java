package com.travel.personaltravel.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
public class PlanRoute {

    /**
     * id : 5636d2d07a2b5a0001b3e471
     * images : [{"url":"http://images.taozilvxing.com/8631f0edbf138936ae2b56427ecdbe2d?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/8631f0edbf138936ae2b56427ecdbe2d?imageView2/2/w/200","full":"http://images.taozilvxing.com/8631f0edbf138936ae2b56427ecdbe2d?imageView2/2/w/1200","caption":"","width":300,"height":202}]
     * title : 京都之旅
     * status : traveled
     * userId : 206502
     * itineraryDays : 2
     * updateTime : 1446433488284
     * localities : [{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""}]
     * itinerary : [{"dayIndex":0,"poi":{"id":"547bfe2eb8ce043eb2d88efb","zhName":"银阁寺","enName":"","rating":0.9199999999999999,"address":"","images":[{"url":"http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/200","full":"http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/1200","caption":"","width":300,"height":200}],"type":"vs","timeCostDesc":"1小时","priceDesc":"500日元","rank":18,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.798118,35.027062]}}},{"dayIndex":0,"poi":{"id":"54814e028b5f77f8306f1530","zhName":"哲学之道","enName":"","rating":0.8800000000000001,"address":"","images":[{"url":"http://images.taozilvxing.com/854fc7d7c3ad4431d2ba75368e541e1a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!994x656a4a12/thumbnail/300","thumb":"http://images.taozilvxing.com/854fc7d7c3ad4431d2ba75368e541e1a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!994x656a4a12/thumbnail/200","full":"http://images.taozilvxing.com/854fc7d7c3ad4431d2ba75368e541e1a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!994x656a4a12/thumbnail/1200","caption":"","width":994,"height":656}],"type":"vs","timeCostDesc":"半小时","priceDesc":"免费","rank":5,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.79505,35.027159]}}},{"dayIndex":0,"poi":{"id":"547bfe06b8ce043eb2d867db","zhName":"京都御所","enName":"","rating":0.86,"address":"","images":[{"url":"http://images.taozilvxing.com/29fa030b058fedcd0f530b829607dd97?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!800x532a0a0/thumbnail/300","thumb":"http://images.taozilvxing.com/29fa030b058fedcd0f530b829607dd97?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!800x532a0a0/thumbnail/200","full":"http://images.taozilvxing.com/29fa030b058fedcd0f530b829607dd97?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!800x532a0a0/thumbnail/1200","caption":"","width":800,"height":532}],"type":"vs","timeCostDesc":"2小时","priceDesc":"免费","rank":9,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.763893,35.023671]}}},{"dayIndex":0,"poi":{"id":"547bfe05b8ce043eb2d866f8","zhName":"花见小路","enName":"","rating":0,"address":"","images":[{"url":"http://images.taozilvxing.com/266c806c84337269f9b0346d6a70a83a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!4240x2832a0a0/thumbnail/300","thumb":"http://images.taozilvxing.com/266c806c84337269f9b0346d6a70a83a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!4240x2832a0a0/thumbnail/200","full":"http://images.taozilvxing.com/266c806c84337269f9b0346d6a70a83a?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!4240x2832a0a0/thumbnail/1200","caption":"","width":4240,"height":2832}],"type":"vs","timeCostDesc":"0.5-1小时","priceDesc":"免费","rank":32,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.775177,35.005534]}}},{"dayIndex":0,"poi":{"id":"5492c4e1e721e7171745af55","zhName":"祇园角","enName":"Gion District","rating":0,"address":"","images":[{"url":"http://images.taozilvxing.com/28c27ddda886c2b59324d0020ce04333?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/28c27ddda886c2b59324d0020ce04333?imageView2/2/w/200","full":"http://images.taozilvxing.com/28c27ddda886c2b59324d0020ce04333?imageView2/2/w/1200","caption":"","width":300,"height":203}],"type":"vs","timeCostDesc":"1小时","priceDesc":"成人：3150日元\n高中生、大学生：2200日元\n初中生、小学生：1900日元","rank":1000000,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":null,"location":{"coordinates":[135.777137,35.003766]}}},{"dayIndex":0,"poi":{"id":"547bfe2eb8ce043eb2d88efc","zhName":"八坂神社","enName":"","rating":0.8800000000000001,"address":"","images":[{"url":"http://images.taozilvxing.com/7326dfcc547391613158a6f63179c545?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!2048x1358a0a0/thumbnail/300","thumb":"http://images.taozilvxing.com/7326dfcc547391613158a6f63179c545?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!2048x1358a0a0/thumbnail/200","full":"http://images.taozilvxing.com/7326dfcc547391613158a6f63179c545?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!2048x1358a0a0/thumbnail/1200","caption":"","width":2048,"height":1358}],"type":"vs","timeCostDesc":"1-2小时","priceDesc":"免费","rank":8,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.778392,35.004304]}}},{"dayIndex":0,"poi":{"id":"547bfe05b8ce043eb2d866e9","zhName":"清水寺","enName":"","rating":0.9199999999999999,"address":"","images":[{"url":"http://images.taozilvxing.com/d9f05a0a3adb749b742c74079f0cd0d5?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!2048x1364a0a0/thumbnail/300","thumb":"http://images.taozilvxing.com/d9f05a0a3adb749b742c74079f0cd0d5?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!2048x1364a0a0/thumbnail/200","full":"http://images.taozilvxing.com/d9f05a0a3adb749b742c74079f0cd0d5?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!2048x1364a0a0/thumbnail/1200","caption":"","width":2048,"height":1364}],"type":"vs","timeCostDesc":"4小时","priceDesc":" 成人：300日元（约22人民币） \n 儿童：200日元（约15人民币）","rank":2,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.784922,34.994988]}}},{"dayIndex":1,"poi":{"id":"547bfe05b8ce043eb2d866ea","zhName":"二条城","enName":"","rating":0.8800000000000001,"address":"","images":[{"url":"http://images.taozilvxing.com/8f62b75f96626c3bc8d2ae3a645029ee?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!842x628a254a0/thumbnail/300","thumb":"http://images.taozilvxing.com/8f62b75f96626c3bc8d2ae3a645029ee?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!842x628a254a0/thumbnail/200","full":"http://images.taozilvxing.com/8f62b75f96626c3bc8d2ae3a645029ee?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!842x628a254a0/thumbnail/1200","caption":"","width":842,"height":628}],"type":"vs","timeCostDesc":"4小时","priceDesc":"成人：600日元（约45人民币） \n学生：350日元（约26人民币） \n儿童：200日元（约15人民币）","rank":1,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.747585,35.014111]}}},{"dayIndex":1,"poi":{"id":"5492cff1e721e7171745dd17","zhName":"金阁寺","enName":"","rating":0.9,"address":"","images":[{"url":"http://images.taozilvxing.com/f4dac044351c362ade2aaa47ba6152e2?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!846x618a0a6/thumbnail/300","thumb":"http://images.taozilvxing.com/f4dac044351c362ade2aaa47ba6152e2?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!846x618a0a6/thumbnail/200","full":"http://images.taozilvxing.com/f4dac044351c362ade2aaa47ba6152e2?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!846x618a0a6/thumbnail/1200","caption":"","width":846,"height":618}],"type":"vs","timeCostDesc":"4小时","priceDesc":" 成人：400日元（约30人民币） \n 儿童：300日元（约22人民币）","rank":4,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":null,"location":{"coordinates":[135.729218,35.039554]}}},{"dayIndex":1,"poi":{"id":"547bfe2eb8ce043eb2d88f00","zhName":"天龙寺","enName":"","rating":0.9400000000000001,"address":"","images":[{"url":"http://images.taozilvxing.com/23e5a197330abb6694d67c0d5fc806b7?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/23e5a197330abb6694d67c0d5fc806b7?imageView2/2/w/200","full":"http://images.taozilvxing.com/23e5a197330abb6694d67c0d5fc806b7?imageView2/2/w/1200","caption":"","width":300,"height":200}],"type":"vs","timeCostDesc":"2小时","priceDesc":"成人：500日元，\n初中生、小学生：300日元\n学龄前儿童：免费","rank":22,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.673617,35.015851]}}},{"dayIndex":1,"poi":{"id":"5492ce6be721e7171745d119","zhName":"岚山","enName":"","rating":0.9199999999999999,"address":"","images":[{"url":"http://images.taozilvxing.com/c7323ae6c5b5355854925d65f3339d63?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!852x546a114a2/thumbnail/300","thumb":"http://images.taozilvxing.com/c7323ae6c5b5355854925d65f3339d63?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!852x546a114a2/thumbnail/200","full":"http://images.taozilvxing.com/c7323ae6c5b5355854925d65f3339d63?imageMogr2/auto-orient/strip/gravity/NorthWest/crop/!852x546a114a2/thumbnail/1200","caption":"","width":852,"height":546}],"type":"vs","timeCostDesc":"2小时","priceDesc":"免费（不包含寺院门票）","rank":3,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.666687,35.009542]}}}]
     * shopping : [{"id":"54ae92885c142faec2fb8412","zhName":"京都手工艺品中心","enName":"","rating":0.96,"address":"21 Entomi-chÅ\u008d, ShÅ\u008dgoin SakyÅ\u008d-ku","images":[{"url":"http://images.taozilvxing.com/4fce71954c89baa360206d16b2fd86d8?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/4fce71954c89baa360206d16b2fd86d8?imageView2/2/w/200","full":"http://images.taozilvxing.com/4fce71954c89baa360206d16b2fd86d8?imageView2/2/w/1200","caption":"","width":300,"height":233}],"type":"shopping","tel":["075 761-5080"],"rank":8,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.780106,35.017651]}},{"id":"54ae92875c142faec2fb8401","zhName":"四条河原町","enName":"","rating":1,"address":"","images":[{"url":"http://images.taozilvxing.com/01213d2b1d2cb4c6dd176b26d100992c?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/01213d2b1d2cb4c6dd176b26d100992c?imageView2/2/w/200","full":"http://images.taozilvxing.com/01213d2b1d2cb4c6dd176b26d100992c?imageView2/2/w/1200","caption":"","width":300,"height":210}],"type":"shopping","tel":[],"rank":1,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.769333,35.003769]}},{"id":"54ae92885c142faec2fb8410","zhName":"锦市场商店街","enName":"","rating":0.96,"address":"京都市中京区锦小路通","images":[{"url":"http://images.taozilvxing.com/0a3845d0076b551601abb309f6fec856?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/0a3845d0076b551601abb309f6fec856?imageView2/2/w/200","full":"http://images.taozilvxing.com/0a3845d0076b551601abb309f6fec856?imageView2/2/w/1200","caption":"","width":300,"height":198}],"type":"shopping","tel":[],"rank":4,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.751404,35.010426]}}]
     * restaurant : [{"id":"54ae69f85c142faec2f70cdc","zhName":"拉面小路","enName":"","rating":1,"address":"京都站车站大楼10楼","images":[{"url":"http://images.taozilvxing.com/bb2eaea5e37bdff3d9a037ba4ebefa27?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/bb2eaea5e37bdff3d9a037ba4ebefa27?imageView2/2/w/200","full":"http://images.taozilvxing.com/bb2eaea5e37bdff3d9a037ba4ebefa27?imageView2/2/w/1200","caption":"","width":300,"height":200}],"type":"restaurant","priceDesc":"","tel":[],"rank":1,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.757751,34.985458]}},{"id":"54ae69f85c142faec2f70ce1","zhName":"Grotto日本餐厅","enName":"","rating":0.8,"address":"日本京都 606-8417 Jodoji Nishida-cho 114, Sakyo-ku","images":[{"url":"http://images.taozilvxing.com/a8371bcad94e19dfd7306868ff101ffb?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/a8371bcad94e19dfd7306868ff101ffb?imageView2/2/w/200","full":"http://images.taozilvxing.com/a8371bcad94e19dfd7306868ff101ffb?imageView2/2/w/1200","caption":"","width":300,"height":202}],"type":"restaurant","priceDesc":"","tel":["+81 75-771-0606"],"rank":4,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.787674,35.028545]}},{"id":"54ae69f85c142faec2f70cdf","zhName":"Hyoteihonten日本餐厅","enName":"","rating":0.8,"address":"日本京都 606-8437 Nanzenjikusagawacho, Sakyoku","images":[{"url":"http://images.taozilvxing.com/81db2a5b50a4f8edd9351593dffc6168?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/81db2a5b50a4f8edd9351593dffc6168?imageView2/2/w/200","full":"http://images.taozilvxing.com/81db2a5b50a4f8edd9351593dffc6168?imageView2/2/w/1200","caption":"","width":300,"height":205}],"type":"restaurant","priceDesc":"1，162元-1，934元","tel":["+81 75-771-4116"],"rank":5,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.786514,35.011356]}},{"id":"54ae69fb5c142faec2f70e64","zhName":"Tosuiro日本餐厅","enName":"","rating":1,"address":"日本京都 604-0961 517-3 Kamiosakamachi, Sanjo Agaru Kiyacho, Nakagyo-ku","images":[{"url":"http://images.taozilvxing.com/8caff7b42b1024d734ca4027e185685b?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/8caff7b42b1024d734ca4027e185685b?imageView2/2/w/200","full":"http://images.taozilvxing.com/8caff7b42b1024d734ca4027e185685b?imageView2/2/w/1200","caption":"","width":300,"height":204}],"type":"restaurant","priceDesc":"","tel":["+81 75-251-1600"],"rank":10,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.768036,35.011635]}}]
     * detailUrl : http://h5.taozilvxing.com/planshare.php?pid=5636d2d07a2b5a0001b3e471&uid=206502
     */

    private ResultEntity result;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public ResultEntity getResult() {
        return result;
    }


    public static PlanRoute Parse(String jsonstr){
        PlanRoute planRoute =null;
        JSONObject json= null;
        try {
            json = new JSONObject(jsonstr);
            JSONObject obj=json.getJSONObject("result");
            Gson gson=new Gson();
          planRoute = gson.fromJson(json.toString(), PlanRoute.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
            return planRoute;
    }



    public static class ResultEntity {
        private String id;
        private String title;
        private String status;
        private int userId;
        private int itineraryDays;
        private long updateTime;
        private String detailUrl;
        /**
         * url : http://images.taozilvxing.com/8631f0edbf138936ae2b56427ecdbe2d?imageView2/2/w/300
         * thumb : http://images.taozilvxing.com/8631f0edbf138936ae2b56427ecdbe2d?imageView2/2/w/200
         * full : http://images.taozilvxing.com/8631f0edbf138936ae2b56427ecdbe2d?imageView2/2/w/1200
         * caption :
         * width : 300
         * height : 202
         */

        private List<ImagesEntity> images;
        /**
         * id : 546f2da8b8ce0440eddb2891
         * zhName : 京都
         * enName :
         */

        private List<LocalitiesEntity> localities;
        /**
         * dayIndex : 0
         * poi : {"id":"547bfe2eb8ce043eb2d88efb","zhName":"银阁寺","enName":"","rating":0.9199999999999999,"address":"","images":[{"url":"http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/200","full":"http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/1200","caption":"","width":300,"height":200}],"type":"vs","timeCostDesc":"1小时","priceDesc":"500日元","rank":18,"targets":["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"],"locality":{"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""},"location":{"coordinates":[135.798118,35.027062]}}
         */

        private List<ItineraryEntity> itinerary;
        /**
         * id : 54ae92885c142faec2fb8412
         * zhName : 京都手工艺品中心
         * enName :
         * rating : 0.96
         * address : 21 Entomi-chÅ, ShÅgoin SakyÅ-ku
         * images : [{"url":"http://images.taozilvxing.com/4fce71954c89baa360206d16b2fd86d8?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/4fce71954c89baa360206d16b2fd86d8?imageView2/2/w/200","full":"http://images.taozilvxing.com/4fce71954c89baa360206d16b2fd86d8?imageView2/2/w/1200","caption":"","width":300,"height":233}]
         * type : shopping
         * tel : ["075 761-5080"]
         * rank : 8
         * targets : ["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"]
         * locality : {"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""}
         * location : {"coordinates":[135.780106,35.017651]}
         */

        private List<ShoppingEntity> shopping;
        /**
         * id : 54ae69f85c142faec2f70cdc
         * zhName : 拉面小路
         * enName :
         * rating : 1.0
         * address : 京都站车站大楼10楼
         * images : [{"url":"http://images.taozilvxing.com/bb2eaea5e37bdff3d9a037ba4ebefa27?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/bb2eaea5e37bdff3d9a037ba4ebefa27?imageView2/2/w/200","full":"http://images.taozilvxing.com/bb2eaea5e37bdff3d9a037ba4ebefa27?imageView2/2/w/1200","caption":"","width":300,"height":200}]
         * type : restaurant
         * priceDesc :
         * tel : []
         * rank : 1
         * targets : ["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"]
         * locality : {"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""}
         * location : {"coordinates":[135.757751,34.985458]}
         */

        private List<RestaurantEntity> restaurant;

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setItineraryDays(int itineraryDays) {
            this.itineraryDays = itineraryDays;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public void setImages(List<ImagesEntity> images) {
            this.images = images;
        }

        public void setLocalities(List<LocalitiesEntity> localities) {
            this.localities = localities;
        }

        public void setItinerary(List<ItineraryEntity> itinerary) {
            this.itinerary = itinerary;
        }

        public void setShopping(List<ShoppingEntity> shopping) {
            this.shopping = shopping;
        }

        public void setRestaurant(List<RestaurantEntity> restaurant) {
            this.restaurant = restaurant;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getStatus() {
            return status;
        }

        public int getUserId() {
            return userId;
        }

        public int getItineraryDays() {
            return itineraryDays;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public List<ImagesEntity> getImages() {
            return images;
        }

        public List<LocalitiesEntity> getLocalities() {
            return localities;
        }

        public List<ItineraryEntity> getItinerary() {
            return itinerary;
        }

        public List<ShoppingEntity> getShopping() {
            return shopping;
        }

        public List<RestaurantEntity> getRestaurant() {
            return restaurant;
        }

        public static class ImagesEntity {
            private String url;
            private String thumb;
            private String full;
            private String caption;
            private int width;
            private int height;

            public void setUrl(String url) {
                this.url = url;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public void setFull(String full) {
                this.full = full;
            }

            public void setCaption(String caption) {
                this.caption = caption;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getUrl() {
                return url;
            }

            public String getThumb() {
                return thumb;
            }

            public String getFull() {
                return full;
            }

            public String getCaption() {
                return caption;
            }

            public int getWidth() {
                return width;
            }

            public int getHeight() {
                return height;
            }
        }

        public static class LocalitiesEntity {
            private String id;
            private String zhName;
            private String enName;

            public void setId(String id) {
                this.id = id;
            }

            public void setZhName(String zhName) {
                this.zhName = zhName;
            }

            public void setEnName(String enName) {
                this.enName = enName;
            }

            public String getId() {
                return id;
            }

            public String getZhName() {
                return zhName;
            }

            public String getEnName() {
                return enName;
            }
        }

        public static class ItineraryEntity implements Parcelable {
            private int dayIndex;
            /**
             * id : 547bfe2eb8ce043eb2d88efb
             * zhName : 银阁寺
             * enName :
             * rating : 0.9199999999999999
             * address :
             * images : [{"url":"http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/300","thumb":"http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/200","full":"http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/1200","caption":"","width":300,"height":200}]
             * type : vs
             * timeCostDesc : 1小时
             * priceDesc : 500日元
             * rank : 18
             * targets : ["5434d70d10114e684bb1b4e9","546f2da8b8ce0440eddb2891"]
             * locality : {"id":"546f2da8b8ce0440eddb2891","zhName":"京都","enName":""}
             * location : {"coordinates":[135.798118,35.027062]}
             */

            private PoiEntity poi;



            public static final Creator<ItineraryEntity> CREATOR = new Creator<ItineraryEntity>() {
                @Override
                public ItineraryEntity createFromParcel(Parcel in) {
                    ItineraryEntity itineraryEntity=new ItineraryEntity();
                    //TODO 实例化
                    itineraryEntity.dayIndex= in.readInt();
                    itineraryEntity.poi= (PoiEntity) in.readSerializable();
                    return itineraryEntity;
                }

                @Override
                public ItineraryEntity[] newArray(int size) {
                    return new ItineraryEntity[size];
                }
            };

            public void setDayIndex(int dayIndex) {
                this.dayIndex = dayIndex;
            }

            public void setPoi(PoiEntity poi) {
                this.poi = poi;
            }

            public int getDayIndex() {
                return dayIndex;
            }

            public PoiEntity getPoi() {
                return poi;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(dayIndex);
                dest.writeSerializable(poi);
            }

            public static class PoiEntity implements Serializable {
                private String id;
                private String zhName;
                private String enName;
                private double rating;
                private String address;
                private String type;
                private String timeCostDesc;
                private String priceDesc;
                private int rank;
                /**
                 * id : 546f2da8b8ce0440eddb2891
                 * zhName : 京都
                 * enName :
                 */

                private LocalityEntity locality;
                private LocationEntity location;
                /**
                 * url : http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/300
                 * thumb : http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/200
                 * full : http://images.taozilvxing.com/007c086ef5bbf5077f9166a5c52870bf?imageView2/2/w/1200
                 * caption :
                 * width : 300
                 * height : 200
                 */

                private List<ImagesEntity> images;
                private List<String> targets;

                public void setId(String id) {
                    this.id = id;
                }

                public void setZhName(String zhName) {
                    this.zhName = zhName;
                }

                public void setEnName(String enName) {
                    this.enName = enName;
                }

                public void setRating(double rating) {
                    this.rating = rating;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public void setTimeCostDesc(String timeCostDesc) {
                    this.timeCostDesc = timeCostDesc;
                }

                public void setPriceDesc(String priceDesc) {
                    this.priceDesc = priceDesc;
                }

                public void setRank(int rank) {
                    this.rank = rank;
                }

                public void setLocality(LocalityEntity locality) {
                    this.locality = locality;
                }

                public void setLocation(LocationEntity location) {
                    this.location = location;
                }

                public void setImages(List<ImagesEntity> images) {
                    this.images = images;
                }

                public void setTargets(List<String> targets) {
                    this.targets = targets;
                }

                public String getId() {
                    return id;
                }

                public String getZhName() {
                    return zhName;
                }

                public String getEnName() {
                    return enName;
                }

                public double getRating() {
                    return rating;
                }

                public String getAddress() {
                    return address;
                }

                public String getType() {
                    return type;
                }

                public String getTimeCostDesc() {
                    return timeCostDesc;
                }

                public String getPriceDesc() {
                    return priceDesc;
                }

                public int getRank() {
                    return rank;
                }

                public LocalityEntity getLocality() {
                    return locality;
                }

                public LocationEntity getLocation() {
                    return location;
                }

                public List<ImagesEntity> getImages() {
                    return images;
                }

                public List<String> getTargets() {
                    return targets;
                }

                public static class LocalityEntity implements  Serializable{
                    private String id;
                    private String zhName;
                    private String enName;

                    public void setId(String id) {
                        this.id = id;
                    }

                    public void setZhName(String zhName) {
                        this.zhName = zhName;
                    }

                    public void setEnName(String enName) {
                        this.enName = enName;
                    }

                    public String getId() {
                        return id;
                    }

                    public String getZhName() {
                        return zhName;
                    }

                    public String getEnName() {
                        return enName;
                    }
                }

                public static class LocationEntity implements  Serializable{
                    private List<Double> coordinates;

                    public void setCoordinates(List<Double> coordinates) {
                        this.coordinates = coordinates;
                    }

                    public List<Double> getCoordinates() {
                        return coordinates;
                    }
                }

                public static class ImagesEntity implements  Serializable{
                    private String url;
                    private String thumb;
                    private String full;
                    private String caption;
                    private int width;
                    private int height;

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public void setFull(String full) {
                        this.full = full;
                    }

                    public void setCaption(String caption) {
                        this.caption = caption;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public String getThumb() {
                        return thumb;
                    }

                    public String getFull() {
                        return full;
                    }

                    public String getCaption() {
                        return caption;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public int getHeight() {
                        return height;
                    }
                }
            }
        }

        public static class ShoppingEntity {
            private String id;
            private String zhName;
            private String enName;
            private double rating;
            private String address;
            private String type;
            private int rank;
            /**
             * id : 546f2da8b8ce0440eddb2891
             * zhName : 京都
             * enName :
             */

            private LocalityEntity locality;
            private LocationEntity location;
            /**
             * url : http://images.taozilvxing.com/4fce71954c89baa360206d16b2fd86d8?imageView2/2/w/300
             * thumb : http://images.taozilvxing.com/4fce71954c89baa360206d16b2fd86d8?imageView2/2/w/200
             * full : http://images.taozilvxing.com/4fce71954c89baa360206d16b2fd86d8?imageView2/2/w/1200
             * caption :
             * width : 300
             * height : 233
             */

            private List<ImagesEntity> images;
            private List<String> tel;
            private List<String> targets;

            public void setId(String id) {
                this.id = id;
            }

            public void setZhName(String zhName) {
                this.zhName = zhName;
            }

            public void setEnName(String enName) {
                this.enName = enName;
            }

            public void setRating(double rating) {
                this.rating = rating;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public void setLocality(LocalityEntity locality) {
                this.locality = locality;
            }

            public void setLocation(LocationEntity location) {
                this.location = location;
            }

            public void setImages(List<ImagesEntity> images) {
                this.images = images;
            }

            public void setTel(List<String> tel) {
                this.tel = tel;
            }

            public void setTargets(List<String> targets) {
                this.targets = targets;
            }

            public String getId() {
                return id;
            }

            public String getZhName() {
                return zhName;
            }

            public String getEnName() {
                return enName;
            }

            public double getRating() {
                return rating;
            }

            public String getAddress() {
                return address;
            }

            public String getType() {
                return type;
            }

            public int getRank() {
                return rank;
            }

            public LocalityEntity getLocality() {
                return locality;
            }

            public LocationEntity getLocation() {
                return location;
            }

            public List<ImagesEntity> getImages() {
                return images;
            }

            public List<String> getTel() {
                return tel;
            }

            public List<String> getTargets() {
                return targets;
            }

            public static class LocalityEntity {
                private String id;
                private String zhName;
                private String enName;

                public void setId(String id) {
                    this.id = id;
                }

                public void setZhName(String zhName) {
                    this.zhName = zhName;
                }

                public void setEnName(String enName) {
                    this.enName = enName;
                }

                public String getId() {
                    return id;
                }

                public String getZhName() {
                    return zhName;
                }

                public String getEnName() {
                    return enName;
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
                private String url;
                private String thumb;
                private String full;
                private String caption;
                private int width;
                private int height;

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public void setFull(String full) {
                    this.full = full;
                }

                public void setCaption(String caption) {
                    this.caption = caption;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public String getUrl() {
                    return url;
                }

                public String getThumb() {
                    return thumb;
                }

                public String getFull() {
                    return full;
                }

                public String getCaption() {
                    return caption;
                }

                public int getWidth() {
                    return width;
                }

                public int getHeight() {
                    return height;
                }
            }
        }

        public static class RestaurantEntity {
            private String id;
            private String zhName;
            private String enName;
            private double rating;
            private String address;
            private String type;
            private String priceDesc;
            private int rank;
            /**
             * id : 546f2da8b8ce0440eddb2891
             * zhName : 京都
             * enName :
             */

            private LocalityEntity locality;
            private LocationEntity location;
            /**
             * url : http://images.taozilvxing.com/bb2eaea5e37bdff3d9a037ba4ebefa27?imageView2/2/w/300
             * thumb : http://images.taozilvxing.com/bb2eaea5e37bdff3d9a037ba4ebefa27?imageView2/2/w/200
             * full : http://images.taozilvxing.com/bb2eaea5e37bdff3d9a037ba4ebefa27?imageView2/2/w/1200
             * caption :
             * width : 300
             * height : 200
             */

            private List<ImagesEntity> images;
            private List<?> tel;
            private List<String> targets;

            public void setId(String id) {
                this.id = id;
            }

            public void setZhName(String zhName) {
                this.zhName = zhName;
            }

            public void setEnName(String enName) {
                this.enName = enName;
            }

            public void setRating(double rating) {
                this.rating = rating;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setPriceDesc(String priceDesc) {
                this.priceDesc = priceDesc;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public void setLocality(LocalityEntity locality) {
                this.locality = locality;
            }

            public void setLocation(LocationEntity location) {
                this.location = location;
            }

            public void setImages(List<ImagesEntity> images) {
                this.images = images;
            }

            public void setTel(List<?> tel) {
                this.tel = tel;
            }

            public void setTargets(List<String> targets) {
                this.targets = targets;
            }

            public String getId() {
                return id;
            }

            public String getZhName() {
                return zhName;
            }

            public String getEnName() {
                return enName;
            }

            public double getRating() {
                return rating;
            }

            public String getAddress() {
                return address;
            }

            public String getType() {
                return type;
            }

            public String getPriceDesc() {
                return priceDesc;
            }

            public int getRank() {
                return rank;
            }

            public LocalityEntity getLocality() {
                return locality;
            }

            public LocationEntity getLocation() {
                return location;
            }

            public List<ImagesEntity> getImages() {
                return images;
            }

            public List<?> getTel() {
                return tel;
            }

            public List<String> getTargets() {
                return targets;
            }

            public static class LocalityEntity {
                private String id;
                private String zhName;
                private String enName;

                public void setId(String id) {
                    this.id = id;
                }

                public void setZhName(String zhName) {
                    this.zhName = zhName;
                }

                public void setEnName(String enName) {
                    this.enName = enName;
                }

                public String getId() {
                    return id;
                }

                public String getZhName() {
                    return zhName;
                }

                public String getEnName() {
                    return enName;
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
                private String url;
                private String thumb;
                private String full;
                private String caption;
                private int width;
                private int height;

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public void setFull(String full) {
                    this.full = full;
                }

                public void setCaption(String caption) {
                    this.caption = caption;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public String getUrl() {
                    return url;
                }

                public String getThumb() {
                    return thumb;
                }

                public String getFull() {
                    return full;
                }

                public String getCaption() {
                    return caption;
                }

                public int getWidth() {
                    return width;
                }

                public int getHeight() {
                    return height;
                }
            }
        }
    }
}
