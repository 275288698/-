package com.babycare.httpconnect;

import java.util.HashMap;
import java.util.Map;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;



/*Make asynchronous HTTP requests, handle responses in anonymous callbacks 
 HTTP requests happen outside the UI thread 
 Requests use a threadpool to cap concurrent resource usage 
 GET/POST params builder (RequestParams) 
 Multipart file uploads with no additional third party libraries 
 Tiny size overhead to your application, only 19kb for everything 
 Automatic smart request retries optimized for spotty mobile connections 
 Automatic gzip response decoding support for super-fast requests 
 Optional built-in response parsing into JSON (JsonHttpResponseHandler) 
 Optional persistent cookie store, saves cookies into your app's SharedPreferences
 */


public class HttpRestClient {
	private static HttpUrls mHttpUrls = new HttpUrls();
	

	public static AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();

	public static HttpUrls getmHttpUrls() {
		return mHttpUrls;
	}

	public static void setmHttpUrls(HttpUrls mHttpUrls) {
		HttpRestClient.mHttpUrls = mHttpUrls;
	}


	

	


	
	
	// 2014 -7-17 
	
	public static void registe(Context context,String mobile,String captcha,String password,String device,AsyncHttpResponseHandler handler){
		RequestParams requestParams = new RequestParams();
		requestParams.put("mobile", mobile);
		requestParams.put("captcha",captcha);
		requestParams.put("password",password);
		requestParams.put("device",device);
		mAsyncHttpClient.post(context,HttpUrls.regist, requestParams,handler);
	}
	
	// 2014 -7-17 
	
