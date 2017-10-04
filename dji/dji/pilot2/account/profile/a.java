package dji.pilot2.account.profile;

import android.graphics.Bitmap;
import android.net.Uri;

public interface a {

    public interface b extends dji.pilot2.coupon.b {
        void a(Bitmap bitmap);

        void a(boolean z, String str);

        void a(boolean z, String str, String str2);

        void a(boolean z, String str, String str2, int i, String str3, String str4);
    }

    public interface a extends dji.pilot2.coupon.a {
        void a();

        void a(int i, int i2);

        void a(Uri uri, int i, int i2);

        void a(String str);

        void a(String str, int i, int i2);

        void a(String str, int i, String str2, String str3);

        void b();

        void c();
    }
}
