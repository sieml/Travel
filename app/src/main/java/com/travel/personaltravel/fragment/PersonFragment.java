package com.travel.personaltravel.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.travel.personaltravel.adapter.UserRecyvleAdapter;
import com.travel.personaltravel.application.AiLYApplication;
import com.travel.personaltravel.R;
import com.travel.personaltravel.activity.PlanDetailActivity;
import com.travel.personaltravel.activity.SettingActivity;
import com.travel.personaltravel.adapter.CallBack;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.model.Plan;
import com.travel.personaltravel.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的 主页面
 */
public class PersonFragment extends Fragment implements CallBack, View.OnClickListener {

    private RecyclerView recyclerView;

    private LinearLayout linearLayout;

    private List<Plan> data;
    private View header;
    private View inflate;
    private RecyclerView.LayoutManager layoutManager;
    private String userId;
    private int page;

    private int pos;
    private HttpUtils http;
    private UserRecyvleAdapter adapter;
    private TextView setbtn;


    public PersonFragment() {
        //从主页获取userid
        userId = "206502";

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        page = 0;

        http = new HttpUtils();

// 垂直布局
        layoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        recyclerView.setLayoutManager(layoutManager);


        //TODO 添加头部

        //设置头部内容
        RecyclerViewHeader header = RecyclerViewHeader.fromXml(getContext(), R.layout.fragment_person_header);

        final ImageView imgview = (ImageView) header.findViewById(R.id.person_header_img);
        final TextView nametxt = (TextView) header.findViewById(R.id.person_header_name);
        final TextView ranktxt = (TextView) header.findViewById(R.id.person_header_rank);
        final TextView idtxt = (TextView) header.findViewById(R.id.person_header_id);
        String userurl = SieConstant.baseUrl + String.format(SieConstant.userurl, userId);

        //获取json数据并显示
        http.send(HttpRequest.HttpMethod.GET, userurl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                if (result != null) {
                    User user = User.parseJson(result);
                    if (user != null) {
                        nametxt.setText(user.getNickName());
                        ranktxt.setText("LV" + user.getLevel());
                        idtxt.setText(user.getUserId());
                        AiLYApplication.bitmapUtils.display(imgview, user.getAvatarSmall());
                    }

                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
            }
        });

        //添加头部
        header.attachTo(recyclerView);

        //初始化data
        data = new ArrayList<>();

        //设置adapter
        adapter = new UserRecyvleAdapter(data, getContext(), this);
        recyclerView.setAdapter(adapter);


        //获取计划内容数据
        String url = SieConstant.baseUrl + String.format(SieConstant.homeUrl, userId, page);
        http.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                List<Plan> lists = Plan.getListPlan(result);
                data.addAll(lists);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }

        });

        //监听滚动事件
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //获取可视的第一个view
                View topView = layoutManager.getChildAt(0);
                if (topView.getTop() < linearLayout.getMeasuredHeight()) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });

        //TODO 点击设置
        setbtn.setOnClickListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 获取视图
        View ret = inflater.inflate(R.layout.fragment_person, container, false);

        //获取悬浮窗
        linearLayout = (LinearLayout) ret.findViewById(R.id.fragment_person_linear);

        //获取recycleview
        recyclerView = (RecyclerView) ret.findViewById(R.id.fragment_person_list);
        //获取设置button
        setbtn = (TextView) ret.findViewById(R.id.fragment_person_setting);

        return ret;
    }


    @Override
    public void getPos(int pos) {
        this.pos = pos;
    }

    @Override
    public void getType(int type) {
        if (type == UserRecyvleAdapter.DELETE) {
            String url = SieConstant.baseUrl + String.format(SieConstant.delPlan, data.get(pos).getId());

            //删除旅遊計劃
            http.send(HttpRequest.HttpMethod.DELETE, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String result = responseInfo.result;

                    data.remove(pos);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(HttpException error, String msg) {
                    Toast.makeText(getContext(), "刪除失敗", Toast.LENGTH_LONG).show();
                }
            });

        } else if (type == UserRecyvleAdapter.INTENT) {
            //打開旅行計劃詳細窗口
            Intent intent = new Intent(getContext(), PlanDetailActivity.class);
            intent.putExtra("userid", userId);
            intent.putExtra("name", data.get(pos).getTitle());
            intent.putExtra("id", data.get(pos).getId());
            startActivity(intent);
        }
    }

    //点击设置
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), SettingActivity.class);
        startActivity(intent);
    }
}
