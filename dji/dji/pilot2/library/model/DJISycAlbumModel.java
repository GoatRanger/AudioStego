package dji.pilot2.library.model;

import android.content.Context;
import com.dji.frame.c.d;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.pilot.usercenter.mode.g;

public class DJISycAlbumModel {
    public int downloadState = 0;
    public int fileLevel = 0;
    public boolean isDownLoadEnd = false;
    public boolean isOrg = false;
    public boolean isScreen = false;
    public boolean isThumb = false;
    public boolean isfileLeve2To = false;
    public boolean islocal = false;
    public g mLocalInfo;
    public DJIAlbumFileInfo mRemoteInfo;
    public String mScreenAbsPath = null;
    public String mThumbmAbsPath = null;
    public String orgPath = null;
    public long sortTime = 0;

    public DJISycAlbumModel(DJIAlbumFileInfo dJIAlbumFileInfo, g gVar) {
        this.mRemoteInfo = dJIAlbumFileInfo;
        this.mLocalInfo = gVar;
    }

    public DJISycAlbumModel(DJIAlbumFileInfo dJIAlbumFileInfo) {
        this.mRemoteInfo = dJIAlbumFileInfo;
        this.mLocalInfo = new g();
    }

    public DJISycAlbumModel(g gVar, boolean z) {
        if (z) {
            this.mLocalInfo = gVar;
            this.mRemoteInfo = new DJIAlbumFileInfo();
        }
    }

    public DJISycAlbumModel(g gVar, boolean z, boolean z2) {
        if (z) {
            this.mLocalInfo = gVar;
            this.mRemoteInfo = new DJIAlbumFileInfo();
            this.sortTime = gVar.h;
            this.orgPath = gVar.e;
            this.isOrg = true;
            this.fileLevel = 5;
        }
    }

    public void generateLocalInfo(Context context) {
        if (this.mRemoteInfo != null) {
            this.mLocalInfo.d = false;
            this.mLocalInfo.e = d.a(context, "DJI Album/") + this.mRemoteInfo.d();
        }
    }
}
