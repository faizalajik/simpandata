package com.example.fak.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<DataReminder> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datalist = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        String total = extras.getString("data1");
        String waktu = extras.getString("data2");
        String tanggal = extras.getString("data3");
        //datalist.add(new DataReminder(total,waktu,tanggal,"dadah"));
        rv = (RecyclerView) findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new AdapterRecyclerView(this,datalist));

        try {
            FileInputStream fis = new FileInputStream("t.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<DataReminder> data = (List<DataReminder>) ois.readObject();
            ois.close();
            data.add(new DataReminder(total,waktu,tanggal,"dadah"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //String total = extras.getString("data1");

    }
}
