package dji.device.pano;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.alipay.sdk.j.f;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.common.utils.BitmapUtil;
import dji.device.common.DJIUIEventManagerLongan.e;
import dji.device.common.DJIUIEventManagerLongan.g;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.widget.LonganPopWarnView;
import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.logic.f.b;
import dji.logic.f.d;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushCurPanoFileName;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushShotParams$PanoMode;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.PhotoState;
import dji.midware.data.model.P3.DataCameraSwitchUSB;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.Thread.State;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class LonganPanoStatusManager {
    public static boolean isNeedSwitchLiveviewContinus = false;
    public static dji.device.camera.a.c.a mPanoType = dji.device.camera.a.c.a.PANO_AUTO;
    String FOV = "74.f";
    final int MAX_FAILED_TIMES = 3;
    private final int MSG_ID_DOWNLOAD_ALL_FINISH = 5;
    private final int MSG_ID_DOWNLOAD_FAILED = 9;
    private final int MSG_ID_DOWNLOAD_ONE = 4;
    private final int MSG_ID_PANO_FINISH = 8;
    private final int MSG_ID_READY_DISPLAY = 16;
    private final int MSG_ID_RESET = 7;
    private final int MSG_ID_START_PANO = 3;
    private final int MSG_ID_STRAT_DOWNLOAD = 1;
    private final int MSG_ID_SWITCHUSB_CONTINUS = 18;
    private final int MSG_ID_SWITCHUSB_FAILED = 17;
    private final int MSG_ID_SWITCH_USB = 2;
    private final int MSG_ID_TAKE_PANO_TINISH = 6;
    final String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath();
    final int USB_DOWNLOAD = 1;
    final int USB_LIVEVIEW = 0;
    String curResultFileName = null;
    public boolean isConnectLost = false;
    private boolean isDownOneFinished = true;
    boolean isReset = true;
    Context mCtx;
    int mCurDownloadedIndex = 0;
    int mCurSavedNumber = 0;
    private a mCurStatus = a.NOT_PANOING;
    int mCurTakenNumber = 0;
    int mCurTakingNumber = 0;
    Thread mDownloadThead = new Thread(new Runnable(this) {
        final /* synthetic */ LonganPanoStatusManager a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.downScreennail((DJIAlbumFileInfo) this.a.mFileInfos.get(this.a.mCurDownloadedIndex), new dji.logic.album.a.d.a<DJIAlbumFile>(this) {
                final /* synthetic */ AnonymousClass4 a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.a.isDownOneFinished = false;
                }

                public void a(long j, long j2, long j3) {
                }

                public void a(long j, long j2) {
                }

                public void a(DJIAlbumFile dJIAlbumFile) {
                    this.a.a.isDownOneFinished = true;
                    Log.d("pano", "succeed");
                    if (this.a.a.mHandler != null) {
                        this.a.a.mHandler.sendMessage(this.a.a.mHandler.obtainMessage(4, 0, 0, dJIAlbumFile.e));
                    }
                }

                public void a(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
                    Log.d("pano", f.b);
                    this.a.a.isDownOneFinished = true;
                    Log.d("pano", f.b + dJIAlbumPullErrorType + "failed times:" + this.a.a.mReDownloadFailedTimes);
                    LonganPanoStatusManager longanPanoStatusManager = this.a.a;
                    longanPanoStatusManager.mReDownloadFailedTimes++;
                    if (this.a.a.mHandler != null) {
                        this.a.a.mHandler.sendEmptyMessageDelayed(1, 300);
                    }
                    this.a.a.setStatus(a.REDOWNLOADING_PANO_PHOTO);
                }
            });
            this.a.setStatus(a.DOWNLOADING_PANO_PHOTO);
        }
    });
    ArrayList<DJIAlbumFileInfo> mFileInfos;
    Handler mHandler = new Handler(new Callback(this) {
        final /* synthetic */ LonganPanoStatusManager a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            if (this.a.mHandler != null) {
                switch (message.what) {
                    case 1:
                        if (this.a.mCurStatus != a.TAKING_PANO_FINISH && this.a.mCurStatus != a.DOWNLOADING_PANO_PHOTO && this.a.mCurStatus != a.REDOWNLOADING_PANO_PHOTO && this.a.mCurStatus != a.REDOWNLOADING_FAILED) {
                            this.a.mHandler.removeMessages(1);
                            break;
                        }
                        c();
                        break;
                        break;
                    case 2:
                        this.a.switchUsbTo(message.arg1);
                        break;
                    case 3:
                        if (this.a.mCurStatus == a.DOWNLOADING_FINISH) {
                            Log.d("pano", "download finished");
                            a();
                            break;
                        }
                        break;
                    case 4:
                        a(message);
                        break;
                    case 5:
                        this.a.setStatus(a.DOWNLOADING_FINISH);
                        this.a.mHandler.sendEmptyMessage(3);
                        if (DataCameraGetPushStateInfo.getInstance().getVerstion() < 4) {
                            this.a.mHandler.sendMessage(this.a.mHandler.obtainMessage(2, 0, 0));
                            break;
                        }
                        break;
                    case 6:
                        if (DataCameraGetPushStateInfo.getInstance().getVerstion() < 4) {
                            this.a.USBGotoDownload();
                        } else {
                            this.a.mHandler.sendEmptyMessage(1);
                        }
                        c.a().e(m.HIDE_ALL);
                        break;
                    case 7:
                        this.a.reset();
                        if (this.a.mUsb_status == 1 && DataCameraGetPushStateInfo.getInstance().getVerstion() < 4) {
                            this.a.mHandler.sendEmptyMessage(18);
                            break;
                        }
                    case 8:
                        c.a().e(g.PANO_LOAD_FINISH);
                        this.a.mHandler.sendEmptyMessageDelayed(16, 500);
                        this.a.mHandler.sendEmptyMessage(7);
                        this.a.USBGotoLiveView();
                        break;
                    case 9:
                        this.a.mHandler.removeMessages(1);
                        this.a.setStatus(a.REDOWNLOADING_FAILED);
                        Log.d("pano", "redown");
                        break;
                    case 16:
                        this.a.setStatus(a.READY_DISPLAY);
                        this.a.USBGotoLiveView();
                        break;
                    case 17:
                        if (!LonganPanoStatusManager.isNeedSwitchLiveviewContinus) {
                            LonganPanoStatusManager longanPanoStatusManager = this.a;
                            longanPanoStatusManager.mReSwitchUSBTimes++;
                            if (this.a.mReSwitchUSBTimes >= 3) {
                                this.a.setStatus(a.SWITCH_USB_FAILED);
                                this.a.mHandler.sendEmptyMessage(7);
                                this.a.mReSwitchUSBTimes = 0;
                                c.a().e(g.PANO_SWITCHUSB_FAILED);
                                LonganPanoStatusManager.isNeedSwitchLiveviewContinus = true;
                                break;
                            }
                            this.a.switchUsbTo(message.arg1);
                            break;
                        }
                        break;
                    case 18:
                        if (DataCameraGetPushStateInfo.getInstance().getVerstion() < 4 && LonganPanoStatusManager.isNeedSwitchLiveviewContinus) {
                            this.a.switchUsbTo(0);
                            this.a.mHandler.sendEmptyMessageDelayed(18, 2000);
                            break;
                        }
                    default:
                        break;
                }
            }
            return false;
        }

        private void a(Message message) {
            if (this.a.mHandler != null) {
                this.a.mHandler.sendEmptyMessage(1);
            }
            if (this.a.mCurDownloadedIndex != this.a.mTotalNumber - 1) {
                c.a().e(g.PANO_DOWNLOADED_ONE_FILE);
            } else if (this.a.mHandler != null) {
                this.a.mHandler.sendEmptyMessage(5);
            }
            this.a.mReDownloadFailedTimes = 0;
            LonganPanoStatusManager longanPanoStatusManager = this.a;
            longanPanoStatusManager.mCurDownloadedIndex++;
            this.a.saveScreenShot((Bitmap) message.obj, this.a.mCurDownloadedIndex + dji.pilot2.main.a.a.n);
            this.a.mLocalFiles.add(this.a.savePath + this.a.mCurDownloadedIndex + dji.pilot2.main.a.a.n);
        }

        private void a() {
            this.a.mLocalFiles.addAll(b());
            this.a.curResultFileName = this.a.resutFilePath + "DJIPANO_" + this.a.mPanoFileCreateTime + dji.pilot2.main.a.a.n;
            this.a.mLocalFiles.add(this.a.curResultFileName);
            this.a.startPano((String[]) this.a.mLocalFiles.toArray(new String[this.a.mLocalFiles.size()]));
        }

        private ArrayList<String> b() {
            DataCameraGetPushShotParams$PanoMode panoMode = DataCameraGetPushShotParams.getInstance().getPanoMode();
            ArrayList<String> arrayList = new ArrayList();
            arrayList.add("-f");
            this.a.FOV = String.valueOf(b.k(null));
            arrayList.add(this.a.FOV);
            arrayList.add("-m");
            arrayList.add(String.valueOf(panoMode.ordinal()));
            arrayList.add("-o");
            return arrayList;
        }

        private void c() {
            if (this.a.mReDownloadFailedTimes >= 3) {
                if (this.a.mHandler != null) {
                    this.a.mHandler.sendEmptyMessage(9);
                }
            } else if (this.a.mCurDownloadedIndex > this.a.mFileInfos.size() - 1) {
                Log.d("pano", "mCurDownloadedIndex:" + this.a.mCurDownloadedIndex + "mFileInfos.size()" + this.a.mFileInfos.size());
                if (this.a.mHandler != null) {
                    this.a.mHandler.sendEmptyMessageDelayed(1, 500);
                }
            } else {
                this.a.downloadOnePhoto();
            }
        }
    });
    ArrayList<String> mLocalFiles;
    long mPanoFileCreateTime;
    float mProgressUnit = 0.0f;
    int mReDownloadFailedTimes = 0;
    int mReSwitchUSBTimes = 0;
    int mTotalNumber = 0;
    int mUsb_status = 0;
    dji.logic.album.a.b.c nailLoader;
    final String resutFilePath = (this.SDCARD_PATH + "/DJI/dji.pilot/DJI Album/");
    final String savePath = (this.SDCARD_PATH + "/DJI/dji.pilot/DJI Pano/");

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[o.values().length];

        static {
            try {
                a[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum a {
        NOT_PANOING,
        READY_START,
        GIMBNAL_BUSY,
        GIMBAL_INCLINE,
        READY_NEXT,
        TAKING_PANO_PHOTO,
        TAKING_PANO_FINISH,
        DOWNLOADING_PANO_PHOTO,
        DOWNLOADING_FINISH,
        REDOWNLOADING_PANO_PHOTO,
        REDOWNLOADING_FAILED,
        SWITCH_USB_FAILED,
        STITCHING,
        READY_DISPLAY,
        STITCH_FAILED
    }

    public LonganPanoStatusManager(Context context) {
        this.mCtx = context;
        init();
    }

    private void init() {
        c.a().a(this);
        onEventMainThread(dji.device.camera.a.c.getInstance());
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        this.mFileInfos = new ArrayList();
        this.mLocalFiles = new ArrayList();
        this.mLocalFiles.add("-i");
        if (ServiceManager.getInstance().isConnected() && !d.e(null)) {
            switchUsbTo(0);
        }
    }

    public void uninit() {
        c.a().d(this);
        reset();
        this.mHandler = null;
    }

    public a getStatus() {
        return this.mCurStatus;
    }

    public void setStatus(a aVar) {
        if (this.mCurStatus != aVar || this.mCurStatus == a.TAKING_PANO_PHOTO) {
            this.mCurStatus = aVar;
            c.a().e(this);
        }
    }

    public void reset() {
        c.a().e(m.SHOW_ALL);
        if (this.mFileInfos != null) {
            this.mFileInfos.clear();
        }
        if (this.mLocalFiles != null) {
            this.mLocalFiles.clear();
            this.mLocalFiles.add("-i");
        }
        this.mCurSavedNumber = 0;
        this.mTotalNumber = 0;
        this.mCurDownloadedIndex = 0;
        this.mCurTakenNumber = 0;
        this.mReDownloadFailedTimes = 0;
        this.mProgressUnit = 0.0f;
        setStatus(a.READY_START);
    }

    public Bitmap getResultPano() {
        if (this.mCurStatus != a.READY_DISPLAY) {
            return null;
        }
        return BitmapUtil.getLoacalBitmap(this.curResultFileName);
    }

    public int getSmallPhotoTotalNum() {
        DataCameraGetPushShotParams instance = DataCameraGetPushShotParams.getInstance();
        if (instance.getPanoMode() == DataCameraGetPushShotParams$PanoMode.Manual || instance.getPanoMode() == DataCameraGetPushShotParams$PanoMode.Auto360) {
            this.mTotalNumber = 8;
        } else if (instance.getPanoMode() == DataCameraGetPushShotParams$PanoMode.SECTORIAL) {
            this.mTotalNumber = 9;
        } else {
            this.mTotalNumber = 5;
        }
        return this.mTotalNumber;
    }

    public int getCurTakedPhotoIndex() {
        return this.mCurTakenNumber == 0 ? 1 : this.mCurTakenNumber;
    }

    public float getProgressUnit() {
        return DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity / (((float) this.mTotalNumber) * 2.0f);
    }

    public void USBGotoLiveView() {
        Log.d("pano", "usb want to:live");
        if (this.mUsb_status != 0 && this.mHandler != null) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(2, 0, 0));
        }
    }

    public void USBGotoDownload() {
        Log.d("pano", "usb want to:down");
        if (this.mUsb_status != 1 && this.mHandler != null) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(2, 1, 0));
        }
    }

    public void retryDownload() {
        this.mReDownloadFailedTimes = 0;
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessage(1);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (this.mCurStatus != a.NOT_PANOING) {
            if (dataCameraGetPushStateInfo.getPhotoState() == PhotoState.FullView) {
                if (this.mCurStatus == a.READY_START) {
                    c.a().e(g.PANO_START);
                }
                if (this.mCurStatus == a.READY_START || this.mCurStatus == a.READY_NEXT) {
                    setStatus(a.TAKING_PANO_PHOTO);
                }
            } else if (dataCameraGetPushStateInfo.getPhotoState() == PhotoState.NO && this.mCurStatus == a.TAKING_PANO_PHOTO) {
                reset();
                c.a().e(g.PANO_FINISH);
            }
        }
    }

    public void onEventMainThread(dji.device.camera.a.c cVar) {
        if (cVar.c() == dji.device.camera.a.c.b.PANO) {
            if (this.mCurStatus == a.NOT_PANOING) {
                reset();
                setStatus(a.READY_START);
            }
            mPanoType = cVar.d();
            return;
        }
        setStatus(a.NOT_PANOING);
    }

    public void onEventBackgroundThread(dji.device.camera.a.a aVar) {
        if (aVar.d() == dji.device.camera.a.a.a.RECORD) {
            setStatus(a.NOT_PANOING);
        }
    }

    public void onEventMainThread(DataCameraGetPushCurPanoFileName dataCameraGetPushCurPanoFileName) {
        if (this.mCurStatus != a.NOT_PANOING) {
            DJIAlbumFileInfo dJIAlbumFileInfo = new DJIAlbumFileInfo();
            dJIAlbumFileInfo.d = (int) dataCameraGetPushCurPanoFileName.getIndex();
            dJIAlbumFileInfo.c = dataCameraGetPushCurPanoFileName.getCreateTime();
            dJIAlbumFileInfo.e = dataCameraGetPushCurPanoFileName.getCurSavedNumber();
            this.mCurSavedNumber = dataCameraGetPushCurPanoFileName.getCurSavedNumber();
            this.mTotalNumber = dataCameraGetPushCurPanoFileName.getTotalNumber();
            if (dataCameraGetPushCurPanoFileName.getPanoCreateTime() != 0) {
                this.mPanoFileCreateTime = dataCameraGetPushCurPanoFileName.getPanoCreateTime();
            }
            Log.d("pano", "mCurTakenNumber:" + this.mCurTakenNumber + "mTotalNumber" + this.mTotalNumber + "event.getCurTakenNumber():" + dataCameraGetPushCurPanoFileName.getCurTakenNumber());
            if (!(this.mCurTakenNumber >= this.mTotalNumber || this.mTotalNumber == 0 || this.mCurTakenNumber == dataCameraGetPushCurPanoFileName.getCurTakenNumber())) {
                this.mCurTakenNumber = dataCameraGetPushCurPanoFileName.getCurTakenNumber();
                setStatus(a.TAKING_PANO_PHOTO);
                c.a().e(g.PANO_TAKED_ONE);
            }
            Log.d("pano", "mCurSavedNumber:" + this.mCurSavedNumber + "mFileInfos.size" + this.mFileInfos.size());
            if (this.mCurSavedNumber > this.mFileInfos.size()) {
                this.mFileInfos.add(dJIAlbumFileInfo);
                Log.d("pano", "add one");
            }
            if (this.mProgressUnit == 0.0f && this.mTotalNumber != 0) {
                this.mProgressUnit = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity / (((float) this.mTotalNumber) * 2.0f);
            }
            if (this.mCurSavedNumber == this.mTotalNumber && this.mCurSavedNumber != 0) {
                setStatus(a.TAKING_PANO_FINISH);
                if (this.mHandler != null) {
                    this.mHandler.sendEmptyMessage(6);
                }
                this.mCurTakingNumber = 0;
            }
        }
    }

    private void switchUsbTo(final int i) {
        DataCameraSwitchUSB.getInstance().a(i).start(new dji.midware.e.d(this) {
            final /* synthetic */ LonganPanoStatusManager b;

            public void onSuccess(Object obj) {
                this.b.mReSwitchUSBTimes = 0;
                this.b.mUsb_status = i;
                if (LonganPanoStatusManager.isNeedSwitchLiveviewContinus) {
                    LonganPanoStatusManager.isNeedSwitchLiveviewContinus = false;
                } else if (i == 1 && this.b.mHandler != null) {
                    this.b.mHandler.sendEmptyMessageDelayed(1, 1000);
                }
                Log.d("pano", "switch usb succeed");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                int i = 1;
                LonganPanoStatusManager longanPanoStatusManager = this.b;
                if (i == 1) {
                    i = 0;
                }
                longanPanoStatusManager.mUsb_status = i;
                Log.d("pano", "switch usb failed");
                if (this.b.mHandler != null) {
                    this.b.mHandler.sendMessage(this.b.mHandler.obtainMessage(17, i, 0));
                }
            }
        });
    }

    private void startPano(final String[] strArr) {
        new Thread(this) {
            final /* synthetic */ LonganPanoStatusManager b;

            public void run() {
                this.b.setStatus(a.STITCHING);
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    DJILogHelper.getInstance().LOGD("", "run: " + Stitch.stitching(strArr), false, false);
                    this.b.writeExifInfoToPano();
                    Log.d("Pano", "total time = " + (System.currentTimeMillis() - currentTimeMillis));
                    if (this.b.mHandler != null) {
                        this.b.mHandler.sendEmptyMessage(8);
                    }
                    try {
                        Class cls = Class.forName("dji.pilot2.library.MixAlbumSyncManager");
                        File file = new File(this.b.curResultFileName);
                        try {
                            try {
                                cls.getMethod("scan", new Class[]{Context.class, File.class}).invoke(null, new Object[]{this.b.mCtx, file});
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (IllegalArgumentException e2) {
                                e2.printStackTrace();
                            } catch (InvocationTargetException e3) {
                                e3.printStackTrace();
                            }
                        } catch (NoSuchMethodException e4) {
                            e4.printStackTrace();
                        }
                    } catch (ClassNotFoundException e5) {
                        e5.printStackTrace();
                    }
                } catch (Exception e6) {
                    LonganPopWarnView.getInstance(this.b.mCtx).pop(0, R.string.longan_pano_failed, dji.device.widget.LonganPopWarnView.a.LENGTH_SHORT);
                    c.a().e(g.PANO_FAILED);
                    this.b.reset();
                }
            }
        }.start();
    }

    protected void writeExifInfoToPano() {
        if (this.mLocalFiles != null && this.mLocalFiles.size() > 0) {
            DataCameraGetPushShotParams instance = DataCameraGetPushShotParams.getInstance();
            Float valueOf = Float.valueOf(((float) instance.getApertureSize()) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
            Integer valueOf2 = Integer.valueOf(instance.getRelISO());
            String relShutterString = instance.getRelShutterString();
            Integer valueOf3 = Integer.valueOf(instance.getExposureMode().a());
            Integer valueOf4 = Integer.valueOf(instance.getWhiteBalance());
            Date date = new Date(System.currentTimeMillis());
            dji.b.a.a.a.a(new File(this.curResultFileName), valueOf, relShutterString, valueOf3, valueOf4, valueOf2, "HG310", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date));
        }
    }

    public static void stitchedOnframe() {
        c.a().e(g.PANO_STITCHED_ONE_FRAME);
    }

    private void downloadOnePhoto() {
        if (this.mDownloadThead.getState() == State.NEW) {
            this.mDownloadThead.start();
        } else if (this.isDownOneFinished) {
            this.mDownloadThead.run();
        } else if (this.mHandler != null) {
            this.mHandler.sendEmptyMessageDelayed(1, 50);
        }
    }

    public void saveScreenShot(Bitmap bitmap, String str) {
        try {
            File file = new File(this.savePath);
            File file2 = new File(this.savePath + str);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            OutputStream fileOutputStream = new FileOutputStream(file2);
            if (fileOutputStream != null) {
                bitmap.compress(CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void downScreennail(DJIAlbumFileInfo dJIAlbumFileInfo, dji.logic.album.a.d.a<DJIAlbumFile> aVar) {
        Log.d("pano", "to download" + dJIAlbumFileInfo.d + "subindex:" + dJIAlbumFileInfo.e);
        if (this.nailLoader == null) {
            this.nailLoader = new dji.logic.album.a.b.c();
        }
        this.nailLoader.a(dJIAlbumFileInfo, aVar);
        this.nailLoader.a(dji.midware.data.config.a.a.c.Pano_SCR);
        this.nailLoader.b();
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass5.a[oVar.ordinal()]) {
            case 1:
                this.isConnectLost = false;
                return;
            case 2:
                this.isConnectLost = true;
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(e eVar) {
        if (eVar == e.FORCE_START_PANO) {
            this.mTotalNumber = DataCameraGetPushCurPanoFileName.getInstance().getCurTakenNumber();
            setStatus(a.TAKING_PANO_FINISH);
            this.mHandler.sendEmptyMessage(6);
        }
    }
}
