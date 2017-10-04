package it.sauronsoftware.ftp4j.listparsers;

import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPListParseException;
import it.sauronsoftware.ftp4j.FTPListParser;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnixListParser implements FTPListParser {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("MMM dd yyyy HH:mm", Locale.US);
    private static final Pattern PATTERN = Pattern.compile("^([dl\\-])[r\\-][w\\-][xSs\\-][r\\-][w\\-][xSs\\-][r\\-][w\\-][xTt\\-]\\s+(?:\\d+\\s+)?\\S+\\s*\\S+\\s+(\\d+)\\s+(?:(\\w{3})\\s+(\\d{1,2}))\\s+(?:(\\d{4})|(?:(\\d{1,2}):(\\d{1,2})))\\s+([^\\\\*?\"<>|]+)(?: -> ([^\\\\*?\"<>|]+))?$");

    public FTPFile[] parse(String[] strArr) throws FTPListParseException {
        int length = strArr.length;
        if (length == 0) {
            return new FTPFile[0];
        }
        if (strArr[0].startsWith("total")) {
            length--;
            String[] strArr2 = new String[length];
            for (int i = 0; i < length; i++) {
                strArr2[i] = strArr[i + 1];
            }
            strArr = strArr2;
        }
        Calendar instance = Calendar.getInstance();
        int i2 = instance.get(1);
        FTPFile[] fTPFileArr = new FTPFile[length];
        int i3 = 0;
        while (i3 < length) {
            Matcher matcher = PATTERN.matcher(strArr[i3]);
            if (matcher.matches()) {
                fTPFileArr[i3] = new FTPFile();
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                String group3 = matcher.group(3);
                String group4 = matcher.group(4);
                String group5 = matcher.group(5);
                String group6 = matcher.group(6);
                String group7 = matcher.group(7);
                String group8 = matcher.group(8);
                String group9 = matcher.group(9);
                if (group.equals("-")) {
                    fTPFileArr[i3].setType(0);
                } else if (group.equals("d")) {
                    fTPFileArr[i3].setType(1);
                } else if (group.equals("l")) {
                    fTPFileArr[i3].setType(2);
                    fTPFileArr[i3].setLink(group9);
                } else {
                    throw new FTPListParseException();
                }
                try {
                    Object obj;
                    fTPFileArr[i3].setSize(Long.parseLong(group2));
                    if (group4.length() == 1) {
                        group4 = new StringBuffer().append("0").append(group4).toString();
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(group3);
                    stringBuffer.append(' ');
                    stringBuffer.append(group4);
                    stringBuffer.append(' ');
                    if (group5 == null) {
                        stringBuffer.append(i2);
                        obj = 1;
                    } else {
                        stringBuffer.append(group5);
                        obj = null;
                    }
                    stringBuffer.append(' ');
                    if (group6 == null || group7 == null) {
                        stringBuffer.append("00:00");
                    } else {
                        if (group6.length() == 1) {
                            group6 = new StringBuffer().append("0").append(group6).toString();
                        }
                        if (group7.length() == 1) {
                            group7 = new StringBuffer().append("0").append(group7).toString();
                        }
                        stringBuffer.append(group6);
                        stringBuffer.append(':');
                        stringBuffer.append(group7);
                    }
                    try {
                        Date parse;
                        Date time;
                        synchronized (DATE_FORMAT) {
                            parse = DATE_FORMAT.parse(stringBuffer.toString());
                        }
                        if (obj != null) {
                            Calendar instance2 = Calendar.getInstance();
                            instance2.setTime(parse);
                            if (instance2.after(instance) && instance2.getTimeInMillis() - instance.getTimeInMillis() > 86400000) {
                                instance2.set(1, i2 - 1);
                                time = instance2.getTime();
                                fTPFileArr[i3].setModifiedDate(time);
                                fTPFileArr[i3].setName(group8);
                                i3++;
                            }
                        }
                        time = parse;
                        fTPFileArr[i3].setModifiedDate(time);
                        fTPFileArr[i3].setName(group8);
                        i3++;
                    } catch (ParseException e) {
                        throw new FTPListParseException();
                    }
                } catch (Throwable th) {
                    FTPListParseException fTPListParseException = new FTPListParseException();
                }
            } else {
                throw new FTPListParseException();
            }
        }
        return fTPFileArr;
    }
}
