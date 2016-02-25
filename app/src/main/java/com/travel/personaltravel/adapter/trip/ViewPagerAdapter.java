package com.travel.personaltravel.adapter.trip;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.lidroid.xutils.BitmapUtils;
import com.travel.personaltravel.widget.MatrixImageView;

import java.util.List;

/**
 * Created by coder
 * time 2015/11/4.
 */
public class ViewPagerAdapter extends PagerAdapter{
    List<String> images;
    public ViewPagerAdapter(List<String> images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        MatrixImageView imageView=new MatrixImageView(container.getContext());
        BitmapUtils bitmapUtils = new BitmapUtils(container.getContext());
        bitmapUtils.display(imageView,images.get(position));
        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
