package dji.pilot2.mine.d;

import android.os.AsyncTask;
import android.widget.TextView;
import java.io.File;
import java.io.FileFilter;
import java.lang.ref.WeakReference;

public class b extends AsyncTask<Void, Long, Long> {
    final String[] a;
    final WeakReference<TextView> b;
    FileFilter c = null;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Long) obj);
    }

    protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
        a((Long[]) objArr);
    }

    public b(String[] strArr, TextView textView) {
        this.a = strArr;
        this.b = new WeakReference(textView);
    }

    public void a(FileFilter fileFilter) {
        this.c = fileFilter;
    }

    protected Long a(Void... voidArr) {
        String[] strArr = this.a;
        int length = strArr.length;
        int i = 0;
        long j = 0;
        while (i < length) {
            try {
                File file = new File(strArr[i]);
                if (file.exists() && file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    for (File file2 : listFiles) {
                        if (this.c == null || this.c.accept(file2)) {
                            j += file2.length();
                            publishProgress(new Long[]{Long.valueOf(j)});
                        }
                    }
                }
                i++;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return Long.valueOf(0);
            }
        }
        return Long.valueOf(j);
    }

    protected void a(Long... lArr) {
        super.onProgressUpdate(lArr);
        try {
            if (lArr[0].longValue() != 0) {
                ((TextView) this.b.get()).setText(String.format("%.2fM", new Object[]{Double.valueOf(((double) lArr[0].longValue()) / 1048576.0d)}));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    protected void a(Long l) {
        super.onPostExecute(l);
        try {
            if (l.longValue() != 0) {
                ((TextView) this.b.get()).setText(String.format("%.2fM", new Object[]{Double.valueOf(((double) l.longValue()) / 1048576.0d)}));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
