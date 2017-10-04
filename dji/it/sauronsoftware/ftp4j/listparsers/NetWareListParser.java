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

public class NetWareListParser implements FTPListParser {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("MMM dd yyyy HH:mm", Locale.US);
    private static final Pattern PATTERN = Pattern.compile("^(d|-)\\s+\\[.{8}\\]\\s+\\S+\\s+(\\d+)\\s+(?:(\\w{3})\\s+(\\d{1,2}))\\s+(?:(\\d{4})|(?:(\\d{1,2}):(\\d{1,2})))\\s+([^\\\\/*?\"<>|]+)$");

    public FTPFile[] parse(String[] strArr) throws FTPListParseException {
        int length = strArr.length;
        Calendar instance = Calendar.getInstance();
        int i = instance.get(1);
        FTPFile[] fTPFileArr = new FTPFile[length];
        int i2 = 0;
        while (i2 < length) {
            Matcher matcher = PATTERN.matcher(strArr[i2]);
            if (matcher.matches()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                String group3 = matcher.group(3);
                String group4 = matcher.group(4);
                String group5 = matcher.group(5);
                String group6 = matcher.group(6);
                String group7 = matcher.group(7);
                String group8 = matcher.group(8);
                fTPFileArr[i2] = new FTPFile();
                if (group.equals("-")) {
                    fTPFileArr[i2].setType(0);
                } else if (group.equals("d")) {
                    fTPFileArr[i2].setType(1);
                } else {
                    throw new FTPListParseException();
                }
                try {
                    Object obj;
                    fTPFileArr[i2].setSize(Long.parseLong(group2));
                    if (group4.length() == 1) {
                        group4 = new StringBuffer().append("0").append(group4).toString();
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(group3);
                    stringBuffer.append(' ');
                    stringBuffer.append(group4);
                    stringBuffer.append(' ');
                    if (group5 == null) {
                        stringBuffer.append(i);
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
                                instance2.set(1, i - 1);
                                time = instance2.getTime();
                                fTPFileArr[i2].setModifiedDate(time);
                                fTPFileArr[i2].setName(group8);
                                i2++;
                            }
                        }
                        time = parse;
                        fTPFileArr[i2].setModifiedDate(time);
                        fTPFileArr[i2].setName(group8);
                        i2++;
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
