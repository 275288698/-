package generalplus.com.BabudouAssistant;

import generalplus.com.GPComAir5Lib.JSONParser;
import generalplus.com.GPComAir5Lib.JSONParser1;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.babycare.R;
import com.babycare.app.MyAplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

  
public class Xiugai extends Activity{
	
	private Button button15,button16,button17;
	private EditText edittext_username,edittext_pwd,edittext_email,edittext_phonenumber;
	private String username,email,phonenumber,pwd,id;
	private String json1,json3;
	private static String url_add ="http://www.ubabytv.com.cn/ubabytvapp/app_login.php";
	private static String url_add1 ="http://www.ubabytv.com.cn/ubabytvapp/app_getuserinfo.php"; 
	private static String url_add2 ="http://www.ubabytv.com.cn/ubabytvapp/app_changeuserinfo.php";
	private ProgressDialog pDialog;	
	JSONParser jsonParser = new JSONParser();
	JSONParser1 jsonParser1 = new JSONParser1();	
	private static final String TAG_MESSAGE = "message";
	
	final DBAdapter db1 = new DBAdapter(this);
	
	private JSONObject hay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);;
		setContentView(R.layout.xiugai);
		
		MyAplication appKiller = (MyAplication) getApplication();
		appKiller.activities.add(this); 
		
		if(globe.x==1){	
			Xiugai.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
		}
		if(globe.x==2){
			Xiugai.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		edittext_username   =(EditText)findViewById(R.id.TextView01);
		edittext_pwd        =(EditText)findViewById(R.id.TextView04);
		
		edittext_email      =(EditText)findViewById(R.id.TextView05);
		edittext_phonenumber=(EditText)findViewById(R.id.TextView07);
//		edittext_pwd1        =(EditText)findViewById(R.id.TextView09);
//		edittext_pwd2        =(EditText)findViewById(R.id.TextView11);
		
		button15=(Button)findViewById(R.id.button15);
		button16=(Button)findViewById(R.id.button16);
		button17=(Button)findViewById(R.id.button17);
		
		button15.setOnClickListener(click);
		button16.setOnClickListener(click);
		button17.setOnClickListener(click);
		
		

	} 
		
		
	public OnClickListener click =new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
				
			switch(v.getId()){
			case R.id.button16:
				if(validate()){
					new Up().execute();	
					
				}
				break;	
			case R.id.button17:
				if(validate2()){
					new Up2().execute();	
					System.out.println("11666");
				}
				break;			
			case R.id.button15:
				finish();
				break;
			default:
				break;
			}
		}
	};
	
	//第一次登录连接（用户名+密码）
	private boolean validate()
	{
		username = edittext_username.getText().toString();
		 pwd = edittext_pwd.getText().toString();
		 
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
			pDialog = new ProgressDialog(Xiugai.this);
			pDialog.setMessage("正在提交..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		 protected String doInBackground(String... args) {
			 
			 List<NameValuePair> data = new ArrayList<NameValuePair>();
			 data.add(new BasicNameValuePair("username", username));
			 data.add(new BasicNameValuePair("pwd", pwd));
			 try{
				 json1 = jsonParser.makeHttpRequest(url_add,"POST", data);
				 return json1;
			 }catch(Exception e){
				 e.printStackTrace(); 
				 return ""; 
			 }
		 }
		
		 protected void onPostExecute(String json1) {  
			 pDialog.dismiss();
			 
			 if(json1.equals("login_succeed"+"\n")||json1.equals("login_succeed_inactive_member"+"\n")){
				 globe.username=username;
				 globe.pwd=pwd;
				 
			        db1.open(); 
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
			        
			        new Up1().execute();
			        
			     System.out.println(json1);	        
				 Toast.makeText(getApplicationContext(), "登录成功", 4000).show();
				  	 	
			 }else{
				 System.out.println(json1);
				 Toast.makeText(getApplicationContext(), "登录失败！", 4000).show();
			}
		 }
	}	
	
	//第二次连接（用户名）
	class Up1 extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Xiugai.this);
			pDialog.setMessage("正在查看..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
 

		protected String doInBackground(String... args) {
			  
			 List<NameValuePair> data = new ArrayList<NameValuePair>();
			 data.add(new BasicNameValuePair("username", username));
			 try{
				 JSONObject json = jsonParser1.makeHttpRequest(url_add1,"POST", data);
				
				 System.out.println(json);
				 
				 //解析json
		            id = json.getString("uid");
		            username = json.getString("username");
		            email = json.getString("email");
		            phonenumber = json.getString("mobile");
		            
		            System.out.println(id);
		            System.out.println(username);
		            System.out.println(email);
		            System.out.println(phonenumber);
		            // displaying all data in textview
				 
				 String message = json.toString();
				 System.out.println(message);
				 return message;
			 }catch(Exception e){
				 e.printStackTrace(); 
				 return ""; 
			 }
		 }
		
		 protected void onPostExecute(String message) {  
			 pDialog.dismiss();

			 edittext_username.setText(username);
	         edittext_email.setText(email);
	         edittext_phonenumber.setText(phonenumber);	

			//message 为接收doInbackground的返回值
			 if(message==null){
				 Toast.makeText(getApplicationContext(), "查看失败", 8000).show();
			 }
		 }
	}
	//第三次连接(id+手机+邮箱)
	private boolean validate2()
	{
		username = edittext_username.getText().toString();
		 email = edittext_email.getText().toString();
		 phonenumber = edittext_phonenumber.getText().toString();
		 pwd = edittext_pwd.getText().toString();

		if (username.equals("")){
			DialogUtil.showDialog(this, "您还没有填写昵称！", false);
			return false;
		}else if (email.equals("")){
			DialogUtil.showDialog(this, "您还没有填写邮箱！", false);
			return false;
		}
//		else if (phonenumber.equals("")){
//			DialogUtil.showDialog(this, "您还没有填写手机！", false);
//			return false;
//		}

		else if (!email.matches("\\w+@\\w+\\.\\w+")){
			DialogUtil.showDialog(this, "您邮箱输入错误！", false);
			return false;
		}else if(phonenumber.length()!=11){	
			DialogUtil.showDialog(this,"您手机号码不正确！",false);
			return false;
		}
//		else if(pwd.length()<6){
//			DialogUtil.showDialog(this,"密码长度必须不少于六位！",false);
//			return false;
//		}else if(pwd1.length()<6){
//			DialogUtil.showDialog(this,"新密码长度必须不少于六位！",false);
//			return false;
//		}else if (!(pwd1.equals(pwd2))){
//			DialogUtil.showDialog(this, "新密码与重复密码不一致！", false);
//			return false;
//		}
		else {
			return true;
		}
	}
	
	class Up2 extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Xiugai.this);
			pDialog.setMessage("正在修改..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}


		protected String doInBackground(String... args) {
			 
			 List<NameValuePair> data = new ArrayList<NameValuePair>();
			 data.add(new BasicNameValuePair("id", id));
			 data.add(new BasicNameValuePair("tel", phonenumber));
			 data.add(new BasicNameValuePair("email", email));
			 try{
				 json3 = jsonParser.makeHttpRequest(url_add2,"POST", data);
				 System.out.println(data);
				 System.out.println(json3);
				 return json3;
			 }catch(Exception e){
				 e.printStackTrace(); 
				 return ""; 
			 }
		 }
		
		 protected void onPostExecute(String json3) {  
			 pDialog.dismiss();
			 
			 if(json3.equals("update_success"+"\n")){		 		        			        
			        System.out.println(json3);    
				 Toast.makeText(getApplicationContext(), "修改成功！", 4000).show();
				  	 	
			 }else if(json3.equals("email is exit"+"\n")){		 		        			        
			        System.out.println(json3);    
				 Toast.makeText(getApplicationContext(), "修改失败，该邮箱已被其他用户使用了！", 4000).show();
				  	 	
			 }else{
				 System.out.println(json3);
				 Toast.makeText(getApplicationContext(), "修改失败！", 4000).show();
			}
		 }
	}
	
	@Override
	protected void onDestroy() {
		MyAplication app = (MyAplication) getApplication();
		app.activities.remove(this);
		super.onDestroy();
	}

}
