package com.hpsoft.truyentranh.objects;

public class TheLoai {
    private String tenTheLoai, linkTheLoai;

    public TheLoai(String tenTheLoai, String linkTheLoai) {
        this.tenTheLoai = tenTheLoai;
        this.linkTheLoai = linkTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public String getLinkTheLoai() {
        return linkTheLoai;
    }
}
