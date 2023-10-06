package com.example.connectwithfriends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Vi blåser opp fragment_avtale.xml som visningen for dette fragmentet
        return inflater.inflate(R.layout.fragment_avtaler, container, false);
    }
}
