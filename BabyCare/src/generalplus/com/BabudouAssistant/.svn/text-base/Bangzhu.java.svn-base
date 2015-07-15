package generalplus.com.BabudouAssistant;

import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import com.babycare.R;
import com.babycare.app.MyAplication;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Bangzhu extends Activity {

	private Button button13;
	private TextView textview2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bangzhu);
		
		MyAplication appKiller = (MyAplication) getApplication();
		appKiller.activities.add(this); 
		
		if(globe.x==1){	
			Bangzhu.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
		}
		if(globe.x==2){
			Bangzhu.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		textview2=(TextView)findViewById(R.id.textView2);
		button13=(Button)findViewById(R.id.button13);
		button13.setOnClickListener(click);
		
		String res =readFromRaw(R.raw.shuoming);
		
		textview2.setText(res);
		textview2.setMovementMethod(ScrollingMovementMethod.getInstance());// ÷∂Øª¨∂Ø

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
				
				case R.id.button13:
					finish();
					break;
				default:
					
					break;
				}
			}
		};
		
		public String readFromRaw(int fileInRaw) { 
	        String res = ""; 
	        try { 
	            InputStream in = getResources().openRawResource(fileInRaw); 
	            int length = in.available(); 
	            byte[] buffer = new byte[length]; 
	            in.read(buffer); 
	            res = EncodingUtils.getString(buffer, "GBK"); 
	            // res = new String(buffer,"GBK"); 
	            in.close(); 
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	        return res; 
	    } 	
		
			
}