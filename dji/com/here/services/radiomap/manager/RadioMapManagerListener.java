package com.here.services.radiomap.manager;

public interface RadioMapManagerListener {

    public enum Status {
        Ok(0),
        Error(1),
        NotFoundError(4),
        InputOutputError(12),
        ConnectionError(13),
        DataTransferError(14),
        CancelError(15),
        DataCorruptedError(17),
        NoConnectionFoundError(20);
        
        private static final String TAG = "RadioMapManagerListener.Status";
        final int mCode;

        private Status(int i) {
            this.mCode = i;
        }

        public int toInt() {
            return this.mCode;
        }

        public static Status fromInt(int i) {
            for (Status status : values()) {
                if (status.mCode == i) {
                    return status;
                }
            }
            return Error;
        }
    }

    void onProgress(int i);

    void onQueryCompleted(Status status, long j);

    void onUpdateCompleted(Status status);
}
