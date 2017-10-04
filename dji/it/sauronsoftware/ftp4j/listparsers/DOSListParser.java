package it.sauronsoftware.ftp4j.listparsers;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.usercenter.protocol.d;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPListParseException;
import it.sauronsoftware.ftp4j.FTPListParser;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DOSListParser implements FTPListParser {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yy hh:mm a");
    private static final Pattern PATTERN = Pattern.compile("^(\\d{2})-(\\d{2})-(\\d{2})\\s+(\\d{2}):(\\d{2})(AM|PM)\\s+(<DIR>|\\d+)\\s+([^\\\\/*?\"<>|]+)$");

    public FTPFile[] parse(String[] strArr) throws FTPListParseException {
        int length = strArr.length;
        FTPFile[] fTPFileArr = new FTPFile[length];
        int i = 0;
        while (i < length) {
            Matcher matcher = PATTERN.matcher(strArr[i]);
            if (matcher.matches()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                String group3 = matcher.group(3);
                String group4 = matcher.group(4);
                String group5 = matcher.group(5);
                String group6 = matcher.group(6);
                String group7 = matcher.group(7);
                String group8 = matcher.group(8);
                fTPFileArr[i] = new FTPFile();
                fTPFileArr[i].setName(group8);
                if (group7.equalsIgnoreCase("<DIR>")) {
                    fTPFileArr[i].setType(1);
                    fTPFileArr[i].setSize(0);
                } else {
                    try {
                        long parseLong = Long.parseLong(group7);
                        fTPFileArr[i].setType(0);
                        fTPFileArr[i].setSize(parseLong);
                    } catch (Throwable th) {
                        FTPListParseException fTPListParseException = new FTPListParseException();
                    }
                }
                group8 = new StringBuffer().append(group).append(d.t).append(group2).append(d.t).append(group3).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(group4).append(":").append(group5).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(group6).toString();
                try {
                    Date parse;
                    synchronized (DATE_FORMAT) {
                        parse = DATE_FORMAT.parse(group8);
                    }
                    fTPFileArr[i].setModifiedDate(parse);
                    i++;
                } catch (ParseException e) {
                    throw new FTPListParseException();
                }
            }
            throw new FTPListParseException();
        }
        return fTPFileArr;
    }
}
