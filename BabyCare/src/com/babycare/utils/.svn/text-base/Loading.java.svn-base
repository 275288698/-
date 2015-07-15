package com.babycare.utils;

import com.babycare.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Loading extends Dialog {
	private String loadingMsg = "ÕýÔÚ¼ÓÔØ...";
	
	public Loading(Context context) {
		super(context);
	}
	
	public Loading(Context context,int theme){
		super(context, theme);
	}
	
	public Loading(Context context,int theme,String msg){
		super(context, theme);
		
		this.loadingMsg = msg;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_loading_style);
		
		this.setCancelable(false);
		
		ProgressBar pb = (ProgressBar)findViewById(R.id.layout_loading_bar);
		pb.setVisibility(View.VISIBLE);
		
		TextView tv = (TextView) findViewById(R.id.layout_loading_text);
		tv.setText(loadingMsg);
		tv.setVisibility(View.VISIBLE);
	}
	
	@Override
    protected void onStop() {  
        //Log.d("TAG","+++++++++++++++++++++++++++");  
    }  
	
	
}
