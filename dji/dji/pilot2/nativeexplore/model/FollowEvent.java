package dji.pilot2.nativeexplore.model;

public class FollowEvent {
    public b action;
    public a object;
    public a subject;

    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;

        public a(String str, String str2, String str3, String str4) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
        }
    }

    public enum b {
        FOLLOW,
        UNFOLLOW
    }

    public FollowEvent(b bVar, a aVar, a aVar2) {
        this.action = bVar;
        this.subject = aVar;
        this.object = aVar2;
    }
}
