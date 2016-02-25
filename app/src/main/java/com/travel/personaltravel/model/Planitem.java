package com.travel.personaltravel.model;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/5.
 */
public class Planitem {
    private String name;
    private ArrayList<PlanRoute.ResultEntity.ItineraryEntity> routes;
    private int  dayindex;

    //获得基本路线情况
  public static  ArrayList<Planitem>  getRoutes(PlanRoute.ResultEntity resultEntity){
      ArrayList<Planitem> lists=new ArrayList<>();

      ArrayList<PlanRoute.ResultEntity.ItineraryEntity> itinerary = (ArrayList<PlanRoute.ResultEntity.ItineraryEntity>) resultEntity.getItinerary();
      int day=itinerary.get(0).getDayIndex();
      String name;

      for (int i = 0; i < itinerary.size(); i++) {
          Planitem planitem=new Planitem();
          ArrayList<PlanRoute.ResultEntity.ItineraryEntity> routes=new ArrayList<>();
          while ( itinerary.get(i).getDayIndex()==day){
                 routes.add( itinerary.get(i));
                    i++;
              if (i==itinerary.size()){
                  break;
              }
          }
          planitem.routes=routes;
          planitem.dayindex=day;
          if (i<=itinerary.size() && i>0) {
             PlanRoute.ResultEntity.ItineraryEntity itineraryEntity = itinerary.get(i - 1);
              PlanRoute.ResultEntity.ItineraryEntity.PoiEntity poi = itineraryEntity.getPoi();
              PlanRoute.ResultEntity.ItineraryEntity.PoiEntity.LocalityEntity locality = poi.getLocality();
              if (locality!= null) {
                  planitem.name= locality.getZhName();
              }else{
                  int j=i;
                  while(j>0 && j<=itinerary.size()){
                      j--;
                      if (itinerary.get(j).getPoi().getLocality() != null) {
                          planitem.name= itinerary.get(j).getPoi().getLocality().getZhName();
                          break;
                      }
                  }
              }
              i--;
          }
              day++;
          lists.add(planitem);
      }

      return lists;
  }

    public ArrayList<PlanRoute.ResultEntity.ItineraryEntity> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<PlanRoute.ResultEntity.ItineraryEntity> routes) {
        this.routes = routes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDayindex() {
        return dayindex;
    }

    public void setDayindex(int dayindex) {
        this.dayindex = dayindex;
    }
}
