package com.wscodelabs.callLogs;

import android.provider.CallLog;
import android.provider.CallLog.Calls;
import java.lang.StringBuffer;
import android.database.Cursor;
import java.util.Date;
import android.content.Context;
import org.json.*;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;

public class CallLogModule extends ReactContextBaseJavaModule {

    private Context context;

  public CallLogModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.context= reactContext;
  }

  @Override
  public String getName() {
    return "CallLogs";
  }

  @ReactMethod
public void show( Callback callBack) {
    
    StringBuffer stringBuffer = new StringBuffer();
    Cursor cursor = this.context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
            null, null, null, CallLog.Calls.DATE + " DESC");
    int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
    int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
    int date = cursor.getColumnIndex(CallLog.Calls.DATE);
    int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);  
    int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
    JSONArray callArray = new JSONArray();
    while (cursor.moveToNext()) {
        String phNumber = cursor.getString(number);
        String callType = cursor.getString(type);
        String callDate = cursor.getString(date);
        Date callDayTime = new Date(Long.valueOf(callDate));
        String callDuration = cursor.getString(duration);
        String dir = null;
        int dircode = Integer.parseInt(callType);
        switch (dircode) {
        case CallLog.Calls.OUTGOING_TYPE:
            dir = "OUTGOING";
            break;
        case CallLog.Calls.INCOMING_TYPE:
            dir = "INCOMING";
            break;

        case CallLog.Calls.MISSED_TYPE:
            dir = "MISSED";
            break;
        }

        JSONObject callObj = new JSONObject();
        try{
            callObj.put("phoneNumber",phNumber);
            callObj.put("callType", dir);
            callObj.put("callDate", callDate);
            callObj.put("callDuration", callDuration);
            callObj.put("callDayTime", callDayTime);
            callArray.put(callObj); 
        }
        catch(JSONException e){
            e.printStackTrace();
        }
             
        
    }
    cursor.close();
    callBack.invoke(callArray.toString());
}
}