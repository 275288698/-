package com.babycare.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.babycare.R;
import com.babycare.db.SQLHelper;
import com.babycare.utils.BasicTool;
import com.babycare.utils.Loading;
import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;


public class MyAplication extends Application {
	private static MyAplication mAppApplication;
	private static Loading loading;
	private SQLHelper sqlHelper;
	public  ArrayList<Activity> activities ;
	public void kill(){
		List<Activity> list=activities;
		for (Activity activity : list) {
			activity.finish();
			
		}
		
	}
	
	@Override
	public void onCreate() {
		
		// TODO Auto-generated method stub
		super.onCreate();
		activities =new ArrayList<Activity>();
		SDKInitializer.initialize(getApplicationContext());  
		initImageLoader(getApplicationContext());
		mAppApplication = this;
	}
	
	/** 获取Application */
	public static MyAplication getApp() {
		return mAppApplication;
	}
	
	/** 获取数据库Helper */
	public SQLHelper getSQLHelper() {
		if (sqlHelper == null)
			sqlHelper = new SQLHelper(mAppApplication);
		return sqlHelper;
	}
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		if (sqlHelper != null)
			sqlHelper.close();
		super.onTerminate();
		//整体摧毁的时候调用这个方法
	}
	
	/**
	 * yq 图片下载初始化
	 * 
	 */
	public static ImageLoader imageLoader = ImageLoader.getInstance();
	public static DisplayImageOptions options;

	public void initImageLoader() {
		/********** 异步下载图片缓存类 初始化 */
		MyAplication.initImageLoader(getApplicationContext());
		options = new DisplayImageOptions.Builder()
				// 加载等待 时显示的图片
				// .showImageOnLoading(R.drawable.ic_stub) // resource or
				// drawable
				// .showImageForEmptyUri(R.drawable.ic_empty) // resource or
				// drawable
				// resource or drawable
				// .resetViewBeforeLoading(false) // default
				// .delayBeforeLoading(1000)
				// .cacheInMemory(false) // default
				// .cacheOnDisk(false) // default
				// .preProcessor(...)
				// .postProcessor(...)
				// .extraForDownloader(...)
				.considerExifParams(true)
				// default
				// .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) //
				// default
				// .bitmapConfig(Bitmap.Config.ARGB_8888) // default
				// .decodingOptions(...)
				// .displayer(new SimpleBitmapDisplayer()) // default
				// .handler(new Handler()) // default

				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565).cacheInMemory(true)
				
				.cacheOnDisc(true)
				/**
				 * .displayer(new RoundedBitmapDisplayer(20))
				 **/
				.build();

	}

	public static void initImageLoader(Context context) {

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// Not
				// .memoryCacheExtraOptions(480, 800) // default = device screen
				// dimensions
				// .diskCacheExtraOptions(480, 800, null)
				// .taskExecutor(...)
				// .taskExecutorForCachedImages(...)
				// .threadPoolSize(3) // default
				// .threadPriority(Thread.NORM_PRIORITY - 2) // default
				// .tasksProcessingOrder(QueueProcessingType.FIFO) // default
				// .denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(30) // default
				// .diskCache(new UnlimitedDiscCache(cacheDir)) // default
				// .diskCacheSize(50 * 1024 * 1024)
				// .diskCacheFileCount(100)
				// .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
				// // default
				// .imageDownloader(new BaseImageDownloader(context)) // default
				// .imageDecoder(new BaseImageDecoder()) // default
				// .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				// // default
				.writeDebugLogs().build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
		// imageLoader.init(ImageLoaderConfiguration.createDefault(context));
	}

	
	
	private static void showProgressDialog(Activity activity,String message) {
		if (!BasicTool.isNotEmpty(message)) {
			return ;
		}
		
		loading = new Loading(activity, R.style.loadingDialogTheme,message);
		
		LayoutParams params = loading.getWindow().getAttributes();
		
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		params.alpha = 1.0f;// 透明度
		loading.getWindow().setAttributes(params);
		
		loading.show();
	}
	
	/** 隐藏进度条 */
	private void dismissProgressDialog() {
		if (loading != null && loading.isShowing()) {
			loading.dismiss();
			loading = null;
		}
	}
	
	
	
}
