package dji.device.common.view.set.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.media.TransportMediator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import dji.device.common.DJIUIEventManagerLongan;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.device.common.view.DJIStateTextView;
import dji.device.common.view.set.view.DJIStageViewCompat.a;
import dji.log.DJILogHelper;
import dji.logic.f.b;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetAudio;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetRecordFan;
import dji.midware.data.model.P3.DataCameraSetAudio;
import dji.midware.data.model.P3.DataCameraSetRecordFan;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataOSDGetMicControl;
import dji.midware.data.model.P3.DataOsdGetMicGain;
import dji.midware.data.model.P3.DataOsdGetPushMicInfo;
import dji.midware.data.model.P3.DataOsdGetPushMicInfo.MIC_TYPE;
import dji.midware.data.model.P3.DataOsdSetMicControl;
import dji.midware.data.model.P3.DataOsdSetMicGain;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.pilot.set.e;
import dji.pilot.set.g;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.thirdparty.a.c;

public class LonganShotcutsMicGainView extends ScrollView implements OnClickListener, a {
    private static final int A = 2;
    private static final int B = 3;
    private static final int C = 4;
    private static final int D = 30;
    public static final String a = ".";
    private static final long w = 88;
    private static final String y = "LonganShotcutsMicGainView";
    private static final int z = 1;
    private MIC_TYPE E = MIC_TYPE.OTHER;
    private View F = null;
    DJIStateImageViewCompat b;
    DJIStateImageViewCompat c;
    DJIStateTextView d;
    SeekBar e;
    TextView f;
    int g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    DJIRelativeLayout l;
    DJIRelativeLayout m;
    DJIRelativeLayout n;
    DJISwitchCompat o;
    DJISwitchCompat p;
    DJISwitchCompat q;
    DataOsdGetMicGain r = DataOsdGetMicGain.getInstance();
    DataCameraGetAudio s;
    DataCameraGetRecordFan t;
    DataOSDGetMicControl u;
    Handler v = new Handler(new Callback(this) {
        final /* synthetic */ LonganShotcutsMicGainView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.f.setText("" + this.a.g);
                    break;
                case 2:
                    this.a.o.setChecked(this.a.h);
                    this.a.o.setEnabled(true);
                    if (this.a.k) {
                        if (!this.a.h) {
                            this.a.n.go();
                            break;
                        }
                        this.a.n.show();
                        this.a.d();
                        this.a.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
                            }
                        });
                        break;
                    }
                    break;
                case 3:
                    this.a.p.setEnabled(true);
                    this.a.p.setChecked(this.a.i);
                    break;
                case 4:
                    this.a.q.setChecked(this.a.j);
                    break;
            }
            return false;
        }
    });
    private DataCommonGetVersion x = null;

    public LonganShotcutsMicGainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.d = (DJIStateTextView) findViewById(R.id.longan_mic_gain_default_tv);
        this.b = (DJIStateImageViewCompat) findViewById(R.id.longan_mic_gain_minus_iv);
        this.c = (DJIStateImageViewCompat) findViewById(R.id.longan_mic_gain_plus_iv);
        this.f = (TextView) findViewById(R.id.longan_mic_gain_value_tv);
        this.e = (SeekBar) findViewById(R.id.longan_mic_bar);
        this.e.setMax(100);
        this.e.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ LonganShotcutsMicGainView a;

            {
                this.a = r1;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                this.a.setGain(seekBar.getProgress());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    this.a.g = i;
                    this.a.v.sendEmptyMessage(1);
                }
            }
        });
        this.l = (DJIRelativeLayout) findViewById(R.id.longan_record_sound_switch_ly);
        this.m = (DJIRelativeLayout) findViewById(R.id.longan_record_fan_switch_ly);
        this.n = (DJIRelativeLayout) findViewById(R.id.longan_record_inner_mic_switch_ly);
        this.o = (DJISwitchCompat) findViewById(R.id.longan_record_sound_switch);
        this.p = (DJISwitchCompat) findViewById(R.id.longan_record_fan_switch);
        this.q = (DJISwitchCompat) findViewById(R.id.longan_record_inner_mic_switch);
        this.d.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.n.setOnClickListener(this);
        b();
        c();
        if (i.getInstance().c() != ProductType.Longan) {
            e();
        }
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        onEventMainThread(DataOsdGetPushMicInfo.getInstance());
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        c.a().a(this);
    }

    public void dispatchOnPause() {
        c.a().d(this);
    }

    public View getView() {
        return this;
    }

    public void onClick(View view) {
        boolean z = true;
        boolean z2 = false;
        int id = view.getId();
        if (id == R.id.longan_mic_gain_minus_iv) {
            int i;
            if (this.g - 1 >= 0) {
                i = this.g - 1;
            }
            setGain(i);
        } else if (id == R.id.longan_mic_gain_plus_iv) {
            setGain(this.g + 1 > 100 ? 100 : this.g + 1);
        } else if (id == R.id.longan_mic_gain_default_tv) {
            setGain(30);
        } else if (id == R.id.longan_record_sound_switch_ly) {
            if (!this.o.isChecked()) {
                z2 = true;
            }
            setIsSoundTurnOn(z2);
        } else if (id == R.id.longan_record_fan_switch_ly) {
            if (this.p.isChecked()) {
                z = false;
            }
            setIsFanTurnOff(z);
        } else if (id == R.id.longan_record_inner_mic_switch_ly) {
            if (this.q.isChecked()) {
                z = false;
            }
            setInnerMicEnable(z);
        }
    }

    private void setInnerMicEnable(boolean z) {
        int i;
        DataOsdSetMicControl dataOsdSetMicControl = new DataOsdSetMicControl();
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        dataOsdSetMicControl.a(i, 1).start(new d(this) {
            final /* synthetic */ LonganShotcutsMicGainView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.d();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void setGain(int i) {
        DataOsdSetMicGain.getInstance().a(i).start(new d(this) {
            final /* synthetic */ LonganShotcutsMicGainView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public void onEventMainThread(DataOsdGetPushMicInfo dataOsdGetPushMicInfo) {
        if (dataOsdGetPushMicInfo.getMicType() != this.E) {
            this.E = dataOsdGetPushMicInfo.getMicType();
            a();
        }
    }

    private void a() {
        this.r.start(new d(this) {
            final /* synthetic */ LonganShotcutsMicGainView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.g = this.a.r.getMicGain();
                this.a.e.setProgress(this.a.g);
                this.a.v.sendEmptyMessage(1);
                c.a().e(DJIUIEventManagerLongan.d.MIC_GAIN_UPDATED);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    protected void setIsSoundTurnOn(boolean z) {
        this.o.setEnabled(false);
        new DataCameraSetAudio().a(z).start(new d(this) {
            final /* synthetic */ LonganShotcutsMicGainView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.b();
                c.a().e(new dji.pilot.set.a.a(g.j));
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.b();
            }
        });
    }

    protected void setIsFanTurnOff(final boolean z) {
        if (this.i) {
            this.p.setEnabled(false);
            new DataCameraSetRecordFan().a(z).start(new d(this) {
                final /* synthetic */ LonganShotcutsMicGainView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.c();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.c();
                }
            });
            return;
        }
        e.b(getContext(), R.string.longan_turnoff_fan_tip, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LonganShotcutsMicGainView b;

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.b.p.setEnabled(false);
                new DataCameraSetRecordFan().a(z).start(new d(this) {
                    final /* synthetic */ AnonymousClass9 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.b.c();
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.a.b.c();
                    }
                });
            }
        });
    }

    private void b() {
        this.s = new DataCameraGetAudio();
        this.s.start(new d(this) {
            final /* synthetic */ LonganShotcutsMicGainView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.h = this.a.s.isEnable();
                this.a.v.sendEmptyMessage(2);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void c() {
        this.t = new DataCameraGetRecordFan();
        this.t.start(new d(this) {
            final /* synthetic */ LonganShotcutsMicGainView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.i = this.a.t.isForceTurnOffFan();
                this.a.v.sendEmptyMessage(3);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void d() {
        this.u = new DataOSDGetMicControl();
        this.u.start(new d(this) {
            final /* synthetic */ LonganShotcutsMicGainView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                boolean z = true;
                LonganShotcutsMicGainView longanShotcutsMicGainView = this.a;
                if (this.a.u.getInnerEnable() != 1) {
                    z = false;
                }
                longanShotcutsMicGainView.j = z;
                this.a.v.sendEmptyMessage(4);
                c.a().e(new dji.pilot.set.a.a(g.m));
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void e() {
        this.x = new DataCommonGetVersion().setDeviceType(DeviceType.OFDM);
        this.x.start(new d(this) {
            final /* synthetic */ LonganShotcutsMicGainView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                long parseLong = Long.parseLong(this.a.x.getFirmVer(".").replace(".", ""));
                if (parseLong != 0 && parseLong >= LonganShotcutsMicGainView.w) {
                    this.a.k = true;
                }
                DJILogHelper.getInstance().LOGD(LonganShotcutsMicGainView.y, "===[request version SUCCEED!]===" + parseLong, false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(LonganShotcutsMicGainView.y, "===[request version failed!]===" + aVar, false, true);
            }
        }, 1000, 3);
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (this.F == null) {
            this.F = findViewById(R.id.longan_record_fan_switch_div);
        }
        if (b.h(null) || dataCameraGetPushStateInfo.getVerstion() < 4) {
            this.m.go();
            this.F.setVisibility(8);
            return;
        }
        this.m.show();
        this.F.setVisibility(0);
    }
}
