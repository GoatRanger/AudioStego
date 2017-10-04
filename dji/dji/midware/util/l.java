package dji.midware.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

public class l {

    private static class a extends Thread {
        private final Process a;
        private Integer b;

        private a(Process process) {
            this.a = process;
        }

        public void run() {
            try {
                this.b = Integer.valueOf(this.a.waitFor());
            } catch (InterruptedException e) {
            }
        }
    }

    public static int a(String str, long j) throws IOException, InterruptedException, TimeoutException {
        Process exec = Runtime.getRuntime().exec(str);
        a aVar = new a(exec);
        aVar.start();
        try {
            aVar.join(j);
            if (aVar.b != null) {
                int intValue = aVar.b.intValue();
                exec.destroy();
                return intValue;
            }
            throw new TimeoutException();
        } catch (InterruptedException e) {
            aVar.interrupt();
            Thread.currentThread().interrupt();
            throw e;
        } catch (Throwable th) {
            exec.destroy();
        }
    }

    public static boolean a(String str, int i) throws UnknownHostException, IOException {
        return InetAddress.getByName(str).isReachable(i);
    }
}
