package dji.pilot2.share.d;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import dji.pilot2.mine.e.a.a;
import dji.thirdparty.afinal.f.c;
import java.io.File;

public class b {
    private a a;
    private String b;
    private Context c;
    private c<File> d = null;
    private final OnCancelListener e = new OnCancelListener(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onCancel(DialogInterface dialogInterface) {
            if (this.a.d != null) {
                this.a.d.a(true);
            }
        }
    };

    public b(Context context) {
        this.c = context;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(final dji.pilot2.share.e.a.b bVar) {
        if (this.a != null && this.a.f() != null && !this.a.f().equals("") && new File(this.a.f()).exists()) {
            dji.pilot2.share.f.b.a(this.c, this.a.c(), this.a.d() + "\n" + this.a.b(), this.a.f(), this.a.b(), bVar, dji.pilot2.share.e.a.a.CONTENT_VIDEO, dji.pilot2.share.b.b.a.EDIT_UPLOAD);
        } else if (this.a != null && !b(this.a).equals("")) {
            final dji.pilot2.share.b.a aVar = new dji.pilot2.share.b.a(this.c);
            aVar.setOnCancelListener(this.e);
            String b = b(this.a);
            this.d = com.dji.frame.c.c.b(this.c).a(b, this.c.getExternalCacheDir().getPath() + String.format("/%d.jpg", new Object[]{Long.valueOf(System.currentTimeMillis())}), new dji.thirdparty.afinal.f.a<File>(this) {
                final /* synthetic */ b c;

                public void a(File file) {
                    if (aVar != null && aVar.isShowing()) {
                        aVar.dismiss();
                    }
                    dji.pilot2.share.f.b.a(this.c.c, this.c.a.c(), this.c.a.d() + "\n" + this.c.a.b(), file.getPath(), this.c.a.b(), bVar, dji.pilot2.share.e.a.a.CONTENT_VIDEO, dji.pilot2.share.b.b.a.EDIT_UPLOAD);
                }

                public void a(boolean z) {
                    if (aVar != null) {
                        aVar.show();
                    }
                }

                public void a(long j, long j2) {
                }

                public void a(Throwable th, int i, String str) {
                    if (aVar != null && aVar.isShowing()) {
                        aVar.dismiss();
                    }
                    if (this.c.d != null && !this.c.d.e()) {
                        dji.pilot2.share.f.b.a(this.c.c, this.c.a.c(), this.c.a.d() + "\n" + this.c.a.b(), null, this.c.a.b(), bVar, dji.pilot2.share.e.a.a.CONTENT_VIDEO, dji.pilot2.share.b.b.a.EDIT_UPLOAD);
                    }
                }
            });
        }
    }

    public void b(dji.pilot2.share.e.a.b bVar) {
        if (this.a != null && this.a.f() != null && !this.a.f().equals("") && new File(this.a.f()).exists()) {
            dji.pilot2.share.f.b.a(this.c, this.a.c(), this.a.d() + "\n" + this.a.b(), this.a.f(), this.a.b(), bVar, dji.pilot2.share.e.a.a.CONTENT_IMG, dji.pilot2.share.b.b.a.EDIT_UPLOAD);
        }
    }

    private static String b(a aVar) {
        if (aVar != null && aVar.e() != null && !aVar.e().equals("")) {
            return aVar.e();
        }
        if (aVar == null || aVar.a() == null || aVar.a().equals("")) {
            return "";
        }
        return aVar.a();
    }
}
