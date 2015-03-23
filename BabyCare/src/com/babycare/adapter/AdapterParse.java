package com.babycare.adapter;

import java.util.List;
import java.util.Map;

import com.babycare.R;
import com.babycare.adapter.holder.CommViewHolder;

import android.R.string;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class AdapterParse {
	
	private static LayoutParams para = null; 
	
	public static CommAdapter<String> getJoinFacilityAdapter(Activity activity,List<Map<String, Object>> data){
		
		return new CommAdapter<String>(activity, data,R.layout.join_facility_item) {

			@Override
			public void convert(CommViewHolder holder, Map<String, Object> item) {
				holder.setText(R.id.join_facility_item_text, String.valueOf(item.get("name")));
			}
		};
	}
	
	public static CommAdapter<String> getStoryMachineModeAdapter(Activity activity,List<Map<String, Object>> data){
		
		return new CommAdapter<String>(activity, data,R.layout.story_machine_mode_item) {

			@Override
			public void convert(CommViewHolder holder, Map<String, Object> item) {
				holder.setText(R.id.story_machine_mode_item_text, String.valueOf(item.get("name")));
				
				String isChecked = String.valueOf(item.get("isChecked"));
				
				ImageView imageView = holder.getView(R.id.story_machine_mode_item_img);
				if (isChecked.equals("1")) {
					imageView.setVisibility(View.VISIBLE);
				}else {
					imageView.setVisibility(View.INVISIBLE);
				}
			}
		};
	}
	
}
