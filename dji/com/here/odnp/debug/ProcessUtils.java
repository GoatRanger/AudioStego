package com.here.odnp.debug;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.util.OdnpIOUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProcessUtils {
    private static final int COLUMN_INDEX_NAME = 8;
    private static final int COLUMN_INDEX_PID = 1;
    private static final int COLUMN_INDEX_USER = 0;
    private static final String TAG = "odnp.debug.ProcessUtils";

    public static class ProcessInfo {
        public String name;
        public int pid;
        public String user;

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("user: ");
            stringBuilder.append(this.user);
            stringBuilder.append(", pid: ");
            stringBuilder.append(this.pid);
            stringBuilder.append(", name: ");
            stringBuilder.append(this.name);
            return stringBuilder.toString();
        }
    }

    public static ProcessInfo[] getProcess(String str, String str2) {
        Process start;
        Closeable bufferedReader;
        Closeable closeable;
        Throwable th;
        Throwable th2;
        Process process = null;
        List linkedList = new LinkedList();
        try {
            start = new ProcessBuilder(new String[]{"ps"}).redirectErrorStream(true).start();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream(), Charset.defaultCharset()));
                try {
                    validatePsHeader(bufferedReader);
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        try {
                            ProcessInfo parsePsLine = parsePsLine(readLine);
                            if ((str == null || str.matches(parsePsLine.user)) && (str2 == null || str2.matches(parsePsLine.name))) {
                                linkedList.add(parsePsLine);
                            }
                        } catch (Exception e) {
                        }
                    }
                    OdnpIOUtils.close(bufferedReader);
                    if (start != null) {
                        start.destroy();
                    }
                } catch (IOException e2) {
                    closeable = bufferedReader;
                    OdnpIOUtils.close(closeable);
                    if (start != null) {
                        start.destroy();
                    }
                    return (ProcessInfo[]) linkedList.toArray(new ProcessInfo[linkedList.size()]);
                } catch (Throwable th3) {
                    th = th3;
                    process = start;
                    th2 = th;
                    OdnpIOUtils.close(bufferedReader);
                    if (process != null) {
                        process.destroy();
                    }
                    throw th2;
                }
            } catch (IOException e3) {
                OdnpIOUtils.close(closeable);
                if (start != null) {
                    start.destroy();
                }
                return (ProcessInfo[]) linkedList.toArray(new ProcessInfo[linkedList.size()]);
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = null;
                process = start;
                th2 = th;
                OdnpIOUtils.close(bufferedReader);
                if (process != null) {
                    process.destroy();
                }
                throw th2;
            }
        } catch (IOException e4) {
            start = null;
            OdnpIOUtils.close(closeable);
            if (start != null) {
                start.destroy();
            }
            return (ProcessInfo[]) linkedList.toArray(new ProcessInfo[linkedList.size()]);
        } catch (Throwable th5) {
            th2 = th5;
            bufferedReader = null;
            OdnpIOUtils.close(bufferedReader);
            if (process != null) {
                process.destroy();
            }
            throw th2;
        }
        return (ProcessInfo[]) linkedList.toArray(new ProcessInfo[linkedList.size()]);
    }

    private static void validatePsHeader(BufferedReader bufferedReader) throws IOException {
        String readLine = bufferedReader.readLine();
        if (readLine == null) {
            throw new IOException("Could not read ps command header");
        }
        String[] split = readLine.split("\\s+");
        if (split.length != 8 || !split[0].equalsIgnoreCase("user") || !split[1].equalsIgnoreCase("pid") || !split[split.length - 1].equalsIgnoreCase("name")) {
            throw new IOException("Unexpected ps command header columns: '" + Arrays.toString(split) + "'");
        }
    }

    private static ProcessInfo parsePsLine(String str) throws IOException {
        int i = 9;
        ProcessInfo processInfo = new ProcessInfo();
        String[] split = str.split("\\s+");
        if (split.length < 9) {
            throw new IOException("Could not parse ps command line: '" + str + "'");
        }
        processInfo.user = split[0];
        try {
            processInfo.pid = Integer.parseInt(split[1]);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(split[8]);
            while (i < split.length) {
                stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                stringBuilder.append(split[i]);
                i++;
            }
            processInfo.name = stringBuilder.toString();
            return processInfo;
        } catch (Throwable e) {
            throw new IOException("Could not parse PID", e);
        }
    }
}
