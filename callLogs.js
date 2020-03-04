import { NativeModules } from 'react-native';

const {CallLogs: NativeCallLogs} = NativeModules

class CallLogs {
  static async load(limit, filter) {
    if (!filter) return NativeCallLogs.load(limit)
    const {minTimestamp, maxTimestamp, phoneNumbers} = filter
    const phoneNumbersArray = Array.isArray(phoneNumbers) ? phoneNumbers : typeof phoneNumbers === 'string' ? [phoneNumbers] : []
    return NativeCallLogs.loadWithFilter(
      limit,
      {
        minTimestamp: minTimestamp ? minTimestamp.toString() : undefined,
        maxTimestamp: maxTimestamp ? maxTimestamp.toString() : undefined,
        phoneNumbers: JSON.stringify(phoneNumbersArray)
      }
    )
  }

  static async loadAll() {
    return NativeCallLogs.loadAll()
  }
}

module.exports = CallLogs;
