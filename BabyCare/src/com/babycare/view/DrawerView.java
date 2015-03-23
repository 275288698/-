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
 * �Զ���SlidingMenu �����˵���
 * */
public class DrawerView implements OnClickListener{
	private final Activity activity;
	SlidingMenu localSlidingMenu;
	/**����Χ��*/
	private RelativeLayout search_btn;
	/**���»�*/
	private RelativeLayout favorite_btn;
	/**���ӹ���*/
	private RelativeLayout message_btn;
	/**�ϴ�ʱ��*/
	private RelativeLayout offline_btn;
	/**�������*/
	private RelativeLayout app_activity_btn;
	/**��������*/
	private RelativeLayout setting_btn;
	
	/**���ƹ켣*/
	private TextView track_draw_btn;
	/**�绰�ز�*/
	private TextView phone_call_back_btn;
	/**����Χ��*/
	private TextView electronic_fence_btn;
	
	private LinearLayout login_btn;
	
	public DrawerView(Activity activity) {
		this.activity = activity;
	}

	public SlidingMenu initSlidingMenu() {
		localSlidingMenu = new SlidingMenu(activity);
		localSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);//�������һ��˵�
		localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);//����Ҫʹ�˵�������������Ļ�ķ�Χ
//		localSlidingMenu.setTouchModeBehind(SlidingMenu.SLIDING_CONTENT);//������������ȡ�����˵�����Ľ��㣬������ע�͵�
		localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//������ӰͼƬ�Ŀ��
		localSlidingMenu.setShadowDrawable(R.drawable.shadow);//������ӰͼƬ
		localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu����ʱ��ҳ����ʾ��ʣ����
		localSlidingMenu.setFadeDegree(0.35F);//SlidingMenu����ʱ�Ľ���̶�
		localSlidingMenu.attachToActivity(activity, SlidingMenu.RIGHT);//ʹSlidingMenu������Activity�ұ�
//		localSlidingMenu.setBehindWidthRes(R.dimen.left_drawer_avatar_size);//����SlidingMenu�˵��Ŀ��
		localSlidingMenu.setMenu(R.layout.left_drawer_fragment);//����menu�Ĳ����ļ�
//		localSlidingMenu.toggle();//��̬�ж��Զ��رջ���SlidingMenu
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
		
		//��¼
		case R.id.login_btn:
			activity.startActivity(new Intent(activity,YQ_LoginActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//����Χ��
		case R.id.search_btn:
			activity.startActivity(new Intent(activity,ElectronicFence.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//���»�
		case R.id.favorite_btn:
			activity.startActivity(new Intent(activity,StoryMachine.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//���ӹ���
		case R.id.message_btn:
			activity.startActivity(new Intent(activity,YQ_BabyManageActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//�ϴ�ʱ��
		case R.id.offline_btn:
			activity.startActivity(new Intent(activity,YQ_TimeCycleActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//�������
		case R.id.app_activity_btn:
			activity.startActivity(new Intent(activity,SettingsActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;
			//��������
		case R.id.setting_btn:
			activity.startActivity(new Intent(activity,YQ_AboutUsActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			localSlidingMenu.showContent();
			break;	
			
			//���ƹ켣
		case R.id.track_draw_btn:
			activity.startActivity(new Intent(activity,YQ_AboutUsActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			localSlidingMenu.showContent();
			break;	
			
			//�绰�ز�
		case R.id.phone_call_back_btn:
			activity.startActivity(new Intent(activity,YQ_AboutUsActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			localSlidingMenu.showContent();
			break;	
			//����Χ��
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
