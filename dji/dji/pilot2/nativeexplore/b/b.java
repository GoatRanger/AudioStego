package dji.pilot2.nativeexplore.b;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.b.f;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.nativeexplore.model.BottomCommentModel;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.nativeexplore.model.FollowEvent;
import dji.pilot2.nativeexplore.model.FollowResultModel;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.publics.b.a$i;
import dji.pilot2.utils.k;

public class b {
    private static b b;
    private Context a;
    private a c;

    public interface a {
        void a(boolean z);

        void a(boolean z, BottomCommentModel bottomCommentModel);

        void b(boolean z);
    }

    private b(Context context) {
        this.a = context;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public static synchronized b getInstance(Context context) {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b(context);
            }
            bVar = b;
        }
        return bVar;
    }

    public synchronized void a(String str) {
        c.b(this.a).a(str, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                if (str != null) {
                    this.a.c.a(true, (BottomCommentModel) h.b(str, BottomCommentModel.class));
                }
            }

            public void a(Throwable th, int i, String str) {
                if (this.a.c != null) {
                    this.a.c.a(false, null);
                }
            }
        });
    }

    public synchronized void a(String str, String str2) {
        if (f.getInstance().c()) {
            dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
            bVar.a("comment[content]", str2);
            bVar.a("token", f.getInstance().n());
            Log.i("zhangchen", "url:" + str);
            Log.i("zhangchen", "comment:" + str2);
            Log.i("zhangchen", "token:" + f.getInstance().n());
            c.b(this.a).b(str, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    if (str != null) {
                        this.a.c.a(true);
                    }
                }

                public void a(Throwable th, int i, String str) {
                    if (this.a.c != null) {
                        this.a.c.a(false);
                    }
                }
            });
        } else {
            this.a.startActivity(new Intent(this.a, DJIAccountSignActivity.class));
        }
    }

    public synchronized void b(String str) {
        if (f.getInstance().c()) {
            dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
            bVar.a("token", f.getInstance().n());
            c.b(this.a).d(str, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    if (this.a.c != null) {
                        this.a.c.b(true);
                    }
                }

                public void a(Throwable th, int i, String str) {
                    if (this.a.c != null) {
                        this.a.c.b(false);
                    }
                }
            });
        } else {
            this.a.startActivity(new Intent(this.a, DJIAccountSignActivity.class));
        }
    }

    public synchronized void c(String str) {
        if (f.getInstance().c()) {
            dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
            bVar.a("token", f.getInstance().n());
            c.b(this.a).b(str, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    if (this.a.c != null) {
                        this.a.c.a(true);
                    }
                }

                public void a(Throwable th, int i, String str) {
                    if (this.a.c != null) {
                        this.a.c.a(false);
                    }
                }
            });
        } else {
            this.a.startActivity(new Intent(this.a, DJIAccountSignActivity.class));
        }
    }

    public synchronized void d(String str) {
        if (f.getInstance().c()) {
            dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
            bVar.a("token", f.getInstance().n());
            c.b(this.a).d(str, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    if (this.a.c != null) {
                        this.a.c.b(true);
                    }
                }

                public void a(Throwable th, int i, String str) {
                    if (this.a.c != null) {
                        this.a.c.b(false);
                    }
                }
            });
        } else {
            this.a.startActivity(new Intent(this.a, DJIAccountSignActivity.class));
        }
    }

    public synchronized void b(final String str, final String str2) {
        String a = k.a(str, str2);
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a("token", f.getInstance().n());
        c.b(this.a).b(a, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ b c;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                if (this.c.c != null) {
                    this.c.c.a(true);
                }
                dji.thirdparty.a.c.a().e(this.c.a(str, str2, true));
            }

            public void a(Throwable th, int i, String str) {
                if (this.c.c != null) {
                    this.c.c.a(false);
                }
            }
        });
    }

    public synchronized void c(final String str, final String str2) {
        String b = k.b(str, str2);
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a("token", f.getInstance().n());
        c.b(this.a).d(b, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ b c;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                if (this.c.c != null) {
                    this.c.c.b(true);
                }
                dji.thirdparty.a.c.a().e(this.c.a(str, str2, false));
            }

            public void a(Throwable th, int i, String str) {
                if (this.c.c != null) {
                    this.c.c.b(false);
                }
            }
        });
    }

    public dji.pilot2.nativeexplore.model.a a(String str, String str2, boolean z) {
        dji.pilot2.nativeexplore.model.a.a aVar = z ? dji.pilot2.nativeexplore.model.a.a.favorite : dji.pilot2.nativeexplore.model.a.a.disfavorite;
        dji.pilot2.nativeexplore.model.a.b bVar = dji.pilot2.nativeexplore.model.a.b.video;
        if ("photos".equals(str)) {
            bVar = dji.pilot2.nativeexplore.model.a.b.photo;
        } else if ("videos".equals(str)) {
            bVar = dji.pilot2.nativeexplore.model.a.b.video;
        } else if ("stories".equals(str)) {
            bVar = dji.pilot2.nativeexplore.model.a.b.guide;
        }
        return new dji.pilot2.nativeexplore.model.a(aVar, bVar, str2);
    }

    public synchronized void a(String str, ExploreItem exploreItem) {
        if (f.getInstance().c()) {
            String str2 = "https://www.skypixel.com/api/users/" + str + a$i.S;
            dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
            bVar.a("token", f.getInstance().n());
            c.b(this.a).c(str2, bVar, new dji.pilot2.nativeexplore.a.d.a(this, exploreItem) {
                final /* synthetic */ b a;

                public void a(String str, Object obj) {
                    if (obj instanceof ExploreItem) {
                        ExploreItem exploreItem = (ExploreItem) obj;
                        FollowResultModel followResultModel = (FollowResultModel) h.b(str, FollowResultModel.class);
                        if (followResultModel != null && followResultModel.status == 0) {
                            ((ExploreItem) obj).isFollowed = true;
                            dji.thirdparty.a.c.a().e(new FollowEvent(dji.pilot2.nativeexplore.model.FollowEvent.b.FOLLOW, new dji.pilot2.nativeexplore.model.FollowEvent.a(f.getInstance().i(), f.getInstance().m(), a$e.a + f.getInstance().e(), f.getInstance().h().u), new dji.pilot2.nativeexplore.model.FollowEvent.a(exploreItem.userId, exploreItem.userName, exploreItem.userAvatarUrl, exploreItem.userCountry)));
                        }
                    }
                }

                public void a(Throwable th, int i, String str) {
                    DJILogHelper.getInstance().LOGI("zhang", "follow failed: " + str);
                }
            });
        } else {
            this.a.startActivity(new Intent(this.a, DJIAccountSignActivity.class));
        }
    }
}
