package com.travel.personaltravel.adapter.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.travel.personaltravel.R;
import com.travel.personaltravel.model.SearchResult;

import java.util.List;

/**
 * Created by Android Studio
 * Author: SieLee
 * Email: siesielee@gmail.com
 * Date: 2015/11/4 [9:27]
 */
public class SearchResultAdapter extends BaseAdapter {

    private Context context;
    private List<SearchResult> list;
    private static String[] typeName = {"景点", "美食", "购物"};

    public SearchResultAdapter(Context context, List<SearchResult> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        if ((position) % 6 == 0) {
            return false;
        }
        return super.isEnabled(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;

        LayoutInflater inflater = LayoutInflater.from(context);
        if ((position) % 6 == 0) {

            ret = inflater.inflate(
                    R.layout.search_result_title_item
                    , parent
                    , false
            );
            TextView txtName = (TextView) ret.findViewById(R.id.search_result_type_title_item);
            int loca = 0;
            if (position >= 0 && position <= 5) loca = 0;
            else if (position >= 6 && position <= 11) loca = 1;
            else loca = 2;
            txtName.setText(typeName[loca]);
            ;
        } else {
            ret = inflater.inflate(
                    R.layout.search_result_content_item
                    , parent
                    , false
            );
            ImageView imgIcon = (ImageView) ret.findViewById(R.id.search_result_icon_item);
            TextView txtCname = (TextView) ret.findViewById(R.id.search_result_name_item);
            TextView txtAddress = (TextView) ret.findViewById(R.id.search_result_address_item);
            setStar(ret,position);
            txtCname.setText(list.get(position).getZhName());
            txtAddress.setText(list.get(position).getAddress());

            if (list.get(position).getImages() != null){
                if (list.get(position).getImages().size()>0) {
                    BitmapUtils bitmapUtils = new BitmapUtils(context);
                    bitmapUtils.display(imgIcon, list.get(
                            position).getImages().get(0).getUrl());
                }
            }

        }
        return ret;
    }
    private final int[] resId= {R.id.ic_rating_start_1,R.id.ic_rating_start_2,R.id.ic_rating_start_3,R.id.ic_rating_start_4,R.id.ic_rating_start_5};
    private void setStar(View ret, int position) {
        ImageView icon = null;

        double rating=list.get(position).getRating();
        int sum= (int) (rating/0.2);
        for (int i = 0; i < sum; i++) {
           icon = (ImageView) ret.findViewById(resId[i]);
            icon.setImageResource(R.mipmap.ic_rating_start_highlight);
        }
    }

}
