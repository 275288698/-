package com.babycare.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babycare.ElectronicFence;
import com.babycare.R;
import com.babycare.SettingsActivity;
import com.babycare.StoryMachine;
import com.babycare.YQ_AboutUsActivity;
import com.babycare.YQ_BabyManageActivity;
import com.babycare.YQ_LoginActivity;
import com.babycare.YQ_TimeCycleActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
/** 
 * 自定义SlidingMenu 测拉菜单类
 * */
public class DrawerView implements OnClickListener{
	private final Activity activity;
	SlidingMenu localSlidingMenu;
	/**电子围栏*/
	private RelativeLayout search_btn;
	/**故事机*/
	private RelativeLayout favorite_btn;
	/**孩子管理*/
	private RelativeLayout message_btn;
	/**上传时间*/
	private RelativeLayout offline_btn;
	/**意见反馈*/
	private RelativeLayout app_activity_btn;
	/**关于我们*/
	private RelativeLayout setting_btn;
	
	/**绘制轨迹*/
	private TextView track_draw_btn;
	/**电话回拨*/
	private TextView phone_call_back_btn;
	/**电子围栏*/
	private TextView electronic_fence_btn;
	
	private LinearLayout login_btn;
	
	public DrawerView(Activity activity) {
		this.activity = activity;
	}

	public SlidingMenu initSlidingMenu() {
		localSlidingMenu = new SlidingMenu(activity);
		localSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);//设置左右滑菜单
		localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);//设置要使菜单滑动，触碰屏幕的范围
//		localSlidingMenu.setTouchModeBehind(SlidingMenu.SLIDING_CONTENT);//设置了这个会获取不到菜单里面的焦点，所以先注释掉
		localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片的宽度
		localSlidingMenu.setShadowDrawable(R.drawable.shadow);//设置阴影图片
		localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu划出时主页面显示的剩余宽度
		localSlidingMenu.setFadeDegree(0.35F);//SlidingMenu滑动时的渐变程度
		localSlidingMenu.attachToActivity(activity, SlidingMenu.RIGHT);//使SlidingMenu附加在Activity右边
//		localSlidingMenu.setBehindWidthRes(R.dimen.left_drawer_avatar_size);//设置SlidingMenu菜单的宽度
		localSlidingMenu.setMenu(R.layout.left_drawer_fragment);//设置menu的布局文件
//		localSlidingMenu.toggle();//动态判断自动关闭或开启SlidingMenu
		localSlidingMenu.setSecondaryMenu(R.layout.profile_drawer_right);
		localSlidingMenu.setRightBehindWidthRes(R.dimen.slidingmenu_right_offset);
		localSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
		localSlidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
					public void onOpened() {
						
					}
				});
		localSlidingMenu.setOnClosedListener(new OnClosedListener() {
			
			@Override
			public void onClosed() {
				// TODO Auto-generated method stub
				
			}
		});
		initView();
		return localSlidingMenu;
	}

	private void initView() {
		login_btn = (LinearLayout)localSlidingMenu.findViewById(R.id.login_btn);
		search_btn =(RelativeLayout)localSlidingMenu.findViewById(R.id.search_btn);
		favorite_btn = (RelativeLayout)localSlidingMenu.findViewById(R.id.favorite_btn);
		message_btn =(RelativeLayout)localSlidingMenu.findViewById(R.id.message_btn);
		offline_btn = (RelativeLayout)localSlidingMenu.findViewById(R.id.offline_btn);
		app_activity_btn =(RelativeLayout)localSlidingMenu.findViewById(R.id.search_btn);
		setting_btn = (RelativeLayout)localSlidingMenu.findViewById(R.id.setting_btn);
		track_draw_btn = (TextView) localSlidingMenu.findViewById(R.id.track_draw_btn);
		phone_call_back_btn = (TextView) localSlidingMenu.findViewById(R.id.phone_call_back_btn);
		electronic_fence_btn = (TextView) localSlidingMenu.findViewById(R.id.electronic_fence_btn);
		
		login_btn.setOnClickListener(this);
		search_btn.setOnClickListener(this);
		favorite_btn.setOnClickListener(this);
		message_btn.setOnClickListener(this);
		offline_btn.setOnClickListener(this);
		app_activity_btn.setOnClickListener(this);
		setting_btn.setOnClickListener(this);
		track_draw_btn.setOnClickListener(this);
		phone_call_back_btn.setOnClickListener(this);
		electronic_fence_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		//登录
		case R.id.login_btn:
			activity.startActivity(new Intent(activity,YQ_LoginActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//电子围栏
		case R.id.search_btn:
			activity.startActivity(new Intent(activity,ElectronicFence.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//故事机
		case R.id.favorite_btn:
			activity.startActivity(new Intent(activity,StoryMachine.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//孩子管理
		case R.id.message_btn:
			activity.startActivity(new Intent(activity,YQ_BabyManageActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//上传时间
		case R.id.offline_btn:
			activity.startActivity(new Intent(activity,YQ_TimeCycleActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//意见反馈
		case R.id.app_activity_btn:
			activity.startActivity(new Intent(activity,SettingsActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//关于我们
		case R.id.setting_btn:
			activity.startActivity(new Intent(activity,YQ_AboutUsActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;	
			
			//绘制轨迹
		case R.id.track_draw_btn:
			activity.startActivity(new Intent(activity,YQ_AboutUsActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			localSlidingMenu.showContent();
			break;	
			
			//电话回拨
		case R.id.phone_call_back_btn:
			activity.startActivity(new Intent(activity,YQ_AboutUsActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			localSlidingMenu.showContent();
			break;	
			//电子围栏
		case R.id.electronic_fence_btn:
			activity.startActivity(new Intent(activity,ElectronicFence.class));
			activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			localSlidingMenu.showContent();
			break;	
	
		default:
			break;
	}
}
}
