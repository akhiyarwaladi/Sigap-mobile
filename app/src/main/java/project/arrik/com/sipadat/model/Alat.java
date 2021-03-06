package project.arrik.com.sipadat.model;

import java.io.Serializable;

/**
 * Created by erdearik on 7/23/16.
 */
public class Alat implements Serializable{
    private String nama;
    private String kode;
    private String tglProduksi;

    public Alat() {
    }

    public Alat(String nama, String kode, String tglProduksi) {
        this.nama = nama;
        this.kode = kode;
        this.tglProduksi = tglProduksi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getTglProduksi() {
        return tglProduksi;
    }

    public void setTglProduksi(String tglProduksi) {
        this.tglProduksi = tglProduksi;
    }
}
