package com.travel.personaltravel.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travel.personaltravel.R;
import com.travel.personaltravel.application.AiLYApplication;
import com.travel.personaltravel.model.Plan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 继承 RecyclerView.Adapter，用于显示数据；
 * 需要定义并且使用 UserViewHolder，必须要使用的。
 */
public class UserRecyvleAdapter extends RecyclerView.Adapter<UserViewHolder> {
    public static final int DELETE = 1;

    public static final int INTENT = 2;


    protected List<Plan> lists;
    private Context context;
    private CallBack callBack;

    public UserRecyvleAdapter(List<Plan> lists, Context context, CallBack callBack) {
        this.lists = lists;
        this.context = context;
        this.callBack = callBack;
    }

    /**
     * 根据 ViewType来创建、加载 特定的布局，然后，将创建、加载好的布局
     * 交给ViewHolder，创建新的ViewHolder对象，并且返回；
     * 因为RecyclerView，在ViewHolder 为null的情况，才会调用这个方法；
     * 如果ViewHolder，以及存在，那么不会进入这个方法；方法自身只负责
     * holder 的创建，不处理数据；
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserViewHolder userViewHolder = null;

        // 这个方法不需要检查 是否有复用，因为只要进入这个方法，必然没有复用
        // 因为 RecyclerView 通过Holder来检查复用
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_person_item, parent, false);
        userViewHolder = new UserViewHolder(view, callBack);

        return userViewHolder;
    }

    /**
     * 用于显示指定位置的数据的内容，通过 UserViewHolder 来进行相应控件的更新
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Plan plan = lists.get(position);
        holder.title.setText(plan.getTitle());

        Date date = new Date(Long.parseLong(plan.getUpdateTime()));
        SimpleDateFormat myFmt = new SimpleDateFormat("MM月dd日 HH:mm");

        holder.createTime.setText(myFmt.format(date));
        holder.time.setText(plan.getDayCnt() + "天");
        holder.city.setText(plan.getSummary());
        String imgurl = plan.getImages().get(0).getThumb();
        AiLYApplication.bitmapUtils.display(holder.img, imgurl);

    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (lists != null) {
            return lists.size();
        }
        return ret;
    }
}

