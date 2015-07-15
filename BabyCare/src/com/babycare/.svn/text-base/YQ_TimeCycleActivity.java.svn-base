package com.babycare;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.babycare.base.CustomTitleActivity;

/** 

 * @Description: TODO() 
 * @author 易勤
 * @date 2015-3-19 上午11:15:16 
 * @version V1.0 
 */
public class YQ_TimeCycleActivity extends CustomTitleActivity {
	private View view1,view2,view3,view4,view5,selectView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_timecycle);
		super.onCreate(savedInstanceState);
		setTitle("上传时间间隔");
		setTitleBarRightBtnText("");
		setTitleBarLeftBtnText("返回");
	}

	@Override
	protected void initViews() {
		view1 = findViewById(R.id.arrow1);
		view2 = findViewById(R.id.arrow2);
		view3 = findViewById(R.id.arrow3);
		view4 = findViewById(R.id.arrow4);
		view5 = findViewById(R.id.arrow5);
	}



	@Override
	protected void initDatas() {
		// TODO Auto-generated method stub
		selectView = view1;
		selectView.setVisibility(View.VISIBLE);
	}

	@Override
	protected void installListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void uninstallListeners() {
		// TODO Auto-generated method stub

	}

	public void onBtnClick(View v) {
		selectView.setVisibility(View.GONE);
		switch (v.getId()) {
		case R.id.layout0:
			view1.setVisibility(View.VISIBLE);
			selectView =view1;
			break;
		case R.id.layout3:
			view2.setVisibility(View.VISIBLE);
			selectView =view2;
			break;
		case R.id.layout5:
			view3.setVisibility(View.VISIBLE);
			selectView =view3;
			break;
		case R.id.layout10:
			view4.setVisibility(View.VISIBLE);
			selectView =view4;
			break;
		case R.id.layout20:
			view5.setVisibility(View.VISIBLE);
			selectView =view5;
			break;

	
		default:
			break;
		}
	
	}
	
	

}