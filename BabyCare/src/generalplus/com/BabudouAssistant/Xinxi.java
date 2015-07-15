package generalplus.com.BabudouAssistant;

import com.babycare.R;
import com.babycare.app.MyAplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Xinxi extends Activity {

	private Button button1,button2,button3,button12;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);;
		setContentView(R.layout.xinxi);
		
		MyAplication appKiller = (MyAplication) getApplication();
		appKiller.activities.add(this); 
		
		if(globe.x==1){	
			Xinxi.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
		}
		if(globe.x==2){
			Xinxi.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		button1=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		button3=(Button)findViewById(R.id.button30);
		button12=(Button)findViewById(R.id.button12);
		
		button1.setOnClickListener(click);
		button2.setOnClickListener(click);
		button3.setOnClickListener(click);
		button12.setOnClickListener(click);
		
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
				
			case R.id.button1:
				Intent intent1 = new Intent(Xinxi.this,Zhuce.class);
				startActivity(intent1);
				break;
			case R.id.button2:
				Intent intent2 = new Intent(Xinxi.this,Denglu.class);
				startActivity(intent2);
				break;
			case R.id.button30:
				Intent intent3 = new Intent(Xinxi.this,Xiugai.class);
				startActivity(intent3);
				break;
			case R.id.button12:
				finish();
				break;
			default:
				break;
			}
		} 
	};
			
}
