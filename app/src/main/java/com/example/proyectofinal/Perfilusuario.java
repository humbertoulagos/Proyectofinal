package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Perfilusuario extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;


//llamado a los botones para la funcionalidad de activity_perfilusuario.xml

    /*  /*tareas a realizar

foto de perfil----> seleccionar
imagen por defecto
*/


//https://www.youtube.com/watch?v=pebGcA7rTAA&list=PLhcYacorV7U6HHVsfXnWodwEPI0E38BXD&index=10&ab_channel=RedSystemStudio 10:59 */


    Button Jugarbtn, Putuacionesbtn,AcercaDebtn,cerrarsesion;
    TextView Mipuntuaciontxt,Correo,Nombreusuario,Menutxt,uid,Alias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilusuario);

        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();

        //se inicializa la base de datos con su nombre raiz
        firebaseDatabase=FirebaseDatabase.getInstance();
        mDatabase=firebaseDatabase.getReference("Usuarios");

        //visualizar la info de la base de datos en el perfil de usuario



        //TextView llamando a xml
        Mipuntuaciontxt=findViewById(R.id.Mipuntuaciontxt);
        Nombreusuario=findViewById(R.id.Nombreusuario);
        Menutxt=findViewById(R.id.Menutxt);
        Correo=findViewById(R.id.Correo);
        uid=findViewById(R.id.uid);
        Alias=findViewById(R.id.Alias);


        //botones llamando a xml
        Jugarbtn=findViewById(R.id.Jugarbtn);
        Putuacionesbtn=findViewById(R.id.Puntuacionesbtn);
        AcercaDebtn=findViewById(R.id.Acercadebtn);
        cerrarsesion=findViewById(R.id.btnCerrar_perfil);

        //evento de jugar
        Jugarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Perfilusuario.this,"JUGAR",Toast.LENGTH_SHORT).show();
                //aqui debería enlazar a los fragment para llevar al sitio de los tres juegos
                startActivity(new Intent(Perfilusuario.this,gato2.class));

            }
        });

        //evento de puntuaciones
        Putuacionesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Perfilusuario.this,"PUNTUACIONES",Toast.LENGTH_SHORT).show();
                //aqui el tema de ver los puntajes generales de cada jugador
            }
        });

        AcercaDebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Perfilusuario.this,"ACERCA DE",Toast.LENGTH_SHORT).show();
                //información sobre el equipo de quien construyo la app
            }
        });


        //evento de cerrar sesion
        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Perfilusuario.this,login.class));
            }
        });


    }
    @Override
    protected void onStart() {
        Usuarioenlinea();
        super.onStart();
    }

    //se menciona con una notificacion que el jugador esta en linea
    //con un if se menciona si el el usuario tiene un valor distinto a null, aparecerá la notificación
    private void Usuarioenlinea(){
        if(user!=null){
            Toast.makeText(this, "jugador en linea",Toast.LENGTH_SHORT).show();
        }else{
            startActivity(new Intent(Perfilusuario.this,numeros.class));
            finish();
        }

        mostrarinfo();
    }

    //este metodo es para hacer una consulta a la base de datos

    private void mostrarinfo(){

        //consulta

        //con la siguiente consulta se va hacer que lo ordene por correo //se puede ordenar por puntuacion
        Query query=mDatabase.orderByChild("Email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //haciendo un recorrido constante para obtener los datos solicitados
                for(DataSnapshot ds :snapshot.getChildren()){
                    //String mipuntuacionString=""+ds.child("Puntaje").getValue();
                    String nombreusuarioString=""+ds.child("Nombre").getValue();
                    String UidString=""+ds.child("Uid").getValue();
                    String EmailString=""+ds.child("Email").getValue();

                    //mostrar los datos
                    Nombreusuario.setText(nombreusuarioString);
                    uid.setText(UidString);
                    Correo.setText(EmailString);



                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

        /*
        String id=mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String Uid=snapshot.child("Uid").getValue().toString();
                    String nombre= snapshot.child("Nombre").getValue().toString();
                    String Email= snapshot.child("Email").getValue().toString();
                    String alias= snapshot.child("Alias").getValue().toString();
                    //se puede mostrar el correo, pero hay que incluir otro TextView en activity_guardarperfil

                    Nombreusuario.setText(nombre);
                    Correo.setText(Email);
                    Alias.setText(alias);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/




}