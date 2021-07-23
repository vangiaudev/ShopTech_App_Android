package vangiau.example.shoptech.model;

import java.io.Serializable;

public class HinhAnhChiTiet implements Serializable {

    private String hinhAnhChiTiet;

    public HinhAnhChiTiet(String hinhAnhChiTiet) {
        this.hinhAnhChiTiet = hinhAnhChiTiet;
    }

    public String getHinhAnhChiTiet() {
        return hinhAnhChiTiet;
    }

    public void setHinhAnhChiTiet(String hinhAnhChiTiet) {
        this.hinhAnhChiTiet = hinhAnhChiTiet;
    }
}
