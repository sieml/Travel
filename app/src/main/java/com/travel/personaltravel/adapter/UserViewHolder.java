package com.travel.personaltravel.adapter;

/**
 * Created by Android Studio
 * Author: SieLee
 * Email: siesielee@gmail.com
 * Date: 2016/2/24 [0:05]
 *
 * @Description: TODO
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.personaltravel.R;

/**
 * 创建自己的 UserViewHolder，必须要继承 RecyclerView.UserViewHolder
 */
class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    ImageView img;
    TextView title;
    TextView time;
    TextView createTime;
    TextView city;


    CallBack callBack ;

    public UserViewHolder(View itemView, CallBack callBack) {
        super(itemView);
        this.callBack=callBack;
        // 通常 UserViewHolder 的构造，就使用获取控件视图的；
        img= (ImageView) itemView.findViewById(R.id.person_item_img);
        title = (TextView) itemView.findViewById(R.id.person_item_name);
        time = (TextView) itemView.findViewById(R.id.person_item_staytime);
        createTime = (TextView) itemView.findViewById(R.id.person_item_createtime);
        city= (TextView) itemView.findViewById(R.id.person_item_cityname);

        // 后续处理点击事件的操作
        itemView.setOnClickListener(this);
        //长按点击事件的事件监听
        itemView.setOnLongClickListener(this);

    }
    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        int type;
        if (v instanceof Button){
            type=UserRecyvleAdapter.DELETE;
        }else{
            type=UserRecyvleAdapter.INTENT;
        }
        callBack.getPos(position);
        callBack.getType(type);
    }


    @Override
    public boolean onLongClick(View v) {
        int position = getAdapterPosition();
        final Button btn= (Button) v.findViewById(R.id.person_item_delete);
        final Button canclebtn= (Button) v.findViewById(R.id.person_item_cancel);
        btn.setVisibility(View.VISIBLE);
        canclebtn.setVisibility(View.VISIBLE);

        btn.setOnClickListener(this);

        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setVisibility(View.GONE);
                canclebtn.setVisibility(View.GONE);
            }
        });


        return true;
    }
}
