package dji.thirdparty.c;

import com.alipay.sdk.c.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class p {
    static final Logger a = Logger.getLogger(p.class.getName());

    private p() {
    }

    public static e a(w wVar) {
        if (wVar != null) {
            return new r(wVar);
        }
        throw new IllegalArgumentException("source == null");
    }

    public static d a(v vVar) {
        if (vVar != null) {
            return new q(vVar);
        }
        throw new IllegalArgumentException("sink == null");
    }

    public static v a(OutputStream outputStream) {
        return a(outputStream, new x());
    }

    private static v a(final OutputStream outputStream, final x xVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        } else if (xVar != null) {
            return new v() {
                public void a_(c cVar, long j) throws IOException {
                    y.a(cVar.c, 0, j);
                    while (j > 0) {
                        xVar.g();
                        s sVar = cVar.b;
                        int min = (int) Math.min(j, (long) (sVar.e - sVar.d));
                        outputStream.write(sVar.c, sVar.d, min);
                        sVar.d += min;
                        j -= (long) min;
                        cVar.c -= (long) min;
                        if (sVar.d == sVar.e) {
                            cVar.b = sVar.a();
                            t.a(sVar);
                        }
                    }
                }

                public void flush() throws IOException {
                    outputStream.flush();
                }

                public void close() throws IOException {
                    outputStream.close();
                }

                public x a() {
                    return xVar;
                }

                public String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static v a(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        x c = c(socket);
        return c.a(a(socket.getOutputStream(), c));
    }

    public static w a(InputStream inputStream) {
        return a(inputStream, new x());
    }

    private static w a(final InputStream inputStream, final x xVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (xVar != null) {
            return new w() {
                public long a(c cVar, long j) throws IOException {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    } else if (j == 0) {
                        return 0;
                    } else {
                        try {
                            xVar.g();
                            s g = cVar.g(1);
                            int read = inputStream.read(g.c, g.e, (int) Math.min(j, (long) (8192 - g.e)));
                            if (read == -1) {
                                return -1;
                            }
                            g.e += read;
                            cVar.c += (long) read;
                            return (long) read;
                        } catch (AssertionError e) {
                            if (p.a(e)) {
                                throw new IOException(e);
                            }
                            throw e;
                        }
                    }
                }

                public void close() throws IOException {
                    inputStream.close();
                }

                public x a() {
                    return xVar;
                }

                public String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static w a(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    @IgnoreJRERequirement
    public static w a(Path path, OpenOption... openOptionArr) throws IOException {
        if (path != null) {
            return a(Files.newInputStream(path, openOptionArr));
        }
        throw new IllegalArgumentException("path == null");
    }

    public static v b(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static v c(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    @IgnoreJRERequirement
    public static v b(Path path, OpenOption... openOptionArr) throws IOException {
        if (path != null) {
            return a(Files.newOutputStream(path, openOptionArr));
        }
        throw new IllegalArgumentException("path == null");
    }

    public static w b(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        x c = c(socket);
        return c.a(a(socket.getInputStream(), c));
    }

    private static a c(final Socket socket) {
        return new a() {
            protected IOException a(IOException iOException) {
                IOException socketTimeoutException = new SocketTimeoutException(a.f);
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            protected void a() {
                try {
                    socket.close();
                } catch (Throwable e) {
                    p.a.log(Level.WARNING, "Failed to close timed out socket " + socket, e);
                } catch (AssertionError e2) {
                    if (p.a(e2)) {
                        p.a.log(Level.WARNING, "Failed to close timed out socket " + socket, e2);
                        return;
                    }
                    throw e2;
                }
            }
        };
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
