package com.example.luisangel.ltrun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luisangel.ltrun.Usuarios.FirebaseReferences;
import com.example.luisangel.ltrun.Usuarios.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.StringTokenizer;

public class RegistroActivity extends AppCompatActivity {

    String nombreU; //Guardar nombre del usuario
    String apodoU; //Guardar nick del usuario
    String id;
    String fechaNacimientoU;
    String ciudadU;
    String marcazapU;
    String marcaRelojU;
    String mejorTiempoU;
    Button guardar;



    Usuario usuario;

    //Listener sobre el botón para guardar los datos introducidos y pasarselos a la Actividad principal
    public interface buttonListener{
        public void onClickButton(String id);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        final String emailU = getIntent().getStringExtra("id");
        int i=0;
        StringTokenizer st = new StringTokenizer(emailU,"@");
        String[] datos=new String[2];
        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            datos[i]=t;
            i++;
        }
        id=datos[0];

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        guardar = (Button) findViewById(R.id.buttonGuardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nombre = (EditText) findViewById(R.id.editTextNombre);
                nombreU = nombre.getText().toString();
                EditText apodo = (EditText) findViewById(R.id.editTextNick);
                apodoU = apodo.getText().toString();
                //EditText email = (EditText) findViewById(R.id.editTextEmail);
                //emailU = email.getText().toString();
                EditText ciudad = (EditText) findViewById(R.id.editTextCiudad);
                ciudadU = ciudad.getText().toString();
                EditText fechanac = (EditText) findViewById(R.id.editTextFnacimiento);
                fechaNacimientoU = fechanac.getText().toString();
                EditText marcaZap = (EditText) findViewById(R.id.editTextMarcaZap);
                marcazapU = marcaZap.getText().toString();
                EditText marcaReloj = (EditText) findViewById(R.id.editTextMarcaReloj);
                marcaRelojU = marcaReloj.getText().toString();
                EditText mejorTiempo = (EditText) findViewById(R.id.editTextMejorTiempo);
                mejorTiempoU = mejorTiempo.getText().toString();


                usuario = new Usuario(id ,nombreU, apodoU, emailU, ciudadU, fechaNacimientoU, marcazapU, marcaRelojU, mejorTiempoU);
                DatabaseReference UsuariosRef = database.getReference(FirebaseReferences.USUARIO_REFERENCE);
                UsuariosRef.child(id).setValue(usuario);


                Intent inicioActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(inicioActivity);
                Intent intent = new Intent (RegistroActivity.this, PrincipalActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });



    }
}
