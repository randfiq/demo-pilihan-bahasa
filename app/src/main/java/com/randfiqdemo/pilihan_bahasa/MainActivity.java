package com.randfiqdemo.pilihan_bahasa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.randfiqdemo.pilihan_bahasa.utils.LocaleHelper;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Locale myLocale;
    String currentLanguage = "id", currentLang;

    private String mLanguageCode = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Locale lokasi = new Locale("id");
        Locale.setDefault(lokasi);

        Configuration config = new Configuration();
        config.locale = lokasi;

        setContentView(R.layout.activity_main);

        currentLanguage = getIntent().getStringExtra(currentLang);

        spinner = findViewById(R.id.spinner);

        List<String> list = new ArrayList<String>();

        list.add((String) getText(R.string.pilihan_bahasa));
        list.add("Bahasa");
        list.add("English");
        list.add("Español");
        list.add("Français");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, simple_spinner_item, list);
        adapter.setDropDownViewResource(simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        getResources().updateConfiguration(
                config,
                getResources().getDisplayMetrics()
        );

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("id");
                        break;
                    case 2:
                        setLocale("en");
                        break;
                    case 3:
                        setLocale("es");
                        break;
                    case 4:
                        setLocale("fr");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        findViewById(R.id.btnChangeLangView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocaleHelper.setLocale(MainActivity.this, mLanguageCode);
                recreate();
            }
        });
    }

    public void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(MainActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}