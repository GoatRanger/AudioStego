package com.google.api.client.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DateTime implements Serializable {
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final Pattern RFC3339_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?");
    private static final long serialVersionUID = 1;
    private final boolean dateOnly;
    private final int tzShift;
    private final long value;

    public DateTime(Date date, TimeZone timeZone) {
        this(false, date.getTime(), timeZone == null ? null : Integer.valueOf(timeZone.getOffset(date.getTime()) / 60000));
    }

    public DateTime(long j) {
        this(false, j, null);
    }

    public DateTime(Date date) {
        this(date.getTime());
    }

    public DateTime(long j, int i) {
        this(false, j, Integer.valueOf(i));
    }

    public DateTime(boolean z, long j, Integer num) {
        this.dateOnly = z;
        this.value = j;
        int offset = z ? 0 : num == null ? TimeZone.getDefault().getOffset(j) / 60000 : num.intValue();
        this.tzShift = offset;
    }

    public DateTime(String str) {
        DateTime parseRfc3339 = parseRfc3339(str);
        this.dateOnly = parseRfc3339.dateOnly;
        this.value = parseRfc3339.value;
        this.tzShift = parseRfc3339.tzShift;
    }

    public long getValue() {
        return this.value;
    }

    public boolean isDateOnly() {
        return this.dateOnly;
    }

    public int getTimeZoneShift() {
        return this.tzShift;
    }

    public String toStringRfc3339() {
        StringBuilder stringBuilder = new StringBuilder();
        Calendar gregorianCalendar = new GregorianCalendar(GMT);
        gregorianCalendar.setTimeInMillis(this.value + (((long) this.tzShift) * 60000));
        appendInt(stringBuilder, gregorianCalendar.get(1), 4);
        stringBuilder.append('-');
        appendInt(stringBuilder, gregorianCalendar.get(2) + 1, 2);
        stringBuilder.append('-');
        appendInt(stringBuilder, gregorianCalendar.get(5), 2);
        if (!this.dateOnly) {
            stringBuilder.append('T');
            appendInt(stringBuilder, gregorianCalendar.get(11), 2);
            stringBuilder.append(':');
            appendInt(stringBuilder, gregorianCalendar.get(12), 2);
            stringBuilder.append(':');
            appendInt(stringBuilder, gregorianCalendar.get(13), 2);
            if (gregorianCalendar.isSet(14)) {
                stringBuilder.append('.');
                appendInt(stringBuilder, gregorianCalendar.get(14), 3);
            }
            if (this.tzShift == 0) {
                stringBuilder.append('Z');
            } else {
                int i = this.tzShift;
                if (this.tzShift > 0) {
                    stringBuilder.append('+');
                } else {
                    stringBuilder.append('-');
                    i = -i;
                }
                int i2 = i / 60;
                i %= 60;
                appendInt(stringBuilder, i2, 2);
                stringBuilder.append(':');
                appendInt(stringBuilder, i, 2);
            }
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return toStringRfc3339();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DateTime)) {
            return false;
        }
        DateTime dateTime = (DateTime) obj;
        if (this.dateOnly == dateTime.dateOnly && this.value == dateTime.value && this.tzShift == dateTime.tzShift) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long[] jArr = new long[3];
        jArr[0] = this.value;
        jArr[1] = this.dateOnly ? 1 : 0;
        jArr[2] = (long) this.tzShift;
        return Arrays.hashCode(jArr);
    }

    public static DateTime parseRfc3339(String str) throws NumberFormatException {
        Matcher matcher = RFC3339_PATTERN.matcher(str);
        if (matcher.matches()) {
            Object obj;
            int parseInt = Integer.parseInt(matcher.group(1));
            int parseInt2 = Integer.parseInt(matcher.group(2)) - 1;
            int parseInt3 = Integer.parseInt(matcher.group(3));
            Object obj2 = matcher.group(4) != null ? 1 : null;
            String group = matcher.group(9);
            if (group != null) {
                obj = 1;
            } else {
                obj = null;
            }
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            if (obj == null || obj2 != null) {
                int parseInt4;
                Calendar gregorianCalendar;
                long timeInMillis;
                Integer num;
                boolean z;
                if (obj2 != null) {
                    i = Integer.parseInt(matcher.group(5));
                    i2 = Integer.parseInt(matcher.group(6));
                    i3 = Integer.parseInt(matcher.group(7));
                    if (matcher.group(8) != null) {
                        parseInt4 = Integer.parseInt(matcher.group(8).substring(1));
                        gregorianCalendar = new GregorianCalendar(GMT);
                        gregorianCalendar.set(parseInt, parseInt2, parseInt3, i, i2, i3);
                        gregorianCalendar.set(14, parseInt4);
                        timeInMillis = gregorianCalendar.getTimeInMillis();
                        if (obj2 != null || obj == null) {
                            num = null;
                        } else {
                            int i4;
                            if (Character.toUpperCase(group.charAt(0)) == 'Z') {
                                i4 = 0;
                            } else {
                                i4 = (Integer.parseInt(matcher.group(11)) * 60) + Integer.parseInt(matcher.group(12));
                                if (matcher.group(10).charAt(0) == '-') {
                                    i4 = -i4;
                                }
                                timeInMillis -= ((long) i4) * 60000;
                            }
                            num = Integer.valueOf(i4);
                        }
                        if (obj2 != null) {
                            z = true;
                        } else {
                            z = false;
                        }
                        return new DateTime(z, timeInMillis, num);
                    }
                }
                parseInt4 = 0;
                gregorianCalendar = new GregorianCalendar(GMT);
                gregorianCalendar.set(parseInt, parseInt2, parseInt3, i, i2, i3);
                gregorianCalendar.set(14, parseInt4);
                timeInMillis = gregorianCalendar.getTimeInMillis();
                if (obj2 != null) {
                }
                num = null;
                if (obj2 != null) {
                    z = false;
                } else {
                    z = true;
                }
                return new DateTime(z, timeInMillis, num);
            }
            throw new NumberFormatException("Invalid date/time format, cannot specify time zone shift without specifying time: " + str);
        }
        throw new NumberFormatException("Invalid date/time format: " + str);
    }

    private static void appendInt(StringBuilder stringBuilder, int i, int i2) {
        if (i < 0) {
            stringBuilder.append('-');
            i = -i;
        }
        int i3 = i;
        while (i3 > 0) {
            i3 /= 10;
            i2--;
        }
        for (i3 = 0; i3 < i2; i3++) {
            stringBuilder.append('0');
        }
        if (i != 0) {
            stringBuilder.append(i);
        }
    }
}
