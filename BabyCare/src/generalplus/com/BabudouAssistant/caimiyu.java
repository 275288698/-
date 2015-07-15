package generalplus.com.BabudouAssistant;

import generalplus.com.GPComAir5Lib.ComAir5Wrapper;

import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import com.babycare.R;
import com.babycare.app.MyAplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.hardware.SensorManager;
import android.hardware.Sensor;  
import android.hardware.SensorEvent;  
import android.hardware.SensorEventListener;  

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class caimiyu extends Activity implements SensorEventListener{
	private ComAir5Wrapper m_ComAir5 = new ComAir5Wrapper();
	private ProgressDialog MyDialog;
	private Thread newThread; 
	long lastClick;//记录�?���?��点击的时�?	

	private Button backBtn,tureBtn,falseBtn,nextBtn,replayBtn;
	private TextView mimianView,midiView;
	private String res1,res2;
	
	 
	//摇一�?
	SensorManager sensorManager = null;  
	Vibrator vibrator = null;  

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.caimiyu);
		
		MyAplication appKiller = (MyAplication) getApplication();
		appKiller.activities.add(this);
		
		mimianView=(TextView)findViewById(R.id.mimianView);
		midiView=(TextView)findViewById(R.id.midiView);
		backBtn=(Button)findViewById(R.id.backBtn);
		tureBtn=(Button)findViewById(R.id.tureBtn);
		falseBtn=(Button)findViewById(R.id.falseBtn);
	  	nextBtn=(Button)findViewById(R.id.nextBtn);
	  	replayBtn=(Button)findViewById(R.id.replayBtn);
	
		
		backBtn.setOnClickListener(click);
		tureBtn.setOnClickListener(click);
		falseBtn.setOnClickListener(click);
		nextBtn.setOnClickListener(click);
		replayBtn.setOnClickListener(click);
		
		
		res1 =globe.caimiyuname[globe.itemIndex];
		res2 =globe.caimiyukey[globe.itemIndex];
		
		mimianView.setText(""+res1);
		midiView.setText(res2);
		mimianView.setMovementMethod(ScrollingMovementMethod.getInstance());//手动滑动
		midiView.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		
		
	
	//摇一�?  
	    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);  
		vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);  

	}
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {// handler接收到消息后就会执行此方�?			
			MyDialog.dismiss();// 关闭ProgressDialog
		}
	};
	
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
			
			case R.id.backBtn:
				finish();

				break;
				
			case R.id.tureBtn:
				if (System.currentTimeMillis() - lastClick <= 1500)  
		        {   
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  	
				MyDialog = ProgressDialog.show( caimiyu.this,"", "正在发送...");       	
	        	newThread = new Thread(new Runnable() {   
		        	@Override 
		        	public void run() {  
		        		//这里写入子线程需要做的工�? 
		        		SystemClock.sleep(1500);
		        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
		        	}
		        });
		        newThread.start(); //启动线程	
				m_ComAir5.PlayComAirCmd2(8, globe.voice);
				System.out.println("ture");
				
				break;
				
			case R.id.falseBtn:
				if (System.currentTimeMillis() - lastClick <= 1500)  
		        {   
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  	
				MyDialog = ProgressDialog.show( caimiyu.this,"", "正在发送...");       	
	        	newThread = new Thread(new Runnable() {   
		        	@Override 
		        	public void run() {  
		        		//这里写入子线程需要做的工�? 
		        		SystemClock.sleep(1500);
		        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
		        	}
		        });
		        newThread.start(); //启动线程	
				m_ComAir5.PlayComAirCmd2(9, globe.voice);
				
				break;
				
			case R.id.nextBtn:
				if (System.currentTimeMillis() - lastClick <= 1500)  
		        {   
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  	
				MyDialog = ProgressDialog.show( caimiyu.this,"", "正在发送...");       	
	        	newThread = new Thread(new Runnable() {   
		        	@Override 
		        	public void run() {  
		        		//这里写入子线程需要做的工�? 
		        		SystemClock.sleep(3000);
		        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
		        	}
		        });
		        newThread.start(); //启动线程	
				m_ComAir5.PlayComAirCmd1(globe.cmd1[globe.itemIndex+1],globe.cmd2[globe.itemIndex+1], globe.voice);
			
				res1 =globe.caimiyuname[globe.itemIndex+1];
				res2 =globe.caimiyukey[globe.itemIndex+1];
				mimianView.setText(""+res1);
				midiView.setText(res2);
				globe.itemIndex++;
				
				break;
				
			case R.id.replayBtn:
				if (System.currentTimeMillis() - lastClick <= 1500)  
		        {   
		            return;  
		        }  
		        lastClick = System.currentTimeMillis();  	
				MyDialog = ProgressDialog.show( caimiyu.this,"", "正在发送...");       	
	        	newThread = new Thread(new Runnable() {    
		        	@Override 
		        	public void run() {  
		        		//这里写入子线程需要做的工�? 
		        		SystemClock.sleep(1500);
		        		handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
		        	}
		        });
		        newThread.start(); //启动线程	
		        m_ComAir5.PlayComAirCmd1(globe.cmd1[globe.itemIndex],globe.cmd2[globe.itemIndex], globe.voice);
				
				break;
				
			default:
				
				break;
			}
		}
	};
	
	
	
	    @Override  
	    protected void onPause()  
	    {  
	        super.onPause();  
	        sensorManager.unregisterListener(this);  
	    }  
	  
	    @Override  
	    protected void onResume()  
	    {  
	        super.onResume();  
	        sensorManager.registerListener(this,  
	                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),  
	                SensorManager.SENSOR_DELAY_NORMAL);  
	    }  
	  
	    @Override  
	    public void onAccuracyChanged(Sensor sensor, int accuracy)  
	    {  
	        //当传感器精度改变时回调该方法，Do nothing.   
	    }  
	  
	    @Override  
	    public void onSensorChanged(SensorEvent event)  
	    {  
	  
	        int sensorType = event.sensor.getType();  
	        //values[0]:X轴，values[1]：Y轴，values[2]：Z�?  
	        float[] values = event.values;  
	        if (sensorType == Sensor.TYPE_ACCELEROMETER)  
	        {  
	            if ((Math.abs(values[0]) > 17 || Math.abs(values[1]) > 17 || Math  
	                    .abs(values[2]) > 17))  
	            {  
	                Log.d("sensor x ", "============ values[0] = " + values[0]);  
	                Log.d("sensor y ", "============ values[1] = " + values[1]);  
	                 Log.d("sensor z ", "============ values[2] = " + values[2]);  
	                 System.out.println("摇一摇成�?!!");  
	                //摇动手机后，再伴随震动提示~~   
	                vibrator.vibrate(500);  
	            }  
	  
	        }  
	    }  
	  
	}
//	public String readFromRaw(int fileInRaw) { 
//        String res = ""; 
//        try { 
//            InputStream in = getResources().openRawResource(fileInRaw); 
//            int length = in.available(); 
//            byte[] buffer = new byte[length]; 
//            in.read(buffer); 
//            res = EncodingUtils.getString(buffer, "GBK"); 
//            // res = new String(buffer,"GBK"); 
//            in.close(); 
//        } catch (Exception e) { 
//            e.printStackTrace(); 
//        } 
//        return res; 
//    }

