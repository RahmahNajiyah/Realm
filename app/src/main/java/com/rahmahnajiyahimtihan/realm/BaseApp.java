package com.rahmahnajiyahimtihan.realm;

import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by User on 2/15/2018.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //konfigurasi relm / menyusun elemen realm
        RealmConfiguration configuration = new RealmConfiguration.Builder(this).schemaVersion(0).migration(new DataMigration()).build();
        Realm.setDefaultConfiguration(configuration);

    }

    private class DataMigration implements RealmMigration {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            //schema = perencanaan
            RealmSchema schema = realm.getSchema();
            //jika belum ada tabel atau jumlah tabel 0 (sama sekali tdk ada)
            if (oldVersion == 0){
                //buat tabel br
                schema.create("mahasiswa").addField("id_mahasiswa", int.class).addField("nama_mahasiswa", String.class).addField("alamat_mahasiswa", String.class); //dlm kurung nama tabelnya
                oldVersion++;
                //fungsi ini sama dgn membuat tabel di mysql (database)
                //mahasiswa adlah nama tabelnya
                //field adalah nama kolomnya
                //oldVersion adlh jmlh tabel

            }
        }
    }
}
