package project.arrik.com.sipadat.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by erdearik on 7/23/16.
 */
public class AllData implements Serializable {
    String ph;
    String suhu;
    String doo;
    String status;
    String waktu;
    String output;

    protected AllData(Parcel in) {
        ph = in.readString();
        suhu = in.readString();
        doo = in.readString();
        status = in.readString();
        waktu = in.readString();
        output = in.readString();
        id = in.readString();
        unread = in.readInt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    int unread;

    public AllData(){

    }

    public AllData(String ph, String suhu, String doo, String status, String waktu, String output) {
        this.ph = ph;
        this.suhu = suhu;
        this.doo = doo;
        this.status = status;
        this.waktu = waktu;
        this.output = output;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

}