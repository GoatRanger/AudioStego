package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetImageSize.SizeType;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraSetFileIndexMode;
import dji.midware.data.model.P3.DataCameraSetFileIndexMode.FileIndexMode;
import dji.midware.data.model.P3.DataCameraSetImageSize;
import dji.midware.data.model.P3.DataCameraSetQuickPlayBack;
import dji.midware.data.model.P3.DataCameraSetVideoContrastEnhance;
import dji.midware.data.model.P3.DataCameraSetVideoEncode;
import dji.midware.data.model.P3.DataCameraSetVideoEncode.VideoEncodeType;
import dji.midware.data.model.P3.DataCameraSetVideoFormat;
import dji.midware.data.model.P3.DataCameraTauFFCMode;
import dji.midware.data.model.P3.DataCameraTauFFCMode.FFCMode;
import dji.midware.data.model.P3.DataCameraTauParamAGC;
import dji.midware.data.model.P3.DataCameraTauParamAGC.AGCType;
import dji.midware.data.model.P3.DataCameraTauParamGainMode;
import dji.midware.data.model.P3.DataCameraTauParamGainMode.GainMode;
import dji.midware.data.model.P3.DataCameraTauParamIsothermUnit;
import dji.midware.data.model.P3.DataCameraTauParamROI;
import dji.midware.data.model.P3.DataCameraTauParamROI.ROIType;
import dji.pilot.R;
import dji.pilot.fpv.camera.newfn.a.c;
import dji.pilot.fpv.camera.newfn.b.d;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIListView;
import java.util.ArrayList;
import java.util.List;

public class DJICameraBaseListView extends DJILinearLayout implements a {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = 10;
    public static final int l = 100;
    public static final int m = 101;
    public static final int n = 102;
    public static final int o = 103;
    public static final int p = 104;
    public static final int q = 105;
    public static final int r = 106;
    public static final int s = 107;
    protected int A = Integer.MAX_VALUE;
    protected DJIListView t = null;
    protected OnItemClickListener u = null;
    protected c.a v = null;
    protected c w = null;
    protected int x = Integer.MAX_VALUE;
    protected List<d> y = null;
    protected final dji.pilot.fpv.camera.more.a z = dji.pilot.fpv.camera.more.a.getInstance();

    public DJICameraBaseListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void updateData(int i) {
        boolean j = b.j(DataCameraGetPushStateInfo.getInstance().getCameraType());
        if (this.x != i || i == 105 || (j && this.x == 1)) {
            this.A = Integer.MAX_VALUE;
            this.x = i;
            this.y = a();
            this.w.a(this.y);
        }
        this.t.setEnabled(true);
        this.t.setAlpha(1.0f);
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        if (j) {
            onEventMainThread(DataCameraGetPushTauParam.getInstance());
        } else {
            onEventMainThread(DataCameraGetPushShotParams.getInstance());
        }
    }

    protected void a(int i) {
        int size = this.y.size();
        int i2 = 0;
        boolean z = false;
        while (i2 < size) {
            boolean z2;
            d dVar = (d) this.y.get(i2);
            if (dVar.j == i) {
                dVar.k = true;
                z2 = true;
            } else {
                dVar.k = false;
                z2 = z;
            }
            i2++;
            z = z2;
        }
        if (!(z || this.y.isEmpty())) {
            ((d) this.y.get(0)).k = true;
        }
        this.w.notifyDataSetChanged();
    }

