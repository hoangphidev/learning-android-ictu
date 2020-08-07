package com.hpsoft.truyentranh.objects;

public class ListChap {
    private String tenChap, linkChap,luotXem,ngayDang;

    public ListChap(String tenChap, String linkChap, String luotXem, String ngayDang) {
        this.tenChap = tenChap;
        this.linkChap = linkChap;
        this.luotXem = luotXem;
        this.ngayDang = ngayDang;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getLinkChap() {
        return linkChap;
    }

    public void setLinkChap(String linkChap) {
        this.linkChap = linkChap;
    }

    public String getLuotXem() {
        return luotXem;
    }

    public void setLuotXem(String luotXem) {
        this.luotXem = luotXem;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }
}
