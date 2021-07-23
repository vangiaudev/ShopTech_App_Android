package vangiau.example.shoptech.model;

import java.io.Serializable;

public class DanhGia implements Serializable {
    private int id;
    private String noidung;
    private double rating;
    private String ngaydanhgia;
    private String email;
    private String hoten;
    private String avatar;
    private int idsanpham;

    public DanhGia() {
    }

    public DanhGia(int id, String noidung, double rating, String ngaydanhgia, String email, String hoten, String avatar, int idsanpham) {
        this.id = id;
        this.noidung = noidung;
        this.rating = rating;
        this.ngaydanhgia = ngaydanhgia;
        this.email = email;
        this.hoten = hoten;
        this.avatar = avatar;
        this.idsanpham = idsanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getNgaydanhgia() {
        return ngaydanhgia;
    }

    public void setNgaydanhgia(String ngaydanhgia) {
        this.ngaydanhgia = ngaydanhgia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }
}
