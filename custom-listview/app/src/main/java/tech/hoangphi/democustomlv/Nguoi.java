package tech.hoangphi.democustomlv;

public class Nguoi {
    private String ten, cv;
    private int anh;

    public Nguoi(String ten, String cv, int anh) {
        this.ten = ten;
        this.cv = cv;
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }
}
