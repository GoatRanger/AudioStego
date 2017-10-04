package dji.pilot2.library;

public enum a {
    MomentSelect(1),
    MomentUnSelect(2),
    MomentDelete(3),
    MomentMainDelete(4),
    MOMENTCUT(5),
    MEDIASDDOWNLOADEND(6),
    VideoAllDelete(7),
    SELECTCLEAR(8),
    PhotoUpdate(9),
    PhotoDIsConnect(10),
    PhotoDownLibrary(11),
    PhotoDownLibraryEnd(12),
    PhotoDownNotify(13),
    PhotoTake(14),
    PhotoTakeClick(15),
    PhotoDownLoadFinish(16),
    PhotoDownBegin(17),
    PhotoDownLoadIng(18),
    PhotoSelect(19),
    PhotoUnSelect(20),
    PhotoDelete(21),
    PhotoMainDelete(22),
    THUMBNAIL_REFRESH(23),
    SCREENNAIL_REFRESH(24),
    PhotoAllDelete(25),
    MainButtonClear(26),
    ResetLibraryLayout(27),
    CacheDelete(28),
    AppScanFinish(29),
    MomentUpdateByAdd(30);
    
    private int E;

    private a(int i) {
        this.E = 0;
        this.E = i;
    }

    public int a() {
        return this.E;
    }
}
