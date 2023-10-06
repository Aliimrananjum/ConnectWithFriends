package com.example.connectwithfriends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.connectwithfriends.R;

public class MainActivity extends AppCompatActivity {
    // Definerer knappene for navigasjon
    ImageButton btnAvtaler, btnKontakter, btnPreferanser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Kobler knappene fra XML til Java-koden
        btnAvtaler = findViewById(R.id.btnAvtaler);
        btnKontakter = findViewById(R.id.btnKontakter);
        btnPreferanser = findViewById(R.id.btnPreferanser);

        // Sett opp startfragmentet
        loadFragment(new AvtalerFragment());

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
