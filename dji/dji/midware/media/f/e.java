package dji.midware.media.f;

import android.util.Log;

public class e extends a {
    public a h = new a(this);
    public c i = new c(this);
    public b j = new b(this);

    public class a extends a {
        public final b h = b.mvhd;
        public final int i = 108;
        public byte j;
        public byte[] k = new byte[3];
        public int l;
        public int m;
        public int n;
        public int o;
        public double p;
        public short q;
        public byte[] r = new byte[10];
        public byte[] s = new byte[36];
        public byte[] t = new byte[24];
        public int u;
        final /* synthetic */ e v;

        public a(e eVar) {
            this.v = eVar;
        }

        public boolean a(byte[] bArr, int i) {
            this.e = i;
            this.f = c.b(bArr, 0);
            this.g = c.b(bArr, 4, 4);
            if (this.f == 108 && this.h.a(this.g)) {
                this.j = bArr[8];
                System.arraycopy(bArr, 9, this.k, 0, 3);
                this.l = c.b(bArr, 12);
                this.m = c.b(bArr, 16);
                this.n = c.b(bArr, 20);
                this.o = c.b(bArr, 24);
                this.p = (double) c.d(bArr, 28);
                this.q = c.a(bArr, 32);
                System.arraycopy(bArr, 34, this.r, 0, 10);
                System.arraycopy(bArr, 44, this.s, 0, 36);
                System.arraycopy(bArr, 80, this.t, 0, 24);
                this.u = c.b(bArr, 104);
                this.d = bArr;
                return true;
            }
            Log.e(a, "mvhd parse error");
            return false;
        }
    }

    public class b extends a {
        public final b h = b.trak;
        public b i = new b(this);
        public a j = new a(b.edts, 36);
        public a k = new a(this);
        final /* synthetic */ e l;

        public class a extends a {
            public final b h = b.mdia;
            public a i = new a(b.mdhd, 32);
            public a j = new a(b.hdlr, 49);
            public a k = new a(this);
            final /* synthetic */ b l;

            public class a extends a {
                public final b h = b.minf;
                public a i = new a(b.vmhd, 20);
                public a j = new a(b.dinf, 36);
                public a k = new a(this);
                final /* synthetic */ a l;

                public class a extends a {
                    public final b h = b.stbl;
                    public b i = new b(this);
                    public a j = new a();
                    public a k = new a();
                    public a l = new a();
                    public c m = new c(this);
                    public a n = new a(this);
                    final /* synthetic */ a o;

                    public class a extends a {
                        public final b h = b.stco;
                        public byte i;
                        public byte[] j = new byte[3];
                        public int k;
                        public int[] l;
                        final /* synthetic */ a m;

                        public a(dji.midware.media.f.e.b.a.a.a r2) {
                            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                            /*
                            r1 = this;
                            r1.m = r2;
                            r1.<init>();
                            r0 = dji.midware.media.f.b.stco;
                            r1.h = r0;
                            r0 = 3;
                            r0 = new byte[r0];
                            r1.j = r0;
                            return;
                            */
                            throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.a.<init>(dji.midware.media.f.e$b$a$a$a):void");
                        }

                        public boolean a(byte[] r5, int r6) {
                            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                            /*
                            r4 = this;
                            r2 = 4;
                            r0 = 0;
                            r1 = dji.midware.media.f.c.b(r5, r0);
                            r4.f = r1;
                            r1 = dji.midware.media.f.c.b(r5, r2, r2);
                            r4.g = r1;
                            r1 = 8;
                            r1 = r5[r1];
                            r4.i = r1;
                            r1 = 9;
                            r2 = r4.j;
                            r3 = 3;
                            java.lang.System.arraycopy(r5, r1, r2, r0, r3);
                            r1 = 12;
                            r1 = dji.midware.media.f.c.b(r5, r1);
                            r4.k = r1;
                            r1 = 16;
                            r2 = r4.k;
                            if (r2 <= 0) goto L_0x0041;
                        L_0x002a:
                            r2 = r4.k;
                            r2 = new int[r2];
                            r4.l = r2;
                        L_0x0030:
                            r2 = r4.k;
                            if (r0 >= r2) goto L_0x0048;
                        L_0x0034:
                            r2 = r4.l;
                            r3 = dji.midware.media.f.c.b(r5, r1);
                            r2[r0] = r3;
                            r1 = r1 + 4;
                            r0 = r0 + 1;
                            goto L_0x0030;
                        L_0x0041:
                            r0 = a;
                            r1 = "parse stco entry count error";
                            android.util.Log.e(r0, r1);
                        L_0x0048:
                            r0 = 1;
                            return r0;
                            */
                            throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.a.a(byte[], int):boolean");
                        }
                    }

