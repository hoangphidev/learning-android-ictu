package com.hpsoft.truyentranh.objects;

import java.util.List;

public class ChapTruyen {
    private String tenTruyen;
    private String moTa;
    private List<ListChap> listChap;

    public ChapTruyen() {
    }

    public ChapTruyen(String tenTruyen, String moTa, List<ListChap> listChap) {
        this.tenTruyen = tenTruyen;
        this.moTa = moTa;
        this.listChap = listChap;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<ListChap> getListChap() {
        return listChap;
    }

    public void setListChap(List<ListChap> listChap) {
        this.listChap = listChap;
    }
}
