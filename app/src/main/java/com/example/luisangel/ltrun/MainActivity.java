package com.example.luisangel.ltrun;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.luisangel.ltrun.Usuarios.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Definimos objetos view
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private Button buttonRegister;

    //Definimos objeto de tipo Autentificación de Firebase
    private FirebaseAuth mAuth;


    FirebaseAuth.AuthStateListener mAuthListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos views views
        editTextEmail = (EditText) findViewById(R.id.editTextCorreo);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Context context = getApplicationContext();
                if (user !=null){
                    Toast.makeText(context,"SESION INICIADA CON: "+user.getEmail(),Toast.LENGTH_LONG).show();
                    Log.i("SESION", "SESION INICIADA CON: "+user.getEmail());
                }
            }

        };
    }

    private void registrar (final String email, String password){
        //Creamos un nuevo registro de usuario
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Context context = getApplicationContext();
                if(task.isSuccessful()){
                    Toast.makeText(context,"SESION CREADA CORRECTAMENTE",Toast.LENGTH_LONG).show();
                    Log.i("SESION","SESION CREADA CORRECTAMENTE");
                    //Pasamos a la ventana de registro
                    Intent registroActivity = new Intent(getApplicationContext(), RegistroActivity.class);
                    startActivity(registroActivity);
                    //Mandamos el correo como id del registo, lo pasamos al Activity de Registro
                    Intent intent = new Intent (MainActivity.this, RegistroActivity.class);
                    intent.putExtra("id", email);
                    startActivity(intent);
                    Log.i("REGISTRO", "REGISTRO: "+email);
                }else{
                    //Error al realizar el registro
                    Toast.makeText(context,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    Log.i("SESION", task.getException().getMessage());
                }
            }
        });
    }

    private void iniciarSesion (final String email, String password){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("SESION", "signInWithEmail:onComplete:" + task.isSuccessful());
                        //Error al iniciar sesión
                        if (!task.isSuccessful()) {
                            Log.w("SESION", "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, "FALLO DE REGISTRO",
                                    Toast.LENGTH_SHORT).show();
                        //Se ha iniciado sesión
                        }else{
                            //Pasamos a la Activity Principal
                            Intent segundaActivity = new Intent(getApplicationContext(), PrincipalActivity.class);
                            startActivity(segundaActivity);
                            //Pasamos los datos de de id a Principal para pasarselos a Perfil luego.
                            //Cogemos el correo y tan solo se coge la parte de antes de la @ para crear el id
                            Intent intent = new Intent (MainActivity.this, PrincipalActivity.class);
                            int i=0;
                            StringTokenizer st = new StringTokenizer(email,"@");
                            String[] datos=new String[2];
                            while (st.hasMoreTokens()) {
                                String t = st.nextToken();
                                datos[i]=t;
                                i++;
                            }
                            String id=datos[0];
                            intent.putExtra("id", id);
                            startActivity(intent);
                            Log.i("INICIO", "INICIO: "+id);
                        }


                    }
                });
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonSignup:
                String emailinicio = editTextEmail.getText().toString();
                String passwordinicio = editTextPassword.getText().toString();
                iniciarSesion(emailinicio,passwordinicio);
                break;
            case R.id.buttonRegister:
                final String emailreg = editTextEmail.getText().toString();
                String passwordreg = editTextPassword.getText().toString();
                registrar(emailreg,passwordreg);
                break;
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }

    }


}