                    public class b extends a {
                        public final b h = b.stsd;
                        public a i = new a(this);
                        public byte j;
                        public byte[] k = new byte[3];
                        public int l;
                        final /* synthetic */ a m;

                        public class a extends a {
                            public final b h = b.avc1;
                            public int i;
                            public int j;
                            public int k;
                            public int l;
                            public a m = new a(this);
                            final /* synthetic */ b n;

                            public class a extends a {
                                public final b h = b.avcC;
                                public byte[] i = new byte[45];
                                public byte[] j = new byte[4];
                                final /* synthetic */ a k;

                                public a(dji.midware.media.f.e.b.a.a.a.b.a r2) {
                                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                                    /*
                                    r1 = this;
                                    r1.k = r2;
                                    r1.<init>();
                                    r0 = dji.midware.media.f.b.avcC;
                                    r1.h = r0;
                                    r0 = 45;
                                    r0 = new byte[r0];
                                    r1.i = r0;
                                    r0 = 4;
                                    r0 = new byte[r0];
                                    r1.j = r0;
                                    return;
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.b.a.a.<init>(dji.midware.media.f.e$b$a$a$a$b$a):void");
                                }

                                public boolean a(byte[] r6, int r7) {
                                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                                    /*
                                    r5 = this;
                                    r4 = 4;
                                    r3 = 0;
                                    r0 = dji.midware.media.f.c.b(r6, r3);
                                    r5.f = r0;
                                    r0 = dji.midware.media.f.c.b(r6, r4, r4);
                                    r5.g = r0;
                                    r0 = 16;
                                    r1 = r5.i;
                                    r2 = 45;
                                    java.lang.System.arraycopy(r6, r0, r1, r3, r2);
                                    r0 = 64;
                                    r1 = r5.j;
                                    java.lang.System.arraycopy(r6, r0, r1, r3, r4);
                                    r0 = 1;
                                    return r0;
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.b.a.a.a(byte[], int):boolean");
                                }
                            }

                            public a(dji.midware.media.f.e.b.a.a.a.b r2) {
                                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                                /*
                                r1 = this;
                                r1.n = r2;
                                r1.<init>();
                                r0 = dji.midware.media.f.b.avc1;
                                r1.h = r0;
                                r0 = new dji.midware.media.f.e$b$a$a$a$b$a$a;
                                r0.<init>(r1);
                                r1.m = r0;
                                return;
                                */
                                throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.b.a.<init>(dji.midware.media.f.e$b$a$a$a$b):void");
                            }

                            public boolean a(byte[] r6, int r7) {
                                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                                /*
                                r5 = this;
                                r4 = 86;
                                r3 = 4;
                                r0 = 0;
                                r1 = dji.midware.media.f.c.b(r6, r0);
                                r5.f = r1;
                                r1 = dji.midware.media.f.c.b(r6, r3, r3);
                                r5.g = r1;
                                r1 = 32;
                                r1 = dji.midware.media.f.c.a(r6, r1);
                                r5.i = r1;
                                r1 = 34;
                                r1 = dji.midware.media.f.c.a(r6, r1);
                                r5.j = r1;
                                r1 = 36;
                                r1 = dji.midware.media.f.c.b(r6, r1);
                                r5.k = r1;
                                r1 = 40;
                                r1 = dji.midware.media.f.c.b(r6, r1);
                                r5.l = r1;
                                r1 = dji.midware.media.f.c.b(r6, r4);
                                r2 = 90;
                                r2 = dji.midware.media.f.c.b(r6, r2, r3);
                                r3 = new byte[r1];
                                java.lang.System.arraycopy(r6, r4, r3, r0, r1);
                                r1 = dji.midware.media.f.b.avcC;
                                r1 = r1.a(r2);
                                if (r1 == 0) goto L_0x004d;
                            L_0x0047:
                                r0 = r5.m;
                                r0.a(r3, r4);
                                r0 = 1;
                            L_0x004d:
                                return r0;
                                */
                                throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.b.a.a(byte[], int):boolean");
                            }
                        }

