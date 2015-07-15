package com.babycare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.babycare.adapter.AdapterParse;
import com.babycare.adapter.CommAdapter;
import com.babycare.base.CustomTitleActivity;

public class StoryMachineMode extends CustomTitleActivity {

	private ListView mListView;
	private List<Map<String, Object>> dataList;
	
	private CommAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.story_machine_mode);
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void initViews() {
		setTitle("模式");
		setTitleBarRightBtnText("");
		mListView = (ListView) findViewById(R.id.story_machine_mode_list);
	}

	@Override
	protected void initDatas() {
		dataList = new ArrayList<Map<String,Object>>();
		String[] modes = new String[]{"情景模式","户外模式","安静模式","游玩模式"};
		
		for (int i = 0; i < modes.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", modes[i]);
			map.put("isChecked", "0");
			dataList.add(map);
		}
		dataList.get(0).put("isChecked", "1");
		mListView.setAdapter(adapter = AdapterParse.getStoryMachineModeAdapter(this, dataList));
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				int len = dataList.size();
				for (int i = 0; i <len; i++) {
					dataList.get(i).put("isChecked", "0");
				}
				dataList.get(position).put("isChecked", "1");
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	protected void installListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void uninstallListeners() {
		// TODO Auto-generated method stub

	}

}
