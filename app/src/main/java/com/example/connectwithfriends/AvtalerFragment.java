package com.example.connectwithfriends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * AvtalerFragment representerer skjermen for avtaler.
 */
public class AvtalerFragment extends Fragment {

    /**
     * Denne metoden kalles når fragmentets brukergrensesnitt skal tegnes for første gang.
     * Vi returnerer her en visning som er rotvisningen for fragmentets layout.
     *
     * @param inflater - brukes til å blåse opp (inflate) layout XML-filer til tilsvarende visningsobjekter
     * @param container - parent-visningen som fragmentets UI skal festes til
     * @param savedInstanceState - tidligere lagret tilstand av fragmentet
     * @return - returnerer rotvisningen for fragmentets layout
     */


    private FloatingActionButton btnLeggTilAvtale;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avtaler, container, false);

        // Initialiser knappen
        btnLeggTilAvtale = view.findViewById(R.id.btnLeggTilAvtale);

        // Sett opp en OnClickListener for FAB
        btnLeggTilAvtale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visLeggTilAvtaleFragment();
            }
        });

        return view;

    }

    // Metode for å vise LeggTilAvtaleFragment når FAB blir trykket
    private void visLeggTilAvtaleFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Opprett en ny instans av LeggTilAvtaleFragment
        LeggTilAvtaleFragment leggTilAvtaleFragment = new LeggTilAvtaleFragment();

        // Erstatt den nåværende fragmenten med LeggTilAvtaleFragment
        fragmentTransaction.replace(R.id.frameLayout, leggTilAvtaleFragment);
        fragmentTransaction.addToBackStack(null); // Dette gjør at brukeren kan navigere tilbake til AvtaleFragment ved å trykke tilbake-knappen

        // Utfør endringene
        fragmentTransaction.commit();
    }



}
