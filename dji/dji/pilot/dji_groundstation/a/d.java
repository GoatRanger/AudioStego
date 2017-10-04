package dji.pilot.dji_groundstation.a;

public class d {
    private static final String a = "GSMode";

    public enum c {
        None(0, 0),
        PointOfInterest(16, 16),
        PointOfInsterst_Start(17, 16),
        PointOfInsterst_Status(18, 16),
        WayPoint(32, 32),
        WayPoint_Collection(33, 32),
        WayPoint_Status(34, 32),
        WayPoint_Setting(35, 32),
        WayPoint_SetReturnHomeHeight(36, 32),
        WayPoint_UploadMission(37, 32),
        WayPoint_PageAddNew(38, 32),
        WayPoint_DownloadMission(39, 32),
        FollowMe(48, 48),
        FollowMe_Status(49, 48),
        CourseLock(64, 64),
        CourseLock_Status(65, 64),
        HomeLock(80, 80),
        HomeLock_Status(81, 80),
        Pano(96, 96),
        TerrainTracking(dji.pilot.usercenter.protocol.d.k, dji.pilot.usercenter.protocol.d.k),
        TerrainTracking_Status(113, dji.pilot.usercenter.protocol.d.k),
        Tripod(128, 128);
        
        private int w;
        private int x;

        private c(int i, int i2) {
            this.w = 0;
            this.x = 0;
            this.w = i;
            this.x = i2;
        }

        public int a() {
            return this.w;
        }

        public int b() {
            return this.x;
        }

        public boolean a(c cVar) {
            return cVar.b() == this.x;
        }
    }
}
