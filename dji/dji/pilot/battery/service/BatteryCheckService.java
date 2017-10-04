package dji.pilot.battery.service;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.dji.frame.c.d;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.pilot.R;
import dji.pilot.battery.model.BanSN;
import dji.pilot.battery.model.BanSnMd5;
import dji.pilot.battery.model.InvalidBatteryEvent;
import dji.pilot.publics.objects.g;
import dji.pilot2.utils.k;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BatteryCheckService extends Service {
    public static final String a = "BatterCheckService";
    public static final String b = "battery_sn";
    public static final String c = "battery_sn_md5";
    public static final String d = "service_type";
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final String i = ".BatteryData";
    public static final String j = "ban_sn_preference_key";
    public static final String k = "ban_sn_md5_preference_key";
    a l;
    HandlerThread m;
    BanSN n;
    BanSnMd5 o;
    List<dji.pilot.battery.model.a> p;
    String q;
    String r;

    private final class a extends Handler {
        final /* synthetic */ BatteryCheckService a;

        public a(BatteryCheckService batteryCheckService, Looper looper) {
            this.a = batteryCheckService;
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    Intent intent = (Intent) message.obj;
                    String stringExtra = intent.getStringExtra(BatteryCheckService.b);
                    String stringExtra2 = intent.getStringExtra(BatteryCheckService.c);
                    Object obj = null;
                    if (!(this.a.n == null || this.a.n.invalid_battery_sn_list == null)) {
                        for (String str : this.a.n.invalid_battery_sn_list) {
                            if (stringExtra != null && stringExtra.equals(str)) {
                                obj = 1;
                            }
                        }
                    }
                    if (!(this.a.o == null || this.a.o.invalid_battery_md5_list == null)) {
                        for (String str2 : this.a.o.invalid_battery_md5_list) {
                            if (stringExtra2 != null && stringExtra2.equals(str2)) {
                                obj = 1;
                            }
                        }
                    }
                    if (obj != null) {
                        DJILogHelper.getInstance().LOGI("BatteryCheck", "invalid battery");
                        c.a().e(InvalidBatteryEvent.INVALID);
                        break;
                    }
                    break;
                case 2:
                    com.dji.frame.c.c.b(this.a).a(k.D(), new 3(this));
                    com.dji.frame.c.c.b(this.a).a(k.E(), new 4(this));
                    break;
                case 3:
                    DJILogHelper.getInstance().LOGI("BatteryCheck", "send auto landing command");
                    Builder builder = new Builder(this.a);
                    builder.setMessage(R.string.v2_invalid_battery).setPositiveButton(17039379, new 1(this));
                    AlertDialog create = builder.create();
                    create.getWindow().setType(2003);
                    create.show();
                    DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.AUTO_LANDING).start(new 2(this));
                    break;
            }
            this.a.stopSelf(message.arg1);
        }
    }

    public void onCreate() {
        super.onCreate();
        this.q = g.b((Context) this, j, "");
        this.n = (BanSN) h.b(this.q, BanSN.class);
        this.r = g.b((Context) this, k, "");
        this.o = (BanSnMd5) h.b(this.r, BanSnMd5.class);
        this.m = new HandlerThread("BatteryCheckThread", 10);
        this.m.start();
        this.l = new a(this, this.m.getLooper());
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            Message obtainMessage = this.l.obtainMessage();
            obtainMessage.arg1 = i2;
            obtainMessage.what = intent.getIntExtra("service_type", 0);
            obtainMessage.obj = intent;
            this.l.sendMessage(obtainMessage);
        }
        return 2;
    }

    private void a() {
        ObjectInputStream objectInputStream;
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        StreamCorruptedException e3;
        ClassNotFoundException e4;
        File file = new File(d.a(this, i));
        if (file.exists()) {
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                try {
                    this.p = Arrays.asList((dji.pilot.battery.model.a[]) objectInputStream.readObject());
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e6) {
                    e2 = e6;
                    try {
                        e2.printStackTrace();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e52) {
                                e52.printStackTrace();
                            }
                        }
                        if (this.p == null) {
                            this.p = new ArrayList();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (StreamCorruptedException e8) {
                    e3 = e8;
                    e3.printStackTrace();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e522) {
                            e522.printStackTrace();
                        }
                    }
                    if (this.p == null) {
                        this.p = new ArrayList();
                    }
                } catch (IOException e9) {
                    e522 = e9;
                    e522.printStackTrace();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e5222) {
                            e5222.printStackTrace();
                        }
                    }
                    if (this.p == null) {
                        this.p = new ArrayList();
                    }
                } catch (ClassNotFoundException e10) {
                    e4 = e10;
                    e4.printStackTrace();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e52222) {
                            e52222.printStackTrace();
                        }
                    }
                    if (this.p == null) {
                        this.p = new ArrayList();
                    }
                }
            } catch (FileNotFoundException e11) {
                e2 = e11;
                objectInputStream = null;
                e2.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (this.p == null) {
                    this.p = new ArrayList();
                }
            } catch (StreamCorruptedException e12) {
                e3 = e12;
                objectInputStream = null;
                e3.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (this.p == null) {
                    this.p = new ArrayList();
                }
            } catch (IOException e13) {
                e52222 = e13;
                objectInputStream = null;
                e52222.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (this.p == null) {
                    this.p = new ArrayList();
                }
            } catch (ClassNotFoundException e14) {
                e4 = e14;
                objectInputStream = null;
                e4.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (this.p == null) {
                    this.p = new ArrayList();
                }
            } catch (Throwable th3) {
                th = th3;
                objectInputStream = null;
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
        }
        if (this.p == null) {
            this.p = new ArrayList();
        }
    }

    private void b() {
        ObjectOutputStream objectOutputStream;
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        StreamCorruptedException e3;
        File file = new File(d.a(this, i));
        if (file.exists()) {
            file.delete();
        }
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(d.a(this, i))));
            try {
                objectOutputStream.writeObject(this.p.toArray());
                objectOutputStream.flush();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e5) {
                e2 = e5;
                try {
                    e2.printStackTrace();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (StreamCorruptedException e7) {
                e3 = e7;
                e3.printStackTrace();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e422) {
                        e422.printStackTrace();
                    }
                }
            } catch (IOException e8) {
                e422 = e8;
                e422.printStackTrace();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e4222) {
                        e4222.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e9) {
            e2 = e9;
            objectOutputStream = null;
            e2.printStackTrace();
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        } catch (StreamCorruptedException e10) {
            e3 = e10;
            objectOutputStream = null;
            e3.printStackTrace();
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        } catch (IOException e11) {
            e4222 = e11;
            objectOutputStream = null;
            e4222.printStackTrace();
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            throw th;
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
