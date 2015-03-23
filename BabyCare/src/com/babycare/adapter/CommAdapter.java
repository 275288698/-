package com.babycare.adapter;

import java.util.List;
import java.util.Map;

import com.babycare.adapter.holder.CommViewHolder;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public abstract class CommAdapter<T> extends BaseAdapter {

	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<Map<String, Object>> mData = null;
	protected final int mItemLayoutId;

	/**
	 * @param mContext 上下文对象
	 * @param mData 数据源
	 * @param itemLayoutId listview对应的Item
	 */
	public CommAdapter(Context mContext, List<Map<String, Object>> mData, int itemLayoutId) {
		super();
		mInflater = LayoutInflater.from(mContext);
		this.mContext = mContext;
		this.mData = mData;
		this.mItemLayoutId = itemLayoutId;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Map<String, Object> getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		CommViewHolder viewHolder = CommViewHolder.get(mContext, convertView, parent, mItemLayoutId,position);
		
		convert(viewHolder, getItem(position));
		
		return viewHolder.getConvertView();
	}

	public abstract void convert(CommViewHolder holder, Map<String, Object> item);

	

}
