package dji.thirdparty.f.d;

public final class z {
    private z() {
        throw new IllegalStateException("No instances!");
    }

    public static <R> x<R> a(final n<? extends R> nVar) {
        return new x<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 0) {
                    return nVar.call();
                }
                throw new RuntimeException("Func0 expecting 0 arguments.");
            }
        };
    }

    public static <T0, R> x<R> a(final o<? super T0, ? extends R> oVar) {
        return new x<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 1) {
                    return oVar.a(objArr[0]);
                }
                throw new RuntimeException("Func1 expecting 1 argument.");
            }
        };
    }

    public static <T0, T1, R> x<R> a(final p<? super T0, ? super T1, ? extends R> pVar) {
        return new x<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 2) {
                    return pVar.b(objArr[0], objArr[1]);
                }
                throw new RuntimeException("Func2 expecting 2 arguments.");
            }
        };
    }

    public static <T0, T1, T2, R> x<R> a(final q<? super T0, ? super T1, ? super T2, ? extends R> qVar) {
        return new x<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 3) {
                    return qVar.a(objArr[0], objArr[1], objArr[2]);
                }
                throw new RuntimeException("Func3 expecting 3 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, R> x<R> a(final r<? super T0, ? super T1, ? super T2, ? super T3, ? extends R> rVar) {
        return new x<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 4) {
                    return rVar.a(objArr[0], objArr[1], objArr[2], objArr[3]);
                }
                throw new RuntimeException("Func4 expecting 4 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, T4, R> x<R> a(final s<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? extends R> sVar) {
        return new x<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 5) {
                    return sVar.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
                }
                throw new RuntimeException("Func5 expecting 5 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, T4, T5, R> x<R> a(final t<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> tVar) {
        return new x<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 6) {
                    return tVar.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
                }
                throw new RuntimeException("Func6 expecting 6 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, R> x<R> a(final u<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> uVar) {
        return new x<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 7) {
                    return uVar.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
                }
                throw new RuntimeException("Func7 expecting 7 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, R> x<R> a(final v<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> vVar) {
        return new x<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 8) {
                    return vVar.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
                }
                throw new RuntimeException("Func8 expecting 8 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, R> x<R> a(final w<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> wVar) {
        return new x<R>() {
            public R a(Object... objArr) {
                if (objArr.length == 9) {
                    return wVar.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8]);
                }
                throw new RuntimeException("Func9 expecting 9 arguments.");
            }
        };
    }

    public static x<Void> a(final b bVar) {
        return new x<Void>() {
            public /* synthetic */ Object a(Object[] objArr) {
                return b(objArr);
            }

            public Void b(Object... objArr) {
                if (objArr.length != 0) {
                    throw new RuntimeException("Action0 expecting 0 arguments.");
                }
                bVar.a();
                return null;
            }
        };
    }

    public static <T0> x<Void> a(final c<? super T0> cVar) {
        return new x<Void>() {
            public /* synthetic */ Object a(Object[] objArr) {
                return b(objArr);
            }

            public Void b(Object... objArr) {
                if (objArr.length != 1) {
                    throw new RuntimeException("Action1 expecting 1 argument.");
                }
                cVar.a(objArr[0]);
                return null;
            }
        };
    }

    public static <T0, T1> x<Void> a(final d<? super T0, ? super T1> dVar) {
        return new x<Void>() {
            public /* synthetic */ Object a(Object[] objArr) {
                return b(objArr);
            }

            public Void b(Object... objArr) {
                if (objArr.length != 2) {
                    throw new RuntimeException("Action3 expecting 2 arguments.");
                }
                dVar.a(objArr[0], objArr[1]);
                return null;
            }
        };
    }

    public static <T0, T1, T2> x<Void> a(final e<? super T0, ? super T1, ? super T2> eVar) {
        return new x<Void>() {
            public /* synthetic */ Object a(Object[] objArr) {
                return b(objArr);
            }

            public Void b(Object... objArr) {
                if (objArr.length != 3) {
                    throw new RuntimeException("Action3 expecting 3 arguments.");
                }
                eVar.a(objArr[0], objArr[1], objArr[2]);
                return null;
            }
        };
    }
}
