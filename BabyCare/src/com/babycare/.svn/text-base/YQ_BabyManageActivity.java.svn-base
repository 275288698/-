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
public class YQ_BabyManageActivity extends CustomTitleActivity {
	private View view1,view2,view3,view4,view5,view6,view7,unBind;
	
	private boolean isShowing = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_babymanage);
		super.onCreate(savedInstanceState);
		setTitle("孩子管理");
		setTitleBarRightBtnText("编辑");
		setTitleBarLeftBtnText("返回");
	}

	@Override
	protected void initViews() {
		view1 = findViewById(R.id.arrow1);
		view2 = findViewById(R.id.arrow2);
		view3 = findViewById(R.id.arrow3);
		view4 = findViewById(R.id.arrow4);
		view5 = findViewById(R.id.arrow5);
		view6 = findViewById(R.id.arrow6);
		view7 = findViewById(R.id.arrow7);
		unBind = findViewById(R.id.btn_unBind);
		hideView();
	}

	private void hideView() {
		view1.setVisibility(View.GONE);
		view2.setVisibility(View.GONE);
		view3.setVisibility(View.GONE);
		view4.setVisibility(View.GONE);
		view5.setVisibility(View.GONE);
		view6.setVisibility(View.GONE);
		view7.setVisibility(View.INVISIBLE);
		unBind.setVisibility(View.INVISIBLE);
	}

	private void showView() {
		view1.setVisibility(View.VISIBLE);
		view2.setVisibility(View.VISIBLE);
		view3.setVisibility(View.VISIBLE);
		view4.setVisibility(View.VISIBLE);
		view5.setVisibility(View.VISIBLE);
		view6.setVisibility(View.VISIBLE);
		view7.setVisibility(View.VISIBLE);
		unBind.setVisibility(View.VISIBLE);
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
		case R.id.set_name:
			startActivity(new Intent(YQ_BabyManageActivity.this,YQ_NameChange_Activity.class));
			break;
		case R.id.btn_unBind:
			

			break;
	
		default:
			break;
		}
	}
	
	public void onTitleBarLeftBtnClick(View view) {
		// TODO Auto-generated method stub
		showAlerDialog();
	}

	@Override
	public void onTitleBarRightBtnClick(View view) {
		if (isShowing) {
			hideView();
			setTitleBarRightBtnText("编辑");
			setTitleBarLeftBtnText("返回");
		}else {
			showView();
			setTitleBarRightBtnText("完成");
			setTitleBarLeftBtnText("取消");
		}
		isShowing = !isShowing;
	}
	
	public void showAlerDialog() {
	
		
		
		View view = LayoutInflater.from(YQ_BabyManageActivity.this).inflate(
				R.layout.babymanage_dialog, null);
		TextView tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);

		TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
		final Dialog mDeleteDialog = new Dialog(YQ_BabyManageActivity.this, R.style.dialog);  
		mDeleteDialog.setContentView(view);  
		mDeleteDialog.show();  
		mDeleteDialog.getWindow().setGravity(Gravity.CENTER); 		
		
		tv_cancle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDeleteDialog.dismiss();
				finish();
			}
		});
		tv_confirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDeleteDialog.dismiss();
			}
		});

	}

}