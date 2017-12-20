package com.example.amyas.customwidget.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.example.amyas.customwidget.R;
import com.example.amyas.customwidget.util.ToastUtil;

/**
 * 百度地图方向指示最好采用传感器，获取位置间隔时间应为3-5秒
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private TextureMapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private BaiduLocationListener mListener = new BaiduLocationListener();
    private boolean isFirstLoc = true;
    private double mLatitude;
    private double mLongitude;
    protected String[] PERMISSIONS_CONTACT = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int REQUEST_CONACTS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        /**
         * 检查权限
         */
        if (Build.VERSION.SDK_INT>=23){
            showContacts();
        }
        initMap();
        initLocationClient();

    }

    private void initMap(){
        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setOnMapClickListener(mOnMapClickListener);
        mBaiduMap.setOnMarkerClickListener(mOnMarkerClickListener);
        // 更换方向指示箭头
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_direction_arrow);
        MyLocationConfiguration config = new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.COMPASS, true, null);
        mBaiduMap.setMyLocationConfiguration(config);
    }


    private void showContacts(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED){
            requestContactsPermissions();
        }
    }

    private void requestContactsPermissions(){
        ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONACTS);

    }


    public static boolean verifyPermissions(int[] grantResults){
        for (int result: grantResults){
            if (result!=PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_CONACTS){
            if (verifyPermissions(grantResults)){
                initMap();
                initLocationClient();
            }else {
                finish();
            }
        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 初始化定位 client 对象
     */
    private void initLocationClient() {
        mLocationClient = new LocationClient(this);
        mLocationClient.registerLocationListener(mListener);
        LocationClientOption option = new LocationClientOption();
        // 设置定位模式， 默认高精度
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //设置返回的定位结果坐标系
        //默认gcj02
        option.setCoorType("bd09ll");
        //设置定位间隔，默认为0，即只定位1次
        //此处设置定位请求的间隔大于等于10000ms
        option.setScanSpan(3000);
//        option.setIgnoreKillProcess(false);
        option.setOpenGps(true);
        option.setIsNeedAddress(true);
        option.setLocationNotify(true);
        //设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);

        //设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);

        //为LocationClient配置option
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mLocationClient.unRegisterLocationListener(mListener);
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
        mBaiduMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    private void showLocation(){
        // TODO: 2017/12/11 检查所有的定位权限，运行时申请
        Log.e(TAG, "showLocation: try to showLocation");

    }

    private class BaiduLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.e(TAG, "onReceiveLocation: latitude "+ location.getLatitude());
            Log.e(TAG, "onReceiveLocation: longitude "+ location.getLongitude());
            Log.e(TAG, "onReceiveLocation: direction "+location.getDirection());
//            LatLng point = new LatLng(bdLocation.getLatitude(),
//                    bdLocation.getLongitude());
//            MapStatus mapStatus = new MapStatus.Builder()
//                    .target(point)
//                    .zoom(16)
//                    .build();
//            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
//            mBaiduMap.setMapStatus(mapStatusUpdate);
            if (isFirstLoc){
                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(latLng)
                        .zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                isFirstLoc = false;
                resetLocationByBDLocation(location);

            }else {
                resetLocationByBDLocation(location);
            }

        }

    }

    BaiduMap.OnMapClickListener mOnMapClickListener = new BaiduMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            mBaiduMap.clear();
            LatLng point = new LatLng(latLng.latitude, latLng.longitude);
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_gonggao);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            mBaiduMap.addOverlay(option);
        }

        @Override
        public boolean onMapPoiClick(MapPoi mapPoi) {
            ToastUtil.showToast(MainActivity.this, mapPoi.getName());
            return false;
        }
    };

    BaiduMap.OnMarkerClickListener mOnMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            ToastUtil.showToast(MainActivity.this, marker.getTitle());
            return false;
        }
    };

    private void resetLocationByBDLocation(BDLocation location){
//        LatLng latLng = new LatLng()
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                .direction(location.getDirection()).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
        MyLocationConfiguration config = new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL, true, null);
        mBaiduMap.setMyLocationConfiguration(config);

    }
}