                        public b(dji.midware.media.f.e.b.a.a.a r2) {
                            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                            /*
                            r1 = this;
                            r1.m = r2;
                            r1.<init>();
                            r0 = dji.midware.media.f.b.stsd;
                            r1.h = r0;
                            r0 = 3;
                            r0 = new byte[r0];
                            r1.k = r0;
                            r0 = new dji.midware.media.f.e$b$a$a$a$b$a;
                            r0.<init>(r1);
                            r1.i = r0;
                            return;
                            */
                            throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.b.<init>(dji.midware.media.f.e$b$a$a$a):void");
                        }

                        public boolean a(byte[] r7, int r8) {
                            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                            /*
                            r6 = this;
                            r5 = 16;
                            r4 = 4;
                            r0 = 0;
                            r1 = dji.midware.media.f.c.b(r7, r0);
                            r6.f = r1;
                            r1 = dji.midware.media.f.c.b(r7, r4, r4);
                            r6.g = r1;
                            r1 = 8;
                            r1 = r7[r1];
                            r6.j = r1;
                            r1 = 9;
                            r2 = r6.k;
                            r3 = 3;
                            java.lang.System.arraycopy(r7, r1, r2, r0, r3);
                            r1 = 12;
                            r1 = dji.midware.media.f.c.b(r7, r1);
                            r6.l = r1;
                            r1 = dji.midware.media.f.c.b(r7, r5);
                            r2 = 20;
                            r2 = dji.midware.media.f.c.b(r7, r2, r4);
                            r3 = new byte[r1];
                            java.lang.System.arraycopy(r7, r5, r3, r0, r1);
                            r1 = dji.midware.media.f.b.avc1;
                            r1 = r1.a(r2);
                            if (r1 == 0) goto L_0x0043;
                        L_0x003d:
                            r0 = r6.i;
                            r0.a(r3, r5);
                            r0 = 1;
                        L_0x0043:
                            return r0;
                            */
                            throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.b.a(byte[], int):boolean");
                        }
                    }

                    public class c extends a {
                        public final b h = b.stsz;
                        public byte i;
                        public byte[] j = new byte[3];
                        public int k;
                        public int l;
                        public int[] m;
                        final /* synthetic */ a n;

                        public c(dji.midware.media.f.e.b.a.a.a r2) {
                            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                            /*
                            r1 = this;
                            r1.n = r2;
                            r1.<init>();
                            r0 = dji.midware.media.f.b.stsz;
                            r1.h = r0;
                            r0 = 3;
                            r0 = new byte[r0];
                            r1.j = r0;
                            return;
                            */
                            throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.c.<init>(dji.midware.media.f.e$b$a$a$a):void");
                        }

