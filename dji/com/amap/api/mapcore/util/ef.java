package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ef extends ei {
    private static boolean a = true;
    private String[] b = new String[10];
    private int c = 0;
    private boolean d = false;
    private int e = 0;

    protected ef(int i) {
        super(i);
    }

    protected boolean a(Context context) {
        if (dq.m(context) != 1 || !a) {
            return false;
        }
        a = false;
        synchronized (Looper.getMainLooper()) {
            ev evVar = new ev(context);
            ew a = evVar.a();
            if (a == null) {
                return true;
            } else if (a.c()) {
                a.c(false);
                evVar.a(a);
                return true;
            } else {
                return false;
            }
        }
    }

    protected String a(List<dv> list) {
        InputStream fileInputStream;
        fo foVar;
        InputStream inputStream;
        Throwable e;
        String str;
        String str2;
        InputStream inputStream2 = null;
        fo foVar2 = null;
        try {
            File file = new File("/data/anr/traces.txt");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    foVar2 = new fo(fileInputStream, fp.a);
                    Object obj = null;
                    while (true) {
                        try {
                            String str3;
                            Object obj2;
                            String a = foVar2.a();
                            if (a.contains("pid")) {
                                while (!a.contains("\"main\"")) {
                                    a = foVar2.a();
                                }
                                str3 = a;
                                obj2 = 1;
                            } else {
                                str3 = a;
                                obj2 = obj;
                            }
                            if (str3.equals("")) {
                                obj = null;
                            } else {
                                obj = obj2;
                            }
                            if (obj != null) {
                                b(str3);
                                if (this.e == 5) {
                                    break;
                                } else if (this.d) {
                                    this.e++;
                                } else {
                                    for (dv dvVar : list) {
                                        this.d = ei.a(dvVar.e(), str3);
                                        if (this.d) {
                                            a(dvVar);
                                        }
                                    }
                                }
                            }
                        } catch (EOFException e2) {
                        } catch (FileNotFoundException e3) {
                            foVar = foVar2;
                            inputStream = fileInputStream;
                        } catch (IOException e4) {
                            e = e4;
                        }
                    }
                    if (foVar2 != null) {
                        try {
                            foVar2.close();
                        } catch (Throwable e5) {
                            eb.a(e5, "ANRWriter", "initLog1");
                        } catch (Throwable e52) {
                            eb.a(e52, "ANRWriter", "initLog2");
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            e52 = e6;
                            str = "ANRWriter";
                            str2 = "initLog3";
                            eb.a(e52, str, str2);
                            if (this.d) {
                                return null;
                            }
                            return d();
                        } catch (Throwable th) {
                            e52 = th;
                            str = "ANRWriter";
                            str2 = "initLog4";
                            eb.a(e52, str, str2);
                            if (this.d) {
                                return d();
                            }
                            return null;
                        }
                    }
                } catch (FileNotFoundException e7) {
                    foVar = null;
                    inputStream = fileInputStream;
                    if (foVar != null) {
                        try {
                            foVar.close();
                        } catch (Throwable e522) {
                            eb.a(e522, "ANRWriter", "initLog1");
                        } catch (Throwable e5222) {
                            eb.a(e5222, "ANRWriter", "initLog2");
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e8) {
                            e5222 = e8;
                            str = "ANRWriter";
                            str2 = "initLog3";
                            eb.a(e5222, str, str2);
                            if (this.d) {
                                return null;
                            }
                            return d();
                        } catch (Throwable th2) {
                            e5222 = th2;
                            str = "ANRWriter";
                            str2 = "initLog4";
                            eb.a(e5222, str, str2);
                            if (this.d) {
                                return d();
                            }
                            return null;
                        }
                    }
                    if (this.d) {
                        return null;
                    }
                    return d();
                } catch (IOException e9) {
                    e5222 = e9;
                    foVar2 = null;
                    try {
                        eb.a(e5222, "ANRWriter", "initLog");
                        if (foVar2 != null) {
                            try {
                                foVar2.close();
                            } catch (Throwable e52222) {
                                eb.a(e52222, "ANRWriter", "initLog1");
                            } catch (Throwable e522222) {
                                eb.a(e522222, "ANRWriter", "initLog2");
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e10) {
                                e522222 = e10;
                                str = "ANRWriter";
                                str2 = "initLog3";
                                eb.a(e522222, str, str2);
                                if (this.d) {
                                    return null;
                                }
                                return d();
                            } catch (Throwable th3) {
                                e522222 = th3;
                                str = "ANRWriter";
                                str2 = "initLog4";
                                eb.a(e522222, str, str2);
                                if (this.d) {
                                    return d();
                                }
                                return null;
                            }
                        }
                        if (this.d) {
                            return d();
                        }
                        return null;
                    } catch (Throwable th4) {
                        e522222 = th4;
                        if (foVar2 != null) {
                            try {
                                foVar2.close();
                            } catch (Throwable e11) {
                                eb.a(e11, "ANRWriter", "initLog1");
                            } catch (Throwable e112) {
                                eb.a(e112, "ANRWriter", "initLog2");
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e1122) {
                                eb.a(e1122, "ANRWriter", "initLog3");
                            } catch (Throwable e11222) {
                                eb.a(e11222, "ANRWriter", "initLog4");
                            }
                        }
                        throw e522222;
                    }
                } catch (Throwable th5) {
                    e522222 = th5;
                    foVar2 = null;
                    if (foVar2 != null) {
                        foVar2.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw e522222;
                }
                if (this.d) {
                    return d();
                }
                return null;
            }
            if (null != null) {
                try {
                    foVar2.close();
                } catch (Throwable e12) {
                    eb.a(e12, "ANRWriter", "initLog1");
                } catch (Throwable e122) {
                    eb.a(e122, "ANRWriter", "initLog2");
                }
            }
            if (null != null) {
                try {
                    inputStream2.close();
                } catch (Throwable e5222222) {
                    eb.a(e5222222, "ANRWriter", "initLog3");
                } catch (Throwable e52222222) {
                    eb.a(e52222222, "ANRWriter", "initLog4");
                }
            }
            return null;
        } catch (FileNotFoundException e13) {
            foVar = null;
            inputStream = null;
            if (foVar != null) {
                foVar.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (this.d) {
                return null;
            }
            return d();
        } catch (IOException e14) {
            e52222222 = e14;
            foVar2 = null;
            fileInputStream = null;
            eb.a(e52222222, "ANRWriter", "initLog");
            if (foVar2 != null) {
                foVar2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (this.d) {
                return d();
            }
            return null;
        } catch (Throwable th6) {
            e52222222 = th6;
            foVar2 = null;
            fileInputStream = null;
            if (foVar2 != null) {
                foVar2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw e52222222;
        }
    }

    private String d() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int i = this.c;
            while (i < 10 && i <= 9) {
                stringBuilder.append(this.b[i]);
                i++;
            }
            for (i = 0; i < this.c; i++) {
                stringBuilder.append(this.b[i]);
            }
        } catch (Throwable th) {
            eb.a(th, "ANRWriter", "getLogInfo");
        }
        return stringBuilder.toString();
    }

    private void b(String str) {
        try {
            if (this.c > 9) {
                this.c = 0;
            }
            this.b[this.c] = str;
            this.c++;
        } catch (Throwable th) {
            eb.a(th, "ANRWriter", "addData");
        }
    }
}
