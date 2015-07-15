package generalplus.com.BabudouAssistant;

import generalplus.com.GPComAir5Lib.JSONParser;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.babycare.R;
import com.babycare.app.MyAplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Login extends Activity {

	private Button zhuce,denglu,tiaoguo;
	private ProgressDialog pDialog;
	private Thread newThread; //声明�?��子线�?
	private String json;
	private static String url_add ="http://www.ubabytv.com.cn/ubabytvapp/app_login.php";
	
	JSONParser jsonParser = new JSONParser();
	final DBAdapter db2 = new DBAdapter(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		DBAdapter db2 = new DBAdapter(this);
  
		//add a contact   
        db2.open(); 
        //�?��第一次插入语�?�?，第�?��用于 记录产品型号；第二个用于待定；第三个用于记录用户名；第四个用于记录密码�?
        //如有�?��插入语句请及时将其删除，不然用户不断�?��再登录会使数据库数据很大�?
        long id = db2.insertContact("", 0); 
             id = db2.insertContact("", 0);
        	   id = db2.insertContact("", 0);
        	   id = db2.insertContact("", 0);
        //在开发�?测试完之后将这四个语句修改成原始状�?
//        if ( db2.updateContact(1, "", 0) )
//        	System.out.println("Ubbie Update sceess!");
//        else 
//        	System.out.println("Ubbie Update failed!");
//        if ( db2.updateContact(2, "", 0) )
//        	System.out.println("Ubbie Update sceess!");
//        else 
//        	System.out.println("Ubbie Update failed!");
//        if ( db2.updateContact(3, "", 0) )
//        	System.out.println("Ubbie Update sceess!");
//        else    
//        	System.out.println("Ubbie Update failed!");
//        if ( db2.updateContact(4, "", 0) )
//        	System.out.println("Ubbie Update sceess!");
//        else 
//        	System.out.println("Ubbie Update failed!");
        
        //delete a particular contact删除再次插入的语�? 
        int i=5;
        do{
        	db2.deleteContact(i);
//        	if (db2.deleteContact(i))  
//       		System.out.println("Delete successful!"); 
//        	else  
//        		System.out.println("Delete failed!");  
        	i++;
        }while(i<1000);
   
 
        //get a contact获取每个语句中所�?��数据
        Cursor c = db2.getContact(1);  
        if (c.moveToFirst()) 
            DisplayContact(c); 
        else 
        	System.out.println("No contact found!");
      
      	
      	Cursor c2 = db2.getContact(2);  
        if (c2.moveToFirst()) 
            DisplayContact2(c2);
        else 
        	System.out.println("No contact found!");
        
        Cursor c3 = db2.getContact(3);  
        if (c3.moveToFirst()) 
            DisplayContact3(c3); 
        else 
        	System.out.println("No contact found!");
        
        Cursor c4 = db2.getContact(4);  
        if (c4.moveToFirst()) 
            DisplayContact4(c4); 
        else 
        	System.out.println("No contact found!");
        db2.close();
		
        //在自动登录时 
    	if(!globe.username.equals("")){
    		//自动登录	
    		new Up().execute();	
		}  
        
        
    	MyAplication appKiller = (MyAplication) getApplication();
		appKiller.activities.add(this); 
		
		zhuce=(Button)findViewById(R.id.zhuce);
		denglu=(Button)findViewById(R.id.denglu);
		tiaoguo=(Button)findViewById(R.id.tiaoguo);
		
		zhuce.setOnClickListener(click);
		denglu.setOnClickListener(click);
		tiaoguo.setOnClickListener(click);
        
      	Login.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      	
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
				
				case R.id.zhuce:
					
					Intent intent1 = new Intent(Login.this,Zhuce.class);
					startActivity(intent1);
					break;
				case R.id.denglu:
					Intent intent2 = new Intent(Login.this,Denglu.class);
					startActivity(intent2); 
					break;
				case R.id.tiaoguo:
					if(globe.label==0){
						Intent intent3 = new Intent(Login.this,Leixing.class);
						startActivity(intent3);
					}else if(globe.label==3){			        
//				        tiaoguo.setClickable(false);//关闭跳过的按�?
						Intent intent3 = new Intent(Login.this,MainActivity1.class);
						finish();
						startActivity(intent3);
						
				
						
					}else{
						Intent intent4 = new Intent(Login.this,MainActivity.class);
						finish();
						startActivity(intent4);
					}
					break;
				default:					
					break;
				
				}
			}
			
		};
		
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {// handler接收到消息后就会执行此方�?
				pDialog.dismiss();// 关闭ProgressDialog
			}
		};
		
		public void DisplayContact(Cursor c)  //获取判断控制型号的参�?
	    {  
	        globe.label=c.getInt(2);
//	        System.out.println(c.getString(0));
//	        System.out.println(c.getString(1));
//	        System.out.println(c.getString(2));
//	        System.out.println(globe.label);        
	    }
		
		public void DisplayContact2(Cursor c2)  //获取收藏夹中的xml文件内容
	    {  
			globe.json=c2.getString(1);
//	        globe.label2=c2.getInt(2);
//	        System.out.println(c2.getString(0));
//	        System.out.println(c2.getString(1));
//	        System.out.println(c2.getString(2));
//	        System.out.println(globe.label2);
	    }
		public void DisplayContact3(Cursor c3)  //获取用户名并保存
	    {  
	        globe.username=c3.getString(1);
//	        System.out.println(c3.getString(0));
//	        System.out.println(c3.getString(1));
//	        System.out.println(c3.getString(2));
//	        System.out.println(globe.username);
	    }
		public void DisplayContact4(Cursor c4)  //获取密码并保�?
	    {  
	        globe.pwd=c4.getString(1);
//	        System.out.println(c4.getString(0));
//	        System.out.println(c4.getString(1));
//	        System.out.println(c4.getString(2));
//	        System.out.println(globe.pwd);
	    }
		
		class Up extends AsyncTask<String, String, String> {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(Login.this);
				pDialog.setMessage("自动登录..");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
	 
			}
			
			 protected String doInBackground(String... args) {
				     
				 List<NameValuePair> data = new ArrayList<NameValuePair>();
				 data.add(new BasicNameValuePair("username", globe.username));
				 data.add(new BasicNameValuePair("pwd", globe.pwd));
				 
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
				//message 为接收doInbackground的返回�?	 
				 if(message.equals("login_succeed"+"\n")||message.equals("login_succeed_inactive_member"+"\n")){		     
				     System.out.println(message);				        
					 Toast.makeText(getApplicationContext(), "登录成功", 4000).show();	  						
				 }else if(message.equals("login_invalid"+"\n")){
					 System.out.println(message);
					 Toast.makeText(getApplicationContext(), "网络错误或用户名不存在或密码错误", 4000).show();
				 }else{
					 System.out.println(message);
					 Toast.makeText(getApplicationContext(), "登录失败", 4000).show();
				 }
				 
				//跳到上一次�?好的型号�?��制的发码界面
				if(globe.label==0){
					Intent intent3 = new Intent(Login.this,Leixing.class);
					startActivity(intent3);
				}else if(globe.label==3){
					Intent intent3 = new Intent(Login.this,MainActivity1.class);
					startActivity(intent3);
					Toast.makeText(getApplicationContext(), "正在解析文件", 4000).show();
				}else{
					Intent intent4 = new Intent(Login.this,MainActivity.class);
					startActivity(intent4);
					Toast.makeText(getApplicationContext(), "正在解析文件", 4000).show();
				}
 
				
			 }
		}

}
