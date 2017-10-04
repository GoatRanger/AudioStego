package it.sauronsoftware.ftp4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

class FTPDataTransferServer implements FTPDataTransferConnectionProvider, Runnable {
    private IOException exception;
    private ServerSocket serverSocket = null;
    private Socket socket;
    private Thread thread;

    public FTPDataTransferServer() throws FTPDataTransferException {
        int parseInt;
        int parseInt2;
        Object obj;
        Object obj2 = null;
        String property = System.getProperty(FTPKeys.ACTIVE_DT_PORT_RANGE);
        if (property != null) {
            Object obj3;
            int i;
            StringTokenizer stringTokenizer = new StringTokenizer(property, "-");
            if (stringTokenizer.countTokens() == 2) {
                String nextToken = stringTokenizer.nextToken();
                String nextToken2 = stringTokenizer.nextToken();
                try {
                    parseInt = Integer.parseInt(nextToken);
                } catch (NumberFormatException e) {
                    parseInt = 0;
                }
                try {
                    parseInt2 = Integer.parseInt(nextToken2);
                } catch (NumberFormatException e2) {
                    parseInt2 = 0;
                }
                if (parseInt > 0 && r0 > 0 && r0 >= parseInt) {
                    obj3 = 1;
                    obj = 1;
                    if (obj3 == null) {
                        System.err.println(new StringBuffer().append("WARNING: invalid value \"").append(property).append("\" for the ").append(FTPKeys.ACTIVE_DT_PORT_RANGE).append(" system property. The value should ").append("be in the start-stop form, with ").append("start > 0, stop > 0 and start <= stop.").toString());
                    }
                    i = parseInt2;
                    parseInt2 = parseInt;
                    parseInt = i;
                }
            }
            obj3 = null;
            parseInt2 = 0;
            parseInt = 0;
            obj = null;
            if (obj3 == null) {
                System.err.println(new StringBuffer().append("WARNING: invalid value \"").append(property).append("\" for the ").append(FTPKeys.ACTIVE_DT_PORT_RANGE).append(" system property. The value should ").append("be in the start-stop form, with ").append("start > 0, stop > 0 and start <= stop.").toString());
            }
            i = parseInt2;
            parseInt2 = parseInt;
            parseInt = i;
        } else {
            parseInt = 0;
            parseInt2 = 0;
            obj = null;
        }
        if (obj != null) {
            ArrayList arrayList = new ArrayList();
            for (parseInt2 = 
/*
Method generation error in method: it.sauronsoftware.ftp4j.FTPDataTransferServer.<init>():void
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r0_11 'parseInt2' int) = (r0_9 'parseInt2' int), (r0_30 'parseInt2' int) binds: {(r0_9 'parseInt2' int)=B:17:0x006e, (r0_30 'parseInt2' int)=B:47:0x0116} in method: it.sauronsoftware.ftp4j.FTPDataTransferServer.<init>():void
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:183)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:328)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:265)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:228)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:118)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:83)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:19)
	at jadx.core.ProcessClass.process(ProcessClass.java:43)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:530)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:514)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 22 more

*/

            public int getPort() {
                return this.serverSocket.getLocalPort();
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r5 = this;
                r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
                r1 = "ftp4j.activeDataTransfer.acceptTimeout";
                r3 = java.lang.System.getProperty(r1);
                if (r3 == 0) goto L_0x0046;
            L_0x000a:
                r1 = 0;
                r2 = java.lang.Integer.parseInt(r3);	 Catch:{ NumberFormatException -> 0x0060 }
            L_0x000f:
                if (r2 < 0) goto L_0x0014;
            L_0x0011:
                r0 = 1;
                r1 = r0;
                r0 = r2;
            L_0x0014:
                if (r1 != 0) goto L_0x0046;
            L_0x0016:
                r1 = java.lang.System.err;
                r2 = new java.lang.StringBuffer;
                r2.<init>();
                r4 = "WARNING: invalid value \"";
                r2 = r2.append(r4);
                r2 = r2.append(r3);
                r3 = "\" for the ";
                r2 = r2.append(r3);
                r3 = "ftp4j.activeDataTransfer.acceptTimeout";
                r2 = r2.append(r3);
                r3 = " system property. The value should ";
                r2 = r2.append(r3);
                r3 = "be an integer greater or equal to 0.";
                r2 = r2.append(r3);
                r2 = r2.toString();
                r1.println(r2);
            L_0x0046:
                r1 = r5.serverSocket;	 Catch:{ IOException -> 0x0063 }
                r1.setSoTimeout(r0);	 Catch:{ IOException -> 0x0063 }
                r0 = r5.serverSocket;	 Catch:{ IOException -> 0x0063 }
                r0 = r0.accept();	 Catch:{ IOException -> 0x0063 }
                r5.socket = r0;	 Catch:{ IOException -> 0x0063 }
                r0 = r5.socket;	 Catch:{ IOException -> 0x0063 }
                r1 = 524288; // 0x80000 float:7.34684E-40 double:2.590327E-318;
                r0.setSendBufferSize(r1);	 Catch:{ IOException -> 0x0063 }
                r0 = r5.serverSocket;	 Catch:{ IOException -> 0x006c }
                r0.close();	 Catch:{ IOException -> 0x006c }
            L_0x005f:
                return;
            L_0x0060:
                r2 = move-exception;
                r2 = -1;
                goto L_0x000f;
            L_0x0063:
                r0 = move-exception;
                r5.exception = r0;	 Catch:{ all -> 0x006e }
                r0 = r5.serverSocket;	 Catch:{ IOException -> 0x006c }
                r0.close();	 Catch:{ IOException -> 0x006c }
                goto L_0x005f;
            L_0x006c:
                r0 = move-exception;
                goto L_0x005f;
            L_0x006e:
                r0 = move-exception;
                r1 = r5.serverSocket;	 Catch:{ IOException -> 0x0075 }
                r1.close();	 Catch:{ IOException -> 0x0075 }
            L_0x0074:
                throw r0;
            L_0x0075:
                r1 = move-exception;
                goto L_0x0074;
                */
                throw new UnsupportedOperationException("Method not decompiled: it.sauronsoftware.ftp4j.FTPDataTransferServer.run():void");
            }

            public void dispose() {
                if (this.serverSocket != null) {
                    try {
                        this.serverSocket.close();
                    } catch (IOException e) {
                    }
                }
            }

            public Socket openDataTransferConnection() throws FTPDataTransferException {
                if (this.socket == null && this.exception == null) {
                    try {
                        this.thread.join();
                    } catch (Exception e) {
                    }
                }
                if (this.exception != null) {
                    throw new FTPDataTransferException("Cannot receive the incoming connection", this.exception);
                } else if (this.socket != null) {
                    return this.socket;
                } else {
                    throw new FTPDataTransferException("No socket available");
                }
            }
        }