    public void onEventMainThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        int a = 101 == this.x ? dataCameraGetPushTauParam.getROIType().a() : 102 == this.x ? dataCameraGetPushTauParam.getDigitalFilter() : 103 == this.x ? dataCameraGetPushTauParam.getAGC().a() : 104 == this.x ? (dataCameraGetPushTauParam.getVideoFormat() * 100) + dataCameraGetPushTauParam.getVideoFps() : 1 == this.x ? dataCameraGetPushTauParam.getImageFormat() : 105 == this.x ? dataCameraGetPushTauParam.getIsothermUnit() : 106 == this.x ? dataCameraGetPushTauParam.getGainMode().a() : 107 == this.x ? dataCameraGetPushTauParam.getFFCMode().a() : Integer.MAX_VALUE;
        if (Integer.MAX_VALUE != a && this.A != a) {
            this.A = a;
            a(a);
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int value = this.x == 0 ? dataCameraGetPushShotParams.getImageRatio().value() : 1 == this.x ? dataCameraGetPushShotParams.getImageFormat() : 2 == this.x ? dataCameraGetPushShotParams.getVideoStoreFormat() : 3 == this.x ? dataCameraGetPushShotParams.getVideoStandard() : 4 == this.x ? dataCameraGetPushShotParams.getDigitalFilter() : 5 == this.x ? DJIGenSettingDataManager.getInstance().k() : 6 == this.x ? dataCameraGetPushShotParams.getAntiFlicker() : 9 == this.x ? dataCameraGetPushShotParams.getConstrastEhance() : 10 == this.x ? dataCameraGetPushShotParams.getPrimaryVideoEncodeType().a() : Integer.MAX_VALUE;
        if (Integer.MAX_VALUE != value && this.A != value) {
            this.A = value;
            a(value);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        int a;
        if (7 != this.x) {
            a = 8 == this.x ? dataCameraGetPushStateInfo.getFileIndexMode().a() : Integer.MAX_VALUE;
        } else if (dataCameraGetPushStateInfo.getFastPlayBackEnabled()) {
            a = (byte) dataCameraGetPushStateInfo.getFastPlayBackTime();
            if (a > dji.pilot.fpv.camera.more.a.B_.length) {
                a = 0;
            }
        } else {
            a = 0;
        }
        if (!(Integer.MAX_VALUE == a || this.A == a)) {
            this.A = a;
            a(a);
        }
        if (3 == this.x || 104 == this.x) {
            RecordType recordState = dataCameraGetPushStateInfo.getRecordState();
            if (recordState == RecordType.START || recordState == RecordType.STARTING) {
                this.t.setEnabled(false);
                this.t.setAlpha(0.3f);
                return;
            }
            this.t.setEnabled(true);
            this.t.setAlpha(1.0f);
        }
    }

    protected boolean a(AdapterView<?> adapterView, View view, int i, long j) {
        return false;
    }

    protected boolean a(int i, int i2, Object obj) {
        return false;
    }

    private void b() {
        this.v = new c.a(this) {
            final /* synthetic */ DJICameraBaseListView a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, Object obj) {
                if (!this.a.a(i, i2, obj) && 103 == this.a.x) {
                    dji.thirdparty.a.c.a().e(DJICameraTauSceneView.b.TOSHOW);
                }
            }
        };
        this.u = new OnItemClickListener(this) {
            final /* synthetic */ DJICameraBaseListView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = ((d) this.a.y.get(i)).j;
                if (this.a.A != i2 && !this.a.a(adapterView, view, i, j)) {
                    boolean z;
                    dji.midware.e.d anonymousClass1 = new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            this.a.a.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.a.t.setEnabled(true);
                                    if (104 == this.a.a.a.x) {
                                        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                                        bVar.b = R.string.tau_changing_format;
                                        bVar.a = DJIErrorPopView.d.a;
                                        dji.thirdparty.a.c.a().e(bVar);
                                    }
                                }
                            });
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.a.a.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.a.t.setEnabled(true);
                                }
                            });
                        }
                    };
                    if (this.a.x < 100) {
                        if (this.a.x == 0) {
                            DataCameraSetImageSize.getInstance().a().a(SizeType.DEFAULT).a(RatioType.find(i2)).start(anonymousClass1);
                            z = true;
                        } else if (1 == this.a.x) {
                            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.m).a(i2).start(anonymousClass1);
                            z = true;
                        } else if (2 == this.a.x) {
                            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.s).a(((d) this.a.y.get(i)).j).start(anonymousClass1);
                            z = true;
                        } else if (3 == this.a.x) {
                            if (CameraType.DJICameraTypeFC220S == DataCameraGetPushStateInfo.getInstance().getCameraType()) {
                                DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
                                dataBaseCameraSetting.a(dji.midware.data.config.P3.b.a.au);
                                dataBaseCameraSetting.a(i2).start(anonymousClass1);
                                z = true;
                            } else {
                                if (i2 == 0) {
                                    e.a("FPV_GeneralSettings_Camera_Button_NTSC/PAL_PAL");
                                    this.a.a(this.a.getContext().getString(R.string.fpv_gensetting_videotyle_pal), i);
                                } else if (i2 == 1) {
                                    e.a("FPV_GeneralSettings_Camera_Button_NTSC/PAL_NTSC");
                                    this.a.a(this.a.getContext().getString(R.string.fpv_gensetting_videotyle_ntsc), i);
                                }
                                z = false;
                            }
                        } else if (4 == this.a.x) {
                            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.Z).a(i2).start(anonymousClass1);
                            z = true;
                        } else if (5 == this.a.x) {
                            DJIGenSettingDataManager.getInstance().a(i2);
                            this.a.A = ((d) this.a.y.get(i)).j;
                            this.a.a(this.a.A);
                            z = false;
                        } else if (6 == this.a.x) {
                            if (i == 0) {
                                e.a("FPV_GeneralSettings_Camera_PullDownView_Anti-Flicker_Auto");
                            } else if (i == 1) {
                                e.a("FPV_GeneralSettings_Camera_PullDownView_Anti-Flicker_60Hz");
                            } else if (i == 2) {
                                e.a("FPV_GeneralSettings_Camera_PullDownView_Anti-Flicker_50Hz");
                            }
                            if (CameraType.DJICameraTypeGD600 == DataCameraGetPushStateInfo.getInstance().getCameraType()) {
                                if (i2 == 1) {
                                    this.a.b("60", 1);
                                } else if (i2 == 2) {
                                    this.a.b("50", 2);
                                }
                                z = false;
                            } else {
                                new DataBaseCameraSetting().a(dji.sdksharedlib.b.b.q).a(i2).start(anonymousClass1);
                                z = true;
                            }
                        } else if (7 == this.a.x) {
                            DataCameraSetQuickPlayBack.getInstance().a(true);
                            DataCameraSetQuickPlayBack.getInstance().a((byte) i2);
                            DataCameraSetQuickPlayBack.getInstance().start(anonymousClass1);
                            z = true;
                        } else if (8 == this.a.x) {
                            DataCameraSetFileIndexMode.getInstance().a(FileIndexMode.find(i2));
                            DataCameraSetFileIndexMode.getInstance().start(anonymousClass1);
                            z = true;
                        } else if (9 == this.a.x) {
                            new DataCameraSetVideoContrastEnhance().a(i2).start(anonymousClass1);
                            z = true;
                        } else {
                            if (10 == this.a.x) {
                                new DataCameraSetVideoEncode().a().a(VideoEncodeType.find(i2)).start(anonymousClass1);
                                z = true;
                            }
                            z = true;
                        }
                    } else if (101 == this.a.x) {
                        new DataCameraTauParamROI().a(ROIType.find(i2)).b(false).start(anonymousClass1);
                        z = true;
                    } else if (102 == this.a.x) {
                        new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.Z).a(i2).start(anonymousClass1);
                        z = true;
                    } else if (103 == this.a.x) {
                        new DataCameraTauParamAGC().a(AGCType.find(i2)).b(false).start(anonymousClass1);
                        z = true;
                    } else if (104 == this.a.x) {
                        int i3 = i2 % 100;
                        new DataCameraSetVideoFormat().a().a(i2 / 100).b(i3).start(anonymousClass1);
                        z = true;
                    } else if (105 == this.a.x) {
                        new DataCameraTauParamIsothermUnit().a(i2).b(false).start(anonymousClass1);
                        z = true;
                    } else if (106 == this.a.x) {
                        new DataCameraTauParamGainMode().a(GainMode.find(i2)).b(false).start(anonymousClass1);
                        z = true;
                    } else {
                        if (107 == this.a.x) {
                            new DataCameraTauFFCMode().a(FFCMode.find(i2)).b(false).start(anonymousClass1);
                        }
                        z = true;
                    }
                    if (z) {
                        this.a.t.setEnabled(false);
                    }
                }
            }
        };
    }

    private void b(int i) {
        DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
        dataBaseCameraSetting.a(dji.midware.data.config.P3.b.a.au);
        dataBaseCameraSetting.a(((d) this.y.get(i)).j).start(null);
    }

    private void a(String str, final int i) {
        Context context = getContext();
        dji.pilot.fpv.leftmenu.b bVar = new dji.pilot.fpv.leftmenu.b(context);
        bVar.a(1);
        bVar.a(new dji.pilot.fpv.leftmenu.b.a(this) {
            final /* synthetic */ DJICameraBaseListView b;

            public void b(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.b.b(i);
            }

            public void a(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }

            public void a(DialogInterface dialogInterface, boolean z, int i) {
            }
        });
        bVar.a(8, 0).e(8);
        bVar.a(8, "");
        bVar.a(context.getString(R.string.fpv_gensetting_videotyle_tip));
        bVar.b(context.getString(R.string.fpv_gensetting_videotyle_tip_desc, new Object[]{str}));
        bVar.show();
    }

    private void b(String str, final int i) {
        Context context = getContext();
        dji.pilot.fpv.leftmenu.b bVar = new dji.pilot.fpv.leftmenu.b(context);
        bVar.a(1);
        bVar.a(new dji.pilot.fpv.leftmenu.b.a(this) {
            final /* synthetic */ DJICameraBaseListView b;

            public void b(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.b.c(i);
            }

            public void a(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }

            public void a(DialogInterface dialogInterface, boolean z, int i) {
            }
        });
        bVar.a(8, 0).e(8);
        bVar.a(8, "");
        bVar.a(context.getString(R.string.fpv_gensetting_antiblink_tip));
        bVar.b(context.getString(R.string.fpv_gensetting_antiblink_tip_desc, new Object[]{str}));
        bVar.show();
    }

    private void c(int i) {
        new DataBaseCameraSetting().a(dji.sdksharedlib.b.b.q).a(i).start(null);
    }

    protected List<d> a() {
        int i = 1;
        int[] iArr = null;
        int i2 = 0;
        List<d> arrayList = new ArrayList();
        int[] iArr2;
        if (i.getInstance().b() == CameraType.DJICameraTypeFC220S) {
            String[] strArr;
            int[] V;
            String[] U;
            if (this.x == 0) {
                U = this.z.U();
                strArr = U;
                iArr2 = dji.pilot.fpv.camera.more.a.v_;
                V = this.z.V();
                i = 0;
            } else if (1 == this.x) {
                U = this.z.a.a();
                strArr = U;
                iArr2 = this.z.a.a;
                V = this.z.a.b();
                i = 0;
            } else if (2 == this.x) {
                U = this.z.a.c();
                strArr = U;
                iArr2 = this.z.a.b;
                V = this.z.a.d();
                i = 0;
            } else if (3 == this.x) {
                U = this.z.ae();
                strArr = U;
                iArr2 = dji.pilot.fpv.camera.more.a.y_;
                V = this.z.af();
                i = 0;
            } else if (4 == this.x) {
                iArr2 = null;
                strArr = this.z.S();
                V = this.z.T();
                i = 0;
            } else if (5 == this.x) {
                strArr = this.z.aa();
                iArr2 = dji.pilot.fpv.camera.more.a.z_;
                V = this.z.ab();
            } else if (6 == this.x) {
                U = this.z.a.e();
                strArr = U;
                iArr2 = this.z.a.c;
                V = this.z.a.f();
                i = 0;
            } else if (7 == this.x) {
                U = this.z.ac();
                strArr = U;
                iArr2 = dji.pilot.fpv.camera.more.a.B_;
                V = this.z.ad();
                i = 0;
            } else if (8 == this.x) {
                U = this.z.ag();
                strArr = U;
                iArr2 = dji.pilot.fpv.camera.more.a.A_;
                V = this.z.ah();
                i = 0;
            } else {
                V = null;
                iArr2 = null;
                strArr = null;
            }
            int length = V.length;
            for (int i3 = 0; i3 < length; i3++) {
                d dVar = new d();
                dVar.e = i;
                dVar.f = strArr[i3];
                dVar.g = iArr2[i3];
                dVar.j = V[i3];
                dVar.k = false;
                dVar.i = null;
                arrayList.add(dVar);
            }
        } else {
            String[] U2;
            int[] iArr3;
            if (this.x < 100) {
                if (this.x == 0) {
                    U2 = this.z.U();
                    iArr2 = this.z.V();
                    iArr3 = null;
                } else if (1 == this.x) {
                    U2 = this.z.M();
                    iArr2 = this.z.N();
                    iArr3 = null;
                } else if (2 == this.x) {
                    U2 = this.z.ai();
                    iArr2 = this.z.aj();
                    iArr3 = null;
                } else if (3 == this.x) {
                    U2 = this.z.ae();
                    iArr2 = this.z.af();
                    iArr3 = null;
                } else if (4 == this.x) {
                    U2 = this.z.S();
                    iArr2 = this.z.T();
                    iArr3 = null;
                } else if (5 == this.x) {
                    U2 = this.z.aa();
                    int[] iArr4 = dji.pilot.fpv.camera.more.a.z_;
                    iArr2 = this.z.ab();
                    iArr3 = null;
                    iArr = iArr4;
                    i2 = 1;
                } else if (6 == this.x) {
                    U2 = this.z.ak();
                    iArr2 = this.z.al();
                    iArr3 = null;
                } else if (7 == this.x) {
                    U2 = this.z.ac();
                    iArr2 = this.z.ad();
                    iArr3 = null;
                } else if (8 == this.x) {
                    U2 = this.z.ag();
                    iArr2 = this.z.ah();
                    iArr3 = null;
                } else if (9 == this.x) {
                    U2 = this.z.am();
                    iArr2 = this.z.an();
                    iArr3 = null;
                } else {
                    if (10 == this.x) {
                        U2 = this.z.ao();
                        iArr2 = this.z.ap();
                        iArr3 = null;
                    }
                    iArr3 = null;
                    i2 = 1;
                    iArr2 = null;
                    U2 = null;
                }
            } else if (101 == this.x) {
                U2 = this.z.at();
                iArr2 = this.z.av();
                iArr3 = null;
            } else if (102 == this.x) {
                U2 = this.z.aw();
                iArr2 = this.z.ax();
                iArr3 = null;
            } else if (103 == this.x) {
                U2 = this.z.ay();
                iArr3 = dji.pilot.fpv.camera.more.a.B;
                iArr2 = this.z.az();
            } else if (104 == this.x) {
                U2 = this.z.aA();
                iArr2 = this.z.aC();
                iArr3 = null;
            } else if (105 == this.x) {
                U2 = this.z.aD();
                iArr2 = this.z.aE();
                iArr3 = null;
            } else if (106 == this.x) {
                U2 = this.z.ar();
                iArr2 = this.z.as();
                iArr3 = null;
            } else {
                if (107 == this.x) {
                    U2 = this.z.aF();
                    iArr2 = this.z.aG();
                    iArr3 = null;
                }
                iArr3 = null;
                i2 = 1;
                iArr2 = null;
                U2 = null;
            }
            a(i2, U2, iArr, iArr2, iArr3, arrayList);
        }
        return arrayList;
    }

    protected void a(int i, String[] strArr, int[] iArr, int[] iArr2, int[] iArr3, List<d> list) {
        int length = iArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3;
            d dVar = new d();
            dVar.e = i;
            dVar.f = strArr[i2];
            if (iArr != null) {
                i3 = iArr[i2];
            } else {
                i3 = 0;
            }
            dVar.g = i3;
            if (iArr3 != null) {
                i3 = iArr3[i2];
            } else {
                i3 = 0;
            }
            dVar.h = i3;
            dVar.j = iArr2[i2];
            dVar.k = false;
            dVar.i = null;
            list.add(dVar);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            b();
            this.w = new c(getContext());
            this.w.a(this.v);
            this.t = (DJIListView) findViewById(R.id.m6);
            this.t.setAdapter(this.w);
            this.t.setOnItemClickListener(this.u);
        }
    }

    public void dispatchOnStart() {
        this.t.setEnabled(true);
    }

    public void dispatchOnStop() {
        if (103 == this.x) {
            dji.thirdparty.a.c.a().e(DJICameraTauSceneView.b.TOHIDE);
        }
    }

    public void dispatchOnResume() {
        dji.thirdparty.a.c.a().a(this);
    }

    public void dispatchOnPause() {
        dji.thirdparty.a.c.a().d(this);
    }

    public View getView() {
        return this;
    }
}
