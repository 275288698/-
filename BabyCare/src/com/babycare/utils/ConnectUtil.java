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
	
	/** ��ʱʱ��  */
	private static final int TIME_OUT = 60 * 1000;
	/** ������Ӵ���  */
	private static final int MAX_CONNECTIONS = 2;
	
	private static AsyncHttpClient client = new AsyncHttpClient();

	static {
		client.setTimeout(TIME_OUT);
		
		client.setMaxConnections(MAX_CONNECTIONS);
	}

	/**
	 * ����Post����,��Json��ʽ��������
	 * @param context	�����Ķ���
	 * @param relativeUrl	���·��
	 * @param params	���ݵĲ���������Ϊnull
	 * @param asyncHttpResponseHandler	�ص�����
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
	 * @param context �����Ķ���
	 * @param relativeUrl ���·��
	 * @param params ����
	 * @param asyncHttpResponseHandler �ص�����
	 */
	public static void getRequest(Context context,String relativeUrl, RequestParams params,JsonHttpResponseHandler asyncHttpResponseHandler){
		client.get(getAbsoluteUrl(context,relativeUrl), addExtra(params), asyncHttpResponseHandler);
		
		Log.i(TAG,relativeUrl+params.toString());
	}
	/**
	 * ��Ӷ����ֶ�
	 * @param params	������������Ҫ���ֶ�
	 * @return
	 */
	private static RequestParams addExtra(RequestParams params){
		if (null == params) {
			params = new RequestParams();
		}
		//�������й̶���������д��û�пɺ���
//		params.put("token", DetectTool.getToken());
//		params.put("version", DetectTool.getVersionName());
//		params.put("mn", DetectTool.getType());
		
		
		return params;
	}

	/**
	 * �������·����ȡȫ·��
	 * @param context	�����Ķ���
	 * @param relativeUrl	���·��
	 * @return
	 */
	private static String getAbsoluteUrl(Context context,String relativeUrl) {
		if(!relativeUrl.endsWith("?"))
			relativeUrl+="?";
			
		String mainHostUrl = AsyncCallBack.DOMAIN;//���д����
//		mainHostUrl = context.getString(R.string.mainHostUrl);
		
		return mainHostUrl + relativeUrl;
	}
}
