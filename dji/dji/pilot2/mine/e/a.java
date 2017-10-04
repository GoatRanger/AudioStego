package dji.pilot2.mine.e;

import dji.pilot2.mine.jsonbean.NewArtworkListModel.AccountModel;
import dji.pilot2.nativeexplore.model.ExploreListModel.ExploreItemModel;
import dji.pilot2.utils.k;

public abstract class a implements Comparable<a> {
    public static final String a = "undefined";
    public static final String b = "photo";
    public static final String c = "video";
    protected String d;
    protected long e;
    public AccountModel f;
    public String g;
    public String h;
    public String i;
    public String j;
    public boolean k;
    public int l;
    public int m;
    public int n;
    public long o;
    public ExploreItemModel p;
    protected a q = new a();

    public static class a {
        public String a = "";
        public String b = "";
        public String c = "";
        public String d = "";
        public String e = "";
        public String f = "";

        public String a() {
            return a(this.a);
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }

        public String e() {
            return a(this.e);
        }

        public String f() {
            return this.f;
        }

        private String a(String str) {
            if ("".equals(str) || str == null) {
                return str;
            }
            if (str.startsWith("//")) {
                return "http:" + str;
            }
            if (str.startsWith("http")) {
                return str;
            }
            return k.i() + str;
        }
    }

    public enum b {
        ThumbnailSize_XLarge("1920x1920"),
        ThumbnailSize_Large("1024x1024"),
        ThumbnailSize_Mid("256x256"),
        ThumbnailSize_Small("128x128"),
        ThumbnailSize_Smaller("64x64");
        
        String f;

        private b(String str) {
            this.f = str;
        }

        public String toString() {
            return this.f;
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return a((a) obj);
    }

    public a(ExploreItemModel exploreItemModel) {
        this.p = exploreItemModel;
        this.d = exploreItemModel.id;
        this.e = exploreItemModel.created_at;
        this.f = new AccountModel();
        this.f.id = exploreItemModel.account_id;
        this.f.name = exploreItemModel.account_name;
        this.f.avatar = exploreItemModel.account_avatar;
        this.g = exploreItemModel.type;
        this.h = exploreItemModel.title;
        this.i = exploreItemModel.country;
        if ("photos".equals(exploreItemModel.type)) {
            this.j = exploreItemModel.thumb_l_url;
        } else if ("videos".equals(exploreItemModel.type)) {
            this.j = exploreItemModel.thumbnail_large_url;
        }
        this.k = exploreItemModel.is_liked;
        this.l = exploreItemModel.likes_count;
        this.m = exploreItemModel.comment_count;
        this.n = exploreItemModel.views_count;
        this.o = exploreItemModel.updated_at;
    }

    public int a(a aVar) {
        long j = this.e - aVar.e;
        if (j == 0) {
            return 0;
        }
        return j < 0 ? 1 : -1;
    }

    public String a() {
        return a;
    }

    public String b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }

    protected void a(long j) {
        this.e = j;
    }

    public String d() {
        return this.q.a;
    }

    public void a(String str) {
        this.q.a = e(str);
    }

    public void a(String str, b bVar) {
        this.q.a = e(str) + bVar.f;
    }

    protected void b(String str) {
        this.q.b = e(str);
    }

    public String e() {
        return this.q.b;
    }

    public String f() {
        return this.q.c;
    }

    public void c(String str) {
        this.q.c = str;
    }

    public String g() {
        return this.q.d;
    }

    public void d(String str) {
        this.q.d = str;
    }

    public a h() {
        return this.q;
    }

    private String e(String str) {
        if (str == null || str.startsWith("http") || str.startsWith(com.alipay.sdk.b.b.a) || !str.startsWith("//")) {
            return str;
        }
        return "http:" + str;
    }
}
