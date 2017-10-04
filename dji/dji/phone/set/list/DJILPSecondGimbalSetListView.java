package dji.phone.set.list;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.common.view.set.b.b;
import dji.device.common.view.set.listview.DJIBaseSecondaryListView;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetSSDVideoFormat;
import dji.midware.data.model.P3.DataCameraSetVideoFormat;
import dji.midware.data.model.P3.DataCameraSetVideoRecordMode;
import dji.midware.data.model.P3.DataGimbalGetHandleParams;
import dji.midware.data.model.P3.DataGimbalSetHandleParams;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.pilot.set.e;
import dji.pilot.set.g;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.List;

public class DJILPSecondGimbalSetListView extends DJIBaseSecondaryListView {
    public static final int i = 0;
    public static final int j = 1;
    public static final int k = 2;
    public static final int l = 3;
    public static final int m = 4;
    public static final int n = 5;
    public static final int o = 6;
    public static final int p = 7;
    public static final int q = 1001;
    public static final int r = 1002;
    private static final int s = 1;
    private int t = 1;
    private DJICameraDataManagerCompat u = DJICameraDataManagerCompat.getInstance();
    private int v = 0;

    public class a {
        public int a;
        final /* synthetic */ DJILPSecondGimbalSetListView b;

        public a(DJILPSecondGimbalSetListView dJILPSecondGimbalSetListView) {
            this.b = dJILPSecondGimbalSetListView;
        }
    }

    public DJILPSecondGimbalSetListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void a() {
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventMainThread(DataCameraGetPushShotParams.getInstance());
        }
        if (ServiceManager.getInstance().isConnected()) {
            d();
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int imageFormat;
        int i = Integer.MAX_VALUE;
        if (1 == this.d) {
            imageFormat = dataCameraGetPushShotParams.getImageFormat();
        } else if (this.d == 0) {
            if (dataCameraGetPushShotParams.getVideoStandard() != this.t) {
                this.t = dataCameraGetPushShotParams.getVideoStandard();
                this.e = c();
                this.f = Integer.MAX_VALUE;
                this.g = Integer.MAX_VALUE;
                this.c.a(this.e);
                this.c.notifyDataSetChanged();
            }
            imageFormat = dataCameraGetPushShotParams.getVideoFormat();
            i = dataCameraGetPushShotParams.getVideoFps();
        } else {
            imageFormat = 2 == this.d ? dataCameraGetPushShotParams.getDigitalFilter() : 5 == this.d ? dji.pilot.set.a.f(this.h) : 1001 == this.d ? this.v : Integer.MAX_VALUE;
        }
        if (b(imageFormat, i)) {
            this.f = imageFormat;
            this.g = i;
            a(this.f, this.g);
        }
    }

    private void e() {
        int i = this.v;
        if (b(i, Integer.MAX_VALUE)) {
            this.f = i;
            this.g = Integer.MAX_VALUE;
            a(this.f, this.g);
            a aVar = new a(this);
            aVar.a = 1001;
            c.a().e(aVar);
        }
    }

