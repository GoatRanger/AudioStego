package dji.midware.b;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanFilter.Builder;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.text.TextUtils;
import android.util.Log;
import com.dji.frame.c.l;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import dji.log.DJILogHelper;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@TargetApi(18)
public class a {
    private static Context G = null;
    private static final a L = new a();
    private static final int V = 0;
    private static final int W = 1;
    private static final int X = 2;
    private static final int Y = 3;
    private static final int Z = 4;
    public static String a = "0000FFF0-0000-1000-8000-00805F9B34FB";
    private static final int aa = 10000;
    private static final int ab = 1;
    public static ArrayList<b> e = new ArrayList();
    private static final String g = "BLE";
    private static final String h = "BLELog";
    private static final String i = "BLE ERROR:";
    private static final String j = "BLE DEBUG:";
    private static final boolean k = false;
    private static final String l = "ble_last_address";
    private static final int o = 0;
    private static final int p = 1;
    private static final int q = 2;
    private static String r = "D0:B5:C2:B0:B5:C4";
    private static BluetoothManager s;
    private static BluetoothAdapter t;
    private static BluetoothLeScanner u;
    private Timer A;
    private Timer B;
    private int C = 0;
    private ScanResult D;
    private LeScanCallback E;
    private ScanCallback F;
    private d H = null;
    private e I;
    private List<ScanFilter> J;
    private String K;
    private final BluetoothGattCallback M = new BluetoothGattCallback(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 == 2) {
                this.a.C = 2;
                this.a.d();
                this.a.g();
                this.a.v();
                if (this.a.y == null) {
                    this.a.y = bluetoothGatt;
                }
                this.a.y.discoverServices();
            } else if (i2 == 0) {
                if (this.a.C != 0) {
                    this.a.z();
                    if (this.a.U != null && this.a.U.hasMessages(1)) {
                        this.a.U.sendEmptyMessage(3);
                    }
                }
                this.a.H.a(bluetoothGatt, i, i2);
            }
            this.a.e("onConnectionStateChange:" + i2);
        }

        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            this.a.e("onServicesDiscovered:" + i);
            if (i == 0) {
                int i2 = 0;
                for (BluetoothGattService characteristics : this.a.y.getServices()) {
                    i2 = characteristics.getCharacteristics().size() + i2;
                }
                if (i2 == 0) {
                    this.a.f("mothod : onServicesDiscovered -> totalCharacteristics == 0");
                    throw new RuntimeException("totalCharacteristics == 0");
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.a.y();
                if (this.a.v != null) {
                    this.a.w = this.a.v.getCharacteristic(UUID.fromString(this.a.b));
                    if (this.a.w != null) {
                        this.a.w.setWriteType(1);
                        return;
                    } else {
                        this.a.f("mothod : onServicesDiscovered -> mWriteCharacteristic == null");
                        return;
                    }
                }
                this.a.f("mothod : onServicesDiscovered -> mGattService == null");
            }
        }

        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }

        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            this.a.H.a(bluetoothGatt, bluetoothGattCharacteristic);
        }

        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            DJILogHelper.getInstance().LOGD(a.g, "DJIMethod : onDescriptorWrite (807)" + i, false, true);
        }
    };
    private volatile LinkedList<g> N;
    private final Lock O = new ReentrantLock();
    private final int P = (this.f * 500);
    private long Q = 0;
    private int R = 0;
    private int S;
    private b T = null;
    private f U = new f(this, dji.midware.util.b.b());
    String b = "0000FFF5-0000-1000-8000-00805F9B34FB";
    String c = "0000FFF4-0000-1000-8000-00805F9B34FB";
    String d = "00002902-0000-1000-8000-00805f9b34fb";
    public int f = 7;
    private boolean m = false;
    private boolean n = false;
    private BluetoothGattService v;
    private BluetoothGattCharacteristic w;
    private String x;
    private BluetoothGatt y;
    private Timer z;

    public enum a {
        BLE_CONNECTION_TIMEOUT,
        BLE_CONNECTION_CONNECTED,
        BLE_CONNECTION_DISCONNECTED
    }

    public interface b {
        void a(a aVar);
    }

    public enum c {
        BLE_FIND_DEVICE,
        BLE_DEVICE_CONNECTED,
        BLE_DEVICE_DISCONNECTED,
        BLE_DEVICE_CONNECTING
    }

    public interface d {
        void a(BluetoothGatt bluetoothGatt, int i, int i2);

        void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);
    }

    public interface e {
        void onScanResultUpdate(ArrayList<b> arrayList);
    }

    private class f extends Handler {
        final /* synthetic */ a a;

        public f(a aVar, Looper looper) {
            this.a = aVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.U.sendEmptyMessageDelayed(1, 10000);
                    return;
                case 1:
                    if (this.a.T != null) {
                        this.a.T.a(a.BLE_CONNECTION_TIMEOUT);
                        return;
                    }
                    return;
                case 2:
                    this.a.U.removeMessages(1);
                    if (this.a.T != null) {
                        this.a.T.a(a.BLE_CONNECTION_CONNECTED);
                        return;
                    }
                    return;
                case 3:
                    this.a.U.removeMessages(1);
                    if (this.a.T != null) {
                        this.a.T.a(a.BLE_CONNECTION_DISCONNECTED);
                        return;
                    }
                    return;
                case 4:
                    this.a.e("retry to writeDescriptor");
                    if (message.arg1 == 1 && this.a.y != null) {
                        if (this.a.y.writeDescriptor((BluetoothGattDescriptor) message.obj)) {
                            this.a.a(this.a.y, 0, 2);
                            return;
                        } else {
                            this.a.U.sendMessageDelayed(this.a.U.obtainMessage(4, 1, 0, message.obj), 200);
                            return;
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public class g {
        public byte[] a;
        public long b;
        final /* synthetic */ a c;

        public g(a aVar) {
            this.c = aVar;
        }
    }

    public static a getInstance() {
        return L;
    }

    private a() {
        u();
    }

    public static void a(Context context) {
        G = context;
        if (s == null) {
            s = (BluetoothManager) G.getSystemService("bluetooth");
            if (s == null) {
                DJILogHelper.getInstance().LOGE(g, "BLE ERROR::mothod : setContext -> mBluetoothManager == null", h);
                return;
            }
        }
        t = s.getAdapter();
        if (t == null) {
            DJILogHelper.getInstance().LOGE(g, "BLE ERROR::mothod : setContext -> mBluetoothAdapter == null", h);
        }
    }

    @TargetApi(21)
    private void u() {
        if (VERSION.SDK_INT >= 21) {
            this.F = new ScanCallback(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onScanResult(int i, ScanResult scanResult) {
                    super.onScanResult(i, scanResult);
                    ScanRecord scanRecord = scanResult.getScanRecord();
                    if (this.a.c(scanRecord.getBytes())) {
                        BluetoothDevice device = scanResult.getDevice();
                        c cVar = c.BLE_DEVICE_DISCONNECTED;
                        this.a.e("find DJI device, name:" + device.getName() + "state:" + this.a.C);
                        if (this.a.C == 1) {
                            if (device.getAddress().equals(this.a.x)) {
                                cVar = c.BLE_DEVICE_CONNECTING;
                            }
                        } else if (this.a.C == 2 && device.getAddress().equals(this.a.x)) {
                            cVar = c.BLE_DEVICE_CONNECTED;
                        }
                        b a = this.a.a(device, cVar, scanResult.getRssi());
                        if (!a.e.contains(a)) {
                            boolean z;
                            byte[] manufacturerSpecificData = scanRecord.getManufacturerSpecificData(58816);
                            if (manufacturerSpecificData != null && manufacturerSpecificData[0] == (byte) 0) {
                                if (this.a.D == null) {
                                    this.a.D = scanResult;
                                } else if (scanResult.getRssi() > this.a.D.getRssi()) {
                                    this.a.D = scanResult;
                                }
                                a.d = true;
                                this.a.K = device.getAddress();
                                DJILogHelper.getInstance().LOGD(a.g, "placed on gimbal");
                            }
                            if (device.getBondState() == 12) {
                                z = true;
                            } else {
                                z = false;
                            }
                            a.e = z;
                            a.e.add(a);
                            dji.thirdparty.a.c.a().e(c.BLE_FIND_DEVICE);
                            DJILogHelper.getInstance().LOGD(a.g, "find a dji ble device");
                        }
                    }
                }
            };
            ScanFilter build = new Builder().setServiceUuid(ParcelUuid.fromString(a)).build();
            this.J = new ArrayList(1);
            this.J.add(build);
            return;
        }
        this.E = new LeScanCallback(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                if (bluetoothDevice.getName() != null && !bluetoothDevice.getName().isEmpty() && this.a.c(bArr)) {
                    this.a.a(bluetoothDevice, i);
                }
            }
        };
    }

    private boolean c(byte[] bArr) {
        if ((bArr[9] & 255) == 192 && (bArr[10] & 255) == 229) {
            return true;
        }
        return false;
    }

    public boolean a(d dVar) {
        if (t == null) {
            return false;
        }
        this.H = dVar;
        if (VERSION.SDK_INT >= 21) {
            u = t.getBluetoothLeScanner();
        }
        if (VERSION.SDK_INT < 18) {
            return true;
        }
        b(true);
        return true;
    }

    private boolean b(final boolean z) {
        if (t.getProfileConnectionState(4) != 2) {
            return false;
        }
        t.getProfileProxy(G, new ServiceListener(this) {
            final /* synthetic */ a b;

            public void onServiceDisconnected(int i) {
            }

            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                List connectedDevices = bluetoothProfile.getConnectedDevices();
                if (connectedDevices == null || connectedDevices.size() <= 0) {
                    Log.i("W", "mDevices is null");
                    return;
                }
                BluetoothDevice bluetoothDevice = (BluetoothDevice) connectedDevices.get(0);
                if (z) {
                    this.b.y = bluetoothDevice.connectGatt(a.G, true, this.b.M);
                    this.b.d(bluetoothDevice.getAddress());
                    return;
                }
                boolean z;
                b a = this.b.a(bluetoothDevice, c.BLE_DEVICE_DISCONNECTED, 0);
                if (bluetoothDevice.getBondState() == 12) {
                    z = true;
                } else {
                    z = false;
                }
                a.e = z;
                if (!a.e.contains(a)) {
                    a.e.add(a);
                }
            }
        }, 4);
        return true;
    }

    public boolean a() {
        return t != null && t.isEnabled();
    }

    public boolean b() {
        if (t == null || t.isEnabled()) {
            return false;
        }
        t.enable();
        return true;
    }

    public void a(e eVar) {
        this.I = eVar;
    }

    public boolean c() {
        return this.m;
    }

    public void a(int i) {
        a(i, null);
    }

    public void d() {
        if (!a()) {
            return;
        }
        if (!this.n && !this.m) {
            return;
        }
        if (VERSION.SDK_INT < 21) {
            t.stopLeScan(this.E);
        } else if (u != null) {
            u.stopScan(this.F);
        }
    }

    public void a(int i, List<ScanFilter> list) {
        e("startScan");
        if (t.isEnabled() || t.enable()) {
            if (this.m) {
                d();
                e.clear();
                this.m = false;
            }
            v();
            if (this.U.hasMessages(4)) {
                this.U.removeMessages(4);
            }
            if (this.C == 1) {
                if (this.y != null) {
                    l();
                }
                dji.thirdparty.a.c.a().e(c.BLE_DEVICE_DISCONNECTED);
            }
            if (list == null) {
                list = this.J;
            }
            this.z = new Timer();
            this.z.schedule(new TimerTask(this) {
                final /* synthetic */ a b;

                public void run() {
                    if (this.b.m) {
                        this.b.x();
                    } else {
                        this.b.a(list);
                        this.b.m = true;
                        DJILogHelper.getInstance().LOGD(a.g, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, false, true);
                    }
                    a.e.clear();
                    this.b.b(false);
                    if (this.b.x != null) {
                        BluetoothDevice remoteDevice = a.t.getRemoteDevice(this.b.x);
                        if (remoteDevice != null && this.b.C == 2 && a.s.getConnectedDevices(7).contains(remoteDevice)) {
                            a.e.add(this.b.a(remoteDevice, c.BLE_DEVICE_CONNECTED, 0));
                        }
                    }
                }
            }, 0, 2000);
            this.A = new Timer();
            this.A.schedule(new TimerTask(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.m) {
                        this.a.d();
                        this.a.m = false;
                        DJILogHelper.getInstance().LOGD(a.g, "stop user Scan", false, false);
                        if (this.a.z != null) {
                            this.a.z.cancel();
                        }
                    }
                }
            }, (long) (i * 1000));
        }
    }

    private void a(List<ScanFilter> list) {
        if (!a()) {
            return;
        }
        if (VERSION.SDK_INT < 21) {
            t.startLeScan(this.E);
            return;
        }
        ScanSettings build = new ScanSettings.Builder().setScanMode(2).build();
        if (u == null) {
            u = t.getBluetoothLeScanner();
        }
        BluetoothLeScanner bluetoothLeScanner = u;
        if (list == null) {
            list = this.J;
        }
        bluetoothLeScanner.startScan(list, build, this.F);
    }

    private void v() {
        if (this.z != null) {
            this.z.cancel();
            this.m = false;
        }
        if (this.A != null) {
            this.A.cancel();
        }
    }

    private void w() {
        if (this.C != 1) {
            return;
        }
        if (e.isEmpty()) {
            l();
            dji.thirdparty.a.c.a().e(c.BLE_DEVICE_DISCONNECTED);
            return;
        }
        if (!e.contains(a(t.getRemoteDevice(this.x), c.BLE_DEVICE_DISCONNECTED, 0))) {
            DJILogHelper.getInstance().LOGD(g, com.alipay.sdk.c.a.f, false, true);
            l();
            dji.thirdparty.a.c.a().e(c.BLE_DEVICE_DISCONNECTED);
        }
    }

    private void x() {
        if (this.I != null) {
            if (!e.isEmpty()) {
                Collections.sort(e);
            }
            this.I.onScanResultUpdate(e);
        }
    }

    public ArrayList<b> e() {
        return e;
    }

    public void f() {
        if (this.B == null) {
            this.B = new Timer();
            this.B.schedule(new TimerTask(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (!this.a.m) {
                        DJILogHelper.getInstance().LOGD(a.g, "startAuto Scan", false, false);
                        a.e.clear();
                        this.a.a(null);
                        this.a.n = true;
                    }
                    new Timer().schedule(new TimerTask(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (this.a.a.n && !this.a.a.m) {
                                this.a.a.d();
                                this.a.a.n = false;
                                if (this.a.a.I != null) {
                                    this.a.a.x();
                                }
                                DJILogHelper.getInstance().LOGD(a.g, "BLE auto scan stop", false, false);
                            }
                        }
                    }, 4000);
                }
            }, 1000, 10000);
        }
    }

    public void g() {
        if (this.B != null) {
            this.B.cancel();
            this.n = false;
            this.B = null;
        }
    }

    public String h() {
        return this.x;
    }

    public String i() {
        return G.getSharedPreferences(G.getPackageName(), 0).getString(l, "");
    }

    public String j() {
        return this.K;
    }

    public String a(String str) {
        String name = t.getRemoteDevice(str).getName();
        return name == null ? "" : name;
    }

    public String k() {
        if (!m()) {
            return "";
        }
        String name = t.getRemoteDevice(this.x).getName();
        if (name == null) {
            return "";
        }
        return name;
    }

    private void a(BluetoothDevice bluetoothDevice, int i) {
        b a = a(bluetoothDevice, c.BLE_DEVICE_DISCONNECTED, i);
        if (!e.contains(a)) {
            e.add(a);
        }
        dji.thirdparty.a.c.a().e(c.BLE_FIND_DEVICE);
    }

    public void a(String str, b bVar) {
        this.T = bVar;
        this.U.sendEmptyMessage(0);
        b(str);
    }

    public boolean b(String str) {
        e("start connect");
        if (t == null) {
            f("mothod : connectBluetoothAdapter null");
        }
        if (l.a(str)) {
            f("mothod : connectaddress empty");
        }
        d();
        v();
        g();
        final BluetoothDevice remoteDevice = t.getRemoteDevice(str);
        int connectionState = s.getConnectionState(remoteDevice, 7);
        if (connectionState == 0) {
            if (this.x != null && str.equals(this.x) && this.y != null) {
                Log.d(g, "Trying to use an existing mBluetoothGatt for connection.");
                if (this.y.connect()) {
                    d(str);
                    return true;
                }
                f("mothod : connect -> connect failed!");
                return false;
            } else if (remoteDevice == null) {
                f("mothod : connect -> device is null");
                return false;
            } else {
                this.y = remoteDevice.connectGatt(G, true, this.M);
                if (this.y == null) {
                    f("mothod : connect -> mBluetoothGatt == null");
                    return true;
                }
                d(str);
                return true;
            }
        } else if (connectionState == 1) {
            d(str);
            f("mothod : connect -> connectionState == BluetoothProfile.STATE_CONNECTING");
            return true;
        } else {
            if (this.y != null) {
                l();
            }
            d(str);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    this.b.y = remoteDevice.connectGatt(a.G, true, this.b.M);
                }
            }, 2000);
            f("mothod : connect -> connectionState == BluetoothProfile.STATE_CONNECTED");
            return true;
        }
    }

    public static b a(ArrayList<b> arrayList, boolean z) {
        b bVar = null;
        b bVar2 = new b();
        if (arrayList.size() == 1) {
            return (b) arrayList.get(0);
        }
        if (arrayList.size() <= 1) {
            return bVar2;
        }
        Iterator it = arrayList.iterator();
        b bVar3 = null;
        while (it.hasNext()) {
            bVar2 = (b) it.next();
            if (bVar2.d) {
                bVar3 = bVar2;
            }
            if (!bVar2.e) {
                bVar2 = bVar;
            }
            bVar = bVar2;
        }
        if (bVar != null) {
            return bVar3;
        }
        if (bVar3 != null) {
            return bVar;
        }
        if (!z) {
            Collections.sort(arrayList);
        }
        return (b) arrayList.get(0);
    }

    public void a(b bVar) {
        this.T = bVar;
        this.U.sendEmptyMessage(0);
        l();
    }

    public void l() {
        e("disconnect");
        if (t == null || this.y == null) {
            Log.w(g, "BluetoothAdapter not initialized");
            return;
        }
        this.y.disconnect();
        this.y.close();
        z();
    }

    private boolean y() {
        BluetoothGattService bluetoothGattService;
        if (this.y == null) {
            bluetoothGattService = null;
        } else {
            bluetoothGattService = this.y.getService(UUID.fromString(a));
        }
        this.v = bluetoothGattService;
        if (this.v != null) {
            BluetoothGattCharacteristic characteristic = this.v.getCharacteristic(UUID.fromString(this.c));
            if (characteristic != null) {
                this.y.setCharacteristicNotification(characteristic, true);
                BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString(this.d));
                if (descriptor != null) {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    if (this.y.writeDescriptor(descriptor)) {
                        a(this.y, 0, 2);
                        return true;
                    }
                    this.U.sendMessageDelayed(this.U.obtainMessage(4, 1, 0, descriptor), 50);
                    f("mothod : enableNotification -> writeResult false");
                } else {
                    f("mothod : enableNotification -> config == null");
                    l();
                }
            } else {
                f("mothod : enableNotification -> readData == null");
                l();
            }
        } else {
            f("mothod : enableNotification -> mGattService == null");
            l();
        }
        return false;
    }

    public boolean a(byte[] bArr) {
        if (this.w == null) {
            return false;
        }
        this.w.setValue(bArr);
        return this.y.writeCharacteristic(this.w);
    }

    public boolean b(byte[] bArr) {
        if (this.N == null) {
            return false;
        }
        g gVar = new g(this);
        gVar.a = bArr;
        gVar.b = System.currentTimeMillis();
        a(gVar);
        return true;
    }

    private void a(BluetoothGatt bluetoothGatt, int i, int i2) {
        A();
        dji.thirdparty.a.c.a().e(c.BLE_DEVICE_CONNECTED);
        if (this.x == null) {
            this.x = i();
            if (l.a(this.x)) {
                this.x = bluetoothGatt.getDevice().getAddress();
                G.getSharedPreferences(G.getPackageName(), 0).edit().putString(l, this.x).commit();
            }
        }
        if (this.U != null && this.U.hasMessages(1)) {
            this.U.sendEmptyMessage(2);
        }
        this.H.a(bluetoothGatt, i, i2);
    }

    private b a(BluetoothDevice bluetoothDevice, c cVar, int i) {
        b bVar = new b();
        bVar.a = bluetoothDevice.getAddress();
        bVar.b = bluetoothDevice.getName();
        bVar.f = cVar;
        bVar.c = i;
        bVar.e = bluetoothDevice.getBondState() == 12;
        return bVar;
    }

    public boolean m() {
        return this.C == 2;
    }

    private void d(String str) {
        this.x = str;
        this.C = 1;
        dji.thirdparty.a.c.a().e(c.BLE_DEVICE_CONNECTING);
        G.getSharedPreferences(G.getPackageName(), 0).edit().putString(l, this.x).commit();
    }

    private void z() {
        this.x = null;
        this.C = 0;
        p();
        e.clear();
        this.H.a(this.y, 3, 0);
        dji.thirdparty.a.c.a().e(c.BLE_DEVICE_DISCONNECTED);
        DJILogHelper.getInstance().LOGD(g, "STATE_DISCONNECTED", false, false);
    }

    public boolean n() {
        if (t == null || this.y == null) {
            return false;
        }
        return true;
    }

    public void o() {
        d();
        e.clear();
        p();
        this.m = false;
        this.n = false;
        e.clear();
        if (this.y != null) {
            this.y.close();
            this.y = null;
        }
        this.C = 0;
    }

    public void p() {
        g();
        d();
        v();
    }

    public boolean q() {
        if (this.y != null) {
            try {
                Method method = this.y.getClass().getMethod("refresh", new Class[0]);
                if (method != null) {
                    return ((Boolean) method.invoke(this.y, new Object[0])).booleanValue();
                }
            } catch (Exception e) {
                Log.e(g, "An exception occured while refreshing device");
            }
        } else {
            f("mothod : refreshDeviceCache -> mBluetoothGatt == null");
        }
        return false;
    }

    private void A() {
        this.N = new LinkedList();
        new Thread(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                while (true) {
                    this.a.B();
                    try {
                        Thread.sleep((long) this.a.f, 500000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void B() {
        this.O.lock();
        if (this.N == null) {
            this.O.unlock();
        } else if (this.N.size() == 0) {
            this.O.unlock();
        } else {
            g gVar = (g) this.N.peekFirst();
            this.S = b(gVar);
            if (this.S == 0) {
                this.N.removeFirst();
                this.O.unlock();
            } else {
                if (System.currentTimeMillis() - gVar.b > ((long) this.P)) {
                    this.N.clear();
                    f("remove form queue for timeout,cur queue size:" + this.N.size());
                }
                this.O.unlock();
            }
            if (this.N.size() > 500) {
                f("procQueue.size() too large(500)");
            }
        }
    }

    public boolean a(g gVar) {
        if (!n()) {
            return false;
        }
        this.O.lock();
        this.N.add(gVar);
        this.O.unlock();
        return true;
    }

    public int b(g gVar) {
        if (!n()) {
            return -2;
        }
        if (a(gVar.a)) {
            return 0;
        }
        return -1;
    }

    private void C() {
        if (System.currentTimeMillis() - this.Q > 1000) {
            e("send speed:" + (((float) this.R) / 1000.0f) + "kb/s");
            this.R = 0;
            this.Q = System.currentTimeMillis();
        }
    }

    private void e(String str) {
        DJILogHelper.getInstance().LOGD(g, "BLE DEBUG::" + str, h);
    }

    private void f(String str) {
        DJILogHelper.getInstance().LOGE(g, "BLE ERROR::" + str, h);
    }

    public int c(String str) {
        if (t == null || s == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        return s.getConnectionState(t.getRemoteDevice(str), 7);
    }

    public void a(boolean z) {
        if (VERSION.SDK_INT >= 21 && !z) {
        }
    }
}
