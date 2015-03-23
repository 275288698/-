package com.babycare;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
 * 
 * @Description: TODO()
 * @author Ò×ÇÚ
 * @date 2015-3-18 ÏÂÎç12:31:38
 * @version V1.0
 */
public class YQ_LoginActivity extends CustomTitleActivity {
	private Button register, resend, confirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);
		super.onCreate(savedInstanceState);
		setTitle("ÓÂÍÞ");
		setTitleBarRightBtnText("");
		setTitleBarLeftBtnText("");
	}

	@Override
	protected void initViews() {
		register = (Button) findViewById(R.id.btn_register);
		resend = (Button) findViewById(R.id.btn_resend);
		confirm = (Button) findViewById(R.id.btn_confirm);
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
		case R.id.btn_login:
			startActivity(new Intent(YQ_LoginActivity.this,ContentMainActivity.class));
			break;
		case R.id.tv_register:
			startActivity(new Intent(YQ_LoginActivity.this,YQ_RegisterActivity.class));
			break;
		case R.id.tv_forgetPW:
			startActivity(new Intent(YQ_LoginActivity.this,ForgetPwdActivity.class));
			break;
		default:
			break;
		}
	}

	

}
