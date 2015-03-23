package com.babycare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.babycare.base.CustomTitleActivity;

public class ForgetPwdActivity extends CustomTitleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.forget_pwd);
		super.onCreate(savedInstanceState);
		setTitle("Íü¼ÇÃÜÂë");
		setTitleBarRightBtnText("");
		setTitleBarLeftBtnText("·µ»Ø");
	}
	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

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
	public void onBtnClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			startActivity(new Intent(ForgetPwdActivity.this,ContentMainActivity.class));
			break;
		case R.id.btn_resend:
			v.setEnabled(false);

			break;

		default:
			break;
		}
	}

}
