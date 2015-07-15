package generalplus.com.BabudouAssistant;

import generalplus.com.GPComAir5Lib.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EncodingUtils;

import com.babycare.R;
import com.babycare.app.MyAplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class Denglu extends Activity{

	private EditText edittext_nicheng,edittext_mima;
	private Button button_zhuce,button_denglu;
	
	private String username,pwd;
	private String json,json1;
	private String res;
	
	final DBAdapter db1 = new DBAdapter(this);
	
	private static String url_add ="http://www.ubabytv.com.cn/ubabytvapp/app_login.php";
	private static String url_add1 ="http://www.ubabytv.com.cn/ubabytvapp/app_getpwd.php";
//	private static final String TAG_MESSAGE = "message";
	
	private ProgressDialog pDialog;
	
	JSONParser jsonParser = new JSONParser();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.denglu);	    
		
		if(globe.x==1){	
			Denglu.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
		}
		if(globe.x==2){
			Denglu.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		MyAplication appKiller = (MyAplication) getApplication();
		appKiller.activities.add(this); 
		
		button_zhuce=(Button)findViewById(R.id.button_zhuce2);
		button_denglu=(Button)findViewById(R.id.button_denglu1);
		
		edittext_nicheng=(EditText)findViewById(R.id.editText_nicheng);
		edittext_mima   =(EditText)findViewById(R.id.editText_mima);
		
		button_zhuce.setOnClickListener(click);
		button_denglu.setOnClickListener(click);
		
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
			 
			case R.id.button_zhuce2:
				username = edittext_nicheng.getText().toString();
				if (username.equals("")){
					Toast.makeText(getApplicationContext(), "您还没有填写昵称！", 4000).show();
				}else{
					new Up1().execute();	
				}
				break;
			case R.id.button_denglu1:
				// creating new product in background thread
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
		 username = edittext_nicheng.getText().toString();
		 pwd = edittext_mima.getText().toString();
		 
		if (username.equals("")){
			DialogUtil.showDialog(this, "您还没有填写昵称", false);
			
			return false;
		}else if (pwd.equals("")){
			DialogUtil.showDialog(this, "您还没有填写密码", false);
			return false;
		}else if(pwd.length()<6){
			DialogUtil.showDialog(this,"密码长度必须不少于六位！",false);
			return false;
		}else{
			return true;
		}
	}
	
	class Up extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Denglu.this);
			pDialog.setMessage("正在登录..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
 
		}
		
		 protected String doInBackground(String... args) {
			     
			 List<NameValuePair> data = new ArrayList<NameValuePair>();
			 data.add(new BasicNameValuePair("username", username));
			 data.add(new BasicNameValuePair("pwd", pwd));
			 
			 try{
				 json = jsonParser.makeHttpRequest(url_add,"POST", data);
				 System.out.println(data);
				 System.out.println(json);
				 return json;
			 }catch(Exception e){
				 e.printStackTrace(); 
				 return ""; 
			 }
		 }

		 protected void onPostExecute(String message) {  
			 pDialog.dismiss();
			//message 为接收doInbackground的返回值	 
			 if(message.equals("login_succeed"+"\n")||message.equals("login_succeed_inactive_member"+"\n")){
				 globe.username=username;
				 globe.pwd=pwd;
				//update contact  控制类型的选择
			        db1.open(); 
//			        if ( db1.updateContact(2, "ggm2", 2) )
//			        	System.out.println("Update sceess!");
//			        else 
//			        	System.out.println("Update failed!"); 
			      //update contact  用户名的保存  
			        System.out.println(globe.username);
			        if ( db1.updateContact(3, globe.username, 0) )
			        	System.out.println("username Update sceess!");
			        else 
			        	System.out.println("username Update failed!");
			      //update contact  用户的密码  
			        System.out.println(globe.pwd);
			        if ( db1.updateContact(4, globe.pwd, 0) )
			        	System.out.println("globe.pwd Update sceess!");
			        else 
			        	System.out.println("globe.pwd Update failed!");
			        db1.close();
			     
			        System.out.println(message);
			        
				 Toast.makeText(getApplicationContext(), "登录成功", 4000).show();
				 if(globe.label==0){
					 Intent intent2 = new Intent(Denglu.this,Leixing.class);
					 startActivity(intent2);
				 }else if(globe.label==3){
					 Intent intent3 = new Intent(Denglu.this,MainActivity1.class);
					 finish();
					 startActivity(intent3);
				 }
				 
				else{
					 Intent intent3 = new Intent(Denglu.this,MainActivity.class);
					 finish();
					 startActivity(intent3);
				 }
				  	 
					
			 }else if(message.equals("login_invalid"+"\n")){
				 System.out.println(message);
				 Toast.makeText(getApplicationContext(), "网络错误或用户名不存在或密码错误！", 4000).show();
			}else{
				 System.out.println(message);
				 Toast.makeText(getApplicationContext(), "登录失败！", 4000).show();
			}
		 }
	}
	
	
	class Up1 extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Denglu.this);
			pDialog.setMessage("正在获取..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
 
		}
		
		 protected String doInBackground(String... args) {
			     
			 List<NameValuePair> data = new ArrayList<NameValuePair>();
			 data.add(new BasicNameValuePair("username", username));
			 
			 try{
				 json1 = jsonParser.makeHttpRequest(url_add1,"POST", data);
				 System.out.println(data);
				 System.out.println(json1);
				 return json1;
			 }catch(Exception e){
				 e.printStackTrace(); 
				 return ""; 
			 }
		 }

		 protected void onPostExecute(String message) {  
			 pDialog.dismiss();
			//message 为接收doInbackground的返回值	 
			 if(message.equals("getpasswd_send_succeed"+"\n")){
				 Toast.makeText(getApplicationContext(), "密码重置已经发送到邮箱了", 4000).show();			
			 }else if(message.equals("getpasswd_account_notmatch"+"\n")){
				 System.out.println(message);
				 Toast.makeText(getApplicationContext(), "用户名和邮箱不匹配", 4000).show();
			}else{
				 System.out.println(message);
				 Toast.makeText(getApplicationContext(), "获取失败！", 4000).show();
			}
		 }
	}

}