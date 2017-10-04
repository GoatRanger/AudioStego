package dji.phone.bluetooth;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.midware.b.a.e;
import dji.midware.b.b;
import dji.midware.b.c;
import dji.midware.data.manager.P3.i;
import dji.phone.a.h;
import dji.phone.bluetooth.a.a;
import dji.pilot.fpv.R;
import java.util.ArrayList;
import java.util.List;

public class DJILPBleDialogView extends RelativeLayout implements OnClickListener, a {
    private static final String n = "DJILPBLEDialogView";
    private static final int o = 20;
    ImageView a;
    ImageView b;
    ViewGroup c;
    ViewGroup d;
    TextView e;
    ListView f;
    a g;
    e h;
    OnItemClickListener i;
    List<b> j = new ArrayList();
    boolean k;
    ObjectAnimator l;
    String m;
    private Runnable p = new Runnable(this) {
        final /* synthetic */ DJILPBleDialogView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.e.setVisibility(0);
        }
    };
    private Runnable q = new Runnable(this) {
        final /* synthetic */ DJILPBleDialogView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.e.setVisibility(8);
        }
    };

    public DJILPBleDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.d = (ViewGroup) findViewById(R.id.confirm_status_ly);
        this.c = (ViewGroup) findViewById(R.id.lp_ble_list_frame_ly);
        this.b = (ImageView) findViewById(R.id.lp_ble_refresh_iv);
        this.a = (ImageView) findViewById(R.id.lp_ble_cancel_iv);
        this.f = (ListView) findViewById(R.id.lp_ble_lv);
        this.e = (TextView) findViewById(R.id.lp_ble_empty_tv);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.g = new a(getContext());
        this.g.a(this.j);
        this.g.a((a) this);
        this.f.setAdapter(this.g);
        ObjectAnimator objectAnimator = new ObjectAnimator();
        this.l = ObjectAnimator.ofFloat(this.b, "Rotation", new float[]{0.0f, 60.0f, 120.0f, 180.0f, 240.0f, 300.0f, 360.0f}).setDuration(1000);
        this.l.setInterpolator(new AccelerateDecelerateInterpolator());
        this.l.setRepeatCount(20);
        this.l.setRepeatMode(1);
        a();
        dji.f.a.a(this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        onEventMainThread(dji.phone.preview.a.d);
        if (getVisibility() == 0) {
            startScan();
            onEventMainThread(dji.phone.preview.a.d);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.f.a.b(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.lp_ble_confirm_cancel_btn) {
            h.getInstance().dismiss();
        } else if (id == R.id.lp_ble_cancel_iv) {
            c.getInstance().c();
            h.getInstance().dismiss();
        } else if (id == R.id.lp_ble_refresh_iv) {
            startScan();
        }
    }

    public void startScan() {
        if (!this.l.isRunning()) {
            this.l.start();
            if (!this.g.a().isEmpty()) {
                this.g.a().clear();
                b();
            }
            if (c.getInstance().c()) {
                c.getInstance().a(20, this.h);
                c.getInstance().f().f();
                return;
            }
            dji.phone.preview.a.getInstance().h();
        }
    }

    private void a() {
        this.i = new OnItemClickListener(this) {
            final /* synthetic */ DJILPBleDialogView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                c.getInstance().a(((b) this.a.g.getItem(i)).a);
                DJILogHelper.getInstance().LOGD(DJILPBleDialogView.n, "onItemClick: ", false, true);
            }
        };
        this.h = new e(this) {
            final /* synthetic */ DJILPBleDialogView a;

            {
                this.a = r1;
            }

            public void onScanResultUpdate(ArrayList<b> arrayList) {
                this.a.g.a().clear();
                List arrayList2 = new ArrayList();
                arrayList2.addAll(arrayList);
                this.a.g.a(arrayList2);
                this.a.b();
                if (arrayList.isEmpty()) {
                    this.a.post(this.a.p);
                } else {
                    this.a.post(this.a.q);
                }
            }
        };
    }

    private void b() {
        post(new Runnable(this) {
            final /* synthetic */ DJILPBleDialogView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.g.notifyDataSetChanged();
            }
        });
    }

    public void onEventBackgroundThread(dji.midware.b.a.c cVar) {
        b a;
        switch (cVar) {
            case BLE_DEVICE_CONNECTED:
                DJILogHelper.getInstance().LOGD(n, "ble test" + dji.midware.b.a.c.BLE_DEVICE_CONNECTED, false, true);
                a = this.g.a(this.m);
                if (a != null) {
                    a.f = dji.midware.b.a.c.BLE_DEVICE_CONNECTED;
                } else {
                    String h = c.getInstance().a.f().h();
                    for (b a2 : this.j) {
                        if (!a2.a.isEmpty() && !h.isEmpty() && a2.a.equals(h)) {
                            a2.f = dji.midware.b.a.c.BLE_DEVICE_CONNECTED;
                        }
                    }
                }
                b();
                postDelayed(new Runnable(this) {
                    final /* synthetic */ DJILPBleDialogView a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        h.getInstance().dismiss();
                    }
                }, 200);
                return;
            case BLE_DEVICE_DISCONNECTED:
                DJILogHelper.getInstance().LOGD(n, "ble test" + dji.midware.b.a.c.BLE_DEVICE_DISCONNECTED, false, true);
                a2 = this.g.a(this.m);
                if (a2 != null) {
                    a2.f = dji.midware.b.a.c.BLE_DEVICE_DISCONNECTED;
                    b();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onClick(View view, View view2, int i, int i2, String str) {
        if (this.l != null && this.l.isRunning()) {
            this.l.cancel();
            c.getInstance().c();
        }
        if (i2 == R.id.lp_ble_action_btn) {
            this.m = str;
            b bVar = (b) this.g.getItem(i);
            c.getInstance().a(bVar.a);
            bVar.f = dji.midware.b.a.c.BLE_DEVICE_CONNECTING;
            DJILogHelper.getInstance().LOGD(n, "ble testitem btn clicked  name:" + bVar.b + "  address:" + bVar.a, false, true);
            this.g.notifyDataSetChanged();
            dji.publics.b.b.a.getInstance().a("createtime", System.currentTimeMillis() + "", false).a("action", "5", false).a("pageid", "2", false).a("device_ver", dji.device.common.b.getInstance().b(), false).a("device_type", i.getInstance().c()._name(), true);
        }
    }

    public void onEventMainThread(dji.phone.h.b bVar) {
        dji.phone.h.a.a(this, getRotation(), (float) bVar.b());
    }
}
