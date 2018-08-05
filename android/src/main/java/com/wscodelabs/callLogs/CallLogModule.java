package com.wscodelabs.callLogs;

import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.database.Cursor;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

public class CallLogModule extends ReactContextBaseJavaModule {

    private Context context;

    public CallLogModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    @Override
    public String getName() {
        return "CallLogs";
    }

    @ReactMethod
    public void loadAll(Promise promise) {
        load(-1, promise);
    }

    @ReactMethod
    public void load(int limit, Promise promise) {
        Cursor cursor = this.context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");

        WritableArray result = Arguments.createArray();

        if (cursor == null) {
            promise.resolve(result);
            return;
        }

        int callLogCount = 0;

        final int NUMBER_COLUMN_INDEX = cursor.getColumnIndex(Calls.NUMBER);
        final int TYPE_COLUMN_INDEX = cursor.getColumnIndex(Calls.TYPE);
        final int DATE_COLUMN_INDEX = cursor.getColumnIndex(Calls.DATE);
        final int DURATION_COLUMN_INDEX = cursor.getColumnIndex(Calls.DURATION);
        final int NAME_COLUMN_INDEX = cursor.getColumnIndex(Calls.CACHED_NAME);

        while (cursor.moveToNext() && this.shouldContinue(limit, callLogCount++)) {
            String phoneNumber = cursor.getString(NUMBER_COLUMN_INDEX);
            int duration = cursor.getInt(DURATION_COLUMN_INDEX);
            String name = cursor.getString(NAME_COLUMN_INDEX);

            String timestampStr = cursor.getString(DATE_COLUMN_INDEX);
            DateFormat df = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.MEDIUM, SimpleDateFormat.MEDIUM);
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = df.format(new Date(Long.valueOf(timestampStr)));

            String type = this.resolveCallType(cursor.getInt(TYPE_COLUMN_INDEX));

            WritableMap callLog = Arguments.createMap();
            callLog.putString("phoneNumber", phoneNumber);
            callLog.putInt("duration", duration);
            callLog.putString("name", name);
            callLog.putString("timestamp", timestampStr);
            callLog.putString("dateTime", dateTime);
            callLog.putString("type", type);
            callLog.putInt("rawType", cursor.getInt(TYPE_COLUMN_INDEX));

            result.pushMap(callLog);
        }

        cursor.close();

        promise.resolve(result);
    }

    private String resolveCallType(int callTypeCode) {
        switch (callTypeCode) {
            case Calls.OUTGOING_TYPE:
                return "OUTGOING";
            case Calls.INCOMING_TYPE:
                return "INCOMING";
            case Calls.MISSED_TYPE:
                return "MISSED";
            default:
                return "UNKNOWN";
        }
    }

    private boolean shouldContinue(int limit, int count) {
        return limit < 0 || count < limit;
    }
}

