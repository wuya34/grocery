package com.example.amyas.grocery.util;

import android.util.Log;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 百度地图工具类
 * author: Amyas
 * date: 2018/1/25
 */

public class MapUtil {
    /**
     * 地图精度，单位/米
     */
    public static final double[] DISTANCE = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000,
            20000, 25000, 50000, 100000, 200000, 500000, 1000000, 2000000};
    /**
     * 地图精度对应缩放级别
     */
    public static final int[] LEVEL = {20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3};

    /**
     * 根据3个经纬度计算地图缩放等级
     *
     * @param list
     * @return 百度地图缩放等级
     */
    public static int getZoomLevelByLatLngGroup(List<LatLng> list) {
        List<Double> latLngDistanceGroup = getLatLngDistanceGroup(list);
        int index = findIndex(DISTANCE, latLngDistanceGroup.get(latLngDistanceGroup.size()-1)/5);
        if (index<0||index>18){
            Log.e("1", "getZoomLevelByLatLngGroup: 缩放等级不合法");
            return LEVEL[5];
        }
        return LEVEL[index];

    }

    /**
     * 获取LatLng集合两两间的距离,升序排列
     * @param list
     */
    public static List<Double> getLatLngDistanceGroup(List<LatLng> list){
        List<Double> distance = new ArrayList<>();
        for (int i = 0; i < list.size()-1; i++) {
            for (int j = i+1; j < list.size(); j++) {
                distance.add(DistanceUtil.getDistance(list.get(i), list.get(j)));
            }
        }
        Collections.sort(distance);
        return distance;
    }

    /**
     * 计算LatLng列表中的中心点
     * @return 中心点的LatLng
     */
    public static LatLng getCenterLatLngInGroup(List<LatLng> list){
        int count = list.size();
        double longitude = 0;
        double latitude = 0;
        for (LatLng latLng : list) {
            longitude += latLng.longitude;
            latitude += latLng.latitude;
        }
        return new LatLng(longitude/count,latitude/count);
    }

    public static int findIndex(double[] a, double b) {
        for (int i = 0; i < a.length; i++) {
            if (b < a[i]) {
                return i ;
            }
        }
        return -1;
    }

    /**
     * 计算多边形面积
     * @return
     */
    public static String getArea(List<LatLng> pts) {
        double totalArea = 0;// 初始化总面积
        double LowX = 0.0;
        double LowY = 0.0;
        double MiddleX = 0.0;
        double MiddleY = 0.0;
        double HighX = 0.0;
        double HighY = 0.0;
        double AM = 0.0;
        double BM = 0.0;
        double CM = 0.0;
        double AL = 0.0;
        double BL = 0.0;
        double CL = 0.0;
        double AH = 0.0;
        double BH = 0.0;
        double CH = 0.0;
        double CoefficientL = 0.0;
        double CoefficientH = 0.0;
        double ALtangent = 0.0;
        double BLtangent = 0.0;
        double CLtangent = 0.0;
        double AHtangent = 0.0;
        double BHtangent = 0.0;
        double CHtangent = 0.0;
        double ANormalLine = 0.0;
        double BNormalLine = 0.0;
        double CNormalLine = 0.0;
        double OrientationValue = 0.0;
        double AngleCos = 0.0;
        double Sum1 = 0.0;
        double Sum2 = 0.0;
        double Count2 = 0;
        double Count1 = 0;
        double Sum = 0.0;
        double Radius = 6378137.0;// WGS84椭球半径
        int Count = pts.size();
        //最少3个点
        if (Count  < 3) {
            return "";
        }
        for ( int i = 0; i < Count; i++) {
            if (i == 0) {
                LowX = pts.get(Count - 1).longitude * Math.PI / 180;
                LowY = pts.get(Count - 1).latitude * Math.PI / 180;
                MiddleX = pts.get(0).longitude * Math.PI / 180;
                MiddleY = pts.get(0).latitude * Math.PI / 180;
                HighX = pts.get(1).longitude * Math.PI / 180;
                HighY = pts.get(1).latitude * Math.PI / 180;
            } else if (i == Count - 1) {
                LowX = pts.get(Count - 2).longitude * Math.PI / 180;
                LowY = pts.get(Count - 2).latitude * Math.PI / 180;
                MiddleX = pts.get(Count - 1).longitude * Math.PI / 180;
                MiddleY = pts.get(Count - 1).latitude * Math.PI / 180;
                HighX = pts.get(0).longitude * Math.PI / 180;
                HighY = pts.get(0).latitude * Math.PI / 180;
            } else {
                LowX = pts.get(i - 1).longitude * Math.PI / 180;
                LowY = pts.get(i - 1).latitude * Math.PI / 180;
                MiddleX = pts.get(i).longitude * Math.PI / 180;
                MiddleY = pts.get(i).latitude * Math.PI / 180;
                HighX = pts.get(i + 1).longitude * Math.PI / 180;
                HighY = pts.get(i + 1).latitude * Math.PI / 180;
            }
            AM = Math.cos(MiddleY) * Math.cos(MiddleX);
            BM = Math.cos(MiddleY) * Math.sin(MiddleX);
            CM = Math.sin(MiddleY);
            AL = Math.cos(LowY) * Math.cos(LowX);
            BL = Math.cos(LowY) * Math.sin(LowX);
            CL = Math.sin(LowY);
            AH = Math.cos(HighY) * Math.cos(HighX);
            BH = Math.cos(HighY) * Math.sin(HighX);
            CH = Math.sin(HighY);
            CoefficientL = (AM * AM + BM * BM + CM * CM)/ (AM * AL + BM * BL + CM * CL);
            CoefficientH = (AM * AM + BM * BM + CM * CM)/ (AM * AH + BM * BH + CM * CH);
            ALtangent = CoefficientL * AL - AM;
            BLtangent = CoefficientL * BL - BM;
            CLtangent = CoefficientL * CL - CM;
            AHtangent = CoefficientH * AH - AM;
            BHtangent = CoefficientH * BH - BM;
            CHtangent = CoefficientH * CH - CM;
            AngleCos = (AHtangent * ALtangent + BHtangent * BLtangent + CHtangent* CLtangent)/ (Math.sqrt(AHtangent * AHtangent + BHtangent* BHtangent

                    + CHtangent * CHtangent) * Math.sqrt(ALtangent * ALtangent + BLtangent* BLtangent + CLtangent * CLtangent));
            AngleCos = Math.acos(AngleCos);
            ANormalLine = BHtangent * CLtangent - CHtangent * BLtangent;
            BNormalLine = 0 - (AHtangent * CLtangent - CHtangent* ALtangent);
            CNormalLine = AHtangent * BLtangent - BHtangent * ALtangent;
            if (AM != 0)
                OrientationValue = ANormalLine / AM;
            else if (BM != 0)
                OrientationValue = BNormalLine / BM;
            else
                OrientationValue = CNormalLine / CM;
            if (OrientationValue > 0) {
                Sum1 += AngleCos;
                Count1++;
            } else {
                Sum2 += AngleCos;
                Count2++;
            }
        }

        double tempSum1, tempSum2;
        tempSum1 = Sum1 + (2 * Math.PI * Count2 - Sum2);
        tempSum2 = (2 * Math.PI * Count1 - Sum1) + Sum2;
        if (Sum1 > Sum2) {
            if ((tempSum1 - (Count - 2) * Math.PI) < 1)
                Sum = tempSum1;
            else
                Sum = tempSum2;
        } else {
            if ((tempSum2 - (Count - 2) * Math.PI) < 1)
                Sum = tempSum2;
            else
                Sum = tempSum1;
        }
        totalArea = (Sum - (Count - 2) * Math.PI) * Radius * Radius;

        return String.valueOf(Math.floor(totalArea)); // 返回总面积
    }


}
