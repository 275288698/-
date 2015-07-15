package generalplus.com.BabudouAssistant;


import java.util.List;

import com.babycare.R;
import com.babycare.app.MyAplication;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class zhishiwenda extends Activity {

	private Button ganshou,xinxi,bangzhu,tuichu,kongzhi,button9;
	
	final DBAdapter db1 = new DBAdapter(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shezhi);
	if(globe.x==1){	
		zhishiwenda.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
	}
	if(globe.x==2){
		zhishiwenda.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
		MyAplication appKiller = (MyAplication) getApplication();
		appKiller.activities.add(this); 

		ganshou=(Button)findViewById(R.id.ganshou);
		xinxi=(Button)findViewById(R.id.xinxi);
		bangzhu=(Button)findViewById(R.id.bangzhu);
		tuichu=(Button)findViewById(R.id.tuichu);
		kongzhi=(Button)findViewById(R.id.kongzhi);
		button9=(Button)findViewById(R.id.button9);
		
		ganshou.setOnClickListener(click);
		xinxi.setOnClickListener(click);
		bangzhu.setOnClickListener(click);
		kongzhi.setOnClickListener(click);
		tuichu.setOnClickListener(click);
		button9.setOnClickListener(click);
		
		
		
		
		
	} 
	
	@Override  
	protected void onDestroy() { 
		MyAplication app = (MyAplication) getApplication();
		app.activities.remove(this);
		super.onDestroy();
	}
		
		   public OnClickListener click =new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				switch(v.getId()){
				
				case R.id.button9:
					finish();
//					Intent intent = new Intent(Shezhi.this,MainActivity.class);
//					startActivity(intent);
					break;	
				case R.id.ganshou:
					Intent intent1 = new Intent(zhishiwenda.this,Fankui.class);
					startActivity(intent1);
					break;
				case R.id.xinxi:
					Intent intent2 = new Intent(zhishiwenda.this,Xinxi.class);
					startActivity(intent2);
					break;		
				case R.id.bangzhu:
					Intent intent3 = new Intent(zhishiwenda.this,Bangzhu.class);
					startActivity(intent3);
					break;
				case R.id.kongzhi:
					Intent intent4 = new Intent(zhishiwenda.this,Leixing.class);
					startActivity(intent4);
					break;
				case R.id.tuichu:

					new AlertDialog.Builder(zhishiwenda.this)
					.setTitle("提示")
					.setMessage("确定退出?")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									setResult(RESULT_OK); // 确定按钮事件
									db1.open(); 
							        if ( db1.updateContact(1, "", 0) )
							        	System.out.println("Ubbie Update sceess!");
							        else 
							        	System.out.println("Ubbie Update failed!");
							        if ( db1.updateContact(2, "", 0) )
							        	System.out.println("Ubbie Update sceess!");
							        else 
							        	System.out.println("Ubbie Update failed!");
							        if ( db1.updateContact(3, "", 0) )
							        	System.out.println("Ubbie Update sceess!");
							        else 
							        	System.out.println("Ubbie Update failed!");
							        if ( db1.updateContact(4, "", 0) )
							        	System.out.println("Ubbie Update sceess!");
							        else 
							        	System.out.println("Ubbie Update failed!");
							        db1.close();
							        
							        globe.x=0;//1代表竖屏，2代表横屏，0默认的竖屏
							        
								MyAplication appKiller = (MyAplication) getApplication();
								List<Activity> list = appKiller.activities;
								for (Activity activity : list) {
										activity.finish();
									}	

								}
							})          
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// 取消按钮事件
								}
							}).show(); 
				
					break;
				default:
					
					break;
				}
			}
		};
			
}
