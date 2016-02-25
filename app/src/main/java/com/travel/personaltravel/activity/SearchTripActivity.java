package com.travel.personaltravel.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.travel.personaltravel.BuildConfig;
import com.travel.personaltravel.R;
import com.travel.personaltravel.adapter.MultiItemCommonAdapter;
import com.travel.personaltravel.adapter.MultiItemTypeSupport;
import com.travel.personaltravel.adapter.ViewHolder;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.model.morecity.Destination;
import com.travel.personaltravel.model.morecity.Image;
import com.travel.personaltravel.model.morecity.MoreCityForeign;
import com.travel.personaltravel.util.ParseUtils;

import java.util.LinkedList;
import java.util.List;

public class SearchTripActivity extends AppCompatActivity {
    @ViewInject(value = R.id.activity_search_trip_input)
    private EditText editTextKeyWord;
    @ViewInject(value = R.id.activity_search_trip_listView)
    private ListView listView;
    private LinkedList<Destination> destinations;
    private MultiItemCommonAdapter<Destination> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_trip);
        ViewUtils.inject(this);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, SieConstant.pathForeignAll, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                List<MoreCityForeign> moreCityForeign = ParseUtils.getMoreCityForeign(result);
                LinkedList<Destination> listCity = new LinkedList<Destination>();
                LinkedList<Image> listImage = new LinkedList<Image>();
                for (int i = 0; i < moreCityForeign.size(); i++) {
                    List<Destination> destinations = moreCityForeign.get(i).getDestinations();
                    listCity.addAll(destinations);
                }
                for (int j = 0; j < listCity.size(); j++) {
                    Image image = listCity.get(j).getImages().get(0);
                    image.setId(listCity.get(j).getId());
                    listImage.add(image);
                }
                DbUtils dbUtils = DbUtils.create(SearchTripActivity.this);
                try {
                    dbUtils.saveAll(listCity);
                    dbUtils = DbUtils.create(SearchTripActivity.this);
                    dbUtils.saveAll(listImage);
                } catch (DbException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (BuildConfig.DEBUG) Log.d("MoreCityForeignFragment", msg);
            }
        });



        final DbUtils db = DbUtils.create(this);
        destinations=new LinkedList<>();
        adapter=new MultiItemCommonAdapter<Destination>(this, destinations, new MultiItemTypeSupport<Destination>() {
            @Override
            public int getLayoutId(int position, Destination destination) {
                return R.layout.item_fragment_trip_listview;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public int getItemViewType(int position, Destination destination) {
                return 0;
            }
        }) {
            @Override
            public void convert(ViewHolder holder, Destination destination) {
                holder.setText(R.id.item_fragment_trip_listView_cityName, destination.getZhName());
                holder.setText(R.id.item_fragment_trip_listView_cityNamePinYin, destination.getEnName());
                DbUtils dbUtils = DbUtils.create(SearchTripActivity.this);
                try {
                    Image image = dbUtils.findById(Image.class, destination.getId());
                    String url = image.getUrl();
                    BitmapUtils bitmapUtils = new BitmapUtils(SearchTripActivity.this);
                    bitmapUtils.display(holder.getView(R.id.item_fragment_trip_listView_imageView), url);
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        };
        listView.setAdapter(adapter);

    }

    @OnClick(value = R.id.activity_search_trip_back)
    public void onClickBack(View view) {
        finish();
    }

    @OnClick(value = R.id.activity_search_trip_search)
    public void onClickSearch(View view) {
        List<Destination> all = null;
        String keyWord = editTextKeyWord.getText().toString();
        DbUtils dbUtils = DbUtils.create(this);
        Selector selector = Selector.from(Destination.class)
                .where("zhName", " like ", "'%" + keyWord + "%'");
        try {
           all = dbUtils.findAll(selector);
            destinations.clear();
            destinations.addAll( all);
            adapter.notifyDataSetChanged();

        } catch (DbException e) {
            e.printStackTrace();
        }

    }
}


