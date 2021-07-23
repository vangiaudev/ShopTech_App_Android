package vangiau.example.shoptech.model;

import java.io.Serializable;

public class ChiTietDonHang implements Serializable {
    private int ID;
    private int maDonHang;
    private int maThietBi;
    private String tenThietBi;
    private int giaThietBi;
    private int soLuongThietBi;
    private String ngayDat;
    private String emailDat;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(int ID, int maDonHang, int maThietBi, String tenThietBi, int giaThietBi, int soLuongThietBi, String ngayDat, String emailDat) {
        this.ID = ID;
        this.maDonHang = maDonHang;
        this.maThietBi = maThietBi;
        this.tenThietBi = tenThietBi;
        this.giaThietBi = giaThietBi;
        this.soLuongThietBi = soLuongThietBi;
        this.ngayDat = ngayDat;
        this.emailDat = emailDat;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaThietBi() {
        return maThietBi;
    }

    public void setMaThietBi(int maThietBi) {
        this.maThietBi = maThietBi;
    }

    public String getTenThietBi() {
        return tenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    public int getGiaThietBi() {
        return giaThietBi;
    }

    public void setGiaThietBi(int giaThietBi) {
        this.giaThietBi = giaThietBi;
    }

    public int getSoLuongThietBi() {
        return soLuongThietBi;
    }

    public void setSoLuongThietBi(int soLuongThietBi) {
        this.soLuongThietBi = soLuongThietBi;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getEmailDat() {
        return emailDat;
    }

    public void setEmailDat(String emailDat) {
        this.emailDat = emailDat;
    }
}
