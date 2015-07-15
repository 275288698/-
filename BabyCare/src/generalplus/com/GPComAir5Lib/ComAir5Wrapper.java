package generalplus.com.GPComAir5Lib;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;

public class ComAir5Wrapper {

	private static String DBGTag = "GPComAir5Wrapper";
	public int i32SampleRate = 48000;
	
	public enum eComAirErrCode
	{
		COMAIR5_NOERR(0),
		COMAIR5_AUDIONOTINIT(1),
		COMAIR5_AUIDOUINTFAILED(2),
		COMAIR5_ENABLEIORECFAILED(3),
		COMAIR5_PROPERTYNOTFOUND(4),
		COMAIR5_PROPERTYOPERATIONFAILED(5),
		COMAIR5_PROPERTYSIZEMISSMATCH(6),
		COMAIR5_PLAYCOMAIR5SOUNDFAILED(7);
		private final int val;
		private eComAirErrCode(int v) { val = v; }
	};
	
//	public enum eComAir5PropertyTarget
//	{
//		eComAir5PropertyTarget_Encode(0),
//		eComAir5PropertyTarget_Decode(1);
//		private final int val;
//		private eComAir5PropertyTarget(int v) { val = v; }
//	};
	
//	public enum eComAir5Property
//	{
//		eComAir5Property_RegCode(0),		//Encode/Decode,Set only,String
//		eComAir5Property_CentralFreq(1),	//Encode/Decode,Set/Get,Int
//		eComAir5Property_iDfValue(2),		//Encode/Decode,Set/Get,Int
//		eComAir5Property_SampleRate(3),		//Encode/Decode,Set,Function Pointer
//		eComAir5Property_SaveRawData(4);	//Decode,Set,Function Pointer
//		private final int val;
//		private eComAir5Property(int v) { val = v; }
//	};

	// Native Library Start -------
	static {
		try {
			Log.i(DBGTag, "Trying to load GPComAir5Lib.so ...");
			System.loadLibrary("GPComAir5Lib");
		} catch (UnsatisfiedLinkError Ule) {
			Log.e(DBGTag, "Cannot load GPComAir5Lib.so ...");
			Ule.printStackTrace();
		} finally {
		}
	}
	private static native int InitComAir5();
	private static native int UnitComAir5();
	private static native int StartComAir5Decode();
	private static native int StopComAir5Decode();
	private static native int ComAir5Decode(short[] shBuffer);
	public native int SetComAir5Property(int eTarget, int eProperty, Object ObjectValue);
	public native int GetComAir5Property(int eTarget, int eProperty);
	private static native byte[] PlayComAir5Cmd(int i32command, float SoundValume);
	// Native Library End -------
	
	//===============================================================================
	// Constructor & Listener
	//===============================================================================
	public void SetDisplayCommandValueHelper(DisplayCommandValueHelper helper) 
	{
		  ComAir5Wrapper.displayCmdHelper = helper;
	}
	private static DisplayCommandValueHelper displayCmdHelper;

	public static abstract class DisplayCommandValueHelper
	{
		public abstract void getCommand(int count, int cmdValue);
	}
	
	
	public class CSVAnalysis {  
	    private InputStreamReader fr = null;  
	    private BufferedReader br = null;  
	  
	    public CSVAnalysis(String f) throws IOException {  
	        fr = new InputStreamReader(new FileInputStream(f));  
	    }  
	
	
	}
	//===============================================================================
	// CALLBACK Function MUST Exist, DO NOT ALTER API
	//===============================================================================
	int decodedCmdValue = -1;
	private static int m_i32CommnadCnt = 0;
	public void CommandDecoded(int cmdValue)
	{
		decodedCmdValue = cmdValue;
		if (displayCmdHelper!=null)
			displayCmdHelper.getCommand(m_i32CommnadCnt, decodedCmdValue);
		m_i32CommnadCnt++;
	}
	
	RecordTask recorder;
	public int StartComAir5DecodeProcess()
	{
		int iResult = InitComAir5();
		iResult = StartComAir5Decode();
		recorder = new RecordTask();// must new an instance, one task can be executed only once
		recorder.execute();
		return iResult;
	}
	
	public int StopComAir5DecodeProcess()
	{
		recorder.setRecording(false);
		int iResult = StopComAir5Decode();
		iResult = UnitComAir5();		
		m_i32CommnadCnt = 0;
		return iResult;
	}
	
	class RecordTask extends AsyncTask<Void, Integer, Void> {
		private AudioRecord audioRecord = null;
		private int sampleRateInHz = i32SampleRate;
		private int channelConfig = AudioFormat.CHANNEL_IN_MONO;
		private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
		private int bufferSize, bufferRead;
		private short[] buffer;
		private boolean isRecording = false;
		
