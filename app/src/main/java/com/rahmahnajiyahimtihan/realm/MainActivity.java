package com.rahmahnajiyahimtihan.realm;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RealmHelper realmHelper;
    ListView listView;
    private ArrayList<MahasiswaModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realmHelper = new RealmHelper(MainActivity.this);
        listView = (ListView) findViewById(R.id.list);
        data = new ArrayList<>();
        getData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final MahasiswaModel MM = data.get(i);
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.aksi_dialog);

                //deklarasi komponen aksi dialog
                final EditText edNama = (EditText) dialog.findViewById(R.id.updateNama);
                final  EditText edAlamat = (EditText) dialog.findViewById(R.id.updateAlamat);
                final Button edit = (Button) dialog.findViewById(R.id.btnUpdate);
                Button hapus = (Button) dialog.findViewById(R.id.btnHapus);

                edNama.setText(MM.getNama_mahasiswa());
                edAlamat.setText(MM.getAlamat_mahasiswa());

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nama = edNama.getText().toString();
                        String alamat = edAlamat.getText().toString();
                        realmHelper.updateData(MM.id_mahasiswa, nama, alamat);
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Data Berhasil Update", Toast.LENGTH_SHORT).show();
                        getData();
                    }
                });

                hapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realmHelper.deleteData(MM.id_mahasiswa);
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                        getData();
                    }
                });
                dialog.show();

            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.insert_dialaog);

                final EditText addnama = (EditText) dialog.findViewById(R.id.insertNama);
                final EditText addalamat = (EditText) dialog.findViewById(R.id.insertAlamat);
                Button tambah = (Button) dialog.findViewById(R.id.btnTambah);

                tambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nama = addnama.getText().toString();
                        String alamat = addalamat.getText().toString();
                        realmHelper.addMahasiswa(nama,alamat);
                        dialog.dismiss();
                        getData();
                    }
                });
                dialog.show();
        }
        });
    }

    private void getData() {
        try {
            data = realmHelper.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            CustomAdapter adapter = new CustomAdapter(this, data);
            listView.setAdapter(adapter);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class CustomAdapter  extends BaseAdapter{
        Context c;
        ArrayList<MahasiswaModel> data;

        public CustomAdapter(Context c, ArrayList<MahasiswaModel> data) {
            this.c = c;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitem, null);
            TextView txtnama = (TextView) view.findViewById(R.id.txtNama);
            TextView txtalamat = (TextView) view.findViewById(R.id.txtAlamat);

            txtnama.setText(data.get(i).getNama_mahasiswa());
            txtalamat.setText(data.get(i).getAlamat_mahasiswa());

            return view;


        }
    }
}
