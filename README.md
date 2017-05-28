# call log package for android in react-native


## installation:

```
 npm install --save react-native-call-log
 
```

## Option: Automatic
```
 react-native link
 
```

## Usage

```
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


