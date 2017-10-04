package dji.pilot2.mine.d;

import android.os.AsyncTask;
import android.widget.TextView;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileFilter;
import java.lang.ref.WeakReference;

public class a extends AsyncTask<Void, String, Boolean> {
    final String[] a;
    final WeakReference<TextView> b;
    final dji.pilot2.library.a c;
    FileFilter d = null;
    private Runnable e = null;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Boolean) obj);
    }

    protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
        a((String[]) objArr);
    }

    public a(String[] strArr, TextView textView, dji.pilot2.library.a aVar) {
        this.a = strArr;
        this.b = new WeakReference(textView);
        this.c = aVar;
    }

    public void a(FileFilter fileFilter) {
        this.d = fileFilter;
    }

    public void a(Runnable runnable) {
        this.e = runnable;
    }

    protected Boolean a(Void... voidArr) {
        String[] strArr = this.a;
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            try {
                File file = new File(strArr[i]);
                if (file.exists() && file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    for (File file2 : listFiles) {
                        if (this.d == null || this.d.accept(file2)) {
                            file2.delete();
                        }
                    }
                }
                if (this.e != null) {
                    this.e.run();
                }
                i++;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }
        return Boolean.valueOf(true);
    }

    protected void a(String... strArr) {
        super.onProgressUpdate(strArr);
    }

    protected void a(Boolean bool) {
        super.onPostExecute(bool);
        if (this.c != null) {
            c.a().e(this.c);
        }
        try {
            if (this.b.get() != null) {
                ((TextView) this.b.get()).setText("0M");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
