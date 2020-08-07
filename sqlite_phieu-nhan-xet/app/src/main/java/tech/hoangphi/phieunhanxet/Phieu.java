package tech.hoangphi.phieunhanxet;

public class Phieu {
    String ten, chucVu, nhanXet;

    public Phieu(String ten, String chucVu, String nhanXet) {
        this.ten = ten;
        this.chucVu = chucVu;
        this.nhanXet = nhanXet;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getNhanXet() {
        return nhanXet;
    }

    public void setNhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
    }
}