    protected void b() {
        this.b = new OnItemClickListener(this) {
            final /* synthetic */ DJILPSecondGimbalSetListView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                final int i2 = ((b) this.a.e.get(i)).i;
                final int i3 = ((b) this.a.e.get(i)).j;
                if (!this.a.b(i2, i3)) {
                    return;
                }
                if (1 == this.a.d) {
                    new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.m).a(i2).start(null);
                } else if (this.a.d == 0) {
                    if (i2 != -1) {
                        final DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
                        new DataCameraSetVideoFormat().a().a(i2).b(i3).start(new d(this) {
                            final /* synthetic */ AnonymousClass1 d;

                            public void onSuccess(Object obj) {
                                if (instance.isGetted() && instance.getCameraType() == CameraType.DJICameraTypeFC550Raw) {
                                    DataCameraSetSSDVideoFormat.getInstance().a(i2).b(i3).start(null);
                                }
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                            }
                        });
                        DataCameraSetVideoRecordMode dataCameraSetVideoRecordMode = new DataCameraSetVideoRecordMode();
                        if (i3 == 7) {
                            dataCameraSetVideoRecordMode.a(2, 0, 0);
                        } else {
                            dataCameraSetVideoRecordMode.a(0, 0, 0);
                        }
                        dataCameraSetVideoRecordMode.start(null);
                    }
                } else if (2 == this.a.d) {
                    if (i2 != this.a.u.getPortraitModeCmdId() || DataCameraGetPushStateInfo.getInstance().getVerstion() >= 4) {
                        new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.Z).a(i2).start(null);
                    } else {
                        e.b(this.a.getContext(), R.string.longan_firmware_not_support, new OnClickListener(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                    }
                } else if (5 == this.a.d) {
                    dji.pilot.set.a.a(this.a.getContext(), g.a, i2);
                    this.a.f = ((b) this.a.e.get(i)).i;
                    this.a.g = Integer.MAX_VALUE;
                    this.a.a(this.a.f, this.a.g);
                } else if (1001 == this.a.d) {
                    this.a.c(i2, i);
                }
            }
        };
    }

    protected List<b> c() {
        String[] pictureFormatAr;
        int[] iArr;
        int[] pictureFormatCmdIdAr;
        int[] iArr2 = null;
        int i = 2;
        List arrayList = new ArrayList();
        if (1 == this.d) {
            pictureFormatAr = this.u.getPictureFormatAr();
            DJICameraDataManagerCompat dJICameraDataManagerCompat = this.u;
            iArr = DJICameraDataManagerCompat.mPictureFormatImgRes;
            pictureFormatCmdIdAr = this.u.getPictureFormatCmdIdAr();
        } else if (this.d == 0) {
            CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
            int videoStandard = DataCameraGetPushShotParams.getInstance().getVideoStandard();
            pictureFormatAr = this.u.getVideoTitles(cameraType, videoStandard);
            pictureFormatCmdIdAr = this.u.getVideoResolutionValues(cameraType, videoStandard);
            i = 0;
            iArr = null;
            iArr2 = this.u.getVideoFpsValues(cameraType, videoStandard);
        } else if (2 == this.d) {
            String[] stringArray;
            int[] intArray;
            if (dji.logic.f.b.i(null)) {
                stringArray = getResources().getStringArray(R.array.camera_digitalfilter_array_osmo);
                intArray = getResources().getIntArray(R.array.camera_digitalfilter_value_array_osmo);
            } else {
                stringArray = this.u.getDigitalFilterAr();
                intArray = this.u.getDigitalFilterCmdIdAr();
            }
            i = 0;
            pictureFormatCmdIdAr = intArray;
            iArr = null;
            pictureFormatAr = stringArray;
        } else if (5 == this.d) {
            pictureFormatAr = this.u.getShowGridAr();
            iArr = DJICameraDataManagerCompat.mShowGridImgRes;
            pictureFormatCmdIdAr = this.u.getShowGridCmdIdAr();
        } else if (1001 == this.d) {
            pictureFormatAr = new String[]{this.h.getString(R.string.longan_gimbal_profile_walk), this.h.getString(R.string.longan_gimbal_profile_sport)};
            iArr = new int[]{R.drawable.gimbal_profile_walk, R.drawable.gimbal_profile_sport};
            pictureFormatCmdIdAr = new int[]{0, 2};
        } else {
            pictureFormatCmdIdAr = null;
            iArr = null;
            pictureFormatAr = null;
        }
        int length = pictureFormatCmdIdAr.length;
        for (videoStandard = 0; videoStandard < length; videoStandard++) {
            arrayList.add(a(videoStandard, i, pictureFormatAr, iArr, pictureFormatCmdIdAr, iArr2));
        }
        return arrayList;
    }

    protected void c(final int i, int i2) {
        new DataGimbalSetHandleParams().d(i).start(new d(this) {
            final /* synthetic */ DJILPSecondGimbalSetListView b;

            public void onSuccess(Object obj) {
                switch (i) {
                    case 0:
                        dji.publics.b.b.a.getInstance().e(dji.publics.b.a.b.p, "1", false);
                        break;
                    case 2:
                        dji.publics.b.b.a.getInstance().e(dji.publics.b.a.b.p, "2", false);
                        break;
                }
                dji.publics.b.b.a.getInstance().e("createtime", System.currentTimeMillis() + "", false).e("device_type", i.getInstance().c()._name(), false).e("pro_id", dji.publics.b.b.a.a, false).e("device_ver", dji.device.common.b.getInstance().b(), true);
                this.b.d();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.d();
            }
        });
    }

    protected void d() {
        final DataGimbalGetHandleParams dataGimbalGetHandleParams = new DataGimbalGetHandleParams();
        dataGimbalGetHandleParams.start(new d(this) {
            final /* synthetic */ DJILPSecondGimbalSetListView b;

            public void onSuccess(Object obj) {
                this.b.v = dataGimbalGetHandleParams.getProfile();
                DJILogHelper.getInstance().LOGD("", "mGimbalProfile = " + this.b.v, false, true);
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.e();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }
}
