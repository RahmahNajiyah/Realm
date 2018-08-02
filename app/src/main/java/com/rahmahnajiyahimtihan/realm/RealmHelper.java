package com.rahmahnajiyahimtihan.realm;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by User on 2/15/2018.
 */

public class RealmHelper {
    //berisi kumpulan method jadi semacam api

    private Realm realm;
    private RealmResults<Mahasiswa> realmResults;
    Context context;

    //generate > constuctor > choose realm & context (ctrl+klik)
    public RealmHelper(Context context) {
        this.context = context;
        this.realm = realm.getInstance(context);
    }

    //method untuk menambah data
    public void  addMahasiswa (String nama, String alamat){
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setId_mahasiswa((int)(System.currentTimeMillis()/1000 /*auto increment*/));
        mahasiswa.setNama_mahasiswa(nama);
        mahasiswa.setAlamat_mahasiswa(alamat);

        realm.beginTransaction(); //memuali realm
        realm.copyToRealm(mahasiswa); //class mahasiswa di copy ke realm
        realm.commitTransaction(); //menjalankan realm
        Toast.makeText(context, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
    }

    //method untk menampilkan data
    public ArrayList<MahasiswaModel> findAll(){
        ArrayList<MahasiswaModel> data = new ArrayList<>();
        //menampilkan semua data yg ada di realm class mahasiswa
        realmResults = realm.where(Mahasiswa.class).findAll();

        //mengurutkan data dari yang terbaru (sort = sortir)
        realmResults.sort("id_mahasiswa", Sort.DESCENDING);
        //mengecek data
        if (realmResults.size() > 0){
            Toast.makeText(context, "Data Ada", Toast.LENGTH_SHORT).show();
            //mengambil data
            for (int i = 0; i < realmResults.size(); i++){
                int id = realmResults.get(i).getId_mahasiswa();
                String nama = realmResults.get(i).getNama_mahasiswa();
                String alamat = realmResults.get(i).getAlamat_mahasiswa();
                //datanya dimasukkin ke arralist
                data.add(new MahasiswaModel(id, nama, alamat));

            }
        }else {
            Toast.makeText(context, "data tidak ada", Toast.LENGTH_SHORT).show();
        }
        return data;
    }

    //method untuk memperbaharui data
    public void updateData(int id, String nama, String alamat) {
        realm.beginTransaction();
        //mencari id mahasiswa yg sam dgn id yg mau di update
        Mahasiswa mahasiswa = realm.where(Mahasiswa.class).equalTo("id_mahasiswa", id).findFirst();
        mahasiswa.setAlamat_mahasiswa(alamat);
        realm.commitTransaction();
    }

    //method untuk mnghapus data
    public void deleteData(int id) {
        realm.beginTransaction(); //diawali dgn beginTransaction diakhir dgn commitTransaction
        RealmResults<Mahasiswa> mahasiswa = realm.where(Mahasiswa.class).equalTo("id_mahasiswa", id).findAll();
        mahasiswa.remove(0); // removes an object at a given index
        mahasiswa.removeLast(); //removes and return the last object in the list
        mahasiswa.clear(); //remove all object from the list
        realm.commitTransaction();
    }

}
