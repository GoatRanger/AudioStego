package dji.midware.data.forbid;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import dji.thirdparty.afinal.b.b;

class DJIFlyForbidController$1 implements b {
    final /* synthetic */ DJIFlyForbidController this$0;

    DJIFlyForbidController$1(DJIFlyForbidController dJIFlyForbidController) {
        this.this$0 = dJIFlyForbidController;
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 4 && i2 == 5) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS dji_pilot_flyunlimit_jsonbean_UnlockListItem");
            } catch (Exception e) {
                Log.e(getClass().getName(), e.toString());
            }
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
