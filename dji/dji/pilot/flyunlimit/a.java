package dji.pilot.flyunlimit;

import android.content.Context;
import dji.pilot.R;

public class a {
    public static final int A = 405;
    public static final int B = 406;
    public static final int C = 407;
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = 10;
    public static final int l = 11;
    public static final int m = 12;
    public static final int n = 13;
    public static final int o = 14;
    public static final int p = 15;
    public static final int q = 16;
    public static final int r = 17;
    public static final int s = 18;
    public static final int t = 19;
    public static final int u = 441;
    public static final int v = 401;
    public static final int w = 410;
    public static final int x = 400;
    public static final int y = 402;
    public static final int z = 404;

    public static String a(Context context, int i) {
        switch (i) {
            case 1:
                return "网络错误";
            case 2:
                return "DJI账号验证失败";
            case 3:
                return "无法获取位置信息";
            case 4:
                return "获取位置信息为空";
            case 5:
                return "验证失败";
            case 16:
                return "该区域不支持一键解禁";
            case 17:
                return "不能解禁";
            case 18:
                return "有些区域不能解禁";
            default:
                return "";
        }
    }

    public static String b(Context context, int i) {
        switch (i) {
            case 400:
                return context.getString(R.string.nfz_server_error_signature);
            case 401:
            case w /*410*/:
            case u /*441*/:
                return context.getString(R.string.nfz_server_error_format);
            case y /*402*/:
                return context.getString(R.string.nfz_server_error_too_frequent);
            case 404:
                return context.getString(R.string.nfz_server_error_sn_empty);
            case A /*405*/:
                return context.getString(R.string.nfz_server_error_user_not_verify);
            case B /*406*/:
                return context.getString(R.string.nfz_server_error_too_more_unlimit);
            case C /*407*/:
                return context.getString(R.string.nfz_server_error_not_allow_unlimit);
            default:
                return context.getString(R.string.nfz_server_error_system_busy);
        }
    }
}
