package com.example.luisangel.ltrun;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luisangel.ltrun.Usuarios.FirebaseReferences;
import com.example.luisangel.ltrun.Usuarios.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PerfilFragment extends Fragment {

    //Declaración de variables
    String nombreU,apodoU,emailU,fechanacU,ciudadU,marcazapU,marcaRelojU,mejorTiempoU,mParam1;
    TextView nombre,apodo,email,fechanac,ciudad,marcazap,marcareloj,mejortiempo;

    public static final String ARG_ALIAS = "param1";



    //CONSTRUCTOR
    public PerfilFragment() {
        // Required empty public constructor
    }

    //RECOGEMOS LOS ARGUMENTOS QUE LE PASAMOS DESDE EL MainActivity
    public static PerfilFragment newInstance(String param1) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ALIAS, param1);
        fragment.setArguments(args);
        Log.i("ID1", "EL ID ES reg2: "+param1);
        return fragment;
    }

    //EN EL onCreate COMPRUEBA QUE LOS ARGUMENTOS NO SON NULOS Y PARA CREAR EL JuegoFragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_ALIAS);
            Log.i("ID1", "EL ID ES reg1: "+mParam1);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        //Base de datos Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Referencia donde apunta la base de datos
        DatabaseReference UsuariosRef = database.getReference(FirebaseReferences.USUARIO_REFERENCE);

        UsuariosRef.child(mParam1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Pasamos a la clase usuario el contenido donde apunta la referencia
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                //Pasamos cada valor de la clase usuario a una variable
                nombreU = usuario.getNombre();
                apodoU =  usuario.getApodo();
                emailU = usuario.getEmail();
                fechanacU = usuario.getFechanac();
                ciudadU = usuario.getCiudad();
                marcazapU = usuario.getMarcaZap();
                marcaRelojU = usuario.getMarcaReloj();
                mejorTiempoU = usuario.getMejorTiempo();

                nombre = (TextView) v.findViewById(R.id.textViewNombrePv);
                nombre.setText(nombreU);
                apodo = (TextView) v.findViewById(R.id.textViewNickPv);
                apodo.setText(apodoU);
                email = (TextView) v.findViewById(R.id.textViewEmailPv);
                email.setText(emailU);
                fechanac = (TextView) v.findViewById(R.id.textViewFnacimientoPv);
                fechanac.setText(fechanacU);
                ciudad = (TextView) v.findViewById(R.id.textViewCiudadPv);
                ciudad.setText(ciudadU);
                marcazap = (TextView) v.findViewById(R.id.textViewMarcaZapPv);
                marcazap.setText(marcazapU);
                marcareloj = (TextView) v.findViewById(R.id.textViewMarcaRelojPv);
                marcareloj.setText(marcaRelojU);
                mejortiempo = (TextView) v.findViewById(R.id.textViewMejorTiempoPv);
                mejortiempo.setText(mejorTiempoU);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
