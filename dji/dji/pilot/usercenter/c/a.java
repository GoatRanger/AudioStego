package dji.pilot.usercenter.c;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;

public final class a {
    private static final String a = a.class.getSimpleName();
    private static final String b = (a + "_key_tab");
    private final Context c;
    private final FragmentManager d;
    private final int e;
    private final ArrayList<a> f = new ArrayList(5);
    private a g = null;

    static final class a {
        public Fragment a = null;
        public final String b;
        public final Bundle c;
        public final Class<?> d;

        a(String str, Class<?> cls, Bundle bundle) {
            this.b = str;
            this.d = cls;
            this.c = bundle;
        }
    }

    public a(Context context, FragmentManager fragmentManager, int i) {
        this.c = context;
        this.d = fragmentManager;
        this.e = i;
    }

    public void a(String str, Class<?> cls, Bundle bundle) {
        this.f.add(new a(str, cls, bundle));
    }

    public void a(String str) {
        if (str != null && str.length() > 0) {
            if (this.g == null || !str.equals(this.g.b)) {
                FragmentTransaction a = a(str, null);
                if (a != null) {
                    a.commit();
                }
            }
        }
    }

    public void a(Bundle bundle) {
        if (this.g != null && this.g.b != null) {
            bundle.putString(b, this.g.b);
        }
    }

    public void b(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString(b);
            if (string != null && string.length() > 0) {
                if (this.g == null || !string.equals(this.g.b)) {
                    FragmentTransaction fragmentTransaction = null;
                    for (int i = 0; i < this.f.size(); i++) {
                        a aVar = (a) this.f.get(i);
                        aVar.a = this.d.findFragmentByTag(aVar.b);
                        if (!(aVar.a == null || aVar.a.isDetached() || string.equals(aVar.b))) {
                            if (fragmentTransaction == null) {
                                fragmentTransaction = this.d.beginTransaction();
                            }
                            fragmentTransaction.detach(aVar.a);
                        }
                    }
                    FragmentTransaction a = a(string, fragmentTransaction);
                    if (a != null) {
                        a.commit();
                        this.d.executePendingTransactions();
                    }
                }
            }
        }
    }

    private FragmentTransaction a(String str, FragmentTransaction fragmentTransaction) {
        if (str != null && str.length() > 0) {
            a aVar = null;
            int i = 0;
            while (i < this.f.size()) {
                a aVar2 = (a) this.f.get(i);
                if (!str.equals(aVar2.b)) {
                    aVar2 = aVar;
                }
                i++;
                aVar = aVar2;
            }
            if (aVar != null) {
                if (fragmentTransaction == null) {
                    fragmentTransaction = this.d.beginTransaction();
                }
                if (!(this.g == null || this.g.a == null)) {
                    fragmentTransaction.detach(this.g.a);
                }
                if (aVar.a == null) {
                    aVar.a = Fragment.instantiate(this.c, aVar.d.getName(), aVar.c);
                    fragmentTransaction.add(this.e, aVar.a, aVar.b);
                } else {
                    fragmentTransaction.attach(aVar.a);
                }
                this.g = aVar;
            }
        }
        return fragmentTransaction;
    }
}
