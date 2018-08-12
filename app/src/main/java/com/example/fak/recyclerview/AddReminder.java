package com.example.fak.recyclerview;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddReminder extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TimePickerDialog timePickerDialog;
    private Spinner spinnerAdd;
    private Button btnUpdate;
    private EditText Editdate,Edittotal,Edittime;
    private String date, time, note, total;
    private SharedPreferences preferences;
    public String filename = "reminder1";
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYTOTAL = "Key Total";
    public static final String KEYTANGGAL = "Key Tanggal";
    public static final String KEYWAKTU = "Key Waktu";
    public static final String KEYNOTE = "Key Note";
    public static final String KEYCATEGORY = "Key Category";
    private File file ;
    private RecyclerView rv;
    private ArrayList<DataReminder> data;
    private String[] jenisAsuransi = {
            "Health Insurance",
            "Car Insurance",
            "Pension Insurance",
            "Life Insurance"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        file = new File(this.getFilesDir(), filename);
        spinnerAdd=(Spinner)findViewById(R.id.spinnerAdd);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, jenisAsuransi);
        spinnerAdd.setAdapter(adapter);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        Editdate = (EditText) findViewById(R.id.edit_date);
        Edittotal = (EditText) findViewById(R.id.edit_total);
        Edittime = (EditText)findViewById(R.id.edit_time);
        btnUpdate = (Button)findViewById(R.id.button_update);

        Editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDateDialog();
            }
        });
        Edittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimeDialog();
            }
        });
        date=Editdate.getText().toString();
        time=Edittime.getText().toString();
        loaddata();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // data = new ArrayList<>();

               // String category = category.getText().toString();
                String total = Edittotal.getText().toString();
                String tanggal = Editdate.getText().toString();
                String waktu = Edittime.getText().toString();

                Toast.makeText(AddReminder.this, "UserName dan Password disimpan", Toast.LENGTH_SHORT).show();
                data.add(new DataReminder(total,tanggal,waktu,"faizal"));
                savedata();
                Intent intent = new Intent(AddReminder.this, MainActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)data);
                intent.putExtra("BUNDLE",args);
                startActivity(intent);
            }
        });

    }
    private void savedata (){
        SharedPreferences sharedPreferences = getSharedPreferences("datasave", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("datalist",json);
        editor.apply();
    }
    private void loaddata() {
        SharedPreferences sharedPreferences = getSharedPreferences("datasave", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datalist", null);
        Type type = new TypeToken<ArrayList<DataReminder>>() {}.getType();
        data = gson.fromJson(json, type);

        if (data == null) {
            data = new ArrayList<>();
        }
    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                Editdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
    private void showTimeDialog() {

        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                Edittime.setText(+hourOfDay+":"+minute);
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

}
