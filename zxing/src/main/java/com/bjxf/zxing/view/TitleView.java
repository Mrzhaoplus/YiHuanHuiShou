package com.bjxf.zxing.view;




import com.bjxf.zxing.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleView extends LinearLayout {

	private TextView title;
	private TextView tvRight;
	private ImageView imgLeft, imgRight;
	private LinearLayout ll_title_view;

	// 获取图片对象
	public ImageView getLeftImg() {
		return imgLeft;
	}

	public ImageView getRightImg() {
		return imgRight;
	}

	public TitleView(Context context) {
		super(context);
		init(null);
	}

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public TitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		View v = LayoutInflater.from(getContext()).inflate(
				R.layout.layout_title, this);
		title = (TextView) v.findViewById(R.id.title_title1);
		tvRight = (TextView) v.findViewById(R.id.title_tv_right);
		imgLeft = (ImageView) v.findViewById(R.id.title_img_left);
		imgRight = (ImageView) v.findViewById(R.id.title_img_right);
		ll_title_view = (LinearLayout) v.findViewById(R.id.ll_title_view);
		if (attrs == null) {
			return;
		}
		TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.TitleView);

		int N = a.getIndexCount();
		for (int i = 0; i < N; i++) {
			int index = a.getIndex(i);
			if (index == R.styleable.TitleView_tv_img_left_visibility) {
				setVisibility(imgLeft, a.getInt(index, 0));

			} else if (index == R.styleable.TitleView_tv_img_right_visibility) {
				setVisibility(imgRight, a.getInt(index, 2));

			} else if (index == R.styleable.TitleView_tv_title) {
				title.setText(a.getString(index));

			} else if (index == R.styleable.TitleView_tv_right) {
				tvRight.setText(a.getString(index));

			} else if (index == R.styleable.TitleView_tv_right_tv_visibility) {
				setVisibility(tvRight, a.getInt(index, 2));

			} else {
			}
		}
	}

	private void setVisibility(View v, int visable) {
		switch (visable) {
		case 0:
			v.setVisibility(View.VISIBLE);
			break;
		case 1:
			v.setVisibility(View.INVISIBLE);
			break;
		case 2:
			v.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

	public void setTitle(String text) {
		title.setText(text);
	}

	public void setRightTopText(String text) {
		tvRight.setText(text);
	}

	public void setBackClickListener(View.OnClickListener listener) {
		imgLeft.setOnClickListener(listener);
	}

	public void setRightClickListener(View.OnClickListener listener) {
		imgRight.setOnClickListener(listener);
		tvRight.setOnClickListener(listener);
	}

	public void setLeftImageResource(int imgId) {
		imgLeft.setImageResource(imgId);
	}

	public void setRightImageResource(int imgId) {
		imgRight.setImageResource(imgId);
	}

	
	public void setTitleTextColor(int resID){
		title.setTextColor(getResources().getColor(resID));
	}
	
	
	// 设置背景颜色
	public void setBackGround(int color) {
		ll_title_view.setBackgroundResource(color);
	}

	public void setRightTopTextVisibility(int visibility) {
		tvRight.setVisibility(visibility);
	}

}
