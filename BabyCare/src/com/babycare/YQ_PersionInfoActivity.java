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
public class YQ_PersionInfoActivity extends CustomTitleActivity {
	private View view1,view2,view3,view4,view5,view6,view7,unBind;
	
	private boolean isShowing = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_personinfo);
		super.onCreate(savedInstanceState);
		setTitle("个人信息");
		setTitleBarRightBtnText("编辑");
		setTitleBarLeftBtnText("Back");
	}

	@Override
	protected void initViews() {
		view1 = findViewById(R.id.arrow1);
		view2 = findViewById(R.id.arrow2);
		view3 = findViewById(R.id.arrow3);
		view4 = findViewById(R.id.arrow4);
		view5 = findViewById(R.id.arrow5);
		
		unBind = findViewById(R.id.layout_unBind);
		hideView();
	}

	private void hideView() {
		view1.setVisibility(View.GONE);
		view2.setVisibility(View.GONE);
		view3.setVisibility(View.GONE);
		view4.setVisibility(View.GONE);
		view5.setVisibility(View.GONE);
		unBind.setVisibility(View.VISIBLE);
	}

	private void showView() {
		view1.setVisibility(View.VISIBLE);
		view2.setVisibility(View.VISIBLE);
		view3.setVisibility(View.VISIBLE);
		view4.setVisibility(View.VISIBLE);
		view5.setVisibility(View.VISIBLE);
		unBind.setVisibility(View.INVISIBLE);
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
			startActivity(new Intent(YQ_PersionInfoActivity.this,YQ_NameChange_Activity.class));
			break;
		case R.id.layout_unBind:
			startActivity(new Intent(YQ_PersionInfoActivity.this,YQ_BindActivity.class));
			break;
		case R.id.layout_baby1:
			startActivity(new Intent(YQ_PersionInfoActivity.this,YQ_BabyManageActivity.class));
			break;
		case R.id.layout_baby2:
			startActivity(new Intent(YQ_PersionInfoActivity.this,YQ_BabyManageActivity.class));
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
	
		
		
		View view = LayoutInflater.from(YQ_PersionInfoActivity.this).inflate(
				R.layout.babymanage_dialog, null);
		TextView tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);

		TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
		final Dialog mDeleteDialog = new Dialog(YQ_PersionInfoActivity.this, R.style.dialog);  
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