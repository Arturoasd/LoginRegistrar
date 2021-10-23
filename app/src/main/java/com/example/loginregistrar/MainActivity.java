package com.example.loginregistrar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario,edtContraseña;
    Button btnIngresar,btnRegistro;

    AwesomeValidation awesomeValidation;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_login);

        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtContraseña = (EditText)findViewById(R.id.edtContraseña);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null)
        {
            irahome();
        }

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.edtUsuario, Patterns.EMAIL_ADDRESS,R.string.invalid_mail);
        awesomeValidation.addValidation(this,R.id.edtContraseña,".{6,}",R.string.invalid_password);

        btnIngresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (awesomeValidation.validate())
                {
                    String mail = edtUsuario.getText().toString();
                    String pass = edtContraseña.getText().toString();

                    firebaseAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                irahome();
                            }
                            else
                                {
                                String errorCode = ((FirebaseAuthException)task.getException()).getErrorCode();
                                Toast.makeText(MainActivity.this, errorCode, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Registrar.class);
                startActivity(i);
            }
        });

    }

    private void irahome()
    {
        Intent i = new Intent(this,cliente.class);
        i.putExtra("mail",edtUsuario.getText().toString());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}