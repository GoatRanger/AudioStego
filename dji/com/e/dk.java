package com.e;

import android.content.Context;
import android.os.Looper;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class dk extends d {
    private static boolean a = true;
    private String[] b = new String[10];
    private int c = 0;
    private boolean d = false;
    private int e = 0;

    protected dk(int i) {
        super(i);
    }

    private void b(String str) {
        try {
            if (this.c > 9) {
                this.c = 0;
            }
            this.b[this.c] = str;
            this.c++;
        } catch (Throwable th) {
            dg.a(th, "ANRWriter", "addData");
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
            dg.a(th, "ANRWriter", "getLogInfo");
        }
        return stringBuilder.toString();
    }

    protected String a(List<dc> list) {
        InputStream fileInputStream;
        ah ahVar;
        InputStream inputStream;
        Throwable e;
        String str;
        String str2;
        InputStream inputStream2 = null;
        ah ahVar2 = null;
        try {
            File file = new File("/data/anr/traces.txt");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    ahVar2 = new ah(fileInputStream, ai.a);
                    Object obj = null;
                    while (true) {
                        try {
                            String str3;
                            Object obj2;
                            String a = ahVar2.a();
                            if (a.contains("pid")) {
                                while (!a.contains("\"main\"")) {
                                    a = ahVar2.a();
                                }
                                str3 = a;
                                obj2 = 1;
                            } else {
                                str3 = a;
                                obj2 = obj;
                            }
                            obj = str3.equals("") ? null : obj2;
                            if (obj != null) {
                                b(str3);
                                if (this.e == 5) {
                                    break;
                                } else if (this.d) {
                                    this.e++;
                                } else {
                                    for (dc dcVar : list) {
                                        this.d = d.a(dcVar.e(), str3);
                                        if (this.d) {
                                            a(dcVar);
                                        }
                                    }
                                }
                            }
                        } catch (EOFException e2) {
                        } catch (FileNotFoundException e3) {
                            ahVar = ahVar2;
                            inputStream = fileInputStream;
                        } catch (IOException e4) {
                            e = e4;
                        }
                    }
                    if (ahVar2 != null) {
                        try {
                            ahVar2.close();
                        } catch (Throwable e5) {
                            dg.a(e5, "ANRWriter", "initLog1");
                        } catch (Throwable e52) {
                            dg.a(e52, "ANRWriter", "initLog2");
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            e52 = e6;
                            str = "ANRWriter";
                            str2 = "initLog3";
                            dg.a(e52, str, str2);
                            return this.d ? null : d();
                        } catch (Throwable th) {
                            e52 = th;
                            str = "ANRWriter";
                            str2 = "initLog4";
                            dg.a(e52, str, str2);
                            if (this.d) {
                            }
                        }
                    }
                } catch (FileNotFoundException e7) {
                    ahVar = null;
                    inputStream = fileInputStream;
                    if (ahVar != null) {
                        try {
                            ahVar.close();
                        } catch (Throwable e522) {
                            dg.a(e522, "ANRWriter", "initLog1");
                        } catch (Throwable e5222) {
                            dg.a(e5222, "ANRWriter", "initLog2");
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e8) {
                            e5222 = e8;
                            str = "ANRWriter";
                            str2 = "initLog3";
                            dg.a(e5222, str, str2);
                            if (this.d) {
                            }
                        } catch (Throwable th2) {
                            e5222 = th2;
                            str = "ANRWriter";
                            str2 = "initLog4";
                            dg.a(e5222, str, str2);
                            if (this.d) {
                            }
                        }
                    }
                    if (this.d) {
                    }
                } catch (IOException e9) {
                    e5222 = e9;
                    ahVar2 = null;
                    try {
                        dg.a(e5222, "ANRWriter", "initLog");
                        if (ahVar2 != null) {
                            try {
                                ahVar2.close();
                            } catch (Throwable e52222) {
                                dg.a(e52222, "ANRWriter", "initLog1");
                            } catch (Throwable e522222) {
                                dg.a(e522222, "ANRWriter", "initLog2");
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e10) {
                                e522222 = e10;
                                str = "ANRWriter";
                                str2 = "initLog3";
                                dg.a(e522222, str, str2);
                                if (this.d) {
                                }
                            } catch (Throwable th3) {
                                e522222 = th3;
                                str = "ANRWriter";
                                str2 = "initLog4";
                                dg.a(e522222, str, str2);
                                if (this.d) {
                                }
                            }
                        }
                        if (this.d) {
                        }
                    } catch (Throwable th4) {
                        e522222 = th4;
                        if (ahVar2 != null) {
                            try {
                                ahVar2.close();
                            } catch (Throwable e11) {
                                dg.a(e11, "ANRWriter", "initLog1");
                            } catch (Throwable e112) {
                                dg.a(e112, "ANRWriter", "initLog2");
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e1122) {
                                dg.a(e1122, "ANRWriter", "initLog3");
                            } catch (Throwable e11222) {
                                dg.a(e11222, "ANRWriter", "initLog4");
                            }
                        }
                        throw e522222;
                    }
                } catch (Throwable th5) {
                    e522222 = th5;
                    ahVar2 = null;
                    if (ahVar2 != null) {
                        ahVar2.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw e522222;
                }
                if (this.d) {
                }
            }
            if (null != null) {
                try {
                    ahVar2.close();
                } catch (Throwable e12) {
                    dg.a(e12, "ANRWriter", "initLog1");
                } catch (Throwable e122) {
                    dg.a(e122, "ANRWriter", "initLog2");
                }
            }
            if (null != null) {
                try {
                    inputStream2.close();
                } catch (Throwable e5222222) {
                    dg.a(e5222222, "ANRWriter", "initLog3");
                } catch (Throwable e52222222) {
                    dg.a(e52222222, "ANRWriter", "initLog4");
                }
            }
            return null;
        } catch (FileNotFoundException e13) {
            ahVar = null;
            inputStream = null;
            if (ahVar != null) {
                ahVar.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (this.d) {
            }
        } catch (IOException e14) {
            e52222222 = e14;
            ahVar2 = null;
            fileInputStream = null;
            dg.a(e52222222, "ANRWriter", "initLog");
            if (ahVar2 != null) {
                ahVar2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (this.d) {
            }
        } catch (Throwable th6) {
            e52222222 = th6;
            ahVar2 = null;
            fileInputStream = null;
            if (ahVar2 != null) {
                ahVar2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw e52222222;
        }
    }

    protected boolean a(Context context) {
        if (cx.m(context) != 1 || !a) {
            return false;
        }
        a = false;
        synchronized (Looper.getMainLooper()) {
            q qVar = new q(context);
            r a = qVar.a();
            if (a == null) {
                return true;
            } else if (a.c()) {
                a.c(false);
                qVar.a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
