package com.example.connectwithfriends;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.Nullable;

public class LeggTilKontaktFragment extends Fragment {

    private EditText editTextFornavn, editTextEtternavn, editTextNummer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.legg_til_kontakt_fragment,container,false);

        editTextFornavn = view.findViewById(R.id.fornavn);
        editTextEtternavn = view.findViewById(R.id.etternavn);
        editTextNummer = view.findViewById(R.id.nummer);

        view.findViewById(R.id.lagreKontakt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveKontakt();
            }
        });

        return view;
    }

    private void saveKontakt(){
        final String fornavn = editTextFornavn.getText().toString().trim();
        final String etternavn = editTextEtternavn.getText().toString().trim();
        final String nummer = editTextNummer.getText().toString().trim();

        class SaveKontant extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids){
                Kontakt kontakt = new Kontakt();
                kontakt.setFornavn(fornavn);
                kontakt.setEtternavn(etternavn);
                kontakt.setNummer(nummer);

                DatabaseClient.getInstance(getActivity().getApplicationContext())
                        .getAppDatabase()
                        .kontaktDao()
                        .insert(kontakt);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        }

        SaveKontant saveTask = new SaveKontant();
        saveTask.execute();
        visKontaktFragment();
    }

    private void visKontaktFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Opprett en ny instans av LeggTilAvtaleFragment
        KontakterFragment kontakterFragment = new KontakterFragment();

        // Erstatt den nåværende fragmenten med LeggTilAvtaleFragment
        fragmentTransaction.replace(R.id.frameLayout, kontakterFragment);
        //
        //fragmentTransaction.addToBackStack(null); // Dette gjør at brukeren kan navigere tilbake til AvtaleFragment ved å trykke tilbake-knappen

        // Utfør endringene
        fragmentTransaction.commit();
    }


}
