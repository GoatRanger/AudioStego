package dji.setting.ui.flyc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataFlycSetPushParams;
import dji.midware.e.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class c {
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;
    private HashMap<Integer, HashMap<String, Integer>> a = new HashMap();
    private HashSet<String> b = new HashSet();
    private Handler f = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ c a;

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.c((String[]) message.obj, message.arg1);
                    return;
                case 1:
                    this.a.d((String[]) message.obj, message.arg1);
                    return;
                case 2:
                    this.a.c();
                    return;
                default:
                    return;
            }
        }
    };

    private static class a {
        private static c a = new c();

        private a() {
        }
    }

    public static c a() {
        return a.a;
    }

    public void a(String[] strArr, int i) {
        Message obtainMessage = this.f.obtainMessage(0);
        obtainMessage.obj = strArr;
        obtainMessage.arg1 = i;
        this.f.sendMessage(obtainMessage);
    }

    public void b(String[] strArr, int i) {
        Message obtainMessage = this.f.obtainMessage(1);
        obtainMessage.obj = strArr;
        obtainMessage.arg1 = i;
        this.f.sendMessage(obtainMessage);
    }

    private void c(String[] strArr, int i) {
        if (!e(strArr, i)) {
            if (!this.a.containsKey(Integer.valueOf(i))) {
                this.a.put(Integer.valueOf(i), new HashMap());
            }
            HashMap hashMap = (HashMap) this.a.get(Integer.valueOf(i));
            for (Object obj : strArr) {
                if (hashMap.containsKey(obj)) {
                    hashMap.put(obj, Integer.valueOf(((Integer) hashMap.get(obj)).intValue() + 1));
                } else {
                    hashMap.put(obj, Integer.valueOf(1));
                }
            }
            if (this.f.hasMessages(2)) {
                this.f.removeMessages(2);
            }
            this.f.sendMessageDelayed(this.f.obtainMessage(2), 200);
        }
    }

    private void d(String[] strArr, int i) {
        if (this.a.containsKey(Integer.valueOf(i))) {
            HashMap hashMap = (HashMap) this.a.get(Integer.valueOf(i));
            for (Object obj : strArr) {
                if (hashMap.containsKey(obj)) {
                    int intValue = ((Integer) hashMap.get(obj)).intValue() - 1;
                    if (intValue == 0) {
                        hashMap.remove(obj);
                    } else {
                        hashMap.put(obj, Integer.valueOf(intValue));
                    }
                }
            }
            if (hashMap.size() == 0) {
                this.a.remove(Integer.valueOf(i));
            }
        }
        if (this.f.hasMessages(2)) {
            this.f.removeMessages(2);
        }
        this.f.sendMessageDelayed(this.f.obtainMessage(2), 200);
    }

    private boolean e(String[] strArr, int i) {
        if (!this.a.containsKey(Integer.valueOf(i))) {
            boolean z;
            if (strArr.length + b().length > 40) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } else if (this.a.size() >= 4) {
            return true;
        } else {
            HashMap hashMap = (HashMap) this.a.get(Integer.valueOf(i));
            int length = b().length;
            for (Object containsKey : strArr) {
                if (!hashMap.containsKey(containsKey)) {
                    length++;
                }
            }
            if (length <= 40) {
                return false;
            }
            return true;
        }
    }

    private String[] b() {
        ArrayList arrayList = new ArrayList();
        for (Integer num : this.a.keySet()) {
            for (String add : ((HashMap) this.a.get(num)).keySet()) {
                arrayList.add(add);
            }
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }

    private String[] a(int i) {
        Set keySet = ((HashMap) this.a.get(Integer.valueOf(i))).keySet();
        String[] strArr = new String[keySet.size()];
        keySet.toArray(strArr);
        return strArr;
    }

    private void c() {
        int i = 0;
        int i2 = 0;
        for (Integer num : this.a.keySet()) {
            String[] a = a(num.intValue());
            a(i2, num.intValue(), i, a);
            i2++;
            i = a.length + i;
        }
        while (i2 < 4) {
            a(i2, 0, i, null);
            i2++;
        }
    }

    private void a(int i, int i2, int i3, String[] strArr) {
        DataFlycSetPushParams.getInstance().a(i).b(i2).c(i3).a(strArr).start(new d(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("", "start push group 0 success ", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "start push group 0 fail " + aVar, false, true);
            }
        });
    }
}
