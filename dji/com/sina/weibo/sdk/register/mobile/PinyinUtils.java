package com.sina.weibo.sdk.register.mobile;

import android.content.Context;
import android.support.v4.media.TransportMediator;
import android.text.TextUtils;
import com.facebook.internal.l;
import com.google.api.client.http.HttpStatusCodes;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.here.posclient.analytics.TrackerEvent;
import com.loopj.android.http.BuildConfig;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.flyunlimit.a;
import dji.pilot.publics.control.rc.b;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.b.a.b.q;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PinyinUtils {
    private static final int DISTINGUISH_LEN = 10;
    private static final char FIRST_CHINA = '一';
    private static final char LAST_CHINA = '龥';
    private static final String[] PINYIN;
    private static final char SPECIAL_HANZI = '〇';
    private static final String SPECIAL_HANZI_PINYIN = "LING";
    private static volatile boolean isLoad = false;
    private static PinyinUtils sInstance;
    private static short[] sPinyinIndex;

    public static class MatchedResult {
        public int end = -1;
        public int start = -1;
    }

    static {
        String[] strArr = new String[a.C];
        strArr[0] = "a";
        strArr[1] = "ai";
        strArr[2] = com.alipay.sdk.h.a.i;
        strArr[3] = "ang";
        strArr[4] = "ao";
        strArr[5] = "ba";
        strArr[6] = "bai";
        strArr[7] = "ban";
        strArr[8] = "bang";
        strArr[9] = "bao";
        strArr[10] = "bei";
        strArr[11] = "ben";
        strArr[12] = "beng";
        strArr[13] = "bi";
        strArr[14] = "bian";
        strArr[15] = "biao";
        strArr[16] = "bie";
        strArr[17] = "bin";
        strArr[18] = "bing";
        strArr[19] = "bo";
        strArr[20] = "bu";
        strArr[21] = "ca";
        strArr[22] = "cai";
        strArr[23] = "can";
        strArr[24] = "cang";
        strArr[25] = "cao";
        strArr[26] = "ce";
        strArr[27] = "cen";
        strArr[28] = "ceng";
        strArr[29] = "cha";
        strArr[30] = "chai";
        strArr[31] = "chan";
        strArr[32] = "chang";
        strArr[33] = "chao";
        strArr[34] = "che";
        strArr[35] = "chen";
        strArr[36] = "cheng";
        strArr[37] = "chi";
        strArr[38] = "chong";
        strArr[39] = "chou";
        strArr[40] = "chu";
        strArr[41] = "chuai";
        strArr[42] = "chuan";
        strArr[43] = "chuang";
        strArr[44] = "chui";
        strArr[45] = "chun";
        strArr[46] = "chuo";
        strArr[47] = "ci";
        strArr[48] = "cong";
        strArr[49] = "cou";
        strArr[50] = "cu";
        strArr[51] = "cuan";
        strArr[52] = "cui";
        strArr[53] = "cun";
        strArr[54] = "cuo";
        strArr[55] = "da";
        strArr[56] = "dai";
        strArr[57] = "dan";
        strArr[58] = "dang";
        strArr[59] = "dao";
        strArr[60] = "de";
        strArr[61] = "deng";
        strArr[62] = "di";
        strArr[63] = "dia";
        strArr[64] = "dian";
        strArr[65] = "diao";
        strArr[66] = "die";
        strArr[67] = "ding";
        strArr[68] = "diu";
        strArr[69] = "dong";
        strArr[70] = "dou";
        strArr[71] = "du";
        strArr[72] = "duan";
        strArr[73] = "dui";
        strArr[74] = "dun";
        strArr[75] = "duo";
        strArr[76] = "e";
        strArr[77] = "ei";
        strArr[78] = "en";
        strArr[79] = "er";
        strArr[80] = "fa";
        strArr[81] = "fan";
        strArr[82] = "fang";
        strArr[83] = "fei";
        strArr[84] = "fen";
        strArr[85] = "feng";
        strArr[86] = "fo";
        strArr[87] = "fou";
        strArr[88] = "fu";
        strArr[89] = "ga";
        strArr[90] = "gai";
        strArr[91] = "gan";
        strArr[92] = "gang";
        strArr[93] = "gao";
        strArr[94] = "ge";
        strArr[95] = "gei";
        strArr[96] = "gen";
        strArr[97] = "geng";
        strArr[98] = "gong";
        strArr[99] = "gou";
        strArr[100] = "gu";
        strArr[101] = "gua";
        strArr[102] = "guai";
        strArr[103] = "guan";
        strArr[104] = "guang";
        strArr[105] = "gui";
        strArr[106] = "gun";
        strArr[107] = "guo";
        strArr[108] = "ha";
        strArr[109] = "hai";
        strArr[FTPCodes.RESTART_MARKER] = "han";
        strArr[TrackerEvent.PositioningOnlineOutdoor] = "hang";
        strArr[d.k] = "hao";
        strArr[113] = "he";
        strArr[114] = "hei";
        strArr[115] = "hen";
        strArr[116] = "heng";
        strArr[117] = "hong";
        strArr[118] = "hou";
        strArr[119] = "hu";
        strArr[120] = "hua";
        strArr[TrackerEvent.PositioningHybridOutdoor] = "huai";
        strArr[122] = "huan";
        strArr[123] = "huang";
        strArr[124] = "hui";
        strArr[FTPCodes.DATA_CONNECTION_ALREADY_OPEN] = "hun";
        strArr[TransportMediator.KEYCODE_MEDIA_PLAY] = "huo";
        strArr[TransportMediator.KEYCODE_MEDIA_PAUSE] = "ji";
        strArr[128] = "jia";
        strArr[129] = "jian";
        strArr[TransportMediator.KEYCODE_MEDIA_RECORD] = "jiang";
        strArr[TrackerEvent.PositioningOfflineOutdoor] = "jiao";
        strArr[TrackerEvent.PositioningOfflineCommonIndoor] = "jie";
        strArr[TrackerEvent.PositioningOfflinePrivateIndoor] = "jin";
        strArr[134] = "jing";
        strArr[135] = "jiong";
        strArr[136] = "jiu";
        strArr[137] = "ju";
        strArr[138] = "juan";
        strArr[139] = "jue";
        strArr[140] = "jun";
        strArr[141] = "ka";
        strArr[142] = "kai";
        strArr[143] = "kan";
        strArr[144] = "kang";
        strArr[145] = "kao";
        strArr[BuildConfig.VERSION_CODE] = "ke";
        strArr[147] = "ken";
        strArr[148] = "keng";
        strArr[149] = "kong";
        strArr[150] = "kou";
        strArr[151] = "ku";
        strArr[152] = "kua";
        strArr[153] = "kuai";
        strArr[154] = "kuan";
        strArr[155] = "kuang";
        strArr[156] = "kui";
        strArr[157] = "kun";
        strArr[158] = "kuo";
        strArr[159] = "la";
        strArr[160] = "lai";
        strArr[161] = "lan";
        strArr[162] = "lang";
        strArr[163] = "lao";
        strArr[164] = "le";
        strArr[165] = "lei";
        strArr[166] = "leng";
        strArr[167] = "li";
        strArr[168] = "lia";
        strArr[169] = "lian";
        strArr[170] = "liang";
        strArr[171] = "liao";
        strArr[172] = "lie";
        strArr[173] = "lin";
        strArr[174] = "ling";
        strArr[175] = "liu";
        strArr[176] = "long";
        strArr[177] = "lou";
        strArr[178] = "lu";
        strArr[179] = "luan";
        strArr[180] = "lun";
        strArr[181] = "luo";
        strArr[182] = "lv";
        strArr[183] = "lve";
        strArr[184] = "m";
        strArr[185] = "ma";
        strArr[186] = "mai";
        strArr[187] = "man";
        strArr[188] = "mang";
        strArr[189] = "mao";
        strArr[l.f] = "me";
        strArr[191] = "mei";
        strArr[192] = "men";
        strArr[193] = "meng";
        strArr[194] = "mi";
        strArr[195] = "mian";
        strArr[196] = "miao";
        strArr[197] = "mie";
        strArr[198] = MessageKey.MSG_ACCEPT_TIME_MIN;
        strArr[199] = "ming";
        strArr[200] = "miu";
        strArr[dji.pilot.usercenter.f.d.y] = "mo";
        strArr[FTPCodes.SUPERFLOUS_COMMAND] = "mou";
        strArr[203] = "mu";
        strArr[HttpStatusCodes.STATUS_CODE_NO_CONTENT] = "na";
        strArr[205] = "nai";
        strArr[206] = "nan";
        strArr[207] = "nang";
        strArr[208] = "nao";
        strArr[209] = "ne";
        strArr[210] = "nei";
        strArr[211] = "nen";
        strArr[212] = "neng";
        strArr[213] = "ng";
        strArr[FTPCodes.HELP_MESSAGE] = "ni";
        strArr[FTPCodes.NAME_SYSTEM_TIME] = "nian";
        strArr[216] = "niang";
        strArr[217] = "niao";
        strArr[218] = "nie";
        strArr[219] = "nin";
        strArr[FTPCodes.SERVICE_READY_FOR_NEW_USER] = "ning";
        strArr[221] = "niu";
        strArr[TrackerEvent.RadioMapManualCommonIndoor] = "none";
        strArr[TrackerEvent.RadioMapManualPrivateIndoor] = "nong";
        strArr[dji.thirdparty.g.b.a.a.fw_] = "nou";
        strArr[FTPCodes.DATA_CONNECTION_OPEN] = "nu";
        strArr[FTPCodes.DATA_CONNECTION_CLOSING] = "nuan";
        strArr[FTPCodes.ENTER_PASSIVE_MODE] = "nuo";
        strArr[228] = "nv";
        strArr[229] = "nve";
        strArr[FTPCodes.USER_LOGGED_IN] = "o";
        strArr[231] = "ou";
        strArr[232] = "pa";
        strArr[233] = "pai";
        strArr[234] = "pan";
        strArr[235] = "pang";
        strArr[236] = "pao";
        strArr[237] = "pei";
        strArr[238] = "pen";
        strArr[239] = "peng";
        strArr[240] = "pi";
        strArr[241] = "pian";
        strArr[242] = "piao";
        strArr[243] = "pie";
        strArr[244] = "pin";
        strArr[245] = "ping";
        strArr[246] = "po";
        strArr[247] = "pou";
        strArr[248] = "pu";
        strArr[249] = "qi";
        strArr[250] = "qia";
        strArr[251] = "qian";
        strArr[252] = "qiang";
        strArr[253] = "qiao";
        strArr[254] = "qie";
        strArr[255] = "qin";
        strArr[256] = "qing";
        strArr[257] = "qiong";
        strArr[258] = "qiu";
        strArr[259] = "qu";
        strArr[260] = "quan";
        strArr[261] = "que";
        strArr[b.j] = "qun";
        strArr[b.k] = "ran";
        strArr[b.l] = "rang";
        strArr[265] = "rao";
        strArr[266] = "re";
        strArr[267] = "ren";
        strArr[268] = "reng";
        strArr[269] = "ri";
        strArr[270] = "rong";
        strArr[271] = "rou";
        strArr[272] = "ru";
        strArr[273] = "ruan";
        strArr[274] = "rui";
        strArr[275] = "run";
        strArr[276] = "ruo";
        strArr[277] = "sa";
        strArr[278] = "sai";
        strArr[279] = "san";
        strArr[280] = "sang";
        strArr[281] = "sao";
        strArr[282] = "se";
        strArr[283] = "sen";
        strArr[284] = "seng";
        strArr[285] = "sha";
        strArr[286] = "shai";
        strArr[287] = "shan";
        strArr[288] = "shang";
        strArr[289] = "shao";
        strArr[290] = "she";
        strArr[291] = "shei";
        strArr[292] = "shen";
        strArr[293] = "sheng";
        strArr[294] = "shi";
        strArr[295] = "shou";
        strArr[296] = "shu";
        strArr[297] = "shua";
        strArr[298] = "shuai";
        strArr[299] = "shuan";
        strArr[300] = "shuang";
        strArr[HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY] = "shui";
        strArr[HttpStatusCodes.STATUS_CODE_FOUND] = "shun";
        strArr[HttpStatusCodes.STATUS_CODE_SEE_OTHER] = "shuo";
        strArr[HttpStatusCodes.STATUS_CODE_NOT_MODIFIED] = "si";
        strArr[d.b] = "song";
        strArr[d.f] = "sou";
        strArr[307] = "su";
        strArr[q.b] = "suan";
        strArr[309] = "sui";
        strArr[310] = "sun";
        strArr[311] = "suo";
        strArr[312] = "ta";
        strArr[313] = "tai";
        strArr[314] = "tan";
        strArr[d.h] = "tang";
        strArr[316] = "tao";
        strArr[317] = "te";
        strArr[318] = "teng";
        strArr[319] = "ti";
        strArr[320] = "tian";
        strArr[321] = "tiao";
        strArr[322] = "tie";
        strArr[323] = "ting";
        strArr[324] = "tong";
        strArr[PullToRefreshBase.SMOOTH_SCROLL_LONG_DURATION_MS] = "tou";
        strArr[326] = "tu";
        strArr[327] = "tuan";
        strArr[328] = "tui";
        strArr[329] = "tun";
        strArr[330] = "tuo";
        strArr[FTPCodes.USERNAME_OK] = "wa";
        strArr[FTPCodes.NEED_ACCOUNT] = "wai";
        strArr[333] = "wan";
        strArr[334] = "wang";
        strArr[335] = "wei";
        strArr[336] = "wen";
        strArr[337] = "weng";
        strArr[338] = "wo";
        strArr[339] = "wu";
        strArr[340] = "xi";
        strArr[l.g] = "xia";
        strArr[342] = "xian";
        strArr[343] = "xiang";
        strArr[344] = "xiao";
        strArr[345] = "xie";
        strArr[346] = "xin";
        strArr[347] = "xing";
        strArr[348] = "xiong";
        strArr[349] = "xiu";
        strArr[FTPCodes.PENDING_FURTHER_INFORMATION] = "xu";
        strArr[351] = "xuan";
        strArr[352] = "xue";
        strArr[353] = "xun";
        strArr[354] = "ya";
        strArr[355] = "yan";
        strArr[356] = "yang";
        strArr[357] = "yao";
        strArr[358] = "ye";
        strArr[359] = "yi";
        strArr[360] = "yiao";
        strArr[361] = "yin";
        strArr[362] = "ying";
        strArr[363] = "yo";
        strArr[364] = "yong";
        strArr[365] = "you";
        strArr[366] = "yu";
        strArr[367] = "yuan";
        strArr[368] = "yue";
        strArr[369] = "yun";
        strArr[370] = "za";
        strArr[371] = "zai";
        strArr[372] = "zan";
        strArr[373] = "zang";
        strArr[374] = "zao";
        strArr[375] = "ze";
        strArr[376] = "zei";
        strArr[377] = "zen";
        strArr[378] = "zeng";
        strArr[379] = "zha";
        strArr[380] = "zhai";
        strArr[381] = "zhan";
        strArr[382] = "zhang";
        strArr[383] = "zhao";
        strArr[384] = "zhe";
        strArr[385] = "zhei";
        strArr[386] = "zhen";
        strArr[387] = "zheng";
        strArr[388] = "zhi";
        strArr[389] = "zhong";
        strArr[390] = "zhou";
        strArr[391] = "zhu";
        strArr[392] = "zhua";
        strArr[393] = "zhuai";
        strArr[394] = "zhuan";
        strArr[395] = "zhuang";
        strArr[396] = "zhui";
        strArr[397] = "zhun";
        strArr[398] = "zhuo";
        strArr[399] = "zi";
        strArr[400] = "zong";
        strArr[401] = "zou";
        strArr[a.y] = "zu";
        strArr[HttpStatusCodes.STATUS_CODE_FORBIDDEN] = "zuan";
        strArr[404] = "zui";
        strArr[a.A] = "zun";
        strArr[a.B] = "zuo";
        PINYIN = strArr;
    }

    private PinyinUtils() {
    }

    public static synchronized PinyinUtils getInstance(Context context) {
        PinyinUtils pinyinUtils;
        synchronized (PinyinUtils.class) {
            if (sInstance == null) {
                sInstance = new PinyinUtils();
            }
            loadData(context);
            pinyinUtils = sInstance;
        }
        return pinyinUtils;
    }

    private static void loadData(Context context) {
        InputStream open;
        DataInputStream dataInputStream;
        InputStream inputStream;
        Throwable th;
        Throwable th2;
        DataInputStream dataInputStream2 = null;
        InputStream inputStream2 = null;
        DataInputStream dataInputStream3 = null;
        try {
            if (isLoad) {
                if (null != null) {
                    try {
                        dataInputStream3.close();
                    } catch (IOException e) {
                        return;
                    }
                }
                if (null != null) {
                    inputStream2.close();
                    return;
                }
                return;
            }
            open = context.getAssets().open("pinyinindex");
            try {
                dataInputStream = new DataInputStream(open);
                try {
                    sPinyinIndex = new short[((int) ((long) (dataInputStream.available() >> 1)))];
                    for (int i = 0; i < sPinyinIndex.length; i++) {
                        sPinyinIndex[i] = dataInputStream.readShort();
                    }
                    isLoad = true;
                    if (dataInputStream != null) {
                        try {
                            dataInputStream.close();
                        } catch (IOException e2) {
                            return;
                        }
                    }
                    if (open != null) {
                        open.close();
                    }
                } catch (IOException e3) {
                    inputStream = open;
                } catch (Exception e4) {
                }
            } catch (IOException e5) {
                dataInputStream = null;
                inputStream = open;
                try {
                    isLoad = false;
                    if (dataInputStream != null) {
                        try {
                            dataInputStream.close();
                        } catch (IOException e6) {
                            return;
                        }
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    open = inputStream;
                    dataInputStream2 = dataInputStream;
                    th2 = th;
                    if (dataInputStream2 != null) {
                        try {
                            dataInputStream2.close();
                        } catch (IOException e7) {
                            throw th2;
                        }
                    }
                    if (open != null) {
                        open.close();
                    }
                    throw th2;
                }
            } catch (Exception e8) {
                dataInputStream = null;
                try {
                    isLoad = false;
                    if (dataInputStream != null) {
                        try {
                            dataInputStream.close();
                        } catch (IOException e9) {
                            return;
                        }
                    }
                    if (open != null) {
                        open.close();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    dataInputStream2 = dataInputStream;
                    th2 = th;
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    if (open != null) {
                        open.close();
                    }
                    throw th2;
                }
            } catch (Throwable th5) {
                th2 = th5;
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                if (open != null) {
                    open.close();
                }
                throw th2;
            }
        } catch (IOException e10) {
            dataInputStream = null;
            isLoad = false;
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e11) {
            dataInputStream = null;
            open = null;
            isLoad = false;
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            if (open != null) {
                open.close();
            }
        } catch (Throwable th6) {
            th2 = th6;
            open = null;
            if (dataInputStream2 != null) {
                dataInputStream2.close();
            }
            if (open != null) {
                open.close();
            }
            throw th2;
        }
    }

    private String getPinyin(char c) {
        if (!isLoad) {
            return "";
        }
        String str = "";
        if (c == SPECIAL_HANZI) {
            return SPECIAL_HANZI_PINYIN;
        }
        if (c < FIRST_CHINA || c > LAST_CHINA) {
            return String.valueOf(c);
        }
        str = PINYIN[sPinyinIndex[c - 19968]];
        if (str == null) {
            return "";
        }
        return str;
    }

    public String getPinyin(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (!isLoad) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(getPinyin(str.charAt(i)));
        }
        return stringBuilder.toString();
    }

    public MatchedResult getMatchedResult(String str, String str2) {
        MatchedResult matchedResult = new MatchedResult();
        matchedResult.start = -1;
        matchedResult.end = -1;
        if (!isLoad) {
            return matchedResult;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return matchedResult;
        }
        String str3;
        int i;
        int i2;
        String toUpperCase = str.toUpperCase();
        String toUpperCase2 = str2.toUpperCase();
        if (Math.min(toUpperCase.length(), toUpperCase2.length()) > 10) {
            toUpperCase = toUpperCase.substring(0, 10);
            toUpperCase2 = toUpperCase2.substring(0, 10);
            str3 = toUpperCase;
        } else {
            str3 = toUpperCase;
        }
        int indexOf = str3.indexOf(toUpperCase2);
        if (indexOf >= 0) {
            matchedResult.start = indexOf;
            matchedResult.end = (indexOf + toUpperCase2.length()) - 1;
        }
        char[] cArr = new char[toUpperCase2.length()];
        for (i = 0; i < toUpperCase2.length(); i++) {
            cArr[i] = toUpperCase2.charAt(i);
        }
        char[] cArr2 = new char[str3.length()];
        String[] strArr = new String[str3.length()];
        i = str3.length();
        for (i2 = 0; i2 < i; i2++) {
            char charAt = str3.charAt(i2);
            cArr2[i2] = charAt;
            Object pinyin = getPinyin(charAt);
            if (TextUtils.isEmpty(pinyin)) {
                strArr[i2] = new StringBuilder(String.valueOf(charAt)).toString();
            } else {
                strArr[i2] = pinyin.toUpperCase();
            }
        }
        char c = cArr[0];
        for (int i3 = 0; i3 < strArr.length; i3++) {
            char charAt2 = strArr[i3].charAt(0);
            char c2 = cArr2[i3];
            if (charAt2 == c || c2 == c) {
                i2 = distinguish(cArr, 0, subCharRangeArray(cArr2, i3, cArr2.length - 1), subStringRangeArray(strArr, i3, strArr.length - 1), 0, 0);
                if (i2 != -1) {
                    matchedResult.start = i3;
                    matchedResult.end = i2 + i3;
                    return matchedResult;
                }
            }
        }
        return matchedResult;
    }

    public int distinguish(char[] cArr, int i, char[] cArr2, String[] strArr, int i2, int i3) {
        if (i == 0 && (cArr[0] == cArr2[0] || cArr[0] == strArr[0].charAt(0))) {
            if (cArr.length != 1) {
                return distinguish(cArr, 1, cArr2, strArr, 0, 1);
            }
            return 0;
        } else if (strArr[i2].length() <= i3 || i >= cArr.length || !(cArr[i] == cArr2[i2] || cArr[i] == strArr[i2].charAt(i3))) {
            if (strArr.length <= i2 + 1 || i >= cArr.length || !(cArr[i] == cArr2[i2 + 1] || cArr[i] == strArr[i2 + 1].charAt(0))) {
                if (strArr.length > i2 + 1) {
                    for (int i4 = 1; i4 < i; i4++) {
                        if (distinguish(cArr, i - i4, cArr2, strArr, i2 + 1, 0) != -1) {
                            return i2 + 1;
                        }
                    }
                }
                return -1;
            } else if (i != cArr.length - 1) {
                return distinguish(cArr, i + 1, cArr2, strArr, i2 + 1, 1);
            } else if (distinguish(cArr, cArr2, strArr, i2)) {
                return i2 + 1;
            } else {
                return -1;
            }
        } else if (i != cArr.length - 1) {
            return distinguish(cArr, i + 1, cArr2, strArr, i2, i3 + 1);
        } else if (distinguish(cArr, cArr2, strArr, i2)) {
            return i2;
        } else {
            return -1;
        }
    }

    private boolean distinguish(char[] cArr, char[] cArr2, String[] strArr, int i) {
        String str = new String(cArr);
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            i3 = str.indexOf(strArr[i2].charAt(0), i3);
            if (i3 == -1) {
                i3 = str.indexOf(cArr2[i2], i3);
            }
            if (i3 == -1) {
                return false;
            }
            i2++;
            i3++;
        }
        return true;
    }

    private char[] subCharRangeArray(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[((i2 - i) + 1)];
        int i3 = 0;
        while (i <= i2) {
            cArr2[i3] = cArr[i];
            i++;
            i3++;
        }
        return cArr2;
    }

    private String[] subStringRangeArray(String[] strArr, int i, int i2) {
        String[] strArr2 = new String[((i2 - i) + 1)];
        int i3 = 0;
        while (i <= i2) {
            strArr2[i3] = strArr[i];
            i++;
            i3++;
        }
        return strArr2;
    }

    public static PinyinUtils getObject() {
        return sInstance;
    }
}
