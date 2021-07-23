package vangiau.example.shoptech.model;

import java.io.Serializable;

public class DanhSachSanPham implements Serializable {
    public String giagoc;
    public String giatri;
    public String hinhanhchitiet2;
    public String hinhanhchitiet3;
    public String hinhanhthietbi;
    public String id;
    public String idthietbi;
    public String mota;
    public String tenthietbi;

    public DanhSachSanPham() {
    }

    public DanhSachSanPham(String giagoc, String giatri, String hinhanhchitiet2, String hinhanhchitiet3, String hinhanhthietbi, String id, String idthietbi, String mota, String tenthietbi) {
        this.giagoc = giagoc;
        this.giatri = giatri;
        this.hinhanhchitiet2 = hinhanhchitiet2;
        this.hinhanhchitiet3 = hinhanhchitiet3;
        this.hinhanhthietbi = hinhanhthietbi;
        this.id = id;
        this.idthietbi = idthietbi;
        this.mota = mota;
        this.tenthietbi = tenthietbi;
    }

    public String getGiagoc() {
        return giagoc;
    }

    public void setGiagoc(String giagoc) {
        this.giagoc = giagoc;
    }

    public String getGiatri() {
        return giatri;
    }

    public void setGiatri(String giatri) {
        this.giatri = giatri;
    }

    public String getHinhanhchitiet2() {
        return hinhanhchitiet2;
    }

    public void setHinhanhchitiet2(String hinhanhchitiet2) {
        this.hinhanhchitiet2 = hinhanhchitiet2;
    }

    public String getHinhanhchitiet3() {
        return hinhanhchitiet3;
    }

    public void setHinhanhchitiet3(String hinhanhchitiet3) {
        this.hinhanhchitiet3 = hinhanhchitiet3;
    }

    public String getHinhanhthietbi() {
        return hinhanhthietbi;
    }

    public void setHinhanhthietbi(String hinhanhthietbi) {
        this.hinhanhthietbi = hinhanhthietbi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdthietbi() {
        return idthietbi;
    }

    public void setIdthietbi(String idthietbi) {
        this.idthietbi = idthietbi;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTenthietbi() {
        return tenthietbi;
    }

    public void setTenthietbi(String tenthietbi) {
        this.tenthietbi = tenthietbi;
    }
}
