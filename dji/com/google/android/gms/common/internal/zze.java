package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class zze {
    public static final zze zzaeL = zza((CharSequence) "\t\n\u000b\f\r     　 ᠎ ").zza(zza(' ', ' '));
    public static final zze zzaeM = zza((CharSequence) "\t\n\u000b\f\r     　").zza(zza(' ', ' ')).zza(zza(' ', ' '));
    public static final zze zzaeN = zza('\u0000', '');
    public static final zze zzaeO;
    public static final zze zzaeP = zza('\t', '\r').zza(zza('\u001c', ' ')).zza(zzc(' ')).zza(zzc('᠎')).zza(zza(' ', ' ')).zza(zza(' ', '​')).zza(zza(' ', ' ')).zza(zzc(' ')).zza(zzc('　'));
    public static final zze zzaeQ = new zze() {
        public boolean zzd(char c) {
            return Character.isDigit(c);
        }
    };
    public static final zze zzaeR = new zze() {
        public boolean zzd(char c) {
            return Character.isLetter(c);
        }
    };
    public static final zze zzaeS = new zze() {
        public boolean zzd(char c) {
            return Character.isLetterOrDigit(c);
        }
    };
    public static final zze zzaeT = new zze() {
        public boolean zzd(char c) {
            return Character.isUpperCase(c);
        }
    };
    public static final zze zzaeU = new zze() {
        public boolean zzd(char c) {
            return Character.isLowerCase(c);
        }
    };
    public static final zze zzaeV = zza('\u0000', '\u001f').zza(zza('', ''));
    public static final zze zzaeW = zza('\u0000', ' ').zza(zza('', ' ')).zza(zzc('­')).zza(zza('؀', '؃')).zza(zza((CharSequence) "۝܏ ឴឵᠎")).zza(zza(' ', '‏')).zza(zza(' ', ' ')).zza(zza(' ', '⁤')).zza(zza('⁪', '⁯')).zza(zzc('　')).zza(zza('?', '')).zza(zza((CharSequence) "﻿￹￺￻"));
    public static final zze zzaeX = zza('\u0000', 'ӹ').zza(zzc('־')).zza(zza('א', 'ת')).zza(zzc('׳')).zza(zzc('״')).zza(zza('؀', 'ۿ')).zza(zza('ݐ', 'ݿ')).zza(zza('฀', '๿')).zza(zza('Ḁ', '₯')).zza(zza('℀', '℺')).zza(zza('ﭐ', '﷿')).zza(zza('ﹰ', '﻿')).zza(zza('｡', 'ￜ'));
    public static final zze zzaeY = new zze() {
        public zze zza(zze com_google_android_gms_common_internal_zze) {
            zzx.zzw(com_google_android_gms_common_internal_zze);
            return this;
        }

        public boolean zzb(CharSequence charSequence) {
            zzx.zzw(charSequence);
            return true;
        }

        public boolean zzd(char c) {
            return true;
        }
    };
    public static final zze zzaeZ = new zze() {
        public zze zza(zze com_google_android_gms_common_internal_zze) {
            return (zze) zzx.zzw(com_google_android_gms_common_internal_zze);
        }

        public boolean zzb(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        public boolean zzd(char c) {
            return false;
        }
    };

    private static class zza extends zze {
        List<zze> zzafg;

        zza(List<zze> list) {
            this.zzafg = list;
        }

        public zze zza(zze com_google_android_gms_common_internal_zze) {
            List arrayList = new ArrayList(this.zzafg);
            arrayList.add(zzx.zzw(com_google_android_gms_common_internal_zze));
            return new zza(arrayList);
        }

        public boolean zzd(char c) {
            for (zze zzd : this.zzafg) {
                if (zzd.zzd(c)) {
                    return true;
                }
            }
            return false;
        }
    }

    static {
        zze zza = zza('0', '9');
        zze com_google_android_gms_common_internal_zze = zza;
        for (char c : "٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０".toCharArray()) {
            com_google_android_gms_common_internal_zze = com_google_android_gms_common_internal_zze.zza(zza(c, (char) (c + 9)));
        }
        zzaeO = com_google_android_gms_common_internal_zze;
    }

    public static zze zza(final char c, final char c2) {
        zzx.zzaa(c2 >= c);
        return new zze() {
            public boolean zzd(char c) {
                return c <= c && c <= c2;
            }
        };
    }

    public static zze zza(CharSequence charSequence) {
        switch (charSequence.length()) {
            case 0:
                return zzaeZ;
            case 1:
                return zzc(charSequence.charAt(0));
            case 2:
                final char charAt = charSequence.charAt(0);
                final char charAt2 = charSequence.charAt(1);
                return new zze() {
                    public boolean zzd(char c) {
                        return c == charAt || c == charAt2;
                    }
                };
            default:
                final char[] toCharArray = charSequence.toString().toCharArray();
                Arrays.sort(toCharArray);
                return new zze() {
                    public boolean zzd(char c) {
                        return Arrays.binarySearch(toCharArray, c) >= 0;
                    }
                };
        }
    }

    public static zze zzc(final char c) {
        return new zze() {
            public zze zza(zze com_google_android_gms_common_internal_zze) {
                return com_google_android_gms_common_internal_zze.zzd(c) ? com_google_android_gms_common_internal_zze : super.zza(com_google_android_gms_common_internal_zze);
            }

            public boolean zzd(char c) {
                return c == c;
            }
        };
    }

    public zze zza(zze com_google_android_gms_common_internal_zze) {
        return new zza(Arrays.asList(new zze[]{this, (zze) zzx.zzw(com_google_android_gms_common_internal_zze)}));
    }

    public boolean zzb(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!zzd(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean zzd(char c);
}
