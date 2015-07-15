package generalplus.com.BabudouAssistant;

import java.io.InputStream; 
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.babycare.R;
import com.babycare.app.MyAplication;

import generalplus.com.GPComAir5Lib.ComAir5Wrapper;
import generalplus.com.GPComAir5Lib.Person;
import generalplus.com.GPComAir5Lib.SAXXmlContentHandler;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class MainActivity1 extends ActivityGroup {
	
	private ComAir5Wrapper m_ComAir5 = new ComAir5Wrapper();
	private MediaPlayer mPlayer = null;
	private ArrayAdapter<Person> adapter;
	private ArrayList<Person> persons;
	public AudioManager audioManager;
	
	private Button buttongushi1,buttonerge1,buttonyingyu1,buttonxingge1,buttonjuese1,buttonshezhi1,buttonback1;
	
	private Button rotary;
	private Button prev;
	private Button playpause;
	private Button next;
	private Button plus;
	private Button minus;
	private Button loop;
	
	private Button buttonboshi;
	private ListView listView;
	
 	private ProgressDialog MyDialog;
	private Thread newThread; //声明�?��子线�?	
	private int [] one = new int[300];
	private int [] two = new int[300];
	int i32Cmd, i32Count,i=0,j=0;
	long lastClick;
	private boolean flag=true;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity1);
        
        MyAplication appKiller = (MyAplication) getApplication();
		appKiller.activities.add(this);
        
        listView = (ListView) findViewById(R.id.listView);
        buttonboshi=(Button)findViewById(R.id.buttonboshi);
 
        prev=(Button)findViewById(R.id.button_prev);
        rotary=(Button)findViewById(R.id.button_rotary1);
        playpause=(Button)findViewById(R.id.button_playpause);
        next=(Button)findViewById(R.id.button_next);
        plus=(Button)findViewById(R.id.button_plus);
        minus=(Button)findViewById(R.id.button_minus);
        loop=(Button)findViewById(R.id.button_loop);
        
        buttongushi1=(Button)findViewById(R.id.buttongushi1);
        buttonerge1=(Button)findViewById(R.id.buttonerge1);
        buttonyingyu1=(Button)findViewById(R.id.buttonyingyu1);
        buttonxingge1=(Button)findViewById(R.id.buttonxingge1);
        buttonjuese1=(Button)findViewById(R.id.buttonjuese1);
        buttonshezhi1=(Button)findViewById(R.id.buttonshezhi1);
        buttonback1=(Button)findViewById(R.id.buttonback1);
        
		prev.setOnClickListener(click);
		rotary.setOnClickListener(click);
		playpause.setOnClickListener(click);
		next.setOnClickListener(click);
		plus.setOnClickListener(click);
		minus.setOnClickListener(click);
		loop.setOnClickListener(click);
				
		buttongushi1.setOnClickListener(click);
		buttonerge1.setOnClickListener(click);
		buttonyingyu1.setOnClickListener(click);
		buttonxingge1.setOnClickListener(click);
		buttonjuese1.setOnClickListener(click);
		buttonshezhi1.setOnClickListener(click);
		    
        //设置屏幕倒置 
        if(MainActivity1.this.getRequestedOrientation()==-1){
          Toast.makeText(MainActivity1.this, "系统的朝向无法获取", Toast.LENGTH_LONG).show();        
        }else{
        	if(MainActivity1.this.getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
        		globe.x=1;
        		MainActivity1.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        	}else if(MainActivity1.this.getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT){
        		MainActivity1.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        		globe.x=2;
        	}
        }
        
