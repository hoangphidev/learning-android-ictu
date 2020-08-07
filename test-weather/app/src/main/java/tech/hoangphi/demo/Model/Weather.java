package tech.hoangphi.demo.Model;

import java.io.Serializable;

public class Weather implements Serializable {
    String diadiem, kieuthoitiet;
    int nhietdo;

    public Weather(String diadiem, int nhietdo) {
        this.diadiem = diadiem;
        this.nhietdo = nhietdo;
        if (nhietdo > 30) {
            kieuthoitiet = "Sunny";
        } else if (nhietdo < 20) {
            kieuthoitiet = "Rainy";
        } else
            kieuthoitiet = "Cloudy";
    }

    public String getDiadiem() {
        return diadiem;
    }

    public int getNhietdo() {
        return nhietdo;
    }

    public String getKieuThoiTiet() {
        return kieuthoitiet;
    }
}
