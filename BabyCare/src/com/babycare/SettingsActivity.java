package com.babycare;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.babycare.base.BaseActivity;

public class SettingsActivity extends BaseActivity implements OnClickListener{
	TextView title;
	TextView right_text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
	}
	

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
//		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_text:
			
			break;
		default:
			break;
		}
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		title = (TextView)findViewById(R.id.title);
		right_text = (TextView)findViewById(R.id.right_text);
		right_text.setVisibility(View.VISIBLE);
	
	}

	@Override
	protected void initDatas() {
		// TODO Auto-generated method stub
	
		title.setText("设置");
		right_text.setText("意见反馈");
	}

	@Override
	protected void installListeners() {
		// TODO Auto-generated method stub
		right_text.setClickable(true);
		right_text.setOnClickListener(this);
	}

	@Override
	protected void uninstallListeners() {
		// TODO Auto-generated method stub
		
	}
}
