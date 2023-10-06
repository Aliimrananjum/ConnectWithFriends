package com.example.connectwithfriends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class LeggTilAvtaleFragment extends Fragment {

    private AvtaleDataKilde dataKilde;
    private ArrayAdapter<Avtale> avtaleArrayAdapter;

    private EditText avtaleEditText;

    private List<Avtale> avtaler;

    private Button leggtilButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.legg_til_avtale_fragment, container, false);

        // Initialiser variabler etter at Fragment er opprettet
        leggtilButton = view.findViewById(R.id.leggtil);
        avtaleEditText = view.findViewById(R.id.input_AvtaleNavn);

        leggtilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String avtaleNavn = avtaleEditText.getText().toString();
                if (!avtaleNavn.isEmpty()) {
                    Avtale avtale = dataKilde.leggInnAvtale(avtaleNavn);
                    /*
                    avtaleArrayAdapter.add(avtale);
                    avtaleEditText.setText("");

                     */
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataKilde = new AvtaleDataKilde(getContext());
        try {
            dataKilde.open();
        } catch (Exception e) {
            e.printStackTrace();
            // Her kan du legge til en logg for feil, eller vise en feilmelding til brukeren.
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        dataKilde.close();
    }

}

