package dji.thirdparty.g.b.b.a;

import com.facebook.internal.l;
import com.google.api.client.http.HttpStatusCodes;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import dji.pilot.publics.control.rc.b;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.utils.a.a;
import it.sauronsoftware.ftp4j.FTPCodes;

public interface i extends g, h {
    public static final e[] aU = new e[]{fy_, fz_, fA_, fB_, fC_, fD_, fE_, fF_, fG_, fH_, fI_, fJ_, fK_, gy_, fL_, fM_, gz_, gA_, gB_, gC_, gD_, gE_, gF_, gG_, gH_, gI_, gJ_, gK_, gL_, gM_, gN_, gO_, gP_, gQ_, gR_, gS_, gT_, gU_, gV_, gW_, gX_, gY_, gZ_, ha_, hb_, hc_, hd_, he_, hf_, hg_, hh_, hi_, hj_, hk_, hl_, hm_, hn_, ho_, hp_, hq_, hr_, hs_, ht_, hu_, hv_, hw_, hx_, hy_, hz_, hA_, hB_, hC_, hD_, hE_, hF_};
    public static final e fA_ = new e("Image Width", 256, gl_, 1, fV_);
    public static final e fB_ = new e("Image Length", 257, gl_, 1, fV_);
    public static final e fC_ = new e("Bits Per Sample", 258, ms, -1, fV_);
    public static final e fD_ = new e("Compression", 259, ms, 1, fV_);
    public static final e fE_ = new e("Photometric Interpretation", (int) b.j, ms, 1, fV_);
    public static final e fF_ = new e("Threshholding", (int) b.k, ms, 1, fV_);
    public static final e fG_ = new e("Cell Width", (int) b.l, ms, 1, fV_);
    public static final e fH_ = new e("Cell Length", 265, ms, 1, fV_);
    public static final e fI_ = new e("Fill Order", 266, ms, 1, fV_);
    public static final e fJ_ = new e("Document Name", 269, gm_, -1, fV_);
    public static final e fK_ = new e("Image Description", 270, gm_, -1, fV_);
    public static final e fL_ = new e(a.m, 272, gm_, -1, fV_);
    public static final e fM_ = new e.b("Strip Offsets", 273, gl_, -1, fV_);
    public static final e fy_ = new e("New Subfile Type", 254, gk_, 1, fV_);
    public static final e fz_ = new e("Subfile Type", 255, ms, 1, fV_);
    public static final e gA_ = new e("Samples Per Pixel", 277, ms, 1, fV_);
    public static final e gB_ = new e("Rows Per Strip", 278, gl_, 1, fV_);
    public static final e gC_ = new e("Strip Byte Counts", 279, gn_, -1, fV_);
    public static final e gD_ = new e("Min Sample Value", 280, ms, -1, fV_);
    public static final e gE_ = new e("Max Sample Value", 281, ms, -1, fV_);
    public static final e gF_ = new e("XResolution", 282, go_, 1, fV_);
    public static final e gG_ = new e("YResolution", 283, go_, 1, fV_);
    public static final e gH_ = new e("Planar Configuration", 284, ms, 1, fV_);
    public static final e gI_ = new e("Page Name", 285, gm_, -1, fV_);
    public static final e gJ_ = new e("XPosition", 286, go_, -1, fV_);
    public static final e gK_ = new e("YPosition", 287, go_, -1, fV_);
    public static final e gL_ = new e("Free Offsets", 288, gk_, -1, fV_);
    public static final e gM_ = new e("Free Byte Counts", 289, gk_, -1, fV_);
    public static final e gN_ = new e("Gray Response Unit", 290, ms, 1, fV_);
    public static final e gO_ = new e("Gray Response Curve", 291, ms, -1, fV_);
    public static final e gP_ = new e("T4 Options", 292, gk_, 1, fV_);
    public static final e gQ_ = new e("T6 Options", 293, gk_, 1, fV_);
    public static final e gR_ = new e("Resolution Unit", 296, ms, 1, fV_);
    public static final e gS_ = new e("Page Number", 297, ms, 2, fV_);
    public static final e gT_ = new e("Transfer Function", (int) HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY, ms, -1, fV_);
    public static final e gU_ = new e("Software", (int) d.b, gm_, -1, fV_);
    public static final e gV_ = new e("Date Time", (int) d.f, gm_, 20, fV_);
    public static final e gW_ = new e("Artist", (int) d.h, gm_, -1, fV_);
    public static final e gX_ = new e("Host Computer", 316, gm_, -1, fV_);
    public static final e gY_ = new e("Predictor", 317, ms, 1, fV_);
    public static final e gZ_ = new e("White Point", 318, go_, 2, fV_);
    public static final e gy_ = new e("Make", 271, gm_, -1, fV_);
    public static final e gz_ = new e(dji.sdksharedlib.b.b.bW, 274, ms, 1, fV_);
    public static final e hA_ = new e("YCbCr Coefficients", 529, go_, 3, fV_);
    public static final e hB_ = new e("YCbCr Sub Sampling", (int) FTPCodes.NOT_LOGGED_IN, ms, 2, fV_);
    public static final e hC_ = new e("YCbCr Positioning", 531, ms, 1, fV_);
    public static final e hD_ = new e("Reference Black White", 532, gk_, -1, fV_);
    public static final e hE_ = new e("Copyright", 33432, gm_, -1, fV_);
    public static final e hF_ = new e("XMP", 700, gq_, -1, fV_);
    public static final e hG_ = new e.d("Unknown Tag", -1, gs_, -1, mB);
    public static final e ha_ = new e("Primary Chromaticities", 319, go_, 6, fV_);
    public static final e hb_ = new e("Color Map", 320, ms, -1, fV_);
    public static final e hc_ = new e("Halftone Hints", 321, ms, 2, fV_);
    public static final e hd_ = new e("Tile Width", 322, gl_, 1, fV_);
    public static final e he_ = new e("Tile Length", 323, gl_, 1, fV_);
    public static final e hf_ = new e.b("Tile Offsets", 324, gk_, -1, fV_);
    public static final e hg_ = new e("Tile Byte Counts", (int) PullToRefreshBase.SMOOTH_SCROLL_LONG_DURATION_MS, gl_, -1, fV_);
    public static final e hh_ = new e("Ink Set", (int) FTPCodes.NEED_ACCOUNT, ms, 1, fV_);
    public static final e hi_ = new e("Ink Names", 333, gm_, -1, fV_);
    public static final e hj_ = new e("Number Of Inks", 334, ms, 1, fV_);
    public static final e hk_ = new e("Dot Range", 336, gp_, -1, fV_);
    public static final e hl_ = new e("Target Printer", 337, gm_, -1, fV_);
    public static final e hm_ = new e("Extra Samples", 338, gq_, -1, fV_);
    public static final e hn_ = new e("Sample Format", 339, ms, -1, fV_);
    public static final e ho_ = new e("SMin Sample Value", 340, gr_, -1, fV_);
    public static final e hp_ = new e("SMax Sample Value", (int) l.g, gr_, -1, fV_);
    public static final e hq_ = new e("Transfer Range", 342, ms, 6, fV_);
    public static final e hr_ = new e("JPEGProc", 512, ms, 1, fV_);
    public static final e hs_ = new e.b("JPEGInterchange Format", 513, gk_, 1, fV_);
    public static final e ht_ = new e("JPEGInterchange Format Length", 514, gk_, 1, fV_);
    public static final e hu_ = new e("JPEGRestart Interval", (int) dji.pilot.f.a.a.v, ms, 1, fV_);
    public static final e hv_ = new e("JPEGLossless Predictors", (int) dji.pilot.f.a.a.x, ms, -1, fV_);
    public static final e hw_ = new e("JPEGPoint Transforms", (int) dji.pilot.f.a.a.y, ms, -1, fV_);
    public static final e hx_ = new e("JPEGQTables", 519, gk_, -1, fV_);
    public static final e hy_ = new e("JPEGDCTables", 520, gk_, -1, fV_);
    public static final e hz_ = new e("JPEGACTables", 521, gk_, -1, fV_);
}
