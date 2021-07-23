package vangiau.example.shoptech.model;

public class LoaiThietBi {
    public int id;
    public String tenThietBi;
    public String hinhAnhThietBi;

    public LoaiThietBi() {
    }

    public LoaiThietBi(int id, String tenThietBi, String hinhAnhThietBi) {
        this.id = id;
        this.tenThietBi = tenThietBi;
        this.hinhAnhThietBi = hinhAnhThietBi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThietBi() {
        return tenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    public String getHinhAnhThietBi() {
        return hinhAnhThietBi;
    }

    public void setHinhAnhThietBi(String hinhAnhThietBi) {
        this.hinhAnhThietBi = hinhAnhThietBi;
    }
}
