package dji.dbox.upgrade.p4.statemachine;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.dji.frame.c.h;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.dbox.upgrade.p4.c.a;
import dji.dbox.upgrade.p4.model.DJIUpCfgModel;
import dji.dbox.upgrade.p4.model.DJIUpCfgModel.DJIFirmwareGroup;
import dji.dbox.upgrade.p4.model.DJIUpListElement;
import dji.dbox.upgrade.p4.model.DJIUpUrlModel;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.midware.natives.UpgradeVerify;
import dji.thirdparty.a.c;
import dji.thirdparty.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

abstract class b {
    protected String TAG = getClass().getSimpleName();
    private DJIUpCfgModel cfgModel;
    private boolean cfgSetted = false;
    private Timer daemonTimer;
    private boolean deviceVerSetted = false;
    private int getVerSucSize = 0;
    protected ArrayList<DataCommonGetVersion> getVersions = new ArrayList();
    private Handler handler;
    HandlerThread handlerThread;
    private boolean isAlive = true;
    private List<DJIUpListElement> list;
    private boolean serverCfgGetted = false;
    private int serverCfgIndex = 0;
    private boolean serverCfgSetted = false;
    private a serverManager;
    protected g stateMachine;
    private int verIndex = 0;

    public b(g gVar, Context context) {
        this.stateMachine = gVar;
        this.serverManager = DJIUpgradeP4Service.j();
        this.stateMachine.i();
        this.handlerThread = new HandlerThread("DJIUpCollectPackManager");
        this.handlerThread.start();
    }

    protected void setCfgModel(DJIUpCfgModel dJIUpCfgModel) {
        this.cfgModel = dJIUpCfgModel;
        if (this.cfgModel != null) {
            if (this.cfgModel.b()) {
                dji.dbox.upgrade.p4.a.a.o = "null";
            } else {
                dji.dbox.upgrade.p4.a.a.o = this.cfgModel.b;
            }
            dji.dbox.upgrade.p4.a.a.b(this.TAG, "daemonTimer showVertion=" + dji.dbox.upgrade.p4.a.a.o);
        }
        this.cfgSetted = true;
    }

    private void initModules() {
        if (this.getVersions.size() <= 0) {
            Iterator it = this.cfgModel.h.iterator();
            while (it.hasNext()) {
                DJIUpCfgModel.a aVar = (DJIUpCfgModel.a) it.next();
                if (dji.dbox.upgrade.p4.a.a.x.contains(aVar.i) && aVar.i != DJIFirmwareGroup.GL) {
                    String str = aVar.a;
                    int parseInt = Integer.parseInt(str.substring(0, 2));
                    int parseInt2 = Integer.parseInt(str.substring(2));
                    DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
                    dataCommonGetVersion.setDeviceType(DeviceType.find(parseInt));
                    dataCommonGetVersion.setDeviceModel(parseInt2);
                    this.getVersions.add(dataCommonGetVersion);
                }
            }
        }
    }

    protected void startCollect() {
        resetStatus();
        dji.dbox.upgrade.p4.a.a.d();
        startDeamonTimer();
    }

    protected void resetStatus() {
        this.cfgModel = null;
        this.cfgSetted = false;
        this.serverCfgSetted = false;
        this.deviceVerSetted = false;
        this.serverCfgGetted = false;
        this.getVerSucSize = 0;
        this.serverCfgIndex = 0;
        this.verIndex = 0;
        this.list = null;
        this.isAlive = true;
        if (this.handler == null) {
            this.handler = new Handler(this.handlerThread.getLooper(), new Callback(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public boolean handleMessage(Message message) {
                    switch (message.what) {
                        case 0:
                            this.a.verIndex = this.a.verIndex + 1;
                            if (this.a.verIndex < this.a.getVersions.size() && ServiceManager.getInstance().isConnected()) {
                                this.a.getDeviceVers();
                                break;
                            }
                    }
                    return false;
                }
            });
        }
    }

    protected boolean isAllSetted() {
        return this.cfgSetted && this.serverCfgSetted && this.deviceVerSetted;
    }

