package dji.pilot2.usercenter.activity;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.dji.frame.c.d;
import dji.gs.d.c;
import dji.pilot.usercenter.f.e;
import dji.pilot2.main.a.a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class DJIFlightRecordPlayerActivity$4 implements c {
    final /* synthetic */ DJIFlightRecordPlayerActivity a;

    DJIFlightRecordPlayerActivity$4(DJIFlightRecordPlayerActivity dJIFlightRecordPlayerActivity) {
        this.a = dJIFlightRecordPlayerActivity;
    }

    public void a(Bitmap bitmap) {
        if (bitmap != null) {
            DJIFlightRecordPlayerActivity.g(this.a).setEnabled(true);
            File file = new File(d.a(this.a.getBaseContext(), "Tmp/"));
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                File createTempFile = File.createTempFile("fs_" + System.currentTimeMillis(), a.n, file);
                createTempFile.deleteOnExit();
                OutputStream fileOutputStream = new FileOutputStream(createTempFile);
                bitmap.compress(CompressFormat.JPEG, 80, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                this.a.startActivity(e.a(this.a.getBaseContext(), createTempFile.getAbsolutePath(), null));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
