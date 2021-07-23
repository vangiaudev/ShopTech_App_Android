package vangiau.example.shoptech.model;

public class GioHang {
    private int id;
    private String ten;
    private long gia;
    private String hinh;
    private int soLuong;

    public GioHang(int id, String ten, long gia, String hinh, int soLuong) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.hinh = hinh;
        this.soLuong = soLuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
