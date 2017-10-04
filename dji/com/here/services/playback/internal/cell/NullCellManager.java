package com.here.services.playback.internal.cell;

import com.here.odnp.cell.ICellManager;
import com.here.odnp.cell.ICellManager.ICellListener;

public class NullCellManager implements ICellManager {
    private static final String TAG = "services.playback.internal.cell.NullCellManager";

    public void open(ICellListener iCellListener) {
    }

    public void close() {
    }

    public boolean startCellScan() {
        return false;
    }

    public void cancelCellScan() {
    }

    public boolean isCellSupported() {
        return true;
    }
}
