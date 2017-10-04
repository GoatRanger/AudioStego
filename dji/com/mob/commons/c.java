package com.mob.commons;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Base64;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.MobRSA;
import com.mob.tools.utils.R;
import com.mob.tools.utils.SQLiteHelper;
import com.mob.tools.utils.SQLiteHelper.SingleTableDB;
import dji.pilot.usercenter.mode.n;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

public class c implements Callback {
    private static c a;
    private Context b;
    private Handler c;
    private SingleTableDB d;
    private Hashon e = new Hashon();
    private DeviceHelper f;
    private Random g;

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c(context);
            }
            cVar = a;
        }
        return cVar;
    }

    private c(Context context) {
        this.b = context.getApplicationContext();
        this.f = DeviceHelper.getInstance(context);
        this.g = new Random();
        MobHandlerThread mobHandlerThread = new MobHandlerThread();
        mobHandlerThread.start();
        this.c = new Handler(mobHandlerThread.getLooper(), this);
        File file = new File(R.getCacheRoot(context), "comm/dbs/.dh");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        this.d = SQLiteHelper.getDatabase(file.getAbsolutePath(), "DataHeap_1");
        this.d.addField(n.ax, "text", true);
        this.d.addField("data", "text", true);
        this.c.sendEmptyMessage(1);
    }

    public synchronized void a(long j, HashMap<String, Object> hashMap) {
        Message message = new Message();
        message.what = 2;
        message.obj = new Object[]{Long.valueOf(j), hashMap};
        this.c.sendMessage(message);
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                a();
                this.c.sendEmptyMessageDelayed(1, 10000);
                break;
            case 2:
                Object[] objArr = (Object[]) message.obj;
                long longValue = ((Long) R.forceCast(objArr[0], Long.valueOf(-1))).longValue();
                if (longValue > 0) {
                    b(longValue, (HashMap) objArr[1]);
                    break;
                }
                break;
        }
        return false;
    }

    private void b(final long j, final HashMap<String, Object> hashMap) {
        e.a(new File(R.getCacheRoot(this.b), "comm/locks/.dhlock"), true, new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(n.ax, String.valueOf(j));
                    contentValues.put("data", Base64.encodeToString(Data.AES128Encode(Data.rawMD5(this.c.f.getManufacturer()), this.c.e.fromHashMap(hashMap).getBytes("utf-8")), 2));
                    SQLiteHelper.insert(this.c.d, contentValues);
                } catch (Throwable th) {
                    MobLog.getInstance().w(th);
                }
            }
        });
    }

    private void a() {
        String networkType = this.f.getNetworkType();
        if (networkType != null && !"none".equals(networkType)) {
            e.a(new File(R.getCacheRoot(this.b), "comm/locks/.dhlock"), true, new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    ArrayList d = this.a.b();
                    if (d.size() > 0 && this.a.a(d)) {
                        this.a.b(d);
                    }
                }
            });
        }
    }

    private ArrayList<String[]> b() {
        ArrayList<String[]> arrayList = new ArrayList();
        try {
            Cursor query = SQLiteHelper.query(this.d, new String[]{n.ax, "data"}, null, null, null);
            if (query != null) {
                if (query.moveToFirst()) {
                    long a = a.a(this.b);
                    do {
                        Object obj = new String[]{query.getString(0), query.getString(1)};
                        long j = -1;
                        try {
                            j = Long.parseLong(obj[0]);
                        } catch (Throwable th) {
                        }
                        if (j <= a) {
                            arrayList.add(obj);
                        }
                    } while (query.moveToNext());
                }
                query.close();
            }
        } catch (Throwable th2) {
            MobLog.getInstance().w(th2);
        }
        return arrayList;
    }

    private boolean a(ArrayList<String[]> arrayList) {
        try {
            b a = b.a(this.b);
            ArrayList a2 = a.a();
            if (a2.isEmpty()) {
                return false;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("plat", Integer.valueOf(this.f.getPlatformCode()));
            hashMap.put("device", this.f.getDeviceKey());
            hashMap.put("mac", this.f.getMacAddress());
            hashMap.put(n.E, this.f.getModel());
            hashMap.put("duid", DeviceAuthorizer.authorize(this.b, null));
            hashMap.put("imei", this.f.getIMEI());
            hashMap.put("serialno", this.f.getSerialno());
            hashMap.put("networktype", this.f.getDetailNetworkTypeForStatic());
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(this.e.fromJson(new String(Data.AES128Decode(Data.rawMD5(this.f.getManufacturer()), Base64.decode(((String[]) it.next())[1], 2)), "utf-8").trim()));
            }
            hashMap.put("datas", arrayList2);
            arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("appkey", ((MobProduct) a2.get(0)).getProductAppkey()));
            arrayList2.add(new KVPair("m", a(this.e.fromHashMap(hashMap))));
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new KVPair("User-Identity", a.a(a2)));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            return "200".equals(String.valueOf(this.e.fromJson(a.httpPost("http://c.data.mob.com/v2/cdata", arrayList2, null, arrayList3, networkTimeOut)).get("status")));
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return false;
        }
    }

    private String a(String str) throws Throwable {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeLong(this.g.nextLong());
        dataOutputStream.writeLong(this.g.nextLong());
        dataOutputStream.flush();
        dataOutputStream.close();
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream2);
        gZIPOutputStream.write(str.getBytes("utf-8"));
        gZIPOutputStream.flush();
        gZIPOutputStream.close();
        byte[] AES128Encode = Data.AES128Encode(toByteArray, byteArrayOutputStream2.toByteArray());
        toByteArray = new MobRSA(1024).encode(toByteArray, new BigInteger("ceeef5035212dfe7c6a0acdc0ef35ce5b118aab916477037d7381f85c6b6176fcf57b1d1c3296af0bb1c483fe5e1eb0ce9eb2953b44e494ca60777a1b033cc07", 16), new BigInteger("191737288d17e660c4b61440d5d14228a0bf9854499f9d68d8274db55d6d954489371ecf314f26bec236e58fac7fffa9b27bcf923e1229c4080d49f7758739e5bd6014383ed2a75ce1be9b0ab22f283c5c5e11216c5658ba444212b6270d629f2d615b8dfdec8545fb7d4f935b0cc10b6948ab4fc1cb1dd496a8f94b51e888dd", 16));
        OutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream3);
        dataOutputStream2.writeInt(toByteArray.length);
        dataOutputStream2.write(toByteArray);
        dataOutputStream2.writeInt(AES128Encode.length);
        dataOutputStream2.write(AES128Encode);
        dataOutputStream2.flush();
        dataOutputStream2.close();
        return Base64.encodeToString(byteArrayOutputStream3.toByteArray(), 2);
    }

    private void b(ArrayList<String[]> arrayList) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String[] strArr = (String[]) it.next();
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append('\'').append(strArr[0]).append('\'');
            }
            SQLiteHelper.delete(this.d, "time in (" + stringBuilder.toString() + ")", null);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }
}
