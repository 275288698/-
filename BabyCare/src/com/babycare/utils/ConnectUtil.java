package com.babycare.utils;

import android.R;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ConnectUtil {
	private static final String TAG = ConnectUtil.class.getSimpleName();
	
	/** 超时时间  */
	private static final int TIME_OUT = 60 * 1000;
	/** 最多连接次数  */
	private static final int MAX_CONNECTIONS = 2;
	
	private static AsyncHttpClient client = new AsyncHttpClient();

	static {
		client.setTimeout(TIME_OUT);
		
		client.setMaxConnections(MAX_CONNECTIONS);
	}

	/**
	 * 发送Post请求,以Json形式返回数据
	 * @param context	上下文对象
	 * @param relativeUrl	相对路径
	 * @param params	传递的参数，可以为null
	 * @param asyncHttpResponseHandler	回调函数
	 */
	public static void postRequest(Context context,String relativeUrl, RequestParams params,JsonHttpResponseHandler asyncHttpResponseHandler) {
		client.post(getAbsoluteUrl(context,relativeUrl), addExtra(params),asyncHttpResponseHandler);
		
		
		Log.i(TAG, getAbsoluteUrl(context,relativeUrl)+addExtra(params).toString());
	}
	
	public static void originalRequest(String relativeUrl, RequestParams params,AsyncHttpResponseHandler asyncHttpResponseHandler) {
		client.post(relativeUrl, params,asyncHttpResponseHandler);
		
		Log.i(TAG,relativeUrl+params.toString());
	}
	
	/**
	 * @param context 上下文对象
	 * @param relativeUrl 相对路径
	 * @param params 参数
	 * @param asyncHttpResponseHandler 回调函数
	 */
	public static void getRequest(Context context,String relativeUrl, RequestParams params,JsonHttpResponseHandler asyncHttpResponseHandler){
		client.get(getAbsoluteUrl(context,relativeUrl), addExtra(params), asyncHttpResponseHandler);
		
		Log.i(TAG,relativeUrl+params.toString());
	}
	/**
	 * 添加额外字段
	 * @param params	发送请求所需要的字段
	 * @return
	 */
	private static RequestParams addExtra(RequestParams params){
		if (null == params) {
			params = new RequestParams();
		}
		//这块如果有固定参数则填写，没有可忽略
//		params.put("token", DetectTool.getToken());
//		params.put("version", DetectTool.getVersionName());
//		params.put("mn", DetectTool.getType());
		
		
		return params;
	}

	/**
	 * 根据相对路径获取全路径
	 * @param context	上下文对象
	 * @param relativeUrl	相对路径
	 * @return
	 */
	private static String getAbsoluteUrl(Context context,String relativeUrl) {
		if(!relativeUrl.endsWith("?"))
			relativeUrl+="?";
			
		String mainHostUrl = AsyncCallBack.DOMAIN;//这块写域名
//		mainHostUrl = context.getString(R.string.mainHostUrl);
		
		return mainHostUrl + relativeUrl;
	}
}
