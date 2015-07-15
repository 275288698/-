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
				T.showShort(context, "�������쳣�����Ժ����ԣ�");
				break;
			case HttpStatus.SC_NOT_FOUND :
				T.showShort(context, "��Ч��������Դ��");
				break;
			case HttpStatus.SC_BAD_REQUEST :
				T.showShort(context, "�����������");
				break;
			case HttpStatus.SC_FORBIDDEN :
				T.showShort(context, "�Բ�����û����ز���Ȩ�ޣ�");
				break;
			case 10000 :  //������û���Ϣ����
				T.showShort(context, "������û���Ϣ���ڣ������µ�¼��");
				//reLogin(activity);
				break;
			case 10001 :  //û�в���Ȩ��
				T.showShort(context, "�Բ�����û����ز���Ȩ�ޣ�");
				break;
			default : 
				T.showShort(context, "δ֪����"+responseString);
				break;
			}
			
		}
	}
	
	public static void processExc(String code, String msc,Context context){
		T.showShort(context, msc+", ������:"+code);
	}
	
	public static void reLogin(final Context context){
		if(null==context)
			return ;
		
		new AlertDialog.Builder(context)
		.setTitle("��ܰ��ʾ")
		.setMessage("�ػ���ʱ������Ҫ���µ�¼��")
		.setPositiveButton("ȷ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				/*Intent intent = new Intent(activity, LoginActivity.class);
				activity.startActivity(intent);*/
				
			}
		}).show();
		
		
	}
}