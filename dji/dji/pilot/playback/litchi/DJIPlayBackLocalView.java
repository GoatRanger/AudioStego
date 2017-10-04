package dji.pilot.playback.litchi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.widget.b;
import dji.pilot.usercenter.f.c;
import dji.pilot.usercenter.f.d;
import dji.pilot.usercenter.mode.PhotoPreviewInfo;
import dji.pilot.usercenter.mode.g;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lecho.lib.hellocharts.model.h;

public class DJIPlayBackLocalView extends DJIPlayBackBaseView {
    protected static final String b = Environment.getExternalStorageDirectory().getPath();
    public static final int c = 1;
    public static final int d = 2;
    public static a e = null;
    private static final SparseArray<g> r = new SparseArray(12);
    public boolean a;
    public Thread f;
    private final int g;
    private final float h;
    private ExpandableListView i;
    private View j;
    private ProgressBar k;
    private DJITextView l;
    private OnGroupClickListener m;
    private OnClickListener n;
    private Context o;
    private f p;
    private ImageLoader q;
    private List<a> s;
    private List<g> t;
    private b u;
    private b v;
    private g w;

    public static class a extends Handler {
        private final WeakReference<DJIPlayBackLocalView> a;

        public a(DJIPlayBackLocalView dJIPlayBackLocalView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIPlayBackLocalView);
        }

        public void handleMessage(Message message) {
            DJIPlayBackLocalView dJIPlayBackLocalView = (DJIPlayBackLocalView) this.a.get();
            if (dJIPlayBackLocalView != null) {
                switch (message.what) {
                    case 1:
                        dJIPlayBackLocalView.k.setVisibility(8);
                        dJIPlayBackLocalView.l.setText(R.string.usercenter_album_empty);
                        dJIPlayBackLocalView.notifyDataChanged();
                        if (dJIPlayBackLocalView.s.size() > 0) {
                            dJIPlayBackLocalView.j.setVisibility(8);
                            return;
                        }
                        return;
                    case 2:
                        dJIPlayBackLocalView.e();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIPlayBackLocalView(Context context) {
        this(context, null);
    }

    public DJIPlayBackLocalView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIPlayBackLocalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 4;
        this.h = h.l;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.a = true;
        this.v = null;
        this.w = null;
        this.f = null;
        if (!isInEditMode()) {
            this.o = context;
            f();
        }
    }

    public void attachFragment(f fVar) {
        this.p = fVar;
    }

    public void detachFragment() {
        this.p = null;
    }

    public void selectPic(g gVar) {
        if (d.c(gVar.g)) {
            r.put(gVar.hashCode(), gVar);
            notifyDataChanged();
        }
    }

    public void selectAllPic() {
        if (this.s != null) {
            for (int i = 0; i < this.s.size(); i++) {
                for (int i2 = 0; i2 < ((a) this.s.get(i)).d().size(); i2++) {
                    selectPic((g) ((a) this.s.get(i)).d().get(i2));
                }
            }
        }
    }

    public void clearSelects() {
        r.clear();
        notifyDataChanged();
    }

    public void shareSelects() {
        int i = 0;
        int size = r.size();
        ArrayList arrayList = new ArrayList();
        if (size == 0) {
            Toast.makeText(this.o, R.string.usercenter_album_select_none, 0).show();
        } else {
            ArrayList arrayList2 = new ArrayList();
            while (i < size) {
                arrayList2.add(r.valueAt(i));
                i++;
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                String str = ((g) it.next()).e;
                if (new File(str).exists()) {
                    arrayList.add(Uri.fromFile(new File(str)));
                }
            }
            r.clear();
            notifyDataChanged();
        }
        if (arrayList.size() > 0) {
            Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
            intent.setType("image/*");
            intent.setFlags(268435456);
            this.o.startActivity(intent);
        }
    }

    public void deleteSelects() {
        if (r.size() == 0) {
            Toast.makeText(this.o, R.string.usercenter_album_select_none, 0).show();
        } else {
            a();
        }
    }

    public void deleteAlbum(int i) {
        boolean delete;
        if (i != -1) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < this.t.size()) {
                if (d.c(((g) this.t.get(i2)).g)) {
                    i3++;
                }
                if (i3 > i) {
                    break;
                }
                i2++;
            }
            File file = new File(((g) this.t.get(i2)).e);
            if (file.exists()) {
                delete = file.delete();
                Log.v("delete", "Photo is delete: " + delete);
                if (delete) {
                    this.t.remove(i2);
                }
            }
        } else if (this.w != null) {
            File file2 = new File(this.w.e);
            if (file2.exists()) {
                delete = file2.delete();
                Log.v("delete", "Video is delete: " + delete);
                if (delete) {
                    this.t.remove(this.w);
                }
            }
        }
        initAlbumGroup(this.t, this.s);
        notifyDataChanged();
    }

