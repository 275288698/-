package generalplus.com.BabudouAssistant;

import generalplus.com.GPComAir5Lib.JSONParser;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.babycare.R;
import com.babycare.app.MyAplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fankui extends Activity {

	private Button button11,tijiao;
	private String content;
	
	private EditText edittext_yijian;
	private static String url_add ="http://www.ubabytv.com.cn/ubabytvapp/appmessage.php";
//	private static final String TAG_MESSAGE = "message";
	
	private ProgressDialog pDialog;
	
	final DBAdapter db2 = new DBAdapter(this);
	
	JSONParser jsonParser = new JSONParser();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fankui);
		
		DBAdapter db2 = new DBAdapter(this);
		
		MyAplication appKiller = (MyAplication) getApplication();
		appKiller.activities.add(this); 
		
		if(globe.x==1){	
			Fankui.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
		}
		if(globe.x==2){
			Fankui.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		button11=(Button)findViewById(R.id.button11);
		tijiao=(Button)findViewById(R.id.tijiao);
		edittext_yijian=(EditText)findViewById(R.id.textView5);
		
		button11.setOnClickListener(click);
		tijiao.setOnClickListener(click);

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
				
				case R.id.button11:
					finish();
					break;
				case R.id.tijiao:
					System.out.println("111111");
			
					db2.open();
					Cursor c3 = db2.getContact(3);
					System.out.println("222222");
			        if (c3.moveToFirst()) 
			            DisplayContact3(c3); 
			        else 
			        	System.out.println("No contact found!");
			        db2.close();
			        
					if(validate()){
						new Up().execute();	
					}
					break;
				default:
					
					break;
				}
			}
		};
		
		
		private boolean validate()
		{
			
			String s8= edittext_yijian.getText().toString().trim();
			if (s8.equals(""))
			{
				DialogUtil.showDialog(this, "您还没有填写意见！", false);
				return false;
			}
			
			
	        
			if (globe.username.equals(""))
			{
				DialogUtil.showDialog(this, "您还没有登录！", false);
				return false;
			}
			return true;
		}
		
		class Up extends AsyncTask<String, String, String> {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(Fankui.this);
				pDialog.setMessage("正在发送..");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

			 protected String doInBackground(String... args) {
				 
				 content = edittext_yijian.getText().toString();
				     
				 List<NameValuePair> data = new ArrayList<NameValuePair>();
				 data.add(new BasicNameValuePair("username", globe.username));
				 data.add(new BasicNameValuePair("content", content));
				 
				 try{
					 
//					 String url_add ="http://www.liulinyan.com/apptest.php";
//					 System.out.println(s);
					 System.out.println(data);
					 String json = jsonParser.makeHttpRequest(url_add,"POST", data);
					 
//					 String message = json.getString(TAG_MESSAGE);
					 String message = json;
					 System.out.println(message);
					 return message;
				 }catch(Exception e){
					 e.printStackTrace(); 
					 return ""; 
				 }
			 }
			
			 protected void onPostExecute(String message) {  
				 pDialog.dismiss();
				//message 为接收doInbackground的返回值
				 if(message.equals("ok"+"\n")){
					 Toast.makeText(getApplicationContext(), "提交成功！", 4000).show();
				 }else{
					 Toast.makeText(getApplicationContext(), "提交失败！", 4000).show();
				 }
			 }
		}
		
		public void DisplayContact3(Cursor c3)  //获取用户名并保存
	    {  
	        globe.username=c3.getString(1);
//	        System.out.println(c3.getString(0));
//	        System.out.println(c3.getString(1));
//	        System.out.println(c3.getString(2));
//	        System.out.println(globe.username);
	    }
			
}
