package generalplus.com.BabudouAssistant;

import com.babycare.R;
import com.babycare.app.MyAplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
  
public class Leixing extends Activity{

	private Button queding;
	private Spinner spinner1;
	
	private ProgressDialog pDialog;
	private Thread newThread; //声明�?��子线�?
	
	private String kongzhixinghao;
	   
	private String[] xinghao = { "优彼亲子熊三代", "小博士","小厨师","优彼亲子家教机","消防员","小警察"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leixing);  
		
		final DBAdapter db1 = new DBAdapter(this);
	    
		MyAplication appKiller = (MyAplication) getApplication();
		appKiller.activities.add(this); 
		//设置屏幕
		if(globe.x==1){	
			Leixing.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
		}
		if(globe.x==2){
			Leixing.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		queding=(Button)findViewById(R.id.queding);
		
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xinghao);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner1.setAdapter(adapter1);
		
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		
		
		queding.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				kongzhixinghao = spinner1.getSelectedItem().toString();//获取型号选择的�?项并转换成文�?
//				pDialog = ProgressDialog.show( Leixing.this,"", "正在加载数据...");
//				newThread = new Thread(new Runnable() {   
//		        	@Override 
//		        	public void run() {  
//		        		//这里写入子线程需要做的工�? 
//		        		SystemClock.sleep(1000);
//		        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
//		        	}
//		        });
//		        newThread.start(); //启动线程	
				if(kongzhixinghao.equals("优彼亲子熊代"))
			    {
			    	//优彼亲子熊三代命�?
			    	//update contact  
			        db1.open(); 
			        if ( db1.updateContact(1, "ggm", 2) )
			        	System.out.println("Ubbie Update sceess!");
			        else 
			        	System.out.println("Ubbie Update failed!"); 
			        db1.close();
			        
			    	Intent intent = new Intent(Leixing.this,MainActivity.class);
			    	finish();
			    	startActivity(intent);
			    }else if(kongzhixinghao.equals("小厨师"))
			    {
			    	//小厨师三代命�?
			    	//update contact  
			        db1.open(); 
			        if ( db1.updateContact(1, "ggm", 6) )
			        	System.out.println("Ubbie Update sceess!");
			        else 
			        	System.out.println("Ubbie Update failed!"); 
			        db1.close();
			        
			    	Intent intent = new Intent(Leixing.this,MainActivity.class);//4
			    	finish();
			    	startActivity(intent);
			    }else if(kongzhixinghao.equals("优彼亲子家教机"))
			    {
			    	//三代熊太空版命令
			    	//update contact  
			        db1.open(); 
			        if ( db1.updateContact(1, "ggm", 7) )
			        	System.out.println("Ubbie Update sceess!");
			        else 
			        	System.out.println("Ubbie Update failed!"); 
			        db1.close();
			        
			    	Intent intent = new Intent(Leixing.this,MainActivity.class);
			    	finish();
			    	startActivity(intent);
			    }
			    else if(kongzhixinghao.equals("消防员"))
			    {
			    	//三代熊太空版命令
			    	//update contact  
			        db1.open(); 
			        if ( db1.updateContact(1, "ggm", 8) )
			        	System.out.println("Ubbie Update sceess!");
			        else 
			        	System.out.println("Ubbie Update failed!"); 
			        db1.close();
			        
			    	Intent intent = new Intent(Leixing.this,MainActivity.class);
			    	finish();
			    	startActivity(intent);
			    }
			    else if(kongzhixinghao.equals("小警察"))
			    {
			    	//三代熊太空版命令
			    	//update contact  
			        db1.open(); 
			        if ( db1.updateContact(1, "ggm", 9) )
			        	System.out.println("Ubbie Update sceess!");
			        else 
			        	System.out.println("Ubbie Update failed!"); 
			        db1.close();
			        
			    	Intent intent = new Intent(Leixing.this,MainActivity.class);
			    	finish();
			    	startActivity(intent);
			    }else{
			    	//小博士控制命�?
				    	//update contact  
				        db1.open(); 
				        if (db1.updateContact(1, "ggm", 3))
				        	System.out.println("Xiaoboshi Update sceess!");
				        else 
				        	System.out.println("Xiaoboshi Update failed!"); 
				        db1.close();
//				        globe.x=2;
				    	Intent intent = new Intent(Leixing.this,MainActivity1.class);
				    	finish();
				    	startActivity(intent);			    
			    }
			}
		});
	}
	

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {// handler接收到消息后就会执行此方�?
			pDialog.dismiss();// 关闭ProgressDialog
		}
	};

	@Override
	protected void onDestroy() {
		MyAplication app = (MyAplication) getApplication();
		app.activities.remove(this);
		super.onDestroy();
	}
	
	  
	  
}