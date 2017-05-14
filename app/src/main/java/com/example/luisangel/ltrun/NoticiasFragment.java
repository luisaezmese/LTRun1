package com.example.luisangel.ltrun;

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

import com.example.luisangel.ltrun.Usuarios.Comentarios;
import com.example.luisangel.ltrun.Usuarios.FirebaseReferences;
import com.example.luisangel.ltrun.Usuarios.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NoticiasFragment extends Fragment {

    TextView noticiaU, noticiasem;
    EditText noticiaE;
    Button enviar;
    String mParam1;

    public static final String ARG_ALIAS = "param1";

    public NoticiasFragment() {
        // Required empty public constructor
    }

    //RECOGEMOS LOS ARGUMENTOS QUE LE PASAMOS DESDE EL MainActivity
    public static NoticiasFragment newInstance(String param1) {
        NoticiasFragment fragment = new NoticiasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ALIAS, param1);
        fragment.setArguments(args);
        Log.i("ID1", "EL ID ES reg2: "+param1);
        return fragment;
    }


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

        final View v = inflater.inflate(R.layout.fragment_noticias, container, false);




        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference(FirebaseReferences.COMENTARIOS_REFERENCE);
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               String not = String.valueOf(dataSnapshot.getValue());

               String remplazado=not.replace("{", " ");
               String remplazado2=remplazado.replace(",", "\n");
               String remplazado3=remplazado2.replace("}", " ");

                Log.i("NOTICIAS", "NOTICAS: "+remplazado);

                noticiaU = (TextView) v.findViewById(R.id.textViewComentarios);
                noticiaU.setText(remplazado3);
                noticiasem = (TextView) v.findViewById(R.id.textViewNoticia);

                noticiaE = (EditText) v.findViewById(R.id.editTextescribirNot);

                enviar = (Button) v.findViewById(R.id.buttonEnviarNot);

                enviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                DatabaseReference Ref = database.getReference(FirebaseReferences.COMENTARIOS_REFERENCE);

                String noticia = noticiaE.getText().toString();
                //Usuario usuario = new Usuario();
                //String id = usuario.getApodo();
                Ref.child(mParam1).setValue(noticia);
                noticiaE.setText("");

                    }
                });


                /*List<Comentarios> lista_noticias = new ArrayList<>(); //inicializamos la lista donde almacenaremos los objetos Fruta

                JSONObject object = null;

                                                //Creamos un objeto JSON a partir de la cadena
                try {
                    object = new JSONObject(String.valueOf(not1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray json_array = object.optJSONArray("Comentarios");

                for (int i = 0; i < json_array.length(); i++) {
                    try {
                        lista_noticias.add(new Comentarios(json_array.getJSONObject(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                for (int i=0;i< lista_noticias.size();i++){

                    noticiaU = (EditText) v.findViewById(R.id.editTextNoticias);
                    noticiaU.setText((CharSequence) lista_noticias.get(i));

                }*/



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Referencia donde apunta la base de datos
        DatabaseReference UsuariosRef = database.getReference(FirebaseReferences.NOTICIAS_REFERENCE);

        UsuariosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String comentarios = String.valueOf(dataSnapshot.getValue());
                noticiasem.setText(comentarios);

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
    public void onAttach(Context context) {
        super.onAttach(context);
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
