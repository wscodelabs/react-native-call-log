declare namespace CallLogs {
  export interface CallFilter {
    minTimestamp?: number;
    maxTimestamp?: number;
    phoneNumbers?: string;
  }

  export interface CallLog {
    phoneNumber: string;
    duration: number;
    name: string;
    timestamp: string;
    dateTime: string;
    type: 'OUTGOING' | 'INCOMING' | 'MISSED' | 'UNKNOWN';
    rawType: number;
  }

  const load: (limit: number, filter?: CallFilter) => Promise<CallLog[]>;

  const loadAll: () => Promise<CallLog[]>;
}

export = CallLogs;