		@Override
		protected Void doInBackground(Void... params) {// Done in worker thread
			try {
			    // startRecording
				audioRecord.startRecording();
				while (isRecording)
				{
					bufferRead = audioRecord.read(buffer, 0, bufferSize);
			    	if (bufferRead>0) {
			    		short[] tmp = new short[bufferRead];
			    		System.arraycopy(buffer, 0, tmp, 0, bufferRead);
			    		
			    		int dec = ComAir5Decode(tmp);
			    		if (dec!=-1)
			    			publishProgress(dec);
			    	}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public void setRecording(boolean brec) {
		    this.isRecording = brec;
		    if (!isRecording && audioRecord.getState()==AudioRecord.RECORDSTATE_RECORDING )
		    	audioRecord.stop();
		}

		protected void onPostExecute(Void result) {
		    audioRecord.release();
		}
		protected void onPreExecute() {// Done in UI thread
			bufferSize = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);// in bytes
		    buffer = new short[bufferSize];
		    audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRateInHz, channelConfig, audioFormat, bufferSize*10);

		    if(audioRecord != null)
		        Log.d(DBGTag, "audiorecord object created");
		    else
		        Log.d(DBGTag, "audiorecord NOT created");

			isRecording = true;
			Log.e(DBGTag, "onPreExecute Sample Rate = " + sampleRateInHz);
		}
	}
	
	
//	/*
//	 * 播放第一个声波
//	 * */
//	private PlayAudio player;	
//	
//	public void PlayComAirCmd(int iCmd, float voice) {
//		Log.e(DBGTag, "PlayComAirCmd ... cmd = " + iCmd);
//		byte[] byaryPCM = PlayComAir5Cmd(iCmd, 1.0f);
//		if (byaryPCM.length==1){ // show ERROR code
//    		Log.d("Encode Error", " err =" + byaryPCM[0]);
//    		
//    		return;
//    	}
//		player = new PlayAudio(byaryPCM, voice);// must new an instance, one task can be executed only once
//		player.execute();
//	}
//	
//	//===============================================================================
//    // Play Encoded data, do not alter sample rate
//	//第一个参数是指doInbackground接受的参数类型
//	//第二个参数定义onprogressupdate的参数
//	//第三个参数定义doinbackground返回值类型和onpostexecute的参数类型
//    //===============================================================================
//	private class PlayAudio extends AsyncTask<Void, Integer, Void> {
//		private byte[] byaryPCM;
//		private MediaPlayer mp;
//		private File outputFile;
//		private float fVolumeValue = 1.0f;
//
//		public PlayAudio(byte[] byary, float fVolume) {
//	        super();
//	        // set binary data to be played
//	        byaryPCM = new byte[byary.length];
//	        System.arraycopy(byary, 0, byaryPCM, 0, byary.length);
//	        this.fVolumeValue = fVolume;  
//	    }
//		
////		public boolean IsComAirCmdPlaying(){
////			if (mp==null)
////				return false;
////			if (mp.isPlaying())
////				return true;			
////			return false;
////		}
//		
//		//在异步线程中，不能更新ＵＩ，三个点说明参数是变长的，有可能是一个整形，两个整形，params是传入的数组。
//		//需要在UI主线程之外完成的任务
//		@Override
//		protected Void doInBackground(Void... params) {
//			try {
//				if (byaryPCM.length==1)
//					return null;
//				if ( mp!=null ){
//					stopMediaPlayer();
//				}
//
////				 if(null == mp) { 
////					 mp = new MediaPlayer();
////					 } else { 
////						 if(mp.isPlaying()) {
////							 mp.stop();
////							 }
////						 } 
//		    	// write temp file begin
//		    	outputFile = new File(Environment.getExternalStorageDirectory().getPath() + "/encpcm.wav");
//		    	if( outputFile.exists() )
//		    		outputFile.delete();
//		    	System.out.println("播放第一个wav");
//		    	System.out.println(outputFile);
//		    	
//		    	FileOutputStream f = new FileOutputStream(outputFile);
//		    	f.write(byaryPCM);
//		    	f.close();
//		    	mp = new MediaPlayer();
//		    	mp.setDataSource(outputFile.getAbsolutePath());
//		    	mp.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置用于展示mediaPlayer的容器
//		    	mp.setVolume(fVolumeValue, fVolumeValue);
//		    	if(mp.isPlaying()) {
//					 mp.stop();
//					 }
//		    	mp.prepare();
//		    	mp.start();	
//		    	
//		    	mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
//		        {
//		            @Override
//		            public void onCompletion(MediaPlayer arg0) 
//		            {
////		            	mp.stop();
//		            	if(mp.isPlaying()) {
//							 mp.stop();
//							 }
//		                outputFile.delete();
//		            }					
//		        });		    	
//		    }catch (Exception e) {
//		        e.printStackTrace();
//		    } 
//			return null;
//		}
//		
//		protected void onPostExecute(Void result) {//在执行doinbackground方法后调用。在UI线程中，可以更新UI。
//			
//		}
//		protected void onPreExecute() {//在执行doinbackground方法前调用。在UI线程中，可以更新UI。
//			
//		}
//		private void stopMediaPlayer() {
//	        if (mp != null) {
//	            mp.stop();
//	            mp.reset();
//	            mp.release();
//	            mp = null;
//	            System.out.println("播放关闭");
//	        }
//	        
//	    }
//	}
	
	
	/*
	 * 播放两个声波
	 * */
	private PlayAudio player;	
	
