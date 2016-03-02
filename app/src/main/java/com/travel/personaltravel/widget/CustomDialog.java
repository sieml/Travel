package com.travel.personaltravel.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.travel.personaltravel.R;


/**
 * Created by Android Studio
 * Author: SieLee
 * Email: siesielee@gmail.com
 * Date: 2015/12/26 [18:16]
 *
 * @Description: TODO 自定义对话框
 */
public class CustomDialog extends Dialog implements
        View.OnClickListener {

    /**
     * 布局文件
     **/
    int layoutRes;
    /**
     * 上下文对象
     **/
    Context context;
    /**
     * 确定按钮
     **/
    private Button confirmBtn;
    /**
     * 取消按钮
     **/
    private Button cancelBtn;
    /**
     * 点击次数
     **/
    private int check_count = 0;
    private String txt;
    /**
     * 回调
     **/
    private positiveCallback pCallback;
    private negativeCallback nCallback;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * 自定义布局的构造方法
     *
     * @param context
     * @param resLayout
     */
    public CustomDialog(Context context, int resLayout) {
        super(context);
        this.context = context;
        this.layoutRes = resLayout;
    }

    /**
     * 自定义主题及布局的构造方法
     *
     * @param context
     * @param theme
     * @param resLayout
     */
    public CustomDialog(Context context, int theme, int resLayout) {
        super(context, theme);
        this.context = context;
        this.layoutRes = resLayout;
    }

    public CustomDialog(Context context, int theme, int resLayout, String txt) {
        super(context, theme);
        this.context = context;
        this.layoutRes = resLayout;
        this.txt = txt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 指定布局
        this.setContentView(layoutRes);
        // 根据id在布局中找到控件对象
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        // 设置按钮的文本颜色
        //confirmBtn.setTextColor(0xff1E90FF);
        //cancelBtn.setTextColor(0xff1E90FF);

        // 为按钮绑定点击事件监听器
        confirmBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.confirm_btn:
                // 点击了确认按钮
                //回调
                if (this.pCallback != null) {
                    this.pCallback.clickConfirm();
                }
                break;

            case R.id.cancel_btn:
                // 点击了取消按钮
                //回调
                if (this.nCallback != null) {
                    this.nCallback.clickCancel();
                }
                break;
            default:
                break;
        }
    }

    public interface positiveCallback {
        void clickConfirm();
    }

    public interface negativeCallback {
        void clickCancel();
    }

    public void setpCallback(final positiveCallback pCallback) {
        this.pCallback = pCallback;
    }

    public void setnCallback(final negativeCallback nCallback) {
        this.nCallback = nCallback;
    }
}