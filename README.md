# call log package for android in react-native


## Installation:
Run `npm install --save react-native-call-log`
 

### Android

#### Option: Automatic(with `rnpm`)
`react-native link`
#### Option: Manually
* Edit your `android/settings.gradle` to look like this (exclude +)

```diff
+ include ':react-native-call-log'
+ project(':react-native-call-log').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-call-log/android')
```
* Edit your `MainApplication.java` from ( `android/app/src/main/java/...`) to look like this (exclude +)
```diff
+ import com.wscodelabs.callLogs.CallLogPackage;

@Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
+         new CallLogPackage()
      );
    }
```

## Permission
Add permission to `android/app/src/mainAndroidMenifest.xml `file 
```xml
 <uses-permission android:name="android.permission.READ_CALL_LOG"></uses-permission>
```
## Usage
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
## Example 
Clone or download the repository then Run `cd Example && npm install`

#### Screenshot
[![callrecord.jpg](https://s23.postimg.org/uxrtt72wb/callrecord.jpg)](https://postimg.org/image/st7gs419j/)