                        public boolean a(byte[] r5, int r6) {
                            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                            /*
                            r4 = this;
                            r2 = 4;
                            r0 = 0;
                            r1 = dji.midware.media.f.c.b(r5, r0);
                            r4.f = r1;
                            r1 = dji.midware.media.f.c.b(r5, r2, r2);
                            r4.g = r1;
                            r1 = 8;
                            r1 = r5[r1];
                            r4.i = r1;
                            r1 = 9;
                            r2 = r4.j;
                            r3 = 3;
                            java.lang.System.arraycopy(r5, r1, r2, r0, r3);
                            r1 = 12;
                            r1 = dji.midware.media.f.c.b(r5, r1);
                            r4.k = r1;
                            r1 = 16;
                            r1 = dji.midware.media.f.c.b(r5, r1);
                            r4.l = r1;
                            r1 = 20;
                            r2 = r4.l;
                            if (r2 <= 0) goto L_0x0049;
                        L_0x0032:
                            r2 = r4.l;
                            r2 = new int[r2];
                            r4.m = r2;
                        L_0x0038:
                            r2 = r4.l;
                            if (r0 >= r2) goto L_0x0050;
                        L_0x003c:
                            r2 = r4.m;
                            r3 = dji.midware.media.f.c.b(r5, r1);
                            r2[r0] = r3;
                            r1 = r1 + 4;
                            r0 = r0 + 1;
                            goto L_0x0038;
                        L_0x0049:
                            r0 = a;
                            r1 = "parse stsz sample count error";
                            android.util.Log.e(r0, r1);
                        L_0x0050:
                            r0 = 1;
                            return r0;
                            */
                            throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.c.a(byte[], int):boolean");
                        }
                    }

