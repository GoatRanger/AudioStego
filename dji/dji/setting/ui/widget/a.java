package dji.setting.ui.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import dji.pilot.setting.ui.R;

public class a extends AlertDialog {
    public static int a = -1126;
    private static final String b = "      ";
    private static final int c = (b.length() * 2);

    protected a(Context context) {
        super(context, R.style.setting_ui_dialog);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (a != -1126) {
            getWindow().setFlags(8, 8);
            getWindow().getDecorView().setSystemUiVisibility(a);
        }
    }

    public void show() {
        super.show();
        if (a != -1126) {
            getWindow().clearFlags(8);
        }
    }

    public static AlertDialog a(Context context) {
        return new a(context);
    }

    public static a a(Context context, int i) {
        a aVar = new a(context);
        aVar.setMessage(c(context, i));
        aVar.setButton(-1, context.getResources().getString(R.string.app_ok), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        aVar.show();
        return aVar;
    }

    public static void a(Context context, String str) {
        a aVar = new a(context);
        aVar.setMessage(a(str));
        aVar.setButton(-1, context.getResources().getString(R.string.app_ok), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        aVar.show();
    }

    public static Dialog a(Context context, int i, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Dialog aVar = new a(context);
        aVar.setMessage(c(context, i));
        aVar.setButton(-1, context.getResources().getString(R.string.app_ok), onClickListener);
        aVar.setButton(-2, context.getResources().getString(R.string.app_cancel), onClickListener2);
        aVar.show();
        return aVar;
    }

    public static Dialog a(Context context, String str, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Dialog aVar = new a(context);
        aVar.setMessage(a(str));
        aVar.setButton(-1, context.getResources().getString(R.string.app_ok), onClickListener);
        aVar.setButton(-2, context.getResources().getString(R.string.app_cancel), onClickListener2);
        aVar.show();
        return aVar;
    }

    public static Dialog a(Context context, int i, OnClickListener onClickListener) {
        Dialog aVar = new a(context);
        aVar.setMessage(c(context, i));
        aVar.setButton(-1, context.getResources().getString(R.string.app_ok), onClickListener);
        aVar.show();
        return aVar;
    }

    public static Dialog a(Context context, String str, OnClickListener onClickListener) {
        Dialog aVar = new a(context);
        aVar.setMessage(a(str));
        aVar.setButton(-1, context.getResources().getString(R.string.app_ok), onClickListener);
        aVar.show();
        return aVar;
    }

    public static Dialog b(Context context, int i, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Dialog aVar = new a(context);
        aVar.setMessage(c(context, i));
        aVar.setButton(-1, context.getResources().getString(R.string.app_cancel), onClickListener2);
        aVar.setButton(-2, context.getResources().getString(R.string.app_ok), onClickListener);
        aVar.show();
        return aVar;
    }

    public static Dialog b(Context context, int i, OnClickListener onClickListener) {
        return b(context, context.getResources().getString(i), onClickListener);
    }

    public static Dialog b(Context context, String str, OnClickListener onClickListener) {
        CharSequence a = a(str);
        Dialog aVar = new a(context);
        aVar.setMessage(a);
        aVar.setButton(-1, context.getResources().getString(R.string.app_ok), onClickListener);
        aVar.setButton(-2, context.getResources().getString(R.string.app_cancel), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        aVar.show();
        return aVar;
    }

    public static Dialog c(Context context, int i, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return b(context, context.getResources().getString(i), onClickListener, onClickListener2);
    }

    public static Dialog b(Context context, String str, OnClickListener onClickListener, OnClickListener onClickListener2) {
        CharSequence a = a(str);
        Dialog aVar = new a(context);
        aVar.setMessage(a);
        aVar.setButton(-1, context.getResources().getString(R.string.app_ok), onClickListener);
        aVar.setButton(-2, context.getResources().getString(R.string.app_cancel), onClickListener2);
        aVar.show();
        return aVar;
    }

    public static Dialog b(Context context, int i) {
        return b(context, context.getResources().getString(i));
    }

    public static Dialog b(Context context, String str) {
        CharSequence a = a(str);
        Dialog bVar = new b(context);
        bVar.setMessage(a);
        bVar.setCancelable(true);
        bVar.setCanceledOnTouchOutside(false);
        bVar.setButton(-2, context.getResources().getString(R.string.app_cancel), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        bVar.show();
        return bVar;
    }

    private static String c(Context context, int i) {
        return a(context.getResources().getString(i));
    }

    private static String a(String str) {
        int length = c - str.length();
        if (length <= 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b.substring(0, length / 2)).append(str).append(b.substring(0, length / 2));
        return stringBuilder.toString();
    }
}
