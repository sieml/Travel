package com.travel.personaltravel.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import static java.lang.Integer.*;

/**
 * Created by on
 * Author: Zern
 * DATE:2015/11/4
 * Time: 15:32
 * email:AndroidZern@163.com
 */
public class MyListView extends ListView{
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                MAX_VALUE >>2 ,
                MeasureSpec.AT_MOST
        ) ;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



}
