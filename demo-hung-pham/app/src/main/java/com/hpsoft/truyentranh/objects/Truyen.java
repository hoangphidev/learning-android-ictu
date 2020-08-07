package com.hpsoft.truyentranh.objects;

public class Truyen {
    private String tenTruyen, linkTruyen,linkImage, rating;

    public Truyen(String tenTruyen, String linkTruyen, String linkImage, String rating) {
        this.tenTruyen = tenTruyen;
        this.linkTruyen = linkTruyen;
        this.linkImage = linkImage;
        this.rating = rating;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getLinkTruyen() {
        return linkTruyen;
    }

    public void setLinkTruyen(String linkTruyen) {
        this.linkTruyen = linkTruyen;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