                    public a(dji.midware.media.f.e.b.a.a r2) {
                        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                        /*
                        r1 = this;
                        r1.o = r2;
                        r1.<init>();
                        r0 = dji.midware.media.f.b.stbl;
                        r1.h = r0;
                        r0 = new dji.midware.media.f.e$b$a$a$a$b;
                        r0.<init>(r1);
                        r1.i = r0;
                        r0 = new dji.midware.media.f.a;
                        r0.<init>();
                        r1.j = r0;
                        r0 = new dji.midware.media.f.a;
                        r0.<init>();
                        r1.k = r0;
                        r0 = new dji.midware.media.f.a;
                        r0.<init>();
                        r1.l = r0;
                        r0 = new dji.midware.media.f.e$b$a$a$a$c;
                        r0.<init>(r1);
                        r1.m = r0;
                        r0 = new dji.midware.media.f.e$b$a$a$a$a;
                        r0.<init>(r1);
                        r1.n = r0;
                        return;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.<init>(dji.midware.media.f.e$b$a$a):void");
                    }

                    public boolean a(byte[] r8, int r9) {
                        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                        /*
                        r7 = this;
                        r6 = 0;
                        r5 = 4;
                        r0 = dji.midware.media.f.c.b(r8, r6);
                        r7.f = r0;
                        r0 = dji.midware.media.f.c.b(r8, r5, r5);
                        r7.g = r0;
                        r0 = 8;
                    L_0x0010:
                        r1 = r7.f;
                        if (r0 >= r1) goto L_0x007c;
                    L_0x0014:
                        r1 = dji.midware.media.f.c.b(r8, r0);
                        r0 = r0 + 4;
                        r2 = dji.midware.media.f.c.b(r8, r0, r5);
                        r0 = r0 + 4;
                        r3 = new byte[r1];
                        r0 = r0 + -8;
                        java.lang.System.arraycopy(r8, r0, r3, r6, r1);
                        r4 = dji.midware.media.f.b.stsd;
                        r4 = r4.a(r2);
                        if (r4 == 0) goto L_0x0036;
                    L_0x002f:
                        r2 = r7.i;
                        r2.a(r3, r0);
                    L_0x0034:
                        r0 = r0 + r1;
                        goto L_0x0010;
                    L_0x0036:
                        r4 = dji.midware.media.f.b.stts;
                        r4 = r4.a(r2);
                        if (r4 == 0) goto L_0x0044;
                    L_0x003e:
                        r2 = r7.j;
                        r2.a(r3, r0);
                        goto L_0x0034;
                    L_0x0044:
                        r4 = dji.midware.media.f.b.ctts;
                        r4 = r4.a(r2);
                        if (r4 == 0) goto L_0x0052;
                    L_0x004c:
                        r2 = r7.k;
                        r2.a(r3, r0);
                        goto L_0x0034;
                    L_0x0052:
                        r4 = dji.midware.media.f.b.stsc;
                        r4 = r4.a(r2);
                        if (r4 == 0) goto L_0x0060;
                    L_0x005a:
                        r2 = r7.l;
                        r2.a(r3, r0);
                        goto L_0x0034;
                    L_0x0060:
                        r4 = dji.midware.media.f.b.stsz;
                        r4 = r4.a(r2);
                        if (r4 == 0) goto L_0x006e;
                    L_0x0068:
                        r2 = r7.m;
                        r2.a(r3, r0);
                        goto L_0x0034;
                    L_0x006e:
                        r4 = dji.midware.media.f.b.stco;
                        r2 = r4.a(r2);
                        if (r2 == 0) goto L_0x0034;
                    L_0x0076:
                        r2 = r7.n;
                        r2.a(r3, r0);
                        goto L_0x0034;
                    L_0x007c:
                        r0 = 1;
                        return r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a.a(byte[], int):boolean");
                    }
                }

                public a(dji.midware.media.f.e.b.a r4) {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                    /*
                    r3 = this;
                    r3.l = r4;
                    r3.<init>();
                    r0 = dji.midware.media.f.b.minf;
                    r3.h = r0;
                    r0 = new dji.midware.media.f.a;
                    r1 = dji.midware.media.f.b.vmhd;
                    r2 = 20;
                    r0.<init>(r1, r2);
                    r3.i = r0;
                    r0 = new dji.midware.media.f.a;
                    r1 = dji.midware.media.f.b.dinf;
                    r2 = 36;
                    r0.<init>(r1, r2);
                    r3.j = r0;
                    r0 = new dji.midware.media.f.e$b$a$a$a;
                    r0.<init>(r3);
                    r3.k = r0;
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.<init>(dji.midware.media.f.e$b$a):void");
                }

                public boolean a(byte[] r11, int r12) {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                    /*
                    r10 = this;
                    r9 = 4;
                    r1 = 0;
                    r0 = dji.midware.media.f.c.b(r11, r1);
                    r10.f = r0;
                    r0 = dji.midware.media.f.c.b(r11, r9, r9);
                    r10.g = r0;
                    r0 = 8;
                    r2 = r1;
                    r3 = r1;
                    r4 = r0;
                    r0 = r1;
                L_0x0014:
                    r5 = r11.length;
                    if (r4 >= r5) goto L_0x0058;
                L_0x0017:
                    r5 = dji.midware.media.f.c.b(r11, r4);
                    r4 = r4 + 4;
                    r6 = dji.midware.media.f.c.b(r11, r4, r9);
                    r4 = r4 + 4;
                    r7 = new byte[r5];
                    r4 = r4 + -8;
                    java.lang.System.arraycopy(r11, r4, r7, r1, r5);
                    r8 = dji.midware.media.f.b.vmhd;
                    r8 = r8.a(r6);
                    if (r8 == 0) goto L_0x003a;
                L_0x0032:
                    r3 = r10.i;
                    r3 = r3.a(r7, r5);
                L_0x0038:
                    r4 = r4 + r5;
                    goto L_0x0014;
                L_0x003a:
                    r8 = dji.midware.media.f.b.dinf;
                    r8 = r8.a(r6);
                    if (r8 == 0) goto L_0x0049;
                L_0x0042:
                    r2 = r10.j;
                    r2 = r2.a(r7, r5);
                    goto L_0x0038;
                L_0x0049:
                    r8 = dji.midware.media.f.b.stbl;
                    r6 = r8.a(r6);
                    if (r6 == 0) goto L_0x0038;
                L_0x0051:
                    r0 = r10.k;
                    r0 = r0.a(r7, r5);
                    goto L_0x0038;
                L_0x0058:
                    if (r3 == 0) goto L_0x0060;
                L_0x005a:
                    if (r2 == 0) goto L_0x0060;
                L_0x005c:
                    if (r0 == 0) goto L_0x0060;
                L_0x005e:
                    r1 = 1;
                L_0x005f:
                    return r1;
                L_0x0060:
                    r4 = a;
                    r5 = new java.lang.StringBuilder;
                    r5.<init>();
                    r6 = "minf parse error";
                    r5 = r5.append(r6);
                    r3 = r5.append(r3);
                    r2 = r3.append(r2);
                    r0 = r2.append(r0);
                    r0 = r0.toString();
                    android.util.Log.e(r4, r0);
                    goto L_0x005f;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a.a(byte[], int):boolean");
                }
            }

            public a(dji.midware.media.f.e.b r4) {
                /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.checkInstructions(MethodNode.java:122)
	at jadx.core.dex.visitors.blocksmaker.BlockSplitter.visit(BlockSplitter.java:41)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r3 = this;
                r3.l = r4;
                r3.<init>();
                r0 = dji.midware.media.f.b.mdia;
                r3.h = r0;
                r0 = new dji.midware.media.f.a;
                r1 = dji.midware.media.f.b.mdhd;
                r2 = 32;
                r0.<init>(r1, r2);
                r3.i = r0;
                r0 = new dji.midware.media.f.a;
                r1 = dji.midware.media.f.b.hdlr;
                r2 = 49;
                r0.<init>(r1, r2);
                r3.j = r0;
                r0 = new dji.midware.media.f.e$b$a$a;
                r0.<init>(r3);
                r3.k = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.<init>(dji.midware.media.f.e$b):void");
            }

            public boolean a(byte[] r11, int r12) {
                /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.checkInstructions(MethodNode.java:122)
	at jadx.core.dex.visitors.blocksmaker.BlockSplitter.visit(BlockSplitter.java:41)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r10 = this;
                r9 = 4;
                r1 = 0;
                r0 = dji.midware.media.f.c.b(r11, r1);
                r10.f = r0;
                r0 = dji.midware.media.f.c.b(r11, r9, r9);
                r10.g = r0;
                r0 = 8;
                r2 = r1;
                r3 = r1;
                r4 = r0;
                r0 = r1;
            L_0x0014:
                r5 = r11.length;
                if (r4 >= r5) goto L_0x0058;
            L_0x0017:
                r5 = dji.midware.media.f.c.b(r11, r4);
                r4 = r4 + 4;
                r6 = dji.midware.media.f.c.b(r11, r4, r9);
                r4 = r4 + 4;
                r7 = new byte[r5];
                r4 = r4 + -8;
                java.lang.System.arraycopy(r11, r4, r7, r1, r5);
                r8 = dji.midware.media.f.b.mdhd;
                r8 = r8.a(r6);
                if (r8 == 0) goto L_0x003a;
            L_0x0032:
                r3 = r10.i;
                r3 = r3.a(r7, r4);
            L_0x0038:
                r4 = r4 + r5;
                goto L_0x0014;
            L_0x003a:
                r8 = dji.midware.media.f.b.hdlr;
                r8 = r8.a(r6);
                if (r8 == 0) goto L_0x0049;
            L_0x0042:
                r2 = r10.j;
                r2 = r2.a(r7, r4);
                goto L_0x0038;
            L_0x0049:
                r8 = dji.midware.media.f.b.minf;
                r6 = r8.a(r6);
                if (r6 == 0) goto L_0x0038;
            L_0x0051:
                r0 = r10.k;
                r0 = r0.a(r7, r4);
                goto L_0x0038;
            L_0x0058:
                if (r3 == 0) goto L_0x0060;
            L_0x005a:
                if (r2 == 0) goto L_0x0060;
            L_0x005c:
                if (r0 == 0) goto L_0x0060;
            L_0x005e:
                r1 = 1;
            L_0x005f:
                return r1;
            L_0x0060:
                r0 = a;
                r2 = "mdia parse error";
                android.util.Log.e(r0, r2);
                goto L_0x005f;
                */
                throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a.a(byte[], int):boolean");
            }
        }

