package com.rahmahnajiyahimtihan.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by User on 2/15/2018.
 */

public class Mahasiswa extends RealmObject {

    @PrimaryKey
    private  int id_mahasiswa;
    private  String nama_mahasiswa;
    private String alamat_mahasiswa;

    //right click> generate> setter and getter> choose all

    public int getId_mahasiswa() {
        return id_mahasiswa;
    }

    public void setId_mahasiswa(int id_mahasiswa) {
        this.id_mahasiswa = id_mahasiswa;
    }

    public String getNama_mahasiswa() {
        return nama_mahasiswa;
    }

    public void setNama_mahasiswa(String nama_mahasiswa) {
        this.nama_mahasiswa = nama_mahasiswa;
    }

    public String getAlamat_mahasiswa() {
        return alamat_mahasiswa;
    }

    public void setAlamat_mahasiswa(String alamat_mahasiswa) {
        this.alamat_mahasiswa = alamat_mahasiswa;
    }
}