	public void PlayComAirCmd1(int iCmd1,int iCmd2,float fVolume) {
		Log.e(DBGTag, "PlayComAirCmd ... cmd = " + iCmd1);
		Log.e(DBGTag, "PlayComAirCmd ... cmd = " + iCmd2);
		byte[] byaryPCM1 = PlayComAir5Cmd(iCmd1, 1.0f);
		if (byaryPCM1.length==1){ // show ERROR code
    		Log.d("Encode Error", " err =" + byaryPCM1[0]);		
    		return;
    	}
		
		byte[] byaryPCM2 = PlayComAir5Cmd(iCmd2, 1.0f);
		if (byaryPCM2.length==1){ // show ERROR code
    		Log.d("Encode Error", " err =" + byaryPCM2[0]);
    		return;
    	}

		player = new PlayAudio(byaryPCM1, byaryPCM2,fVolume);// must new an instance, one task can be executed only once
		player.execute();
	}
	//===============================================================================
    // Play Encoded data, do not alter sample rate
	//第一个参数是指doInbackground接受的参数类型
	//第二个参数定义onprogressupdate的参数
	//第三个参数定义doinbackground返回值类型和onpostexecute的参数类型
    //===============================================================================
	private class PlayAudio extends AsyncTask<Void, Integer, Void> {
		private byte[] byaryPCM;
		private byte[] byaryPCM1;
		private int k=0;
		private MediaPlayer mp,mp1;
		private File outputFile,outputFile1;
		private float fVolumeValue = 1.0f;

		public PlayAudio(byte[] byary,byte[] byary1,float fVolume) {
	        super();
	        // set binary data to be played
	        byaryPCM = new byte[byary.length];
	        byaryPCM1 = new byte[byary1.length];
	        
	        System.arraycopy(byary, 0, byaryPCM, 0, byary.length);
	        System.arraycopy(byary1, 0, byaryPCM1, 0, byary1.length);
	        this.fVolumeValue = fVolume;  
	    }
		
		//在异步线程中，不能更新ＵＩ，三个点说明参数是变长的，有可能是一个整形，两个整形，params是传入的数组。
		//需要在UI主线程之外完成的任务
		@Override
		protected Void doInBackground(Void... params) {
			try {
				if (byaryPCM.length==1&&byaryPCM1.length==1)
					return null;
				if ( mp!=null ){
					stopMediaPlayer();
				}

		    	// write temp file begin
		    	outputFile = new File(Environment.getExternalStorageDirectory().getPath() + "/encpcm.wav");
		    	if( outputFile.exists() )
		    		outputFile.delete();
//		    	System.out.println("播放第一个wav");
		    	
		    	FileOutputStream f = new FileOutputStream(outputFile);
		    	f.write(byaryPCM);
		    	f.close();
		    	
		    	mp = new MediaPlayer();
		    	
		    	mp.setDataSource(outputFile.getAbsolutePath());
		    	mp.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置用于展示mediaPlayer的容器
		    	mp.setVolume(fVolumeValue, fVolumeValue);
		    	
		    	mp.prepare();
		    	mp.start();	
		    	mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
		        {
		            @Override
		            public void onCompletion(MediaPlayer arg0) 
		            {  
		            	mp.stop();
		            	if(mp.isPlaying()) {
							 mp.stop();
						}
		                outputFile.delete();
//		                System.out.println("第一个wav播放完成");
		                k=1;
		                SystemClock.sleep(200);
		                if(k==1)
				    	{
				    	outputFile1 = new File(Environment.getExternalStorageDirectory().getPath() + "/encpcm.wav");
				    	if( outputFile1.exists() )
				    		outputFile1.delete();
				    	System.out.println("播放第二个wav");
				    		
				    	FileOutputStream f1;
						try {
							f1 = new FileOutputStream(outputFile1);
							f1.write(byaryPCM1);
							f1.close();
				    	
							mp1 = new MediaPlayer();
							mp1.setDataSource(outputFile.getAbsolutePath());
							mp1.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置用于展示mediaPlayer的容器
							mp1.setVolume(fVolumeValue, fVolumeValue);
				    	
							mp1.prepare();
							mp1.start();	
							mp1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
							{
								@Override
								public void onCompletion(MediaPlayer arg0) 
								{
//				            		mp.stop();
									if(mp1.isPlaying()) {
										mp1.stop();
									}
									outputFile.delete();
//					                System.out.println("第二个wav播放完成");
								}					
							});
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    	}
		            }					
		        });	    		    
		    }catch (Exception e) {
		        e.printStackTrace();
		    } 
			return null;
		}
		
