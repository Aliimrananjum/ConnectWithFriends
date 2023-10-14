package com.example.connectwithfriends;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KontakterFragment extends Fragment {

    private EditText editTextFornavn, editTextEtternavn, editTextNummer;
    private TextView visalle;

    private FloatingActionButton btnLeggTilAvtale;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Vi blåser opp fragment_avtale.xml som visningen for dette fragmentet

        View view = inflater.inflate(R.layout.fragment_kontakter,container, false);

        // Initialiser knappen
        btnLeggTilAvtale = view.findViewById(R.id.btnLeggTilKontakt);

        visalle=view.findViewById(R.id.visalle);
        showKontakt();

        btnLeggTilAvtale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visLeggTilKontantFragment();
            }
        });

        return view;
    }




    private void showKontakt() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            List<Kontakt> alle = DatabaseClient.getInstance(getActivity().getApplicationContext()).getAppDatabase().kontaktDao().getAll();

            StringBuilder tekst = new StringBuilder();
            for (Kontakt kontakt : alle) {
                tekst.append("Fornavn: ").append(kontakt.getFornavn())
                        .append(" ,Etternavn: ").append(kontakt.getEtternavn())
                        .append(" ,Nummer: ").append(kontakt.getNummer()).append("\n");
            }

            handler.post(() -> {
                visalle.setText(tekst.toString());
                Toast.makeText(getActivity().getApplicationContext(), "Vist", Toast.LENGTH_LONG).show();
            });
        });

    }

    private void visLeggTilKontantFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Opprett en ny instans av LeggTilAvtaleFragment
        LeggTilKontaktFragment leggTilKontaktFragment = new LeggTilKontaktFragment();

        // Erstatt den nåværende fragmenten med LeggTilAvtaleFragment
        fragmentTransaction.replace(R.id.frameLayout, leggTilKontaktFragment);
        fragmentTransaction.addToBackStack(null); // Dette gjør at brukeren kan navigere tilbake til AvtaleFragment ved å trykke tilbake-knappen

        // Utfør endringene
        fragmentTransaction.commit();
    }


}