    private void startDeamonTimer() {
        final boolean z = DJIUpgradeP4Service.a == DJIUpgradeP4Service.a.b;
        dji.dbox.upgrade.p4.a.a.b(this.TAG, "***************************************" + DJIUpgradeP4Service.a + "******************************************");
        startGetDeviceCfg();
        getServerCFG();
        dji.dbox.upgrade.p4.a.a.b(this.TAG, "***************************************" + DJIUpgradeP4Service.a + "******************************************");
        if (this.daemonTimer == null) {
            this.daemonTimer = new Timer();
            this.daemonTimer.schedule(new TimerTask(this) {
                final /* synthetic */ b b;

                public void run() {
                    boolean z = true;
                    if (this.b.isAllSetted()) {
                        dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "daemonTimer serverCfgGetted=" + this.b.serverCfgGetted + " deviceCfgGetted=" + (this.b.cfgModel != null));
                        dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "isAllSetted()=" + this.b.isAllSetted());
                        if (this.b.serverCfgGetted && this.b.cfgModel != null) {
                            DJIUpListElement dJIUpListElement = null;
                            DJIUpCfgModel dJIUpCfgModel = null;
                            for (DJIUpListElement dJIUpListElement2 : this.b.list) {
                                DJIUpListElement dJIUpListElement22;
                                if (dJIUpListElement22.cfgModel != null) {
                                    DJIUpCfgModel dJIUpCfgModel2;
                                    dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "element.cfgModel=" + dJIUpListElement22.cfgModel.d + " cfgModel.antirollback " + this.b.cfgModel.d);
                                    if (dJIUpListElement22.cfgModel.d >= this.b.cfgModel.d) {
                                        dJIUpListElement22.isAllow = true;
                                    } else {
                                        dJIUpListElement22.isAllow = false;
                                    }
                                    if (dJIUpCfgModel == null) {
                                        dJIUpCfgModel2 = dJIUpListElement22.cfgModel;
                                    } else if (this.b.compareFirVer(dJIUpListElement22.cfgModel.b, dJIUpCfgModel.b) == 1) {
                                        dJIUpCfgModel2 = dJIUpListElement22.cfgModel;
                                    } else {
                                        dJIUpListElement22 = dJIUpListElement;
                                        dJIUpCfgModel2 = dJIUpCfgModel;
                                    }
                                    dJIUpCfgModel = dJIUpCfgModel2;
                                    dJIUpListElement = dJIUpListElement22;
                                }
                            }
                            dji.dbox.upgrade.p4.a.a.v = dJIUpListElement;
                            if (this.b.cfgModel.b()) {
                                dji.dbox.upgrade.p4.a.a.s = true;
                                dji.dbox.upgrade.p4.a.a.r = false;
                            } else {
                                boolean z2;
                                if (this.b.compareFirVer(dJIUpCfgModel.b, this.b.cfgModel.b) == 1) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                dji.dbox.upgrade.p4.a.a.s = z2;
                                if (dJIUpCfgModel.c == 0) {
                                    z = false;
                                }
                                dji.dbox.upgrade.p4.a.a.r = z;
                            }
                            dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "daemonTimer latestCfgModel=" + dJIUpCfgModel.b + " cfgModel=" + this.b.cfgModel.b);
                            dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "daemonTimer isNeedUpgradeMC " + dji.dbox.upgrade.p4.a.a.s + " isNeedLock " + dji.dbox.upgrade.p4.a.a.r);
                        }
                        if (this.b.cfgModel != null && this.b.deviceVerSetted) {
                            if (!dji.dbox.upgrade.p4.a.a.p.equals("")) {
                                int access$500;
                                if (z) {
                                    access$500 = this.b.compareVers();
                                } else {
                                    access$500 = 0;
                                }
                                if (access$500 < 0) {
                                    dji.dbox.upgrade.p4.a.a.p += "-";
                                } else if (access$500 > 0) {
                                }
                            }
                            dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "daemonTimer showVertion2=" + dji.dbox.upgrade.p4.a.a.o);
                        }
                        if (this.b.cfgModel == null) {
                            dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "deviceCfgGetted=" + this.b.cfgModel);
                        }
                        if (this.b.list != null) {
                            Collections.sort(this.b.list, new Comparator<DJIUpListElement>(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public /* synthetic */ int compare(Object obj, Object obj2) {
                                    return a((DJIUpListElement) obj, (DJIUpListElement) obj2);
                                }

                                public int a(DJIUpListElement dJIUpListElement, DJIUpListElement dJIUpListElement2) {
                                    return this.a.b.compareFirVer(dJIUpListElement2.product_version, dJIUpListElement.product_version);
                                }
                            });
                        }
                        if (!ServiceManager.getInstance().isRemoteOK()) {
                            dji.dbox.upgrade.p4.a.a.s = false;
                        }
                        dji.dbox.upgrade.p4.a.a.t = this.b.list;
                        this.b.stateMachine.h();
                        if (this.b.daemonTimer != null) {
                            this.b.daemonTimer.cancel();
                            this.b.daemonTimer = null;
                        }
                        c.a().e(dji.dbox.upgrade.p4.a.b.CollectComplete);
                    }
                }
            }, 0, 1000);
        }
    }

    protected void startGetDeviceCfg() {
    }

    private int compareVers() {
        Iterator it = this.getVersions.iterator();
        int i = 0;
        while (it.hasNext()) {
            DataCommonGetVersion dataCommonGetVersion = (DataCommonGetVersion) it.next();
            if (dataCommonGetVersion.isGetted()) {
                String firmVer = dataCommonGetVersion.getFirmVer(".");
                int value = dataCommonGetVersion.getDeviceType().value();
                int modelId = dataCommonGetVersion.getModelId();
                if (this.cfgModel.h.size() == 0) {
                    return 1;
                }
                Iterator it2 = this.cfgModel.h.iterator();
                while (it2.hasNext()) {
                    DJIUpCfgModel.a aVar = (DJIUpCfgModel.a) it2.next();
                    if (aVar.e == value && aVar.f == modelId) {
                        int compareFirVer = compareFirVer(firmVer, aVar.b);
                        if (compareFirVer > 0) {
                            return 1;
                        }
                        if (compareFirVer < 0) {
                            i = -1;
                        }
                    }
                }
                continue;
            }
            i = i;
        }
        return i;
    }

    protected int compareFirVer(String str, String str2) {
        String replace = str.replace(".", "");
        String replace2 = str2.replace(".", "");
        int parseInt = Integer.parseInt(replace);
        int parseInt2 = Integer.parseInt(replace2);
        if (parseInt > parseInt2) {
            return 1;
        }
        if (parseInt == parseInt2) {
            return 0;
        }
        return -1;
    }

    protected void getDeviceVers() {
        if (DJIUpgradeP4Service.a == DJIUpgradeP4Service.a.b) {
            initModules();
            if (this.getVersions.size() <= 0) {
                this.deviceVerSetted = true;
                return;
            } else if (this.verIndex >= this.getVersions.size()) {
                this.deviceVerSetted = true;
                return;
            } else {
                final DataCommonGetVersion dataCommonGetVersion = (DataCommonGetVersion) this.getVersions.get(this.verIndex);
                dataCommonGetVersion.start(new d(this) {
                    final /* synthetic */ b b;

                    public void onSuccess(Object obj) {
                        dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "getVers verIndex=" + this.b.verIndex + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + dataCommonGetVersion.getDeviceType() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + dataCommonGetVersion.getModelId() + " suc");
                        this.b.getVerSucSize = this.b.getVerSucSize + 1;
                        this.b.next();
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "getVers " + dataCommonGetVersion.getDeviceType() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + dataCommonGetVersion.getModelId() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aVar);
                        this.b.next();
                    }
                }, 500, 2);
                return;
            }
        }
        this.deviceVerSetted = true;
    }

    private void next() {
        if (this.verIndex == this.getVersions.size() - 1) {
            dji.dbox.upgrade.p4.a.a.b(this.TAG, "getVers over getVerSucSize=" + this.getVerSucSize);
            this.deviceVerSetted = true;
            this.verIndex = 0;
        } else if (this.handler != null) {
            this.handler.sendEmptyMessageDelayed(0, 50);
        }
    }

    private void getServerCFG() {
        if (this.list != null) {
            this.list.clear();
        }
        if (this.serverManager.b()) {
            getServerList();
        } else {
            getUrlList();
        }
    }

    private void getUrlList() {
        if (this.isAlive) {
            try {
                this.serverManager.a(new dji.thirdparty.afinal.f.a<String>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                    }

                    public void a(long j, long j2) {
                    }

                    public void a(String str) {
                        DJIUpUrlModel dJIUpUrlModel = (DJIUpUrlModel) h.b(str, DJIUpUrlModel.class);
                        if (dJIUpUrlModel == null || dJIUpUrlModel.apis == null || dJIUpUrlModel.apis.allfile == null) {
                            this.a.serverCfgSetted = true;
                            dji.dbox.upgrade.p4.a.a.b(this.a.TAG, "getUrlList -- urlModel null");
                            return;
                        }
                        dji.dbox.upgrade.p4.a.a.b(this.a.TAG, "getUrlList -- onSuccess");
                        this.a.serverManager.a(dJIUpUrlModel);
                        this.a.getServerList();
                    }

                    public void a(Throwable th, int i, String str) {
                        this.a.serverCfgSetted = true;
                        dji.dbox.upgrade.p4.a.a.b(this.a.TAG, "getUrlList -- onFailure " + str);
                    }
                });
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                dji.dbox.upgrade.p4.a.a.b(this.TAG, "getUrlList --" + e.getMessage());
                this.serverCfgSetted = true;
            }
        }
    }

    private void getServerList() {
        if (this.isAlive) {
            try {
                this.serverManager.b(new dji.thirdparty.afinal.f.a<String>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void a(String str) {
                        try {
                            this.a.list = h.a(str, new TypeToken<List<DJIUpListElement>>(this) {
                                final /* synthetic */ AnonymousClass5 a;

                                {
                                    this.a = r1;
                                }
                            });
                            dji.dbox.upgrade.p4.a.a.b(this.a.TAG, "getServerList -- onSuccess size=" + this.a.list.size());
                            dji.dbox.upgrade.p4.a.a.b(this.a.TAG, "getServerList -- onSuccess listJson=" + str);
                            if (this.a.list.size() > 0) {
                                this.a.filterElements();
                                this.a.serverCfgIndex = 0;
                                this.a.nextCfg();
                                return;
                            }
                            this.a.serverCfgSetted = true;
                        } catch (Exception e) {
                            dji.dbox.upgrade.p4.a.a.b(this.a.TAG, "getServerList-Exception -- " + str);
                            this.a.serverCfgSetted = true;
                        }
                    }

                    public void a(boolean z) {
                    }

                    public void a(long j, long j2) {
                    }

                    public void a(Throwable th, int i, String str) {
                        this.a.serverCfgSetted = true;
                        dji.dbox.upgrade.p4.a.a.b(this.a.TAG, "getServerCFG -- onFailure " + str);
                    }
                });
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                dji.dbox.upgrade.p4.a.a.b(this.TAG, "getServerCFG --" + e.getMessage());
                this.serverCfgSetted = true;
            }
        }
    }

    protected void filterElements() {
        int size = this.list.size();
        int i = 0;
        while (i < size) {
            int i2;
            DJIUpListElement dJIUpListElement = (DJIUpListElement) this.list.get(i);
            if (dJIUpListElement.isDeprecated()) {
                this.list.remove(dJIUpListElement);
                i2 = i - 1;
                i = size - 1;
            } else {
                i2 = i;
                i = size;
            }
            size = i;
            i = i2 + 1;
        }
    }

    private void nextCfg() {
        if (!this.isAlive) {
            return;
        }
        if (this.list == null) {
            this.serverCfgGetted = false;
            this.serverCfgSetted = false;
        } else if (this.serverCfgIndex < this.list.size()) {
            getCfg((DJIUpListElement) this.list.get(this.serverCfgIndex));
            this.serverCfgIndex++;
        } else {
            this.serverCfgGetted = true;
            this.serverCfgSetted = true;
        }
    }

    private void getCfg(final DJIUpListElement dJIUpListElement) {
        dji.dbox.upgrade.p4.a.a.b(this.TAG, "getCfg --" + dJIUpListElement.product_version);
        try {
            this.serverManager.a(new dji.thirdparty.afinal.f.a<File>(this) {
                final /* synthetic */ b b;

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(File file) {
                    String absolutePath = file.getAbsolutePath();
                    String replace = absolutePath.replace(".cfg.sig", "_verify.xml");
                    if (UpgradeVerify.native_verifyCfg(absolutePath, replace)) {
                        try {
                            File file2 = new File(replace);
                            dJIUpListElement.cfgModel = dji.dbox.upgrade.p4.model.a.a.a(file2);
                            dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "getCfg onSuccess delete=" + file2.delete());
                            this.b.nextCfg();
                            return;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "getCfg --verify failure");
                    this.b.serverCfgSetted = true;
                }

                public void a(Throwable th, int i, String str) {
                    dji.dbox.upgrade.p4.a.a.b(this.b.TAG, "getCfg --onFailure " + str);
                    this.b.serverCfgSetted = true;
                }
            }, dJIUpListElement.product_version);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            dji.dbox.upgrade.p4.a.a.b(this.TAG, "getCfg --onFailure " + e.getMessage());
            this.serverCfgSetted = true;
        }
    }

    public void stop() {
        this.isAlive = false;
        if (this.daemonTimer != null) {
            this.daemonTimer.cancel();
            this.daemonTimer = null;
        }
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
    }
}
