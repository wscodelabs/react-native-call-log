# call log package for android in react-native


## Installation:
Run `yarn add react-native-call-log`
 

### Android

#### Option: Automatic
`react-native link`
#### Option: Manually
* Edit your `android/settings.gradle` to look like this (exclude +)

```diff
+ include ':react-native-call-log'
+ project(':react-native-call-log').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-call-log/android')
```

* Edit your `android/app/build.gradle` (note: **app** folder) to look like this (exclude +)

 ```diff
dependencies {
 + implementation project(':react-native-call-log')
 }
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

## Usage

```javascript
import { PermissionsAndroid } from 'react-native';
import CallLogs from 'react-native-call-log'
 
 componentDidMount =  async() => {
    try {
      const granted = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.READ_CALL_LOG,
        {
          title: 'Call Log Example',
          message:
            'Access your call logs',
          buttonNeutral: 'Ask Me Later',
          buttonNegative: 'Cancel',
          buttonPositive: 'OK',
        }
      )
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        console.log(CallLogs);
        CallLogs.load(5).then(c => console.log(c));
      } else {
        console.log('Call Log permission denied');
      }
    }
    catch (e) {
      console.log(e);
    }
   }
```

## Methods 
Methods       | Description
------------- | -------------
load(LIMIT)   | `LIMIT: number` get maximum number of call logs.  
loadAll()        | get all call logs 

## Example 
Clone or download the repository then Run `cd Example && npm install`

