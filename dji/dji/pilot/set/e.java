package dji.pilot.set;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class e {
    public static void a(Context context, int i) {
        Builder builder = new Builder(context, R.style.SetDialog);
        builder.setMessage(i);
        builder.setPositiveButton(R.string.app_ok, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(R.string.app_cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public static void a(Context context, int i, OnClickListener onClickListener) {
        Builder builder = new Builder(context, R.style.SetDialog);
        builder.setMessage(i);
        builder.setPositiveButton(R.string.app_ok, onClickListener);
        builder.setNegativeButton(R.string.app_cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public static void a(Context context, String str, OnClickListener onClickListener) {
        Builder builder = new Builder(context, R.style.SetDialog);
        builder.setMessage(str);
        builder.setPositiveButton(R.string.app_ok, onClickListener);
        builder.setNegativeButton(R.string.app_cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public static void a(Context context, int i, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Builder builder = new Builder(context, R.style.SetDialog);
        builder.setMessage(i);
        builder.setPositiveButton(R.string.app_ok, onClickListener);
        builder.setNegativeButton(R.string.app_cancel, onClickListener2);
        builder.create().show();
    }

    public static void a(Context context, String str, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Builder builder = new Builder(context, R.style.SetDialog);
        builder.setMessage(str);
        builder.setPositiveButton(R.string.app_ok, onClickListener);
        builder.setNegativeButton(R.string.app_cancel, onClickListener2);
        builder.create().show();
    }

    public static void b(Context context, int i) {
        Builder builder = new Builder(context, R.style.SetDialog);
        builder.setMessage(i);
        builder.setNegativeButton(R.string.app_cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public static void c(Context context, int i) {
        Builder builder = new Builder(context, R.style.SetDialog);
        builder.setMessage(i);
        builder.setNegativeButton(R.string.app_ok, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public static void b(Context context, int i, OnClickListener onClickListener) {
        Builder builder = new Builder(context, R.style.SetDialog);
        builder.setMessage(i);
        builder.setNegativeButton(R.string.app_ok, onClickListener);
        builder.create().show();
    }

    public static void b(Context context, String str, OnClickListener onClickListener) {
        Builder builder = new Builder(context, R.style.SetDialog);
        builder.setMessage(str);
        builder.setNegativeButton(R.string.app_ok, onClickListener);
        builder.create().show();
    }

    public static void a() {
    }
}
