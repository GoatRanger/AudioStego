package com.google.api.client.http;

import com.alipay.sdk.h.a;
import com.alipay.sdk.j.i;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import dji.pilot.usercenter.protocol.d;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UriTemplate {
    private static final String COMPOSITE_NON_EXPLODE_JOINER = ",";
    static final Map<Character, CompositeOutput> COMPOSITE_PREFIXES = new HashMap();

    private enum CompositeOutput {
        PLUS(Character.valueOf('+'), "", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, true),
        HASH(Character.valueOf('#'), "#", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, true),
        DOT(Character.valueOf('.'), ".", ".", false, false),
        FORWARD_SLASH(Character.valueOf('/'), d.t, d.t, false, false),
        SEMI_COLON(Character.valueOf(';'), i.b, i.b, true, false),
        QUERY(Character.valueOf('?'), "?", a.b, true, false),
        AMP(Character.valueOf('&'), a.b, a.b, true, false),
        SIMPLE(null, "", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, false);
        
        private final String explodeJoiner;
        private final String outputPrefix;
        private final Character propertyPrefix;
        private final boolean requiresVarAssignment;
        private final boolean reservedExpansion;

        private CompositeOutput(Character ch, String str, String str2, boolean z, boolean z2) {
            this.propertyPrefix = ch;
            this.outputPrefix = (String) Preconditions.checkNotNull(str);
            this.explodeJoiner = (String) Preconditions.checkNotNull(str2);
            this.requiresVarAssignment = z;
            this.reservedExpansion = z2;
            if (ch != null) {
                UriTemplate.COMPOSITE_PREFIXES.put(ch, this);
            }
        }

        String getOutputPrefix() {
            return this.outputPrefix;
        }

        String getExplodeJoiner() {
            return this.explodeJoiner;
        }

        boolean requiresVarAssignment() {
            return this.requiresVarAssignment;
        }

        int getVarNameStartIndex() {
            return this.propertyPrefix == null ? 0 : 1;
        }

        String getEncodedValue(String str) {
            if (this.reservedExpansion) {
                return CharEscapers.escapeUriPath(str);
            }
            return CharEscapers.escapeUri(str);
        }

        boolean getReservedExpansion() {
            return this.reservedExpansion;
        }
    }

    static {
        CompositeOutput.values();
    }

    static CompositeOutput getCompositeOutput(String str) {
        CompositeOutput compositeOutput = (CompositeOutput) COMPOSITE_PREFIXES.get(Character.valueOf(str.charAt(0)));
        return compositeOutput == null ? CompositeOutput.SIMPLE : compositeOutput;
    }

    private static Map<String, Object> getMap(Object obj) {
        Map<String, Object> linkedHashMap = new LinkedHashMap();
        for (Entry entry : Data.mapOf(obj).entrySet()) {
            Object value = entry.getValue();
            if (!(value == null || Data.isNull(value))) {
                linkedHashMap.put(entry.getKey(), value);
            }
        }
        return linkedHashMap;
    }

    public static String expand(String str, String str2, Object obj, boolean z) {
        if (str2.startsWith(d.t)) {
            GenericUrl genericUrl = new GenericUrl(str);
            genericUrl.setRawPath(null);
            str2 = genericUrl.build() + str2;
        } else if (!(str2.startsWith("http://") || str2.startsWith("https://"))) {
            str2 = str + str2;
        }
        return expand(str2, obj, z);
    }

    public static String expand(String str, Object obj, boolean z) {
        Map map = getMap(obj);
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        int length = str.length();
        while (i < length) {
            int indexOf = str.indexOf(123, i);
            if (indexOf != -1) {
                stringBuilder.append(str.substring(i, indexOf));
                i = str.indexOf(FTPCodes.DATA_CONNECTION_ALREADY_OPEN, indexOf + 2);
                String substring = str.substring(indexOf + 1, i);
                int i2 = i + 1;
                boolean endsWith = substring.endsWith("*");
                CompositeOutput compositeOutput = getCompositeOutput(substring);
                int varNameStartIndex = compositeOutput.getVarNameStartIndex();
                i = substring.length();
                if (endsWith) {
                    i--;
                }
                substring = substring.substring(varNameStartIndex, i);
                Object remove = map.remove(substring);
                if (remove == null) {
                    i = i2;
                } else {
                    if (remove instanceof Iterator) {
                        remove = getListPropertyValue(substring, (Iterator) remove, endsWith, compositeOutput);
                    } else if ((remove instanceof Iterable) || remove.getClass().isArray()) {
                        remove = getListPropertyValue(substring, Types.iterableOf(remove).iterator(), endsWith, compositeOutput);
                    } else if (remove.getClass().isEnum()) {
                        substring = FieldInfo.of((Enum) remove).getName();
                        if (substring != null) {
                            remove = CharEscapers.escapeUriPath(substring);
                        }
                    } else if (!Data.isValueOfPrimitiveType(remove)) {
                        remove = getMapPropertyValue(substring, getMap(remove), endsWith, compositeOutput);
                    } else if (compositeOutput.getReservedExpansion()) {
                        remove = CharEscapers.escapeUriPathWithoutReserved(remove.toString());
                    } else {
                        remove = CharEscapers.escapeUriPath(remove.toString());
                    }
                    stringBuilder.append(remove);
                    i = i2;
                }
            } else if (i == 0 && !z) {
                return str;
            } else {
                stringBuilder.append(str.substring(i));
                if (z) {
                    GenericUrl.addQueryParams(map.entrySet(), stringBuilder);
                }
                return stringBuilder.toString();
            }
        }
        if (z) {
            GenericUrl.addQueryParams(map.entrySet(), stringBuilder);
        }
        return stringBuilder.toString();
    }

    private static String getListPropertyValue(String str, Iterator<?> it, boolean z, CompositeOutput compositeOutput) {
        if (!it.hasNext()) {
            return "";
        }
        String explodeJoiner;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(compositeOutput.getOutputPrefix());
        if (z) {
            explodeJoiner = compositeOutput.getExplodeJoiner();
        } else {
            explodeJoiner = COMPOSITE_NON_EXPLODE_JOINER;
            if (compositeOutput.requiresVarAssignment()) {
                stringBuilder.append(CharEscapers.escapeUriPath(str));
                stringBuilder.append("=");
            }
        }
        while (it.hasNext()) {
            if (z && compositeOutput.requiresVarAssignment()) {
                stringBuilder.append(CharEscapers.escapeUriPath(str));
                stringBuilder.append("=");
            }
            stringBuilder.append(compositeOutput.getEncodedValue(it.next().toString()));
            if (it.hasNext()) {
                stringBuilder.append(explodeJoiner);
            }
        }
        return stringBuilder.toString();
    }

    private static String getMapPropertyValue(String str, Map<String, Object> map, boolean z, CompositeOutput compositeOutput) {
        if (map.isEmpty()) {
            return "";
        }
        String str2;
        String explodeJoiner;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(compositeOutput.getOutputPrefix());
        if (z) {
            str2 = "=";
            explodeJoiner = compositeOutput.getExplodeJoiner();
        } else {
            String str3 = COMPOSITE_NON_EXPLODE_JOINER;
            String str4 = COMPOSITE_NON_EXPLODE_JOINER;
            if (compositeOutput.requiresVarAssignment()) {
                stringBuilder.append(CharEscapers.escapeUriPath(str));
                stringBuilder.append("=");
            }
            str2 = str4;
            explodeJoiner = str3;
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            str3 = compositeOutput.getEncodedValue((String) entry.getKey());
            str4 = compositeOutput.getEncodedValue(entry.getValue().toString());
            stringBuilder.append(str3);
            stringBuilder.append(str2);
            stringBuilder.append(str4);
            if (it.hasNext()) {
                stringBuilder.append(explodeJoiner);
            }
        }
        return stringBuilder.toString();
    }
}
