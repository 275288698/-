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
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Zhuce extends Activity{
	//对控件对象进行声明
	private Button zhucetijiao,xieyi;
	private EditText nicheng,youxiang,shouji,shurumima,querenmima;
	private CheckBox yuedu;
	
	private Boolean biaoqian=false;
	private String username,email,phonenumber,pwd,resecret;
	
	final DBAdapter db1 = new DBAdapter(this);
	
	private static String url_add1 ="http://www.ubabytv.com.cn/ubabytvapp/app_regiester.php";
	
//	private static String url_add1 ="http://www.liulinyan.com/app_api/appregiester.php";
//	private static final String TAG_MESSAGE = "message";
	
	private ProgressDialog pDialog;
	
	JSONParser jsonParser = new JSONParser();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhuce);
		
		MyAplication appKiller = (MyAplication) getApplication();
		appKiller.activities.add(this); 
		
		if(globe.x==1){	
			Zhuce.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
		}
		if(globe.x==2){
			Zhuce.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		//通过控件的ID来得到代表控件的对象
		xieyi=(Button)findViewById(R.id.xieyi);
		zhucetijiao=(Button)findViewById(R.id.zhucetijiao);
		yuedu=(CheckBox)findViewById(R.id.yuedu);
		
		nicheng    =(EditText)findViewById(R.id.nicheng);
		shurumima  =(EditText)findViewById(R.id.shurumima);
		querenmima =(EditText)findViewById(R.id.querenmima);
		youxiang   =(EditText)findViewById(R.id.youxiang);
		shouji     =(EditText)findViewById(R.id.shouji);
		
		int MAX_TEXT_INPUT_LENGTH=11;

		shouji.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_TEXT_INPUT_LENGTH)});
		
		xieyi.setOnClickListener(click);
		zhucetijiao.setOnClickListener(click);
		
		//给CheckBox设置事件监听
		yuedu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
		    @Override
		public void onCheckedChanged(CompoundButton buttonView,
		boolean isChecked) {
		    	// TODO Auto-generated method stub
		    	if(isChecked){
		    		biaoqian = true;
		    	}else{
		    		biaoqian = false;
		    	}
		    }
		});

		
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
			
			case R.id.xieyi:
				Intent intent = new Intent(Zhuce.this,Xieyi.class);
				startActivity(intent);
				break;
			case R.id.zhucetijiao:	
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
		 username = nicheng.getText().toString();
		 email = youxiang.getText().toString();
		 phonenumber = shouji.getText().toString();
		 pwd = shurumima.getText().toString();
		 resecret = querenmima.getText().toString();	

		if (username.equals("")){
			DialogUtil.showDialog(this, "您还没有填写昵称！", false);
			return false;
		}else if (email.equals("")){
			DialogUtil.showDialog(this, "您还没有填写邮箱！", false);
			return false;
		}else if (phonenumber.equals("")){
			DialogUtil.showDialog(this, "您还没有填写手机！", false);
			return false;
		}else if (pwd.equals("")){
			DialogUtil.showDialog(this, "您还没有填写密码！", false);
			return false;
		}else if (resecret.equals("")){
			DialogUtil.showDialog(this, "您还没有填写重复密码！", false);
			return false;
		}else if (!email.matches("\\w+@\\w+\\.\\w+")){
			DialogUtil.showDialog(this, "您邮箱输入错误！", false);
			return false;
		}else if(phonenumber.length()!=11){	
			DialogUtil.showDialog(this,"您手机号码不正确！",false);
			return false;
		}else if(pwd.length()<6){
			DialogUtil.showDialog(this,"密码长度必须不少于六位！",false);
			return false;
		}else if (!(pwd.equals(resecret))){
			DialogUtil.showDialog(this, "密码与重复密码不一致！", false);
			return false;
		}else if (!biaoqian){
			DialogUtil.showDialog(this, "请点击已阅读并同意《用户协议》！", false);
			return false;
		}else {
			return true;
		}
	}
	
	class Up extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Zhuce.this);
			pDialog.setMessage("正在注册..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		
		 protected String doInBackground(String... args) {			 
			     
			 List<NameValuePair> data = new ArrayList<NameValuePair>();
			 data.add(new BasicNameValuePair("username", username));
			 data.add(new BasicNameValuePair("pwd", pwd));
			 data.add(new BasicNameValuePair("email", email));
			 data.add(new BasicNameValuePair("tel", phonenumber));
			 
			 
			 try{
				 System.out.println(data);
//				 JSONObject json = jsonParser.makeHttpRequest(url_add1,"POST", data);
				 String json = jsonParser.makeHttpRequest(url_add1,"POST", data);
//				 System.out.println(data);
//				 String message = json.getString(TAG_MESSAGE);
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
			 if(message.equals("register_succeed"+"\n")){
				 
				 globe.username=username;
				 globe.pwd=pwd;

			        db1.open(); 
			        //update contact  用户名的保存  
			        if ( db1.updateContact(3, globe.username, 0) )
			        	System.out.println("username Update sceess!");
			        else 
			        	System.out.println("username Update failed!");
			      //update contact  用户的密码  
			        if ( db1.updateContact(4, globe.pwd, 0) )
			        	System.out.println("globe.pwd Update sceess!");
			        else 
			        	System.out.println("globe.pwd Update failed!");
			        db1.close();
			       
				 Toast.makeText(getApplicationContext(), "注册成功!", 20).show();
				
				 if(globe.label==0){
					 Intent intent1 = new Intent(Zhuce.this,Leixing.class);
					 startActivity(intent1);
					 System.out.println("100");
				 }else if(globe.label==3){
					 Intent intent3 = new Intent(Zhuce.this,MainActivity1.class);
					 finish();
					 startActivity(intent3);
					 System.out.println("103");
				 }else{
					 Intent intent2 = new Intent(Zhuce.this,MainActivity.class);
					 finish();
					 startActivity(intent2);
					 System.out.println("102");
				 }			        
			}else if(message.equals("profile_username_tooshort"+"\n")){
				Toast.makeText(getApplicationContext(), "用户名小于3个字符！", 20).show();
			}else if(message.equals("profile_username_toolong"+"\n")){
				Toast.makeText(getApplicationContext(), "用户名超过15个字符！", 20).show();
			}else if(message.equals("profile_username_duplicate"+"\n")){
				Toast.makeText(getApplicationContext(), "该用户名已经被注册！", 20).show();
			}else if(message.equals("profile_passwd_illegal"+"\n")){
				Toast.makeText(getApplicationContext(), "密码名包含敏感字符！", 20).show();
			}else if(message.equals("profile_username_illegal"+"\n")){
				Toast.makeText(getApplicationContext(), "用户名包含敏感字符！", 20).show();
			}else if(message.equals("profile_username_protect"+"\n")){
				Toast.makeText(getApplicationContext(), "用户名保护被系统屏蔽的字符！", 20).show();
			}else if(message.equals("profile_email_illegal"+"\n")){
				Toast.makeText(getApplicationContext(), "email地址无效！", 20).show();
			}else if(message.equals("profile_email_domain_illegal"+"\n")){
				Toast.makeText(getApplicationContext(), "Email 包含不可使用的邮箱域名！", 20).show();
			}else if(message.equals("profile_email_duplicate"+"\n")){
				Toast.makeText(getApplicationContext(), "Email 地址已经被注册！", 20).show();
			}else{
				Toast.makeText(getApplicationContext(), "注册失败！", 20).show();
			}
		 }
	}

}