    private void a() {
        if (this.v == null) {
            this.v = b.a(this.o, (int) R.string.fpv_playback_del_image, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPlayBackLocalView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.c();
                }
            }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPlayBackLocalView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.d();
                }
            });
            this.v.setCancelable(true);
            this.v.setCanceledOnTouchOutside(true);
        }
        if (this.v != null && !this.v.isShowing()) {
            this.v.a((int) R.string.fpv_playback_del_image);
            this.v.show();
        }
    }

    private void b() {
        if (this.v != null && this.v.isShowing()) {
            this.v.dismiss();
            this.v = null;
        }
    }

    private void c() {
        b();
    }

    private void d() {
        b();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < r.size(); i++) {
            arrayList.add(r.valueAt(i));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            File file = new File(gVar.e);
            if (file.exists()) {
                Boolean valueOf = Boolean.valueOf(file.delete());
                Log.v("delete", "Pic Delete: " + valueOf);
                if (valueOf.booleanValue()) {
                    this.t.remove(gVar);
                }
            }
        }
        r.clear();
        initAlbumGroup(this.t, this.s);
        notifyDataChanged();
    }

    private void e() {
        this.k.setVisibility(0);
        this.j.setVisibility(0);
        this.l.setText(R.string.usercenter_cloudalbum_loading);
        this.f = new Thread(new Runnable(this) {
            final /* synthetic */ DJIPlayBackLocalView a;

            {
                this.a = r1;
            }

            public void run() {
                File file = new File(DJIPlayBackLocalView.b + h.d);
                if (file.isDirectory()) {
                    this.a.t.clear();
                    File[] listFiles = file.listFiles();
                    for (File a : listFiles) {
                        this.a.t.add(g.a(a, false));
                    }
                    this.a.s.clear();
                    this.a.sortPic(this.a.t, this.a.s);
                    DJIPlayBackLocalView.e.sendEmptyMessage(1);
                }
            }
        });
        this.f.start();
    }

    public void sortPic(List<g> list, List<a> list2) {
        super.sortPic(list, list2);
    }

    private void f() {
        this.q = ImageLoader.getInstance();
        this.n = new OnClickListener(this) {
            final /* synthetic */ DJIPlayBackLocalView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    this.a.c((g) view.getTag());
                } catch (Exception e) {
                }
            }
        };
        this.m = new OnGroupClickListener(this) {
            final /* synthetic */ DJIPlayBackLocalView a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                if (this.a.p.k() == 0) {
                    DJILogHelper.getInstance().LOGD("onGroupClick", "Click", false, true);
                    for (int i2 = 0; i2 < ((a) this.a.s.get(i)).d().size(); i2++) {
                        this.a.selectPic((g) ((a) this.a.s.get(i)).d().get(i2));
                    }
                }
                return true;
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.i = (ExpandableListView) findViewById(R.id.bgu);
            this.j = findViewById(R.id.bgv);
            this.l = (DJITextView) findViewById(R.id.bgx);
            this.k = (ProgressBar) findViewById(R.id.bgw);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) this.o.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int dimensionPixelSize = (displayMetrics.widthPixels - (getResources().getDimensionPixelSize(R.dimen.n3) * 5)) / 4;
            int i = (int) (((float) dimensionPixelSize) * h.l);
            this.i.setGroupIndicator(null);
            this.i.setOnGroupClickListener(this.m);
            this.t = new ArrayList();
            this.s = new ArrayList();
            e = new a(this);
            this.u = new b(this.o, 4, i, dimensionPixelSize, this.s, r, this.q, this.n, false);
            this.i.setAdapter(this.u);
            e();
            this.i.setOnScrollListener(new PauseOnScrollListener(this.q, true, true));
        }
    }

    public void notifyDataChanged() {
        this.u.notifyDataSetChanged();
        for (int i = 0; i < this.u.getGroupCount(); i++) {
            this.i.expandGroup(i);
        }
    }

    private void a(g gVar) {
        e.a("PlayBack_AlbumView_Button_SelectPhoto");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < this.s.size()) {
            int i4 = i3;
            i3 = i2;
            for (int i5 = 0; i5 < ((a) this.s.get(i)).d().size(); i5++) {
                g gVar2 = (g) ((a) this.s.get(i)).d().get(i5);
                PhotoPreviewInfo a = gVar2.a();
                if (a != null) {
                    if (gVar.equals(gVar2)) {
                        i4 = i3;
                    }
                    arrayList.add(a);
                    i3++;
                }
            }
            i++;
            i2 = i3;
            i3 = i4;
        }
        Bundle a2 = DJIPlayBackPhotoPreviewActivity.a(1, arrayList, i3);
        Intent intent = new Intent(this.o, DJIPlayBackPhotoPreviewActivity.class);
        intent.setFlags(131072);
        intent.putExtras(a2);
        if (this.o instanceof Activity) {
            ((Activity) this.o).startActivityForResult(intent, 1);
            com.dji.frame.c.b.a(this.o, dji.pilot.publics.objects.a.a);
        }
    }

    private void b(g gVar) {
        e.a("PlayBack_AlbumView_Button_SelectVideo");
        DJIPlayBackVideoPreviewActivity.a(this.o, gVar.b(), 0, dji.pilot.publics.objects.a.a);
        this.w = gVar;
    }

    private void c(g gVar) {
        int k = this.p.k();
        if (k == -1) {
            if (!c.b(gVar.e)) {
                Toast.makeText(this.o, R.string.usercenter_album_file_nonexist, 0).show();
            } else if (d.c(gVar.g)) {
                a(gVar);
            } else if (d.b(gVar.g)) {
                b(gVar);
            }
        } else if (k == 0 && d.c(gVar.g)) {
            k = gVar.hashCode();
            if (r.indexOfKey(k) >= 0) {
                r.delete(k);
            } else {
                r.put(k, gVar);
            }
            notifyDataChanged();
        }
    }
}
