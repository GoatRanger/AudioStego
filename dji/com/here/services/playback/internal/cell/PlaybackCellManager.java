package com.here.services.playback.internal.cell;

import com.here.odnp.cell.ICellManager;
import com.here.odnp.cell.ICellManager.ICellListener;
import com.here.services.playback.internal.util.PlaybackReader;

public class PlaybackCellManager implements ICellManager {
    private static final String TAG = "services.playback.internal.cell.PlaybackCellManager";
    private ICellListener mCellListener;
    private final PlaybackReader mPlaybackReader;

    public PlaybackCellManager(PlaybackReader playbackReader) {
        this.mPlaybackReader = playbackReader;
    }

    public void open(ICellListener iCellListener) {
        this.mCellListener = iCellListener;
        this.mPlaybackReader.addCellListener(iCellListener);
    }

    public void close() {
        this.mPlaybackReader.removeCellListener(this.mCellListener);
        this.mCellListener = null;
    }

    public boolean startCellScan() {
        return this.mPlaybackReader.startCellScan();
    }

    public void cancelCellScan() {
        this.mPlaybackReader.cancelCellScan();
    }

    public boolean isCellSupported() {
        return true;
    }
}
