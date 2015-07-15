package com.babycare;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.babycare.base.CustomTitleActivity;
import com.babycare.httpconnect.HttpRestClient;
import com.babycare.httpconnect.JsonHttpResponseHandler;
import com.babycare.utils.AsyncCallBack;
import com.babycare.utils.BasicTool;
import com.babycare.utils.ConnectUtil;
import com.babycare.utils.L;
import com.babycare.utils.T;
import com.babycare.utils.UserConfig;
import com.loopj.android.http.RequestParams;

/**
 * 
 * @Description: TODO()
 * @author ����
 * @date 2015-3-18 ����12:31:38
 * @version V1.0
 */
public class YQ_BindActivity extends CustomTitleActivity {
	private EditText cellphone;
	private String num;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_bind);
		super.onCreate(savedInstanceState);
		setTitle("�������ֱ�");
		setTitleBarLeftBtnText("������Ϣ");
		setTitleBarRightBtnText("");
	}

	@Override
	protected void initViews() {
		
		cellphone = (EditText) findViewById(R.id.cellphone);
		
	}

	@Override
	protected void initDatas() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void installListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void uninstallListeners() {
		// TODO Auto-generated method stub

	}

	public void onBtnClick(final View v) {
		switch (v.getId()) {
		
		
		case R.id.btn_confirm://������֤��
			num = cellphone.getText().toString();
		
			if (num.length()<6) {
				if (!BasicTool.isNetWork(YQ_BindActivity.this)) {
					T.showShort(YQ_BindActivity.this, "�����ֻ������Ƿ����ӣ�");
					return;
				}
				
				HttpRestClient.hasRegist(YQ_BindActivity.this, num, new JsonHttpResponseHandler(){
					private boolean hasRegist = false;
					@Override
					public void onSuccess(JSONObject response) {
						// TODO Auto-generated method stub
						super.onSuccess(response);
						try {
							switch (response.getInt("code")) {
							case 10701:
								break;
							default:
								hasRegist = true;
								break;
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}

					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						super.onFinish();
						if (hasRegist) {
							T.showShort(YQ_BindActivity.this, num+"�Ѿ��󶨹���!");
						}else {
							showAlerDialog(num);
						}
					}
					
					
					
				});
				
			
			}else {
				T.showShort(YQ_BindActivity.this, "�豸�ű������6λ��");
			}
			
			
			
			break;
		default:
			break;
		}
	}

	public void showAlerDialog(final String num) {
	
		
		View view = LayoutInflater.from(YQ_BindActivity.this).inflate(
				R.layout.dialog_register, null);
		TextView tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);
		TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
		TextView dialogContent = (TextView) view.findViewById(R.id.dialogContent);
		dialogContent.setText(dialogContent.getText().toString()+num);
		
		final Dialog mDeleteDialog = new Dialog(YQ_BindActivity.this, R.style.dialog);  
		mDeleteDialog.setContentView(view);  
		mDeleteDialog.show();  
		mDeleteDialog.getWindow().setGravity(Gravity.CENTER); 		
		
		tv_cancle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDeleteDialog.dismiss();

			}
		});
		tv_confirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDeleteDialog.dismiss();
				HttpPost_Captcha(num);
				
			}
		});

	}
	
	private void HttpPost_Captcha(String mobile) {
		RequestParams params = new RequestParams();
		
		params.put("mobile", mobile);
		String relativeUrl ="/auths/send_captcha";
//		ConnectUtil.getRequest(YQ_BindActivity.this, relativeUrl, params, new CaptchaTask(YQ_BindActivity.this));
	}
	
	private class CaptchaTask extends AsyncCallBack{

		public CaptchaTask(Context context) {
			super(context);
		}

		@Override//�����ʾ��������ʾ��Ϣ����
		public String getLoadingMsg() {
			return getString(R.string.submit_tip);
		}

		@Override
		protected void resultCallBack(JSONObject result) {
		
			try {
				switch (result.getInt("code")) {
				case 10000:
				
					T.showShort(YQ_BindActivity.this, "��֤�뷢�ͳɹ���");
					break;
				case 10101:
					T.showShort(YQ_BindActivity.this, "�ֻ���ʽ����");
					break;

				default:
					break;
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			

		}
	}
	
	
	
	
//		RequestParams params = new RequestParams();
//		
//		params.put("mobile", mobile);
//		params.put("captcha", captcha);
//		params.put("password", password);
//		params.put("device", device);
//		
//		String relativeUrl ="/auths/register";
//		
//		ConnectUtil.postRequest(YQ_RegisterActivity.this, relativeUrl, params, new NormalTask(YQ_RegisterActivity.this));
	}

