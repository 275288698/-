package com.babycare;

/**
 * 
 */

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.babycare.base.CustomTitleActivity;
import com.babycare.tool.T;

/**
 * 
 * @Description: TODO()
 * @author ����
 * @date 2014-11-10 ����9:51:10
 * @version V1.0
 */
public class YQ_VolumeChange_Activity extends CustomTitleActivity {
	private TextView  tv_volume;

	/*
	 * (��д���෽��)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_volumechange);
		super.onCreate(savedInstanceState);
		setTitle("����");
		setTitleBarRightBtnText("");

	}
	
	
	public void onBtnClick(View v) {
		switch (v.getId()) {
		case R.id.layout_volume:
			T.showShort(YQ_VolumeChange_Activity.this, "����ݻ���ѡ�����˵�����");
			tv_volume.setText(Integer.parseInt(tv_volume.getText().toString())+1+"");
			break;
	
	
		default:
			break;
		}
	}



	@Override
	protected void initViews() {
		tv_volume = (TextView) findViewById(R.id.tv_volume);

	}

	@Override
	protected void initDatas() {
		// TODO Auto-generated method stub

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
