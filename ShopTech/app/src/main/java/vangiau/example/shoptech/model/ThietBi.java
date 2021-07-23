package vangiau.example.shoptech.model;

import java.io.Serializable;

public class ThietBi implements Serializable {
    private int ID;
    private String tenThietBi;
    private Integer giaGoc;
    private Integer giaTri;
    private String hinhAnhThietBi;
    private String moTa;
    private int IDThietBi;
    private String hinhAnhThietBi2;
    private String hinhAnhThietBi3;

    public ThietBi() {
    }

    public ThietBi(int ID, String tenThietBi, Integer giaGoc, Integer giaTri, String hinhAnhThietBi, String moTa, int IDThietBi, String hinhAnhThietBi2, String hinhAnhThietBi3) {
        this.ID = ID;
        this.tenThietBi = tenThietBi;
        this.giaGoc = giaGoc;
        this.giaTri = giaTri;
        this.hinhAnhThietBi = hinhAnhThietBi;
        this.moTa = moTa;
        this.IDThietBi = IDThietBi;
        this.hinhAnhThietBi2 = hinhAnhThietBi2;
        this.hinhAnhThietBi3 = hinhAnhThietBi3;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenThietBi() {
        return tenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    public Integer getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(Integer giaGoc) {
        this.giaGoc = giaGoc;
    }

    public Integer getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Integer giaTri) {
        this.giaTri = giaTri;
    }

    public String getHinhAnhThietBi() {
        return hinhAnhThietBi;
    }

    public void setHinhAnhThietBi(String hinhAnhThietBi) {
        this.hinhAnhThietBi = hinhAnhThietBi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getIDThietBi() {
        return IDThietBi;
    }

    public void setIDThietBi(int IDThietBi) {
        this.IDThietBi = IDThietBi;
    }

    public String getHinhAnhThietBi2() {
        return hinhAnhThietBi2;
    }

    public void setHinhAnhThietBi2(String hinhAnhThietBi2) {
        this.hinhAnhThietBi2 = hinhAnhThietBi2;
    }

    public String getHinhAnhThietBi3() {
        return hinhAnhThietBi3;
    }

    public void setHinhAnhThietBi3(String hinhAnhThietBi3) {
        this.hinhAnhThietBi3 = hinhAnhThietBi3;
    }
}