        public class b extends a {
            public final int h;
            public final b i;
            public byte j;
            public byte[] k;
            public int l;
            public int m;
            public int n;
            public int o;
            public int p;
            public byte[] q;
            public short r;
            public short s;
            public short t;
            public short u;
            public byte[] v;
            public int w;
            public int x;
            final /* synthetic */ b y;

            public b(dji.midware.media.f.e.b r2) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r1 = this;
                r1.y = r2;
                r1.<init>();
                r0 = 92;
                r1.h = r0;
                r0 = dji.midware.media.f.b.tkhd;
                r1.i = r0;
                r0 = 3;
                r0 = new byte[r0];
                r1.k = r0;
                r0 = 8;
                r0 = new byte[r0];
                r1.q = r0;
                r0 = 0;
                r1.s = r0;
                r0 = 36;
                r0 = new byte[r0];
                r1.v = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.b.<init>(dji.midware.media.f.e$b):void");
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public boolean a(byte[] r6, int r7) {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:120)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r5 = this;
                r4 = 8;
                r2 = 4;
                r0 = 0;
                r5.e = r7;
                r1 = dji.midware.media.f.c.b(r6, r0);
                r5.f = r1;
                r1 = dji.midware.media.f.c.b(r6, r2, r2);
                r5.g = r1;
                r1 = r5.f;
                r2 = 92;
                if (r1 != r2) goto L_0x0022;
            L_0x0018:
                r1 = r5.i;
                r2 = r5.g;
                r1 = r1.a(r2);
                if (r1 != 0) goto L_0x002a;
            L_0x0022:
                r1 = a;
                r2 = "tkhd parse error";
                android.util.Log.e(r1, r2);
            L_0x0029:
                return r0;
            L_0x002a:
                r1 = r6[r4];
                r5.j = r1;
                r1 = 9;
                r2 = r5.k;
                r3 = 3;
                java.lang.System.arraycopy(r6, r1, r2, r0, r3);
                r1 = 12;
                r1 = dji.midware.media.f.c.b(r6, r1);
                r5.l = r1;
                r1 = 16;
                r1 = dji.midware.media.f.c.b(r6, r1);
                r5.m = r1;
                r1 = 20;
                r1 = dji.midware.media.f.c.b(r6, r1);
                r5.n = r1;
                r1 = 24;
                r1 = dji.midware.media.f.c.b(r6, r1);
                r5.o = r1;
                r1 = 28;
                r1 = dji.midware.media.f.c.b(r6, r1);
                r5.p = r1;
                r1 = 32;
                r2 = r5.q;
                java.lang.System.arraycopy(r6, r1, r2, r0, r4);
                r1 = 40;
                r1 = dji.midware.media.f.c.a(r6, r1);
                r5.r = r1;
                r1 = 42;
                r1 = dji.midware.media.f.c.a(r6, r1);
                r5.s = r1;
                r1 = 44;
                r1 = dji.midware.media.f.c.a(r6, r1);
                r5.t = r1;
                r1 = 46;
                r1 = dji.midware.media.f.c.a(r6, r1);
                r5.u = r1;
                r1 = 48;
                r2 = r5.v;
                r3 = 36;
                java.lang.System.arraycopy(r6, r1, r2, r0, r3);
                r0 = 84;
                r0 = dji.midware.media.f.c.b(r6, r0);
                r0 >>= 16;
                r5.w = r0;
                r0 = 88;
                r0 = dji.midware.media.f.c.b(r6, r0);
                r0 >>= 16;
                r5.x = r0;
                r0 = 1;
                goto L_0x0029;
                */
                throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.b.a(byte[], int):boolean");
            }
        }

