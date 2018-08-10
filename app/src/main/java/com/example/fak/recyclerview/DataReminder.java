package com.example.fak.recyclerview;

import java.io.Serializable;

public class DataReminder implements Serializable {
    String total, tanggal, waktu, note;

    public DataReminder() {
    }

    public DataReminder(String total, String tanggal, String waktu, String note) {
        this.total = total;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.note = note;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
