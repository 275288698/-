package com.babycare;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.babycare.base.CustomTitleActivity;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class ElectronicFence extends CustomTitleActivity {

	private   MapView mMapView = null; 
	private BaiduMap mBaiduMap = null;
	private LocationClient mLocClient;
	public boolean isFirstLoc = true;// �Ƿ��״ζ�λ
	public MyLocationListenner myListener = new MyLocationListenner();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.electronic_fence);
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		setTitle("����Χǽ");
		setTitleBarRightBtnText(null);
//		 mMapView = (MapView) findViewById(R.id.bmapView);  
//		mMapView.showZoomControls(false);;// ȡ���Ŵ���С
//
//		mBaiduMap = mMapView.getMap();
//
//		mBaiduMap.setMaxAndMinZoomLevel(19, 7);
//
//	
//		mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(16));
//
//		new MapStatus.Builder().zoom(14);
//
//		mBaiduMap.setMyLocationEnabled(true);
//		// ��λ��ʼ��
//		mLocClient = new LocationClient(this);
//		mLocClient.registerLocationListener(myListener);
//		LocationClientOption option = new LocationClientOption();
//		option.setOpenGps(true);// ��gps
//		option.setCoorType("bd09ll"); // ������������
//		option.setScanSpan(1000);
//
//		mLocClient.setLocOption(option);
//		mLocClient.start();
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

	 @Override  
	    protected void onDestroy() {  
	        super.onDestroy();  
	        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
//	        mMapView.onDestroy();  
	    }  
	    @Override  
	    protected void onResume() {  
	        super.onResume();  
	        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
//	        mMapView.onResume();  
	        }  
	    @Override  
	    protected void onPause() {  
	        super.onPause();  
	        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
//	        mMapView.onPause();  
	        }  
	    /**
		 * ��λSDK��������
		 */
		public class MyLocationListenner implements BDLocationListener {

			@Override
			public void onReceiveLocation(BDLocation location) {
				// map view ���ٺ��ڴ����½��յ�λ��
				if (location == null || mMapView == null)
					return;
				MyLocationData locData = new MyLocationData.Builder()
						.accuracy(1000)
						// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
						.direction(100).latitude(location.getLatitude())
						.longitude(location.getLongitude()).build();

				mBaiduMap.setMyLocationData(locData);
				if (isFirstLoc) {
					isFirstLoc = false;
					LatLng ll = new LatLng(location.getLatitude(),
							location.getLongitude());
					MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
					mBaiduMap.animateMapStatus(u);
				}
			}

			public void onReceivePoi(BDLocation poiLocation) {
			}
		}
		
		public void add(View view){
			onViewcl(view);
		}
		public void delete(View view){
			onViewcl(view);
		}
		public void expand(View view){
			onViewcl(view);
		}
		public void narrow(View view){
			onViewcl(view);
		}
		private void onViewcl(View view) {
			TextView textView = (TextView) view;
			Toast.makeText(getApplicationContext(), textView.getText().toString(), 1).show();
			
		}
}
