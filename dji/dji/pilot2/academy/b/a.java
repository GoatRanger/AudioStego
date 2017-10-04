package dji.pilot2.academy.b;

import android.content.Context;
import com.dji.frame.c.c;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot.usercenter.protocol.e$b;
import dji.pilot2.academy.model.AcademyDocInfo;
import dji.pilot2.academy.model.AcademyDocInfo.DocInfo;
import dji.pilot2.academy.model.AcademyHotFaqInfo;
import dji.pilot2.academy.model.AcademyVideoInfo;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class a {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;

    public static void a(Context context, final String str, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a, final int i) {
        c.b(context).a(str, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
                DJILogHelper.getInstance().LOGI("bob", "NewAcademyProtocolBox getCollegeInfo onStart CMDID_GET_NEW_COLLEGE_VIDEO = 1");
                dji_pilot_usercenter_protocol_e_a.a(i, z, 0, e$b.a(0, 0, obj, null));
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object obj = null;
                DJILogHelper.getInstance().LOGI("bob", "NewAcademyProtocolBox getCollegeInfo onSuccess CMDID_GET_NEW_COLLEGE_VIDEO = 1");
                if (i == 1) {
                    obj = new AcademyVideoInfo();
                    obj.mVideoInfos = AcademyVideoInfo.parsel(str);
                } else if (i == 2) {
                    obj = new AcademyDocInfo();
                    obj.mDocInfos = AcademyDocInfo.parsel(str);
                    if (obj.mDocInfos != null) {
                        for (int i = 0; i < obj.mDocInfos.size(); i++) {
                            ((DocInfo) obj.mDocInfos.get(i)).listUrl = str;
                        }
                    }
                } else if (i == 4) {
                    obj = new AcademyHotFaqInfo();
                    obj.mHotFaqInfos = AcademyHotFaqInfo.parsel(str);
                }
                dji_pilot_usercenter_protocol_e_a.a(i, 0, 0, e$b.a(0, 0, str, obj), obj);
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGI("bob", "NewAcademyProtocolBox getCollegeInfo onFailure CMDID_GET_NEW_COLLEGE_VIDEO = 1");
                dji_pilot_usercenter_protocol_e_a.a(i, i, 0, e$b.a(0, 0, str, null));
            }
        });
    }

    public static long a(String str) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(str, new ParsePosition(0)).getTime();
    }
}
