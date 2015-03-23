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
public class YQ_RegisterActivity extends CustomTitleActivity {
	private Button register, resend, confirm;
	private View firstLayout, secondLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_register);
		super.onCreate(savedInstanceState);
		setTitle("×¢²á");
		setTitleBarRightBtnText("");
	}

	@Override
	protected void initViews() {
		register = (Button) findViewById(R.id.btn_register);
		resend = (Button) findViewById(R.id.btn_resend);
		confirm = (Button) findViewById(R.id.btn_confirm);
		firstLayout = findViewById(R.id.layout_before);
		secondLayout = findViewById(R.id.layout_after);
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
			startActivity(new Intent(YQ_RegisterActivity.this,ContentMainActivity.class));
			break;
		case R.id.btn_resend:
			v.setEnabled(false);

			break;
		case R.id.btn_confirm:
			showAlerDialog();
			break;
		default:
			break;
		}
	}

	public void showAlerDialog() {
	
		
		
		View view = LayoutInflater.from(YQ_RegisterActivity.this).inflate(
				R.layout.register_dialog, null);
		TextView tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);
		TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);

		final Dialog mDeleteDialog = new Dialog(YQ_RegisterActivity.this, R.style.dialog);  
		mDeleteDialog.setContentView(view);  
		mDeleteDialog.show();  
		mDeleteDialog.getWindow().setGravity(Gravity.CENTER); 		
		
		tv_cancle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDeleteDialog.dismiss();

			}
		});
		tv_confirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDeleteDialog.dismiss();
				firstLayout.setVisibility(View.GONE);
				secondLayout.setVisibility(View.VISIBLE);
			}
		});

	}

}
