package dji.pilot2.library;

import android.content.Context;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.pilot.fpv.d.b;
import dji.pilot.playback.litchi.h;
import dji.pilot.usercenter.mode.g;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.library.model.DJISycAlbumModel;
import dji.thirdparty.a.c;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class e {
    public static int a = 0;
    private static Context g;
    private static e m = null;
    int b = 0;
    boolean c = false;
    Runnable d = new Runnable(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void run() {
            while (this.a.e) {
                while (this.a.f) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!this.a.d()) {
                    break;
                }
                int i;
                if (this.a.h.size() > 0) {
                    for (i = 0; i < this.a.h.size(); i++) {
                        this.a.j.add(this.a.h.get(i));
                    }
                    this.a.a(this.a.j);
                    this.a.h.clear();
                    this.a.a(true);
                }
                if (this.a.i.size() > 0) {
                    DJILogHelper.getInstance().LOGD("", this.a.i.size() + "mDeleteDownList.size()", true, true);
                    for (i = 0; i < this.a.i.size(); i++) {
                        this.a.j.remove(this.a.i.get(i));
                    }
                    this.a.a(this.a.j);
                    this.a.i.clear();
                }
                if (this.a.k.size() > 0 && this.a.j.size() == 0 && this.a.h.size() == 0) {
                    for (i = 0; i < this.a.k.size(); i++) {
                        this.a.j.add(this.a.k.get(i));
                    }
                    this.a.a(this.a.j);
                    this.a.k.clear();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                if (this.a.j.size() > 0) {
                    e eVar;
                    DJISycAlbumModel dJISycAlbumModel = (DJISycAlbumModel) this.a.j.get(0);
                    File file = new File(b.f(e.g) + d.t + dJISycAlbumModel.mRemoteInfo.c());
                    if (file.exists()) {
                        dJISycAlbumModel.downloadState = d.n;
                    } else {
                        this.a.l.b(dJISycAlbumModel);
                        this.a.b = 0;
                        while (!file.exists() && this.a.b < 1) {
                            if (dJISycAlbumModel.downloadState == d.q) {
                                eVar = this.a;
                                eVar.b++;
                                this.a.l.b(dJISycAlbumModel);
                            }
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e3) {
                                e3.printStackTrace();
                            }
                            if (!this.a.e()) {
                                this.a.c = true;
                                break;
                            }
                        }
                        if (!this.a.c) {
                            if (file.exists()) {
                                dJISycAlbumModel.downloadState = d.n;
                            } else {
                                dJISycAlbumModel.downloadState = d.q;
                            }
                        }
                    }
                    this.a.a(false);
                    if (dJISycAlbumModel.downloadState == d.n) {
                        dJISycAlbumModel.isThumb = true;
                        dJISycAlbumModel.mThumbmAbsPath = b.f(e.g) + d.t + dJISycAlbumModel.mRemoteInfo.c();
                        file = new File(b.f(e.g) + d.t + dJISycAlbumModel.mRemoteInfo.d());
                        if (file.exists()) {
                            dJISycAlbumModel.downloadState = d.o;
                        } else {
                            this.a.l.a(dJISycAlbumModel);
                            this.a.b = 0;
                            this.a.c = false;
                            while (!file.exists() && this.a.b < 1) {
                                if (dJISycAlbumModel.downloadState == d.p) {
                                    eVar = this.a;
                                    eVar.b++;
                                    this.a.l.a(dJISycAlbumModel);
                                }
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e32) {
                                    e32.printStackTrace();
                                }
                                if (!this.a.e()) {
                                    this.a.c = true;
                                    break;
                                }
                            }
                            if (!this.a.c) {
                                if (file.exists()) {
                                    dJISycAlbumModel.downloadState = d.o;
                                } else {
                                    dJISycAlbumModel.downloadState = d.p;
                                }
                                if (dJISycAlbumModel.downloadState == d.o && b.c(i.getInstance().c())) {
                                    file = new File(b.f(e.g) + d.t + dJISycAlbumModel.mRemoteInfo.b());
                                    if (!file.exists()) {
                                        this.a.l.b(dJISycAlbumModel.mRemoteInfo);
                                        this.a.b = 0;
                                        this.a.c = false;
                                        while (!file.exists() && this.a.b < 1) {
                                            if (dJISycAlbumModel.downloadState == d.t) {
                                                eVar = this.a;
                                                eVar.b++;
                                                this.a.l.a(dJISycAlbumModel.mRemoteInfo);
                                            }
                                            try {
                                                Thread.sleep(500);
                                            } catch (InterruptedException e322) {
                                                e322.printStackTrace();
                                            }
                                            if (!this.a.e()) {
                                                this.a.c = true;
                                                break;
                                            }
                                        }
                                    }
                                    dJISycAlbumModel.downloadState = d.o;
                                    if (this.a.c) {
                                    }
                                }
                            }
                        }
                    }
                    if (!this.a.f) {
                        if (dJISycAlbumModel.downloadState != d.o) {
                            this.a.k.add(dJISycAlbumModel);
                            this.a.j.remove(0);
                        } else {
                            File file2 = new File(b.f(e.g) + d.t + dJISycAlbumModel.mRemoteInfo.d());
                            if (file2.exists()) {
                                dJISycAlbumModel = (DJISycAlbumModel) this.a.j.get(0);
                                dJISycAlbumModel.mLocalInfo = g.a(file2, false);
                                dJISycAlbumModel.fileLevel = 3;
                                this.a.j.remove(0);
                            } else {
                                this.a.k.add(dJISycAlbumModel);
                                this.a.j.remove(0);
                            }
                        }
                        this.a.a(false);
                    }
                }
                if (this.a.k.size() == 0 && this.a.j.size() == 0) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e22) {
                        e22.printStackTrace();
                    }
                }
            }
            this.a.h.clear();
            this.a.k.clear();
            this.a.j.clear();
        }
    };
    private boolean e = false;
    private boolean f = false;
    private ArrayList<DJISycAlbumModel> h = new ArrayList();
    private ArrayList<DJISycAlbumModel> i = new ArrayList();
    private ArrayList<DJISycAlbumModel> j = new ArrayList();
    private ArrayList<DJISycAlbumModel> k = new ArrayList();
    private h l = h.getInstance();
    private MODE n;

    public ArrayList<DJISycAlbumModel> a() {
        this.f = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.j;
    }

    public void b() {
        this.f = false;
    }

    private e() {
    }

    public static e getInstance(Context context) {
        if (m == null) {
            m = new e();
            g = context;
        }
        return m;
    }

    public void a(ArrayList<DJISycAlbumModel> arrayList, ArrayList<DJISycAlbumModel> arrayList2) {
        this.h = arrayList;
        this.i = arrayList2;
        Thread thread = new Thread(this.d);
        if (!this.e) {
            this.e = true;
            a = this.h.size();
            thread.start();
        }
    }

    private void a(boolean z) {
        if (z) {
            a = 0;
            c.a().e(a.PhotoDIsConnect);
            return;
        }
        a = this.j.size() + this.k.size();
        c.a().e(a.PhotoDownNotify);
        c.a().e(a.PhotoDownLibrary);
    }

    private boolean d() {
        this.n = DataCameraGetPushStateInfo.getInstance().getMode();
        MODE b = dji.pilot.publics.e.c.b();
        if (!b.c(null)) {
            if (this.n != MODE.RECORD) {
                while (this.n != b) {
                    this.n = DataCameraGetPushStateInfo.getInstance().getMode();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!ServiceManager.getInstance().isRemoteOK()) {
                        this.e = false;
                        a(true);
                        return this.e;
                    }
                }
            } else if (DataCameraGetPushStateInfo.getInstance().getRecordState() != RecordType.NO) {
                DataCameraGetPushStateInfo.getInstance().getRecordState();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (ServiceManager.getInstance().isRemoteOK()) {
            while (!d.getInstance().a()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e22) {
                    e22.printStackTrace();
                }
                SDCardState sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState();
                if (sDCardState == SDCardState.None || sDCardState == SDCardState.Invalid || sDCardState == SDCardState.Illegal) {
                    this.e = false;
                    return this.e;
                } else if (!ServiceManager.getInstance().isRemoteOK()) {
                    this.e = false;
                    return this.e;
                }
            }
            return this.e;
        }
        this.e = false;
        a(true);
        return this.e;
    }

    private boolean e() {
        this.n = DataCameraGetPushStateInfo.getInstance().getMode();
        MODE b = dji.pilot.publics.e.c.b();
        if (this.n != MODE.RECORD) {
            if (!(this.n == b || b.c(null))) {
                return false;
            }
        } else if (!(DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.NO || b.c(null))) {
            return false;
        }
        if (!d.getInstance().a()) {
            return false;
        }
        SDCardState sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState();
        if (sDCardState == SDCardState.None || sDCardState == SDCardState.Invalid || sDCardState == SDCardState.Illegal || !ServiceManager.getInstance().isRemoteOK()) {
            return false;
        }
        return true;
    }

    public void a(ArrayList<DJISycAlbumModel> arrayList) {
        Collections.sort(arrayList, new Comparator<DJISycAlbumModel>(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((DJISycAlbumModel) obj, (DJISycAlbumModel) obj2);
            }

            public int a(DJISycAlbumModel dJISycAlbumModel, DJISycAlbumModel dJISycAlbumModel2) {
                return Long.valueOf(dJISycAlbumModel2.sortTime).compareTo(Long.valueOf(dJISycAlbumModel.sortTime));
            }
        });
    }
}
