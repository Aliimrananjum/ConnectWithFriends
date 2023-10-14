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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KontakterFragment extends Fragment {

    private EditText editTextFornavn, editTextEtternavn, editTextNummer;
    private TextView visalle;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Vi blåser opp fragment_avtale.xml som visningen for dette fragmentet

        View view = inflater.inflate(R.layout.fragment_kontakter,container, false);

        editTextFornavn = view.findViewById(R.id.fornavn);
        editTextEtternavn = view.findViewById(R.id.etternavn);
        editTextNummer = view.findViewById(R.id.nummer);

        visalle=view.findViewById(R.id.visalle);

        view.findViewById(R.id.lagreKontakt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveKontakt();
            }
        });

        view.findViewById(R.id.visKontakt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKontakt();
            }
        });

        return view;
    }

    private void saveKontakt(){
        final String fornavn = editTextFornavn.getText().toString().trim();
        final String etternavn = editTextEtternavn.getText().toString().trim();
        final String nummer = editTextNummer.getText().toString().trim();

        class SaveKontant extends AsyncTask<Void, Void, Void>{

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


}
