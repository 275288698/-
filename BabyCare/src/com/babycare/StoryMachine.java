package com.babycare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.babycare.base.CustomTitleActivity;

public class StoryMachine extends CustomTitleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.story_machine);
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void initViews() {
		setTitle("¹ÊÊÂ»ú");
		setTitleBarRightBtnText(null);
		
	}

	@Override
	protected void initDatas() {
		
	}

	@Override
	protected void installListeners() {

	}

	@Override
	protected void uninstallListeners() {

	}
	
	public void chooseMachine(View view){
		startActivityForResult(new Intent(this,JoinFacility.class),1);
	}
	
	public void onBtnClickyinliang(View view){
		startActivityForResult(new Intent(this,YQ_VolumeChange_Activity.class),2);
	}
	public void onBtnClickstory(View view){
		startActivityForResult(new Intent(this,StoryMachineStory.class),3);
	}
	public void onBtnClickmode(View view){
		startActivityForResult(new Intent(this,StoryMachineMode.class),4);
	}
	
}
