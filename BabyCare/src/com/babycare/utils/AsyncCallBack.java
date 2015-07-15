package com.babycare.utils;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.babycare.R;
import com.loopj.android.http.JsonHttpResponseHandler;

public class AsyncCallBack extends JsonHttpResponseHandler{
	public final static String DOMAIN="http://120.25.212.225";
	
	private String TAG = AsyncCallBack.class.getSimpleName();
	
	private Loading loading = null;
	/**
	 * ������Ϣ
	 */
	private String loadingMsg = "";
	
	private Context context ;
	
	public AsyncCallBack(Context context){
		this.context = context;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		showLoadingProgressDialog();
	}
	
	/** ����������ʾ��Ϣ�����Ϊ������ʾ */
	public String getLoadingMsg() {
		return loadingMsg;
	}

	/** ͨ��ʧ�ܵĻص����� */
	@Override
	public void onFailure(int statusCode, Header[] headers,String responseString, Throwable throwable) {
		super.onFailure(statusCode, headers, responseString, throwable);
		
		ErrorCallBack.processError(context, statusCode, responseString, throwable);
	}
	
	/** ͨ��ʧ�ܵĻص����� */
	@Override
	public void onFailure(int statusCode, Header[] headers,Throwable throwable, JSONObject errorResponse) {
		super.onFailure(statusCode, headers, throwable, errorResponse);
		
		ErrorCallBack.processError(context, statusCode, errorResponse==null? "":errorResponse.toString(), throwable);
	}

	/** ͨ�ųɹ��Ļص�������������ϢΪ�ַ������ͣ��˴��ò��� */
	@Override
	public void onSuccess(int statusCode, Header[] headers,String responseString) {
		super.onSuccess(statusCode, headers, responseString);
		
		resultExecute(responseString);
		
		L.i(TAG, responseString);
	}

	/** ͨ�ųɹ��Ļص�������������ϢΪJSON��ʽ���˴��ò��� */
	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
		super.onSuccess(statusCode, headers, response);
		
		resultExecute(response);
		
		L.i(TAG, response.toString());
	}

	@Override
	public void onFinish() {
		super.onFinish();
		
		dismissProgressDialog();
	}
	
	private void resultExecute(String resultText) {
		if (!BasicTool.isNotEmpty(resultText)) {
			return;
		}
		
		JSONObject r = null;
		
		try {
			r = new JSONObject(resultText);
			
			resultExecute(r);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void resultExecute(JSONObject retObj) {
		if (null==retObj) {
			return ;
		}
		
		resultCallBack(retObj);
		//�������Ϊͳһ������Ҫ����Դ���
//		try {
//			if(retObj.getString("code").equals("TT")){
//				
//				ErrorCallBack.reLogin(context);
//			}
//			
//			if(retObj.getString("code").equals("000")){
//				resultCallBack(retObj);
//			}else {
//				ErrorCallBack.processExc(retObj.getString("code"), retObj.getString("message"), context);
//				
//				return ;
//			}
//			
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
	}
	
	
	/**
	 * ������Ҫ���Ǹ÷��������Լ����߼�����
	 * 
	 * @param result
	 */
	protected void resultCallBack(JSONObject result) {
		
	}
	
	private void showLoadingProgressDialog() {
		showProgressDialog(getLoadingMsg());
	}
	
	
	private void showProgressDialog(String message) {
		if (!BasicTool.isNotEmpty(message)) {
			return ;
		}
		
		loading = new Loading(context, R.style.loadingDialogTheme,message);
		
		LayoutParams params = loading.getWindow().getAttributes();
		
		((Activity)context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		params.alpha = 1.0f;// ͸����
		loading.getWindow().setAttributes(params);
		
		loading.show();
	}

	/** ���ؽ����� */
	private void dismissProgressDialog() {
		if (loading != null && loading.isShowing()) {
			loading.dismiss();
		}
	}

}

