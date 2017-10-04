package dji.logic.album.a;

import dji.logic.album.model.DJIAlbumDirInfo;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import java.util.ArrayList;

public interface d {

    public interface a<E> {
        void a();

        void a(long j, long j2);

        void a(long j, long j2, long j3);

        void a(DJIAlbumPullErrorType dJIAlbumPullErrorType);

        void a(E e);
    }

    void a(a<DJIAlbumDirInfo> aVar);

    void a(DJIAlbumFileInfo dJIAlbumFileInfo, a<DJIAlbumFile> aVar);

    void a(ArrayList<DJIAlbumFileInfo> arrayList, a<ArrayList<DJIAlbumFile>> aVar);

    void b(DJIAlbumFileInfo dJIAlbumFileInfo, a<DJIAlbumFile> aVar);

    void c();

    void c(DJIAlbumFileInfo dJIAlbumFileInfo, a<DJIAlbumFile> aVar);

    void d(DJIAlbumFileInfo dJIAlbumFileInfo, a<DJIAlbumFile> aVar);

    void e(DJIAlbumFileInfo dJIAlbumFileInfo, a<DJIAlbumFile> aVar);

    void f(DJIAlbumFileInfo dJIAlbumFileInfo, a<DJIAlbumFile> aVar);

    void g(DJIAlbumFileInfo dJIAlbumFileInfo, a<DJIAlbumFile> aVar);
}
