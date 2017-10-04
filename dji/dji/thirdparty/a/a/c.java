package dji.thirdparty.a.a;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;

public abstract class c<T> {
    protected final b a;

    @TargetApi(11)
    public static class a extends c<Fragment> {
        protected /* synthetic */ Object a(h hVar, Bundle bundle) {
            return d(hVar, bundle);
        }

        public a(b bVar) {
            super(bVar);
        }

        protected Fragment d(h hVar, Bundle bundle) {
            Fragment aVar = new dji.thirdparty.a.a.d.a();
            aVar.setArguments(bundle);
            return aVar;
        }
    }

    public static class b extends c<Fragment> {
        protected /* synthetic */ Object a(h hVar, Bundle bundle) {
            return d(hVar, bundle);
        }

        public b(b bVar) {
            super(bVar);
        }

        protected Fragment d(h hVar, Bundle bundle) {
            Fragment bVar = new dji.thirdparty.a.a.d.b();
            bVar.setArguments(bundle);
            return bVar;
        }
    }

    protected abstract T a(h hVar, Bundle bundle);

    protected c(b bVar) {
        this.a = bVar;
    }

    protected T a(h hVar, boolean z, Bundle bundle) {
        if (hVar.c()) {
            return null;
        }
        Bundle bundle2;
        if (bundle != null) {
            bundle2 = (Bundle) bundle.clone();
        } else {
            bundle2 = new Bundle();
        }
        if (!bundle2.containsKey(e.d)) {
            bundle2.putString(e.d, b(hVar, bundle2));
        }
        if (!bundle2.containsKey(e.e)) {
            bundle2.putString(e.e, c(hVar, bundle2));
        }
        if (!bundle2.containsKey(e.f)) {
            bundle2.putBoolean(e.f, z);
        }
        if (!(bundle2.containsKey(e.h) || this.a.i == null)) {
            bundle2.putSerializable(e.h, this.a.i);
        }
        if (!(bundle2.containsKey(e.g) || this.a.h == 0)) {
            bundle2.putInt(e.g, this.a.h);
        }
        return a(hVar, bundle2);
    }

    protected String b(h hVar, Bundle bundle) {
        return this.a.a.getString(this.a.b);
    }

    protected String c(h hVar, Bundle bundle) {
        return this.a.a.getString(this.a.a(hVar.a));
    }
}