        public b(dji.midware.media.f.e r4) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.checkInstructions(MethodNode.java:122)
	at jadx.core.dex.visitors.blocksmaker.BlockSplitter.visit(BlockSplitter.java:41)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
            /*
            r3 = this;
            r3.l = r4;
            r3.<init>();
            r0 = dji.midware.media.f.b.trak;
            r3.h = r0;
            r0 = new dji.midware.media.f.e$b$b;
            r0.<init>(r3);
            r3.i = r0;
            r0 = new dji.midware.media.f.a;
            r1 = dji.midware.media.f.b.edts;
            r2 = 36;
            r0.<init>(r1, r2);
            r3.j = r0;
            r0 = new dji.midware.media.f.e$b$a;
            r0.<init>(r3);
            r3.k = r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.<init>(dji.midware.media.f.e):void");
        }

        public boolean a(byte[] r11, int r12) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.nodes.MethodNode.checkInstructions(MethodNode.java:122)
	at jadx.core.dex.visitors.blocksmaker.BlockSplitter.visit(BlockSplitter.java:41)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
            /*
            r10 = this;
            r9 = 4;
            r1 = 0;
            r10.d = r11;
            r10.e = r12;
            r0 = dji.midware.media.f.c.b(r11, r1);
            r10.f = r0;
            r0 = dji.midware.media.f.c.b(r11, r9, r9);
            r10.g = r0;
            r0 = 8;
            r2 = r1;
            r3 = r1;
            r4 = r0;
            r0 = r1;
        L_0x0018:
            r5 = r11.length;
            if (r4 >= r5) goto L_0x005c;
        L_0x001b:
            r5 = dji.midware.media.f.c.b(r11, r4);
            r4 = r4 + 4;
            r6 = dji.midware.media.f.c.b(r11, r4, r9);
            r4 = r4 + 4;
            r7 = new byte[r5];
            r4 = r4 + -8;
            java.lang.System.arraycopy(r11, r4, r7, r1, r5);
            r8 = dji.midware.media.f.b.tkhd;
            r8 = r8.a(r6);
            if (r8 == 0) goto L_0x003e;
        L_0x0036:
            r3 = r10.i;
            r3 = r3.a(r7, r4);
        L_0x003c:
            r4 = r4 + r5;
            goto L_0x0018;
        L_0x003e:
            r8 = dji.midware.media.f.b.edts;
            r8 = r8.a(r6);
            if (r8 == 0) goto L_0x004d;
        L_0x0046:
            r2 = r10.j;
            r2 = r2.a(r7, r4);
            goto L_0x003c;
        L_0x004d:
            r8 = dji.midware.media.f.b.mdia;
            r6 = r8.a(r6);
            if (r6 == 0) goto L_0x003c;
        L_0x0055:
            r0 = r10.k;
            r0 = r0.a(r7, r4);
            goto L_0x003c;
        L_0x005c:
            if (r3 == 0) goto L_0x0064;
        L_0x005e:
            if (r2 == 0) goto L_0x0064;
        L_0x0060:
            if (r0 == 0) goto L_0x0064;
        L_0x0062:
            r1 = 1;
        L_0x0063:
            return r1;
        L_0x0064:
            r0 = a;
            r2 = "trak parse error";
            android.util.Log.e(r0, r2);
            goto L_0x0063;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.f.e.b.a(byte[], int):boolean");
        }
    }

    public class c extends a {
        public final int h = 2048;
        public final b i = b.udta;
        final /* synthetic */ e j;

