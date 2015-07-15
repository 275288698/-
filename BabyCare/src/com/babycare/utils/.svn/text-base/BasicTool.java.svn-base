package com.babycare.utils;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.Time;
import android.util.Base64;

public class BasicTool {
	
	/** �?��字符串是否为�?
	 * @param str
	 * @return 为空返回false，否则返回true
	 */
	public static boolean isNotEmpty(String str) {
		if (null == str)
			return false;
		return !"".equals(str.trim());
	}

	/**
	 * @function 邮箱验证,验证通过则返回ture，否则为false
	 * @param strEmail
	 * @return
	 */
	public static boolean isEmail(String strEmail) {
		String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);

		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @function 手机号的验证,验证通过则返回ture，否则为false
	 * @param str
	 * @return
	 */
	public static boolean isCellphone(String str) {
		Pattern pattern = Pattern.compile("1[0-9]{10}");
		Matcher matcher = pattern.matcher(str);

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @function 邮政编码验证
	 * @param post
	 * @return
	 */
	public static boolean isPostCode(String post) {
		 if(post.matches("[1-9]\\d{5}(?!\\d)")){  
	            return true;  
	        }else{  
	            return false;  
	        }  
	}
	
	/**
	 * @function 身份证号码编码验�?
	 * @param post
	 * @return
	 */
	public static boolean isIdcardCode(String cardStr) {
		  String regx = "[0-9]{17}x";
		  String reg1 = "[0-9]{15}";
		  String regex = "[0-9]{18}";
		  
		  return cardStr.matches(regx) || cardStr.matches(reg1) || cardStr.matches(regex);
	}

	/**
	 * 获取时间�?
	 * 
	 * @return
	 */
	public static String getCurString() {
		long msg = System.currentTimeMillis();

		return Long.toString(msg);

	}

	/**
	 * 将单个list转成json字符�?
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String listToJsonString(List<Map<String, Object>> list)throws Exception {
		String jsonL = "";
		StringBuffer temp = new StringBuffer();
		temp.append("[");
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> m = list.get(i);
			if (i == list.size() - 1) {
				temp.append(mapToJsonObj(m));
			} else {
				temp.append(mapToJsonObj(m) + ",");
			}
		}
		if (temp.length() > 1) {
			temp = new StringBuffer(temp.substring(0, temp.length()));
		}
		temp.append("]");
		jsonL = temp.toString();
		return jsonL;
	}

	/**
	 * 将map数据解析出来，并拼接成json字符�?
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JSONObject mapToJsonObj(Map map) throws Exception {
		JSONObject json = null;
		StringBuffer temp = new StringBuffer();
		if (!map.isEmpty()) {
			temp.append("{");
			// 遍历map
			Set set = map.entrySet();
			Iterator i = set.iterator();
			while (i.hasNext()) {
				Map.Entry entry = (Map.Entry) i.next();
				String key = (String) entry.getKey();

				Object value = entry.getValue();

				temp.append("\"" + key + "\":");

				if (null == value || "".equals(value)) {
					temp.append("\"\"" + ", ");
				} else if (value instanceof Map<?, ?>) {
					temp.append(mapToJsonObj((Map<String, Object>) value) + ",");
				} else if (value instanceof List<?>) {
					temp.append(listToJsonString((List<Map<String, Object>>) value)+ ",");
				} else if(value instanceof String){
					temp.append("\""+String.valueOf(value) + "\",");
				}else {
					temp.append(String.valueOf(value) + ",");
				}

			}
			if (temp.length() > 1) {
				temp = new StringBuffer(temp.substring(0, temp.length() - 1));
			}

			temp.append("}");

			json = new JSONObject(temp.toString());
		}
		return json;
	}
	
	 /**日期时间比较 
		 * @param DATE 输入的日期�?时间
		 * @param hour 是否还有�?
		 * @return 
		 */
		public static boolean compare_date(String DATE,boolean hasHour) {
			 Time time = new Time();
			 time.setToNow();
			 String DATE2 ;
			 SimpleDateFormat df;
			 if (hasHour) {
				 DATE2 = time.year +"-"+(time.month+1) +"-"+ time.monthDay +" " + time.hour+":"+time.minute;
				 df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 }else {
				 DATE2 = time.year +"-"+(time.month+1) +"-"+ time.monthDay ;
				 df = new SimpleDateFormat("yyyy-MM-dd");
			 }
			 L.e("DATE2当前系统时间", DATE2);
			 L.e("DATE",DATE);
			 
		        try {
		            Date dt1 = df.parse(DATE);
		            Date dt2 = df.parse(DATE2);
		            
		            if (hasHour) {
						if (dt1.getTime() >= dt2.getTime()) {
							return true;
						} else if (dt1.getTime() < dt2.getTime()) {
							return false;
						} 

					}else {
						if (dt1.getTime() > dt2.getTime()) {
							return true;
						} else if (dt1.getTime() <= dt2.getTime()) {
							return false;
						} 
					}
		            
		            
		        } catch (Exception exception) {
		            exception.printStackTrace();
		        }
		        return true;
		    }
	

	
	/**日期比较
	 * @param DATE1 �?��时间
	 * @param DATE2 结束时间
	 * @return
	 */
	public static boolean compare_2_date(String DATE1,String DATE2) {
		try {
		
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            
			if (dt1.getTime() > dt2.getTime()) {
				return true;
			} else if (dt1.getTime() <= dt2.getTime()) {
				return false;
			} 
            
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return true;
	    }
	/**
	 * 将json 对象转换为Map对象
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String jsonString) {
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonString);
			@SuppressWarnings("unchecked")
			Iterator<String> keyIter = jsonObject.keys();
			String key;
			Object value;
			Map<String, Object> valueMap = new HashMap<String, Object>();
			while (keyIter.hasNext()) {
				key = (String) keyIter.next();
				value = jsonObject.get(key);
				valueMap.put(key, value);
			}
			return valueMap;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把json数组字符串转换为ArrayList 形式
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> jsonArrToList(String jsonString) {
		List<Map<String, Object>> list = null;

		try {
			JSONArray jsonArray = new JSONArray(jsonString);

			JSONObject jsonObject;

			list = new ArrayList<Map<String, Object>>();

			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.getJSONObject(i);
				list.add(jsonToMap(jsonObject.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	 /**
	  * 
	  * @param gap  获取时间的间隔，要获取之前的日期则为负，如获取前�?��的时间，则为-7；反之当前日期之后的则为正数
	  * @return
	  */
	public static String getDateBefore(int gap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar now = Calendar.getInstance();

		now.setTime(new Date());
		now.set(Calendar.DATE, now.get(Calendar.DATE) + gap);

		return sdf.format(now.getTime());
	}
	
	 /**
	  * 
	  * @param gap  获取当前日期，格式：yyyy-MM-dd HH:mm:ss
	  * @return
	  */
	public static String getCruDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(new Date());
	}
	
	public static String getOrderTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		return sdf.format(new Date());
	}
	
