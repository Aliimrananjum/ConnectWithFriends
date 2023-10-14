package com.example.connectwithfriends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.connectwithfriends.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Definerer knappene for navigasjon
    ImageButton btnAvtaler, btnKontakter, btnPreferanser;

    //Definerer Avtaler objekter
    private AvtaleDataKilde dataKilde;
    private ArrayAdapter<Avtale> avtaleArrayAdapter;

    private EditText avtaleEditText;

    private List<Avtale> avtaler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Sett opp startfragmentet
        loadFragment(new AvtalerFragment());


    /*
        dataKilde = new AvtaleDataKilde(this);
        dataKilde.open();
        ListView avtaleListView = findViewById(R.id.listView);
        avtaler = dataKilde.finnAlleAvtaler();

        avtaleArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, avtaler);

        avtaleListView.setAdapter(avtaleArrayAdapter);




        // Initialiser og åpne databasen
        AvtaleDataKilde dataKilde = new AvtaleDataKilde(this);
        try {
            dataKilde.open();
        } catch (Exception e) {
            e.printStackTrace();
            // Her kan du legge til en logg for feil, eller vise en feilmelding til brukeren.
        }

         */




        // Kobler knappene fra XML til Java-koden
        btnAvtaler = findViewById(R.id.btnAvtaler);
        btnKontakter = findViewById(R.id.btnKontakter);
        btnPreferanser = findViewById(R.id.btnPreferanser);



        // Når "Avtaler"-knappen trykkes, last AvtalerFragment
        btnAvtaler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AvtalerFragment());
            }
        });
        // Når "Kontakter"-knappen trykkes, last KontakterFragment
        btnKontakter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new KontakterFragment());
            }
        });
        // Når "Preferanser"-knappen trykkes, last PreferanserFragment
        btnPreferanser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new PreferanserFragment());
            }
        });
    }
    // Metode for å laste inn det valgte fragmentet
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
