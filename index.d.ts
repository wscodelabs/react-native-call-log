export type CallType =
    'OUTGOING'
  | 'INCOMING'
  | 'MISSED'
  | 'VOICEMAIL'
  | 'REJECTED'
  | 'BLOCKED'
  | 'ANSWERED_EXTERNALLY'
  | 'UNKNOWN';

export interface CallFilter {
  minTimestamp?: number;
  maxTimestamp?: number;
  types?: CallType | CallType[];
  phoneNumbers?: string | string[];
}

export interface CallLog {
  phoneNumber: string;
  duration: number;
  name: string;
  timestamp: string;
  dateTime: string;
  type: CallType;
  rawType: number;
}

export function load(limit: number, filter?: CallFilter): Promise<CallLog[]>;

export function loadAll(): Promise<CallLog[]>;