	/**日期时间字符串转换为日期字符�?*/
	public static String dateTimeToDate(String datatime) {
		SimpleDateFormat dataTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = dataTime.parse(datatime);
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			return sdf2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	
	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为�?
	 * 
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	
	 /** 缩放Bitmap图片 **/
	 public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {

	        int w = bitmap.getWidth();

	        int h = bitmap.getHeight();

	        Matrix matrix = new Matrix();

	        float scaleWidth = ((float) width / w);

	        float scaleHeight = ((float) height / h);

	        matrix.postScale(scaleWidth, scaleHeight);// 利用矩阵进行缩放不会造成内存溢出

	        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);

	        return newbmp;

	    }
	 
	 /** 把Bitmap用Base64编码并返回生成的字符�?*/
	 public static String bitmaptoString(Bitmap bitmap) {  
	        String string = null;  
	  
	        ByteArrayOutputStream bStream = new ByteArrayOutputStream();  
	  
	        bitmap.compress(CompressFormat.PNG, 100, bStream);  
	  
	        byte[] bytes = bStream.toByteArray();  

	        string = Base64.encodeToString(bytes, Base64.DEFAULT);  
	  
	        return string;  
		}
	 
	 /**
	  * 根据Object obj以及key获取对应的value�?String类型
	  * @param obj		目标对象
	  * @param key		待取值的key
	  * @return
	  */
	public static String getKeyValue(JSONObject obj,String key){
		String value = "";
		
		if (obj.has(key)) {
			try {
				value = obj.getString(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}
	
	 /**
	  * 根据Object obj以及key获取对应的value�?int类型
	  * @param obj		目标对象
	  * @param key		待取值的key
	  * @return
	  */
	public static int getKeyValueInt(JSONObject obj,String key){
		int value = 0;
		
		if (obj.has(key)) {
			try {
				value = obj.getInt(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}
	
	public static double getKeyValueDouble(JSONObject obj,String key){
		double value = 0;
		
		if (obj.has(key)) {
			try {
				value = obj.getDouble(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}
	
	  //判断是否有网络
    public static boolean isNetWork(Context act) {  
        
       ConnectivityManager manager = (ConnectivityManager) act  
              .getApplicationContext().getSystemService(  
                     Context.CONNECTIVITY_SERVICE);  
        
       if (manager == null) {  
           return false;  
       }  
        
       NetworkInfo networkinfo = manager.getActiveNetworkInfo();  
        
       if (networkinfo == null || !networkinfo.isAvailable()) {  
           return false;  
       }  
   
       return true;  
    } 
    
    public static int dip2px(Context context,float dipValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dipValue*scale+0.5f);
   }

   public static int px2dp(Context context,float pxValue){
       final float scale = context.getResources().getDisplayMetrics().density; 
       return (int)(pxValue/scale+0.5f); 
   }
}