//        if(!bTxMode){
        persons = MainActivity1.this.readXml21();
      //simple_expandable_list_item_1是前面留有空格；simple_list_item_1置顶
        adapter = new ArrayAdapter<Person>(MainActivity1.this,
				android.R.layout.simple_list_item_1, persons);
        listView.setAdapter(adapter);
        audioManager=(AudioManager) getSystemService(Service.AUDIO_SERVICE);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> arg0, View arg1,final int arg2, long arg3) {
	        	if (System.currentTimeMillis() - lastClick <= 3100)  
		        {  
//		            showToast("点那么快干什�?!!");  
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  
//		        showToast("你点的是我吗...");
		        if(flag){
		        	flag=false;
		        	MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发�?...");       	
		        	newThread = new Thread(new Runnable() {   
			        	@Override 
			        	public void run() {  
			        		//这里写入子线程需要做的工�? 
			        		SystemClock.sleep(3000);
			        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
			        	}
			        });
			        newThread.start(); //启动线程	
			        
	//		        for(i=0;i<=260;i++)
	//		        {
	//		        	if(i==arg2)
	//		        	m_ComAir5.PlayComAirCmd(one[i], globe.voice);
	//		        }
	//		        for(j=0;j<=260;j++)
	//			    {
	//		        	if(j==arg2)
	//					m_ComAir5.PlayComAirCmd2(two[j], globe.voice);
	//		        }
			        
			        for(i=0,j=0;i<=260;i++,j++)
			        {
			        	if(i==arg2)
			        	m_ComAir5.PlayComAirCmd1(one[i],two[j], globe.voice);
			        }
			        flag=true;
			    }
//		    	m_ComAir5.PlayComAirCmd((24+(arg2)/40), globe.voice);
//				m_ComAir5.PlayComAirCmd2((24+(arg2)%40), globe.voice);
	        }	
	    });	
	}
	
	public void showToast(String str) {  
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();          
    } 

	@Override
	protected void onDestroy() {
		MyAplication app = (MyAplication) getApplication();
		app.activities.remove(this);
		super.onDestroy();
	}
	
	/*
	 * 设置声音按键为媒体音的按�? 和back键设成home�?	 * */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
			audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
			return true;
		} else if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
			audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
			return true;
		}else if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			//back键实现home键的功能
