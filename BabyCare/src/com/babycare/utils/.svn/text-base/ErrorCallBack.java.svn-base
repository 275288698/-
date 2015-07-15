package com.babycare.utils;

import org.apache.http.HttpStatus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class ErrorCallBack {
	public static void processError(Context context,int statusCode, String responseString, Throwable throwable){
		if(null != context){
			switch(statusCode){
			case HttpStatus.SC_INTERNAL_SERVER_ERROR :
				T.showShort(context, "服务器异常，请稍后再试！");
				break;
			case HttpStatus.SC_NOT_FOUND :
				T.showShort(context, "无效的请求资源！");
				break;
			case HttpStatus.SC_BAD_REQUEST :
				T.showShort(context, "请求参数有误！");
				break;
			case HttpStatus.SC_FORBIDDEN :
				T.showShort(context, "对不起，您没有相关操作权限！");
				break;
			case 10000 :  //保存的用户信息过期
				T.showShort(context, "保存的用户信息过期，请重新登录！");
				//reLogin(activity);
				break;
			case 10001 :  //没有操作权限
				T.showShort(context, "对不起，您没有相关操作权限！");
				break;
			default : 
				T.showShort(context, "未知错误："+responseString);
				break;
			}
			
		}
	}
	
	public static void processExc(String code, String msc,Context context){
		T.showShort(context, msc+", 错误码:"+code);
	}
	
	public static void reLogin(final Context context){
		if(null==context)
			return ;
		
		new AlertDialog.Builder(context)
		.setTitle("温馨提示")
		.setMessage("回话超时，您需要重新登录！")
		.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				/*Intent intent = new Intent(activity, LoginActivity.class);
				activity.startActivity(intent);*/
				
			}
		}).show();
		
		
	}
}