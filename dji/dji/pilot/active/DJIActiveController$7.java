package dji.pilot.active;

import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot2.usercenter.activate.c;
import dji.thirdparty.afinal.b;
import dji.thirdparty.afinal.f.a;

class DJIActiveController$7 extends a<String> {
    final /* synthetic */ DJIActiveController$DJIActiveLocalModel a;
    final /* synthetic */ b b;

    DJIActiveController$7(DJIActiveController$DJIActiveLocalModel dJIActiveController$DJIActiveLocalModel, b bVar) {
        this.a = dJIActiveController$DJIActiveLocalModel;
        this.b = bVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        String str2;
        DJIActiveSnModel dJIActiveSnModel = (DJIActiveSnModel) h.b(str, DJIActiveSnModel.class);
        String str3 = "";
        switch (dJIActiveSnModel.status) {
            case 1000:
                str2 = "signature认证失败";
                break;
            case 1001:
                str2 = "请求超时";
                break;
            case 1002:
                str2 = "参数不全或不合法";
                break;
            default:
                str2 = dJIActiveSnModel.status + "";
                DJILogHelper.getInstance().LOGD("DJIActiveController", "上传激活记录(" + this.a.id + ") 成功", false, true);
                this.a.isUploaded = true;
                this.a.isSuccess = true;
                this.a.description = str2;
                this.a.updatetime = System.currentTimeMillis();
                this.b.e(this.a);
                return;
        }
        c.a("激活失败 ：" + str2);
        this.a.isUploaded = true;
        this.a.isSuccess = false;
        this.a.description = str2;
        this.a.updatetime = System.currentTimeMillis();
        this.b.e(this.a);
    }

    public void a(Throwable th, int i, String str) {
        c.a("激活失败 ：errorNo=" + i);
        this.a.isUploaded = true;
        this.a.isSuccess = false;
        this.a.description = str;
        this.a.updatetime = System.currentTimeMillis();
        this.b.e(this.a);
    }
}
