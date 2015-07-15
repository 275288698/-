package com.babycare.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.babycare.R;
import com.babycare.app.MyAplication;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.R.string;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private int[] data = {R.drawable.babysong,R.drawable.babystory,R.drawable.babyteach,R.drawable.poetry,R.drawable.why};
	private String[] titles ={"童话故事","儿歌","早教","诗歌","十万个为什么"};
	private int screenHeigh;
	private int screenWidth;

	public ImageAdapter(Context context,
			 int screenHeigh,
			int screenWidth) {
		mContext = context;
		this.screenHeigh = screenHeigh;
		this.screenWidth = screenWidth;
	}

	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.cinema_image_adapter_view, null);
		final ImageView main = (ImageView) view.findViewById(R.id.main_image);
//		String preferential = data.get(position).get("preferential");
//		if(preferential!=null&&preferential.equals("1")){
		TextView left_right_image = (TextView) view.findViewById(R.id.left_right_image);
		
		
		
//		left_right_image.setVisibility(View.VISIBLE);
//		}
		main.setImageResource(data[position]);
		left_right_image.setText(titles[position]);
//		MyAplication.imageLoader.loadImage(data.get(position).get("img"),
//				MyAplication.options, new ImageLoadingListener() {
//
//					@Override
//					public void onLoadingStarted(String imageUri, View view) {
//
//					}
//
//					@Override
//					public void onLoadingFailed(String imageUri, View view,
//							FailReason failReason) {
//
//					}
//
//					@Override
//					public void onLoadingComplete(String imageUri, View view,
//							Bitmap loadedImage) {
//						main.setImageBitmap(loadedImage);
//					}
//					@Override
//					public void onLoadingCancelled(String imageUri, View view) {
//
//					}
//				});

		view.setLayoutParams(new Gallery.LayoutParams(
				screenWidth/3, LayoutParams.MATCH_PARENT));
//		view.setLayoutParams(new Gallery.LayoutParams(
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
		view.setBackgroundColor(Color.alpha(1));

		return view;
	}

}
