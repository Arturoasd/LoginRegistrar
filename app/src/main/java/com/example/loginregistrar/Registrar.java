package com.example.loginregistrar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Registrar extends AppCompatActivity {

    Button btnRegistrar;
    EditText edtCorreo, edtContra;

    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_iniciarsesion_usuario);

        firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.edtCorreo, Patterns.EMAIL_ADDRESS,R.string.invalid_mail);
        awesomeValidation.addValidation(this,R.id.edtContra,".{6,}",R.string.invalid_password);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        edtContra = (EditText) findViewById(R.id.edtContra);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String correo = edtCorreo.getText().toString();
                String contra = edtContra.getText().toString();

                if(awesomeValidation.validate())
                {
                    firebaseAuth.createUserWithEmailAndPassword(correo,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Registrar.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                                {
                                    String errorCode = ((FirebaseAuthException)task.getException()).getErrorCode();
                                    Toast.makeText(Registrar.this, errorCode, Toast.LENGTH_SHORT).show();
                                }
                        }
                    });
                }
                else
                    {
                        Toast.makeText(Registrar.this, "Debe completar todos los datos correctamente", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
}