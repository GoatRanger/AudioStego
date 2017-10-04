package dji.pilot2.multimoment.videolib;

import dji.thirdparty.afinal.a.a.e;

@e(a = "download_music_info")
public class MusicDownloadTimeInfo {
    private long downloadTime;
    private int id;
    private String musicName;
    private int sequence;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getMusicName() {
        return this.musicName;
    }

    public void setMusicName(String str) {
        this.musicName = str;
    }

    public int getSequence() {
        return this.sequence;
    }

    public void setSequence(int i) {
        this.sequence = i;
    }

    public long getDownloadTime() {
        return this.downloadTime;
    }

    public void setDownloadTime(long j) {
        this.downloadTime = j;
    }
}
