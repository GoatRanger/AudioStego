package dji.pilot2.bigfilm;

import android.content.Context;
import dji.log.DJILogHelper;
import dji.pilot2.utils.n;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class a {
    private static a b = new a();
    protected List<BigFilmBean> a;

    public static a getInstance() {
        return b;
    }

    protected a() {
    }

    public List<BigFilmBean> a(Context context) {
        if (this.a == null) {
            this.a = new ArrayList();
            for (File file : new File(n.a(context, n.q)).listFiles(new FilenameFilter(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public boolean accept(File file, String str) {
                    return str.endsWith(".cfg");
                }
            })) {
                try {
                    this.a.add(new BigFilmBean(context, new FileInputStream(file)));
                } catch (FileNotFoundException e) {
                    if (file.exists()) {
                        file.delete();
                    }
                } catch (Exception e2) {
                    DJILogHelper.getInstance().LOGD("bob", "bob exception");
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        }
        return this.a;
    }

    protected void a(List<BigFilmBean> list) {
        Collections.sort(list, new Comparator<BigFilmBean>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((BigFilmBean) obj, (BigFilmBean) obj2);
            }

            public int a(BigFilmBean bigFilmBean, BigFilmBean bigFilmBean2) {
                return -1;
            }
        });
    }
}