		protected void onPostExecute(Void result) {//在执行doinbackground方法后调用。在UI线程中，可以更新UI。
			
		}
		protected void onPreExecute() {//在执行doinbackground方法前调用。在UI线程中，可以更新UI。
			
		}
		private void stopMediaPlayer() {
	        if (mp == null) {
	            mp.stop();
	            mp.release();
	            mp = null;
	            System.out.println("播放关闭");
	        }        
	    }
	}
	
	/*
	 * 播放第二个声波 
	 * */
	
	private PlayAudio2 player2;
	public void PlayComAirCmd2(int iCmd, float fVolume) {
		
		Log.e(DBGTag, "PlayComAirCmd ... cmd = " + iCmd);
		byte[] byaryPCM2 = PlayComAir5Cmd(iCmd, 1.0f);
		if (byaryPCM2.length==1){ // show ERROR code
    		Log.d("Encode Error", " err =" + byaryPCM2[0]);
    		
    		return;
    	}
		player2 = new PlayAudio2(byaryPCM2, fVolume);// must new an instance, one task can be executed only once
		player2.execute();

	}
	
	
	//===============================================================================
    // Play Encoded data, do not alter sample rate
	//第一个参数是指doInbackground接受的参数类型
	//第二个参数定义onprogressupdate的参数
	//第三个参数定义doinbackground返回值类型和onpostexecute的参数类型
    //===============================================================================
	private class PlayAudio2 extends AsyncTask<Void, Integer, Void> {
		private byte[] byaryPCM;
		private MediaPlayer mp;
		private File outputFile;
		private float fVolumeValue = 1.0f;

		public PlayAudio2(byte[] byary, float fVolume) {
	        super();
	        // set binary data to be played
	        byaryPCM = new byte[byary.length];
	        System.arraycopy(byary, 0, byaryPCM, 0, byary.length);
	        this.fVolumeValue = fVolume;  
	    }
		
//		public boolean IsComAirCmdPlaying(){
//			if (mp==null)
//				return false;
//			if (mp.isPlaying())
//				return true;			
//			return false;
//		}
		
		
		//在异步线程中，不能更新ＵＩ，三个点说明参数是变长的，有可能是一个整形，两个整形，params是传入的数组。
		//需要在UI主线程之外完成的任务
		@Override
		protected Void doInBackground(Void... params) {
//			SystemClock.sleep(1700);
			try {
					
				if (byaryPCM.length==1)
					return null;
				if ( mp!=null ){
					stopMediaPlayer();
				}

//				 if(null == mp) { 
//					 mp = new MediaPlayer();
//					 } else { 
//						 if(mp.isPlaying()) {
//							 mp.stop();
//							 }
//						 } 
		    	// write temp file begin
		    	outputFile = new File(Environment.getExternalStorageDirectory().getPath() + "/encpcm.wav");
		    	if( outputFile.exists() )
		    		outputFile.delete();
		    	System.out.println("播放第二个wav");
		    	System.out.println(outputFile);
		    	
		    	FileOutputStream f = new FileOutputStream(outputFile);
		    	f.write(byaryPCM);
		    	f.close();
		    	mp = new MediaPlayer();
		    	mp.setDataSource(outputFile.getAbsolutePath());
		    	mp.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置用于展示mediaPlayer的容器
		    	mp.setVolume(fVolumeValue, fVolumeValue);
		    	
		    	mp.prepare();
		    	mp.start();	
		    	
		    	mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
		        {
		            @Override
		            public void onCompletion(MediaPlayer arg0) 
		            {
//		            	mp.stop();
		                outputFile.delete();
		            }					
		        });		    	
		    }catch (Exception e) {
		        e.printStackTrace();
		    } 
			return null;
		}
		
		protected void onPostExecute(Void result) {//在执行doinbackground方法后调用。在UI线程中，可以更新UI。
			
		}
		protected void onPreExecute() {//在执行doinbackground方法前调用。在UI线程中，可以更新UI。
			
		}
		private void stopMediaPlayer() {
	        if (mp != null) {
	            mp.stop();
	            mp.release();
	            mp = null;
	            System.out.println("播放关闭");
	        }
	        
	    }
	}

}