	public static void hasRegist(Context context,String mobile,AsyncHttpResponseHandler handler){
		RequestParams requestParams = new RequestParams();
		requestParams.put("mobile", mobile);
		mAsyncHttpClient.post(context,HttpUrls.hasRegist, requestParams,handler);
	}
	/**
	 *  
	 */
	public static Map<String, String> getHeaders()  {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("Authorization", "70cc162db510166cbb7fea787269820b");
		map.put("Token", "_vfWE9YKXrEEeWahpmN1gHvfBtyWipVd");
		map.put("Key", "66612252");
		map.put("Date", "Wed, 16 Jul 2014 03:13:47 GMT");
//		 map.put("Cookie",
//		 "uuid=0c7f52c63f4a77834b3d.1405480402.0.0.0; ci=30");
//		 map.put("Cookie2", "$Version=1");
		// map.put("Accept-Charset", "utf-8");
		// map.put("User-Agent",
		// "AiMovie /google-4.4.2-hammerhead-1776x1080-480-4.1-91-358239054054582-market");
		return map;
	}
	
}
/*
	public static void doHttpRegister(Context context, String userName,
			String pwd, String nickName, String trueName, String age,
			String sex, String tel, String work, String info,
			AsyncHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		requestParams.put("Name", userName);
		requestParams.put("PassWord", pwd);
		requestParams.put("NickName", nickName);
		requestParams.put("ActualName", trueName);
		requestParams.put("Age", age);
		requestParams.put("Sexly", sex);
		requestParams.put("Phone", tel);
		requestParams.put("Position", work);
		requestParams.put("Description", info);
		mAsyncHttpClient.post(context, mHttpUrls.REGISTER, requestParams,
				handler);
	}

	public static void doHttpUpdatePwd(Context context, String name,
			String oldpassword, String newpassword,
			AsyncHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		requestParams.put("name", name);
		requestParams.put("oldpassword", oldpassword);
		requestParams.put("newpassword", newpassword);
		mAsyncHttpClient.post(context, mHttpUrls.UPDATEPASSWORD, requestParams,
				handler);
	}

	public static void doHttpForgotPwd(Context context, String name,
			AsyncHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		requestParams.put("name", name);
		mAsyncHttpClient.get(context, mHttpUrls.FORGOTPWD + "?name=" + name,
				requestParams, handler);
	}

	*//**********************************************************//*

	*//***************** 绗戞灄瀵� 棣栭〉 璇锋眰鏂规硶 **********************************//*
	*//**
	 * 鏍规嵁鍒囨崲鎸夐挳Id锛岃繑鍥炰笉鍚岀被鍨嬬殑鏂伴椈鏁版嵁
	 * 
	 * @param type
	 *            鏂伴椈鍒囨崲鎸夐挳ID
	 * @param page
	 *            鍔犺浇椤垫暟
	 * @param count
	 *            姣忛〉鍔犺浇鏂伴椈鏁扮洰
	 * @param isDown
	 *            鏄惁涓嬫媺鍜屼笂鎺ㄨ姹�
	 * @param time
	 *            鏇存柊鏃堕棿
	 * @param inputtime
	 *            绗戣瘽鎻掑叆鏃堕棿
	 *//*
	public static void doHttpGetFirst(Context context, String type,
			String PageSize, String id, String time, String inputtime,
			String isDown, AsyncHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		// requestParams.put("type",type);
		requestParams.put("m", "content");
		requestParams.put("c", "index");
		requestParams.put("num", GlobalApplication.READ_PAGES);
		if (CustomAnimation.type[0].equals(type)) {
			if (HttpUrls.firstIsDown[2].equals(isDown.trim())) {
				requestParams.put("a", "allDataJson");
			} else {
				requestParams.put("a", "newest");
			}

		} else if (CustomAnimation.type[1].equals(type)) {
			if (HttpUrls.firstIsDown[2].equals(isDown.trim())) {
				requestParams.put("a", "funniest");
			} else {
				requestParams.put("a", "newest");
				requestParams.put("funniest", "1");
			}
		} else if (CustomAnimation.type[7].equals(type)) {
			if (HttpUrls.firstIsDown[2].equals(isDown.trim())) {
				requestParams.put("a", HttpUrls.firstTodayParamsA[0]);
			} else {
				requestParams.put("updatetime", time);
				requestParams.put("a", HttpUrls.firstTodayParamsA[1]);
			}
		} else if (CustomAnimation.type[8].equals(type)) {
			if (HttpUrls.firstIsDown[2].equals(isDown.trim())) {
				requestParams.put("a", HttpUrls.firstImageParamsA[0]);
			} else {
				requestParams.put("a", HttpUrls.firstImageParamsA[1]);
			}
		} else if (CustomAnimation.type[9].equals(type)) {
			if (HttpUrls.firstIsDown[2].equals(isDown.trim())) {
				requestParams.put("a", HttpUrls.firstWordParamsA[0]);
			} else {
				requestParams.put("a", HttpUrls.firstWordParamsA[1]);
			}
		} else {
			requestParams.put("catid", type);
			if (HttpUrls.firstIsDown[2].equals(isDown.trim())) {
				requestParams.put("a", "listDataJson");
			} else {
				requestParams.put("a", "newest");
			}
			// requestParams.put("a", "listDataJson");
		}
		if (!HttpUrls.firstIsDown[2].equals(isDown.trim())) {
			requestParams.put("id", id);
			requestParams.put("isDown", isDown);
			requestParams.put("inputtime", inputtime);
		}
		mAsyncHttpClient.get(context, HttpUrls.FIRST, requestParams, handler);

	}

	@SuppressLint({ "ParserError", "ParserError" })
	public static void doHttpGetNext(Context context, String type, String id,
			String PageSize, AsyncHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		// requestParams.put("type",type);
		requestParams.put("m", "content");
		requestParams.put("c", "index");

		requestParams.put("a", "articleDataJson");
		requestParams.put("id", id);

		mAsyncHttpClient.get(context, HttpUrls.FIRST, requestParams, handler);

	}
	public static void doHttpGetNewCount_(Context context, String todayRecommendId, String getPicId,
			String getCharacterId, AsyncHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		// requestParams.put("type",type);
		requestParams.put("m", "content");
		requestParams.put("c", "index");

		requestParams.put("a", "getNewCount");
		//requestParams.put("id", id);
		requestParams.put("todayRecommendId", todayRecommendId);
		requestParams.put("getPicId", getPicId);
		requestParams.put("getCharacterId", getCharacterId);

		mAsyncHttpClient.get(context, HttpUrls.FIRST, requestParams, handler);

	}
	public static void doHttpGetComment(Context context, String PageSize,
			String id, String type, AsyncHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		// requestParams.put("type",type);
		requestParams.put("m", "content");
		requestParams.put("c", "index");
		requestParams.put("id", id);
		// requestParams.put("catid", type);
		if (type.equals("1")) {
			requestParams.put("a", "appLoveAdd");
		} else {
			requestParams.put("a", "appHateAdd");
		}
		mAsyncHttpClient.get(context, HttpUrls.FIRST, requestParams, handler);

	}

	*//** 璇勮鐨勮闂摼鎺� *//*
	public static void doHttpGetCommentData(Context context, String id,
			String catid, String content, AsyncHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		requestParams.put("id", id);
		requestParams.put("catid", catid);
		requestParams.put("content", content);
		String url = "http://d2.haolexiang.com/index.php?m=comment&c=appIndex&a=appAddComment&content="
				+ content + "&catid=" + catid + "&id=" + id;
		mAsyncHttpClient.get(context, url, null, handler);

	}

	public static void doHttpNewsList(Context context, String type,
			String time, String newsNum, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("", type);
		mAsyncHttpClient.get(context, "", params, handler);
	}
	
//	//鎺ㄩ�佹暟閲�
//	public static void doHttpGetNewCount(Context context, String type,
//			String inputtime, AsyncHttpResponseHandler handler) {
//		RequestParams params = new RequestParams();
//		params.put("inputtime", inputtime);
//		String url = "http://d2.haolexiang.com/index.php?m=comment&c=appIndex&a=appAddComment&content="
//				 + "&inputtime=" + inputtime;
//		mAsyncHttpClient.get(context, url, null, handler);
//	}

	*//*********************************************************************//*

	*//***************** 鐢靛瓙闃呰璇锋眰鏂规硶 **********************************//*
	*//**
	 * 鑾峰彇鐢靛瓙闃呰鐣岄潰 鎺ㄨ崘鏉傚織鍒楄〃鏁版嵁
	 * 
	 * @param tabId
	 *            鏂伴椈鍒囨崲鎸夐挳ID
	 * @param page
	 *            鍔犺浇椤垫暟
	 * @param articleTypes
	 *            闇�瑕佽幏鍙栫殑鍒嗙被
	 * @param handler
	 *//*
	@SuppressLint("ParserError")
	public static void doHttpGetList(Context context, boolean isNews,
			String articleTypes, String beforeTime, String afterTime,
			String keyword, JsonHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		requestParams.put("type", "");
		requestParams.put("isChoiceness", isNews + "");
		requestParams.put("PageNumber", "1");
		requestParams.put("PageSize", GlobalApplication.READ_PAGES);
		requestParams.put("beforeTime", beforeTime);
		requestParams.put("afterTime", afterTime);
		requestParams.put("keyword", keyword);
		mAsyncHttpClient.get(context, mHttpUrls.READ_ARTICLES, requestParams,
				handler);
	}

	*//**
	 * 鑾峰彇鐢靛瓙闃呰鐣岄潰 鏍囬鍒楄〃
	 * 
	 * @param tabId
	 *            鏂伴椈鍒囨崲鎸夐挳ID
	 * @param page
	 *            鍔犺浇椤垫暟
	 * @param count
	 *            姣忛〉鍔犺浇鏂伴椈鏁扮洰
	 * @param handler
	 *//*
	public static void doHttpGetContentTitle(Context context,
			JsonHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		// requestParams.put("tabId",tabId);
		// requestParams.put("page",page);
		// requestParams.put("count",count);
		mAsyncHttpClient.get(context, mHttpUrls.READ_ARTICLES_TITLE,
				requestParams, handler);
	}

	*//**
	 * 鑾峰彇鐢靛瓙闃呰 鍐呭
	 * 
	 * @param tabId
	 *            鏂伴椈鍒囨崲鎸夐挳ID
	 * @param page
	 *            鍔犺浇椤垫暟
	 * @param serviceID
	 *            鏂囩珷鍦ㄦ湇鍔″櫒鐨勫敮涓�鏍囪瘑
	 * @param handler
	 *//*
	@SuppressLint("ParserError")
	public static void doHttpGetContent(Context context, String serviceID,
			JsonHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		requestParams.put("Account_ID", "");
		// requestParams.put("page",page);
		// requestParams.put("count",count);
		mAsyncHttpClient.get(context, mHttpUrls.READ_ARTICLES_CONTENT + "/"
				+ serviceID, requestParams, handler);

	}

	*//**
	 * 鑾峰彇鐢靛瓙闃呰 鎼滅储鐣岄潰 鎺ㄨ崘鐨勭儹璇�
	 *//*
	public static void doHttpGetSearchWord(Context context,
			JsonHttpResponseHandler handler) {
		mAsyncHttpClient
				.get(context, mHttpUrls.READ_SEARCH_WORD, null, handler);

	}

	*//***************************************************//*
	*//************************ 榧庤蒋寰崥 ******************************//*
	*//**
	 * 
	 * @param context
	 *            涓婁笅鏂�
	 * @param userId
	 * @param page
	 * @param count
	 * @param mode
	 *            銆�瑕佽幏鍙栦粈涔堟牱鐨勬暟鎹�
	 *//*
	public static void doHttpGetBlogList(Context context, int userId, int page,
			int count, int mode, JsonHttpResponseHandler handler) {
		RequestParams requestParams = new RequestParams();
		requestParams.put("userid", String.valueOf(userId));
		requestParams.put("page", String.valueOf(page));
		requestParams.put("count", String.valueOf(count));
		mAsyncHttpClient.get(context, mHttpUrls.BLOG_ARTICLES_CONTENT,
				requestParams, handler);
	}
	*//*************************************************************//*
}
*/