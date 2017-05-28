# call log package for android in react-native


## installation:

```javascript
 npm install --save react-native-call-log
 
```

## Option: Automatic
```javascript
 react-native link
 
```


## Usage
Add permission to `AndroidMenifest.xml `file 
```xml
 <uses-permission android:name="android.permission.READ_CALL_LOG"></uses-permission>
```

```javascript
 import CallLogs from 'react-native-call-log'
 
 // fetch call logs data
 
 CallLogs.show((logs) =>{
  // parse logs into json format
   const parsedLogs = JSON.parse(logs);
   
  // logs data format
  /*
    [
      { 
        phoneNumber: '9889789797', 
        callType: 'OUTGOING | INCOMING | MISSED',
        callDate: timestamp,
        callDuration: 'duration of call in sec',
        callDayTime: Date()
      },
      .......
     ]
  */
 });
```


