package dji.thirdparty.f;

class d$h<T> extends d<T> {

    class AnonymousClass1 implements d$f<T> {
        final /* synthetic */ Throwable a;

        AnonymousClass1(Throwable th) {
            this.a = th;
        }

        public void a(k<? super T> kVar) {
            kVar.a(this.a);
        }
    }

    public d$h(Throwable th) {
        super(new AnonymousClass1(th));
    }
}
