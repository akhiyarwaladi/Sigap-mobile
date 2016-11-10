package project.arrik.com.sipadat.model;

/**
 * Created by ALDI on 23/07/2016.
 */
public class SensorModel {
    private String ph, suhu, doo, waktu, hasil;

    public SensorModel(String ph, String suhu, String doo, String waktu, String hasil) {
        this.ph = ph;
        this.suhu = suhu;
        this.doo = doo;
        this.waktu = waktu;
        this.hasil = hasil;
    }

    public SensorModel() {}

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getDoo() {
        return doo;
    }

    public void setDoo(String doo) {
        this.doo = doo;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
