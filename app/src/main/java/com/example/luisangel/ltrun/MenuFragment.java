package com.example.luisangel.ltrun;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    //Declaramos la interface del Fragment y el Listener
    MenuFragmentListener mCallback;
    private customListener mListener;

    public MenuFragment() {
        // Required empty public constructor
    }

    //Interface al clickar en la lista coge la posición y el texto
    public interface MenuFragmentListener{
        public void onListSelected(int position, String item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {

    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback=(MenuFragmentListener)activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+ "must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        String[] datos = new String[]{"PERFIL","PRINCIPAL","CALCULADORA","NOTICIAS","CERRAR"}; //String de datos donde introducimos los nombres

        ArrayList<String> lista = new ArrayList<>(Arrays.asList(datos));

        MenuAdapter adapter = new MenuAdapter(getActivity(),lista);


        //INTERFAZ o VISIÓN
        final ListView listView = (ListView) getActivity().findViewById(R.id.listan);

        listView.setAdapter(adapter); //asociamos el ListView con los datos del array

        //Acciones de la lista
        listView.setOnItemClickListener(new customListener());

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //Implementamos Listener para el listView
    private class customListener implements AdapterView.OnItemClickListener{

        //Recibe el Adapter con el implements, el view, donde se ha clickado y la id
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //String de la posición clickada
            String item = (String) parent.getItemAtPosition(position);
            //Pasamos la información
            mCallback.onListSelected(position,item);
        }
    }

}
