package com.babycare.httpconnect;

import org.json.JSONException;
import org.json.JSONTokener;

import android.os.Message;

/**
 * 		asyncHttpClient.post(null,new SerializableHttpResponseHandler() {
			@Override
			public Object onParseResponse(String cotent) {
				return null;
			}
		});
 * http璇锋眰瀵硅皟鍑芥暟,灏唄ttp杩斿洖鐨勫唴瀹瑰皝瑁呬负鑷繁鎯宠鐨勬暟鎹牸寮�
 * 
 */
public abstract class ObjectHttpResponseHandler extends AsyncHttpResponseHandler {
	protected static final int SUCCESS_SERILIZABLE_MESSAGE = 102;

	public ObjectHttpResponseHandler() {
		super();
	}
	
	/**
	 * 鎴愬姛杩斿洖
	 * @param response
	 */
	public void onSuccess(Object response) {
	
	}
	
	public void onSuccess(int statusCode, Object response) {
		onSuccess(response);
	}

	/**
	 * 澶辫触杩斿洖
	 */
	public void onFailure(Throwable e, String errorResponse) {
		
	}
	
	/**
	 * 蹇呴』瀹炵幇姝ゆ柟娉曞皢string鍐呭灏佽涓鸿嚜宸卞畾涔夌殑鏁版嵁绫诲瀷
	 * @param cotent
	 * @return
	 */
	public abstract Object onParseResponse(String cotent);

	/**
	 * 鎵ц鍦ㄥ悗鍙扮嚎绋�
	 */
	@Override
	protected void sendSuccessMessage(int statusCode, String responseBody) {
			Object jsonResponse = onParseResponse(responseBody);
			sendMessage(obtainMessage(SUCCESS_SERILIZABLE_MESSAGE, new Object[] {
					statusCode, jsonResponse }));
	}

	/**
	 * 璋冪敤ui绾跨▼
	 */
	@Override
	protected void handleMessage(Message msg) {
		switch (msg.what) {
		case SUCCESS_SERILIZABLE_MESSAGE:
			Object[] response = (Object[]) msg.obj;
			handleSuccessSeriableMessage(((Integer) response[0]).intValue(),
					response[1]);
			break;
		default:
			super.handleMessage(msg);
		}
	}

	protected void handleSuccessSeriableMessage(int statusCode, Object jsonResponse) {
		onSuccess(statusCode,jsonResponse);
	}

	@Override
	protected void handleFailureMessage(Throwable e, String responseBody) {
		onFailure(e, responseBody);
	}
	
	/**
	 * 灏唖tring杞崲涓簀son,杞崲澶辫触杩斿洖null
	 * @param responseBody
	 * @throws JSONException
	 */
    protected Object parseResponseToJson(String responseBody) throws JSONException {
        Object result = null;
		responseBody = responseBody.trim();
		if(responseBody.startsWith("{") || responseBody.startsWith("[")) {
			result = new JSONTokener(responseBody).nextValue();
		}
		if (result == null) {
			result = responseBody;
		}
		return result;
    }

}
