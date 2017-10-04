package dji.pilot2.nativeexplore.c;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.c.a.b;
import dji.pilot2.nativeexplore.view.CircleProgressView;

public class c extends dji.pilot2.publics.object.a implements OnClickListener, b {
    private CircleProgressView b;
    private DJIStateTextView c;
    private a d;
    private dji.pilot2.c.b.b e;
    private String f;
    private String g;
    private String h;

    public interface a {
        void a();

        void a(String str, String str2, String str3, String str4);

        void b();

        void c();
    }

    public c(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public c(Context context, int i) {
        super(context, i);
    }

    public c(Context context) {
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_explorepost_dialog);
        setCanceledOnTouchOutside(false);
        c();
    }

    private void c() {
        this.b = (CircleProgressView) findViewById(R.id.cmq);
        this.c = (DJIStateTextView) findViewById(R.id.cms);
        this.c.setOnClickListener(this);
    }

    public void a(String str, String str2, String str3, String str4) {
        this.f = str;
        this.g = str2;
        this.h = str3;
        this.e = new dji.pilot2.c.b.a.a.a.a(str, str2, str3, str4);
        this.e.a((b) this);
        dji.pilot2.c.b.a.getInstance().a(this.e);
    }

    public void b(String str, String str2, String str3, String str4) {
        this.f = str;
        this.g = str2;
        this.h = str3;
        this.e = new dji.pilot2.c.b.a.a.b.a(str, str2, str3, str4);
        this.e.a((b) this);
        dji.pilot2.c.b.a.getInstance().a(this.e);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, a aVar) {
        c cVar = new c(context);
        cVar.a(aVar);
        cVar.a(str, str2, str3, str4);
        cVar.show();
    }

    public static void a(Context context, String str, String str2, String str3, String str4, a aVar, int i) {
        c cVar = new c(context);
        cVar.a(aVar);
        if (i == 2) {
            cVar.b(str, str2, str3, str4);
        } else {
            cVar.a(str, str2, str3, str4);
        }
        cVar.show();
    }

    public void a() {
        DJILogHelper.getInstance().LOGI("bob", "ExplorePostDialog onUploadStart");
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGI("bob", "ExplorePostDialog onUploadSuccess");
        if (this.b != null) {
            this.b.setmCurProgress(100);
        }
        if (this.d != null) {
            this.d.a(str, this.f, this.g, this.h);
        }
        dismiss();
    }

    public void b() {
        DJILogHelper.getInstance().LOGI("bob", "ExplorePostDialog onUploadFailed");
        if (this.d != null) {
            this.d.b();
        }
        dismiss();
    }

    public void f() {
        DJILogHelper.getInstance().LOGI("bob", "ExplorePostDialog onCancel");
        if (this.d != null) {
            this.d.a();
        }
        dismiss();
    }

    public void a(int i) {
        DJILogHelper.getInstance().LOGI("bob", "ExplorePostDialog onUploadProgress " + i);
        if (this.b != null) {
            if (i == 100) {
                i = 99;
            }
            this.b.setmCurProgress(i);
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cms:
                if (this.d != null) {
                    this.d.a();
                }
                dji.pilot2.c.b.a.getInstance().b(this.e);
                dismiss();
                return;
            default:
                return;
        }
    }
}