        public c(e eVar) {
            this.j = eVar;
        }

        public boolean a(byte[] bArr, int i) {
            this.e = i;
            this.f = c.b(bArr, 0);
            this.g = c.b(bArr, 4, 4);
            if (this.f == 2048 && this.i.a(this.g)) {
                this.d = bArr;
                return true;
            }
            Log.e(a, "udta parse error");
            return false;
        }
    }

    public boolean a(byte[] bArr, int i) {
        this.d = bArr;
        this.e = i;
        this.f = c.b(bArr, 0);
        this.g = c.b(bArr, 4, 4);
        boolean z = false;
        boolean z2 = false;
        int i2 = 8;
        boolean z3 = false;
        while (i2 < bArr.length) {
            int b = c.b(bArr, i2);
            i2 += 4;
            String b2 = c.b(bArr, i2, 4);
            Object obj = new byte[b];
            i2 = (i2 + 4) - 8;
            System.arraycopy(bArr, i2, obj, 0, b);
            if (b.mvhd.a(b2)) {
                z2 = this.h.a(obj, i2);
            } else if (b.udta.a(b2)) {
                z = this.i.a(obj, i2);
            } else if (b.trak.a(b2)) {
                z3 = this.j.a(obj, i2);
            }
            i2 += b;
        }
        if (z2 && r0 && r2) {
            return true;
        }
        return false;
    }
}
