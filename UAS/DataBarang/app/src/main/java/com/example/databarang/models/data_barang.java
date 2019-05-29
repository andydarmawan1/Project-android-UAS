package com.example.databarang.models;

public class data_barang {
    //Deklarasi Variable
    private String kode;
    private String nama;
    private String jumlah;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    //Membuat Konstuktor kosong untuk membaca data snapshot
    public data_barang(){
    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public data_barang(String kode, String nama, String jumlah) {
        this.kode = kode;
        this.nama = nama;
        this.jumlah = jumlah;
    }
}
