package dji.pilot.fpv.camera.more.a;

import android.content.Context;
import android.os.Handler.Callback;
import android.os.Message;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetParamName;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.midware.data.model.P3.DataCameraSetParamName;
import dji.midware.e.d;
import dji.midware.util.c;
import dji.pilot.R;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k.a;
import java.util.HashMap;

public class b implements Callback, a {
    private static final int a = 4;
    private static final int b = 4;
    private static final int c = 4096;
    private static final long d = 2000;
    private static final int e = 4097;
    private final USER[] f = new USER[]{USER.USER1, USER.USER2, USER.USER3, USER.USER4};
    private final int[] g = new int[]{R.string.camera_profile_use1, R.string.camera_profile_use2, R.string.camera_profile_use3, R.string.camera_profile_use4};
    private final HashMap<USER, DataCameraGetParamName> h = new HashMap(4);
    private final HashMap<USER, DataCameraSetParamName> i = new HashMap(4);
    private final String[] j = new String[4];
    private final Context k;
    private final k l;

    public b(Context context) {
        this.k = context;
        this.l = new k(this, this);
        for (int i = 0; i < 4; i++) {
            USER user = this.f[i];
            DataCameraGetParamName dataCameraGetParamName = new DataCameraGetParamName(false);
            dataCameraGetParamName.setParamIndex(user);
            DataCameraSetParamName dataCameraSetParamName = new DataCameraSetParamName(false);
            dataCameraSetParamName.a(user);
            this.h.put(user, dataCameraGetParamName);
            this.i.put(user, dataCameraSetParamName);
        }
    }

    public String[] a() {
        String[] strArr = new String[4];
        int i = 0;
        while (i < 4) {
            if (this.j[i] == null || this.j[i].length() <= 0) {
                strArr[i] = this.k.getString(this.g[i]);
            } else {
                strArr[i] = this.j[i];
            }
            i++;
        }
        return strArr;
    }

    public int[] b() {
        return new int[]{1, 2, 3, 4};
    }

    public int a(final int i, final String str, final d dVar) {
        if (str == null || str.length() == 0) {
            return R.string.camera_profile_empty_tip;
        }
        if (str.equals(this.j[i])) {
            return 0;
        }
        byte[] b = c.b(str);
        if (b.length > 63) {
            return R.string.camera_profile_toolong_tip;
        }
        ((DataCameraSetParamName) this.i.get(this.f[i])).a(b).start(new d(this) {
            final /* synthetic */ b d;

            public void onSuccess(final Object obj) {
                this.d.l.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void run() {
                        this.b.d.j[i] = str;
                        dVar.onSuccess(obj);
                    }
                });
            }

            public void onFailure(final dji.midware.data.config.P3.a aVar) {
                this.d.l.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void run() {
                        dVar.onFailure(aVar);
                    }
                });
            }
        });
        return 0;
    }

    public void a(CameraType cameraType) {
        if (dji.pilot.fpv.camera.a.a.d(cameraType)) {
            for (int i = 0; i < 4; i++) {
                a(i, 0, (DataCameraGetParamName) this.h.get(this.f[i]));
            }
        }
    }

    public void a(o oVar) {
        if (o.a == oVar) {
            this.l.removeCallbacksAndMessages(null);
            for (int i = 0; i < 4; i++) {
                ((DataCameraGetParamName) this.h.get(this.f[i])).clear();
            }
        }
    }

    private void a(final int i, final int i2, final DataCameraGetParamName dataCameraGetParamName) {
        if (i2 < 4) {
            dataCameraGetParamName.start(new d(this) {
                final /* synthetic */ b d;

                public void onSuccess(Object obj) {
                    this.d.l.sendMessage(this.d.l.obtainMessage(4097, i, i2, dataCameraGetParamName));
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.d.l.sendMessageDelayed(this.d.l.obtainMessage(4096, i, i2, dataCameraGetParamName), 2000);
                }
            });
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 4096:
                if (message.obj instanceof DataCameraGetParamName) {
                    a(message.arg1, message.arg2 + 1, (DataCameraGetParamName) message.obj);
                    break;
                }
                break;
            case 4097:
                if (message.obj instanceof DataCameraGetParamName) {
                    this.j[message.arg1] = ((DataCameraGetParamName) message.obj).getName();
                    break;
                }
                break;
        }
        return false;
    }

    public boolean isFinished() {
        return false;
    }
}
