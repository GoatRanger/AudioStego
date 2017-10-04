package dji.pilot.fpv.control;

import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.View;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.media.DJIVideoDecoder;
import dji.pilot.R;
import dji.pilot.b;
import dji.pilot.visual.a.d;
import dji.publics.DJIUI.DJIImageView;
import dji.thirdparty.a.c;

class b$1 implements Callback {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public boolean handleMessage(Message message) {
        boolean z = true;
        switch (message.what) {
            case 1:
                b.a(this.a).b();
                break;
            case 2:
                b.a(this.a).a();
                b.p = true;
                break;
            case 3:
                this.a.a((TYPE) message.obj);
                break;
            case 4:
                b.b(this.a).startTakePic(this.a.s, this.a.r.n());
                break;
            case 6:
                this.a.o.go();
                break;
            case 7:
                this.a.o.show();
                this.a.o.setText(b.a(this.a, DataCameraGetPushStateInfo.getInstance().getVideoRecordTime()));
                break;
            case 8:
                if (!b.c(this.a)) {
                    this.a.l.setEnabled(true);
                    this.a.a(this.a.s);
                    break;
                }
                Log.d("", "MSG_ISTIME");
                this.a.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon_stop);
                this.a.m.setEnabled(true);
                this.a.l.setEnabled(false);
                break;
            case 9:
                View view = (View) message.obj;
                if (message.arg1 != 1) {
                    z = false;
                }
                view.setEnabled(z);
                break;
            case 10:
                DJIImageView dJIImageView = this.a.l;
                if (message.arg1 != 1) {
                    z = false;
                }
                dJIImageView.setSelected(z);
                break;
            case 11:
                b.d(this.a).a(b.a + message.obj.toString() + ".amr");
                break;
            case 12:
                b.d(this.a).b();
                break;
            case 13:
                b.e(this.a);
                if (b.f(this.a) >= 5) {
                    this.a.c();
                    break;
                }
                this.a.j.setBackgroundResource(this.a.q.getResources().getIdentifier("camera_controll_takephoto_bg" + b.f(this.a), "drawable", b.b));
                this.a.b.sendEmptyMessageDelayed(13, 200);
                break;
            case 14:
                b.g(this.a);
                break;
            case 15:
                c.a().e(b$a.CENTER);
                break;
            case 20:
                if (message.arg1 != 1) {
                    this.a.c.setAlpha(d.c);
                    this.a.n.show();
                    this.a.a(ExposureMode.i);
                    break;
                }
                this.a.c.setAlpha(1.0f);
                break;
            case 21:
                if ((dji.pilot.fpv.d.b.j(b.h(this.a)) || dji.pilot.fpv.d.b.k(b.h(this.a))) && b.i(this.a).getVisibility() == 0) {
                    b.i(this.a).clearAnimation();
                    b.i(this.a).hide();
                    b.j(this.a).show();
                }
                if (this.a.i.getVisibility() == 0) {
                    this.a.i.clearAnimation();
                    this.a.i.hide();
                    this.a.j.show();
                }
                b.k(this.a).cancel();
                b.a(this.a, false);
                break;
            case 22:
                this.a.e();
                break;
            case 23:
                DJIVideoDecoder e = ServiceManager.getInstance().e();
                if (e != null) {
                    DJILogHelper.getInstance().LOGD("cameracontroller", "fixDelayStream end");
                    e.setConnectLosedelay(2000);
                    break;
                }
                break;
            case 26:
                if (message.arg1 != 1) {
                    this.a.n.show();
                    this.a.h.setBackgroundResource(R.drawable.camera_more_bottomleft_selector);
                    this.a.onEventMainThread(DataCameraGetPushShotParams.getInstance());
                    b.l(this.a).go();
                    break;
                }
                this.a.n.go();
                this.a.h.setBackgroundResource(R.drawable.camera_more_bottomleft_selector);
                this.a.h.setImageResource(R.drawable.selector_camera_control_playback);
                break;
            case 27:
                if (dji.pilot.fpv.d.b.k(null)) {
                    if (RecordType.START != b.m(this.a) && RecordType.STARTING != b.m(this.a)) {
                        this.a.n.show();
                        b.l(this.a).go();
                        break;
                    }
                    this.a.n.go();
                    b.l(this.a).show();
                    break;
                }
                break;
        }
        return false;
    }
}
