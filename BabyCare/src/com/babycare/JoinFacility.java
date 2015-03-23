package com.babycare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.babycare.adapter.AdapterParse;
import com.babycare.base.CustomTitleActivity;

@SuppressLint("NewApi")
public class JoinFacility extends CustomTitleActivity {

	private ListView mListView;
	private List<Map<String, Object>> dataList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.join_facility);
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void initViews() {
		setTitle("连接设备");
		setTitleBarRightBtnText(null);
		
		mListView = (ListView) findViewById(R.id.join_facility_list);
	}

	@Override
	protected void initDatas() {
		dataList = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 6; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "机器" + (i+1));
			dataList.add(map);
		}
		
		mListView.setAdapter(AdapterParse.getJoinFacilityAdapter(this, dataList));
	
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), String.valueOf(item.get("name")), 0).show();
			}
		});
	}

	@Override
	protected void installListeners() {

	}

	@Override
	protected void uninstallListeners() {

	}
	@SuppressLint("NewApi")
	public void  refresh(View view){
		ObjectAnimator oa = ObjectAnimator.ofFloat(view, "rotation", 0, 1080);
		oa.setDuration(3000);
		oa.start();
	}
}
