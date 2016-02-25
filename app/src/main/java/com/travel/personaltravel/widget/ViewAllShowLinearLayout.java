package com.travel.personaltravel.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * @author sunday 2013-12-5 邮箱：zhengchao1937@163.com QQ:804935743
 */
public class ViewAllShowLinearLayout extends LinearLayout {
	private View replacerView;
	private View beReplacedView; // 顶部的View
	private ViewSwitchListener viewSwitchListener; // 对外钩子接口
	private ScrollView mScrollView;
	private boolean isFlag = true; // 辅助判断变量

	/**
	 * 
	 * @param beReplacedView
	 *            被替换的View 最开始显示在界面上的没有被替换的View
	 * @param replacerView
	 *            最开是没有显示在屏幕上，后来显示在屏幕上的View
	 * @param scrollview
	 *            整个ScrollView视图，是View的父布局
	 * 
	 */
	public void initData(View beReplacedView, View replacerView,
			ScrollView scrollview) {
		this.beReplacedView = beReplacedView;
		this.mScrollView = scrollview;
		this.replacerView = replacerView;
	}

	/**
	 * 
	 * @param beReplacedView
	 *            最开是没有显示在屏幕上，后来显示在屏幕上的View
	 * @param scrollview
	 *            被替换的View 最开始显示在界面上的没有被替换的View
	 * @param viewSwitchListener
	 *            当view隐藏和显示时会调用的接口
	 */
	public void initData(View beReplacedView, ScrollView scrollview,
			ViewSwitchListener viewSwitchListener) {
		this.beReplacedView = beReplacedView;
		this.mScrollView = scrollview;
		this.viewSwitchListener = viewSwitchListener;
	}

	public ViewAllShowLinearLayout(Context context) {
		super(context);
		init();
	}

	public ViewAllShowLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		setOrientation(LinearLayout.VERTICAL);
	}

	@Override
	public void computeScroll() {

		if (beReplacedView != null && mScrollView != null
				&& viewSwitchListener != null) {
			int y = mScrollView.getScrollY();
			int top = beReplacedView.getTop();
			if (isFlag) {
				if (y >= top) {
					viewSwitchListener.onViewShow();
					isFlag = false;
				}
			}
			if (!isFlag) {

				if (y < top) {
					viewSwitchListener.onViewGone();
					isFlag = true;
				}
			}
		} else if (beReplacedView != null && mScrollView != null
				&& replacerView != null) {
			int y = mScrollView.getScrollY();
			int top = beReplacedView.getTop();
			if (isFlag) {
				if (y >= top) {
					replacerView.setVisibility(View.VISIBLE);
                    replacerView.setBackgroundColor(Color.parseColor("#FFD4D4D4"));
					isFlag = false;
				}
			}
			if (!isFlag) {

                if (y < top) {
                    replacerView.setVisibility(View.GONE);
                    beReplacedView.setBackgroundColor(Color.parseColor("#00000000"));
                    isFlag = true;
                }
            }
		}
	}

	public interface ViewSwitchListener {
		public void onViewShow();

		public void onViewGone();
	}
}
