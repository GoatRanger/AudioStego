package dji.pilot.sdr.debug;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.log.WM220LogUtil;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataDm368GetForesightShowed;
import dji.midware.data.model.P3.DataDm368SetForesightShowed;
import dji.midware.data.model.P3.DataFlycGetPushGpsSnr;
import dji.midware.data.model.P3.DataFlycSetPushGpsSnr;
import dji.midware.data.model.P3.DataOsdGetPushSdrStatusGroundInfo;
import dji.midware.data.model.P3.DataOsdGetPushSdrStatusInfo;
import dji.midware.data.model.P3.DataOsdSetSdrStartLog;
import dji.midware.data.model.P3.DataOsdSetSdrStatus;
import dji.midware.e.d;
import dji.midware.sdr.log.DJISdrLogDataReciever;
import dji.pilot.R;
import dji.pilot.fpv.inner.DJISnrView;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJISdrDebugView extends DJIRelativeLayout {
    private static int[] F = new int[]{R.id.apw, R.id.aq0, R.id.aq4, R.id.aq8, R.id.aqb, R.id.aqf, R.id.aqj, R.id.aqn, R.id.aqr, R.id.aqv, R.id.aqz, R.id.ar3, R.id.ar7, R.id.ara, R.id.are, R.id.ari, R.id.arm, R.id.arq, R.id.aru, R.id.ary, R.id.as2, R.id.as6, R.id.as_, R.id.asd, R.id.ash, R.id.asl, R.id.asp, R.id.ast, R.id.asx, R.id.at1, R.id.at5, R.id.at9};
    private static int[] G = new int[]{R.id.apx, R.id.aq1, R.id.aq5, R.id.aq9, R.id.aqc, R.id.aqg, R.id.aqk, R.id.aqo, R.id.aqs, R.id.aqw, R.id.ar0, R.id.ar4, R.id.ar8, R.id.arb, R.id.arf, R.id.arj, R.id.arn, R.id.arr, R.id.arv, R.id.arz, R.id.as3, R.id.as7, R.id.asa, R.id.ase, R.id.asi, R.id.asm, R.id.asq, R.id.asu, R.id.asy, R.id.at2, R.id.at6, R.id.at_};
    private static int[] H = new int[]{R.id.atd, R.id.ath, R.id.atl, R.id.atp, R.id.att, R.id.atx, R.id.au1, R.id.au5, R.id.au9, R.id.auc, R.id.aug, R.id.auk, R.id.auo, R.id.aus, R.id.auw, R.id.av1, R.id.av5, R.id.av9, R.id.avc, R.id.avg, R.id.avk, R.id.avo, R.id.avs, R.id.avw, R.id.aw0, R.id.aw4, R.id.aw8, R.id.awb, R.id.awf, R.id.awj, R.id.awn, R.id.awr};
    private static int[] I = new int[]{R.id.ate, R.id.ati, R.id.atm, R.id.atq, R.id.atu, R.id.aty, R.id.au2, R.id.au6, R.id.au_, R.id.aud, R.id.auh, R.id.aul, R.id.aup, R.id.aut, R.id.auy, R.id.av2, R.id.av6, R.id.av_, R.id.avd, R.id.avh, R.id.avl, R.id.avp, R.id.avt, R.id.avx, R.id.aw1, R.id.aw5, R.id.aw9, R.id.awc, R.id.awg, R.id.awk, R.id.awo, R.id.aws};
    private static boolean O = false;
    private static final int P = 0;
    private static final int Q = 1;
    private static final int R = 2;
    private static final int S = 3;
    private static final int T = 4;
    private static final int U = 5;
    private static final int V = 6;
    private static final int W = 7;
    private static final int q = 1;
    private static final int r = 0;
    private static boolean x = false;
    private static boolean y = false;
    private DJITextView[] A;
    private DJITextView[] B;
    private DJITextView[] C;
    private Switch D;
    private Switch E;
    private OnCheckedChangeListener J;
    private boolean K;
    private OnClickListener L;
    private OnCheckedChangeListener M;
    private OnCheckedChangeListener N;
    protected DJIStateTextView a;
    private Handler aa;
    protected DJITextView b;
    protected Switch c;
    protected DJIStateTextView d;
    protected Switch e;
    private Context f;
    private Switch g;
    private DJIRelativeLayout h;
    private DJISnrView i;
    private DJITextView j;
    private DJITextView k;
    private DJIRelativeLayout l;
    private DJISnrView m;
    private DJITextView n;
    private DJITextView o;
    private boolean p;
    private RadioGroup s;
    private RadioButton t;
    private RadioButton u;
    private final int[] v;
    private final int[] w;
    private DJITextView[] z;

    public DJISdrDebugView(Context context) {
        this(context, null);
    }

    public DJISdrDebugView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = false;
        this.t = null;
        this.u = null;
        this.v = new int[32];
        this.w = new int[32];
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.J = new OnCheckedChangeListener(this) {
            final /* synthetic */ DJISdrDebugView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (compoundButton == this.a.e) {
                    this.a.d(z);
                }
            }
        };
        this.K = false;
        this.L = new OnClickListener(this) {
            final /* synthetic */ DJISdrDebugView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                final boolean isChecked = ((Switch) view).isChecked();
                DataDm368SetForesightShowed.getInstance().a(isChecked).start(new d(this) {
                    final /* synthetic */ AnonymousClass11 b;

                    public void onSuccess(Object obj) {
                        this.b.a.K = isChecked;
                        if (isChecked) {
                            this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "PIP open success."));
                        } else {
                            this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "PIP close success."));
                        }
                    }

                    public void onFailure(a aVar) {
                        if (isChecked) {
                            this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "PIP open Fail."));
                        } else {
                            this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "PIP close Fail."));
                        }
                        this.b.a.aa.sendEmptyMessage(7);
                    }
                });
            }
        };
        this.M = new OnCheckedChangeListener(this) {
            final /* synthetic */ DJISdrDebugView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
                if (compoundButton == this.a.g) {
                    DataDm368SetForesightShowed.getInstance().a(z).start(new d(this) {
                        final /* synthetic */ AnonymousClass12 b;

                        public void onSuccess(Object obj) {
                            this.b.a.K = z;
                            if (z) {
                                this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "PIP open success."));
                            } else {
                                this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "PIP close success."));
                            }
                        }

                        public void onFailure(a aVar) {
                            if (z) {
                                this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "PIP open Fail."));
                            } else {
                                this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "PIP close Fail."));
                            }
                            this.b.a.aa.sendEmptyMessage(7);
                        }
                    });
                }
            }
        };
        this.N = new OnCheckedChangeListener(this) {
            final /* synthetic */ DJISdrDebugView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
                if (compoundButton == this.a.c && DJISdrDebugView.O != z) {
                    if (z) {
                        DJISdrLogDataReciever.getInstance(this.a.f).createLogFiles();
                        DataOsdSetSdrStartLog.getInstance().a(true).start(new d(this) {
                            final /* synthetic */ AnonymousClass3 b;

                            public void onSuccess(Object obj) {
                                this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "Sdr open log success."));
                                DJISdrDebugView.O = z;
                            }

                            public void onFailure(a aVar) {
                                this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "Sdr open log fail,ccode= " + aVar.toString()));
                                this.b.a.aa.sendEmptyMessage(1);
                            }
                        });
                        return;
                    }
                    DataOsdSetSdrStartLog.getInstance().a(false).start(new d(this) {
                        final /* synthetic */ AnonymousClass3 b;

                        public void onSuccess(Object obj) {
                            this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "Sdr close log success."));
                            DJISdrDebugView.O = z;
                            DJISdrLogDataReciever.getInstance(this.b.a.f).scanLogFile();
                        }

                        public void onFailure(a aVar) {
                            this.b.a.aa.sendMessage(this.b.a.aa.obtainMessage(0, 1000, 0, "Sdr close log fail,ccode= " + aVar.toString()));
                            this.b.a.aa.sendEmptyMessage(1);
                        }
                    });
                }
            }
        };
        this.aa = new Handler(new Callback(this) {
            final /* synthetic */ DJISdrDebugView a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                boolean z = true;
                switch (message.what) {
                    case 0:
                        Toast.makeText(this.a.getContext(), (String) message.obj, message.arg1).show();
                        break;
                    case 1:
                        this.a.c.setChecked(DJISdrDebugView.O);
                        break;
                    case 2:
                        this.a.j();
                        break;
                    case 3:
                        this.a.k();
                        break;
                    case 4:
                        this.a.h();
                        break;
                    case 5:
                        this.a.i();
                        break;
                    case 6:
                        DJISdrDebugView dJISdrDebugView = this.a;
                        int i = message.arg1;
                        if (message.arg2 != 1) {
                            z = false;
                        }
                        dJISdrDebugView.a(i, z);
                        break;
                    case 7:
                        this.a.g.setChecked(this.a.K);
                        break;
                }
                return false;
            }
        });
        this.f = context;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            c.a().a(this);
            a();
        }
    }

    public void show() {
    }

    public void destroy() {
        c.a().d(this);
    }

    protected void a() {
        this.a = (DJIStateTextView) findViewById(R.id.bko);
        this.b = (DJITextView) findViewById(R.id.bkk);
        this.c = (Switch) findViewById(R.id.bkl);
        this.g = (Switch) findViewById(R.id.bkq);
        this.g.setOnClickListener(this.L);
        DataDm368GetForesightShowed.getInstance().start(new d(this) {
            final /* synthetic */ DJISdrDebugView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.K = DataDm368GetForesightShowed.getInstance().isOpen();
                this.a.aa.sendEmptyMessage(7);
            }

            public void onFailure(a aVar) {
                WM220LogUtil.LOGD("** get ForesightShowed onFailure");
            }
        });
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJISdrDebugView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.g();
            }
        });
        this.c.setOnCheckedChangeListener(this.N);
        this.d = (DJIStateTextView) findViewById(R.id.bkm);
        this.e = (Switch) findViewById(R.id.bkn);
        this.e.setOnCheckedChangeListener(this.J);
        this.h = (DJIRelativeLayout) findViewById(R.id.axt);
        this.i = (DJISnrView) findViewById(R.id.axu);
        this.j = (DJITextView) findViewById(R.id.axv);
        this.k = (DJITextView) findViewById(R.id.axw);
        this.l = (DJIRelativeLayout) findViewById(R.id.axx);
        this.m = (DJISnrView) findViewById(R.id.axy);
        this.n = (DJITextView) findViewById(R.id.axz);
        this.o = (DJITextView) findViewById(R.id.ay0);
        this.s = (RadioGroup) findViewById(R.id.bkr);
        this.t = (RadioButton) findViewById(R.id.bks);
        this.u = (RadioButton) findViewById(R.id.bkt);
        this.s.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(this) {
            final /* synthetic */ DJISdrDebugView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.bks) {
                    this.a.h.show();
                    this.a.l.go();
                    return;
                }
                this.a.h.go();
                this.a.l.show();
            }
        });
        O = DJISdrLogDataReciever.getInstance(this.f).getIsRecieveFlag();
        this.c.setChecked(O);
        if (dji.pilot.publics.e.a.c(null)) {
            e();
        } else {
            f();
        }
    }

    private void e() {
        this.a.show();
        this.b.show();
        this.c.setVisibility(0);
        this.d.show();
        this.e.setVisibility(0);
    }

    private void f() {
        this.a.go();
        this.b.go();
        this.c.setVisibility(8);
        this.d.go();
        this.e.setVisibility(8);
    }

    private void g() {
        int i = 0;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.f).inflate(R.layout.hd_sdr_status_dlg_view, null);
        final Dialog create = new Builder(this.f).create();
        create.show();
        create.getWindow().setContentView(linearLayout);
        LayoutParams attributes = create.getWindow().getAttributes();
        attributes.flags |= 32;
        create.getWindow().setAttributes(attributes);
        create.setCancelable(false);
        this.D = (Switch) linearLayout.findViewById(R.id.apq);
        this.E = (Switch) linearLayout.findViewById(R.id.aps);
        this.z = new DJITextView[32];
        this.A = new DJITextView[32];
        this.B = new DJITextView[32];
        this.C = new DJITextView[32];
        while (i < 32) {
            this.z[i] = (DJITextView) linearLayout.findViewById(F[i]);
            this.A[i] = (DJITextView) linearLayout.findViewById(G[i]);
            this.B[i] = (DJITextView) linearLayout.findViewById(H[i]);
            this.C[i] = (DJITextView) linearLayout.findViewById(I[i]);
            i++;
        }
        this.D.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ DJISdrDebugView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z != DJISdrDebugView.x) {
                    if (z) {
                        DataOsdSetSdrStatus.getInstance().a(1).a(true).start(new d(this) {
                            final /* synthetic */ AnonymousClass7 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                DJISdrDebugView.x = true;
                                DJILogHelper.getInstance().LOGE("", "********************set sdr ground status true, success!", false, true);
                            }

                            public void onFailure(a aVar) {
                                DJILogHelper.getInstance().LOGE("", "********************set sdr ground status true, fail!", false, true);
                                this.a.a.aa.sendEmptyMessage(4);
                            }
                        });
                    } else {
                        DataOsdSetSdrStatus.getInstance().a(1).a(false).start(new d(this) {
                            final /* synthetic */ AnonymousClass7 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                DJISdrDebugView.x = false;
                                DJILogHelper.getInstance().LOGE("", "********************set sdr ground status false, success!", false, true);
                            }

                            public void onFailure(a aVar) {
                                DJILogHelper.getInstance().LOGE("", "********************set sdr ground status false, fail!", false, true);
                                this.a.a.aa.sendEmptyMessage(4);
                            }
                        });
                    }
                }
            }
        });
        this.E.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ DJISdrDebugView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z != DJISdrDebugView.y) {
                    if (z) {
                        DataOsdSetSdrStatus.getInstance().a(0).a(true).start(new d(this) {
                            final /* synthetic */ AnonymousClass8 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                DJISdrDebugView.y = true;
                                DJILogHelper.getInstance().LOGE("", "********************set sdr sky status true, success!", false, true);
                            }

                            public void onFailure(a aVar) {
                                DJILogHelper.getInstance().LOGE("", "********************set sdr sky status true, fail!", false, true);
                                this.a.a.aa.sendEmptyMessage(5);
                            }
                        });
                    } else {
                        DataOsdSetSdrStatus.getInstance().a(0).a(false).start(new d(this) {
                            final /* synthetic */ AnonymousClass8 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                DJISdrDebugView.y = false;
                                DJILogHelper.getInstance().LOGE("", "********************set sdr sky status false, success!", false, true);
                            }

                            public void onFailure(a aVar) {
                                DJILogHelper.getInstance().LOGE("", "********************set sdr sky status false, fail!", false, true);
                                this.a.a.aa.sendEmptyMessage(5);
                            }
                        });
                    }
                }
            }
        });
        ((DJIStateImageView) linearLayout.findViewById(R.id.sl)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJISdrDebugView b;

            public void onClick(View view) {
                create.dismiss();
                this.b.z = null;
                this.b.A = null;
                this.b.B = null;
                this.b.C = null;
                this.b.D = null;
                this.b.E = null;
            }
        });
    }

    private void h() {
        this.D.setChecked(x);
    }

    private void i() {
        this.E.setChecked(y);
    }

    private void j() {
        if (this.z != null && this.A != null) {
            String[] titleList = DataOsdGetPushSdrStatusGroundInfo.getInstance().getTitleList();
            float[] valueList = DataOsdGetPushSdrStatusGroundInfo.getInstance().getValueList();
            if (titleList.length >= 32 && valueList.length >= 32) {
                for (int i = 0; i < 32; i++) {
                    this.z[i].setText(titleList[i]);
                    this.A[i].setText("" + valueList[i]);
                }
                this.D.setChecked(x);
            }
        }
    }

    private void k() {
        if (this.B != null && this.C != null) {
            String[] titleList = DataOsdGetPushSdrStatusInfo.getInstance().getTitleList();
            float[] valueList = DataOsdGetPushSdrStatusInfo.getInstance().getValueList();
            if (titleList.length >= 32 && valueList.length >= 32) {
                for (int i = 0; i < 32; i++) {
                    this.B[i].setText(titleList[i]);
                    this.C[i].setText("" + valueList[i]);
                }
                this.E.setChecked(y);
            }
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushSdrStatusInfo dataOsdGetPushSdrStatusInfo) {
        if (dji.pilot.c.d.i != 1) {
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushSdrStatusGroundInfo dataOsdGetPushSdrStatusGroundInfo) {
        if (dji.pilot.c.d.i != 1) {
        }
    }

    private void d(final boolean z) {
        if (this.p != z) {
            DataFlycSetPushGpsSnr dataFlycSetPushGpsSnr = new DataFlycSetPushGpsSnr();
            dataFlycSetPushGpsSnr.a(z);
            dataFlycSetPushGpsSnr.start(new d(this) {
                final /* synthetic */ DJISdrDebugView b;

                public void onSuccess(Object obj) {
                    this.b.aa.obtainMessage(6, 1, z ? 1 : 0).sendToTarget();
                    if (z) {
                        DJILogHelper.getInstance().LOGD("", "enable successfully", false, true);
                    } else {
                        DJILogHelper.getInstance().LOGD("", "disable successfully", false, true);
                    }
                }

                public void onFailure(a aVar) {
                    int i;
                    Handler a = this.b.aa;
                    if (z) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    a.obtainMessage(6, 0, i).sendToTarget();
                }
            });
        }
    }

    private void a(int i, boolean z) {
        boolean z2 = true;
        Switch switchR;
        if (z) {
            if (i == 1) {
                this.e.setChecked(z);
                this.p = z;
                this.s.setVisibility(0);
                this.t.setChecked(true);
                this.u.setChecked(false);
                this.h.show();
                this.l.go();
                return;
            }
            switchR = this.e;
            if (z) {
                z2 = false;
            }
            switchR.setChecked(z2);
        } else if (i == 1) {
            this.e.setChecked(z);
            this.p = z;
            this.s.setVisibility(8);
            this.h.go();
            this.l.go();
        } else {
            switchR = this.e;
            if (z) {
                z2 = false;
            }
            switchR.setChecked(z2);
        }
    }

    private int a(int[] iArr) {
        int i = 0;
        int length = iArr.length - 1;
        while (length >= 0 && length >= iArr.length - 5) {
            i += iArr[length];
            length--;
        }
        return i / 5;
    }

    private void a(int[] iArr, int[] iArr2) {
        int i = 0;
        while (i < iArr.length && i < iArr2.length) {
            iArr2[i] = iArr[i];
            i++;
        }
    }

    public void onEventMainThread(DataFlycGetPushGpsSnr dataFlycGetPushGpsSnr) {
        if (dji.pilot.c.d.i != 1) {
        }
    }
}
