package com.babycare;

import com.babycare.view.DrawerView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnCloseListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ContentMainActivity extends FragmentActivity {
	
protected SlidingMenu side_drawer;
	

	private ProgressBar top_progress;

	private ImageView top_refresh;

	private ImageView top_head;

	private ImageView top_more;

	public final static int CHANNELREQUEST = 1;

	public final static int CHANNELRESULT = 10;
	
	private boolean isOpen ;

	@Override
		protected void onCreate(Bundle arg0) {
			super.onCreate(arg0);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.content_main);
			initView();
			initSlidingMenu();
		}
	

	private void initView() {
		top_head = (ImageView) findViewById(R.id.top_head);
		top_more = (ImageView) findViewById(R.id.top_more);
		top_more.setBackgroundResource(R.drawable.titlebar_off_mainright);
		top_refresh = (ImageView) findViewById(R.id.top_refresh);
//		top_progress = (ProgressBar) findViewById(R.id.top_progress);
		
		top_head.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(side_drawer.isMenuShowing()){
					side_drawer.showContent();
				}else{
					side_drawer.showMenu();
				}
			}
		});
		top_more.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(side_drawer.isSecondaryMenuShowing()){
					side_drawer.showContent();
					top_more.setBackgroundResource(R.drawable.titlebar_off_mainright);
				}else{
					top_more.setBackgroundResource(R.drawable.titlebar_on_mainright);
					side_drawer.showSecondaryMenu();
					
				}
			}
		});
	}
	
	protected void initSlidingMenu() {
		side_drawer = new DrawerView(this).initSlidingMenu();
		
		side_drawer.setOnCloseListener(new OnCloseListener() {
			
			@Override
			public void onClose() {
				top_more.setBackgroundResource(R.drawable.titlebar_off_mainright);
				
				
//				if (top_more.isChecked()) {
//					top_more.setChecked(false);
//				}
				
			}
		});
	}
	
	
}
