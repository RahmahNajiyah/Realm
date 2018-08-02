package com.rahmahnajiyahimtihan.realm;

/**
 * Created by User on 2/15/2018.
 */

public class MahasiswaModel {
    int id_mahasiswa;
    String nama_mahasiswa;
    String alamat_mahasiswa;
    //right click > generate > setter & getter > choose all (after that step like the below)

    // -"- > generate > Conctructor > Choose all
    public MahasiswaModel(int id_mahasiswa, String nama_mahasiswa, String alamat_mahasiswa) {
        this.id_mahasiswa = id_mahasiswa;
        this.nama_mahasiswa = nama_mahasiswa;
        this.alamat_mahasiswa = alamat_mahasiswa;
    }

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
