package com.example.luisangel.ltrun;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.StringTokenizer;


public class CalculaFragment extends Fragment {


    public CalculaFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calcula, container, false);

        final EditText tiempoU = (EditText) v.findViewById(R.id.editTextTiempo);
        final EditText distanciaU = (EditText) v.findViewById(R.id.editTextDistancia);
        final TextView marca = (TextView) v.findViewById(R.id.textViewMarca);

        final Button calcular = (Button) v.findViewById(R.id.buttonCalcular);
        calcular.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick (View v){


                        final String tiempo = tiempoU.getText().toString();

                        final String distancia = distanciaU.getText().toString();
                        double metros = Double.parseDouble(distancia);
                        int i=0;
                        StringTokenizer st = new StringTokenizer(tiempo,":");
                        int[] datos=new int[3];
                        while (st.hasMoreTokens()) {
                            String t = st.nextToken();
                            datos[i]=Integer.valueOf(t).intValue();
                            i++;
                        }
                        int hora=datos[0];
                        int minutos=datos[1];
                        int segundos=datos[2];

                        Log.i("hora",String.valueOf(hora));
                        Log.i("minutos",String.valueOf(minutos));
                        Log.i("segundos",String.valueOf(segundos));
                        int calseg=(((hora*60)+minutos)*60)+segundos;

                        double calmetrossegundo= metros/calseg;
                        double calkilometoshora=3.6*calmetrossegundo;

                        double ritmoa=(3600/calkilometoshora)/60;

                        //long iPart1 = (long) ritmoa;
                        //double p_dec = ritmoa - iPart1*60;

                        //long iPart2 = (long) p_dec;

                        int iPart1= (int)ritmoa;

                        double p_dec= ritmoa - iPart1;

                        double iPart = p_dec*60;

                        int iPart2 = (int)iPart;

                        String ritmo= iPart1+","+iPart2;
                        Log.i("ritmo",ritmo);

                        marca.setText(ritmo+" min/km");

                    }
                }
        );
        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