//			Intent i= new Intent(Intent.ACTION_MAIN);
//			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //android123提示如果是服务里调用，必须加入new task标识  
//			i.addCategory(Intent.CATEGORY_HOME);
//			startActivity(i);  
			
			globe.x=0;//1代表竖屏�?代表横屏�?默认的竖�?			//�?���?��activity
			MyAplication appKiller = (MyAplication) getApplication();
			List<Activity> list = appKiller.activities;
			for (Activity activity : list) {
					activity.finish();
				}
			
			return true;
		}else{
			
			return false;
		}
	}
	
	
	public OnClickListener click =new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			
			case R.id.buttongushi1:					
				listView.setBackgroundResource(R.drawable.back1);
				buttonboshi.setBackgroundResource(R.drawable.back1);
				buttongushi1.setBackgroundResource(R.drawable.gushi1);
				buttonerge1.setBackgroundResource(R.drawable.erge11);
				buttonyingyu1.setBackgroundResource(R.drawable.yingyu11);
				buttonxingge1.setBackgroundResource(R.drawable.xingge22);
				buttonjuese1.setBackgroundResource(R.drawable.juese22);
				buttonshezhi1.setBackgroundResource(R.drawable.shezhi22);
				buttonback1.setBackgroundResource(R.drawable.back11);
				
				persons = MainActivity1.this.readXml21();
				//simple_expandable_list_item_1是前面留有空格；simple_list_item_1置顶
		        adapter = new ArrayAdapter<Person>(MainActivity1.this,
						android.R.layout.simple_list_item_1, persons);
		        listView.setAdapter(adapter);
		        audioManager=(AudioManager) getSystemService(Service.AUDIO_SERVICE);
				
				listView.setOnItemClickListener(new OnItemClickListener() {
			        @Override
			        public void onItemClick(AdapterView<?> arg0, View arg1,final int arg2, long arg3) {
			        	
			        	if (System.currentTimeMillis() - lastClick <= 3100)  
				        {  
//				            showToast("点那么快干什�?!!");  
				            return;  
				        }  
				        lastClick = System.currentTimeMillis();  
//				        showToast("你点的是我噢...");
				        if(flag){
				        	flag=false;
				        	MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发�?...");       	
				        	newThread = new Thread(new Runnable() {   
					        	@Override 
					        	public void run() {  
					        		//这里写入子线程需要做的工�? 
					        		SystemClock.sleep(3000);
					        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
					        	}
					        });
					        newThread.start(); //启动线程	
					        
					        for(i=0,j=0;i<=260;i++,j++)
					        {
					        	if(i==arg2)
					        	m_ComAir5.PlayComAirCmd1(one[i],two[j], globe.voice);	
					        }
					        flag=true;
					    }
//				    	m_ComAir5.PlayComAirCmd((24+(arg2)/40), globe.voice);
//						m_ComAir5.PlayComAirCmd2((24+(arg2)%40), globe.voice);
			        }	
			    });				
				break;
	
			case R.id.buttonerge1:	
				listView.setBackgroundResource(R.drawable.back2);
				buttonboshi.setBackgroundResource(R.drawable.back2);
				buttongushi1.setBackgroundResource(R.drawable.gushi11);
				buttonerge1.setBackgroundResource(R.drawable.erge1);
				buttonyingyu1.setBackgroundResource(R.drawable.yingyu11);
				buttonxingge1.setBackgroundResource(R.drawable.xingge22);
				buttonjuese1.setBackgroundResource(R.drawable.juese22);
				buttonshezhi1.setBackgroundResource(R.drawable.shezhi22);
				buttonback1.setBackgroundResource(R.drawable.back22);
							
				persons = MainActivity1.this.readXml22();
				//simple_expandable_list_item_1是前面留有空格；simple_list_item_1置顶
		        adapter = new ArrayAdapter<Person>(MainActivity1.this,
						android.R.layout.simple_list_item_1, persons);
		        listView.setAdapter(adapter);
		        audioManager=(AudioManager) getSystemService(Service.AUDIO_SERVICE);
				
				listView.setOnItemClickListener(new OnItemClickListener() {
			        @Override
			        public void onItemClick(AdapterView<?> arg0, View arg1,final int arg2, long arg3) {
			        	
			        	if (System.currentTimeMillis() - lastClick <= 3100)  
				        {  
//				            showToast("点那么快干什�?!!");  
				            return;  
				        }  
				        lastClick = System.currentTimeMillis();  
//				        showToast("你点的是我噢...");
				        if(flag){
				        	flag=false;
				        	MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发�?...");       	
				        	newThread = new Thread(new Runnable() {   
					        	@Override 
					        	public void run() {  
					        		//这里写入子线程需要做的工�? 
					        		SystemClock.sleep(3000);
					        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
					        	}
					        });
					        newThread.start(); //启动线程	
					        
					        for(i=0,j=0;i<=260;i++,j++)
					        {
					        	if(i==arg2)
					        	m_ComAir5.PlayComAirCmd1(one[i],two[j], globe.voice);
					        }
					        flag=true;
					    }
//				    	m_ComAir5.PlayComAirCmd((24+(arg2+300)/40), globe.voice);
//						m_ComAir5.PlayComAirCmd2((24+(arg2+300)%40), globe.voice);
			        }	
			    });				
				break;
				
			case R.id.buttonyingyu1:	
				listView.setBackgroundResource(R.drawable.back3);
				buttonboshi.setBackgroundResource(R.drawable.back3);
				buttongushi1.setBackgroundResource(R.drawable.gushi11);
				buttonerge1.setBackgroundResource(R.drawable.erge11);
				buttonyingyu1.setBackgroundResource(R.drawable.yingyu1);
				buttonxingge1.setBackgroundResource(R.drawable.xingge22);
				buttonjuese1.setBackgroundResource(R.drawable.juese22);
				buttonshezhi1.setBackgroundResource(R.drawable.shezhi22);
				buttonback1.setBackgroundResource(R.drawable.back33);
				
				persons = MainActivity1.this.readXml23();
				//simple_expandable_list_item_1是前面留有空格；simple_list_item_1置顶
		        adapter = new ArrayAdapter<Person>(MainActivity1.this,
						android.R.layout.simple_list_item_1, persons);
		        listView.setAdapter(adapter);
		        audioManager=(AudioManager) getSystemService(Service.AUDIO_SERVICE);
				
				listView.setOnItemClickListener(new OnItemClickListener() {
			        @Override
			        public void onItemClick(AdapterView<?> arg0, View arg1,final int arg2, long arg3) {
			        	
			        	if (System.currentTimeMillis() - lastClick <= 3100)  
				        {  
//				            showToast("点那么快干什�?!!");  
				            return;  
				        }  
				        lastClick = System.currentTimeMillis();  
//				        showToast("你点的是我噢...");
				        if(flag){
				        	flag=false;
				        	MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发�?...");       	
				        	newThread = new Thread(new Runnable() {   
					        	@Override 
					        	public void run() {  
					        		//这里写入子线程需要做的工�? 
					        		SystemClock.sleep(3000);
					        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
					        	}
					        });
					        newThread.start(); //启动线程	
					        
					        for(i=0,j=0;i<=260;i++,j++)
					        {
					        	if(i==arg2)
					        	m_ComAir5.PlayComAirCmd1(one[i],two[j], globe.voice);
	
					        }
					        flag=true;
					    }
//				    	m_ComAir5.PlayComAirCmd((24+(arg2+600)/40), globe.voice);
//						m_ComAir5.PlayComAirCmd2((24+(arg2+600)%40), globe.voice);
			        }	
			    });			
				break;	
				
			case R.id.buttonxingge1:
				persons = MainActivity1.this.readXml24();
				
				listView.setBackgroundResource(R.drawable.back4);
				buttonboshi.setBackgroundResource(R.drawable.back4);
				buttongushi1.setBackgroundResource(R.drawable.gushi11);
				buttonerge1.setBackgroundResource(R.drawable.erge11);
				buttonyingyu1.setBackgroundResource(R.drawable.yingyu11);
				buttonxingge1.setBackgroundResource(R.drawable.xingge2);
				buttonjuese1.setBackgroundResource(R.drawable.juese22);
				buttonshezhi1.setBackgroundResource(R.drawable.shezhi22);
				buttonback1.setBackgroundResource(R.drawable.back44);
				
				//simple_expandable_list_item_1是前面留有空格；simple_list_item_1置顶
		        adapter = new ArrayAdapter<Person>(MainActivity1.this,
						android.R.layout.simple_list_item_1, persons);
		        listView.setAdapter(adapter);
		        audioManager=(AudioManager) getSystemService(Service.AUDIO_SERVICE);
				
				listView.setOnItemClickListener(new OnItemClickListener() {
			        @Override
			        public void onItemClick(AdapterView<?> arg0, View arg1,final int arg2, long arg3) {
			        	
			        	if (System.currentTimeMillis() - lastClick <= 3100)  
				        {  
//				            showToast("点那么快干什�?!!");  
				            return;  
				        }  
				        lastClick = System.currentTimeMillis();  
//				        showToast("你点的是我噢...");
				        if(flag){
				        	flag=false;
				        	MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发送...");       	
				        	newThread = new Thread(new Runnable() {   
					        	@Override 
					        	public void run() {  
					        		//这里写入子线程需要做的工�? 
					        		SystemClock.sleep(3000);
					        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
					        	}
					        });
					        newThread.start(); //启动线程	
					        
					        for(i=0,j=0;i<=260;i++,j++)
					        {
					        	if(i==arg2)
					        	m_ComAir5.PlayComAirCmd1(one[i],two[j], globe.voice);
					        }
					        flag=true;
					    }
//				    	m_ComAir5.PlayComAirCmd((24+(arg2+800)/40), globe.voice);
//						m_ComAir5.PlayComAirCmd2((24+(arg2+800)%40), globe.voice);
			        }	
			    });				
				break;	
		
			case R.id.buttonjuese1:		
				listView.setBackgroundResource(R.drawable.back5);
				buttonboshi.setBackgroundResource(R.drawable.back5);
				buttongushi1.setBackgroundResource(R.drawable.gushi11);
				buttonerge1.setBackgroundResource(R.drawable.erge11);
				buttonyingyu1.setBackgroundResource(R.drawable.yingyu11);
				buttonxingge1.setBackgroundResource(R.drawable.xingge22);
				buttonjuese1.setBackgroundResource(R.drawable.juese2);
				buttonshezhi1.setBackgroundResource(R.drawable.shezhi22);
				buttonback1.setBackgroundResource(R.drawable.back55);
		
				persons = MainActivity1.this.readXml25();
				//simple_expandable_list_item_1是前面留有空格；simple_list_item_1置顶
		        adapter = new ArrayAdapter<Person>(MainActivity1.this,
						android.R.layout.simple_list_item_1, persons);
		        listView.setAdapter(adapter);
		        audioManager=(AudioManager) getSystemService(Service.AUDIO_SERVICE);
				
				listView.setOnItemClickListener(new OnItemClickListener() {
			        @Override
			        public void onItemClick(AdapterView<?> arg0, View arg1,final int arg2, long arg3) {
			        	
			        	if (System.currentTimeMillis() - lastClick <= 3100)  
				        {  
//				            showToast("点那么快干什�?!!");  
				            return;  
				        }  
				        lastClick = System.currentTimeMillis();  
//				        showToast("你点的是我噢...");
				        if(flag){
				        	flag=false;
				        	MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发送...");       	
				        	newThread = new Thread(new Runnable() {   
					        	@Override 
					        	public void run() {  
					        		//这里写入子线程需要做的工�? 
					        		SystemClock.sleep(3000);
					        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
					        	}
					        });
					        newThread.start(); //启动线程	
					        
					        for(i=0,j=0;i<=260;i++,j++)
					        {
					        	if(i==arg2)
					        	m_ComAir5.PlayComAirCmd1(one[i],two[j], globe.voice);
					        }
					        flag=true;
					    }
//				        m_ComAir5.PlayComAirCmd((24+(arg2+1400)/40), globe.voice);
//						m_ComAir5.PlayComAirCmd2((24+(arg2+1400)%40), globe.voice);
			        }	
			    });				
				break;
			
			case R.id.buttonshezhi1:	
				
				if (System.currentTimeMillis() - lastClick <= 1000)  
		        {  
//		            showToast("点那么快干什�?!!");  
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  
//		        showToast("你点的是我噢...");
				 
				Intent intent = new Intent(MainActivity1.this,Shezhi.class);
				startActivity(intent);
				
				break;	
					
			case R.id.button_rotary1:				
				 if(MainActivity1.this.getRequestedOrientation()==-1){
			          Toast.makeText(MainActivity1.this, "系统的朝向无法获取", Toast.LENGTH_LONG).show();        
			        }else{
			        	if(MainActivity1.this.getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
			        		globe.x=1;
			        		MainActivity1.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
			        	}else if(MainActivity1.this.getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT){
			        		globe.x=2;
			        		MainActivity1.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			        	}
			        }
				break;
			case R.id.button_prev:
				
				if (System.currentTimeMillis() - lastClick <= 1500)  
		        {  
//		            showToast("点那么快干什�?!!");  
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  
//		        showToast("你点的是我噢...");
				
				MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发送...");       	
	        	newThread = new Thread(new Runnable() {   
		        	@Override 
		        	public void run() {  
		        		//这里写入子线程需要做的工�? 
		        		SystemClock.sleep(1500);
		        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
		        	}
		        });
		        newThread.start(); //启动线程	
				m_ComAir5.PlayComAirCmd2(1, globe.voice);
//				if(!bTxMode)
//					PlayCmdSound(1);
				break;		
			case R.id.button_playpause:
				
				if (System.currentTimeMillis() - lastClick <= 1500)  
		        {  
//		            showToast("点那么快干什�?!!");  
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  
//		        showToast("你点的是我噢...");
				
				MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发送...");       	
	        	newThread = new Thread(new Runnable() {   
		        	@Override 
		        	public void run() {  
		        		//这里写入子线程需要做的工�? 
		        		SystemClock.sleep(1500);
		        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
		        	}
		        });
		        newThread.start(); //启动线程	
				m_ComAir5.PlayComAirCmd2(0, globe.voice);	
				break;		
			case R.id.button_next:
				
				if (System.currentTimeMillis() - lastClick <= 1500)  
		        {  
//		            showToast("点那么快干什�?!!");  
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  
//		        showToast("你点的是我噢...");
				
				MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发送...");       	
	        	newThread = new Thread(new Runnable() {   
		        	@Override 
		        	public void run() {  
		        		//这里写入子线程需要做的工�? 
		        		SystemClock.sleep(1500);
		        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
		        	}
		        });
		        newThread.start(); //启动线程	
				m_ComAir5.PlayComAirCmd2(2, globe.voice);
//				if(!bTxMode)
//					PlayCmdSound(2);
				break;
			case R.id.button_plus:
				
				if (System.currentTimeMillis() - lastClick <= 1500)  
		        {  
//		            showToast("点那么快干什�?!!");  
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  
//		        showToast("你点的是我噢...");
				
				MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发送...");       	
	        	newThread = new Thread(new Runnable() {   
		        	@Override 
		        	public void run() {  
		        		//这里写入子线程需要做的工�? 
		        		SystemClock.sleep(1500);
		        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
		        	}
		        });
		        newThread.start(); //启动线程	
				m_ComAir5.PlayComAirCmd2(3, globe.voice);	
				break;		
			case R.id.button_minus:
				
				if (System.currentTimeMillis() - lastClick <= 1500)  
		        {  
//		            showToast("点那么快干什�?!!");  
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  
//		        showToast("你点的是我噢...");
				
				MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发送...");       	
	        	newThread = new Thread(new Runnable() {   
		        	@Override 
		        	public void run() {  
		        		//这里写入子线程需要做的工�? 
		        		SystemClock.sleep(1500);
		        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
		        	}
		        });
		        newThread.start(); //启动线程	
				m_ComAir5.PlayComAirCmd2(4, globe.voice);	
				break;
			case R.id.button_loop:
				
				if (System.currentTimeMillis() - lastClick <= 1500)  
		        {  
//		            showToast("点那么快干什�?!!");  
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  
//		        showToast("你点的是我噢...");
				
				MyDialog = ProgressDialog.show( MainActivity1.this,"", "正在发送...");       	
	        	newThread = new Thread(new Runnable() {   
		        	@Override 
		        	public void run() {  
		        		//这里写入子线程需要做的工�? 
		        		SystemClock.sleep(1500);
		        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
		        	}
		        });
		        newThread.start(); //启动线程	
				m_ComAir5.PlayComAirCmd2(5, globe.voice);	
				break;
			default:
				
				break; 
			}
		}
	};
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {// handler接收到消息后就会执行此方�?			
			MyDialog.dismiss();// 关闭ProgressDialog
		}
	};
	
    public void PlayCmdSound(int i32Command)
	{
		if(mPlayer != null)
		{
			mPlayer.stop();
			mPlayer.release();
			mPlayer = null;			
		}
		mPlayer = MediaPlayer.create(this, getResources().getIdentifier("n" + i32Command, "raw", this.getPackageName()));
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mPlayer.setVolume(2.0f, 2.0f);
		
		mPlayer.start();
		
		mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer arg0) {
			}				
		});
	}
    
    private ArrayList<Person> readXml21() {  
        InputStream file = this.getClass().getClassLoader()  
                .getResourceAsStream("assets/boshigushi.xml");  
        // ①创建XML解析处理�? 
        SAXXmlContentHandler contentHandler = new SAXXmlContentHandler(); 
        try {  
            // 创建�?��SAXParserFactory  
            SAXParserFactory factory = SAXParserFactory.newInstance();  
            // ②创建SAX解析�? 
            SAXParser parser = factory.newSAXParser();  
            // ③将XML解析处理器分配给解析�? 
            // ④对文档进行解析，将每个事件发�?给处理器�? 
            parser.parse(file, contentHandler);  
            file.close();  
  
            i=j=0;
            persons=contentHandler.getBooks();
            for(Person person : persons){  
//            	System.out.println("id:"+person.getId()+"\tname:"+person.getName()+"\tonenumber:"+person.getOnenumber()+"\ttwonumber:"+person.getTwonumber()+"\tmold:"+person.getMold());
            	one[i]=person.getOnenumber();
            	i++;
            	two[j]=person.getTwonumber();
            	j++;
            }        
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return contentHandler.getBooks();  		
	}
    
    private ArrayList<Person> readXml22() {  
        InputStream file = this.getClass().getClassLoader()  
                .getResourceAsStream("assets/boshierge.xml");  
        // ①创建XML解析处理�? 
        SAXXmlContentHandler contentHandler = new SAXXmlContentHandler(); 
        try {  
            // 创建�?��SAXParserFactory  
            SAXParserFactory factory = SAXParserFactory.newInstance();  
            // ②创建SAX解析�? 
            SAXParser parser = factory.newSAXParser();  
            // ③将XML解析处理器分配给解析�? 
            // ④对文档进行解析，将每个事件发�?给处理器�? 
            parser.parse(file, contentHandler);  
            file.close();  
  
            i=j=0;
            persons=contentHandler.getBooks();
            for(Person person : persons){  
//            	System.out.println("id:"+person.getId()+"\tname:"+person.getName()+"\tonenumber:"+person.getOnenumber()+"\ttwonumber:"+person.getTwonumber()+"\tmold:"+person.getMold());
            	one[i]=person.getOnenumber();
            	i++;
            	two[j]=person.getTwonumber();
            	j++;
            }  
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return contentHandler.getBooks();  		
	}
    
    private ArrayList<Person> readXml23() {  
        InputStream file = this.getClass().getClassLoader()  
                .getResourceAsStream("assets/boshiyingyu.xml");  
        // ①创建XML解析处理�? 
        SAXXmlContentHandler contentHandler = new SAXXmlContentHandler(); 
        try {  
            // 创建�?��SAXParserFactory  
            SAXParserFactory factory = SAXParserFactory.newInstance();  
            // ②创建SAX解析�? 
            SAXParser parser = factory.newSAXParser();  
            // ③将XML解析处理器分配给解析�? 
            // ④对文档进行解析，将每个事件发�?给处理器�? 
            parser.parse(file, contentHandler);  
            file.close();  
  
            i=j=0;
            persons=contentHandler.getBooks();
            for(Person person : persons){  
//            	System.out.println("id:"+person.getId()+"\tname:"+person.getName()+"\tonenumber:"+person.getOnenumber()+"\ttwonumber:"+person.getTwonumber()+"\tmold:"+person.getMold());
            	one[i]=person.getOnenumber();
            	i++;
            	two[j]=person.getTwonumber();
            	j++;
            }  
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return contentHandler.getBooks();  		
	}
    
    private ArrayList<Person> readXml24() {  
        InputStream file = this.getClass().getClassLoader()  
                .getResourceAsStream("assets/boshixingge.xml");  
        // ①创建XML解析处理�? 
        SAXXmlContentHandler contentHandler = new SAXXmlContentHandler(); 
        try {  
            // 创建�?��SAXParserFactory  
            SAXParserFactory factory = SAXParserFactory.newInstance();  
            // ②创建SAX解析�? 
            SAXParser parser = factory.newSAXParser();  
            // ③将XML解析处理器分配给解析�? 
            // ④对文档进行解析，将每个事件发�?给处理器�? 
            parser.parse(file, contentHandler);  
            file.close();  
  
            i=j=0;
            persons=contentHandler.getBooks();
            for(Person person : persons){  
//            	System.out.println("id:"+person.getId()+"\tname:"+person.getName()+"\tonenumber:"+person.getOnenumber()+"\ttwonumber:"+person.getTwonumber()+"\tmold:"+person.getMold());
            	one[i]=person.getOnenumber();
            	i++;
            	two[j]=person.getTwonumber();
            	j++;
            }  
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return contentHandler.getBooks();  		
	}
    
    private ArrayList<Person> readXml25() {  
        InputStream file = this.getClass().getClassLoader()  
                .getResourceAsStream("assets/boshijuese.xml");  
        // ①创建XML解析处理�? 
        SAXXmlContentHandler contentHandler = new SAXXmlContentHandler(); 
        try {  
            // 创建�?��SAXParserFactory  
            SAXParserFactory factory = SAXParserFactory.newInstance();  
            // ②创建SAX解析�? 
            SAXParser parser = factory.newSAXParser();  
            // ③将XML解析处理器分配给解析�? 
            // ④对文档进行解析，将每个事件发�?给处理器�? 
            parser.parse(file, contentHandler);  
            file.close();  
  
            i=j=0;
            persons=contentHandler.getBooks();
            for(Person person : persons){  
//            	System.out.println("id:"+person.getId()+"\tname:"+person.getName()+"\tonenumber:"+person.getOnenumber()+"\ttwonumber:"+person.getTwonumber()+"\tmold:"+person.getMold());
            	one[i]=person.getOnenumber();
            	i++;
            	two[j]=person.getTwonumber();
            	j++;
            }  
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return contentHandler.getBooks();  		
	}
 
}