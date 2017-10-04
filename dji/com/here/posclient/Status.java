package com.here.posclient;

import android.util.SparseArray;

public enum Status {
    StatusInProgress(-1),
    Ok(0),
    GeneralError(1),
    InternalError(2),
    OomError(3),
    NotFoundError(4),
    AlreadyExistsError(5),
    UsageError(6),
    NotSupportedError(7),
    BusyError(8),
    TimeoutError(9),
    ConversionError(10),
    InvalidArgumentError(11),
    InputOutputError(12),
    ConnectionError(13),
    DataTransferError(14),
    CancelError(15),
    VersionMismatch(16),
    DataCorrupted(17),
    InjectionRejectedForCepError(18),
    InjectionRejectedError(19),
    NoConnectionFoundError(20),
    RadiomapDisabledError(21),
    NotEnabledError(22);
    
    private static SparseArray<Status> mLookupTable;
    private static final Object mLookupTableLock = null;
    private final int mStatusCode;

    static {
        mLookupTableLock = new Object();
    }

    private Status(int i) {
        this.mStatusCode = i;
    }

    public static Status fromInt(int i) {
        if (mLookupTable == null) {
            synchronized (mLookupTableLock) {
                if (mLookupTable == null) {
                    mLookupTable = createLookupTable();
                }
            }
        }
        Status status = (Status) mLookupTable.get(i);
        if (status != null) {
            return status;
        }
        throw new RuntimeException("Unknown status: " + i);
    }

    public int toInt() {
        return this.mStatusCode;
    }

    private static SparseArray<Status> createLookupTable() {
        SparseArray<Status> sparseArray = new SparseArray();
        for (Status status : values()) {
            sparseArray.append(status.mStatusCode, status);
        }
        return sparseArray;
    }
}
