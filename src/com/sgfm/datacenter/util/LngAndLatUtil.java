package com.sgfm.datacenter.util;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

public class LngAndLatUtil {

    public static String MAP_BAIDU_AK = "5a3ac451ae5ee05db20ca3fa4e8b0ac3";

    public static Map<String, String> getLngAndLat(String address) {
        Map<String, String> map = new HashMap<String, String>();
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address.trim() + "&output=json&ak=" + MAP_BAIDU_AK;
        String json = loadJSON(url);
        JSONObject obj = JSONObject.fromObject(json);
        String status = obj.get("status").toString();
        if ("0".equals(status)) {
            String lng = obj.getJSONObject("result").getJSONObject("location").getString("lng");
            String lat = obj.getJSONObject("result").getJSONObject("location").getString("lat");
            map.put("LNG", lng);
            map.put("LAT", lat);
            // System.out.println("经度："+lng+"---纬度："+lat);
        } else {
            // status状态码: 0 正常; 1 服务器内部错误; 2 请求参数非法; 3 权限校验失败; 4 配额校验失败; 5 ak不存在或者非法; 101 服务禁用; 102 不通过白名单或者安全码不对; 2xx 无权限; 3xx 配额错误;
            System.out.println("百度Map获取坐标失败，status：" + status);
        }
        return map;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            InputStream is = yc.getInputStream();// 拿到输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两个坐标的距离
     * 
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 100) / 100.0;
        return s;
    }

    public static void main(String[] args) {
        Map<String, String> map = LngAndLatUtil.getLngAndLat("成都市武侯区天府大道中段天府三街新希望国际C座3层 ");
        System.out.println("经度：" + map.get("LNG") + "---纬度：" + map.get("LAT"));
    }


}
