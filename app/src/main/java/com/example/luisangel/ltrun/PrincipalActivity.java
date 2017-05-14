package com.example.luisangel.ltrun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.luisangel.ltrun.Usuarios.Usuario;
import com.google.firebase.auth.FirebaseAuth;

import java.util.StringTokenizer;

public class PrincipalActivity extends AppCompatActivity implements MenuFragment.MenuFragmentListener,RegistroActivity.buttonListener {

    public Usuario usuario = new Usuario();

    String id;
    Boolean ini=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        id = getIntent().getStringExtra("id");

        Inicio();
    }


    public void Inicio(){

        //Declaramos nuevo Fragment para mostrar
        ContenidoFragment fragment1 = new ContenidoFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenido, fragment1).commit();

    }

    @Override

    //Acciones a realizar al hacer click en cada elemento de la lista según su posición
    public void onListSelected(int position, String item) {
        switch (position) {
            case 0:
                //Si realizamos click en la opción 1=PERFIL cargamos PerfilFragment para introducir los datos del jugador
                    PerfilFragment NuevoPerfil = new PerfilFragment();
                    Bundle args = new Bundle();
                    args.putString(PerfilFragment.ARG_ALIAS, id);
                    NuevoPerfil.setArguments(args);//Encapsulamos dentro del Bundle
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenido, NuevoPerfil).commit();
                break;
            case 1:
                ContenidoFragment contenido = new ContenidoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenido, contenido).commit();
                break;
            case 2:
                CalculaFragment calculadora = new CalculaFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenido, calculadora).commit();
                Toast.makeText(this, item, Toast.LENGTH_LONG).show();
                break;
            case 3:
                NoticiasFragment noticias = new NoticiasFragment();
                Bundle args1 = new Bundle();
                args1.putString(PerfilFragment.ARG_ALIAS, id);
                noticias.setArguments(args1);//Encapsulamos dentro del Bundle
                getSupportFragmentManager().beginTransaction().replace(R.id.contenido, noticias).commit();
                Toast.makeText(this, item, Toast.LENGTH_LONG).show();
                break;
            case 4:
                Intent inicioActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(inicioActivity);
                FirebaseAuth.getInstance().signOut();
                break;
        }

    }

    @Override
    public void onClickButton(String id) {
        usuario.setNombre(id); //Pasamos en nombre introducido
    }


}
